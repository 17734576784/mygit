package com.ke.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ke.model.Substpara;

@Mapper
public interface SubstparaMapper {
	// 获取充电站GPS信息
	List<Substpara> listStationGPS(int operatorId);

	// 判断充电桩是否属于运营商
	boolean isStationOfOperator(Map<String, Object> param);

	// 获取充电站充电记录
	List<Map<String, Object>> listChargeOrders(Map<String, Object> param);
}