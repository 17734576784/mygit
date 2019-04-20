/**   
* @Title: IChinaMobileDeviceService.java 
* @Package com.nb.service.chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月17日 下午3:48:43 
* @version V1.0   
*/
package com.nb.service;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;

/** 
* @ClassName: IChinaMobileDeviceService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月17日 下午3:48:43 
*  
*/
public interface IChinaMobileDeviceService {

	/** 
	* @Title: addDevice 
	* @Description: 创建设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> registerDevice(JSONObject deviceInfo) throws Exception;

	/** 
	* @Title: deleteDevice 
	* @Description: 删除设备 
	* @param @param deviceId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> deleteDevice(JSONObject deviceInfo) throws Exception;

}