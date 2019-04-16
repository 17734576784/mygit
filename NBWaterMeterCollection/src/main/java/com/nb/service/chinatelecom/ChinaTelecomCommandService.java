package com.nb.service.chinatelecom;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nb.exception.ResultBean;
import com.nb.httputil.ChinaTelecomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
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
	
	@Resource
	private CommonMapper commonMapper;
	
	/**
	 * @param command{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","serviceId":"WaterMeter",
	 *        "method": "SET_TEMPERATURE_READ_PERIOD","param":"{\"value\":\"12\"}" }
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ResultBean<?> asynCommand(JSONObject command) throws Exception {
		
		LoggerUtil.Logger(LogName.INFO).info("接收下发命令请求：" + command);
		
		Map<String, String> commandInfo = new HashMap<>();
		commandInfo = JsonUtil.jsonString2SimpleObj(command, commandInfo.getClass());
		if (commandInfo == null || commandInfo.isEmpty()) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
			return result;
		}
		
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, commandInfo);

		String urlPostAsynCmd = Constant.CHINA_TELECOM_POST_ASYN_CMD;
		String appId = commandInfo.get("appId");
		String callbackUrl = Constant.CHINA_TELECOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

		String deviceId = commandInfo.get("deviceId");// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
		ResultBean<String> result = new ResultBean<String>();

		ObjectNode paras = JsonUtil.convertObject2ObjectNode(command.get("param"));// "{\"value\":\"12\"}"
		
		Map<String, String> commandMap = commonMapper.getCommand(commandInfo);
		if (null == commandMap || commandMap.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError("命令类型不存在");
			return result;
		}
		
		Map<String, Object> paramCommand = new HashMap<>();
		paramCommand.putAll(commandMap);
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
		if (commandId.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError(responseBody);

		}else {
			JedisUtils.set(Constant.COMMAND + commandId, commandMap.get("serviceId"), commandExpireTime);
		}

		result.setData(responseBody);
		
		return result;
	}
}
