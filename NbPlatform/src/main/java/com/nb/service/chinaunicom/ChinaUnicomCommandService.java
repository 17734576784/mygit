package com.nb.service.chinaunicom;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nb.exception.ResultBean;
import com.nb.http.ChinaUnicomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ChinaUnicomCommandController 
* @Description: 中国联通命令管理 
* @author dbr
* @date 2019年1月9日 上午10:03:16 
*  
*/
@Service
public class ChinaUnicomCommandService {

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
		ChinaUnicomIotHttpsUtil httpsUtil = new ChinaUnicomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaUnicomAccessToken(httpsUtil, command);

		String urlPostAsynCmd = Constant.CHINA_UNICOM_POST_ASYN_CMD;
		String appId = command.getString("appId");
		String callbackUrl = Constant.CHINA_UNICOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

		String deviceId = ConverterUtils.toStr(command.get("deviceId"));// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
		int commandType = ConverterUtils.toInt(command.get("command_type"));
		ObjectNode paras = JsonUtil.convertObject2ObjectNode(ConverterUtils.toStr(command.get("param")));// "{\"value\":\"12\"}"
		ResultBean<String> result = new ResultBean<String>();

		Map<String, Object> commandMap = CommFunc.getCommandType(Constant.CHINA_UNICOM, commandType);
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
