/**   
* @Title: ICommandService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:33:44 
* @version V1.0   
*/
package com.iot.commandstrategy;

import java.util.Map;

/** 
* @ClassName: ICommandService 
* @Description: 解析命令上行数据接口
* @author dbr
* @date 2018年10月25日 下午2:33:44 
*  
*/
public interface ICommandService {
	/**
	 * 	解析命令上行数据，并处理
	 * @param 
	 * @param serviceMap
	 */
	public void parse(String deviceId, Map<String, String> serviceMap);
}
