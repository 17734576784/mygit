/**
 * 
 */
package com.iot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.exception.ResultBean;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.IotHttpsUtil;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;
import com.iot.utils.StreamClosedHttpResponse;

/**
 * @ClassName: RegisterDeviceController
 * @Description:注册直连设备
 * @author: dbr
 * @date: 2018年9月5日 上午11:33:50
 * 
 */
@RestController
public class RegisterDeviceController {

	/**
	 * @param deviceInfo{"verifyCode":"99999","nodeId":"99999","timeout":0}
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "registerDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> registerDevice(String deviceInfo) throws Exception {
		Log4jUtils.getInfo().info("接收设备注册请求:" + deviceInfo);
		
		IotHttpsUtil httpsUtil = new IotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

		String appId = Constant.APPID;
		String urlReg = Constant.REGISTER_DEVICE;

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
}
