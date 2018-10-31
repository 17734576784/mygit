package com.wo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wo.model.YysCarownerOrder;

public interface YysCarownerOrderMapper {
	boolean deleteYysCarownerOrder(Integer id);

	boolean insertYysCarownerOrder(YysCarownerOrder record);

	YysCarownerOrder getYysCarownerOrder(@Param("serialNumber") String serialNumber);

	boolean updateYysCarownerOrder(YysCarownerOrder record);
	
	Map<String, Object> getPileInfo(@Param("pileNo") String pileNo);
	
	String getPaySerialNumber(@Param("serialNumber") String serialNumber);
	
	String getChargeSerialNumber(@Param("serialNumber") String serialNumber);


}