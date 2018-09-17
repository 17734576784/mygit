/**
 * 
 */
package com.iot.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.HttpsUtil;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;

/**
 * @ClassName: PostAsynCommandController
 * @Description: 创建设备命令
 * @author: dbr
 * @date: 2018年9月5日 下午3:42:39
 * 
 */
@RestController
public class PostAsynCommandController {

	/**
	 * @param command{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","serviceId":"WaterMeter",
	 *        "method": "SET_TEMPERATURE_READ_PERIOD","param":"{\"value\":\"12\"}" }
	 * @return
	 */
	@RequestMapping(value = "asynCommand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject asynCommand(String command) {
		JSONObject rtnJson = new JSONObject();
		JSONObject paramAsynCommand = new JSONObject();
		try {
			paramAsynCommand = JSONObject.parseObject(command);

			HttpsUtil httpsUtil = new HttpsUtil();
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
			rtnJson = CommFunc.result(Constant.SUCCESS, responseBody);

		} catch (Exception e) {
			Log4jUtils.getError().error("创建设备命令异常，入参：" + paramAsynCommand);
			rtnJson = CommFunc.result(Constant.ERROR, "创建设备命令异常");
			e.printStackTrace();
		}

		return rtnJson;
	}
}
