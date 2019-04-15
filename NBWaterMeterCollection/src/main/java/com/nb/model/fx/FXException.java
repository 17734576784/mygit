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
import java.util.Date;
import static com.nb.utils.ConverterUtils.toInt;
import com.nb.utils.DateUtils;

/** 
* @ClassName: FXException 
* @Description: 府星水表电信数据上报异常项 
* @author dbr
* @date 2019年4月15日 下午4:13:42 
*  
*/
public class FXException implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 6897052349813312149L;

	private String Version; // 固件版本
	private String IsKeyTriggerThisReport;// 是否是按键触发上报 0:自动定时上报 1:按键触发上报
	private String TotalCumulateWater; // 累计总水量
	private String MonthCumulateWater; // 当月累计水量
	private String WaterMeterNo;// 水表编号
	private String MeterDiameter;// 水表口径
	private String CurrentDateTime;// 表端当前时间
	private String ValveState;// 阀门状态
	private String BatteryVoltage;// 电池电压
	private String ErrorNo;// 异常代码
	private int date; // 上报日期
	private int time; // 上报时间
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return Version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		Version = version;
	}
	/**
	 * @return the isKeyTriggerThisReport
	 */
	public String getIsKeyTriggerThisReport() {
		return IsKeyTriggerThisReport;
	}
	/**
	 * @param isKeyTriggerThisReport the isKeyTriggerThisReport to set
	 */
	public void setIsKeyTriggerThisReport(String isKeyTriggerThisReport) {
		IsKeyTriggerThisReport = isKeyTriggerThisReport;
	}
	/**
	 * @return the totalCumulateWater
	 */
	public String getTotalCumulateWater() {
		return TotalCumulateWater;
	}
	/**
	 * @param totalCumulateWater the totalCumulateWater to set
	 */
	public void setTotalCumulateWater(String totalCumulateWater) {
		TotalCumulateWater = totalCumulateWater;
	}
	/**
	 * @return the monthCumulateWater
	 */
	public String getMonthCumulateWater() {
		return MonthCumulateWater;
	}
	/**
	 * @param monthCumulateWater the monthCumulateWater to set
	 */
	public void setMonthCumulateWater(String monthCumulateWater) {
		MonthCumulateWater = monthCumulateWater;
	}
	/**
	 * @return the waterMeterNo
	 */
	public String getWaterMeterNo() {
		return WaterMeterNo;
	}
	/**
	 * @param waterMeterNo the waterMeterNo to set
	 */
	public void setWaterMeterNo(String waterMeterNo) {
		WaterMeterNo = waterMeterNo;
	}
	/**
	 * @return the meterDiameter
	 */
	public String getMeterDiameter() {
		return MeterDiameter;
	}
	/**
	 * @param meterDiameter the meterDiameter to set
	 */
	public void setMeterDiameter(String meterDiameter) {
		MeterDiameter = meterDiameter;
	}
	/**
	 * @return the currentDateTime
	 */
	public String getCurrentDateTime() {
		return CurrentDateTime;
	}
	/**
	 * @param currentDateTime the currentDateTime to set
	 */
	public void setCurrentDateTime(String currentDateTime) {
		CurrentDateTime = currentDateTime;
		Date dateTime = DateUtils.parseTimesTampDate(currentDateTime);
		this.date = toInt(DateUtils.formatDateByFormat(dateTime, "yyyyMMdd"));
		this.time = toInt(DateUtils.formatDateByFormat(dateTime, "HHmmss"));
	}
	/**
	 * @return the valveState
	 */
	public String getValveState() {
		return ValveState;
	}
	/**
	 * @param valveState the valveState to set
	 */
	public void setValveState(String valveState) {
		ValveState = valveState;
	}
	/**
	 * @return the batteryVoltage
	 */
	public String getBatteryVoltage() {
		return BatteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(String batteryVoltage) {
		BatteryVoltage = batteryVoltage;
	}
	/**
	 * @return the errorNo
	 */
	public String getErrorNo() {
		return ErrorNo;
	}
	/**
	 * @param errorNo the errorNo to set
	 */
	public void setErrorNo(String errorNo) {
		ErrorNo = errorNo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
}
