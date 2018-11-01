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
import com.iot.utils.Constant;
import com.iot.utils.HttpsUtils;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;

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

		Log4jUtils.getInfo().info("上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		System.out.println("上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		String apiUrl = baseUrl + Constant.UPLOAD_ALARM_URL;
		
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());

		String slope = toStr(dataMap.get("slope"));
		String magnetic = toStr(dataMap.get("magnetic"));
		String alarmtype = toStr(dataMap.get("alarmtype"));

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("slope", slope);
		paramMap.put("magnetic", magnetic);
		paramMap.put("alarmtype", alarmtype);

		JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
		if (httpResult.getInteger("status") == Constant.ERROR) {
			Log4jUtils.getInfo().info("上传告警失败：设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		}
	}

}
