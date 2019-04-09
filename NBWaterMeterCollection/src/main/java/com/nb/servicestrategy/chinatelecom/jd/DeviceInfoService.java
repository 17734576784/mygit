/**   
* @Title: DeviceInfoService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月9日 下午3:26:07 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.servicestrategy.IServiceStrategy;

/** 
* @ClassName: DeviceInfoService 
* @Description: 竟达设备信息 
* @author dbr
* @date 2019年4月9日 下午3:26:07 
*  
*/
@Service
public class DeviceInfoService implements IServiceStrategy {

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

	}

}
