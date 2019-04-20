/**   
* @Title: DeviceParaSettingService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午11:42:13 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.jd.DeviceParaSetting;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: DeviceParaSettingService 
* @Description: 竟达设备参数服务 
* @author dbr
* @date 2019年4月10日 上午11:42:13 
*  
*/
@Service
public class DeviceParaSettingService implements IServiceStrategy {

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
		String logInfo = "上报竟达设备参数 ：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());

		String eventTime = serviceMap.get("eventTime");
		DeviceParaSetting deviceParaSetting = JsonUtil.map2Bean(dataMap, DeviceParaSetting.class);
		deviceParaSetting.setEvnetTime(eventTime);

		System.out.println("上报竟达设备参数 :" + deviceParaSetting.toString());

	}

}
