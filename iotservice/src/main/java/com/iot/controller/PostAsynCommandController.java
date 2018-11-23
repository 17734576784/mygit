/**
 * 
 */
package com.iot.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iot.exception.ResultBean;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.IotHttpsUtil;
import com.iot.utils.JedisUtils;
import com.iot.utils.JsonUtil;

/**
 * @ClassName: PostAsynCommandController
 * @Description: 创建设备命令
 * @author: dbr
 * @date: 2018年9月5日 下午3:42:39
 * 
 */
@RestController
public class PostAsynCommandController {

	@Value("${commandExpireTime}")
	private int commandExpireTime;
	
	/**
	 * @param command{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","serviceId":"WaterMeter",
	 *        "method": "SET_TEMPERATURE_READ_PERIOD","param":"{\"value\":\"12\"}" }
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "asynCommand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> asynCommand(String command) throws Exception {
		
		LoggerUtils.Logger(LogName.INFO).info("接收下发命令请求：" + command);
		JSONObject paramAsynCommand = new JSONObject();
		paramAsynCommand = JSONObject.parseObject(command);

		IotHttpsUtil httpsUtil = new IotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

		String urlPostAsynCmd = Constant.POST_ASYN_CMD;
		String appId = Constant.APPID;
		String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

		String deviceId = ConverterUtils.toStr(paramAsynCommand.get("deviceId"));// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
		String serviceId = ConverterUtils.toStr(paramAsynCommand.get("serviceId"));// "WaterMeter";
		String method = ConverterUtils.toStr(paramAsynCommand.get("method"));// "SET_TEMPERATURE_READ_PERIOD";
		ObjectNode paras = JsonUtil.convertObject2ObjectNode(ConverterUtils.toStr(paramAsynCommand.get("param")));// "{\"value\":\"12\"}"

		Map<String, Object> paramCommand = new HashMap<>();
		paramCommand.put("serviceId", serviceId);
		paramCommand.put("method", method);
		paramCommand.put("paras", paras);

		Map<String, Object> paramPostAsynCmd = new HashMap<>();
		paramPostAsynCmd.put("deviceId", deviceId);
		paramPostAsynCmd.put("command", paramCommand);
		paramPostAsynCmd.put("callbackUrl", callbackUrl);

		String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

		HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);
		String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
		
		JSONObject responseJson = JSON.parseObject(responseBody);
		String commandId = ConverterUtils.toStr(responseJson.getString("commandId"));
		ResultBean<String> result = new ResultBean<String>();
		
		if (commandId.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError(responseBody);

		}else {
			JedisUtils.set(Constant.COMMAND + commandId, serviceId, commandExpireTime);
		}

		
		result.setData(responseBody);
		
		return result;
	}
}
