/**   
* @Title: HistoryDatabaseCustomerThread.java 
* @Package com.nb.customer.historydatabase 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午9:57:54 
* @version V1.0   
*/
package com.nb.customer.historydatabase;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
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
	public volatile static boolean historyDatabaseRunFlag = false;

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
				Object hisdata = null;
				try {
					hisdata = JedisUtils.brpopLpush(Constant.HISTORY_DATABASE_QUEUE,
							Constant.HISTORY_DATABASE_ERROR_QUEUE, 5);
					if (hisdata != null) {
						historyDatabaseExecutor.saveHistoryData();
					}
					
				} catch (Exception e) {
					LoggerUtil.Logger(LogName.ERROR).error("历史库存储数据异常,异常数据{}",hisdata);
				}
			}
		}
		
	}

}
