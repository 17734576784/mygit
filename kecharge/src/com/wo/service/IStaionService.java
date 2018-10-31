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
}
