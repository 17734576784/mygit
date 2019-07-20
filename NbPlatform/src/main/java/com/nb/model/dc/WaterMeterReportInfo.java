/**   
* @Title: WaterMeterReportInfo.java 
* @Package com.nb.model.dc 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年6月3日 下午3:31:16 
* @version V1.0   
*/
package com.nb.model.dc;

import java.io.Serializable;
import java.util.Date;

import com.nb.utils.DateUtils;

/** 
* @ClassName: WaterMeterReportInfo 
* @Description: 道成水表上报数据
* @author dbr
* @date 2019年6月3日 下午3:31:16 
*  
*/
public class WaterMeterReportInfo implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 5949160343746725534L;
	private String meterAddr;
	private Double readingNumber;
	private Double voltage;
	private String imsi;
	private Integer valveState;
	private String imei;
	private String realTime;
	
	private String date; 
	private String time;
	
	/**
	 * @return the meterAddr
	 */
	public String getMeterAddr() {
		return meterAddr;
	}
	/**
	 * @param meterAddr the meterAddr to set
	 */
	public void setMeterAddr(String meterAddr) {
		this.meterAddr = meterAddr;
	}
	/**
	 * @return the readingNumber
	 */
	public Double getReadingNumber() {
		return readingNumber;
	}
	/**
	 * @param readingNumber the readingNumber to set
	 */
	public void setReadingNumber(Double readingNumber) {
		this.readingNumber = readingNumber;
	}
	/**
	 * @return the voltage
	 */
	public Double getVoltage() {
		return voltage;
	}
	/**
	 * @param voltage the voltage to set
	 */
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
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
	 * @return the valveState
	 */
	public Integer getValveState() {
		return valveState;
	}
	/**
	 * @param valveState the valveState to set
	 */
	public void setValveState(Integer valveState) {
		this.valveState = valveState;
	}
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
	 * @return the realTime
	 */
	public String getRealTime() {
		return realTime;
	}

	/**
	 * @param realTime the realTime to set
	 */
	public void setRealTime(String realTime) {
		Date date = new Date();
		if (realTime != null && !realTime.isEmpty()) {
			date = DateUtils.parseDate(realTime, DateUtils.UTC_PATTERN);
		}
		this.realTime = realTime;
		this.date = DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN);
		this.time = DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN);
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
}
