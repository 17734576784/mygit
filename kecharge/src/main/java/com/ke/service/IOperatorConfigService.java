/**   
* @Title: IOperatorConfig.java 
* @Package com.ke.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午2:33:31 
* @version V1.0   
*/
package com.ke.service;

import com.alibaba.fastjson.JSONObject;
import com.ke.model.OperatorConfig;

/** 
* @ClassName: IOperatorConfig 
* @Description: 运营商配置服务 
* @author dbr
* @date 2019年1月10日 下午2:33:31 
*  
*/
public interface IOperatorConfigService {
	
	
	/** 
	* @Title: insertOperatorConfig 
	* @Description: 添加运营商配置 
	* @param @param operatorConfig
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject insertOperatorConfig(OperatorConfig operatorConfig) throws Exception;

	/** 
	* @Title: updateOperatorConfig 
	* @Description: 修改运营商配置
	* @param @param operatorConfig
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject updateOperatorConfig(OperatorConfig operatorConfig) throws Exception;

	/** 
	* @Title: deleteOperatorConfig 
	* @Description: 删除运营商配置 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject deleteOperatorConfig(Integer id) throws Exception;

	/** 
	* @Title: listOperatorConfig 
	* @Description: 获取运营商配置列表
	* @param @param queryJsonStr
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject listOperatorConfig(String queryJsonStr) throws Exception;

	/** 
	* @Title: getOperatorConfig 
	* @Description: 获取指定运营商配置 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject getOperatorConfig(Integer id) throws Exception;

	/** 
	* @Title: listOperator 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	JSONObject listOperator();
	
	JSONObject listOperatorTest();
	
}
