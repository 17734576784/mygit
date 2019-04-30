package com.ke.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.ke.model.ChargeMonitor;

@Mapper
public interface ChargeMonitorMapper {

	boolean deleteChargeMonitor(Integer id);

	boolean insertChargeMonitor(ChargeMonitor record);

	ChargeMonitor getChargeMonitor(String paySerialNumber);

	int updateChargeMonitor(ChargeMonitor record);

	List<ChargeMonitor> listChargeMonitor(Map<String, Object> param);

	boolean backupChargeMonitor(Map<String, Object> param);

	boolean deleteChargeMonitors(Map<String, Object> param);

	
	boolean backupChargeOrder(Map<String, Object> param);

	boolean deleteChargeOrder(Map<String, Object> param);

	/** 获取充电记录 */
	String getEndCause(@Param("endCause") int endCause);

	/** 
	* @Title: listChargeMonitor 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return com.ke.serviceimpl.List<ChargeMonitor>    返回类型 
	* @throws 
	*/
	List<ChargeMonitor> listChargeMonitorTask();

}