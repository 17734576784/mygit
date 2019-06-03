/**   
* @Title: HzWaterMeterAlarmStatus.java 
* @Package com.nb.model.hz 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月31日 上午11:17:31 
* @version V1.0   
*/
package com.nb.model.hz;

import static com.nb.utils.ConverterUtils.toInt;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.nb.utils.DateUtils;

/** 
* @ClassName: HzWaterMeterAlarmStatus 
* @Description: 汇中水表告警数据
* @author dbr
* @date 2019年5月31日 上午11:17:31 
*  
*/
public class HzWaterMeterAlarmStatus implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private Boolean lowFlowAlarm;// 持续低流量告警，0未发生，1发生
	private Boolean highFlowAlarm;// 持续高流量告警，0未发生，1发生
	private Boolean lowBatteryAlarm;// 低电压告警，0未发生，1发生
	private Boolean batteryRunOutAlarm;// 电量严重不足即将耗尽，0未发生，1发生
	private Boolean highTemperatureAlarm;// 高温告警，0未发生，1发生
	private Boolean lowTemperatureAlarm;// 低温告警，0未发生，1发生
	private Boolean innerErrorAlarm;// 内部错误告警，0未发生，1发生
	private Boolean storageFault;// 存储故障告警，0未发生，1发生
	private Boolean waterTempratureSensorFault;// 水温传感器故障告警，0未发生，1发生
	private Boolean communicationAlarm;// 通讯异常告警，0未发生，1发生
	/** 事项上报日期 */
	private int eventDate;

	/** 事项上报时间 */
	private int eventTime;

	/**
	 * @param evnetTime the evnetTime to set
	 */
	public void setEvnetTime(String evnetTime) {
		Date date = new Date();
		if (evnetTime != null && !evnetTime.isEmpty()) {
			date = DateUtils.utcToLocal(evnetTime, DateUtils.UTC_PATTERN);

		}
		this.eventDate = toInt(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN));
		this.eventTime = toInt(DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN));
	}
	
	public void setEvnetTime(Map<String, String> serviceMap) {
		if (serviceMap.containsKey("eventTime")) {
			setEvnetTime(serviceMap.get("eventTime"));
		} else if (serviceMap.containsKey("timestamp")) {
			setEvnetTime(serviceMap.get("timestamp"));
		} else {
			setEvnetTime("");
		}
	}
	
	/**
	 * @return the eventDate
	 */
	public int getEventDate() {
		return eventDate;
	}

	/**
	 * @return the eventTime
	 */
	public int getEventTime() {
		return eventTime;
	}

	/**
	 * @return the lowFlowAlarm
	 */
	public Boolean getLowFlowAlarm() {
		return lowFlowAlarm;
	}
	/**
	 * @param lowFlowAlarm the lowFlowAlarm to set
	 */
	public void setLowFlowAlarm(Boolean lowFlowAlarm) {
		this.lowFlowAlarm = lowFlowAlarm;
	}
	/**
	 * @return the highFlowAlarm
	 */
	public Boolean getHighFlowAlarm() {
		return highFlowAlarm;
	}
	/**
	 * @param highFlowAlarm the highFlowAlarm to set
	 */
	public void setHighFlowAlarm(Boolean highFlowAlarm) {
		this.highFlowAlarm = highFlowAlarm;
	}
	/**
	 * @return the lowBatteryAlarm
	 */
	public Boolean getLowBatteryAlarm() {
		return lowBatteryAlarm;
	}
	/**
	 * @param lowBatteryAlarm the lowBatteryAlarm to set
	 */
	public void setLowBatteryAlarm(Boolean lowBatteryAlarm) {
		this.lowBatteryAlarm = lowBatteryAlarm;
	}
	/**
	 * @return the batteryRunOutAlarm
	 */
	public Boolean getBatteryRunOutAlarm() {
		return batteryRunOutAlarm;
	}
	/**
	 * @param batteryRunOutAlarm the batteryRunOutAlarm to set
	 */
	public void setBatteryRunOutAlarm(Boolean batteryRunOutAlarm) {
		this.batteryRunOutAlarm = batteryRunOutAlarm;
	}
	/**
	 * @return the highTemperatureAlarm
	 */
	public Boolean getHighTemperatureAlarm() {
		return highTemperatureAlarm;
	}
	/**
	 * @param highTemperatureAlarm the highTemperatureAlarm to set
	 */
	public void setHighTemperatureAlarm(Boolean highTemperatureAlarm) {
		this.highTemperatureAlarm = highTemperatureAlarm;
	}
	/**
	 * @return the lowTemperatureAlarm
	 */
	public Boolean getLowTemperatureAlarm() {
		return lowTemperatureAlarm;
	}
	/**
	 * @param lowTemperatureAlarm the lowTemperatureAlarm to set
	 */
	public void setLowTemperatureAlarm(Boolean lowTemperatureAlarm) {
		this.lowTemperatureAlarm = lowTemperatureAlarm;
	}
	/**
	 * @return the innerErrorAlarm
	 */
	public Boolean getInnerErrorAlarm() {
		return innerErrorAlarm;
	}
	/**
	 * @param innerErrorAlarm the innerErrorAlarm to set
	 */
	public void setInnerErrorAlarm(Boolean innerErrorAlarm) {
		this.innerErrorAlarm = innerErrorAlarm;
	}
	/**
	 * @return the storageFault
	 */
	public Boolean getStorageFault() {
		return storageFault;
	}
	/**
	 * @param storageFault the storageFault to set
	 */
	public void setStorageFault(Boolean storageFault) {
		this.storageFault = storageFault;
	}
	/**
	 * @return the waterTempratureSensorFault
	 */
	public Boolean getWaterTempratureSensorFault() {
		return waterTempratureSensorFault;
	}
	/**
	 * @param waterTempratureSensorFault the waterTempratureSensorFault to set
	 */
	public void setWaterTempratureSensorFault(Boolean waterTempratureSensorFault) {
		this.waterTempratureSensorFault = waterTempratureSensorFault;
	}
	/**
	 * @return the communicationAlarm
	 */
	public Boolean getCommunicationAlarm() {
		return communicationAlarm;
	}
	/**
	 * @param communicationAlarm the communicationAlarm to set
	 */
	public void setCommunicationAlarm(Boolean communicationAlarm) {
		this.communicationAlarm = communicationAlarm;
	}

}
