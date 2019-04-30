/**   
* @Title: ChargeMonitorController.java 
* @Package com.ke.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午4:25:42 
* @version V1.0   
*/
package com.ke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ke.service.IChargeMonitorService;

/** 
* @ClassName: ChargeMonitorController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月10日 下午4:25:42 
*  
*/
@RestController
@RequestMapping("/manage")
public class ChargeMonitorController {
	@Autowired
	private IChargeMonitorService chargeMonitorService;
	
	@RequestMapping("/listChargeMonitor")
	public String listChargeMonitor(String queryJsonStr) throws Exception {
		return chargeMonitorService.listChargeMonitor(queryJsonStr).toJSONString();
	}

}
