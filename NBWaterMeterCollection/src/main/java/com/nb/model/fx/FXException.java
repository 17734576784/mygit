/**   
* @Title: FXReport.java 
* @Package com.nb.model.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 下午4:13:42 
* @version V1.0   
*/
package com.nb.model.fx;

import java.io.Serializable;

/** 
* @ClassName: FXException 
* @Description: 府星水表电信数据上报异常项 
* @author dbr
* @date 2019年4月15日 下午4:13:42 
*  
*/
public class FXException implements Serializable {

	private static final long serialVersionUID = 6897052349813312149L;
	/** 固件版本 */
	private String version;
	/** 是否是按键触发上报 0:自动定时上报 1:按键触发上报 */
	private String isKeyTriggerThisReport;
	/** : 累计总水量 */
	private String totalCumulateWater;
	/** 当月累计水量 */
	private String monthCumulateWater;
	/** 水表编号 */
	private String waterMeterNo;
	/** 水表口径 */
	private String meterDiameter;
	/** 表端当前时间 */
	private String currentDateTime;
	/** 阀门状态 */
	private String valveState;
	/** 电池电压 */
	private String batteryVoltage;
	/** 异常代码 */
	private String errorNo;
	/** 上报日期 */
	private int date;
	/** 上报时间 */
	private int time;

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the isKeyTriggerThisReport
	 */
	public String getIsKeyTriggerThisReport() {
		return isKeyTriggerThisReport;
	}
	/**
	 * @param isKeyTriggerThisReport the isKeyTriggerThisReport to set
	 */
	public void setIsKeyTriggerThisReport(String isKeyTriggerThisReport) {
		this.isKeyTriggerThisReport = isKeyTriggerThisReport;
	}
	/**
	 * @return the totalCumulateWater
	 */
	public String getTotalCumulateWater() {
		return totalCumulateWater;
	}
	/**
	 * @param totalCumulateWater the totalCumulateWater to set
	 */
	public void setTotalCumulateWater(String totalCumulateWater) {
		this.totalCumulateWater = totalCumulateWater;
	}
	/**
	 * @return the monthCumulateWater
	 */
	public String getMonthCumulateWater() {
		return monthCumulateWater;
	}
	/**
	 * @param monthCumulateWater the monthCumulateWater to set
	 */
	public void setMonthCumulateWater(String monthCumulateWater) {
		this.monthCumulateWater = monthCumulateWater;
	}
	/**
	 * @return the waterMeterNo
	 */
	public String getWaterMeterNo() {
		return waterMeterNo;
	}
	/**
	 * @param waterMeterNo the waterMeterNo to set
	 */
	public void setWaterMeterNo(String waterMeterNo) {
		this.waterMeterNo = waterMeterNo;
	}
	/**
	 * @return the meterDiameter
	 */
	public String getMeterDiameter() {
		return meterDiameter;
	}
	/**
	 * @param meterDiameter the meterDiameter to set
	 */
	public void setMeterDiameter(String meterDiameter) {
		this.meterDiameter = meterDiameter;
	}
	/**
	 * @return the currentDateTime
	 */
	public String getCurrentDateTime() {
		return currentDateTime;
	}
	/**
	 * @param currentDateTime the currentDateTime to set
	 */
	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
	/**
	 * @return the valveState
	 */
	public String getValveState() {
		return valveState;
	}
	/**
	 * @param valveState the valveState to set
	 */
	public void setValveState(String valveState) {
		this.valveState = valveState;
	}
	/**
	 * @return the batteryVoltage
	 */
	public String getBatteryVoltage() {
		return batteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	/**
	 * @return the errorNo
	 */
	public String getErrorNo() {
		return errorNo;
	}
	/**
	 * @param errorNo the errorNo to set
	 */
	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}
	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}
