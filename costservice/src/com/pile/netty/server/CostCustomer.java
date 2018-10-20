/**   
* @Title: CostCustomer.java 
* @Package com.pile.netty.server 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月20日 下午3:12:28 
* @version V1.0   
*/
package com.pile.netty.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pile.common.Constant;
import com.pile.utils.JedisUtils;
import com.pile.serviceimpl.CalculateFeeExecutor;

/**
 * @ClassName: CostCustomer
 * @Description: 充电单算费消费者
 * @author dbr
 * @date 2018年8月20日 下午3:12:28
 * 
 */
@Component
public class CostCustomer implements InitializingBean {
	
	@SuppressWarnings("unused")
	@Autowired
	private JedisUtils jedisUtils;

	@Autowired
	private CalculateFeeExecutor calculateFeeExecutor;

	public void afterPropertiesSet() throws Exception {
		new Thread(() -> {
			while (true) {
				Object value = JedisUtils.brpopLpush(Constant.COSTQUEUE, Constant.BAKCOSTQUEUE,20);
				if (null != value) {
					try {
						boolean flag = calculateFeeExecutor.calculateOrder(value);
						if (flag) {
							// 处理成功，从备份列表中弹出
							JedisUtils.rpop(Constant.BAKCOSTQUEUE);
						} else {
							// 处理失败，从备份列表中弹出，放入错误列队中 再处理
							JedisUtils.rpopLpush(Constant.BAKCOSTQUEUE, Constant.ERRORCOSTQUEUE);
						}
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
