/**   
* @Title: HistoryDataTask.java 
* @Package com.nb.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 上午10:22:55 
* @version V1.0   
*/
package com.nb.task;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nb.customer.historydatabase.HistoryDatabaseExecutor;
import com.nb.model.NbDailyData;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.NumberUtils;

/** 
* @ClassName: HistoryDataTask 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月15日 上午10:22:55 
*  
*/
public class HistoryDataTask implements Job{

	@Autowired
	private HistoryDatabaseExecutor historyDatabaseExecutor;
	
	/** (非 Javadoc) 
	* <p>Title: execute</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
//		historyDatabaseExecutor.saveDailyData(JsonUtil.jsonObj2Sting(nbDailyData));
	}

}
