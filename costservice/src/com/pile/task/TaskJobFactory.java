/**   
* @Title: TaskJobFactory.java 
* @Package com.pile.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月18日 上午10:30:06 
* @version V1.0   
*/
package com.pile.task;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/** 
* @ClassName: TaskJobFactory 
* @Description: 定时任务工厂类  
* @author dbr
* @date 2018年8月18日 上午10:30:06 
*  
*/
public class TaskJobFactory extends AdaptableJobFactory {
	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		//调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		capableBeanFactory.autowireBean(jobInstance);
 		return jobInstance;
	}
}
