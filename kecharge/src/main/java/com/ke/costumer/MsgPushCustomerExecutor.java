/**   
* @Title: AlarmCustomerExecutor.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:06:07 
* @version V1.0   
*/
package com.ke.costumer;


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
import com.ke.model.ChargeStartMsg;
import com.ke.model.MemberOrders;
import com.ke.service.IChargeService;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.JsonUtil;

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
	* @Title: chargeStartPush 
	* @Description: 充电开始消息推送
	* @param @param obj    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void chargeStartPush(Object obj) {
		JSONObject json = new JSONObject();
		try {
			// 解析推送充电记录中的数据
			ChargeStartMsg chargeStartMsg = JsonUtil.convertJsonStringToObject(obj.toString(), ChargeStartMsg.class);
			if (chargeStartMsg == null) {
				return;
			}

			String paySerialNumber = memberOrdersMapper.getPaySerialNumber(chargeStartMsg.getChargeSerialNumber());
			if (null == paySerialNumber) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			int result = chargeStartMsg.getResut();
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
	* @Title: chargeOverPush 
	* @Description: 充电结束消息推送 
	* @param @param obj    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void chargeOverPush(Object obj) {
		JSONObject json = new JSONObject();
		try {
			
			// 解析推送充电记录中的数据
			String paySerialNumber = obj.toString();
			MemberOrders order = memberOrdersMapper.getmemberOrders(paySerialNumber);
			String pileNo = order.getPileCode();
			if (null == pileNo || pileNo.isEmpty()) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}


			Map<String, String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
			String endCause = endCauseMap.get(order.getEndCause());
			if (null == endCause || endCause.equals("")) {
				endCause = "未知";
			}

			json.put("serialNumber", paySerialNumber);
			json.put("pileNo", order.getPileCode());
			json.put("gunNo", order.getGunId());
			json.put("startDate", DateUtil.formatTimesTampDate(order.getChargebeginDate()));
			json.put("endDate", DateUtil.formatTimesTampDate(order.getChargeendDate()));

			json.put("totalElectricity", ConverterUtil.roundTosString(order.getChargeDl(), 2));
			json.put("chargeMoney", ConverterUtil.roundTosString(order.getTradeMoney() * 100D, 2));
			json.put("serviceMoney", ConverterUtil.roundTosString(order.getServiceMoney() * 100D, 2));
			json.put("endCause", endCause);

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

}
