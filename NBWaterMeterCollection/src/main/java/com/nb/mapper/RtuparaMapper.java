package com.nb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.Rtupara;
@Mapper
public interface RtuparaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rtupara record);

    int insertSelective(Rtupara record);

    Rtupara selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rtupara record);

    int updateByPrimaryKey(Rtupara record);
    
    List<Rtupara> listRtupara();
}