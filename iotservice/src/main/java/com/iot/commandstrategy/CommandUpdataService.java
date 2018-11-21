/**   
* @Title: UpdataService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:00 
* @version V1.0   
*/
package com.iot.commandstrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.Constant;
import com.iot.utils.IotHttpsUtil;
import com.iot.utils.JsonUtil;

/** 
* @ClassName: CommandUpdataService 
* @Description: 远程升级上行解析服务
* @author dbr
* @date 2018年10月25日 下午2:37:00 
*  
*/
@Component
public class CommandUpdataService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		
		String logInfo = "升级命令回复，设备id：" + deviceId + ",内容：" + commandMap.toString();
		LoggerUtils.Logger(LogName.INFO).info(logInfo);
		System.out.println(logInfo);
		String slope = toStr(commandMap.get("slopestatus"));
		String magnetic = toStr(commandMap.get("magneticstatus"));
		
		
	}
	
	public void sendUpgradeData(String deviceId) {
		try {
			IotHttpsUtil httpsUtil = new IotHttpsUtil();
			httpsUtil.initSSLConfigForTwoWay();
		
			String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);
			String urlPostAsynCmd = Constant.POST_ASYN_CMD;
			String appId = Constant.APPID;
			String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
	
			String serviceId = "serviceName";
			String method = "method";
			ObjectNode paras = JsonUtil.convertObject2ObjectNode("");// "{\"value\":\"12\"}"
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
