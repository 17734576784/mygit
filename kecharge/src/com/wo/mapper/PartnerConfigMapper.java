package com.wo.mapper;

import java.util.List;
import java.util.Map;

import com.wo.model.PartnerConfig;

public interface PartnerConfigMapper {
    
	boolean deletePartnerConfig(Integer id);

    boolean insertPartnerConfig(PartnerConfig record);

    PartnerConfig getPartnerConfig(Integer id);

    boolean updatePartnerConfig(PartnerConfig record);
    
    List<PartnerConfig> listPartnerConfig(Map<String,Object> param);
    
    List<Map<String,Object>> listPartnerConfigArchive(Map<String,Object> param);
    
    List<Map<String,Object>> listPartner();
    
    boolean isExistCarowner(int carownerId);

}