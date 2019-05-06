/**   
* @Title: XtWaterMeterAlarm.java 
* @Package com.nb.model.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月26日 上午9:52:44 
* @version V1.0   
*/
package com.nb.model.xt;

import com.nb.model.BaseModel;

/** 
* @ClassName: SuntrontWaterMeterAlarm 
* @Description: 新天科技WaterMeterAlarm服务上报数据项 
* @author dbr
* @date 2019年4月26日 上午9:52:44 
*  
*/
public class SuntrontWaterMeterAlarm extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 9183361787820755299L;
	
	/** 持续高流量告警 0 否 1 是 */
	private Boolean highFlowAlarm;
	/** 低电量警告0 否 1 是 */
	private Boolean lowBatteryWarning;
	/** 低电量告警0 否 1 是 */
	private Boolean lowBatteryAlarm;
	/** 管网泄漏警告0 否 1 是 */
	private Boolean networkLeak;
	/** 逆流警告 0 否 1 是 */
	private Boolean reverseFlow;
	/** 空管警告 0 否 1 是 */
	private Boolean emptyPipe;
	/** 低水压警告 0 否 1 是 */
	private Boolean lowPressure;
	/** 高水压警告 0 否 1 是 */
	private Boolean highPressure;
	/** 内部高温警告0 否 1 是 */
	private Boolean highInnerTemperature;
	/** 高水温警告 0 否 1 是 */
	private Boolean highTemperature;
	/** 低水温警告 0 否 1 是 */
	private Boolean lowTemperature;
	/** 存储故障警告 0 否 1 是 */
	private Boolean storageFault;
	/** 拆表警告 0 否 1 是 */
	private Boolean dismantlingAlarm;
	/** 流量传感器故障 0 否 1 是 */
	private Boolean flowSensorFault;
	/** 水温传感器故障 0 否 1 是 */
	private Boolean temperatureSensorFault;
	/** 内部温度传感器故障 0 否 1 是 */
	private Boolean InnerTemperatureSensorFault;
	/** 磁干扰 0 否 1 是 */
	private Boolean magneticInterference;
	/** 漏水 0 否 1 是 */
	private Boolean leaking;
	/** 流量异常 0 否 1 是 */
	private Boolean flowAbnormal;
	/** 不采样关阀 0 否 1 是 */
	private Boolean nonSamplingCloseValve;
	/** 直读模块异常 0 否 1 是 */
	private Boolean directReadingAbnormal;
	/** 透支状态 0 否 1 是 */
	private Boolean overdraft;
	/** 余额不足状态 0 否 1 是 */
	private Boolean insufficientBalance;
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
	 * @return the lowBatteryWarning
	 */
	public Boolean getLowBatteryWarning() {
		return lowBatteryWarning;
	}
	/**
	 * @param lowBatteryWarning the lowBatteryWarning to set
	 */
	public void setLowBatteryWarning(Boolean lowBatteryWarning) {
		this.lowBatteryWarning = lowBatteryWarning;
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
	 * @return the networkLeak
	 */
	public Boolean getNetworkLeak() {
		return networkLeak;
	}
	/**
	 * @param networkLeak the networkLeak to set
	 */
	public void setNetworkLeak(Boolean networkLeak) {
		this.networkLeak = networkLeak;
	}
	/**
	 * @return the reverseFlow
	 */
	public Boolean getReverseFlow() {
		return reverseFlow;
	}
	/**
	 * @param reverseFlow the reverseFlow to set
	 */
	public void setReverseFlow(Boolean reverseFlow) {
		this.reverseFlow = reverseFlow;
	}
	/**
	 * @return the emptyPipe
	 */
	public Boolean getEmptyPipe() {
		return emptyPipe;
	}
	/**
	 * @param emptyPipe the emptyPipe to set
	 */
	public void setEmptyPipe(Boolean emptyPipe) {
		this.emptyPipe = emptyPipe;
	}
	/**
	 * @return the lowPressure
	 */
	public Boolean getLowPressure() {
		return lowPressure;
	}
	/**
	 * @param lowPressure the lowPressure to set
	 */
	public void setLowPressure(Boolean lowPressure) {
		this.lowPressure = lowPressure;
	}
	/**
	 * @return the highPressure
	 */
	public Boolean getHighPressure() {
		return highPressure;
	}
	/**
	 * @param highPressure the highPressure to set
	 */
	public void setHighPressure(Boolean highPressure) {
		this.highPressure = highPressure;
	}
	/**
	 * @return the highInnerTemperature
	 */
	public Boolean getHighInnerTemperature() {
		return highInnerTemperature;
	}
	/**
	 * @param highInnerTemperature the highInnerTemperature to set
	 */
	public void setHighInnerTemperature(Boolean highInnerTemperature) {
		this.highInnerTemperature = highInnerTemperature;
	}
	/**
	 * @return the highTemperature
	 */
	public Boolean getHighTemperature() {
		return highTemperature;
	}
	/**
	 * @param highTemperature the highTemperature to set
	 */
	public void setHighTemperature(Boolean highTemperature) {
		this.highTemperature = highTemperature;
	}
	/**
	 * @return the lowTemperature
	 */
	public Boolean getLowTemperature() {
		return lowTemperature;
	}
	/**
	 * @param lowTemperature the lowTemperature to set
	 */
	public void setLowTemperature(Boolean lowTemperature) {
		this.lowTemperature = lowTemperature;
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
	 * @return the dismantlingAlarm
	 */
	public Boolean getDismantlingAlarm() {
		return dismantlingAlarm;
	}
	/**
	 * @param dismantlingAlarm the dismantlingAlarm to set
	 */
	public void setDismantlingAlarm(Boolean dismantlingAlarm) {
		this.dismantlingAlarm = dismantlingAlarm;
	}
	/**
	 * @return the flowSensorFault
	 */
	public Boolean getFlowSensorFault() {
		return flowSensorFault;
	}
	/**
	 * @param flowSensorFault the flowSensorFault to set
	 */
	public void setFlowSensorFault(Boolean flowSensorFault) {
		this.flowSensorFault = flowSensorFault;
	}
	/**
	 * @return the temperatureSensorFault
	 */
	public Boolean getTemperatureSensorFault() {
		return temperatureSensorFault;
	}
	/**
	 * @param temperatureSensorFault the temperatureSensorFault to set
	 */
	public void setTemperatureSensorFault(Boolean temperatureSensorFault) {
		this.temperatureSensorFault = temperatureSensorFault;
	}
	/**
	 * @return the innerTemperatureSensorFault
	 */
	public Boolean getInnerTemperatureSensorFault() {
		return InnerTemperatureSensorFault;
	}
	/**
	 * @param innerTemperatureSensorFault the innerTemperatureSensorFault to set
	 */
	public void setInnerTemperatureSensorFault(Boolean innerTemperatureSensorFault) {
		InnerTemperatureSensorFault = innerTemperatureSensorFault;
	}
	/**
	 * @return the magneticInterference
	 */
	public Boolean getMagneticInterference() {
		return magneticInterference;
	}
	/**
	 * @param magneticInterference the magneticInterference to set
	 */
	public void setMagneticInterference(Boolean magneticInterference) {
		this.magneticInterference = magneticInterference;
	}
	/**
	 * @return the leaking
	 */
	public Boolean getLeaking() {
		return leaking;
	}
	/**
	 * @param leaking the leaking to set
	 */
	public void setLeaking(Boolean leaking) {
		this.leaking = leaking;
	}
	/**
	 * @return the flowAbnormal
	 */
	public Boolean getFlowAbnormal() {
		return flowAbnormal;
	}
	/**
	 * @param flowAbnormal the flowAbnormal to set
	 */
	public void setFlowAbnormal(Boolean flowAbnormal) {
		this.flowAbnormal = flowAbnormal;
	}
	/**
	 * @return the nonSamplingCloseValve
	 */
	public Boolean getNonSamplingCloseValve() {
		return nonSamplingCloseValve;
	}
	/**
	 * @param nonSamplingCloseValve the nonSamplingCloseValve to set
	 */
	public void setNonSamplingCloseValve(Boolean nonSamplingCloseValve) {
		this.nonSamplingCloseValve = nonSamplingCloseValve;
	}
	/**
	 * @return the directReadingAbnormal
	 */
	public Boolean getDirectReadingAbnormal() {
		return directReadingAbnormal;
	}
	/**
	 * @param directReadingAbnormal the directReadingAbnormal to set
	 */
	public void setDirectReadingAbnormal(Boolean directReadingAbnormal) {
		this.directReadingAbnormal = directReadingAbnormal;
	}
	/**
	 * @return the overdraft
	 */
	public Boolean getOverdraft() {
		return overdraft;
	}
	/**
	 * @param overdraft the overdraft to set
	 */
	public void setOverdraft(Boolean overdraft) {
		this.overdraft = overdraft;
	}
	/**
	 * @return the insufficientBalance
	 */
	public Boolean getInsufficientBalance() {
		return insufficientBalance;
	}
	/**
	 * @param insufficientBalance the insufficientBalance to set
	 */
	public void setInsufficientBalance(Boolean insufficientBalance) {
		this.insufficientBalance = insufficientBalance;
	}
	
	
}
