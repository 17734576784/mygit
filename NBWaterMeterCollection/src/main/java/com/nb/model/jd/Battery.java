/**   
* @Title: Battery.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:07:45 
* @version V1.0   
*/
package com.nb.model.jd;

import java.io.Serializable;

import com.nb.utils.CommFunc;
import com.nb.utils.Constant;

/** 
* @ClassName: Battery 
* @Description: 竟达水表电池类 
* @author dbr
* @date 2019年4月10日 上午9:07:45 
*  
*/
public class Battery implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -4837778433106762441L;
	private Double batteryVoltage;// 电压 单位:V
	private String batteryvoltageAlarm; // 电压报警 Y:报警,N:正常
	private Double batteryvoltageThreshold; // 电压报警阈值
	private String evnetTime; // 事项上报时间
	/**
	 * @return the evnetTime
	 */
	public String getEvnetTime() {
		return evnetTime;
	}
	/**
	 * @param evnetTime the evnetTime to set
	 */
	public void setEvnetTime(String evnetTime) {
		this.evnetTime = CommFunc.parseEventTime(evnetTime);
	}
	/**
	 * @return the batteryVoltage
	 */
	public Double getBatteryVoltage() {
		return batteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(Double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	/**
	 * @return the batteryvoltageAlarm
	 */
	public String getBatteryvoltageAlarm() {
		return batteryvoltageAlarm;
	}
	/**
	 * @param batteryvoltageAlarm the batteryvoltageAlarm to set
	 */
	public void setBatteryvoltageAlarm(String batteryvoltageAlarm) {
		this.batteryvoltageAlarm = batteryvoltageAlarm;
	}
	/**
	 * @return the batteryvoltageThreshold
	 */
	public Double getBatteryvoltageThreshold() {
		return batteryvoltageThreshold;
	}
	/**
	 * @param batteryvoltageThreshold the batteryvoltageThreshold to set
	 */
	public void setBatteryvoltageThreshold(Double batteryvoltageThreshold) {
		this.batteryvoltageThreshold = batteryvoltageThreshold;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Battery [batteryVoltage=" + batteryVoltage + ", batteryvoltageAlarm=" + batteryvoltageAlarm
				+ ", batteryvoltageThreshold=" + batteryvoltageThreshold + ", evnetTime=" + evnetTime + "]";
	}

	public boolean isAlarm() {
		if (this.batteryvoltageAlarm.equals(Constant.BATTERY_ALARM)) {
			return true;
		} else {
			return false;
		}
	}

}
