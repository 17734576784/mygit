/**
 * 
 */
package com.ke.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ke.model.Pilepara;

/**
 * @author dbr
 * 
 */
@Mapper
public interface PileMapper {
	// 获取指定充电桩状态
	List<Map<String, Object>> getPileState(String PileNo);

	// 获取指定充电桩Gps
	Map<String, Object> getPileGps(String PileNo);

	// 获取指定充电桩的终端编号
	Integer getRtuByPile(String PileNo);

	Map<String, Object> getPileRate(String PileNo);

	// 判断充电桩是否属于该运营商
	boolean isPileOfOperator(Map<String, Object> param);

	// 获取指定充电站下充电桩信息
	List<Map<String, Object>> listPileInfo(String stationNo);
	
	// 获取指定运营商下充电桩信息
	List<Pilepara> listPileByOperatorId(Integer operatorId);
	
	// 获取指定充电桩的运营商
	int getOperatorIdByPileNo(String pileNo);

	// 获取指定充电站下充电枪信息
	List<Map<String, Object>> listGunInfo(String stationNo);

	// 获取指定充电站下充电枪状态信息
	List<Map<String, Object>> listGunState(String stationNo);
}
