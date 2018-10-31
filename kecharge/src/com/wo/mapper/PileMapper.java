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

	// 获取指定充电桩Gps
	Map<String, Object> getPileGps(String PileNo);
	
	// 获取指定充电桩的终端编号
	Integer getRtuByPile(String PileNo);
	
	Map<String,Object> getPileRate(String PileNo);
}
