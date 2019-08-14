/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.service.IChargeService;
import com.wo.util.CommFunc;

/**
 * @author dbr
 *
 */
@RestController
public class ChargeController {
	
	@Autowired
	private IChargeService chargeService;

	@RequestMapping("/chargeStart")
	public String chargeStart(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.chargeService.chargeStart(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}

	@RequestMapping("/chargeOver")
	public String chargeOver(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.chargeService.chargeOver(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}

	@RequestMapping("/chargeData")
	public String chargeData(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.chargeService.getChargeData(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/getPileChargeRcd")
	public String getPileChargeRcd(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.chargeService.getPileChargeRcd(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/chargeRealData")
	public String getChargeRealData(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.chargeService.getChargeRealData(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
}
