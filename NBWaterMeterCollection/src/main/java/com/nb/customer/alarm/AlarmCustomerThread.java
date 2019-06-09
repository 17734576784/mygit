/**   
* @Title: AlarmCustomerThread.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:08:32 
* @version V1.0   
*/
package com.nb.customer.alarm;

import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;

/** 
* @ClassName: AlarmCustomerThread 
* @Description: 事项告警处理线程
* @author dbr
* @date 2019年3月11日 下午4:08:32 
*  
*/
public class AlarmCustomerThread implements Runnable {

	/** 告警事项线程起止标志 */
	public volatile static boolean alarmCustomerRunFlag = true;
	
	private AlarmCustomerExecutor alarmCustomerExecutor;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param alarmCustomerExecutor 
	*/
	public AlarmCustomerThread(AlarmCustomerExecutor alarmCustomerExecutor) {
		super();
		this.alarmCustomerExecutor = alarmCustomerExecutor;
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
			if (alarmCustomerRunFlag) {
				Object alaram = null;
				try {
					alaram = JedisUtils.brpop(Constant.ALARM_EVENT_QUEUE, Constant.REDIS_TIMEOUT);
					if (null != alaram) {
						alarmCustomerExecutor.saveAlarmEvent(alaram);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
