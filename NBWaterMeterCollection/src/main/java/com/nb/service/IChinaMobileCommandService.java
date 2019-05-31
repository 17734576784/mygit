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
	* @Title: offlineReadDeviceResources 
	* @Description: 缓存命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> offlineReadDeviceResources(JSONObject commandInfo) throws Exception;

	/** 
	* @Title: offlineWriteDeviceResources
	* @Description: 缓存命令-写设备资源 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> offlineWriteDeviceResources(JSONObject commandInfo) throws Exception;

	/** 
	* @Title: asynCommand 
	* @Description: 缓存命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> asynCommand(JSONObject commandInfo) throws Exception;
	
	/** 
	* @Title: batchCommand 
	* @Description: 缓存命令-批量下发
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> batchCommand(JSONObject commandInfo) throws Exception;
	
	/** 
	* @Title: instantCommand 
	* @Description: 即时命令 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> instantCommand(JSONObject commandInfo) throws Exception;


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