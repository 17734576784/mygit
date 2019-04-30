/**
 * 
 */
package com.ke.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IStaionService {
	// 获取充电站GPS信息
	JSONObject listStationGPS(String token) throws Exception;

	// 获取充电站充电单
	JSONObject listChargeOrders(String queryJsonStr) throws Exception;
}
