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
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/**   
 * @ClassName:  ChinaTelecomAlarmService   
 * @Description:主动上报告警解析服务 
 * @author: dbr 
 * @date:   2018年10月22日 下午1:59:29   
 *      
 */
@Component
public class ChinaTelecomKeLucencyTransmission implements IServiceStrategy{

	@Value("${website.czurl}")
	private String baseUrl;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String logInfo = "上传376透传数据:设备id：" + deviceId + " ,透传内容：" + serviceMap.toString();
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		String apiUrl = baseUrl + Constant.UPLOAD_376_URL;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			
			String lucentydata = toStr(dataMap.get("lucentydata"));

			JSONObject paramJson = new JSONObject();
	 
			paramJson.put("deviceId", deviceId);
			paramJson.put("frame", lucentydata);
			
 			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
 			StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostJsonGetStatusLine(apiUrl, null,
					paramJson.toJSONString());
 			
			String response = httpResponse.getContent();
			JSONObject httpResult =  JSONObject.parseObject(CommFunc.handleJsonStr(response));
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.CALLBACK).info("上传告警失败：" + logInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.ERROR).error("上传376透传报文执行失败," + logInfo, e);
		}
	}
}
