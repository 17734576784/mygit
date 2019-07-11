/**   
* @Title: TestController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年7月11日 上午11:19:55 
* @version V1.0   
*/
package com.nb.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;

/** 
* @ClassName: TestController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年7月11日 上午11:19:55 
*  
*/
@RestController
public class TestController {
	
	@RequestMapping(value = "/api/376frame", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> test(@RequestBody JSONObject param) {
		ResultBean<?> result = new ResultBean<>();
		System.out.println("接收透传数据：" + param.toJSONString());
		return result;
	}
}
