/**   
* @Title: LoginController.java 
* @Package com.ke.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月4日 下午3:17:12 
* @version V1.0   
*/
package com.ke.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ke.service.IShiroService;
import com.ke.utils.JsonUtil;

/**
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月4日 下午3:17:12
 * 
 */
@RestController
public class LoginController {

	@Autowired
	private IShiroService shiroService;

	@RequestMapping(value="login.json")
	public String doLogin(String queryJsonStr) {
		JSONObject rtnJson = this.shiroService.doLogin(queryJsonStr);
		return JsonUtil.jsonObj2Sting(rtnJson);
	}
}
