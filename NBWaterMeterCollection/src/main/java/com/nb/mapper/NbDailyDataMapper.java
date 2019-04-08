package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbDailyData;
import com.nb.model.NbDailyDataKey;

@Mapper
public interface NbDailyDataMapper {
	
    boolean deleteNbDailyData(NbDailyDataKey key);

    boolean insertNbDailyData(NbDailyData record);

    NbDailyData getNbDailyData(NbDailyDataKey key);

    boolean updateNbDailyData(NbDailyData record);

}