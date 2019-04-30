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
* @ClassName: OverPushCustomerThread 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月30日 上午9:51:19 
*  
*/
public class OverPushCustomerThread implements Runnable {

	/** 充电结束推送线程起止标志 */
	public volatile static boolean overPushCustomerRunFlag = true;

	private MsgPushCustomerExecutor msgPushCustomerExecutor;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param alarmCustomerExecutor 
	*/
	public OverPushCustomerThread(MsgPushCustomerExecutor msgPushCustomerExecutor) {
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
			if (overPushCustomerRunFlag) {
				Object chargeOver = null;
				try {
					chargeOver = JedisUtil.brpop(Constant.CHARGE_OVER_QUEUE, Constant.REDIS_TIMEOUT);
					if (null != chargeOver) {
						msgPushCustomerExecutor.chargeOverPush(chargeOver);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
