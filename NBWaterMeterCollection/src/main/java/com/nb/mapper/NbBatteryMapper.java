package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbBattery;
import com.nb.model.NbBatteryKey;

@Mapper
public interface NbBatteryMapper {

	boolean deleteNbBattery(NbBatteryKey key);

	boolean insertNbBattery(NbBattery record);

	NbBattery getNbBattery(NbBatteryKey key);

	boolean updateNbBattery(NbBattery record);

}