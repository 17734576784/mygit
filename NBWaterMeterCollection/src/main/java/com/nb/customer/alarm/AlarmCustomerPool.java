/**   
* @Title: AlarmCustomerPool.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:03:52 
* @version V1.0   
*/
package com.nb.customer.alarm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/** 
* @ClassName: AlarmCustomerPool 
* @Description: 告警事项消费者线程池 
* @author dbr
* @date 2019年3月11日 下午4:03:52 
*  
*/
@Component
@DependsOn({"jedisUtils"})
public class AlarmCustomerPool {
	
	@Value("${alarm_event_poolsize}")
	private int alarmEventPoolsize;
	
	@Autowired
	private AlarmCustomerExecutor alarmCustomerExecutor;
	
	ExecutorService	alarmCustomerPool;

	@PostConstruct
	public void init() {
		alarmCustomerPool = Executors.newFixedThreadPool(alarmEventPoolsize);
		for (int i = 0; i < alarmEventPoolsize; i++) {
			alarmCustomerPool.execute(new AlarmCustomerThread(alarmCustomerExecutor));
		}
	}

}
