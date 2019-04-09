/**   
* @Title: DeviceAlarmService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月9日 下午3:05:53 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: DeviceAlarmService 
* @Description: 竟达告警信息 
* @author dbr
* @date 2019年4月9日 下午3:05:53 
*  
*/
@Service
public class DeviceAlarmService implements IServiceStrategy {

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
		String logInfo = "上报竟达告警信息：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.Logger(LogName.CALLBACK).info(logInfo);
 		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			
			String evnetTime = serviceMap.get("evnetTime");
			String date = evnetTime.substring(0, 8); 
			String time = toStr(toInt(evnetTime.substring(9, 15)) + 80000);
			
			// 大流量报警 开始时间 YYMMDDHHMMSS
			String peakFlowStartTime = toStr(dataMap.get("peakFlowStartTime"));
			// 最大流速 单位: L/h
			double peakFlow = toDouble(dataMap.get("peakFlow"));
			// 篡改告警 1：篡改，被如下数据被修改时产生此告警：序列号，生产日期，累计流量，0不报警
			byte tampered = toByte(dataMap.get("tampered"));
			// 反流告警 1报警，0不报警
			byte reverseFlowAlarm = toByte(dataMap.get("reverseFlowAlarm"));
			// 磁干扰 1报警，0不报警
			byte magneticInterferenceAlarm = toByte(dataMap.get("magneticInterferenceAlarm"));
			// 内部错误 水表厂家自行定义
			byte internalAlarm = toByte(dataMap.get("internalAlarm"));
			// 远传模块分离告警 1报警，0不报警
			byte disconnectAlarm = toByte(dataMap.get("disconnectAlarm"));


		 
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.Logger(LogName.ERROR).error("解析上传电池电压失败," + logInfo, e);
		}
	}

}
