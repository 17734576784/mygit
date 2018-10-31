/**
 * 
 */
package com.wo.service;

import net.sf.json.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IChargeService {
	// 开始充电
	JSONObject chargeStart(String queryJsonStr);

	// 结束充电
	JSONObject chargeOver(String queryJsonStr);

	// 获取充电过程中充电信息
	JSONObject getChargeData(String queryJsonStr);
	
	// 获取充电过程中充电信息(SOC)
	JSONObject getChargeRealData(String queryJsonStr);

	// 推送充电结束
	boolean SendChargOverRequest(JSONObject json, int retry);

	// 推送充电开始
	boolean SendChargStartRequest(JSONObject json, int retry);

	// 推送直流桩首次上报SOC信息
	boolean SendDCChargInfoRequest(JSONObject json, int retry);
	
	//获取充电桩充电记录
	JSONObject getPileChargeRcd(String queryJsonStr);
}
