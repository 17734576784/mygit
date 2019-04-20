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
 * @ClassName: BatteryDataCustomerThread
 * @Description: 电池电压数据历史库存储线程
 * @author dbr
 * @date 2019年3月11日 上午9:57:54
 * 
 */
public class BatteryDataCustomerThread implements Runnable {

	/** 历史库线程起止标志 */
	public volatile static boolean historyDatabaseRunFlag = true;

	private HistoryDatabaseExecutor historyDatabaseExecutor;

	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param historyDatabaseExecutor 
	*/
	public BatteryDataCustomerThread(HistoryDatabaseExecutor historyDatabaseExecutor) {
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
		while (true) {
			if (historyDatabaseRunFlag) {
				// 电池电压
				Object battery = null;
//				battery = JedisUtils.brpopLpush(Constant.HISTORY_BATTERY_QUEUE, Constant.HISTORY_BATTERY_BAK_QUEUE, 1);
				battery = JedisUtils.brpop(Constant.HISTORY_BATTERY_QUEUE, Constant.REDIS_TIMEOUT);
				if (null != battery) {
					if (historyDatabaseExecutor.saveNbBattery(battery)) {
//						JedisUtils.rpop(Constant.HISTORY_BATTERY_BAK_QUEUE);
					} else {
//						JedisUtils.brpopLpush(Constant.HISTORY_BATTERY_BAK_QUEUE, Constant.HISTORY_BATTERY_ERROR_QUEUE,
//								1);
					}
				}
			}
		}

	}

}
