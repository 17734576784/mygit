/**   
* @Title: CommonController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月13日 下午1:56:56 
* @version V1.0   
*/
package com.nb.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.http.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.chinamobile.ChinaMobileCommandService;
import com.nb.service.chinamobile.ChinaMobileDeviceService;
import com.nb.service.chinatelecom.ChinaTelecomCommandService;
import com.nb.service.chinatelecom.ChinaTelecomDeviceService;
import com.nb.service.chinaunicom.ChinaUnicomCommandService;
import com.nb.service.chinaunicom.ChinaUnicomDeviceService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;

/** 
* @ClassName: CommonController 
* @Description: 网站访问统一入口 
* @author dbr
* @date 2019年2月13日 下午1:56:56 
*  
*/
@RestController
@RequestMapping("/api")
public class CommonController {
	
	@Autowired
	private ChinaMobileCommandService chinaMobileCommandService;
	@Autowired
	private ChinaMobileDeviceService chinaMobileDeviceService;
	@Autowired
	private ChinaTelecomCommandService chinaTelecomCommandService;
	@Autowired
	private ChinaTelecomDeviceService chinaTelecomDeviceService;
	@Autowired
	private ChinaUnicomDeviceService chinaUnicomDeviceService;
	@Autowired
	private ChinaUnicomCommandService chinaUnicomCommandService;
	
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
		case Constant.CHINA_UNICOM:
			result = this.chinaUnicomDeviceService.registerDevice(deviceInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileDeviceService.registerDevice(deviceInfo);
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
		case Constant.CHINA_UNICOM:
			result = this.chinaUnicomDeviceService.deleteDevice(deviceInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileDeviceService.deleteDevice(deviceInfo);
			break;
		}

		return result;
	}
	
	/** 
	* @Title: instantReadDeviceResources 
	* @Description: 即时命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/readresource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantReadDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";
		ResultBean<?> result = new ResultBean<>();

		int commandType = ConverterUtils.toInt(commandInfo.get("command_type"));
		Map<String, String> commandMap = CommFunc.getCommandType(Constant.CHINA_MOBILE, commandType);
		if (null == commandMap || commandMap.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError("命令类型不存在");
			return result;
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("imei", commandInfo.getString("imei"));
		params.putAll(commandMap);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader(commandInfo));

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: instantWriteDeviceResources 
	* @Description: 即时命令-写设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/writeresource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantWriteDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		
		int nbType = commandInfo.getIntValue("nbType");
		int commandType = commandInfo.getIntValue("command_type");
		Map<String, String> commandMap = CommFunc.getCommandType(nbType, commandType);
		if (null == commandMap || commandMap.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError("命令类型不存在");
			return result;
		}
		
		commandMap.remove("res_id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imei", commandInfo.getString("imei"));
		params.put("mode", commandInfo.getString("mode"));
		params.putAll(commandMap);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(commandInfo), commandInfo.getString("data"));

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: instantSendCommand 
	* @Description: 即时命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/command", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantSendCommand(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		int nbType = commandInfo.getIntValue("nbType");

		switch (nbType) {
		case Constant.CHINA_TELECOM:
			result = this.chinaTelecomCommandService.asynCommand(commandInfo);
			break;
		case Constant.CHINA_UNICOM:
			result = this.chinaUnicomCommandService.asynCommand(commandInfo);
			break;
		case Constant.CHINA_MOBILE:
			result = this.chinaMobileCommandService.asynCommand(commandInfo);
			break;
		}

		return result;
	}

}
