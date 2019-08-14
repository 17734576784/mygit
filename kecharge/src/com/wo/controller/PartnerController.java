/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.service.IPartnerService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;

/**
 * @author dbr
 *
 */
@RestController
@RequestMapping("/partner")
public class PartnerController {
	
	@Autowired
	private IPartnerService partnerService;

	@RequestMapping("/listParner")
	public String listParner(){
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerService.listChargeRecord();
		return CommFunc.Josn2Str(rtnJson);
 	}
}
