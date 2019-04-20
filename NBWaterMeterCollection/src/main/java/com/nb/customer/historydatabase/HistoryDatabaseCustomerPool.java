/**   
* @Title: HistoryDatabaseCustomerPool.java 
* @Package com.nb.customer.historydatabase 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午9:58:18 
* @version V1.0   
*/
package com.nb.customer.historydatabase;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


/** 
* @ClassName: HistoryDatabaseCustomerPool 
* @Description: 历史库消费者线程池 
* @author dbr
* @date 2019年3月11日 上午9:58:18 
*  
*/
@Component
@DependsOn({"jedisUtils"})
public class HistoryDatabaseCustomerPool {
	
	@Value("${history_database_poolsize}")
	private int historyDatabasePoolsize;
	
	@Autowired
	private HistoryDatabaseExecutor historyDatabaseExecutor;
	
	@PostConstruct
	public void init() {
		
		ScheduledExecutorService dailyDatabaseCustomerPool = new ScheduledThreadPoolExecutor(historyDatabasePoolsize,
				new BasicThreadFactory.Builder().namingPattern("daily-schedule-pool-%d").daemon(true).build());

		for (int i = 0; i < historyDatabasePoolsize; i++) {
			dailyDatabaseCustomerPool.execute(new DailyDataCustomerThread(historyDatabaseExecutor));
		}
		System.out.println("日数据历史库线程启动成功");

		ScheduledExecutorService instanceDatabaseCustomerPool = new ScheduledThreadPoolExecutor(historyDatabasePoolsize,
				new BasicThreadFactory.Builder().namingPattern("instance-schedule-pool-%d").daemon(true).build());

		for (int i = 0; i < historyDatabasePoolsize; i++) {
			instanceDatabaseCustomerPool.execute(new InstanceDataCustomerThread(historyDatabaseExecutor));
		}
		System.out.println("瞬时量数据历史库线程启动成功");

		ScheduledExecutorService batteryDatabaseCustomerPool = new ScheduledThreadPoolExecutor(historyDatabasePoolsize,
				new BasicThreadFactory.Builder().namingPattern("battery-schedule-pool-%d").daemon(true).build());
		for (int i = 0; i < historyDatabasePoolsize; i++) {
			batteryDatabaseCustomerPool.execute(new BatteryDataCustomerThread(historyDatabaseExecutor));
		}
		System.out.println("电池电压数据历史库线程启动成功");

	}
	

}
