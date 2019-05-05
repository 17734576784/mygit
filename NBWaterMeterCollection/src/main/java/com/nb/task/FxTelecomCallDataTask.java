/**   
* @Title: HistoryDataTask.java 
* @Package com.nb.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 上午10:22:55 
* @version V1.0   
*/
package com.nb.task;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nb.service.ITaskService;

/**
 * @ClassName: FxTelecomCallDataTask
 * @Description: 府星电信平台补招任务
 * @author dbr
 * @date 2019年4月15日 上午10:22:55
 * 
 */
public class FxTelecomCallDataTask implements Job {
	
	@Autowired
	private ITaskService taskService;

	/** (非 Javadoc) 
	* <p>Title: execute</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		taskService.callHistoryData(this.getClass().getName());
	}
}
