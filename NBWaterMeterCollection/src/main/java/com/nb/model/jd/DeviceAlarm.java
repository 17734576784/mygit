/**   
* @Title: DeviceAlarm.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:12:01 
* @version V1.0   
*/
package com.nb.model.jd;

import com.nb.model.BaseModel;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;

/**
 * @ClassName: DeviceAlarm
 * @Description: 竞达DeviceAlarm服务上报数据项 
 * @author dbr
 * @date 2019年4月10日 上午9:12:01
 * 
 */
public class DeviceAlarm extends BaseModel {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7632679080105896668L;
	/** 大流量报警 开始时间 */
	private String peakFlowStartTime;
	/** 最大流速 单位: L/h */
	private Integer peakFlow;
	/** 篡改告警 1：篡改，被如下数据被修改时产生此告警：序列号，生产日期，累计流量，0不报警 */
	private Integer tampered;
	/** 反流告警 1报警，0不报警 */
	private Integer reverseFlowAlarm;
	/** 磁干扰 1报警，0不报警 */
	private Integer magneticInterferenceAlarm;
	/**
	 * @Fields internalAlarm : 内部错误 水表厂家自行定义
	 */ 
	private Integer internalAlarm;
	/** 
	* @Fields disconnectAlarm :  远传模块分离告警 1报警，0不报警 
	*/ 
	private Integer disconnectAlarm;
	/** 
	* @Fields date : 事项上报日期
	*/ 
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the peakFlowStartTime
	 */
	public String getPeakFlowStartTime() {
		return peakFlowStartTime;
	}

	/**
	 * @param peakFlowStartTime
	 *            the peakFlowStartTime to set
	 */
	public void setPeakFlowStartTime(String peakFlowStartTime) {
		this.peakFlowStartTime = DateUtils.stampToDate(ConverterUtils.toLong(peakFlowStartTime));
	}

	/**
	 * @return the peakFlow
	 */
	public Integer getPeakFlow() {
		return peakFlow;
	}

	/**
	 * @param peakFlow
	 *            the peakFlow to set
	 */
	public void setPeakFlow(Integer peakFlow) {
		this.peakFlow = peakFlow;
	}

	/**
	 * @return the tampered
	 */
	public Integer getTampered() {
		return tampered;
	}

	/**
	 * @param tampered
	 *            the tampered to set
	 */
	public void setTampered(Integer tampered) {
		this.tampered = tampered;
	}

	/**
	 * @return the reverseFlowAlarm
	 */
	public Integer getReverseFlowAlarm() {
		return reverseFlowAlarm;
	}

	/**
	 * @param reverseFlowAlarm
	 *            the reverseFlowAlarm to set
	 */
	public void setReverseFlowAlarm(Integer reverseFlowAlarm) {
		this.reverseFlowAlarm = reverseFlowAlarm;
	}

	/**
	 * @return the magneticInterferenceAlarm
	 */
	public Integer getMagneticInterferenceAlarm() {
		return magneticInterferenceAlarm;
	}

	/**
	 * @param magneticInterferenceAlarm
	 *            the magneticInterferenceAlarm to set
	 */
	public void setMagneticInterferenceAlarm(Integer magneticInterferenceAlarm) {
		this.magneticInterferenceAlarm = magneticInterferenceAlarm;
	}

	/**
	 * @return the internalAlarm
	 */
	public Integer getInternalAlarm() {
		return internalAlarm;
	}

	/**
	 * @param internalAlarm
	 *            the internalAlarm to set
	 */
	public void setInternalAlarm(Integer internalAlarm) {
		this.internalAlarm = internalAlarm;
	}

	/**
	 * @return the disconnectAlarm
	 */
	public Integer getDisconnectAlarm() {
		return disconnectAlarm;
	}

	/**
	 * @param disconnectAlarm
	 *            the disconnectAlarm to set
	 */
	public void setDisconnectAlarm(Integer disconnectAlarm) {
		this.disconnectAlarm = disconnectAlarm;
	}

	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "DeviceAlarm [peakFlowStartTime=" + peakFlowStartTime + ", peakFlow=" + peakFlow + ", tampered="
				+ tampered + ", reverseFlowAlarm=" + reverseFlowAlarm + ", magneticInterferenceAlarm="
				+ magneticInterferenceAlarm + ", internalAlarm=" + internalAlarm + ", disconnectAlarm="
				+ disconnectAlarm + "]";
	}
	
}
