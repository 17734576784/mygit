/**   
* @Title: BatteryService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月9日 下午2:31:53 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: BatteryService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月9日 下午2:31:53 
*  
*/
@Service
public class BatteryService implements IServiceStrategy {

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
		// TODO Auto-generated method stub
		String logInfo = "上报电池信息：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.Logger(LogName.CALLBACK).info(logInfo);
 		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			
			String evnetTime = serviceMap.get("evnetTime");
			String date = evnetTime.substring(0, 8); 
			String time = toStr(toInt(evnetTime.substring(9, 15)) + 80000);
			
			// 电池电压 单位:V
			double batteryVoltage = toDouble(dataMap.get("batteryVoltage"));
			// 电压报警 Y:报警,N:正常
			String batteryvoltageAlarm = toStr(dataMap.get("batteryvoltageAlarm"));
			// 电压报警阈值
			double batteryvoltageThreshold = toDouble(dataMap.get("batteryvoltageThreshold"));
			// 电池电压告警
			if (batteryvoltageAlarm.equals(Constant.BATTERY_ALARM)) {
				
			} else { //正常上报电池电压

			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.Logger(LogName.ERROR).error("解析上传电池电压失败," + logInfo, e);
		}
	}

}
