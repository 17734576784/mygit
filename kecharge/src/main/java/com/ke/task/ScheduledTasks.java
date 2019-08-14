/**   
* @Title: ScheduledTasks.java 
* @Package com.ke.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 下午4:42:17 
* @version V1.0   
*/
package com.ke.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ke.service.ITaskService;

/**
 * @ClassName: ScheduledTasks
 * @Description: 定时任务类
 * @author dbr
 * @date 2018年12月21日 下午4:42:17
 * 
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	private ITaskService taskService;

	/**
	 * @Title: pushMessageTask @Description: 消息推送任务 一分钟执行一次 @param 设定文件 @return
	 *         void 返回类型 @throws
	 */
	@Scheduled(cron = "0 */1 * * * ?")
	public void pushMessageTask() {
		// taskService.pushMessageTask();

	}

	/**
	 * @Title: backupChargeMonitorTask @Description: 备份充电记录监控任务 每天一点执行 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void backupChargeMonitorTask() {
		// taskService.backupChargeMonitorTask();
	}

	/**
	 * @Title: backUpChargeOrderTask @Description: 备份充电单 每天一点执行 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void backUpChargeOrderTask() {
		// taskService.backUpChargeOrderTask();
	}

	@Scheduled(cron = "0 */5 * * * ?")
	public void pushHydropwerPileState() {
		try {
			taskService.pushHydropwerPileState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
