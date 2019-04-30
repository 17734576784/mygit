/**
 * 
 */
package com.ke.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IChargeService {
	// 开始充电
	JSONObject chargeStart(String token, String queryJsonStr) throws Exception;

	// 结束充电
	JSONObject chargeOver(String queryJsonStr) throws Exception;

	// 获取充电过程中充电信息
	JSONObject getChargeData(String queryJsonStr) throws Exception;

	// 获取充电过程中充电信息(SOC)
	JSONObject getChargeRealData(String queryJsonStr) throws Exception;

	// 推送充电结束
	boolean SendChargeOverRequest(JSONObject json, int retry) throws Exception;

	// 推送充电开始
	boolean SendChargeStartRequest(JSONObject json, int retry) throws Exception;

	// 推送直流桩首次上报SOC信息
	boolean SendDCChargeInfoRequest(JSONObject json, int retry) throws Exception;

	// 获取充电桩充电记录
	JSONObject getPileChargeRcd(String queryJsonStr) throws Exception;

	// 获取登录token
	String sendLoginTokenRequest(JSONObject json) throws Exception;
}
