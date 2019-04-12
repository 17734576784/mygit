/**   
* @Title: HistoryDatabaseCustomerThread.java 
* @Package com.nb.customer.historydatabase 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午9:57:54 
* @version V1.0   
*/
package com.nb.customer.historydatabase;

import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;

/** 
* @ClassName: HistoryDatabaseCustomerThread 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 上午9:57:54 
*  
*/
public class HistoryDatabaseCustomerThread implements Runnable{

	/** 历史库线程起止标志 */
	public volatile static boolean historyDatabaseRunFlag = true;

	private HistoryDatabaseExecutor historyDatabaseExecutor;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param historyDatabaseExecutor 
	*/
	public HistoryDatabaseCustomerThread(HistoryDatabaseExecutor historyDatabaseExecutor) {
		super();
		this.historyDatabaseExecutor = historyDatabaseExecutor;
	}

	/** (非 Javadoc) 
	* <p>Title: run</p> 
	* <p>Description: </p>  
	* @see java.lang.Runnable#run() 
	*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if (historyDatabaseRunFlag) {
				
				// 电池电压
				Object battery = JedisUtils.brpopLpush(Constant.HISTORY_BATTERY_QUEUE,
						Constant.HISTORY_BATTERY_ERROR_QUEUE, 5);
				if (battery != null) {
					if (historyDatabaseExecutor.saveNbBattery(battery)) {
						JedisUtils.rpop(Constant.HISTORY_BATTERY_ERROR_QUEUE);
					}
				}

				// 日数据
				Object dailyData = JedisUtils.brpopLpush(Constant.HISTORY_DAILY_QUEUE,
						Constant.HISTORY_DAILY_ERROR_QUEUE, 5);
				if (dailyData != null) {
					if (historyDatabaseExecutor.saveDailyData(dailyData)) {
						JedisUtils.rpop(Constant.HISTORY_DAILY_ERROR_QUEUE);
					}
				}
				
				// 瞬时数据
				Object instanceData = JedisUtils.brpopLpush(Constant.HISTORY_INSTAN_QUEUE,
						Constant.HISTORY_INSTAN_ERROR_QUEUE, 5);
				if (instanceData != null) {
					if (historyDatabaseExecutor.saveInstanceData(instanceData)) {
						JedisUtils.rpop(Constant.HISTORY_INSTAN_ERROR_QUEUE);
					}
				}
			}
		}
		
	}

}
