/**   
* @Title: DeviceInfo.java 
* @Package com.nb.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月17日 下午4:11:07 
* @version V1.0   
*/
package com.nb.model;

import java.io.Serializable;

/** 
* @ClassName: DeviceInfo 
* @Description: 设备基本信息 
* @author dbr
* @date 2019年4月17日 下午4:11:07 
*  
*/
public class DeviceInfo implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -7282163824394330254L;

	private String imei;
	private String imsi;
	private String deviceId;
	private String appId;
	private String secret;
	private String manufacturerId;
	private String manufacturerName;
	private String deviceType;
	private String model;
	private String protocolType;
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
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @return the manufacturerId
	 */
	public String getManufacturerId() {
		return manufacturerId;
	}
	/**
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}
	/**
	 * @param manufacturerName the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the protocolType
	 */
	public String getProtocolType() {
		return protocolType;
	}
	/**
	 * @param protocolType the protocolType to set
	 */
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "DeviceInfo [imei=" + imei + ", imsi=" + imsi + ", deviceId=" + deviceId + ", appId=" + appId
				+ ", secret=" + secret + ", manufacturerId=" + manufacturerId + ", manufacturerName=" + manufacturerName
				+ ", deviceType=" + deviceType + ", model=" + model + ", protocolType=" + protocolType + "]";
	}

}
