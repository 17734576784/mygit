/**   
* @Title: AlarmCustomerExecutor.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:06:07 
* @version V1.0   
*/
package com.ke.costumer;

import static com.ke.utils.ConverterUtil.roundBase;
import static com.ke.utils.ConverterUtil.roundTosString;
import static com.ke.utils.ConverterUtil.toDouble;
import static com.ke.utils.ConverterUtil.toInt;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.mapper.MemberOrdersMapper;
import com.ke.model.ChargeMonitor;
import com.ke.model.ChargeSocMsg;
import com.ke.model.MemberOrders;
import com.ke.model.Operator;
import com.ke.model.OperatorConfig;
import com.ke.model.TradeMsgOuterClass;
import com.ke.model.TradeMsgOuterClass.TradeMsgChargeBegin_Inf;
import com.ke.model.TradeMsgOuterClass.TradeMsgChargeEnd_Inf;
import com.ke.model.TradeMsgOuterClass.TradeMsgEvent;
import com.ke.service.IChargeService;
import com.ke.utils.JedisUtil;
import com.ke.utils.JsonUtil;
import com.ke.utils.SerializeUtil;

/**
 * @ClassName: MsgPushCustomerExecutor
 * @Description: 消息推送处理类
 * @author dbr
 * @date 2019年3月11日 下午4:06:07
 * 
 */
@Component
public class MsgPushCustomerExecutor {

	@Resource
	private ChargeMonitorMapper chargeMonitorMapper;

	@Autowired
	private IChargeService chargeService;

	@Resource
	private MemberOrdersMapper memberOrdersMapper;

	/**
	 * @Title: chargeStartPush @Description: 充电开始消息推送 @param @param obj
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void chargeStartPush(Object obj) {
		JSONObject json = new JSONObject();
		try {

			TradeMsgChargeBegin_Inf inf = TradeMsgOuterClass.TradeMsgChargeBegin_Inf
					.parseFrom(obj.toString().getBytes());
			// 解析推送充电记录中的数据
			if (inf == null) {
				return;
			}

			String paySerialNumber = inf.getPaySerial();
			if (null == paySerialNumber) {
				return;
			}

			MemberOrders order = memberOrdersMapper.getmemberOrders(paySerialNumber);
			if (null == order) {
				return;
			}

			String pileNo = order.getPileCode();
			if (null == pileNo || pileNo.isEmpty()) {
				return;
			}

			/** 验证运营商配置是否存在 */
			int operatorId = order.getOperatorId();
			String key = Constant.OPERATORCONFIG_PREFIX + operatorId;
			OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			key = Constant.OPERATOR + operatorId;
			Map<String, String> operatorMap = JedisUtil.hgetAll(key);
			if (operatorMap.isEmpty()) {
				return;
			}
			Operator operator = JsonUtil.jsonString2SimpleObj(operatorMap, Operator.class);

			short clientType = operator.getClientType();
			short infType = operator.getInfType();
			/**
			 * client_type 客户端类型 1:app 2:微信小程序 4:充电接口 按位操作 inf_type
			 * 客户端是接口方式时，接口类型 1:小蜗接口 2:陆游水电桩接口 3:CEC互联互通接口
			 */
			if (clientType == Constant.FOUR && infType == Constant.TWO) {
				json.put("readings", roundBase(toDouble(inf.getReadings()), 4));
				json.put("pileNo", inf.getPilecode());
				json.put("gunNo", inf.getGun());
			}

			int result = toInt(inf.getFlag());
			json.put("serialNumber", paySerialNumber);
			json.put("chargeFlag", (1 - result));

			LoggerUtil.logger(LogName.CHARGE).info("接收充电开始消息,接收内容：" + json.toString());

			// 更新接口充电记录中请求结束充电
			ChargeMonitor chargeMonitor = new ChargeMonitor();
			chargeMonitor.setSerialnumber(paySerialNumber);
			chargeMonitor.setStartReceiveTime(new Date());
			chargeMonitor.setStartPush((byte) 0);
			chargeMonitor.setStartFlag((byte) (1 - result));

			chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

			if (!chargeService.SendChargeStartRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("充电开始推送消息发送失败,!" + json.toString());
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("充电开始推送消息发送失败,有异常信息!" + json.toString(), e);
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @Title: chargeOverPush @Description: 充电结束消息推送 @param @param obj
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void chargeOverPush(Object obj) {
		JSONObject json = new JSONObject();
		try {

			TradeMsgChargeEnd_Inf inf = TradeMsgOuterClass.TradeMsgChargeEnd_Inf.parseFrom(obj.toString().getBytes());
			// 解析推送充电记录中的数据
			String paySerialNumber = inf.getPaySerial();
			String key = Constant.ORDER + paySerialNumber;
			Map<String, String> orderMap = JedisUtil.hgetAll(key);
			if (orderMap.isEmpty()) {
				return;
			}

			int operatorId = toInt(orderMap.get("operatorId"));
			key = Constant.OPERATOR + operatorId;
			Map<String, String> operatorMap = JedisUtil.hgetAll(key);
			if (operatorMap.isEmpty()) {
				return;
			}
			Operator operator = JsonUtil.jsonString2SimpleObj(operatorMap, Operator.class);

			/** 验证运营商配置是否存在 */
			key = Constant.OPERATORCONFIG_PREFIX + operatorId;
			OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
			if (null == operatorConfig) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			Map<String, String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
			String endCause = endCauseMap.get(orderMap.get("endCause"));
			if (null == endCause || endCause.equals("")) {
				endCause = "未知";
			}

			json.put("serialNumber", paySerialNumber);
			json.put("pileNo", inf.getPilecode());
			json.put("gunNo", inf.getGun());
			json.put("startDate", inf.getStartTime());
			json.put("endDate", inf.getEndTime());
			short clientType = operator.getClientType();
			short infType = operator.getInfType();
			/**
			 * client_type 客户端类型 1:app 2:微信小程序 4:充电接口 按位操作 inf_type
			 * 客户端是接口方式时，接口类型 1:小蜗接口 2:陆游水电桩接口 3:CEC互联互通接口
			 */
			if (clientType == Constant.FOUR && infType == Constant.TWO) {
				json.put("readings", roundBase(toDouble(inf.getReadings()), 4));
			} else if (clientType == Constant.FOUR && infType == Constant.ONE) {
				json.put("totalElectricity", roundTosString(toDouble(inf.getEnergy()), 2));
				json.put("chargeMoney", roundTosString(toDouble(inf.getEnergyMoney()) * 100D, 2));
				json.put("serviceMoney", roundTosString(toDouble(inf.getServiceMoney()) * 100D, 2));
				json.put("endCause", endCause);
			} else {
				return;
			}
			LoggerUtil.logger(LogName.CHARGE).info("接收充电结束消息,接收内容:{}", json.toString());

			// 更新接口充电记录中请求结束充电
			ChargeMonitor chargeMonitor = new ChargeMonitor();
			chargeMonitor.setSerialnumber(paySerialNumber);
			chargeMonitor.setEndReceiveTime(new Date());
			chargeMonitor.setEndPush((byte) 0);
			chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

			if (!chargeService.SendChargeOverRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("充电结束推送消息发送失败," + json);
			}

		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("充电结束推送消息发送失败,有异常信息!" + obj, e);
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 推送直流桩首次上报SOC @Title: chargeSocPush @param @param obj 设定文件 @return void
	 * 返回类型 @throws
	 */
	public void chargeSocPush(Object obj) {
		JSONObject json = new JSONObject();
		try {
			ChargeSocMsg chargeSocMsg = JsonUtil.convertJsonStringToObject(obj.toString(), ChargeSocMsg.class);
			if (chargeSocMsg == null) {
				return;
			}

			// 解析推送直流首次充电信息数据
			String paySerialNumber = memberOrdersMapper.getPaySerialNumber(chargeSocMsg.getChargeSerialNumber());
			if (null == paySerialNumber) {
				return;
			}
			ChargeMonitor chargeMonitor = chargeMonitorMapper.getChargeMonitor(paySerialNumber);
			if (chargeMonitor.getSocPush() != null && chargeMonitor.getSocPush() == Constant.PUSHED) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			json.put("serialNumber", paySerialNumber);
			json.put("remainSecond", chargeSocMsg.getRemainSecond());
			json.put("SOC", chargeSocMsg.getSOC());

			LoggerUtil.logger(LogName.CHARGE).info("接收直流首次充电信息(SOC),接收内容：" + json.toString());

			if (!chargeService.SendDCChargeInfoRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("发送直流首次充电信息(SOC)失败," + json.toString());

			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送直流首次充电信息(SOC)失败,有异常信息!" + json.toString(), e);
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 水电桩告警推送 @Title: hydropwerAlarmPush @param @param obj 设定文件 @return void
	 * 返回类型 @throws
	 */
	public void hydropwerAlarmPush(Object obj) {
		JSONObject json = new JSONObject();
		try {
			TradeMsgEvent inf = TradeMsgOuterClass.TradeMsgEvent.parseFrom(obj.toString().getBytes());
			if (null == inf) {
				return;
			}

			json.put("pileNo", inf.getPilecode());
			json.put("alarm", inf.getTypeno());

			LoggerUtil.logger(LogName.CHARGE).info("接收水电桩告警推送消息,接收内容:{}", json.toString());
			if (!chargeService.SendHydroPowerAlarm(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("水电桩告警推送消息发送失败," + json);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("水电桩告警推送消息发送失败,有异常信息!" + obj, e);
			e.printStackTrace();
			return;
		}
	}
}