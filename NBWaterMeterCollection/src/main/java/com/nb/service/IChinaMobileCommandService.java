/**   
* @Title: IChinaMobileCommandService.java 
* @Package com.nb.service.chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月17日 下午3:49:38 
* @version V1.0   
*/
package com.nb.service;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;

/** 
* @ClassName: IChinaMobileCommandService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月17日 下午3:49:38 
*  
*/
public interface IChinaMobileCommandService {

	/** 
	* @Title: instantReadDeviceResources 
	* @Description: 即时命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> instantReadDeviceResources(JSONObject commandInfo) throws Exception;

	/** 
	* @Title: 即时命令-写设备资源 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> instantWriteDeviceResources(JSONObject commandInfo) throws Exception;

	/** 
	* @Title: asynCommand 
	* @Description: 即时命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> asynCommand(JSONObject commandInfo) throws Exception;

	/** 
	* @Title: observe 
	* @Description: 订阅 
	* @param @param observeInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> observe(JSONObject observeInfo) throws Exception;

}