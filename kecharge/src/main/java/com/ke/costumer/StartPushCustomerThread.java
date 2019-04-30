/**   
* @Title: StartPushCustomerThread.java 
* @Package com.ke.costumer 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月30日 上午9:51:19 
* @version V1.0   
*/
package com.ke.costumer;

import com.ke.common.Constant;
import com.ke.utils.JedisUtil;

/** 
* @ClassName: StartPushCustomerThread 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月30日 上午9:51:19 
*  
*/
public class StartPushCustomerThread implements Runnable {

	/** 充电开始推送线程起止标志 */
	public volatile static boolean startPushCustomerRunFlag = true;
	private MsgPushCustomerExecutor msgPushCustomerExecutor;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param alarmCustomerExecutor 
	*/
	public StartPushCustomerThread(MsgPushCustomerExecutor msgPushCustomerExecutor) {
		super();
		this.msgPushCustomerExecutor = msgPushCustomerExecutor;
	}

	/** (非 Javadoc) 
	* <p>Title: run</p> 
	* <p>Description: </p>  
	* @see java.lang.Runnable#run() 
	*/
	@Override
	public void run() {
		while (true) {
			if (startPushCustomerRunFlag) {
				Object chargeStart = null;
				try {
					chargeStart = JedisUtil.brpop(Constant.CHARGE_START_QUEUE, Constant.REDIS_TIMEOUT);
					if (null != chargeStart) {
						msgPushCustomerExecutor.chargeStartPush(chargeStart);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
