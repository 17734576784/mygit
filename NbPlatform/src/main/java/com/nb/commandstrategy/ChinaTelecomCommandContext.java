/**   
* @Title: CommandContext.java 
* @Package com.nb.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:43 
* @version V1.0   
*/
package com.nb.commandstrategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.Constant;

/** 
* @ClassName: CommandContext 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年10月25日 下午2:37:43 
*  
*/
@Service
public class ChinaTelecomCommandContext {

	/** 装载策略对象集合 */
	@Autowired
	private Map<String,ICommandService> commandStrategys = new HashMap<String,ICommandService>();
	
	public void parseCommand(String commandName, String deviceId, Map<String, String> commandMap) {
		commandName = Constant.CHINA_TELECOM_COMMAND + commandName;
		ICommandService service = commandStrategys.get(commandName);
		if (null != service) {
			service.parse(deviceId, commandMap);
		} else {
			LoggerUtils.Logger(LogName.INFO).info("不存在服务：" + commandName);
			System.out.println("不存在服务：" + commandName);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
