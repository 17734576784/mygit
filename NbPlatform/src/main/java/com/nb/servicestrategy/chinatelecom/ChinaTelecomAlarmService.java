/**
 * 
 */
package com.nb.servicestrategy.chinatelecom;

import static com.nb.utils.ConverterUtils.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsUtils;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/**   
 * @ClassName:  AlarmService   
 * @Description:主动上报告警解析服务 
 * @author: dbr 
 * @date:   2018年10月22日 下午1:59:29   
 *      
 */
@Component
public class ChinaTelecomAlarmService implements IServiceStrategy{

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String logInfo = "上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString();
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		String apiUrl = baseUrl + Constant.UPLOAD_ALARM_URL;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			
			String evnetTime = serviceMap.get("eventTime");
			String date = evnetTime.substring(0, 8); 
			String time = toStr(toInt(evnetTime.substring(9, 15)) + 80000);
			
			String slope = toStr(dataMap.get("slope"));
			String magnetic = toStr(dataMap.get("magnetic"));
			String alarmtype = toStr(dataMap.get("alarmtype"));

			Map<String,Object> paramJson = new HashMap<>();
			paramJson.put("slope", slope);
			paramJson.put("magnetic", magnetic);
			paramJson.put("alarmtype", alarmtype);
			paramJson.put("deviceId", deviceId);
			paramJson.put("date", date);
			paramJson.put("time", time);

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.CALLBACK).info("上传告警失败：" + logInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.ERROR).error("上传告警执行失败," + logInfo, e);
		}
	}
}
