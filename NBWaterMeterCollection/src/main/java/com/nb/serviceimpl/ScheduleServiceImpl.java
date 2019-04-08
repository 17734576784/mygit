/**   
* @Title: ScheduleServiceImpl.java 
* @Package com.nb.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月12日 上午10:31:17 
* @version V1.0   
*/
package com.nb.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nb.mapper.ScheduleJobMapper;
import com.nb.model.ScheduleJob;
import com.nb.service.IScheduleService;

/** 
* @ClassName: ScheduleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月12日 上午10:31:17 
*  
*/
@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	/** (非 Javadoc) 
	* <p>Title: findLegalJobList</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.nb.service.IScheduleService#findLegalJobList() 
	*/
	@Override
	public List<ScheduleJob> findLegalJobList() {
		// TODO Auto-generated method stub
		List<ScheduleJob> list = scheduleJobMapper.findLegalJobList();
		return list;
	}

	/** (非 Javadoc) 
	* <p>Title: findDelJobList</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.nb.service.IScheduleService#findDelJobList() 
	*/
	@Override
	public List<ScheduleJob> findDelJobList() {
		// TODO Auto-generated method stub
		List<ScheduleJob> list =  scheduleJobMapper.findDelJobList();
		return list;
	}

	/** (非 Javadoc) 
	* <p>Title: test</p> 
	* <p>Description: </p>  
	* @see com.nb.service.IScheduleService#test() 
	*/
	@Override
	@Transactional
	public void test() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setCronExpression("");
		scheduleJob.setJobGroup("task");
		scheduleJob.setJobName("task2");
		scheduleJob.setJobStatus((byte) 0);
		scheduleJob.setQuartzClass("");
		scheduleJobMapper.insertSelective(scheduleJob);
		scheduleJob.setJobId(1);
		scheduleJobMapper.insertSelective(scheduleJob);
	}

}
