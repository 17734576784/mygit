package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbInstantaneous;
import com.nb.model.NbInstantaneousKey;

@Mapper
public interface NbInstantaneousMapper {

	boolean deleteNbInstantaneous(NbInstantaneousKey key);

	boolean insertNbInstantaneous(NbInstantaneous record);

	NbInstantaneous getNbInstantaneous(NbInstantaneousKey key);

	boolean updateNbInstantaneous(NbInstantaneous record);

}