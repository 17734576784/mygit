package com.nb.service.chinatelecom;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nb.exception.ResultBean;
import com.nb.http.ChinaTelecomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ChinaTelecomCommandController 
* @Description: 中国电信命令管理 
* @author dbr
* @date 2019年1月9日 上午10:01:41 
*  
*/
@Service
public class ChinaTelecomCommandService {

	@Value("${commandExpireTime}")
	private int commandExpireTime;
	
	/**
	 * @param command{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","serviceId":"WaterMeter",
	 *        "method": "SET_TEMPERATURE_READ_PERIOD","param":"{\"value\":\"12\"}" }
	 * @return
	 * @throws Exception 
	 */
	public ResultBean<?> asynCommand(JSONObject command) throws Exception {
		
		LoggerUtils.Logger(LogName.INFO).info("接收下发命令请求：" + command);
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, command);

		String urlPostAsynCmd = Constant.CHINA_TELECOM_POST_ASYN_CMD;
		String appId = command.getString("appId");
		String callbackUrl = Constant.CHINA_TELECOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

		String deviceId = ConverterUtils.toStr(command.get("deviceId"));// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
		String serviceId = ConverterUtils.toStr(command.get("serviceId"));// "WaterMeter";
		String method = ConverterUtils.toStr(command.get("method"));// "SET_TEMPERATURE_READ_PERIOD";
		ObjectNode paras = JsonUtil.convertObject2ObjectNode(ConverterUtils.toStr(command.get("param")));// "{\"value\":\"12\"}"

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
