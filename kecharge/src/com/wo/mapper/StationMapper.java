/**
 * 
 */
package com.wo.mapper;

import java.util.List;
import com.wo.model.Station;

/**
 * @author dbr
 * 
 */
public interface StationMapper {
	// 获取充电站GPS信息
	List<Station> listStationGPS(int partnerId);
}
