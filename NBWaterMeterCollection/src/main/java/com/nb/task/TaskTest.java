/**   
* @Title: TaskTest.java 
* @Package com.nb.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月29日 下午2:50:09 
* @version V1.0   
*/
package com.nb.task;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import com.nb.mapper.ScheduleJobMapper;
import com.nb.service.IScheduleService;

/** 
* @ClassName: TaskTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月29日 下午2:50:09 
*  
*/
public class TaskTest implements Job {

	@Autowired
	private IScheduleService scheduleService;
	
	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	/** (非 Javadoc) 
	* <p>Title: execute</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("执行任务 ： " + LocalDateTime.now() + "  " + scheduleService.findLegalJobList().get(0).getCronExpression());

		scheduleService.test();

	}
	
 

}
