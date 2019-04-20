package com.nb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.ScheduleJob;

/** 
* @ClassName: ScheduleJobMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:35:22 
*  
*/
@Mapper
public interface ScheduleJobMapper {

	List<ScheduleJob> findLegalJobList();

	List<ScheduleJob> findDelJobList();

	int insertSelective(ScheduleJob record);
}