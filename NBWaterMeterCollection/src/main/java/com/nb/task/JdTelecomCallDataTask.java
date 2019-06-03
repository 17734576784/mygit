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

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.service.ITaskService;

/** 
* @ClassName: JdTelecomCallDataTask 
* @Description: 竞达电信平台补招任务
* @author dbr
* @date 2019年5月5日 下午3:06:29 
*  
*/
public class JdTelecomCallDataTask implements Job {
	
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
		try {
			taskService.callHistoryData(this.getClass().getName());
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error(this.getClass().getName() + "补招任务执行异常,{}", e.getMessage());
		}

	}
}
