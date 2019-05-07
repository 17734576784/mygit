/**   
* @Title: SuntrontMeter.java 
* @Package com.nb.model.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午3:28:15 
* @version V1.0   
*/
package com.nb.model.xt;

import com.nb.model.BaseModel;
import com.nb.utils.Constant;

/** 
* @ClassName: SuntrontMeter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年5月6日 下午3:28:15 
*  
*/
public class SuntrontMeter extends BaseModel {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -3945884522980163794L;
	/** 信号强度 0-31 */
	private Integer signalStrength;
	/** 当前累计流量 单位：L */
	private Double currentReading;
	/** 日激活时长 单位：秒 */
	private Integer dailyActivityTime;
	/** 软件版本号 字符串 */
	private String softwareVersion;
	/** 硬件版本号 字符串 */
	private String hardwareVersion;
	/**
	 * @return the signalStrength
	 */
	public Integer getSignalStrength() {
		return signalStrength;
	}
	/**
	 * @param signalStrength the signalStrength to set
	 */
	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}
	/**
	 * @return the currentReading
	 */
	public Double getCurrentReading() {
		return currentReading;
	}
	/**
	 * @param currentReading the currentReading to set
	 */
	public void setCurrentReading(Double currentReading) {
		this.currentReading = currentReading / Constant.NUM_1000;
	}
	/**
	 * @return the dailyActivityTime
	 */
	public Integer getDailyActivityTime() {
		return dailyActivityTime;
	}
	/**
	 * @param dailyActivityTime the dailyActivityTime to set
	 */
	public void setDailyActivityTime(Integer dailyActivityTime) {
		this.dailyActivityTime = dailyActivityTime;
	}
	/**
	 * @return the softwareVersion
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	/**
	 * @param softwareVersion the softwareVersion to set
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	/**
	 * @return the hardwareVersion
	 */
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	/**
	 * @param hardwareVersion the hardwareVersion to set
	 */
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	
	
}
