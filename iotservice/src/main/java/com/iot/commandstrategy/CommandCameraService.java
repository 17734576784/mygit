/**   
* @Title: CameraService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:36:30 
* @version V1.0   
*/
package com.iot.commandstrategy;

import java.util.Map;

import org.springframework.stereotype.Component;

/** 
* @ClassName: CommandCameraService 
* @Description: 摄像头窗口调整上行服务 
* @author dbr
* @date 2018年10月25日 下午2:36:30 
*  
*/
@Component
public class CommandCameraService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		// TODO Auto-generated method stub

	}

}
