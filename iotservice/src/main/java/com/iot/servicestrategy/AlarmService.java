/**
 * 
 */
package com.iot.servicestrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.Constant;
import com.iot.utils.HttpsUtils;
import com.iot.utils.JsonUtil;

/**   
 * @ClassName:  AlarmService   
 * @Description:主动上报告警解析服务 
 * @author: dbr 
 * @date:   2018年10月22日 下午1:59:29   
 *      
 */
@Component
public class AlarmService implements IServiceStrategy{

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {

		LoggerUtils.Logger(LogName.CALLBACK).info("上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		System.out.println("上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		String apiUrl = baseUrl + Constant.UPLOAD_ALARM_URL;
		
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());

		String slope = toStr(dataMap.get("slope"));
		String magnetic = toStr(dataMap.get("magnetic"));
		String alarmtype = toStr(dataMap.get("alarmtype"));

		JSONObject paramJson = new JSONObject();
		paramJson.put("slope", slope);
		paramJson.put("magnetic", magnetic);
		paramJson.put("alarmtype", alarmtype);
		paramJson.put("deviceId", deviceId);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("param", paramJson.toString());
		JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
		if (httpResult.getInteger("status") == Constant.ERROR) {
			LoggerUtils.Logger(LogName.CALLBACK).info("上传告警失败：设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		}
	}

}
