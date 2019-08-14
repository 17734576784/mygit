/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.service.IChargeRecordService;
import com.wo.util.CommFunc;

/**
 * @author dbr
 *
 */
@RestController
public class ChargeRecordController {

	@Autowired
	private IChargeRecordService chargeRecordService; 
	
	@RequestMapping("listChargeRecord")
	public String listChargeRecord(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = chargeRecordService.listChargeRecord(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
}
