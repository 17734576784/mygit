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
import com.iot.utils.IotHttpsUtil;
import com.iot.utils.Log4jUtils;
import com.iot.utils.StreamClosedHttpResponse;

@RestController
public class DeleteDeviceController {
	
	@RequestMapping(value = "deleteDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> deleteDevice(String deviceId) throws Exception {
		Log4jUtils.getInfo().info("接收设备删除请求:" + deviceId);
		
		IotHttpsUtil httpsUtil = new IotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

		String appId = Constant.APPID;
		String deleteUrl = Constant.DELETE_DEVICE + "/" + deviceId;

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
