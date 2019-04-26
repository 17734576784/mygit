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
* @ClassName: XtWaterMeterAlarm 
* @Description: 新天科技WaterMeterAlarm服务上报数据项 
* @author dbr
* @date 2019年4月26日 上午9:52:44 
*  
*/
public class XtWaterMeterAlarm extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 9183361787820755299L;
	
	/**持续高流量告警*/
	private Integer highFlowAlarm;
	/**低电量警告*/
	private Integer lowBatteryWarning;
	/**低电量告警*/
	private Integer lowBatteryAlarm;
	/**管网泄漏警告
	private Integer networkLeak;*/
	 /**逆流警告*/
	private Integer reverseFlow; 
	 /*空管警告**/
	private Integer emptyPipe; 
	/**低水压警告*/
	private Integer lowPressure;
	/**高水压警告*/
	private Integer highPressure;
	/**内部高温警告*/
	private Integer highInnerTemperature;
	/**高水温警告*/ 
	private Integer highTemperature; 
	/**低水温警告*/ 
	private Integer lowTemperature;
	/**存储故障警告*/
	private Integer storageFault; 
	/**拆表警告*/
	private Integer dismantlingAlarm; 
	/**流量传感器故障*/
	private Integer flowSensorFault; 
	 /**水温传感器故障*/
	private Integer temperatureSensorFault; 
	/**内部温度传感器故障*/
	private Integer InnerTemperatureSensorFault;
	/**磁干扰*/
	private Integer magneticInterference;
	/**漏水*/ 
	private Integer leaking; 
	/**流量异常*/ 
	private Integer flowAbnormal; 
	/**不采样关阀*/
	private Integer nonSamplingCloseValve;
	/** 直读模块异常*/
	private Integer directReadingAbnormal;
	/**透支状态*/ 
	private Integer overdraft;
	/**余额不足状态*/
	private Integer insufficientBalance;
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the highFlowAlarm
	 */
	public Integer getHighFlowAlarm() {
		return highFlowAlarm;
	}
	/**
	 * @param highFlowAlarm the highFlowAlarm to set
	 */
	public void setHighFlowAlarm(Integer highFlowAlarm) {
		this.highFlowAlarm = highFlowAlarm;
	}
	/**
	 * @return the lowBatteryWarning
	 */
	public Integer getLowBatteryWarning() {
		return lowBatteryWarning;
	}
	/**
	 * @param lowBatteryWarning the lowBatteryWarning to set
	 */
	public void setLowBatteryWarning(Integer lowBatteryWarning) {
		this.lowBatteryWarning = lowBatteryWarning;
	}
	/**
	 * @return the lowBatteryAlarm
	 */
	public Integer getLowBatteryAlarm() {
		return lowBatteryAlarm;
	}
	/**
	 * @param lowBatteryAlarm the lowBatteryAlarm to set
	 */
	public void setLowBatteryAlarm(Integer lowBatteryAlarm) {
		this.lowBatteryAlarm = lowBatteryAlarm;
	}
	/**
	 * @return the reverseFlow
	 */
	public Integer getReverseFlow() {
		return reverseFlow;
	}
	/**
	 * @param reverseFlow the reverseFlow to set
	 */
	public void setReverseFlow(Integer reverseFlow) {
		this.reverseFlow = reverseFlow;
	}
	/**
	 * @return the emptyPipe
	 */
	public Integer getEmptyPipe() {
		return emptyPipe;
	}
	/**
	 * @param emptyPipe the emptyPipe to set
	 */
	public void setEmptyPipe(Integer emptyPipe) {
		this.emptyPipe = emptyPipe;
	}
	/**
	 * @return the lowPressure
	 */
	public Integer getLowPressure() {
		return lowPressure;
	}
	/**
	 * @param lowPressure the lowPressure to set
	 */
	public void setLowPressure(Integer lowPressure) {
		this.lowPressure = lowPressure;
	}
	/**
	 * @return the highPressure
	 */
	public Integer getHighPressure() {
		return highPressure;
	}
	/**
	 * @param highPressure the highPressure to set
	 */
	public void setHighPressure(Integer highPressure) {
		this.highPressure = highPressure;
	}
	/**
	 * @return the highInnerTemperature
	 */
	public Integer getHighInnerTemperature() {
		return highInnerTemperature;
	}
	/**
	 * @param highInnerTemperature the highInnerTemperature to set
	 */
	public void setHighInnerTemperature(Integer highInnerTemperature) {
		this.highInnerTemperature = highInnerTemperature;
	}
	/**
	 * @return the highTemperature
	 */
	public Integer getHighTemperature() {
		return highTemperature;
	}
	/**
	 * @param highTemperature the highTemperature to set
	 */
	public void setHighTemperature(Integer highTemperature) {
		this.highTemperature = highTemperature;
	}
	/**
	 * @return the lowTemperature
	 */
	public Integer getLowTemperature() {
		return lowTemperature;
	}
	/**
	 * @param lowTemperature the lowTemperature to set
	 */
	public void setLowTemperature(Integer lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	/**
	 * @return the storageFault
	 */
	public Integer getStorageFault() {
		return storageFault;
	}
	/**
	 * @param storageFault the storageFault to set
	 */
	public void setStorageFault(Integer storageFault) {
		this.storageFault = storageFault;
	}
	/**
	 * @return the dismantlingAlarm
	 */
	public Integer getDismantlingAlarm() {
		return dismantlingAlarm;
	}
	/**
	 * @param dismantlingAlarm the dismantlingAlarm to set
	 */
	public void setDismantlingAlarm(Integer dismantlingAlarm) {
		this.dismantlingAlarm = dismantlingAlarm;
	}
	/**
	 * @return the flowSensorFault
	 */
	public Integer getFlowSensorFault() {
		return flowSensorFault;
	}
	/**
	 * @param flowSensorFault the flowSensorFault to set
	 */
	public void setFlowSensorFault(Integer flowSensorFault) {
		this.flowSensorFault = flowSensorFault;
	}
	/**
	 * @return the temperatureSensorFault
	 */
	public Integer getTemperatureSensorFault() {
		return temperatureSensorFault;
	}
	/**
	 * @param temperatureSensorFault the temperatureSensorFault to set
	 */
	public void setTemperatureSensorFault(Integer temperatureSensorFault) {
		this.temperatureSensorFault = temperatureSensorFault;
	}
	/**
	 * @return the innerTemperatureSensorFault
	 */
	public Integer getInnerTemperatureSensorFault() {
		return InnerTemperatureSensorFault;
	}
	/**
	 * @param innerTemperatureSensorFault the innerTemperatureSensorFault to set
	 */
	public void setInnerTemperatureSensorFault(Integer innerTemperatureSensorFault) {
		InnerTemperatureSensorFault = innerTemperatureSensorFault;
	}
	/**
	 * @return the magneticInterference
	 */
	public Integer getMagneticInterference() {
		return magneticInterference;
	}
	/**
	 * @param magneticInterference the magneticInterference to set
	 */
	public void setMagneticInterference(Integer magneticInterference) {
		this.magneticInterference = magneticInterference;
	}
	/**
	 * @return the leaking
	 */
	public Integer getLeaking() {
		return leaking;
	}
	/**
	 * @param leaking the leaking to set
	 */
	public void setLeaking(Integer leaking) {
		this.leaking = leaking;
	}
	/**
	 * @return the flowAbnormal
	 */
	public Integer getFlowAbnormal() {
		return flowAbnormal;
	}
	/**
	 * @param flowAbnormal the flowAbnormal to set
	 */
	public void setFlowAbnormal(Integer flowAbnormal) {
		this.flowAbnormal = flowAbnormal;
	}
	/**
	 * @return the nonSamplingCloseValve
	 */
	public Integer getNonSamplingCloseValve() {
		return nonSamplingCloseValve;
	}
	/**
	 * @param nonSamplingCloseValve the nonSamplingCloseValve to set
	 */
	public void setNonSamplingCloseValve(Integer nonSamplingCloseValve) {
		this.nonSamplingCloseValve = nonSamplingCloseValve;
	}
	/**
	 * @return the directReadingAbnormal
	 */
	public Integer getDirectReadingAbnormal() {
		return directReadingAbnormal;
	}
	/**
	 * @param directReadingAbnormal the directReadingAbnormal to set
	 */
	public void setDirectReadingAbnormal(Integer directReadingAbnormal) {
		this.directReadingAbnormal = directReadingAbnormal;
	}
	/**
	 * @return the overdraft
	 */
	public Integer getOverdraft() {
		return overdraft;
	}
	/**
	 * @param overdraft the overdraft to set
	 */
	public void setOverdraft(Integer overdraft) {
		this.overdraft = overdraft;
	}
	/**
	 * @return the insufficientBalance
	 */
	public Integer getInsufficientBalance() {
		return insufficientBalance;
	}
	/**
	 * @param insufficientBalance the insufficientBalance to set
	 */
	public void setInsufficientBalance(Integer insufficientBalance) {
		this.insufficientBalance = insufficientBalance;
	}
	
	
}
