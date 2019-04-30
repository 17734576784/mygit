package com.ke.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ke.model.OperatorConfig;

@Mapper
public interface OperatorConfigMapper {
	
	boolean deleteOperatorConfig(Integer id);

	boolean insertOperatorConfig(OperatorConfig record);

	Map<String,Object> getOperatorConfig(Integer id);

	boolean updateOperatorConfig(OperatorConfig record);

	List<OperatorConfig> listOperatorConfig(Map<String, Object> param);

	List<Map<String, Object>> listOperatorConfigArchive(Map<String, Object> param);

	List<OperatorConfig> listOperatorConfig();

	List<Map<String, Object>> listOperator();
	
	Integer checkOperatorUsername(Map<String,Object> param);
	
	boolean insertUserRole(int userId);
	
}