/**   
* @Title: AlarmCustomerPool.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:03:52 
* @version V1.0   
*/
package com.ke.costumer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.annotation.PostConstruct;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/** 
* @ClassName: MsgPushCustomerPool 
* @Description: 消息推送消费者线程池 
* @author dbr
* @date 2019年3月11日 下午4:03:52 
*  
*/
@Component
@DependsOn({"jedisUtil"})
public class MsgPushCustomerPool {
	
	@Autowired
	private MsgPushCustomerExecutor msgPushCustomerExecutor;

	@PostConstruct
	public void init() {

		/** 创建充电开始推送线程池 */
		ScheduledExecutorService startPushService = new ScheduledThreadPoolExecutor(1,
				new BasicThreadFactory.Builder().namingPattern("chargeStart-schedule-pool-%d").daemon(true).build());
		startPushService.execute(new StartPushCustomerThread(msgPushCustomerExecutor));

		/** 创建充电结束推送线程池 */
		ScheduledExecutorService OvertPushService = new ScheduledThreadPoolExecutor(1,
				new BasicThreadFactory.Builder().namingPattern("chargeOver-schedule-pool-%d").daemon(true).build());
		OvertPushService.execute(new OverPushCustomerThread(msgPushCustomerExecutor));

		/** 创建Soc推送充线程池 */
		ScheduledExecutorService SocPushService = new ScheduledThreadPoolExecutor(1,
				new BasicThreadFactory.Builder().namingPattern("chargeSoc-schedule-pool-%d").daemon(true).build());
		SocPushService.execute(new SocPushCustomerThread(msgPushCustomerExecutor));

		/** 创建水电桩报警推送充线程池 */
		ScheduledExecutorService alaramPushService = new ScheduledThreadPoolExecutor(1,
				new BasicThreadFactory.Builder().namingPattern("pilealarm-schedule-pool-%d").daemon(true).build());
		alaramPushService.execute(new AlarmPushCustomerThread(msgPushCustomerExecutor));
		
		System.out.println("线程池启动成功");
	}

}
