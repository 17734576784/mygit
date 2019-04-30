/**   
* @Title: IChargeMonitorService.java 
* @Package com.ke.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午3:44:54 
* @version V1.0   
*/
package com.ke.service;

import com.alibaba.fastjson.JSONObject;

/** 
* @ClassName: IChargeMonitorService 
* @Description: 充电单监控服务 
* @author dbr
* @date 2019年1月10日 下午3:44:54 
*  
*/
public interface IChargeMonitorService {
	/** 
	* @Title: listChargeMonitor 
	* @Description: 获取充电单监控列表
	* @param @param queryJsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	JSONObject listChargeMonitor(String queryJsonStr) throws Exception;
}
