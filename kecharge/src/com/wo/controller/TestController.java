/**
 * 
 */
package com.wo.controller;
import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.util.CommFunc;
import com.wo.util.Constant;

/**
 * @author dbr
 *
 */

/**
 * @author dbr
 *
 */
@RestController
@RequestMapping("/pile")
public class TestController {

	@RequestMapping("/login")
	public String login(){
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.OK, "");
		rtnJson.put("token","ABCDE123ASDF");
		return CommFunc.Josn2Str(rtnJson);
 	}
	
	@RequestMapping("/chargeStart")
	public String chargeStart(){
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.OK, "");
		return CommFunc.Josn2Str(rtnJson);
 	}
	
	@RequestMapping("/chargeOver")
	public String chargeOver(){
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.OK, "");
		return CommFunc.Josn2Str(rtnJson);
 	}
	
	@RequestMapping("/chargeRealData")
	public String chargeRealData(){
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.OK, "");
		return CommFunc.Josn2Str(rtnJson);
 	}
	
}
