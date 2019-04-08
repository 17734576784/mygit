package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbCommand;
import com.nb.model.NbCommandKey;

@Mapper
public interface NbCommandMapper {

	boolean deleteNbCommand(NbCommandKey key);

	boolean insertNbCommand(NbCommand record);

	NbCommand getNbCommand(NbCommandKey key);

	boolean updateNbCommand(NbCommand record);

}