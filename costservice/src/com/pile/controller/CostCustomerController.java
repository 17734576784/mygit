/**   
* @Title: TestController.java 
* @Package com.pile.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月26日 下午5:34:41 
* @version V1.0   
*/
package com.pile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pile.customer.CostCustomerThread;

/** 
* @ClassName: TestController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月26日 下午5:34:41 
*  
*/
@RestController
public class CostCustomerController {
	
	@Value("${delayCouponLimit}")
	private int port;

	@RequestMapping("/test")
	public String test(){
		String result = String.valueOf(port);
		return result;
	}
	
	@RequestMapping("/start")
	public String startCostCustomer(){
		CostCustomerThread.runFlag = true;
		return "OK";
	}
	
	@RequestMapping("/stop")
	public String stopCostCustomer(){
		CostCustomerThread.runFlag = false;
		return "OK";
	}
}
