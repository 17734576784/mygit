/**   
* @Title: StationController.java 
* @Package com.ke.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月7日 上午9:28:11 
* @version V1.0   
*/
package com.ke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ke.service.IStaionService;

/** 
* @ClassName: StationController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月7日 上午9:28:11 
*  
*/
@RestController
public class StationController {
	@Autowired
	private IStaionService staionService;
	
	@RequestMapping("/listStationGPS.json")
	public String listStationGPS(String token) throws Exception {
		return this.staionService.listStationGPS(token).toJSONString();
	}
	
	@RequestMapping("/listChargeOrders.json")
	public String listChargeOrders(String queryJsonStr) throws Exception {
		return this.staionService.listChargeOrders(queryJsonStr).toJSONString();
	}
}
