package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.Eve;

@Mapper
public interface EveMapper {

    boolean insertEve(Eve record);
}