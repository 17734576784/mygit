/**   
* @Title: ReportExceptionService.java 
* @Package com.nb.servicestrategy.chinamobile.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午3:27:01 
* @version V1.0   
*/
package com.nb.servicestrategy.chinamobile.fx;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.servicestrategy.IServiceStrategy;

/** 
* @ClassName: ReportExceptionService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年5月24日 下午3:27:01 
*  
*/
@Service
public class ReportExceptionService1 implements IServiceStrategy {

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
