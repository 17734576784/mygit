/**   
* @Title: UpdataService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:00 
* @version V1.0   
*/
package com.iot.commandstrategy;

import java.util.Map;

import org.springframework.stereotype.Component;

/** 
* @ClassName: CommandUpdataService 
* @Description: 远程升级上行解析服务
* @author dbr
* @date 2018年10月25日 下午2:37:00 
*  
*/
@Component
public class CommandUpdataService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub

	}

}
