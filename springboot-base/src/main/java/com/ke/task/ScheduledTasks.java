/**   
* @Title: ScheduledTasks.java 
* @Package com.ke.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 下午4:42:17 
* @version V1.0   
*/
package com.ke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ScheduledTasks
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月21日 下午4:42:17
 * 
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	@Scheduled(fixedRate = 1000 * 30)
	public void reportCurrentTime() {
//		System.out.println("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
	}

	// 每1分钟执行一次
	@Scheduled(cron = "0 */1 *  * * * ")
	public void reportCurrentByCron() {
//		System.out.println("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
	}

	private SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("HH:mm:ss");
	}
}
