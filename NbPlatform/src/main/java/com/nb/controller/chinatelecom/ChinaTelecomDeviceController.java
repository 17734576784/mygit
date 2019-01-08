/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.controller.chinatelecom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JsonUtil;
import com.nb.exception.ResultBean;
import com.nb.http.ChinaTelecomIotHttpsUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.Constant;

import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @ClassName: ChinaMobileDeviceController 
* @Description: 中国电信设备管理 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@RestController
@RequestMapping("/chinatelecom")
public class ChinaTelecomDeviceController {
	
	/** 
	* @Title: registerDevice 
	* @Description: 注册设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "registerDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> registerDevice(@RequestBody String deviceInfo) throws Exception {
		LoggerUtils.Logger(LogName.INFO).info("接收设备注册请求:" + deviceInfo);
		
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil);

		String appId = Constant.CHINA_TELECOM_APPID;
		String urlReg = Constant.CHINA_TELECOM_REGISTER_DEVICE;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		
		String jsonRequest = deviceInfo;
		StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap = JsonUtil.jsonString2SimpleObj(responseReg.getContent(), responseMap.getClass());
		String deviceId = ConverterUtils.toStr(responseMap.get("deviceId"));
		
		ResultBean<String> result = new ResultBean<String>();
		result.setData(deviceId);

		return result;
	}
	
	/** 
	* @Title: modifyDeviceInfo 
	* @Description: 修改设备信息 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "modifyDeviceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> modifyDeviceInfo(String deviceInfo) throws Exception {
		
		LoggerUtils.Logger(LogName.INFO).info("接收修改设备信息请求：" + deviceInfo);
		JSONObject paramModifyDevice = new JSONObject();
		paramModifyDevice = JSONObject.parseObject(deviceInfo);
	
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil);
	
		String appId = Constant.CHINA_TELECOM_APPID;
		String deviceId = ConverterUtils.toStr(paramModifyDevice.get("deviceId"));
		String urlModifyDeviceInfo = Constant.CHINA_TELECOM_MODIFY_DEVICE_INFO + "/" + deviceId;
	
		String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDevice);
	
		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
	
		StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
				header, jsonRequest);
 
		ResultBean<String> result = new ResultBean<String>();
		result.setData(responseModifyDeviceInfo.getContent());

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
	public ResultBean<?> deleteDevice(String deviceId) throws Exception {
		LoggerUtils.Logger(LogName.INFO).info("接收设备删除请求:" + deviceId);
		
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil);

		String appId = Constant.CHINA_TELECOM_APPID;
		String deleteUrl = Constant.CHINA_TELECOM_DELETE_DEVICE + "/" + deviceId;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		
		StreamClosedHttpResponse response = httpsUtil.doDeleteGetStatusLine(deleteUrl, header);
		ResultBean<String> result = new ResultBean<>();

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == Constant.STATUS_404) {
			result = new ResultBean<>(Constant.ERROR, "设备不存");
		}

		return result;
	}

	
}
