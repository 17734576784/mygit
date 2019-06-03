/**   
* @Title: ChinaTelecomWaterMeter.java 
* @Package com.nb.servicestrategy.chinatelecom.hz 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月31日 上午9:01:49 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.hz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.model.hz.HzWaterMeter;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ChinaTelecomWaterMeter 
* @Description: 汇中仪表水表数据服务解析
* @author dbr
* @date 2019年5月31日 上午9:01:49 
*  
*/
@Service
public class ChinaTelecomWaterMeter implements IServiceStrategy{

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String, java.util.Map) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String logInfo = "上报汇中水表数据服务,设备：" + deviceId + ",数据：" + serviceMap.toString();
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		if (serviceMap == null || serviceMap.isEmpty()) {
			return;
		}
		
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
		if (dataMap == null) {
			return;
		}
		
		HzWaterMeter waterMeter = JsonUtil.map2Bean(dataMap, HzWaterMeter.class);
		JSONObject paramJson = new JSONObject();
		paramJson.put("deviceId", deviceId);
		paramJson.put("date", waterMeter.getDate());
		paramJson.put("time", waterMeter.getTime());
		paramJson.put("value", waterMeter.getCumulativeFlow());
		paramJson.put("dataStreamId", "");

		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("param", paramJson.toJSONString());

		String apiUrl = baseUrl + Constant.UPLOAD_DATA_URL;
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		try {
			StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(apiUrl, paramMap);
			String response = httpResponse.getContent();
			JSONObject httpResult = JSONObject.parseObject(CommFunc.handleJsonStr(response));
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.CALLBACK).info("上传汇中水表日表底失败：" + logInfo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
