/**
 * 
 */
package com.wo.service;

import net.sf.json.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IPileService {
	// 获取指定充电桩状态
	JSONObject getPileState(String queryJsonStr);

	// 获取指定充电桩状态
	JSONObject getPileGps(String queryJsonStr);
	
	//获知指定充电桩的费率
	JSONObject getPileRate(String queryJsonStr);
}