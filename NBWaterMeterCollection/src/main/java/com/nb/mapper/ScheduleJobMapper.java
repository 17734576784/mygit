package com.nb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.ScheduleJob;

@Mapper
public interface ScheduleJobMapper {

	List<ScheduleJob> findLegalJobList();

	List<ScheduleJob> findDelJobList();

	int insertSelective(ScheduleJob record);
}