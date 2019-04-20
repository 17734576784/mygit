/**   
* @Title: DeviceState.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:47:53 
* @version V1.0   
*/
package com.nb.model.jd;

import java.io.Serializable;

import com.nb.utils.CommFunc;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;

/** 
* @ClassName: DeviceState 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月10日 上午9:47:53 
*  
*/
public class DeviceState implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -4193798179585845821L;
	/** 数据时间 */
	private String readTime;
	/** 通讯状态 */
	private String comState;
	/** 磁干扰状态 */
	private String interfereState;
	/** 表具信号质量 0-10 */
	private Integer signalQuality;
	/** 功耗状态 0-6 */
	private Integer powerState;
	/** 连续上报失败次数 0-255 */
	private Integer continuityFailTimes;
	/** 上一日上报 0-255 */
	private Integer reportTimes;
	/** 上一日上报成功次数 0-255 */
	private Integer reportSuccessTimes;
	/** 事项上报时间 */
	private String evnetTime;

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
	 * @return the readTime
	 */
	public String getReadTime() {
		return readTime;
	}
	/**
	 * @param readTime the readTime to set
	 */
	public void setReadTime(String readTime) {

		this.readTime = DateUtils.stampToDate(ConverterUtils.toLong(readTime));
	}

	/**
	 * @return the comState
	 */
	public String getComState() {
		return comState;
	}
	/**
	 * @param comState the comState to set
	 */
	public void setComState(String comState) {
		this.comState = comState;
	}
	/**
	 * @return the interfereState
	 */
	public String getInterfereState() {
		return interfereState;
	}
	/**
	 * @param interfereState the interfereState to set
	 */
	public void setInterfereState(String interfereState) {
		this.interfereState = interfereState;
	}
	/**
	 * @return the signalQuality
	 */
	public Integer getSignalQuality() {
		return signalQuality;
	}
	/**
	 * @param signalQuality the signalQuality to set
	 */
	public void setSignalQuality(Integer signalQuality) {
		this.signalQuality = signalQuality;
	}
	/**
	 * @return the powerState
	 */
	public Integer getPowerState() {
		return powerState;
	}
	/**
	 * @param powerState the powerState to set
	 */
	public void setPowerState(Integer powerState) {
		this.powerState = powerState;
	}
	/**
	 * @return the continuityFailTimes
	 */
	public Integer getContinuityFailTimes() {
		return continuityFailTimes;
	}
	/**
	 * @param continuityFailTimes the continuityFailTimes to set
	 */
	public void setContinuityFailTimes(Integer continuityFailTimes) {
		this.continuityFailTimes = continuityFailTimes;
	}
	/**
	 * @return the reportTimes
	 */
	public Integer getReportTimes() {
		return reportTimes;
	}
	/**
	 * @param reportTimes the reportTimes to set
	 */
	public void setReportTimes(Integer reportTimes) {
		this.reportTimes = reportTimes;
	}
	/**
	 * @return the reportSuccessTimes
	 */
	public Integer getReportSuccessTimes() {
		return reportSuccessTimes;
	}
	/**
	 * @param reportSuccessTimes the reportSuccessTimes to set
	 */
	public void setReportSuccessTimes(Integer reportSuccessTimes) {
		this.reportSuccessTimes = reportSuccessTimes;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "DeviceState [readTime=" + readTime + ", comState=" + comState + ", interfereState=" + interfereState
				+ ", signalQuality=" + signalQuality + ", powerState=" + powerState + ", continuityFailTimes="
				+ continuityFailTimes + ", reportTimes=" + reportTimes + ", reportSuccessTimes=" + reportSuccessTimes
				+ ", evnetTime=" + evnetTime + "]";
	}
	
}
