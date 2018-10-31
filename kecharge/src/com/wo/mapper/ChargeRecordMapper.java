package com.wo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wo.model.ChargeRecord;

public interface ChargeRecordMapper {
	boolean deleteChargeRecord(Integer id);

	boolean insertChargeRecord(ChargeRecord record);

	ChargeRecord getChargeRecord(String serialNumber);

	boolean updateChargeRecord(ChargeRecord record);
	
	List<ChargeRecord> listChargeRecord();
	
	boolean backupChargeRecord(Map<String,Object> param);
	
	boolean deleteChargeRecords(Map<String,Object> param);
	
	List<ChargeRecord> listChargeRecordMonitor(Map<String,Object> param);
	
	
	boolean backupChargeOrder(Map<String,Object> param);
	
	boolean deleteChargeOrder(Map<String,Object> param);
	/**获取充电记录*/
	String getEndCause(@Param("endCause") int endCause);

	
}