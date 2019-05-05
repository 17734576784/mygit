/**   
* @Title: DeviceStateService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午11:44:04 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.jd.DeviceState;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: DeviceStateService 
* @Description: 竟达水表设备状态
* @author dbr
* @date 2019年4月10日 上午11:44:04 
*  
*/
@Service
public class DeviceStateService implements IServiceStrategy {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String, java.util.Map) 
	*/
	
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub
		String logInfo = "上报竟达水表设备状态：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());

		DeviceState deviceState = JsonUtil.map2Bean(dataMap, DeviceState.class);
		deviceState.setEvnetTime(serviceMap);

//		System.out.println("上报竟达水表设备状态：" + deviceState.toString());

	}

}
