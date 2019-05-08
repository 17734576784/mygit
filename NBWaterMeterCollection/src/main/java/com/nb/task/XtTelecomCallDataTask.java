/**   
* @Title: XtTelecomCallDataTask.java 
* @Package com.nb.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月8日 上午8:58:38 
* @version V1.0   
*/
package com.nb.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nb.service.ITaskService;

/** 
* @ClassName: XtTelecomCallDataTask 
* @Description: 新天科技电信平台补招任务
* @author dbr
* @date 2019年5月8日 上午8:58:38 
*  
*/
public class XtTelecomCallDataTask implements Job{

	@Autowired
	private ITaskService taskService;
	
	/** (非 Javadoc) 
	* <p>Title: execute</p> 
	* <p>Description: </p> 
	* @param context
	* @throws JobExecutionException 
	* @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	*/
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		taskService.callHistoryData(this.getClass().getName());
	}

}
