/**   
* @Title: OperatorConfigController.java 
* @Package com.ke.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午3:11:03 
* @version V1.0   
*/
package com.ke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ke.model.OperatorConfig;
import com.ke.service.IOperatorConfigService;


/** 
* @ClassName: OperatorConfigController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月10日 下午3:11:03 
*  
*/
@RestController
@RequestMapping("/manage")
public class OperatorConfigController {

	@Autowired
	private IOperatorConfigService operatorConfigService;
	
	@RequestMapping("/insertOperatorConfig.json")
	public String insertOperatorConfig(OperatorConfig operatorConfig) throws Exception {
		return this.operatorConfigService.insertOperatorConfig(operatorConfig).toJSONString();
	}
	
	@RequestMapping("/updateOperatorConfig.json")
	public String updateOperatorConfig(OperatorConfig operatorConfig) throws Exception {
		return this.operatorConfigService.updateOperatorConfig(operatorConfig).toJSONString();
	}
	
	@RequestMapping("/deleteOperatorConfig.json")
	public String deleteOperatorConfig(Integer id) throws Exception {
		return this.operatorConfigService.deleteOperatorConfig(id).toJSONString();
	}
	
	
	@RequestMapping("/listOperatorConfig.json")
	public String listOperatorConfig(String queryJsonStr) throws Exception {
		return this.operatorConfigService.listOperatorConfig(queryJsonStr).toJSONString();
	}
	
	@RequestMapping("/getOperatorConfig.json")
	public String getOperatorConfig(Integer id) throws Exception {
		return this.operatorConfigService.getOperatorConfig(id).toJSONString();
	}
	
	@RequestMapping("/listOperator.json")
	public String listOperator() {
		return this.operatorConfigService.listOperator().toJSONString();
	}
	
	
	@RequestMapping("/listOperatorTest.json")
	public String listOperatorTest() {
		return this.operatorConfigService.listOperatorTest().toJSONString();
	}
	
	
}
