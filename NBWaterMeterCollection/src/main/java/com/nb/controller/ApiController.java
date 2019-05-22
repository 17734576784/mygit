/**   
* @Title: APIController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月13日 下午1:56:56 
* @version V1.0   
*/
package com.nb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ErrorCodeEnum;
import com.nb.exception.ResultBean;
import com.nb.service.IChinaMobileCommandService;
import com.nb.service.IChinaMobileDeviceService;
import com.nb.service.IChinaTelecomCommandService;
import com.nb.service.IChinaTelecomDeviceService;
import com.nb.utils.Constant;

/** 
* @ClassName: APIController 
* @Description: 网站API接入控制器
* @author dbr
* @date 2019年2月13日 下午1:56:56 
*  
*/
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private IChinaMobileCommandService chinaMobileCommandService;
	@Autowired
	private IChinaMobileDeviceService chinaMobileDeviceService;
	@Autowired
	private IChinaTelecomCommandService chinaTelecomCommandService;
	@Autowired
	private IChinaTelecomDeviceService chinaTelecomDeviceService;

	/** 
	* @Title: addDevice 
	* @Description: 创建设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> addDevice(@RequestBody JSONObject deviceInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		int nbType = deviceInfo.getIntValue("nbType");

		switch (nbType) {
		case Constant.CHINA_TELECOM:
			result = this.chinaTelecomDeviceService.registerDevice(deviceInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileDeviceService.registerDevice(deviceInfo);
			break;
		default:
			result.setStatus(ErrorCodeEnum.FAILED.getStatus());
			result.setError("请求参数错误");
			break;
		}

		return result;
	}
	
	/** 
	* @Title: deleteDevice 
	* @Description: 删除设备 
	* @param @param deviceId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "deleteDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> deleteDevice(@RequestBody JSONObject deviceInfo) throws Exception {

		ResultBean<?> result = new ResultBean<>();
		int nbType = deviceInfo.getIntValue("nbType");

		switch (nbType) {
		case Constant.CHINA_TELECOM:
			result = this.chinaTelecomDeviceService.deleteDevice(deviceInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileDeviceService.deleteDevice(deviceInfo);
			break;
		default:
			result.setStatus(ErrorCodeEnum.FAILED.getStatus());
			result.setError("请求参数错误");
			break;
		}

		return result;
	}
	
	/** 
	* @Title: offlineReadDeviceResources 
	* @Description: 缓存命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/readresource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> offlineReadDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		result = chinaMobileCommandService.offlineReadDeviceResources(commandInfo);
		return result;
	}

	/** 
	* @Title: offlineWriteDeviceResources 
	* @Description: 缓存命令-写设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/writeresource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> offlineWriteDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		result = chinaMobileCommandService.offlineWriteDeviceResources(commandInfo);
		return result;
	}

	/** 
	* @Title: asynCommand 
	* @Description: 缓存命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/command", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> asynCommand(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		int nbType = commandInfo.getIntValue("nbType");

		switch (nbType) {
		case Constant.CHINA_TELECOM:
			result = this.chinaTelecomCommandService.asynCommand(commandInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileCommandService.asynCommand(commandInfo);
			break;
		default:
			break;
		}

		return result;
	}
	
	@RequestMapping(value = "/batchCommand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> batchCommand(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		int nbType = commandInfo.getIntValue("nbType");

		switch (nbType) {
		case Constant.CHINA_TELECOM:
			result = this.chinaTelecomCommandService.batchCommand(commandInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileCommandService.asynCommand(commandInfo);
			break;
		default:
			break;
		}

		return result;
	}
	
}
