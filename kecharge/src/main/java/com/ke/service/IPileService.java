/**
 * 
 */
package com.ke.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author dbr
 * 
 */
public interface IPileService {
	// 获取指定充电桩状态
	JSONObject getPileState(String queryJsonStr) throws Exception;

	// 获取指定充电桩状态
	JSONObject getPileGps(String queryJsonStr) throws Exception;

	// 获知指定充电桩的费率
	JSONObject getPileRate(String queryJsonStr) throws Exception;
	
	// 获取指定充电站下的充电桩信息
	JSONObject listPileInfo(String queryJsonStr) throws Exception;

	// 获取指定充电站下的充电枪信息
	JSONObject listGunInfo(String queryJsonStr) throws Exception;

	// 获取指定充电站下的充电枪状态
	JSONObject listGunState(String queryJsonStr) throws Exception;
}