/**   
* @Title: HistoryDatabaseCustomerPool.java 
* @Package com.nb.customer.historydatabase 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午9:58:18 
* @version V1.0   
*/
package com.nb.customer.historydatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

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
	
	ExecutorService historyDatabaseCustomerPool;
	
	@PostConstruct
	public void init() {
//		historyDatabaseCustomerPool = Executors.newFixedThreadPool(historyDatabasePoolsize);
//		for (int i = 0; i < historyDatabasePoolsize; i++) {
//			historyDatabaseCustomerPool.execute(new HistoryDatabaseCustomerThread(historyDatabaseExecutor));
//		}
	}
	

}
