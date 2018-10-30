/**
 * 
 */
package com.iot.servicestrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.iot.utils.JsonUtil;

/**   
 * @ClassName:  CheckService   
 * @Description:主动查询解析服务   
 * @author: dbr 
 * @date:   2018年10月22日 下午2:02:14   
 *      
 */
@Component
public class CheckService implements IServiceStrategy {

	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		try {
			
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			
			String check = toStr(dataMap.get("check"));
			System.out.println("check : "+ check);
//			if (isdata.equals(Constant.UPLOADPIC)) {
//				IotHttpsUtil httpsUtil = new IotHttpsUtil();
//				httpsUtil.initSSLConfigForTwoWay();
//				String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);
//
//				String urlPostAsynCmd = Constant.POST_ASYN_CMD;
//				String appId = Constant.APPID;
//				String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
//				
//		        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"1\"}");
//
//				Map<String, Object> paramCommand = new HashMap<>();
//				paramCommand.put("serviceId", "PhotoData");
//				paramCommand.put("method", "SendPhoto_once");
//				paramCommand.put("paras", paras);
//				
//				Map<String, Object> paramPostAsynCmd = new HashMap<>();
//				paramPostAsynCmd.put("deviceId", deviceId);
//				paramPostAsynCmd.put("command", paramCommand);
//				paramPostAsynCmd.put("callbackUrl", callbackUrl);
//
//				String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
//				Map<String, String> header = new HashMap<>();
//				header.put(Constant.HEADER_APP_KEY, appId);
//				header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
//
//				HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);
//
//				String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
//				CommFunc.result(Constant.SUCCESS, responseBody);
//				System.out.println("isdata : 下发成功");
//
//			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
