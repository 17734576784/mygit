/**
 * 
 */
package com.wo.mapper;

import java.util.List;
import java.util.Map;

import com.wo.model.Station;

/**
 * @author dbr
 * 
 */
public interface StationMapper {
	// 获取充电站GPS信息
	List<Station> listStationGPS(int partnerId);

	// 获取充电站充电记录
	List<Map<String, Object>> listChargeOrders(Map<String, Object> param);

}
