/**   
* @Title: UpresultService.java 
* @Package com.nb.servicestrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月22日 上午8:52:48 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom;

import static com.nb.utils.ConverterUtils.toStr;

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
* @ClassName: ChinaTelecomUpresultService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月22日 上午8:52:48 
*  
*/
@Component
public class ChinaTelecomUpresultService implements IServiceStrategy {
	/** 网站对接服务地址 */
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
		String logInfo = "上传升级结果:设备id：" + deviceId + " ,内容：" + serviceMap.toString();
		System.out.println(logInfo);
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		String apiUrl = baseUrl + Constant.UPLOAD_UPGRADERESULT_URL;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			String version = toStr(dataMap.get("version"));
			String status = toStr(dataMap.get("status"));

			Map<String, Object> paramJson = new HashMap<>();
			paramJson.put("status", status);
			paramJson.put("version", version);
			paramJson.put("deviceId", deviceId);

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject response = HttpsUtils.doPost(apiUrl, paramMap);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					LoggerUtils.Logger(LogName.CALLBACK).info("升级成功发送成功，response：" + response);
				} else {
					LoggerUtils.Logger(LogName.CALLBACK).info("升级成功发送失败，response：" + response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("升级成功发送异常，内容：" + logInfo);
		}	
	}
	
}
