/**
 * 
 */
package com.ke.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ke.service.IChargeService;

/**
 * @author dbr
 *
 */
@RestController
public class ChargeController {
	
	@Autowired
	private IChargeService chargeService;

	@RequestMapping("/chargeStart.json")
	public String chargeStart(String token,String queryJsonStr) throws Exception {
		return this.chargeService.chargeStart(token, queryJsonStr).toJSONString();
	}

	@RequestMapping("/chargeOver.json")
	public String chargeOver(String queryJsonStr) throws Exception {
		return this.chargeService.chargeOver(queryJsonStr).toJSONString();
	}

	@RequestMapping("/chargeData.json")
	public String chargeData(String queryJsonStr) throws Exception {
		return this.chargeService.getChargeData(queryJsonStr).toJSONString();
	}
	
	@RequestMapping("/getPileChargeRcd.json")
	public String getPileChargeRcd(String queryJsonStr) throws Exception {
		return this.chargeService.getPileChargeRcd(queryJsonStr).toJSONString();
	}
	
	@RequestMapping("/chargeRealData.json")
	public String getChargeRealData(String queryJsonStr) throws Exception {
		return this.chargeService.getChargeRealData(queryJsonStr).toJSONString();
	}
}
