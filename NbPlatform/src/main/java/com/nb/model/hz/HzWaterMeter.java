/**   
* @Title: HzWaterMeter.java 
* @Package com.nb.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月31日 上午9:19:39 
* @version V1.0   
*/
package com.nb.model.hz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.nb.utils.DateUtils;

/** 
* @ClassName: HzWaterMeter 
* @Description: 汇中水表数据类 
* @author dbr
* @date 2019年5月31日 上午9:19:39 
*  
*/
public class HzWaterMeter implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 4854962294034872635L;
	/** 数据的采集时间，格式为yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z */
	private String timeOfReading;
	/** 保留 int */
	private Integer internalTemperature;
	/** 日用水时间 int */
	private Integer dailyUseWaterTime;
	/** 累积流量 decimal */
	private Double cumulativeFlow;
	/** 保留 decimal */
	private Double positiveCumulativeFlow;
	/** 保留 decimal */
	private Double negativeCumulativeFlow;
	/** 日结累计流量 decimal */
	private Double dailyFlow;
	/** 保留 decimal */
	private Double dailyReverseFlow;
	/** 日最高瞬时流量 decimal */
	private Double peakFlowRate;
	/** 日最高瞬时流量时间戳,格式为yyyyMMdd'T'HHmmss'Z' */
	private String peakFlowRateTime;
	/** 日最低瞬时流量 decimal */
	private Double lowestFlowRate;
	/** 日最低瞬时流量时间戳,格式为yyyyMMdd'T'HHmmss'Z' */
	private String lowestFlowRateTime;
	/** 保留 decimal */
	private Double peakReverseFlowRate;
	/** 保留 string */
	private String peakReverseFlowRateTime;
	/** 保留 decimal */
	private Double lowestReverseFlowRate;
	/** 保留 string */
	private String lowestReverseFlowRateTime;
	/** 保留 string */
	private String rawData;
	/** 间隔水耗记录 list */
	private List<Double> intervalFlow;
	/** 水耗间隔 int */
	private Integer flowInterval;
	/** 保留 list */
	private List<Double> reverseIntervalFlow;
	/** 保留 int */
	private Integer reverseFlowIntervral;
	/** 水温，格式为[1,2,3] list */
	private List<Double> temperature;
	/** 温度间隔 int */
	private Integer temperatureInterval;
	/** 保留 list */
	private List<Double> pressure;
	/** 保留 int */
	private Integer pressInteveral;
	
	private String date; 
	private String time;
	 
	/**
	 * @return the timeOfReading
	 */
	public String getTimeOfReading() {
		return timeOfReading;
	}
	/**
	 * @param timeOfReading the timeOfReading to set
	 */
	public void setTimeOfReading(String timeOfReading) {
		Date date = DateUtils.parseDate(timeOfReading, DateUtils.UTC_PATTERN);
		this.timeOfReading = DateUtils.formatDateByFormat(date, "yyyyMMdd HHmmss");
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
	/**
	 * @return the internalTemperature
	 */
	public Integer getInternalTemperature() {
		return internalTemperature;
	}
	/**
	 * @param internalTemperature the internalTemperature to set
	 */
	public void setInternalTemperature(Integer internalTemperature) {
		this.internalTemperature = internalTemperature;
	}
	/**
	 * @return the dailyUseWaterTime
	 */
	public Integer getDailyUseWaterTime() {
		return dailyUseWaterTime;
	}
	/**
	 * @param dailyUseWaterTime the dailyUseWaterTime to set
	 */
	public void setDailyUseWaterTime(Integer dailyUseWaterTime) {
		this.dailyUseWaterTime = dailyUseWaterTime;
	}
	/**
	 * @return the cumulativeFlow
	 */
	public Double getCumulativeFlow() {
		return cumulativeFlow;
	}
	/**
	 * @param cumulativeFlow the cumulativeFlow to set
	 */
	public void setCumulativeFlow(Double cumulativeFlow) {
		this.cumulativeFlow = cumulativeFlow;
	}
	/**
	 * @return the positiveCumulativeFlow
	 */
	public Double getPositiveCumulativeFlow() {
		return positiveCumulativeFlow;
	}
	/**
	 * @param positiveCumulativeFlow the positiveCumulativeFlow to set
	 */
	public void setPositiveCumulativeFlow(Double positiveCumulativeFlow) {
		this.positiveCumulativeFlow = positiveCumulativeFlow;
	}
	/**
	 * @return the negativeCumulativeFlow
	 */
	public Double getNegativeCumulativeFlow() {
		return negativeCumulativeFlow;
	}
	/**
	 * @param negativeCumulativeFlow the negativeCumulativeFlow to set
	 */
	public void setNegativeCumulativeFlow(Double negativeCumulativeFlow) {
		this.negativeCumulativeFlow = negativeCumulativeFlow;
	}
	/**
	 * @return the dailyFlow
	 */
	public Double getDailyFlow() {
		return dailyFlow;
	}
	/**
	 * @param dailyFlow the dailyFlow to set
	 */
	public void setDailyFlow(Double dailyFlow) {
		this.dailyFlow = dailyFlow;
	}
	/**
	 * @return the dailyReverseFlow
	 */
	public Double getDailyReverseFlow() {
		return dailyReverseFlow;
	}
	/**
	 * @param dailyReverseFlow the dailyReverseFlow to set
	 */
	public void setDailyReverseFlow(Double dailyReverseFlow) {
		this.dailyReverseFlow = dailyReverseFlow;
	}
	/**
	 * @return the peakFlowRate
	 */
	public Double getPeakFlowRate() {
		return peakFlowRate;
	}
	/**
	 * @param peakFlowRate the peakFlowRate to set
	 */
	public void setPeakFlowRate(Double peakFlowRate) {
		this.peakFlowRate = peakFlowRate;
	}
	/**
	 * @return the peakFlowRateTime
	 */
	public String getPeakFlowRateTime() {
		return peakFlowRateTime;
	}
	/**
	 * @param peakFlowRateTime the peakFlowRateTime to set
	 */
	public void setPeakFlowRateTime(String peakFlowRateTime) {
		Date date = DateUtils.parseDate(peakFlowRateTime, DateUtils.UTC_PATTERN);
		this.peakFlowRateTime = DateUtils.formatDateByFormat(date, "yyyyMMdd HHmmss");
	}
	/**
	 * @return the lowestFlowRate
	 */
	public Double getLowestFlowRate() {
		return lowestFlowRate;
	}
	/**
	 * @param lowestFlowRate the lowestFlowRate to set
	 */
	public void setLowestFlowRate(Double lowestFlowRate) {
		this.lowestFlowRate = lowestFlowRate;
	}
	/**
	 * @return the lowestFlowRateTime
	 */
	public String getLowestFlowRateTime() {
		return lowestFlowRateTime;
	}
	/**
	 * @param lowestFlowRateTime the lowestFlowRateTime to set
	 */
	public void setLowestFlowRateTime(String lowestFlowRateTime) {
		Date date = DateUtils.parseDate(lowestFlowRateTime, DateUtils.UTC_PATTERN);
		this.lowestFlowRateTime = DateUtils.formatDateByFormat(date, "yyyyMMdd HHmmss");
	}
	/**
	 * @return the peakReverseFlowRate
	 */
	public Double getPeakReverseFlowRate() {
		return peakReverseFlowRate;
	}
	/**
	 * @param peakReverseFlowRate the peakReverseFlowRate to set
	 */
	public void setPeakReverseFlowRate(Double peakReverseFlowRate) {
		this.peakReverseFlowRate = peakReverseFlowRate;
	}
	/**
	 * @return the peakReverseFlowRateTime
	 */
	public String getPeakReverseFlowRateTime() {
		return peakReverseFlowRateTime;
	}
	/**
	 * @param peakReverseFlowRateTime the peakReverseFlowRateTime to set
	 */
	public void setPeakReverseFlowRateTime(String peakReverseFlowRateTime) {
		this.peakReverseFlowRateTime = peakReverseFlowRateTime;
	}
	/**
	 * @return the lowestReverseFlowRate
	 */
	public Double getLowestReverseFlowRate() {
		return lowestReverseFlowRate;
	}
	/**
	 * @param lowestReverseFlowRate the lowestReverseFlowRate to set
	 */
	public void setLowestReverseFlowRate(Double lowestReverseFlowRate) {
		this.lowestReverseFlowRate = lowestReverseFlowRate;
	}
	/**
	 * @return the lowestReverseFlowRateTime
	 */
	public String getLowestReverseFlowRateTime() {
		return lowestReverseFlowRateTime;
	}
	/**
	 * @param lowestReverseFlowRateTime the lowestReverseFlowRateTime to set
	 */
	public void setLowestReverseFlowRateTime(String lowestReverseFlowRateTime) {
		this.lowestReverseFlowRateTime = lowestReverseFlowRateTime;
	}
	/**
	 * @return the rawData
	 */
	public String getRawData() {
		return rawData;
	}
	/**
	 * @param rawData the rawData to set
	 */
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	/**
	 * @return the intervalFlow
	 */
	public List<Double> getIntervalFlow() {
		return intervalFlow;
	}
	/**
	 * @param intervalFlow the intervalFlow to set
	 */
	public void setIntervalFlow(List<Double> intervalFlow) {
		this.intervalFlow = intervalFlow;
	}
	/**
	 * @return the flowInterval
	 */
	public Integer getFlowInterval() {
		return flowInterval;
	}
	/**
	 * @param flowInterval the flowInterval to set
	 */
	public void setFlowInterval(Integer flowInterval) {
		this.flowInterval = flowInterval;
	}
	/**
	 * @return the reverseIntervalFlow
	 */
	public List<Double> getReverseIntervalFlow() {
		return reverseIntervalFlow;
	}
	/**
	 * @param reverseIntervalFlow the reverseIntervalFlow to set
	 */
	public void setReverseIntervalFlow(List<Double> reverseIntervalFlow) {
		this.reverseIntervalFlow = reverseIntervalFlow;
	}
	/**
	 * @return the reverseFlowIntervral
	 */
	public Integer getReverseFlowIntervral() {
		return reverseFlowIntervral;
	}
	/**
	 * @param reverseFlowIntervral the reverseFlowIntervral to set
	 */
	public void setReverseFlowIntervral(Integer reverseFlowIntervral) {
		this.reverseFlowIntervral = reverseFlowIntervral;
	}
	/**
	 * @return the temperature
	 */
	public List<Double> getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(List<Double> temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the temperatureInterval
	 */
	public Integer getTemperatureInterval() {
		return temperatureInterval;
	}
	/**
	 * @param temperatureInterval the temperatureInterval to set
	 */
	public void setTemperatureInterval(Integer temperatureInterval) {
		this.temperatureInterval = temperatureInterval;
	}
	/**
	 * @return the pressure
	 */
	public List<Double> getPressure() {
		return pressure;
	}
	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(List<Double> pressure) {
		this.pressure = pressure;
	}
	/**
	 * @return the pressInteveral
	 */
	public Integer getPressInteveral() {
		return pressInteveral;
	}
	/**
	 * @param pressInteveral the pressInteveral to set
	 */
	public void setPressInteveral(Integer pressInteveral) {
		this.pressInteveral = pressInteveral;
	}

}
