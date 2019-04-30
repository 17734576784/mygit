/**   
* @Title: StartComnt.java 
* @Package com.ke.comnt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月9日 下午4:25:28 
* @version V1.0   
*/
package com.ke.configuration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ke.common.CommFunc;
import com.ke.mapper.OperatorConfigMapper;
import com.ke.model.OperatorConfig;

/** 
* @ClassName: StartComnt 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月9日 下午4:25:28 
*  
*/
@Component
@DependsOn("jedisUtil")
public class SystemConfigure {

	@Resource
	private OperatorConfigMapper operatorConfigMapper;

	@PostConstruct
	public void init() {
		/** 初始化运营商配置信息 */
		initOperatorConfig();
	}
	/** 
	* @Title: InitOperatorConfig 
	* @Description: 初始化运营商配置信息，存入reids 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void initOperatorConfig() {
		List<OperatorConfig> operatorConfigList = operatorConfigMapper.listOperatorConfig();
		for (OperatorConfig operatorConfig : operatorConfigList) {
			CommFunc.initOperatorConfig(operatorConfig);
		}
	}

}
