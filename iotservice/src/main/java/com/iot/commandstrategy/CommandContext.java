/**   
* @Title: CommandContext.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:43 
* @version V1.0   
*/
package com.iot.commandstrategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.utils.CommFunc;
import com.iot.utils.Log4jUtils;

/** 
* @ClassName: CommandContext 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年10月25日 下午2:37:43 
*  
*/
@Service
public class CommandContext {

	/** 装载策略对象集合 */
	@Autowired
	private Map<String,ICommandService> commandStrategys = new HashMap<String,ICommandService>();
	
	public void parseCommand(String commandName, String deviceId, Map<String, String> commandMap) {
		commandName = "command" + commandName;
		ICommandService service = commandStrategys.get(commandName);
		if (null != service) {
			service.parse(deviceId, commandMap);
		} else {
			Log4jUtils.getInfo().info("不存在服务：" + commandName);
			System.out.println("不存在服务：" + commandName);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
