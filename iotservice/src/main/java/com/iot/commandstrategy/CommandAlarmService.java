/**   
* @Title: CommandAlarmService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午4:30:49 
* @version V1.0   
*/
package com.iot.commandstrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.Constant;
import com.iot.utils.DateUtils;
import com.iot.utils.HttpsUtils;
import com.iot.utils.Log4jUtils;

/** 
* @ClassName: CommandAlarmService 
* @Description: 告警命令上行解析服务
* @author dbr
* @date 2018年10月25日 下午4:30:49 
*  
*/
public class CommandAlarmService implements ICommandService {

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		Log4jUtils.getInfo().info("设置告警命令回复，设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());

		String slope = toStr(serviceMap.get("slope"));
		String magnetic = toStr(serviceMap.get("magnetic"));
		String apiUrl = baseUrl + Constant.UPLOAD_ALARMCOMMAND_URL;

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("slope", slope);
		paramMap.put("magnetic", magnetic);
		paramMap.put("date", DateUtils.curDate());
		paramMap.put("time", DateUtils.curTime());

		JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
		if (httpResult.getInteger("status") == Constant.ERROR) {
			Log4jUtils.getInfo().info("设置告警命令回复，设备id：" + deviceId + " ,告警内容：" + serviceMap.toString());
		}
	}

}
