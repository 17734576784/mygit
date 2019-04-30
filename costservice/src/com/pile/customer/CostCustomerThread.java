/**   
* @Title: CostCustomer.java 
* @Package com.pile.netty.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月20日 下午3:12:28 
* @version V1.0   
*/
package com.pile.customer;

import com.pile.common.Constant;
import com.pile.utils.JedisUtils;
import com.pile.utils.Log4jUtils;

/**
 * @ClassName: CostCustomer
 * @Description: 充电单算费消费者线程
 * @author dbr
 * @date 2018年8月20日 下午3:12:28
 * 
 */
public class CostCustomerThread implements Runnable {
	
	/** 算费线程起止标志 */
	public volatile static boolean runFlag = true;
	
	private CalculateFeeExecutor calculateFeeExecutor;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public CostCustomerThread(CalculateFeeExecutor calculateFeeExecutor) {
		this.calculateFeeExecutor = calculateFeeExecutor;
	}
	
	/** (非 Javadoc) 
	* <p>Title: run</p> 
	* <p>Description: </p>  
	* @see java.lang.Runnable#run() 
	*/
	@Override
	public void run() {
		while (true) {
			if (runFlag) {
				byte[] value = null;
				try {
					value = JedisUtils.brpopLpush(Constant.COST_QUEUE, Constant.BAK_COST_QUEUE);
					if (null != value) {
						Log4jUtils.getDiscountinfo().info("从C++算费队列取出算费信息:" + value);
						int calculateResult = calculateFeeExecutor.calculateOrder(value);
						if (calculateResult == Constant.CALCULATION_SUCCESS) {
							// 处理成功，从备份列表中弹出
							JedisUtils.rpop(Constant.BAK_COST_QUEUE);
						} else if (calculateResult == Constant.CALCULATION_FAILED) {
							Log4jUtils.getDiscountinfo().info("充电单算费失败:" + value);
							// 处理失败，放入错误列队中 再处理
							JedisUtils.lpush(Constant.ERROR_COST_QUEUE, value);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log4jUtils.getDiscountinfo().info("充电单算费线程异常:" + value);
				}
			}
		}
	}
}
