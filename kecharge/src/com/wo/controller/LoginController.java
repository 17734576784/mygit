/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.service.ILoginService;
import com.wo.util.CommFunc;

/**
 * @author dbr
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	private ILoginService loginService;

	@RequestMapping("/login")
	public String checkLogin(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.loginService.checkUserLogin(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
}
 