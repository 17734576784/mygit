/**   
* @Title: DeviceInfo.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:37:09 
* @version V1.0   
*/
package com.nb.model.jd;

import com.nb.model.BaseModel;

/** 
* @ClassName: DeviceInfo 
* @Description: 竞达DeviceInfo服务上报数据项 
* @author dbr
* @date 2019年4月10日 上午9:37:09 
*  
*/
public class DeviceInfo extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -2684602402948818246L;
	/** IMEI号 */
	private String imei;
	/** IMSI号 */
	private String imsi;
	/** 协议版本 */
	private Integer protocolVersion;
	/** 设备类型 1:小表，2：大表 */
	private Integer deviceType;
	/** 状态 1:已注册，0:未注册 */
	private Integer state;
	/** 水表表号 14位 */
	private String meterNo;
	/** 终端时钟 YYMMDDHHMMSS */
	private String meterTime;
	/** 终端运行时间 单位:天 */
	private Integer runDays;
	/** 终端软件版本 */
	private Integer softwareVersion;

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
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
	 * @return the protocolVersion
	 */
	public Integer getProtocolVersion() {
		return protocolVersion;
	}
	/**
	 * @param protocolVersion the protocolVersion to set
	 */
	public void setProtocolVersion(Integer protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	/**
	 * @return the deviceType
	 */
	public Integer getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the meterNo
	 */
	public String getMeterNo() {
		return meterNo;
	}
	/**
	 * @param meterNo the meterNo to set
	 */
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	/**
	 * @return the meterTime
	 */
	public String getMeterTime() {
		return meterTime;
	}
	/**
	 * @param meterTime the meterTime to set
	 */
	public void setMeterTime(String meterTime) {
		this.meterTime = meterTime;
	}
	/**
	 * @return the runDays
	 */
	public Integer getRunDays() {
		return runDays;
	}
	/**
	 * @param runDays the runDays to set
	 */
	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}
	/**
	 * @return the softwareVersion
	 */
	public Integer getSoftwareVersion() {
		return softwareVersion;
	}
	/**
	 * @param softwareVersion the softwareVersion to set
	 */
	public void setSoftwareVersion(Integer softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "DeviceInfo [imei=" + imei + ", imsi=" + imsi + ", protocolVersion=" + protocolVersion + ", deviceType="
				+ deviceType + ", state=" + state + ", meterNo=" + meterNo + ", meterTime=" + meterTime + ", runDays="
				+ runDays + ", softwareVersion=" + softwareVersion + ", evnetTime=]";
	}
	
	
}
