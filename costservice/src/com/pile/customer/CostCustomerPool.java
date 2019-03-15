/**   
* @Title: CostCustomerPool.java 
* @Package com.pile.netty.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月8日 上午9:59:13 
* @version V1.0   
*/
package com.pile.customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/** 
* @ClassName: CostCustomerPool 
* @Description: 充电单算费消费者线程池
* @author dbr
* @date 2018年12月8日 上午9:59:13 
*  
*/
@Component
@DependsOn({"jedisUtils"})
public class CostCustomerPool {
	@Value("${poolsize}")
	private int poolSize;
	
	@Autowired
	private CalculateFeeExecutor calculateFeeExecutor;
	
	ExecutorService pool = null;

	@PostConstruct
	public void init() {
		pool = Executors.newFixedThreadPool(poolSize);
		for (int i = 0; i < poolSize; i++) {
			pool.execute(new CostCustomerThread(calculateFeeExecutor));
		}
		System.out.println("线程池启动成功！");
	}
	

}
