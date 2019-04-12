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
 * @ClassName: InstanceDataCustomerThread
 * @Description: 瞬时量数据历史库存储线程
 * @author dbr
 * @date 2019年3月11日 上午9:57:54
 * 
 */
public class InstanceDataCustomerThread implements Runnable {

	/** 历史库线程起止标志 */
	public volatile static boolean historyDatabaseRunFlag = true;

	private HistoryDatabaseExecutor historyDatabaseExecutor;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param historyDatabaseExecutor
	 */
	public InstanceDataCustomerThread(HistoryDatabaseExecutor historyDatabaseExecutor) {
		super();
		this.historyDatabaseExecutor = historyDatabaseExecutor;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: run
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (historyDatabaseRunFlag) {
				// 瞬时数据
				Object instanceData = null;
				instanceData = JedisUtils.brpopLpush(Constant.HISTORY_INSTAN_QUEUE, Constant.HISTORY_INSTAN_ERROR_QUEUE,
						1);
				if (null != instanceData) {
					if (historyDatabaseExecutor.saveInstanceData(instanceData)) {
						JedisUtils.rpop(Constant.HISTORY_INSTAN_ERROR_QUEUE);
					} else {
						JedisUtils.brpopLpush(Constant.HISTORY_INSTAN_ERROR_QUEUE, Constant.HISTORY_INSTAN_QUEUE, 1);
					}
				}

			}
		}

	}

}
