package com.wo.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;

import com.wo.http.HttpUtil;
import com.wo.util.WebConfig;
import com.wo.comnt.ComntDef;
import com.wo.comnt.ComntParaBase;
import com.wo.comnt.ComntVector;
import com.wo.comnt.ComntCfgEVCP;
import com.wo.comnt.ComntParaBase.OpDetailInfo;
import com.wo.mapper.ChargeMapper;
import com.wo.mapper.ChargeRecordMapper;
import com.wo.mapper.PileMapper;
import com.wo.mapper.YysCarownerOrderMapper;
import com.wo.model.ChargeRecord;
import com.wo.model.PartnerConfig;
import com.wo.model.YysCarownerOrder;
import com.wo.service.IChargeService;
import static com.wo.util.CommFunc.*;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
@Service
public class ChargeServiceImpl implements IChargeService{
	@Resource
	private ChargeMapper chargeMapper;
	@Resource
	private PileMapper pileMapper;
	@Resource
	private ChargeRecordMapper chargeRecordMapper;

	@Resource
	private YysCarownerOrderMapper yysCarownerOrderMapper;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wo.service.IChargeService#chargeStart(java.lang.String)
	 */
	public JSONObject chargeStart(String queryJsonStr) {
		
		JSONObject rtnJson = new JSONObject();
		String pileNo = "", gunNo = "", payserialNumber = "", chargeMoney = "";
		Log4jUtil.getChargeinfo().info("接收开始充电请求：" + queryJsonStr);
		try {			
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			pileNo = json.optString("pileNo");
			gunNo = json.optString("gunNo");
			chargeMoney = json.optString("chargeMoney");
			payserialNumber = json.optString("serialNumber");
			// 将流水号转成12位
			payserialNumber = autoGenericCode(payserialNumber, Constant.PAY_SERIALNUMBER_LENGTH);
			
			if (pileNo.isEmpty() || gunNo.isEmpty()
					|| payserialNumber.isEmpty() || chargeMoney.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				Log4jUtil.getChargeinfo().info("请求参数错误" + queryJsonStr);
				return rtnJson;
			}
			
			//判断流水号是否已存在
			boolean isExistWasteno = this.chargeMapper.checkWasteno(payserialNumber);
			if (isExistWasteno) {
				rtnJson = errorInfo(Constant.Err, "流水号["	+ payserialNumber + "]已存在");
				Log4jUtil.getChargeinfo().info("流水号["	+ payserialNumber + "]已存在");
				return rtnJson;
			}
			
			//判断终端是否在线
			int rtuId = objToInt(this.pileMapper.getRtuByPile(pileNo));
			boolean rtuOnline = rtuOnlineFlag(rtuId);
			if (!rtuOnline) {
				rtnJson = errorInfo(Constant.Err, "充电桩不在线");
				return rtnJson;
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pileNo", pileNo);
			param.put("gunNo", gunNo);
			int gunState = objToInt(this.chargeMapper.getGunState(param));
			
			byte gunFlag = Constant.GUN_STATE_NULL;
			// 判断枪类型 1:电动汽车 0:电动自行车
			boolean isCarCharge = this.chargeMapper.checkGunType(param);
			if (isCarCharge) {
				gunFlag = Constant.ELECTRIC_VEHICLE;
			} else {
				gunFlag = Constant.ELECTRIC_BICYCLE;
			}
			
			byte cpFlag = objToByte(this.chargeMapper.loadPileCPFlag(param));
			// 向前置发送充断电请求时，先判断一下当前枪的状态。没有cp信号 直接充电；有cp信号的，枪状态为2(连接)方可充电
			if ((gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.NOCP)
					|| (gunState == Constant.GUN_STATE_CONNECTION && cpFlag == Constant.HAVECP && gunFlag == Constant.ELECTRIC_VEHICLE)
					|| (gunState < Constant.GUN_STATE_CHARGING && cpFlag == Constant.HAVECP && gunFlag == Constant.ELECTRIC_BICYCLE)) {
 				
				/**创建充电单和充电记录监控记录*/
				if (!insertChargeOrder(payserialNumber,pileNo,gunNo,chargeMoney)) {
					rtnJson = errorInfo(Constant.Err, "创建充电单失败");
					return rtnJson;
				}
				
				// 组织请求结构体
				ComntCfgEVCP.APP_CHARGE_WITH_PAY app_cherge = new ComntCfgEVCP.APP_CHARGE_WITH_PAY();

				byte[] pileCodeByte = pileNo.getBytes();
				for (int i = 0; i < pileNo.length(); i++) {
					app_cherge.pile_code[i] = pileCodeByte[i];
				}
				byte[] serialNumberByte = payserialNumber.getBytes();
				for (int i = 0; i < payserialNumber.length(); i++) {
					app_cherge.pay_wasteno[i] = serialNumberByte[i];
				}
				
				app_cherge.app_type = Constant.CLIENTTYPE_INTERFACE;
				app_cherge.pay_money = (int) (objToDbl(chargeMoney) * 100);
				app_cherge.gun = objToInt(gunNo) - 1; // 0：A枪 1:B枪
				app_cherge.gun_state = gunState;
				app_cherge.cmd = Constant.CHARGE_START;
				
				YysCarownerOrder order = this.yysCarownerOrderMapper.getYysCarownerOrder(payserialNumber);
				if (null == order) {
					rtnJson = errorInfo(Constant.Err, "不存在对应的充电单");
					return rtnJson;
				}
				
				app_cherge.owner_id = order.getCarownerId();
				
				// 查询充电桩终端及通道信息
				ComntParaBase.RTU_PARA rtuPara = this.queryPileRtuPara(pileNo);

				ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
				app_cherge.toDataStream(task_data_vect);
				StringBuffer opDetail = new StringBuffer();
				// 向前置发送充电请求
				if (!sendRequest((byte) ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_PAY, rtuPara, task_data_vect, opDetail)) {
					rtnJson = errorInfo(Constant.Err, "请求太频繁,请稍后重试!");
					Log4jUtil.getChargeinfo().info("web service 下发错误"+queryJsonStr);
				}else {
					rtnJson = errorInfo(Constant.OK, "");
				}
			} else {
				rtnJson = errorInfo(Constant.Err, "当前充电枪状态不允许充电");
			}
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "请求开始充电异常");
			Log4jUtil.getError().error("请求开始充电异常" + queryJsonStr, e);
			e.printStackTrace();
		}
		
		Log4jUtil.getChargeinfo().info("流水号 ：" + payserialNumber + "请求充电成功！");
		return rtnJson;
	}
	
	/**
	 * 插入接口充电记录  和充电单
	 * @param serialNumber
	 * @param pileNo
	 * @param gunNo
	 * @param chargeMoney
	 * @return
	 */
	private boolean insertChargeOrder(String payserialNumber, String pileNo,
			String gunNo, String chargeMoney) {
		boolean flag = false;
	
		// 将开始请求充电记录存入接口充电记录 充电监控
		ChargeRecord chargeRecord = new ChargeRecord();
		chargeRecord.setSerialnumber(payserialNumber);
		chargeRecord.setPileCode(pileNo);
		chargeRecord.setGunId(objToInt(gunNo));
		chargeRecord.setStartDate(new Date());
		chargeRecord.setChargeMoney((int) (objToDbl(chargeMoney) * 100));
		flag = chargeRecordMapper.insertChargeRecord(chargeRecord);
		
		Map<String, Object> pileInfo = this.yysCarownerOrderMapper.getPileInfo(pileNo);
		
		YysCarownerOrder order = new YysCarownerOrder();
		
		order.setYysId(objToInt(pileInfo.get("yysId")));
		order.setStationId(objToInt(pileInfo.get("stationId")));
		order.setPileId(objToInt(pileInfo.get("pileId")));
		order.setGunFlag(objToByte(gunNo));
		order.setChargeState(Constant.CHARGINGSTATE_V2_PAYSUCCESS);
		order.setAppFlag(Constant.CLIENTTYPE_INTERFACE);
		
		order.setPayWasteno(payserialNumber);
		order.setPayYmd(nowDateInt());
		order.setPayHms(nowTimeInt());
		int partnerId = objToInt(pileInfo.get("partnerId"));
		
		PartnerConfig partnerConfig = WebConfig.partnerUrl.get(partnerId);
		if (null == partnerConfig) {
			return false;
		}
		order.setCarownerId(partnerConfig.getCarownerId());
		
		flag &= this.yysCarownerOrderMapper.insertYysCarownerOrder(order);

		return flag;
	}
	
	
	
	/**
	 * 根据桩体号查询所属终端和通道参数
	 * */
	public ComntParaBase.RTU_PARA queryPileRtuPara(String pileCode){
		//查询对应的rtu信息 ,给rtuPara赋值
		ComntParaBase.RTU_PARA rtuPara = new ComntParaBase.RTU_PARA();
		Map<String, Object> rtuMap = this.chargeMapper.queryRtuParaByPileNo(pileCode);
		if (rtuMap == null)
			return null;
		
		rtuPara.rtu_id = objToInt(rtuMap.get("id"));
		rtuPara.prot_type = objToByte(rtuMap.get("prot_type"));
		rtuPara.rtu_model = objToShort(rtuMap.get("rtu_model"));
		rtuPara.timeout = objToShort(rtuMap.get("timeout"));
		
		return rtuPara;
	}
	
	/**
	 * 发送请求
	 * */
	public boolean sendRequest(byte requestType,ComntParaBase.RTU_PARA rtuPara,
			ComntVector.ByteVector task_data_vect, StringBuffer opDetail){
		
		if(rtuPara == null) {
			opDetail.setLength(0);
			opDetail.append("桩体号对应的终端和通道参数错误");
			System.out.println("桩体号对应的终端和通道参数错误");
			return false;
		}
		
		byte[] task_result = new byte[1];
		//立即应答结果，此处不需要取，无用,保留
		ArrayList<ComntCfgEVCP.APP_CHARGE_WITH_PAY_RESULT> ret_data_vect = new ArrayList<ComntCfgEVCP.APP_CHARGE_WITH_PAY_RESULT>();
		OpDetailInfo detail_info = new OpDetailInfo();

		boolean ret_val = ComntParaBase.get1StepTaskResult("", rtuPara.rtu_id,
				rtuPara.prot_type, rtuPara, requestType,
				(byte) ComntDef.YD_RTU_RUNSTATE_MAN, task_data_vect,
				task_result, ret_data_vect, detail_info, opDetail);

		return ret_val;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#chargeOver(java.lang.String)
	 */
	public JSONObject chargeOver(String queryJsonStr) {
		
		JSONObject rtnJson = new JSONObject();
		String pileNo = "", gunNo = "", paySerialNumber = "";
		Log4jUtil.getChargeinfo().info("接收充电结束请求：" + queryJsonStr);
		try {			
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			pileNo = json.optString("pileNo");
			gunNo = json.optString("gunNo");
			paySerialNumber = json.optString("serialNumber");
			//将流水号转成12位 
			paySerialNumber = autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH) ;
			
			if (pileNo.isEmpty() || gunNo.isEmpty() || paySerialNumber.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			//判断终端是否在线
			int rtuId = objToInt(this.pileMapper.getRtuByPile(pileNo));
			boolean rtuOnline = rtuOnlineFlag(rtuId);
			if (!rtuOnline) {
				rtnJson = errorInfo(Constant.Err, "充电桩不在线");
				return rtnJson;
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pileNo", pileNo);
			param.put("gunNo", gunNo);
			// 根据桩体号查询枪状态
			int gunState = objToInt(this.chargeMapper.getGunState(param));
			
//			if (gunState != 3) {
//				rtnJson = errorInfo(Constant.Err, "当前充电枪可以在充电");
//				return rtnJson;
//			}
			 
			YysCarownerOrder order = this.yysCarownerOrderMapper.getYysCarownerOrder(paySerialNumber);
			if (null == order) {
				rtnJson = errorInfo(Constant.Err, "不存在对应的充电单");
				return rtnJson;
			}
			
			ComntCfgEVCP.APP_CHARGE_WITH_PAY app_cherge = new ComntCfgEVCP.APP_CHARGE_WITH_PAY();

			byte[] pileCode_byte = pileNo.getBytes();
			for (int i = 0; i < pileNo.length(); i++) {
				app_cherge.pile_code[i] = pileCode_byte[i];
			}

			byte[] serialNumber_byte = paySerialNumber.getBytes();
			for (int i = 0; i < paySerialNumber.length(); i++) {
				app_cherge.pay_wasteno[i] = serialNumber_byte[i];
			}
			
			app_cherge.app_type = Constant.CLIENTTYPE_INTERFACE;
			app_cherge.owner_id = order.getCarownerId();
			app_cherge.gun = objToInt(gunNo) - 1; // 0：A枪 1:B枪
			app_cherge.gun_state = gunState;
			app_cherge.cmd = Constant.CHARGE_OVER;
			
			// 查询充电桩终端及通道信息
			ComntParaBase.RTU_PARA rtuPara = this.queryPileRtuPara(pileNo);
			ComntVector.ByteVector task_data_vect = new ComntVector.ByteVector();
			app_cherge.toDataStream(task_data_vect);
			StringBuffer opDetail = new StringBuffer();
			
			// 更新接口充电记录中请求结束充电
			ChargeRecord chargeRecord = new ChargeRecord();
			chargeRecord.setSerialnumber(paySerialNumber);
			chargeRecord.setEndDate(new Date());
			chargeRecordMapper.updateChargeRecord(chargeRecord);
			
			// 向前置发送充电请求
			if (!sendRequest((byte) ComntDef.EVCP_WEBMSGTYPE_APP_CHARGE_PAY,rtuPara, task_data_vect, opDetail)) {
				rtnJson = errorInfo(Constant.Err, "充电太频繁啦,请稍后重试!");
				Log4jUtil.getChargeinfo().info("webservice 下发失败" + queryJsonStr);
			}else{
				rtnJson = errorInfo(Constant.OK, "");
			}
		} catch (Exception e) {
			Log4jUtil.getError().error("请求充电结束异常，请求内容：" + queryJsonStr);
			rtnJson = errorInfo("-1", "请求充电结束异常");
			e.printStackTrace();
		}
		
		Log4jUtil.getChargeinfo().info("流水号：" + paySerialNumber + "请求结束充电成功");
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#getChargeData(java.lang.String)
	 */
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
		Log4jUtil.getChargeinfo().info("接收获取充电过程信息请求:" + queryJsonStr);

		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			paySerialNumber = json.optString("serialNumber");
			// 将流水号转成12位
			paySerialNumber = autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);
			
			if (paySerialNumber.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			String chargeSerialNumber = this.yysCarownerOrderMapper.getChargeSerialNumber(paySerialNumber);
			Map<String,Object> chargeData = this.chargeMapper.getChargeData(chargeSerialNumber);
			//充电状态时 获取数据；其他状态时，返回空值					
			if(chargeData != null && objToInt(chargeData.get("charge_state")) ==  Constant.GUN_STATE_CHARGING){
				
				chargTotalElectricity = objToStr(chargeData.get("charg_total_electricity"));
				chargHighElectricity = objToStr(chargeData.get("charg_high_electricity"));
				chargFlatElectricity = objToStr(chargeData.get("charg_flat_electricity"));
				chargLowElectricity	= objToStr(chargeData.get("charg_low_electricity"));
				chargTipElectricity	= objToStr(chargeData.get("charg_tip_electricity"));
				
				AVoltage = objToStr(chargeData.get("a_voltage"));
				BVoltage = objToStr(chargeData.get("b_voltage"));
				CVoltage = objToStr(chargeData.get("c_voltage"));
				
				ACurrent = objToStr(chargeData.get("a_current"));
				BCurrent = objToStr(chargeData.get("b_current"));
				CCurrent = objToStr(chargeData.get("c_current"));
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			
			rtnJson.put("chargTotalElectricity",chargTotalElectricity);
			rtnJson.put("chargHighElectricity", chargHighElectricity);
			rtnJson.put("chargFlatElectricity", chargFlatElectricity);
			rtnJson.put("chargLowElectricity",  chargLowElectricity);
			rtnJson.put("chargTipElectricity",  chargTipElectricity);
			
			rtnJson.put("AVoltage",AVoltage);
			rtnJson.put("BVoltage",BVoltage);
			rtnJson.put("CVoltage",CVoltage);
			
			rtnJson.put("ACurrent",ACurrent);
			rtnJson.put("BCurrent",BCurrent);
			rtnJson.put("CCurrent",CCurrent);
			
			Log4jUtil.getChargeinfo().info("获取充电过程请求结束 : "+ rtnJson.toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log4jUtil.getError().error("获取充电过程信息异常" + queryJsonStr);
			rtnJson = errorInfo(Constant.Err, "获取充电过程信息异常");
			e.printStackTrace();
		}
		
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#SendChargOverRequest(net.sf.json.JSONObject)
	 */
	public boolean SendChargOverRequest(JSONObject json,int retry) {
		boolean flag = false;
		
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		Integer partnerId = chargeMapper.getPartnerIdBySerialNumber(paySerialNumber);
		if (null == partnerId) {
			return false;
		}
		
		PartnerConfig partnerConfig = WebConfig.partnerUrl.get(partnerId);
		if (null == partnerConfig) {
			return false;
		}
		
		String token = partnerConfig.getToken();
		if (null == token || token.isEmpty()) {
			WebConfig.initLoginToken(partnerId);
		}
		
		param.put("token", token);
		
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", objToLong(paySerialNumber));
		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			String url = partnerConfig.getChargeOverUrl();
			if (url == null || url.isEmpty()) {
				return false;
			}
			String rtnJson = HttpUtil.doPost(url, param);
			
			JSONObject jsonItem = JSONObject.fromObject(rtnJson);
			int status = jsonItem.getInt("status");
			Log4jUtil.getChargeinfo().info("发送充电结束消息：" + json.toString() + " 接收内容： " + rtnJson);
			if(status == Constant.STATUS_OK || status == Constant.STATUS_REQUEST_ERROR){
				
				if (status == Constant.STATUS_REQUEST_ERROR) {
					Log4jUtil.getError().error("丢弃充电结束消息推送	流水号：" + json.toString());
				}
				
				ChargeRecord chargeRecord = new ChargeRecord();
				chargeRecord.setSerialnumber(paySerialNumber);
				chargeRecord.setEndPush(Constant.PUSHED);
				chargeRecord.setEndPushTime(new Date());
   	  			chargeRecordMapper.updateChargeRecord(chargeRecord);
				flag = true;
			} 
			else if(status == Constant.STATUS_CERTIFICATION_ERROR){  //身份过期
				Log4jUtil.getChargeinfo().info("身份过期 重新获取token,发送内容："+json.toString());
				
				if(retry == 0){
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				WebConfig.initLoginToken(partnerId);
				SendChargOverRequest(json,retry);
			}
		} catch (UnsupportedEncodingException e) {
			Log4jUtil.getError().error("发送充电结束请求异常,发送内容："+json.toString());
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#SendChargStartRequest(net.sf.json.JSONObject)
	 */
	public boolean SendChargStartRequest(JSONObject json, int retry) {
		boolean flag = false;

		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		Integer partnerId = chargeMapper.getPartnerIdBySerialNumber(paySerialNumber);
		if (partnerId == null) {
			return false;
		}
		
		PartnerConfig partnerConfig = WebConfig.partnerUrl.get(partnerId);
		if (null == partnerConfig) {
			return false;
		}
		String token = partnerConfig.getToken();
		if (null == token || token.isEmpty()) {
			WebConfig.initLoginToken(partnerId);
		    partnerConfig = WebConfig.partnerUrl.get(partnerId);
			if (null == partnerConfig) {
				return false;
			}
			token = partnerConfig.getToken();
		}
		
		param.put("token", token);
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", objToLong(paySerialNumber));
 		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			
			String url = partnerConfig.getChargeStartUrl();
			if (url == null || url.isEmpty()) {
				return false;
			}
			
			String rtnJson = HttpUtil.doPost(url, param);
			JSONObject jsonItem = JSONObject.fromObject(rtnJson);
			int status = jsonItem.getInt("status");
			Log4jUtil.getChargeinfo().info("发送充电开始消息："+json.toString() +" 返回结果 ： "+ rtnJson);

			if(status == Constant.STATUS_OK || status == Constant.STATUS_REQUEST_ERROR){
				flag = true;
				if (status == Constant.STATUS_REQUEST_ERROR) {
					Log4jUtil.getChargeinfo().info("丢弃充电开始消息推送	流水号：" + json.toString());
				}
				
				ChargeRecord chargeRecord = new ChargeRecord();
				chargeRecord.setSerialnumber(paySerialNumber);
				chargeRecord.setStartPush((byte)1);
	  			chargeRecord.setStartPushTime(new Date());
	  			chargeRecordMapper.updateChargeRecord(chargeRecord);
			} 
			else if(status == Constant.STATUS_CERTIFICATION_ERROR){  //身份过期
				Log4jUtil.getChargeinfo().info("身份过期 重新获取token,发送内容："+json.toString());
				
				if(retry == 0){
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				WebConfig.initLoginToken(partnerId);
				SendChargStartRequest(json,retry);
			}
		} catch (UnsupportedEncodingException e1) {
			Log4jUtil.getError().error("发送充电开始消息异常，发送内容："+json.toString());
			e1.printStackTrace();
		}
		
		return flag;
	}

	public JSONObject getPileChargeRcd(String queryJsonStr) {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		Log4jUtil.getChargeinfo().info("接送获取充电桩充电记录请求:" + queryJsonStr);

		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String paySerialNumber = json.optString("serialNumber");
			paySerialNumber = autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH);
			String pileNo = json.optString("pileNo");
			String gunNo = json.optString("gunNo");
			if (paySerialNumber.isEmpty() || pileNo.isEmpty() || gunNo.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
 			
			boolean checkWasteNo= checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				rtnJson = errorInfo(Constant.Err, "流水号非法");
				return rtnJson;
			}
			
			double totalElectricity = 0, trade_money = 0;
			String endCause = "未知";
	  		String nowTableName = "cpdata.chargercd" + nowYear() + nowMonth();
			int nowYear = nowYear();
	  		int nowYm = nowMonthInt();
			int lastYear = 0;
			String lastYm = "";
	  		if (nowYm == 1) {
	  			lastYear = nowYear - 1;
	  			lastYm = "12";
			}else {
				lastYear = nowYear;
				int lastYM = nowYm -1;
				lastYm = lastYM < 10 ? "0" + lastYM : lastYM + "";
			}
	  		
	  		String lastTableName = "cpdata.chargercd" +lastYear+lastYm;
	  		String chargeSerialNumber = yysCarownerOrderMapper.getChargeSerialNumber(paySerialNumber);

			//查询最近两个月的表
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("nowTableName", nowTableName);
			param.put("lastTableName", lastTableName);
			param.put("serialNumber", chargeSerialNumber);
			param.put("pileNo", pileNo);
			param.put("gunNo", gunNo);
			
			Map<String,Object> chargeRcd= this.chargeMapper.getPileChargeRcd(param);
 
			if (chargeRcd == null) {
				rtnJson = errorInfo(Constant.Err, "不存在【"+paySerialNumber+"】的充电记录");
				return rtnJson;
			}
			
			StringBuffer startDate = new StringBuffer(); 
	  		startDate.append(FormatToYMD(chargeRcd.get("trade_bymd"),"day")).append(" ");
	  		startDate.append(intToTime(chargeRcd.get("trade_bhms"),1));
	  		
	  		StringBuffer endDate = new StringBuffer();
	  		endDate.append(FormatToYMD(chargeRcd.get("trade_eymd"),"day")).append(" ");
	  		endDate.append(intToTime(chargeRcd.get("trade_ehms"),1));
			
	  		totalElectricity = objToDbl(chargeRcd.get("trade_dl"));
	  		trade_money = objToDbl(chargeRcd.get("trade_money"));
	  		int m_cause = objToInt(chargeRcd.get("end_cause"));
		
	  		endCause = chargeRecordMapper.getEndCause(m_cause);
			if (null == endCause || endCause.equals("")) {
				endCause = "未知";
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			
			rtnJson.put("serialNumber",paySerialNumber);
			rtnJson.put("pileNo", pileNo);
			rtnJson.put("gunNo", gunNo);
			rtnJson.put("startDate",  startDate.toString());
			rtnJson.put("endDate",  endDate.toString());
			
			rtnJson.put("totalElectricity",round(totalElectricity, 2));
			rtnJson.put("chargeMoney",round(trade_money, 2));
			rtnJson.put("endCause",endCause);
			
		} catch (Exception e) {
			Log4jUtil.getError().error("获取充电记录异常" + queryJsonStr);
			rtnJson = errorInfo(Constant.Err, "获取充电记录异常");
			e.printStackTrace();
		}
		
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#getChargeRealData(java.lang.String)
	 */
	public JSONObject getChargeRealData(String queryJsonStr) {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		String paySerialNumber = ""; // 流水号

		String pileNo = "";
		byte gunNo = 0;
		byte pileType = 0;
		double chargElectricity = 0D;
		int chargeSecond = 0;
		int chargeMoney = 0;
		int remainSecond = 0;
		int SOC = 0;

		Log4jUtil.getChargeinfo().info("接收获取充电过程实时信息请求:" + queryJsonStr);

		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			paySerialNumber = json.optString("serialNumber");
			paySerialNumber = autoGenericCode(paySerialNumber, Constant.PAY_SERIALNUMBER_LENGTH) ;

			if (paySerialNumber.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			Map<String,Object> chargeData = this.chargeMapper.getChargeRealData(paySerialNumber);
			//充电状态时 获取数据；其他状态时，返回空值					
			if(chargeData != null && objToInt(chargeData.get("charge_state")) == Constant.GUN_STATE_CHARGING){
				
				pileNo = objToStr(chargeData.get("pileNo"));
				gunNo = objToByte(chargeData.get("gunNo"));
				pileType = objToByte(chargeData.get("pileType"));
				chargElectricity = objToDbl(chargeData.get("chargElectricity"));
				chargeSecond	= objToInt(chargeData.get("chargeSecond"));
				
				chargeMoney = (int) (objToDbl(chargeData.get("chargeMoney")));
				remainSecond = objToInt(chargeData.get("remainSecond"));
				SOC = objToInt(chargeData.get("SOC"));
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			
			rtnJson.put("pileNo", pileNo);
			rtnJson.put("gunNo", gunNo);
			rtnJson.put("pileType",  pileType);
			rtnJson.put("chargElectricity",  chargElectricity);
			rtnJson.put("chargeSecond",  chargeSecond);
			rtnJson.put("chargeMoney",chargeMoney);
			rtnJson.put("remainSecond",remainSecond);
			rtnJson.put("SOC",SOC);
			
			
			Log4jUtil.getChargeinfo().info("获取充电过程返回结果 : "+ rtnJson.toString());
		} catch (Exception e) {
			// TODO: handle exception
			Log4jUtil.getError().error("获取充电过程信息异常" + queryJsonStr);
			rtnJson = errorInfo(Constant.Err, "获取充电过程信息异常");
			e.printStackTrace();
		}
		
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IChargeService#SendDCChargInfoRequest(net.sf.json.JSONObject)
	 */
	public boolean SendDCChargInfoRequest(JSONObject json,int retry) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Map<String, String> param = new HashMap<String, String>();
		String paySerialNumber = json.getString("serialNumber");
		Integer partnerId = chargeMapper.getPartnerIdBySerialNumber(paySerialNumber);
		if (null == partnerId) {
			return flag;
		}
		
		PartnerConfig partnerConfig = WebConfig.partnerUrl.get(partnerId);
		if (null == partnerConfig) {
			return flag;
		}
		
		String token = partnerConfig.getToken();
		if (null == token || token.isEmpty()) {
			WebConfig.initLoginToken(partnerId);
		}
		
		String chargeInfoUrl = partnerConfig.getDCChargeInfoUrl();
		if (null == chargeInfoUrl || chargeInfoUrl.isEmpty()) {
			return flag;
		}
		
		param.put("token", token);
		
		JSONObject sendJson = new JSONObject();
		sendJson.putAll(json);
		sendJson.put("serialNumber", objToLong(paySerialNumber));
 		try {
			param.put("queryJsonStr", java.net.URLEncoder.encode(sendJson.toString(), "UTF-8"));
			String url = partnerConfig.getDCChargeInfoUrl();
			if (url == null || url.isEmpty()) {
				return false;
			}

			String rtnJson = HttpUtil.doPost(url, param);
			
			JSONObject jsonItem = JSONObject.fromObject(rtnJson);
			int status = jsonItem.getInt("status");
			Log4jUtil.getChargeinfo().info("发送直流充电信息消息：" + json.toString() + " 接收内容： " + rtnJson);

			if(status == Constant.STATUS_OK || status == Constant.STATUS_REQUEST_ERROR){
				
				if (status == Constant.STATUS_REQUEST_ERROR) {
					Log4jUtil.getError().error("丢弃直流充电信息推送	流水号：" + json.toString());
				}
				
				ChargeRecord chargeRecord = new ChargeRecord();
				chargeRecord.setSerialnumber(paySerialNumber);
				chargeRecord.setSocPush(Constant.PUSHED);
   	  			chargeRecordMapper.updateChargeRecord(chargeRecord);
				flag = true;
			} 
			else if(status == Constant.STATUS_CERTIFICATION_ERROR){  //身份过期
				Log4jUtil.getChargeinfo().info("身份过期 重新获取token,发送内容："+json.toString());
				
				if(retry == 0){
					throw new RuntimeException("连接超时·····");
				}
				retry--;
				WebConfig.initLoginToken(partnerId);
				SendDCChargInfoRequest(json,retry);
			}
		} catch (UnsupportedEncodingException e) {
			Log4jUtil.getError().error("发送直流充电信息请求异常,发送内容："+json.toString());
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}
}
