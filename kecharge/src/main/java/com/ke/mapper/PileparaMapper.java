package com.ke.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ke.model.Pilepara;
import com.ke.model.PileparaKey;
@Mapper
public interface PileparaMapper {
    int deleteByPrimaryKey(PileparaKey key);

    int insert(Pilepara record);

    int insertSelective(Pilepara record);

    Pilepara selectByPrimaryKey(PileparaKey key);

    int updateByPrimaryKeySelective(Pilepara record);

    int updateByPrimaryKey(Pilepara record);
}