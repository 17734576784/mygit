/**   
* @Title: TestController.java 
* @Package com.ke.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月18日 下午3:46:45 
* @version V1.0   
*/
package com.ke.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月18日 下午3:46:45
 * 
 */
@RestController
public class TestController {

//	@Value("${dbr.list}")
//	private String port;
	
//	@RequestMapping("/login.json")
//	public String login() {
//		return "ok";
//	}
	
	@RequestMapping("/list.json")
	public String test2() {
//		System.out.println(port);
		return "test2";
	}

}
