/**
 * 
 */
package com.nb.servicestrategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.StringUtil;

/**   
 * @ClassName:  ServiceStrategyContext.java   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年10月22日 下午2:05:28   
 *      
 */
@Service
public class ServiceStrategyContext {

	/** 装载策略对象集合 */
	@Autowired
	private Map<String,IServiceStrategy> serviceStrategy = new HashMap<String,IServiceStrategy>();
	
	public void parseService(String deviceId, Map<String, String> serviceMap) {
		String serviceId = "";
		if (serviceMap.containsKey("serviceId")) {
			serviceId = serviceMap.get("serviceId");
		} else if (serviceMap.containsKey("ServiceId")) {
			serviceId = serviceMap.get("ServiceId");
		}
		String serviceName = StringUtil.toLowerCaseFirstOne(serviceId) + "Service";
		IServiceStrategy service = serviceStrategy.get(serviceName);
		if (null != service) {
			service.parse(deviceId, serviceMap);
		} else {
			LoggerUtil.logger(LogName.CALLBACK).info("不存在服务：" + serviceName);
			System.out.println("不存在服务：" + serviceName);
		}
	}
}
