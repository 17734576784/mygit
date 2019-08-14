/**
 * 
 */
package com.wo.service;

import net.sf.json.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IStaionService {
	// 获取充电站GPS信息
	JSONObject listStationGPS(Integer partnerId);

	// 获取充电站充电单
	JSONObject listChargeOrders(String queryJsonStr);
}
