/**
 * 
 */
package com.iot.servicestrategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.utils.CommFunc;
import com.iot.utils.Log4jUtils;

/**   
 * @ClassName:  ServiceContext   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年10月22日 下午2:05:28   
 *      
 */
@Service
public class ServiceContext {

	/** 装载策略对象集合 */
	@Autowired
	private Map<String,IServiceStrategy> serviceStrategy = new HashMap<String,IServiceStrategy>();
	
	public void parseService(String serviceName, String deviceId, Map<String, String> serviceMap) {
		serviceName = CommFunc.toLowerCaseFirstOne(serviceName);
		IServiceStrategy service = serviceStrategy.get(serviceName);
		if (null != service) {
			service.parse(deviceId, serviceMap);
		} else {
			Log4jUtils.getInfo().info("不存在服务：" + serviceName);
			System.out.println("不存在服务：" + serviceName);
		}
	}
}
