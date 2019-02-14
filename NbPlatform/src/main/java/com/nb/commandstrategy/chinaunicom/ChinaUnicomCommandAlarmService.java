/**   
* @Title: CommandAlarmService.java 
* @Package com.nb.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午4:30:49 
* @version V1.0   
*/
package com.nb.commandstrategy.chinaunicom;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.commandstrategy.ICommandService;
import com.nb.http.HttpsClientUtil;

/** 
* @ClassName: ChinaUnicomCommandAlarmService 
* @Description: 告警命令上行解析服务
* @author dbr
* @date 2018年10月25日 下午4:30:49 
*  
*/
@Component
public class ChinaUnicomCommandAlarmService implements ICommandService {

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param commandMap 
	* @see com.nb.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		LoggerUtils.Logger(LogName.INFO).info("设置告警命令回复，设备id：" + deviceId + " ,告警内容：" + commandMap.toString());
		String slope = toStr(commandMap.get("slopestatus"));
		String magnetic = toStr(commandMap.get("magneticstatus"));
		String apiUrl = baseUrl + Constant.UPLOAD_ALARMCOMMAND_URL;

		JSONObject paramJson = new JSONObject();
		paramJson.put("slope", slope);
		paramJson.put("magnetic", magnetic);
		paramJson.put("date", DateUtils.curDate());
		paramJson.put("time", DateUtils.curTime());
		paramJson.put("deviceId", deviceId);

//		Map<String, Object> urlMap = new HashMap<>();
//		urlMap.put("param", paramJson.toString());
		try {
//			JSONObject httpResult = HttpsUtils.doPost(apiUrl, urlMap);
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostJsonGetStatusLine(apiUrl,
			paramJson.toJSONString());
			JSONObject httpResult = JSONObject.parseObject(httpResponse.getContent());
			
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.INFO).info("推送设置告警命令回复失败，设备id：" + deviceId + " ,告警内容：" + commandMap.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.ERROR).error("告警命令回复执行异常", e);
		}
	}

}
