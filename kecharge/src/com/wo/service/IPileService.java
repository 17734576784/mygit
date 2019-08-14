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

	// 获取指定充电站下充电桩信息
	JSONObject listPileInfo(String queryJsonStr);

	// 获取指定充电站下充电枪信息
	JSONObject listGunInfo(String queryJsonStr);

	// 获取指定充电站下充电枪状态
	JSONObject listGunState(String queryJsonStr);

	// 获取指定充电桩状态
	JSONObject getPileGps(String queryJsonStr);

	// 获知指定充电桩的费率
	JSONObject getPileRate(String queryJsonStr);
}