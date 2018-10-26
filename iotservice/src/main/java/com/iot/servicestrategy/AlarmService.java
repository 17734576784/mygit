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
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {

		Log4jUtils.getInfo().info("上传告警:设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		String apiUrl = baseUrl + Constant.UPLOAD_ALARM_URL;

		String slope = toStr(serviceMap.get("slope"));
		String magnetic = toStr(serviceMap.get("magnetic"));
		String alarmtype = toStr(serviceMap.get("alarmtype"));

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
