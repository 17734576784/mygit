/**
 * 
 */
package com.wo.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dbr
 * 
 */
public interface PileMapper {
	// 获取指定充电桩状态
	List<Map<String, Object>> getPileState(String PileNo);

	// 获取指定充电站下充电桩信息
	List<Map<String, Object>> listPileInfo(String stationNo);

	// 获取指定充电站下充电枪信息
	List<Map<String, Object>> listGunInfo(String stationNo);
	
	// 获取指定充电站下充电枪状态
	List<Map<String, Object>> listGunState(String stationNo);

	// 获取指定充电桩Gps
	Map<String, Object> getPileGps(String PileNo);

	// 获取指定充电桩的终端编号
	Integer getRtuByPile(String PileNo);

	Map<String, Object> getPileRate(String PileNo);
}
