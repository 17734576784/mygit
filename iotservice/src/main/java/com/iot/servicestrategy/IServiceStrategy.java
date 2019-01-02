/**
 * 
 */
package com.iot.servicestrategy;

import java.util.Map;

/**   
 * @ClassName:  IServiceStrategy   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年10月22日 下午1:48:42   
 *      
 */
public interface IServiceStrategy {
	/**
	 * 	解析service数据，并处理
	 * @param 
	 * @param serviceMap
	 */
	public void parse(String deviceId, Map<String, String> serviceMap);
}
