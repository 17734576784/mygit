/**   
* @Title: FXReport.java 
* @Package com.nb.model.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 下午4:13:42 
* @version V1.0   
*/
package com.nb.model.fx;

import com.nb.model.BaseModel;

/** 
* @ClassName: FXReport 
* @Description: 府星水表电信数据上报数据项 
* @author dbr
* @date 2019年4月15日 下午4:13:462 
*  
*/
public class FXReport extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 6897052349813312149L;

	private String afn;
	private String dir;
	private String imsi;
	private String cnt;
	private String preStoredWater;
	private String remainPreStoredWater;
	private String signalStrength;
	/** 固件版本 */
	private String version;
	/** 是否是按键触发上报 0:自动定时上报 1:按键触发上报 */
	private String isKeyTriggerThisReport;
	/** 累计总水量 */
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
	
	/**
	 * @return the afn
	 */
	public String getAfn() {
		return afn;
	}
	/**
	 * @param afn the afn to set
	 */
	public void setAfn(String afn) {
		this.afn = afn;
	}
	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the cnt
	 */
	public String getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	/**
	 * @return the preStoredWater
	 */
	public String getPreStoredWater() {
		return preStoredWater;
	}
	/**
	 * @param preStoredWater the preStoredWater to set
	 */
	public void setPreStoredWater(String preStoredWater) {
		this.preStoredWater = preStoredWater;
	}
	/**
	 * @return the remainPreStoredWater
	 */
	public String getRemainPreStoredWater() {
		return remainPreStoredWater;
	}
	/**
	 * @param remainPreStoredWater the remainPreStoredWater to set
	 */
	public void setRemainPreStoredWater(String remainPreStoredWater) {
		this.remainPreStoredWater = remainPreStoredWater;
	}
	/**
	 * @return the signalStrength
	 */
	public String getSignalStrength() {
		return signalStrength;
	}
	/**
	 * @param signalStrength the signalStrength to set
	 */
	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}
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
	
}
