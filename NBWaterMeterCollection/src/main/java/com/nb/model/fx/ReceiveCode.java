/**   
* @Title: ReceiveCode.java 
* @Package com.nb.model.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午4:43:32 
* @version V1.0   
*/
package com.nb.model.fx;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: ReceiveCode 
* @Description: 府星移动表端->服务器对象模型 json对应数据模型 
* @author dbr
* @date 2019年5月24日 下午4:43:32 
*  
*/
public class ReceiveCode implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -4512797061341179818L;

	/**
	 * 是否是按键触发上报 0x00:自动定时上报 0x01:按键触发上报
	 */
	private String IsKeyTriggerThisReport;

	/**
	 * 水表编号 5个字节 水表编号格式YYMMNNNNNN，总共10位，YY年MM月，NNNNNN当月第几只NB水表
	 */
	private String WaterMeterNo;

	/**
	 * 水表口径 1个字节 EF 2个字节 例如25口径，则为0X19
	 */
	private String MeterDiameter;

	/**
	 * 表端当前时间  6个字节  EF 7个字节  年、月、日、时、分、秒各1字节，格式为YYMMDDhhmmss
	 */
	private String CurrentDateTime;

	/**
	 * 阀门状态  1字节 HEX  1：正在开阀  2：阀门开到位  3：正在关阀  4：阀门关到位
	 */
	private String ValveState;

	/**
	 * 信号强度  1字节  HEX  AT+CSQ 查询信号强度，返回的第一个数值代表信号强度， 
	 * 0-31代表有信号，数值越大信号越强，99代表没有NBIOT网络信号
	 */
	private String SignalStrength;

	/**
	 * 电池电压  1字节  HEX  单位：V，1位小数  例如0X24表示3.6V
	 */
	private Double BatteryVoltage;

	/**
	 * 异常代码  4字节 32位 每个二进制位表示一个异常  说明：  （1） 总共可以同时存放32个异常；  （2）
	 * 例如同时产生了阀门异常和单干簧管异常第一次，那么上报的数据为0000000000000011=0x0003
	 */
	private String ErrorNo;

	/**
	 * 设置状态  1字节  HEX  0：成功 1：失败
	 */
	private String SettingResponseState;

	/**
	 * 上报起始基准时间  7个字节  年2个字节、月、日、时、分、秒各1字节，格式为YYYYMMDDhhmmss，用于表端设置RTC时间
	 */
	private String ReportBaseTime;

	/**
	 * 上报间隔时间  2个字节  单位：小时
	 */
	private String ReportIntervalHours;

	/**
	 * 单干簧管异常检测周期  1个字节  单位：天/次  例如3，表示： 
	 * 单个干簧管每天吸合2次，连续3天，且3天内无另一干簧管的信号时，自动上报为“单干簧管异常第一次”，关闭阀门，此时用户可以通过按钮开启阀门，收费软件只记录不做处理。
	 * 
	 * 如果表端出现过“单干簧管异常第一次”后，单个干簧管每天吸合2次，连续3天，且3天内无另一干簧管的信号时，自动上报为“单干簧管异常第二次”，关闭阀门，此时用户按按钮不能打开阀门，收费软件记录并通知用户
	 */
	private String SingleDryReedPipeErrorCheckPeriod;

	/**
	 * 干簧管采样倍率  1个字节  单位：m³，保留1位小数  例如0X01，表示干簧管A,B闭合一次所走过的水量为0.1 m³
	 */
	private String DryReedPipeSampleRate;

	/**
	 * 阀门动作时间  1个字节  单位：秒  例如0X08，则表示阀门动作时间为8S，如果阀门动作时间超过8S，则说明阀门异常
	 */
	private String ValveOperatingTime;

	/**
	 * 服务器IP  4个字节  例如10.10.120.199填写0X0A0A78C7
	 */
	private String ServerIp;

	/**
	 * 服务器端口  2个字节  例如10086填写0X2766
	 */
	private Double ServerPort;

	/**
	 * EF 累计正向总水量  4字节  单位：m³，两位小数  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double TotalPositiveCumulateWater;

	/**
	 * EF 累计反向总水量  4字节  单位：m³，两位小数  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double TotalReverseCumulateWater;

	/**
	 * EF 上月正向累计水量  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double LastMonthPositiveCumulateWater;

	/**
	 * EF 上月反向累计水量  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double LastMonthReverseCumulateWater;

	/**
	 * EF 当月正向累计水量  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double MonthPositiveCumulateWater;

	/**
	 * EF 当月反向累计水量  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double MonthReverseCumulateWater;

	/**
	 * EF 大流量告警阀值  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double LargeFlowAlarmThreshold;

	/**
	 * EF 持续流量持续监测时间  2字节  单位：分钟  例如10086填写0X2766
	 */
	private Double ContinuousFlowContinuousMonitorTime;

	/**
	 * EF 大流量持续监测时间  2字节 单位：分钟
	 */
	private Double LargeFlowContinuousMonitorTime;

	/**
	 * EF 持续流量告警阀值  4字节 单位：m³，2位小数  例如0X00000064表示1.00 m³
	 */
	private Double ContinuousFlowAlarmThreshold;

	/**
	 * EF 持续流量持续监测时间  2字节  单位：分钟
	 */
	private Double LowFlowContinuousMonitorTime;
	
	/**
	 * EF 小流量告警阀值  4字节  单位：m³，两位小数  例如0X00000064表示1.00 m³
	 */
	private Double LowFlowAlarmThreshold;

	/**
	 * EF 高压力报警阀值  2字节  单位：MP，2位小数  例如0X0064表示1.00 MP
	 */
	private Double HighPressureAlarmThreshold;

	/**
	 * EF 低压力报警阀值  2字节  单位：MP，2位小数
	 */
	private Double LowPressureAlarmThreshold;

	/**
	 * EF 当前水表压力  2字节  单位：MP，2位小数
	 */
	private Double CurrentWaterMeterPressure;

	/**
	 * EF 前1天~15天日结正向水量  4x15 字节  单位：m³，2位小数 // 例如0X00000064表示1.00 m³
	 */
	private List<Double> FifteenDaysPositiveWaterList;

	/**
	 * 前1天~15天日结反向水量  4x15 字节  单位：m³，2位小数
	 */

	private List<Double> FifteenDaysReverseWaterList;
	/**
	 * 前一天每小时正向水量  4x24 字节  单位：m³，2位小数
	 */
	private List<Double> YesterdayPerHourPositiveWaterList;
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
	 * @return the signalStrength
	 */
	public String getSignalStrength() {
		return SignalStrength;
	}
	/**
	 * @param signalStrength the signalStrength to set
	 */
	public void setSignalStrength(String signalStrength) {
		SignalStrength = signalStrength;
	}
	/**
	 * @return the batteryVoltage
	 */
	public Double getBatteryVoltage() {
		return BatteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(Double batteryVoltage) {
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
	 * @return the settingResponseState
	 */
	public String getSettingResponseState() {
		return SettingResponseState;
	}
	/**
	 * @param settingResponseState the settingResponseState to set
	 */
	public void setSettingResponseState(String settingResponseState) {
		SettingResponseState = settingResponseState;
	}
	/**
	 * @return the reportBaseTime
	 */
	public String getReportBaseTime() {
		return ReportBaseTime;
	}
	/**
	 * @param reportBaseTime the reportBaseTime to set
	 */
	public void setReportBaseTime(String reportBaseTime) {
		ReportBaseTime = reportBaseTime;
	}
	/**
	 * @return the reportIntervalHours
	 */
	public String getReportIntervalHours() {
		return ReportIntervalHours;
	}
	/**
	 * @param reportIntervalHours the reportIntervalHours to set
	 */
	public void setReportIntervalHours(String reportIntervalHours) {
		ReportIntervalHours = reportIntervalHours;
	}
	/**
	 * @return the singleDryReedPipeErrorCheckPeriod
	 */
	public String getSingleDryReedPipeErrorCheckPeriod() {
		return SingleDryReedPipeErrorCheckPeriod;
	}
	/**
	 * @param singleDryReedPipeErrorCheckPeriod the singleDryReedPipeErrorCheckPeriod to set
	 */
	public void setSingleDryReedPipeErrorCheckPeriod(String singleDryReedPipeErrorCheckPeriod) {
		SingleDryReedPipeErrorCheckPeriod = singleDryReedPipeErrorCheckPeriod;
	}
	/**
	 * @return the dryReedPipeSampleRate
	 */
	public String getDryReedPipeSampleRate() {
		return DryReedPipeSampleRate;
	}
	/**
	 * @param dryReedPipeSampleRate the dryReedPipeSampleRate to set
	 */
	public void setDryReedPipeSampleRate(String dryReedPipeSampleRate) {
		DryReedPipeSampleRate = dryReedPipeSampleRate;
	}
	/**
	 * @return the valveOperatingTime
	 */
	public String getValveOperatingTime() {
		return ValveOperatingTime;
	}
	/**
	 * @param valveOperatingTime the valveOperatingTime to set
	 */
	public void setValveOperatingTime(String valveOperatingTime) {
		ValveOperatingTime = valveOperatingTime;
	}
	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return ServerIp;
	}
	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		ServerIp = serverIp;
	}
	/**
	 * @return the serverPort
	 */
	public Double getServerPort() {
		return ServerPort;
	}
	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(Double serverPort) {
		ServerPort = serverPort;
	}
	/**
	 * @return the totalPositiveCumulateWater
	 */
	public Double getTotalPositiveCumulateWater() {
		return TotalPositiveCumulateWater;
	}
	/**
	 * @param totalPositiveCumulateWater the totalPositiveCumulateWater to set
	 */
	public void setTotalPositiveCumulateWater(Double totalPositiveCumulateWater) {
		TotalPositiveCumulateWater = totalPositiveCumulateWater;
	}
	/**
	 * @return the totalReverseCumulateWater
	 */
	public Double getTotalReverseCumulateWater() {
		return TotalReverseCumulateWater;
	}
	/**
	 * @param totalReverseCumulateWater the totalReverseCumulateWater to set
	 */
	public void setTotalReverseCumulateWater(Double totalReverseCumulateWater) {
		TotalReverseCumulateWater = totalReverseCumulateWater;
	}
	/**
	 * @return the lastMonthPositiveCumulateWater
	 */
	public Double getLastMonthPositiveCumulateWater() {
		return LastMonthPositiveCumulateWater;
	}
	/**
	 * @param lastMonthPositiveCumulateWater the lastMonthPositiveCumulateWater to set
	 */
	public void setLastMonthPositiveCumulateWater(Double lastMonthPositiveCumulateWater) {
		LastMonthPositiveCumulateWater = lastMonthPositiveCumulateWater;
	}
	/**
	 * @return the lastMonthReverseCumulateWater
	 */
	public Double getLastMonthReverseCumulateWater() {
		return LastMonthReverseCumulateWater;
	}
	/**
	 * @param lastMonthReverseCumulateWater the lastMonthReverseCumulateWater to set
	 */
	public void setLastMonthReverseCumulateWater(Double lastMonthReverseCumulateWater) {
		LastMonthReverseCumulateWater = lastMonthReverseCumulateWater;
	}
	/**
	 * @return the monthPositiveCumulateWater
	 */
	public Double getMonthPositiveCumulateWater() {
		return MonthPositiveCumulateWater;
	}
	/**
	 * @param monthPositiveCumulateWater the monthPositiveCumulateWater to set
	 */
	public void setMonthPositiveCumulateWater(Double monthPositiveCumulateWater) {
		MonthPositiveCumulateWater = monthPositiveCumulateWater;
	}
	/**
	 * @return the monthReverseCumulateWater
	 */
	public Double getMonthReverseCumulateWater() {
		return MonthReverseCumulateWater;
	}
	/**
	 * @param monthReverseCumulateWater the monthReverseCumulateWater to set
	 */
	public void setMonthReverseCumulateWater(Double monthReverseCumulateWater) {
		MonthReverseCumulateWater = monthReverseCumulateWater;
	}
	/**
	 * @return the largeFlowAlarmThreshold
	 */
	public Double getLargeFlowAlarmThreshold() {
		return LargeFlowAlarmThreshold;
	}
	/**
	 * @param largeFlowAlarmThreshold the largeFlowAlarmThreshold to set
	 */
	public void setLargeFlowAlarmThreshold(Double largeFlowAlarmThreshold) {
		LargeFlowAlarmThreshold = largeFlowAlarmThreshold;
	}
	/**
	 * @return the continuousFlowContinuousMonitorTime
	 */
	public Double getContinuousFlowContinuousMonitorTime() {
		return ContinuousFlowContinuousMonitorTime;
	}
	/**
	 * @param continuousFlowContinuousMonitorTime the continuousFlowContinuousMonitorTime to set
	 */
	public void setContinuousFlowContinuousMonitorTime(Double continuousFlowContinuousMonitorTime) {
		ContinuousFlowContinuousMonitorTime = continuousFlowContinuousMonitorTime;
	}
	/**
	 * @return the largeFlowContinuousMonitorTime
	 */
	public Double getLargeFlowContinuousMonitorTime() {
		return LargeFlowContinuousMonitorTime;
	}
	/**
	 * @param largeFlowContinuousMonitorTime the largeFlowContinuousMonitorTime to set
	 */
	public void setLargeFlowContinuousMonitorTime(Double largeFlowContinuousMonitorTime) {
		LargeFlowContinuousMonitorTime = largeFlowContinuousMonitorTime;
	}
	/**
	 * @return the continuousFlowAlarmThreshold
	 */
	public Double getContinuousFlowAlarmThreshold() {
		return ContinuousFlowAlarmThreshold;
	}
	/**
	 * @param continuousFlowAlarmThreshold the continuousFlowAlarmThreshold to set
	 */
	public void setContinuousFlowAlarmThreshold(Double continuousFlowAlarmThreshold) {
		ContinuousFlowAlarmThreshold = continuousFlowAlarmThreshold;
	}
	/**
	 * @return the lowFlowContinuousMonitorTime
	 */
	public Double getLowFlowContinuousMonitorTime() {
		return LowFlowContinuousMonitorTime;
	}
	/**
	 * @param lowFlowContinuousMonitorTime the lowFlowContinuousMonitorTime to set
	 */
	public void setLowFlowContinuousMonitorTime(Double lowFlowContinuousMonitorTime) {
		LowFlowContinuousMonitorTime = lowFlowContinuousMonitorTime;
	}
	/**
	 * @return the lowFlowAlarmThreshold
	 */
	public Double getLowFlowAlarmThreshold() {
		return LowFlowAlarmThreshold;
	}
	/**
	 * @param lowFlowAlarmThreshold the lowFlowAlarmThreshold to set
	 */
	public void setLowFlowAlarmThreshold(Double lowFlowAlarmThreshold) {
		LowFlowAlarmThreshold = lowFlowAlarmThreshold;
	}
	/**
	 * @return the highPressureAlarmThreshold
	 */
	public Double getHighPressureAlarmThreshold() {
		return HighPressureAlarmThreshold;
	}
	/**
	 * @param highPressureAlarmThreshold the highPressureAlarmThreshold to set
	 */
	public void setHighPressureAlarmThreshold(Double highPressureAlarmThreshold) {
		HighPressureAlarmThreshold = highPressureAlarmThreshold;
	}
	/**
	 * @return the lowPressureAlarmThreshold
	 */
	public Double getLowPressureAlarmThreshold() {
		return LowPressureAlarmThreshold;
	}
	/**
	 * @param lowPressureAlarmThreshold the lowPressureAlarmThreshold to set
	 */
	public void setLowPressureAlarmThreshold(Double lowPressureAlarmThreshold) {
		LowPressureAlarmThreshold = lowPressureAlarmThreshold;
	}
	/**
	 * @return the currentWaterMeterPressure
	 */
	public Double getCurrentWaterMeterPressure() {
		return CurrentWaterMeterPressure;
	}
	/**
	 * @param currentWaterMeterPressure the currentWaterMeterPressure to set
	 */
	public void setCurrentWaterMeterPressure(Double currentWaterMeterPressure) {
		CurrentWaterMeterPressure = currentWaterMeterPressure;
	}
	/**
	 * @return the fifteenDaysPositiveWaterList
	 */
	public List<Double> getFifteenDaysPositiveWaterList() {
		return FifteenDaysPositiveWaterList;
	}
	/**
	 * @param fifteenDaysPositiveWaterList the fifteenDaysPositiveWaterList to set
	 */
	public void setFifteenDaysPositiveWaterList(List<Double> fifteenDaysPositiveWaterList) {
		FifteenDaysPositiveWaterList = fifteenDaysPositiveWaterList;
	}
	/**
	 * @return the fifteenDaysReverseWaterList
	 */
	public List<Double> getFifteenDaysReverseWaterList() {
		return FifteenDaysReverseWaterList;
	}
	/**
	 * @param fifteenDaysReverseWaterList the fifteenDaysReverseWaterList to set
	 */
	public void setFifteenDaysReverseWaterList(List<Double> fifteenDaysReverseWaterList) {
		FifteenDaysReverseWaterList = fifteenDaysReverseWaterList;
	}
	/**
	 * @return the yesterdayPerHourPositiveWaterList
	 */
	public List<Double> getYesterdayPerHourPositiveWaterList() {
		return YesterdayPerHourPositiveWaterList;
	}
	/**
	 * @param yesterdayPerHourPositiveWaterList the yesterdayPerHourPositiveWaterList to set
	 */
	public void setYesterdayPerHourPositiveWaterList(List<Double> yesterdayPerHourPositiveWaterList) {
		YesterdayPerHourPositiveWaterList = yesterdayPerHourPositiveWaterList;
	}

	
}
