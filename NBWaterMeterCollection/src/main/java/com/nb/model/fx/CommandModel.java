/**   
* @Title: ReportNormally.java 
* @Package com.nb.model.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午4:31:01 
* @version V1.0   
*/
package com.nb.model.fx;

import java.io.Serializable;

/**
 * @ClassName: CommandModel
 * @Description: 府星移动服务器->表端 json对应数据模型
 * @author dbr
 * @date 2019年5月24日 下午4:31:01
 * 
 */
public class CommandModel implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 661648522728828193L;
	/** AFN应用层功能码 */
	private int AFN;
	/** 传输方向，0：服务器->表端；1：表端->服务器 */
	private int DIR;

	/** 默认为3，尝试一次，变为2，再尝试一次，变为1（取值只能为1，2，3） */
	private int CNT;

	/** IMSI号 */
	private String IMSI;

	/** 预存水量 */
	private Double PreStoredWater;

	/** 水表编号 总共10位，YY年MM月，NNNNNN当月第几水表 */
	private Double WaterMeterNo;

	/** 水表口径 例如25口径，则为0X19 */
	private Double MeterDiameter;

	/** 表端当前时间 YYMMDDhhmmss */
	private String CurrentDateTime;

	/** 信号强度 0-31代表有信号，数值越大信号越强，99代表没有NBIOT网络信号 */
	private Double SignalStrength;

	/** 电池电压 单位：V，1位小数 */
	private Double BatteryVoltage;

	/** 设置状态 0：成功 1：失败 */
	private Double SettingResponseState;

	/** 干簧管采样倍率 */
	private Double DryReedPipeSampleRate;

	/** 阀门动作时间 单位：秒 例如0X08，则表示阀门动作时间为8S，如果阀门动作时间超过8S，则说明阀门异常 */
	private Double ValveOperatingTime;

	/** 服务器IP 例如10.10.120.199填写0X0A0A78C7 */
	private String ServerIp;

	/** 服务器端口 例如10086填写0X2766 */
	private Double ServerPort;

	/** 上报起始基准时间 格式为YYYYMMDDhhmmss，用于表端设置RTC时间 */
	private String ReportBaseTime;

	/** 上报间隔时间 2个字节 单位：小时 */
	private Double ReportIntervalHours;

	/**
	 * 单干簧管异常检测周期 1个字节 单位：天/次 例如3，表示：
	 * 单个干簧管每天吸合2次，连续3天，且3天内无另一干簧管的信号时，自动上报为“单干簧管异常第一次”，关闭阀门，此时用户可以通过按钮开启阀门，收费软件只记录不做处理。
	 * 如果表端出现过“单干簧管异常第一次”后，单个干簧管每天吸合2次，连续3天，且3天内无另一干簧管的信号时，自动上报为“单干簧管异常第二次”，关闭阀门，此时用户按按钮不能打开阀门，收费软件记录并通知用户
	 */
	private Double SingleDryReedPipeErrorCheckPeriod;

	/** EF 累计正向总水量 4字节 单位：m³，2位小数 */

	private Double TotalPositiveCumulateWater;

	/** EF 累计反向总水量 4字节 单位：m³，2位小数 */

	private Double TotalReverseCumulateWater;

	/** EF 上月正向累计水量 4字节 单位：m³，2位小数 */

	private Double LastMonthPositiveCumulateWater;

	/** EF 上月反向累计水量 4字节 单位：m³，2位小数 */
	private Double LastMonthReverseCumulateWater;

	/** EF 当月正向累计水量 4字节 单位：m³，2位小数 */
	private Double MonthPositiveCumulateWater;

	/** EF 当月反向累计水量 4字节 单位：m³，2位小数 */
	private Double MonthReverseCumulateWater;

	/** EF 大流量告警阀值 4 字节 单位：m³，2位小数 */
	private Double LargeFlowAlarmThreshold;

	/** EF 大流量持续监测时间 2 字节 单位：分钟 */
	private Double LargeFlowContinuousMonitorTime;

	/** EF 持续流量告警阀值 4字节 单位：m³，2位小数 */
	private Double ContinuousFlowAlarmThreshold;

	/** EF 持续流量持续监测时间 2字节 单位：分钟 */
	private Double ContinuousFlowContinuousMonitorTime;

	/** EF 小流量告警阀值 4字节 单位：m³，2位小数 */
	private Double LowFlowAlarmThreshold;

	/** EF 小流量持续监测时间 2字节 单位：分钟 */
	private Double LowFlowContinuousMonitorTime;

	/** EF 高压力报警阀值 2字节 单位：MP，2位小数 */
	private Double HighPressureAlarmThreshold;

	/** EF 低压力报警阀值 2字节 单位：MP，2位小数 */
	private Double LowPressureAlarmThreshold;

	/** 阀门操作 0：关阀 1：开阀 */
	private Double ValveOperate;

	/**
	 * @return the aFN
	 */
	public int getAFN() {
		return AFN;
	}

	/**
	 * @param aFN the aFN to set
	 */
	public void setAFN(int aFN) {
		AFN = aFN;
	}

	/**
	 * @return the dIR
	 */
	public int getDIR() {
		return DIR;
	}

	/**
	 * @param dIR the dIR to set
	 */
	public void setDIR(int dIR) {
		DIR = dIR;
	}

	/**
	 * @return the cNT
	 */
	public int getCNT() {
		return CNT;
	}

	/**
	 * @param cNT the cNT to set
	 */
	public void setCNT(int cNT) {
		CNT = cNT;
	}

	/**
	 * @return the iMSI
	 */
	public String getIMSI() {
		return IMSI;
	}

	/**
	 * @param iMSI the iMSI to set
	 */
	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	/**
	 * @return the preStoredWater
	 */
	public Double getPreStoredWater() {
		return PreStoredWater;
	}

	/**
	 * @param preStoredWater the preStoredWater to set
	 */
	public void setPreStoredWater(Double preStoredWater) {
		PreStoredWater = preStoredWater;
	}

	/**
	 * @return the waterMeterNo
	 */
	public Double getWaterMeterNo() {
		return WaterMeterNo;
	}

	/**
	 * @param waterMeterNo the waterMeterNo to set
	 */
	public void setWaterMeterNo(Double waterMeterNo) {
		WaterMeterNo = waterMeterNo;
	}

	/**
	 * @return the meterDiameter
	 */
	public Double getMeterDiameter() {
		return MeterDiameter;
	}

	/**
	 * @param meterDiameter the meterDiameter to set
	 */
	public void setMeterDiameter(Double meterDiameter) {
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
	 * @return the signalStrength
	 */
	public Double getSignalStrength() {
		return SignalStrength;
	}

	/**
	 * @param signalStrength the signalStrength to set
	 */
	public void setSignalStrength(Double signalStrength) {
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
	 * @return the settingResponseState
	 */
	public Double getSettingResponseState() {
		return SettingResponseState;
	}

	/**
	 * @param settingResponseState the settingResponseState to set
	 */
	public void setSettingResponseState(Double settingResponseState) {
		SettingResponseState = settingResponseState;
	}

	/**
	 * @return the dryReedPipeSampleRate
	 */
	public Double getDryReedPipeSampleRate() {
		return DryReedPipeSampleRate;
	}

	/**
	 * @param dryReedPipeSampleRate the dryReedPipeSampleRate to set
	 */
	public void setDryReedPipeSampleRate(Double dryReedPipeSampleRate) {
		DryReedPipeSampleRate = dryReedPipeSampleRate;
	}

	/**
	 * @return the valveOperatingTime
	 */
	public Double getValveOperatingTime() {
		return ValveOperatingTime;
	}

	/**
	 * @param valveOperatingTime the valveOperatingTime to set
	 */
	public void setValveOperatingTime(Double valveOperatingTime) {
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
	public Double getReportIntervalHours() {
		return ReportIntervalHours;
	}

	/**
	 * @param reportIntervalHours the reportIntervalHours to set
	 */
	public void setReportIntervalHours(Double reportIntervalHours) {
		ReportIntervalHours = reportIntervalHours;
	}

	/**
	 * @return the singleDryReedPipeErrorCheckPeriod
	 */
	public Double getSingleDryReedPipeErrorCheckPeriod() {
		return SingleDryReedPipeErrorCheckPeriod;
	}

	/**
	 * @param singleDryReedPipeErrorCheckPeriod the singleDryReedPipeErrorCheckPeriod to set
	 */
	public void setSingleDryReedPipeErrorCheckPeriod(Double singleDryReedPipeErrorCheckPeriod) {
		SingleDryReedPipeErrorCheckPeriod = singleDryReedPipeErrorCheckPeriod;
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
	 * @return the valveOperate
	 */
	public Double getValveOperate() {
		return ValveOperate;
	}

	/**
	 * @param valveOperate the valveOperate to set
	 */
	public void setValveOperate(Double valveOperate) {
		ValveOperate = valveOperate;
	}

	
}
