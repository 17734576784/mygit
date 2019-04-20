/**   
* @Title: ICommandService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:33:44 
* @version V1.0   
*/
package com.nb.commandstrategy;

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
	 * 解析命令上行数据，并处理 
	* @Title: parse 
	* @Description: 解析命令上行数据，并处理 
	* @param @param deviceId 	设备Id
	* @param @param commandMap    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void parse(String deviceId, Map<String, String> commandMap);
}
