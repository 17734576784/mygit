/**   
* @Title: ChargeServiceImpl.java 
* @Package com.ke.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月12日 上午9:46:19 
* @version V1.0   
*/
package com.ke.serviceimpl;

import static com.ke.utils.ConverterUtil.roundTosString;
import static com.ke.utils.ConverterUtil.toByte;
import static com.ke.utils.ConverterUtil.toDouble;
import static com.ke.utils.ConverterUtil.toInt;
import static com.ke.utils.ConverterUtil.toLong;
import static com.ke.utils.ConverterUtil.toStr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.comnt.ComntCfgEVCP;
import com.ke.comnt.ComntDef;
import com.ke.comnt.ComntParaBase;
import com.ke.comnt.ComntParaBase.OpDetailInfo;
import com.ke.comnt.ComntParaBase.RTU_PARA;
import com.ke.comnt.ComntVector;
import com.ke.http.HttpsClientUtil;
import com.ke.http.StreamClosedHttpResponse;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.ChargeMapper;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.mapper.MemberOrdersMapper;
import com.ke.mapper.PileMapper;
import com.ke.model.ChargeMonitor;
import com.ke.model.LoginUser;
import com.ke.model.MemberOrders;
import com.ke.model.OperatorConfig;
import com.ke.service.IChargeService;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.SerializeUtil;

/**
 * @ClassName: ChargeServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月12日 上午9:46:19
 * 
 */
@Service
public class ChargeServiceImpl implements IChargeService {

	@Resource
	private ChargeMapper chargeMapper;

	@Resource
	private PileMapper pileMapper;

	@Resource
	private ChargeMonitorMapper chargeMonitorMapper;

	@Resource
	private MemberOrdersMapper memberOrdersMapper;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: chargeStart
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#chargeStart(java.lang.String)
	 */
	@Override
	public JSONObject chargeStart(String token, String queryJsonStr) throws Exception {

		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.CHARGE).info("接收开始充电请求：" + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String pileNo = json.getString("pileNo");
		String gunNo = json.getString("gunNo");
		String chargeMoney = json.getString("chargeMoney");
		String payserialNumber = json.getString("serialNumber");

		if (pileNo == null || pileNo.isEmpty() || gunNo == null || gunNo.isEmpty() || payserialNumber == null
				|| payserialNumber.isEmpty() || chargeMoney == null || chargeMoney.isEmpty()) {
			LoggerUtil.logger(LogName.CHARGE).info("请求参数错误" + queryJsonStr);
			return CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
		}

		// 将流水号转成12位
		payserialNumber = CommFunc.autoGenericCode(payserialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		// 判断流水号是否已存在
		boolean isExistWasteno = this.chargeMapper.checkWasteno(payserialNumber);
		if (isExistWasteno) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "流水号[" + payserialNumber + "]已存在");
			LoggerUtil.logger(LogName.CHARGE).info("流水号[" + payserialNumber + "]已存在");
			return rtnJson;
		}

		// 判断终端是否在线
		int rtuId = toInt(this.pileMapper.getRtuByPile(pileNo));
		boolean rtuOnline = CommFunc.rtuOnlineFlag(rtuId);
		if (!rtuOnline) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电桩不在线");
			return rtnJson;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		byte gunFlag = Constant.GUN_STATE_NULL;
		// 判断枪类型 1:电动汽车 0:电动自行车
		boolean isCarCharge = this.chargeMapper.checkGunType(param);
		if (isCarCharge) {
			gunFlag = Constant.ELECTRIC_VEHICLE;
		} else {
			gunFlag = Constant.ELECTRIC_BICYCLE;
		}

		int gunState = toInt(this.chargeMapper.getGunState(param));

		Map<String, Object> pileInfo = this.chargeMapper.getPileInfo(param);
		byte cpFlag = toByte(pileInfo.get("cpFlag"));
		int chargeMoneyInt = (int) (toDouble(chargeMoney) * 100);

		// 获取登录用户对应的会员编码
		LoginUser loginUser = CommFunc.getLoginUserByToken(token);
		int memberId = loginUser.getMemberId();

		// 向前置发送充断电请求时，先判断一下当前枪的状态。没有cp信号 直接充电；有cp信号的，枪状态为2(连接)方可充电
		if ((gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.NOCP)
				|| (gunState == Constant.GUN_STATE_CONNECTION && cpFlag == Constant.HAVECP
						&& gunFlag == Constant.ELECTRIC_VEHICLE)
				|| (gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.HAVECP
						&& gunFlag == Constant.ELECTRIC_BICYCLE)) {

			/** 创建充电单和充电记录监控记录 */
			if (!insertChargeOrder(payserialNumber, pileNo, gunNo, chargeMoneyInt, pileInfo, memberId)) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "创建充电单失败");
				return rtnJson;
			}

			// 组织充断电请求结构体
			ComntCfgEVCP.APP_CHARGE appCharge = new ComntCfgEVCP.APP_CHARGE();
			appCharge.appFlag = Constant.CLIENTTYPE_INTERFACE;
			appCharge.pileId = toInt(pileInfo.get("pileId"));
			appCharge.memberId = memberId;
			appCharge.gunId = toInt(gunNo);
			appCharge.cmd = Constant.CHARGE_START;
			appCharge.payType = Constant.PAY_MOENY;
			appCharge.payMoney = chargeMoneyInt;
			appCharge.payAmount = 0d;

			byte[] pileCode_byte = pileNo.getBytes();
			for (int i = 0; i < pileCode_byte.length; i++) {
				appCharge.pileCode[i] = pileCode_byte[i];
			}

			byte[] wasteno_byte = payserialNumber.getBytes();
			for (int i = 0; i < wasteno_byte.length; i++) {
				appCharge.orderWasteno[i] = wasteno_byte[i];
			}

			MemberOrders order = this.memberOrdersMapper.getmemberOrders(payserialNumber);
			if (null == order) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "不存在对应的充电单");
				return rtnJson;
			}

			// 查询充电桩终端及通道信息
			ComntParaBase.RTU_PARA rtuPara = getRtuParam(rtuId, pileInfo);

			ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
			appCharge.toDataStream(task_data_vect);
			StringBuffer opDetail = new StringBuffer();
			if (!sendChargeRequest(ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_WITH_PAY, rtuPara, task_data_vect, opDetail)) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求太频繁,请稍后重试!");
				LoggerUtil.logger(LogName.CHARGE).info("web service 下发错误" + queryJsonStr);
			} else {
				rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
			}
		} else {
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "当前充电枪状态不允许充电");
		}

		LoggerUtil.logger(LogName.CHARGE).info("流水号 ：" + payserialNumber + "请求充电成功！");

		return rtnJson;
	}

	/**
	 * 向前置发送请求
	 */
	private boolean sendChargeRequest(byte requestType, ComntParaBase.RTU_PARA rtuPara, // 需要核对ComntParaBase.RTU_PARA结构是否更改
			ComntVector.ByteVector task_data_vect, StringBuffer opDetail) {
		if (rtuPara == null) {
			opDetail.setLength(0);
			opDetail.append("桩体号对应的终端和通道参数错误");
			return false;
		}

		byte[] task_result = new byte[1];
		// 立即应答结果，此处不需要取，无用,保留
		ArrayList<ComntCfgEVCP.APP_CHARGE_RESULT> ret_data_vect = new ArrayList<ComntCfgEVCP.APP_CHARGE_RESULT>();
		OpDetailInfo detail_info = new OpDetailInfo();

		boolean ret_val = ComntParaBase.get1StepTaskResult("", rtuPara.rtu_id, rtuPara.prot_type, rtuPara, requestType,
				(byte) ComntDef.YD_RTU_RUNSTATE_MAN, task_data_vect, task_result, ret_data_vect, detail_info, opDetail);

		return ret_val;
	}

	/**
	 * @Title: insertChargeOrder @Description: 创建充电单和充电记录监控记录 @param @param
	 *         payserialNumber @param @param pileNo @param @param
	 *         gunNo @param @param chargeMoney @param @return 设定文件 @return
	 *         boolean 返回类型 @throws
	 */
	@Transactional
	private boolean insertChargeOrder(String payserialNumber, String pileNo, String gunNo, int chargeMoney,
			Map<String, Object> pileInfo, int memberId) throws Exception {
		boolean flag = false;

		ChargeMonitor chargeMonitor = new ChargeMonitor();
		chargeMonitor.setSerialnumber(payserialNumber);
		chargeMonitor.setPileCode(pileNo);
		chargeMonitor.setGunId(toInt(gunNo));
		chargeMonitor.setStartDate(new Date());
		chargeMonitor.setChargeMoney(chargeMoney);
		flag = this.chargeMonitorMapper.insertChargeMonitor(chargeMonitor);

		MemberOrders memberOrders = new MemberOrders();
		memberOrders.setMemberId(memberId);
		memberOrders.setOperatorId(toInt(pileInfo.get("operatorId")));
		memberOrders.setSubstId(toInt(pileInfo.get("stationId")));
		memberOrders.setPileId(toInt(pileInfo.get("pileId")));
		memberOrders.setGunId(toInt(gunNo));
		memberOrders.setPileCode(pileNo);

		memberOrders.setSerialnumber(payserialNumber);
		memberOrders.setTradeDate(DateUtil.formatTimesTampDate(new Date()));
		memberOrders.setPrechargeMoney(chargeMoney);
		memberOrders.setPrepayType(Constant.INTERFACE_CHARGING);
		memberOrders.setAppFlag(Constant.CLIENTTYPE_INTERFACE);
		memberOrders.setChargeState(Constant.CHARGINGSTATE_V2_STARTWAITING);

		flag &= this.memberOrdersMapper.insertmemberOrders(memberOrders);
		JedisUtil.hmset(Constant.ORDER + payserialNumber, CommFunc.beanToHMap(memberOrders));

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: chargeOver
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @see com.ke.service.IChargeService#chargeOver(java.lang.String)
	 */
	@Override
	public JSONObject chargeOver(String queryJsonStr) {

		JSONObject rtnJson = new JSONObject();
		String pileNo = "", gunNo = "", paySerialNumber = "";
		LoggerUtil.logger(LogName.CHARGE).info("接收充电结束请求：" + queryJsonStr);

		JSONObject json = JSONObject.parseObject(queryJsonStr);
		pileNo = json.getString("pileNo");
		gunNo = json.getString("gunNo");
		paySerialNumber = json.getString("serialNumber");
		// 将流水号转成12位
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		if (pileNo.isEmpty() || gunNo.isEmpty() || paySerialNumber.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		// 判断终端是否在线
		int rtuId = toInt(this.pileMapper.getRtuByPile(pileNo));
		boolean rtuOnline = CommFunc.rtuOnlineFlag(rtuId);
		if (!rtuOnline) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电桩不在线");
			return rtnJson;
		}

		MemberOrders order = this.memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (null == order) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "不存在对应的充电单");
			return rtnJson;
		}

		// 更新接口充电监控中请求结束充电
		ChargeMonitor chargemonitor = new ChargeMonitor();
		chargemonitor.setSerialnumber(paySerialNumber);
		chargemonitor.setEndDate(new Date());
		chargeMonitorMapper.updateChargeMonitor(chargemonitor);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		// 组织充断电请求结构体
		ComntCfgEVCP.APP_CHARGE appCharge = new ComntCfgEVCP.APP_CHARGE();
		appCharge.appFlag = Constant.CLIENTTYPE_INTERFACE;
		appCharge.memberId = order.getMemberId();
		appCharge.pileId = order.getPileId();
		appCharge.gunId = toInt(gunNo);
		appCharge.cmd = Constant.CHARGE_OVER;
		appCharge.payType = Constant.PAY_MOENY;
		appCharge.payMoney = order.getPrechargeMoney();
		appCharge.payAmount = 0d;

		byte[] pileCode_byte = pileNo.getBytes();
		for (int i = 0; i < pileCode_byte.length; i++) {
			appCharge.pileCode[i] = pileCode_byte[i];
		}

		byte[] wasteno_byte = paySerialNumber.getBytes();
		for (int i = 0; i < wasteno_byte.length; i++) {
			appCharge.orderWasteno[i] = wasteno_byte[i];
		}

		Map<String, Object> pileInfo = this.chargeMapper.getPileInfo(param);

		// 更新接口充电记录中请求结束充电
		ChargeMonitor chargeMonitor = new ChargeMonitor();
		chargeMonitor.setSerialnumber(paySerialNumber);
		chargeMonitor.setEndDate(new Date());
		chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

		// 查询充电桩终端及通道信息
		ComntParaBase.RTU_PARA rtuPara = getRtuParam(rtuId, pileInfo);
		ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
		appCharge.toDataStream(task_data_vect);
		StringBuffer opDetail = new StringBuffer();
		if (!sendChargeRequest(ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_WITH_PAY, rtuPara, task_data_vect, opDetail)) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求太频繁,请稍后重试!");
			LoggerUtil.logger(LogName.CHARGE).info("web service 下发错误" + queryJsonStr);
		} else {
			JedisUtil.hset(Constant.ORDER + paySerialNumber, "chargeState",
					toStr(Constant.CHARGINGSTATE_V2_ENDWAITING));
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		}
		LoggerUtil.logger(LogName.CHARGE).info("流水号：" + paySerialNumber + "请求结束充电成功");
		return rtnJson;
	}

	/**
	 * @Title: getRtuParam @Description: 查询充电桩终端及通道信息 @param @param
	 *         rtuId @param @param pileInfo @param @return 设定文件 @return RTU_PARA
	 *         返回类型 @throws
	 */
	private RTU_PARA getRtuParam(int rtuId, Map<String, Object> pileInfo) {
		ComntParaBase.RTU_PARA rtuPara = new RTU_PARA();
		rtuPara.rtu_id = rtuId;
		rtuPara.prot_type = toByte(pileInfo.get("protocol"));
		rtuPara.timeout = Constant.CHARGE_TIMEOUT;
		return rtuPara;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getChargeData
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @see com.ke.service.IChargeService#getChargeData(java.lang.String)
	 */
	@Override
	public JSONObject getChargeData(String queryJsonStr) {
		// TODO Auto-generated method stub

		JSONObject rtnJson = new JSONObject();
		String paySerialNumber = ""; // 流水号

		String chargTotalElectricity = ""; // 充电总电量
		String chargHighElectricity = ""; // 充电峰电量
		String chargFlatElectricity = ""; // 充电平电量
		String chargLowElectricity = ""; // 充电谷电量
		String chargTipElectricity = ""; // 充电尖电量

		String AVoltage = ""; // A相电压
		String BVoltage = ""; // B相电压
		String CVoltage = ""; // C相电压

		String ACurrent = ""; // A相电流
		String BCurrent = ""; // B相电流
		String CCurrent = ""; // C相电流
		LoggerUtil.logger(LogName.CHARGE).info("接收获取充电过程信息请求:" + queryJsonStr);

		JSONObject json = JSONObject.parseObject(queryJsonStr);
		paySerialNumber = json.getString("serialNumber");
		// 将流水号转成12位
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		if (paySerialNumber.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		Map<String, String> gunStateMap = getGunStateMap(paySerialNumber);
		if (null == gunStateMap || gunStateMap.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电枪状态不存在");
			return rtnJson;
		}

		// 充电状态时 获取数据；其他状态时，返回空值
		if (toInt(gunStateMap.get("status")) == Constant.GUN_STATE_CHARGING) {

			chargTotalElectricity = toStr(gunStateMap.get("charg_total_electricity"));
			chargHighElectricity = toStr(gunStateMap.get("charg_high_electricity"));
			chargFlatElectricity = toStr(gunStateMap.get("charg_flat_electricity"));
			chargLowElectricity = toStr(gunStateMap.get("charg_low_electricity"));
			chargTipElectricity = toStr(gunStateMap.get("charg_tip_electricity"));

			AVoltage = toStr(gunStateMap.get("va"));
			BVoltage = toStr(gunStateMap.get("vb"));
			CVoltage = toStr(gunStateMap.get("vc"));

			ACurrent = toStr(gunStateMap.get("ia"));
			BCurrent = toStr(gunStateMap.get("ib"));
			CCurrent = toStr(gunStateMap.get("ic"));
		}

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");

		rtnJson.put("chargTotalElectricity", chargTotalElectricity);
		rtnJson.put("chargHighElectricity", chargHighElectricity);
		rtnJson.put("chargFlatElectricity", chargFlatElectricity);
		rtnJson.put("chargLowElectricity", chargLowElectricity);
		rtnJson.put("chargTipElectricity", chargTipElectricity);

		rtnJson.put("AVoltage", AVoltage);
		rtnJson.put("BVoltage", BVoltage);
		rtnJson.put("CVoltage", CVoltage);

		rtnJson.put("ACurrent", ACurrent);
		rtnJson.put("BCurrent", BCurrent);
		rtnJson.put("CCurrent", CCurrent);

		LoggerUtil.logger(LogName.CHARGE).info("获取充电过程请求结束 ,返回结果: " + rtnJson.toString());

		return rtnJson;
	}

	private Map<String, String> getGunStateMap(String paySerialNumber) {
		String orderKey = Constant.ORDER + paySerialNumber;
		Map<String, String> orderMap = JedisUtil.hgetAll(orderKey);
		String gunStateKey = Constant.GUNSTATE + orderMap.get("pileId") + "_" + orderMap.get("gunId");
		if (orderMap.isEmpty()) {
			MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
			gunStateKey = Constant.GUNSTATE + memberOrders.getPileId() + "_" + memberOrders.getGunId();
		}

		Map<String, String> gunStateMap = JedisUtil.hgetAll(gunStateKey);
		return gunStateMap;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getChargeRealData
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @see com.ke.service.IChargeService#getChargeRealData(java.lang.String)
	 */
	@Override
	public JSONObject getChargeRealData(String queryJsonStr) {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		String paySerialNumber = ""; // 流水号

		String pileNo = "";
		byte gunNo = 0;
		byte pileType = 0;
		double chargElectricity = 0D;
		int chargeSecond = 0, chargeMoney = 0, remainSecond = 0, SOC = 0;
		LoggerUtil.logger(LogName.CHARGE).info("接收获取充电过程实时信息请求:" + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		paySerialNumber = json.getString("serialNumber");
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		if (paySerialNumber.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		Map<String, String> gunStateMap = getGunStateMap(paySerialNumber);
		if (null == gunStateMap || gunStateMap.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电枪状态不存在");
			return rtnJson;
		}

		// 充电状态时 获取数据；其他状态时，返回空值
		if (toInt(gunStateMap.get("status")) == Constant.GUN_STATE_CHARGING) {

			Map<String, Object> realData = this.chargeMapper.getChargeRealData(paySerialNumber);
			pileNo = toStr(realData.get("pileNo"));
			gunNo = toByte(realData.get("gunNo"));
			pileType = toByte(realData.get("pileType"));
			chargElectricity = toInt(gunStateMap.get("chargElectricity"));
			chargeSecond = toInt(gunStateMap.get("chargeTm"));
			chargeMoney = toInt(gunStateMap.get("chargeMoney"));
			remainSecond = toInt(gunStateMap.get("remainTm"));
			SOC = toInt(gunStateMap.get("soc"));
		}
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");

		rtnJson.put("pileNo", pileNo);
		rtnJson.put("gunNo", gunNo);
		rtnJson.put("pileType", pileType);
		rtnJson.put("chargElectricity", chargElectricity);
		rtnJson.put("chargeSecond", chargeSecond);
		rtnJson.put("chargeMoney", chargeMoney);
		rtnJson.put("remainSecond", remainSecond);
		rtnJson.put("SOC", SOC);

		LoggerUtil.logger(LogName.CHARGE).info("获取充电过程返回结果 : " + rtnJson.toString());

		return rtnJson;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendChargOverRequest
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @see com.ke.service.IChargeService#SendChargOverRequest(java.lang.String,
	 *      int)
	 */
	@Override
	public boolean SendChargeOverRequest(JSONObject json, int retry) {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (memberOrders == null) {
			return false;
		}

		int operatorId = memberOrders.getOperatorId();
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);

		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", toLong(paySerialNumber));

		String url = operatorConfig.getChargeOverUrl();
		if (url == null || url.isEmpty()) {
			return false;
		}

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送充电结束消息：{} ,接收内容：() ", json.toString(), response.getContent());
			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {

				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.ERROR).error("丢弃充电结束消息推送,流水号：" + json.toString());
				}

				ChargeMonitor chargeMonitor = new ChargeMonitor();
				chargeMonitor.setSerialnumber(paySerialNumber);
				chargeMonitor.setEndPush(Constant.PUSHED);
				chargeMonitor.setEndPushTime(new Date());
				chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
				flag = true;
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeOverRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送充电结束请求异常,发送内容：" + json.toString(), e);
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendChargStartRequest
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @see com.ke.service.IChargeService#SendChargStartRequest(java.lang.String,
	 *      int)
	 */
	@Override
	public boolean SendChargeStartRequest(JSONObject json, int retry) {

		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (memberOrders == null) {
			return false;
		}
		int operatorId = memberOrders.getOperatorId();
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", toLong(paySerialNumber));

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));

			String url = operatorConfig.getChargeStartUrl();
			if (url == null || url.trim().isEmpty()) {
				return false;
			}

			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送充电开始消息：{} ,返回结果 ：{} ", json.toString(), response.getContent());

			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {
				flag = true;
				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.CHARGE).info("丢弃充电开始消息推送	流水号：" + json.toString());
				}

				ChargeMonitor chargeMonitor = new ChargeMonitor();
				chargeMonitor.setSerialnumber(paySerialNumber);
				chargeMonitor.setStartPush((byte) 1);
				chargeMonitor.setStartPushTime(new Date());
				chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeStartRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送充电开始消息异常，发送内容：" + json.toString(), e);
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendDCChargInfoRequest
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @see com.ke.service.IChargeService#SendDCChargInfoRequest(java.lang.String,
	 *      int)
	 */
	@Override
	public boolean SendDCChargeInfoRequest(JSONObject json, int retry) {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (memberOrders == null) {
			return false;
		}
		int operatorId = memberOrders.getOperatorId();
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", toLong(paySerialNumber));

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			String url = operatorConfig.getChargeDcInfoUrl();
			if (url == null || url.trim().isEmpty()) {
				return false;
			}

			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送直流充电信息消息:{} 接收内容： {}", json.toString(), response.getContent());

			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {

				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.ERROR).error("丢弃直流充电信息推送	流水号：" + json.toString());
				}

				ChargeMonitor chargeMonitor = new ChargeMonitor();
				chargeMonitor.setSerialnumber(paySerialNumber);
				chargeMonitor.setSocPush(Constant.PUSHED);
				chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
				flag = true;
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendDCChargeInfoRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送直流充电信息请求异常,发送内容：" + json.toString(), e);
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getPileChargeRcd
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @throws IllegalAccessException
	 * @see com.ke.service.IChargeService#getPileChargeRcd(java.lang.String)
	 */
	@Override
	public JSONObject getPileChargeRcd(String queryJsonStr) throws IllegalAccessException {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.CHARGE).info("接送获取充电桩充电记录请求:" + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String paySerialNumber = json.getString("serialNumber");
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		String pileNo = json.getString("pileNo");
		String gunNo = json.getString("gunNo");
		if (paySerialNumber.isEmpty() || pileNo.isEmpty() || gunNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "请求参数错误");
			return rtnJson;
		}

		boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
		if (!checkWasteNo) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "流水号非法");
			return rtnJson;
		}

		Calendar calendar = Calendar.getInstance();
		int nowYear = DateUtil.getYear(calendar.getTime());
		calendar.add(Calendar.DAY_OF_YEAR, -180);
		int lastYear = DateUtil.getYear(calendar.getTime());

		List<Integer> yearList = new ArrayList<Integer>();
		yearList.add(nowYear);

		if (nowYear != lastYear && lastYear >= Constant.INIT_YEAR) {
			yearList.add(lastYear);
		}

		// 查询最近180天内的数据
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yearList", yearList);
		param.put("serialNumber", paySerialNumber);
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		Map<String, Object> chargeRcd = this.chargeMapper.getPileChargeRcd(param);
		if (chargeRcd == null) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "180天内不存在【" + paySerialNumber + "】的充电记录");
			return rtnJson;
		}

		double totalElectricity = toDouble(chargeRcd.get("totalElectricity"));
		double chargeMoney = toDouble(chargeRcd.get("chargeMoney")) / 100;
		double serviceMoney = toDouble(chargeRcd.get("serviceMoney")) / 100;
		int endCause = toInt(chargeRcd.get("endCause"));

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");

		rtnJson.put("serialNumber", toLong(paySerialNumber));
		rtnJson.put("pileNo", pileNo);
		rtnJson.put("gunNo", gunNo);
		rtnJson.put("startDate", DateUtil.formatTimesTampDate((Date) chargeRcd.get("startDate")));
		rtnJson.put("endDate", DateUtil.formatTimesTampDate((Date) chargeRcd.get("endDate")));

		rtnJson.put("totalElectricity", roundTosString(totalElectricity, 2));
		rtnJson.put("chargeMoney", roundTosString(chargeMoney, 2));
		rtnJson.put("serviceMoney", roundTosString(serviceMoney, 2));

		rtnJson.put("endCause", getendCause(endCause));

		return rtnJson;
	}

	/**
	 * @Title: getendCause @Description: 获取充电结束原因 @param @param
	 *         endCause @param @return 设定文件 @return String 返回类型 @throws
	 */
	private String getendCause(int endCause) {
		Map<String, String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
		return endCauseMap.get(toStr(endCause));
	}

	/**
	 * @Title: getOperatorToken @Description: 获取运营商token @param @param
	 *         operatorId @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	public boolean getOperatorToken(int operatorId) {
		boolean flag = false;
		try {

			String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
			OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				LoggerUtil.logger(LogName.INFO).info("不存在合作伙伴信信息，重新加载:partnderId=" + operatorId);
				return flag;
			}
			JSONObject urlJson = new JSONObject();
			String loginUrl = operatorConfig.getLoginUrl();
			if (null == loginUrl || loginUrl.isEmpty()) {
				return flag;
			}

			urlJson.put("loginUrl", operatorConfig.getLoginUrl());
			urlJson.put("userName", operatorConfig.getUsername());
			urlJson.put("password", operatorConfig.getPassword());

			String rtnToken = sendLoginTokenRequest(urlJson);
			if (!rtnToken.isEmpty()) {
				operatorConfig.setToken(rtnToken);
				JedisUtil.set(key.getBytes(), SerializeUtil.serialize(operatorConfig));
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(e.getMessage());
		}
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: sendLoginTokenRequest
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#sendLoginTokenRequest(com.alibaba.fastjson.JSONObject)
	 */
	@Override
	public String sendLoginTokenRequest(JSONObject json) {
		// TODO Auto-generated method stub
		String token = "";
		Map<String, String> param = new HashMap<String, String>();
		JSONObject urlJson = new JSONObject();
		urlJson.put("userName", json.getString("userName"));
		urlJson.put("password", json.getString("passWord"));
		param.put("queryJsonStr", json.toString());

		String sendLoginUrl = json.getString("loginUrl");
		if (sendLoginUrl.isEmpty()) {
			return token;
		}

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = null;
		try {
			response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(sendLoginUrl, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == response) {
			return token;
		}

		json = JSONObject.parseObject(response.getContent());
		if (json.getInteger("status") == Constant.SUCCESS) {
			// 获取token
			if (json.containsKey("token")) {
				token = json.getString("token");
				LoggerUtil.logger(LogName.INFO).info("请求token成功：token：" + token);
			} else {
				LoggerUtil.logger(LogName.INFO).info("没有返回token");
			}
		} else {
			LoggerUtil.logger(LogName.ERROR).error("状态码：{} 错误描述：{}", json.getIntValue("status"),
					json.getString("error"));
		}

		return token;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: chargeControl
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param token
	 * @param queryJsonStr
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#chargeControl(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public JSONObject chargeControl(String token, String queryJsonStr) throws Exception {
		LoggerUtil.logger(LogName.CHARGE).info("接收开始充电请求：" + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		Integer cmd = json.getIntValue("cmd");
		if (cmd == Constant.CHARGE_START) {
			return hydropwerStart(json, cmd, token);
		} else if (cmd == Constant.CHARGE_OVER) {
			return hydropwerOver(json, cmd, token);
		}

		return null;
	}

	/**
	 * 水电桩结束 @Title: hydropwerOver @param @param json @param @param
	 * cmd @param @param token @param @return @param @throws Exception
	 * 设定文件 @return JSONObject 返回类型 @throws
	 */
	private JSONObject hydropwerOver(JSONObject json, int cmd, String token) throws Exception {
		JSONObject rtnJson = new JSONObject();
		String pileNo = "", gunNo = "", paySerialNumber = "";
		LoggerUtil.logger(LogName.CHARGE).info("接收充电结束请求：" + json.toJSONString());
		pileNo = json.getString("pileNo");
		gunNo = json.getString("gunNo");
		paySerialNumber = json.getString("serialNumber");
		// 将流水号转成12位
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		if (pileNo.isEmpty() || gunNo.isEmpty() || paySerialNumber.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		// 判断终端是否在线
		int rtuId = toInt(this.pileMapper.getRtuByPile(pileNo));
		boolean rtuOnline = CommFunc.rtuOnlineFlag(rtuId);
		if (!rtuOnline) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电桩不在线");
			return rtnJson;
		}

		MemberOrders order = this.memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (null == order) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "不存在对应的充电单");
			return rtnJson;
		}

		// 更新接口充电监控中请求结束充电
		ChargeMonitor chargemonitor = new ChargeMonitor();
		chargemonitor.setSerialnumber(paySerialNumber);
		chargemonitor.setEndDate(new Date());
		chargeMonitorMapper.updateChargeMonitor(chargemonitor);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		// 组织充断电请求结构体
		ComntCfgEVCP.APP_CHARGE appCharge = new ComntCfgEVCP.APP_CHARGE();
		appCharge.appFlag = Constant.CLIENTTYPE_INTERFACE;
		appCharge.memberId = order.getMemberId();
		appCharge.pileId = order.getPileId();
		appCharge.gunId = toInt(gunNo);
		appCharge.cmd = cmd;
		appCharge.payType = Constant.PAY_MOENY;
		appCharge.payMoney = order.getPrechargeMoney();
		appCharge.payAmount = 0d;

		byte[] pileCode_byte = pileNo.getBytes();
		for (int i = 0; i < pileCode_byte.length; i++) {
			appCharge.pileCode[i] = pileCode_byte[i];
		}

		byte[] wasteno_byte = paySerialNumber.getBytes();
		for (int i = 0; i < wasteno_byte.length; i++) {
			appCharge.orderWasteno[i] = wasteno_byte[i];
		}

		Map<String, Object> pileInfo = this.chargeMapper.getPileInfo(param);

		// 更新接口充电记录中请求结束充电
		ChargeMonitor chargeMonitor = new ChargeMonitor();
		chargeMonitor.setSerialnumber(paySerialNumber);
		chargeMonitor.setEndDate(new Date());
		chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

		// 查询充电桩终端及通道信息
		ComntParaBase.RTU_PARA rtuPara = getRtuParam(rtuId, pileInfo);
		ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
		appCharge.toDataStream(task_data_vect);
		StringBuffer opDetail = new StringBuffer();
		if (!sendChargeRequest(ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_HYDROPWER, rtuPara, task_data_vect, opDetail)) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求太频繁,请稍后重试!");
			LoggerUtil.logger(LogName.CHARGE).info("web service 下发错误" + json.toJSONString());
		} else {
			JedisUtil.hset(Constant.ORDER + paySerialNumber, "chargeState",
					toStr(Constant.CHARGINGSTATE_V2_ENDWAITING));
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		}
		LoggerUtil.logger(LogName.CHARGE).info("流水号：" + paySerialNumber + "请求结束充电成功");
		return rtnJson;
	}

	/**
	 * 水电桩开始充电 @Title: hydropwerStart @param @param json @param @param
	 * cmd @param @param token @param @return @param @throws Exception
	 * 设定文件 @return JSONObject 返回类型 @throws
	 */
	private JSONObject hydropwerStart(JSONObject json, int cmd, String token) throws Exception {
		JSONObject rtnJson = new JSONObject();

		String pileNo = json.getString("pileNo");
		String gunNo = json.getString("gunNo");
		String payserialNumber = json.getString("serialNumber");

		if (pileNo == null || pileNo.isEmpty() || gunNo == null || gunNo.isEmpty() || payserialNumber == null
				|| payserialNumber.isEmpty()) {
			LoggerUtil.logger(LogName.CHARGE).info("请求参数错误" + json.toJSONString());
			return CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
		}

		// 将流水号转成12位
		payserialNumber = CommFunc.autoGenericCode(payserialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		// 判断流水号是否已存在
		boolean isExistWasteno = this.chargeMapper.checkWasteno(payserialNumber);
		if (isExistWasteno) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "流水号[" + payserialNumber + "]已存在");
			LoggerUtil.logger(LogName.CHARGE).info("流水号[" + payserialNumber + "]已存在");
			return rtnJson;
		}

		// 判断终端是否在线
		int rtuId = toInt(this.pileMapper.getRtuByPile(pileNo));
		boolean rtuOnline = CommFunc.rtuOnlineFlag(rtuId);
		if (!rtuOnline) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电桩不在线");
			return rtnJson;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		byte gunFlag = Constant.GUN_STATE_NULL;
		// 判断枪类型 1:电动汽车 0:电动自行车
		boolean isCarCharge = this.chargeMapper.checkGunType(param);
		if (isCarCharge) {
			gunFlag = Constant.ELECTRIC_VEHICLE;
		} else {
			gunFlag = Constant.ELECTRIC_BICYCLE;
		}

		int gunState = toInt(this.chargeMapper.getGunState(param));

		Map<String, Object> pileInfo = this.chargeMapper.getPileInfo(param);
		byte cpFlag = 0;// toByte(pileInfo.get("cpFlag"));
		int chargeMoneyInt = Constant.ZERO;

		// 获取登录用户对应的会员编码
		LoginUser loginUser = CommFunc.getLoginUserByToken(token);
		int memberId = loginUser.getMemberId();

		// 向前置发送充断电请求时，先判断一下当前枪的状态。没有cp信号 直接充电；有cp信号的，枪状态为2(连接)方可充电
		if ((gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.NOCP)
				|| (gunState == Constant.GUN_STATE_CONNECTION && cpFlag == Constant.HAVECP
						&& gunFlag == Constant.ELECTRIC_VEHICLE)
				|| (gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.HAVECP
						&& gunFlag == Constant.ELECTRIC_BICYCLE)) {

			/** 创建充电单和充电记录监控记录 */
			if (!insertChargeOrder(payserialNumber, pileNo, gunNo, chargeMoneyInt, pileInfo, memberId)) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "创建充电单失败");
				return rtnJson;
			}

			// 组织充断电请求结构体
			ComntCfgEVCP.APP_CHARGE appCharge = new ComntCfgEVCP.APP_CHARGE();
			appCharge.appFlag = Constant.CLIENTTYPE_INTERFACE;
			appCharge.pileId = toInt(pileInfo.get("pileId"));
			appCharge.memberId = memberId;
			appCharge.gunId = toInt(gunNo);
			appCharge.cmd = cmd;
			appCharge.payType = Constant.ZERO;
			appCharge.payMoney = chargeMoneyInt;
			appCharge.payAmount = 0d;

			byte[] pileCode_byte = pileNo.getBytes();
			for (int i = 0; i < pileCode_byte.length; i++) {
				appCharge.pileCode[i] = pileCode_byte[i];
			}

			byte[] wasteno_byte = payserialNumber.getBytes();
			for (int i = 0; i < wasteno_byte.length; i++) {
				appCharge.orderWasteno[i] = wasteno_byte[i];
			}

			MemberOrders order = this.memberOrdersMapper.getmemberOrders(payserialNumber);
			if (null == order) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "不存在对应的充电单");
				return rtnJson;
			}

			// 查询充电桩终端及通道信息
			ComntParaBase.RTU_PARA rtuPara = getRtuParam(rtuId, pileInfo);

			ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
			appCharge.toDataStream(task_data_vect);
			StringBuffer opDetail = new StringBuffer();
			if (!sendChargeRequest(ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_HYDROPWER, rtuPara, task_data_vect, opDetail)) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求太频繁,请稍后重试!");
				LoggerUtil.logger(LogName.CHARGE).info("web service 下发错误" + json.toJSONString());
			} else {
				rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
			}
		} else {
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "当前充电枪状态不允许充电");
		}

		LoggerUtil.logger(LogName.CHARGE).info("流水号 ：" + payserialNumber + "请求充电成功！");
		return rtnJson;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getPileRecord
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param queryJsonStr
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#getPileRecord(java.lang.String)
	 */
	@Override
	public JSONObject getPileRecord(String queryJsonStr) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.CHARGE).info("接送获取充电桩充电记录请求:" + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String paySerialNumber = json.getString("serialNumber");
		paySerialNumber = CommFunc.autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);

		String pileNo = json.getString("pileNo");
		String gunNo = json.getString("gunNo");
		if (paySerialNumber.isEmpty() || pileNo.isEmpty() || gunNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "请求参数错误");
			return rtnJson;
		}

		boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
		if (!checkWasteNo) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "流水号非法");
			return rtnJson;
		}

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, -180);

		List<Integer> monthList = new ArrayList<Integer>();
		while (calendar.getTimeInMillis() >= calendar1.getTimeInMillis()) {
			monthList.add(ConverterUtil.toInt(DateUtil.formatDateByFormat(calendar.getTime(), "yyyyMM")));
			calendar.add(Calendar.MONTH, -1);
		}

		// 查询最近180天内的数据
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("monthList", monthList);
		param.put("serialNumber", paySerialNumber);
		param.put("pileNo", pileNo);
		param.put("gunNo", gunNo);

		Map<String, Object> chargeRcd = this.chargeMapper.getPileRecord(param);
		if (chargeRcd == null) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "180天内不存在【" + paySerialNumber + "】的充电记录");
			return rtnJson;
		}

		double beginReadings = toDouble(chargeRcd.get("beginReadings"));
		double endReadings = toDouble(chargeRcd.get("endReadings"));

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");

		rtnJson.put("serialNumber", toLong(paySerialNumber));
		rtnJson.put("pileNo", pileNo);
		rtnJson.put("gunNo", gunNo);
		rtnJson.put("startDate", DateUtil.formatTimesTampDate((Date) chargeRcd.get("startDate")));
		rtnJson.put("endDate", DateUtil.formatTimesTampDate((Date) chargeRcd.get("endDate")));

		rtnJson.put("beginReadings", roundTosString(beginReadings, 4));
		rtnJson.put("endReadings", roundTosString(endReadings, 4));

		return rtnJson;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendHydroPowerChargeStart
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#SendHydroPowerChargeStart(com.alibaba.fastjson.JSONObject,
	 *      int)
	 */
	@Override
	public boolean SendHydroPowerChargeStart(JSONObject json, int retry) throws Exception {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (memberOrders == null) {
			return false;
		}
		int operatorId = memberOrders.getOperatorId();
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", toLong(paySerialNumber));

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));

			String url = operatorConfig.getChargeStartUrl();
			if (url == null || url.trim().isEmpty()) {
				return false;
			}

			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送充电开始消息：{} ,返回结果 ：{} ", json.toString(), response.getContent());

			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {
				flag = true;
				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.CHARGE).info("丢弃充电开始消息推送	流水号：" + json.toString());
				}

				ChargeMonitor chargeMonitor = new ChargeMonitor();
				chargeMonitor.setSerialnumber(paySerialNumber);
				chargeMonitor.setStartPush((byte) 1);
				chargeMonitor.setStartPushTime(new Date());
				chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeStartRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送充电开始消息异常，发送内容：" + json.toString(), e);
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendHydroPowerChargeOver
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#SendHydroPowerChargeOver(com.alibaba.fastjson.JSONObject,
	 *      int)
	 */
	@Override
	public boolean SendHydroPowerChargeOver(JSONObject json, int retry) throws Exception {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		MemberOrders memberOrders = memberOrdersMapper.getmemberOrders(paySerialNumber);
		if (memberOrders == null) {
			return false;
		}

		int operatorId = memberOrders.getOperatorId();
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);

		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", toLong(paySerialNumber));

		String url = operatorConfig.getChargeOverUrl();
		if (url == null || url.isEmpty()) {
			return false;
		}

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送充电结束消息：{} ,接收内容：() ", json.toString(), response.getContent());
			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {

				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.ERROR).error("丢弃充电结束消息推送,流水号：" + json.toString());
				}

				ChargeMonitor chargeMonitor = new ChargeMonitor();
				chargeMonitor.setSerialnumber(paySerialNumber);
				chargeMonitor.setEndPush(Constant.PUSHED);
				chargeMonitor.setEndPushTime(new Date());
				chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
				flag = true;
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeOverRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送充电结束请求异常,发送内容：" + json.toString(), e);
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendHydroPowerHeartBeat
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#SendHydroPowerHeartBeat(com.alibaba.fastjson.JSONObject,
	 *      int)
	 */
	@Override
	public boolean SendHydroPowerHeartBeat(JSONObject json, int retry) throws Exception {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		int operatorId = json.getIntValue("operatorId");
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);
		String url = operatorConfig.getChargeHeartUrl();
		if (url == null || url.isEmpty()) {
			return false;
		}
		json.remove("operatorId");
		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(json.toJSONString(), "UTF-8"));

			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送水电桩心跳消息：{} ,接收内容：() ", json.toString(), response.getContent());
			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {
				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.ERROR).error("丢弃充电水电桩心跳推送,：" + json.toString());
				}
				flag = true;
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());
				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeOverRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送水电桩心跳消息异常,发送内容：" + json.toString(), e);
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: SendHydroPowerAlarm
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param json
	 * @param retry
	 * @return
	 * @throws Exception
	 * @see com.ke.service.IChargeService#SendHydroPowerAlarm(com.alibaba.fastjson.JSONObject,
	 *      int)
	 */
	@Override
	public boolean SendHydroPowerAlarm(JSONObject json, int retry) throws Exception {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		String pileNo = json.getString("pileNo");
		int operatorId = this.pileMapper.getOperatorIdByPileNo(pileNo);
		String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
		if (null == operatorConfig) {
			return false;
		}

		String token = operatorConfig.getToken();
		if (null == token || token.isEmpty()) {
			getOperatorToken(operatorId);
			operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return false;
			}
			token = operatorConfig.getToken();
		}

		param.put("token", token);

		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);

		String url = operatorConfig.getChargeAlramUrl();
		if (url == null || url.isEmpty()) {
			return false;
		}

		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse response = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(url, param);
			if (null == response) {
				return false;
			}

			JSONObject jsonItem = JSONObject.parseObject(response.getContent());
			int status = jsonItem.getIntValue("status");
			LoggerUtil.logger(LogName.CHARGE).info("发送充电结束消息：{} ,接收内容：() ", json.toString(), response.getContent());
			if (status == Constant.SUCCESS || status == Constant.REQUEST_BAD) {

				if (status == Constant.REQUEST_BAD) {
					LoggerUtil.logger(LogName.ERROR).error("丢弃充电结束消息推送,流水号：" + json.toString());
				}

				flag = true;
			} else if (status == Constant.TIME_OUT) { // 身份过期
				LoggerUtil.logger(LogName.CHARGE).info("身份过期 重新获取token,发送内容：" + json.toString());

				if (retry == 0) {
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				getOperatorToken(operatorId);
				SendChargeOverRequest(json, retry);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送充电结束请求异常,发送内容：" + json.toString(), e);
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
}
