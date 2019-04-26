/**   
* @Title: WaterMeter.java 
* @Package com.nb.model.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月26日 上午9:15:46 
* @version V1.0   
*/
package com.nb.model.xt;

import static com.nb.utils.ConverterUtils.toInt;

import com.nb.model.BaseModel;

/**
 * @ClassName: XtWaterMeter
 * @Description: 新天科技WaterMeter服务上报数据项
 * @author dbr
 * @date 2019年4月26日 上午9:15:46
 * 
 */
public class XtWaterMeter extends BaseModel {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1753212176395842175L;
	/** 数据的采集时间 */
	private String timeOfReading;
	/** 日结累计正流量 */
	private String dailyFlow;
	/** 日结累计逆流值 */
	private String dailyReverseFlow;
	/** 日最高流速 */
	private String peakFlowRate;
	/** 日最高流速时间 */
	private String peakFlowRateTime;
	/** 间隔流量起始时间 */
	private String intervalFlowStartingTime;
	/** list<int> 间隔流量(30 分钟) */
	private Object intervalFlow;
	/** list<int> 瞬时流量(30 分钟) */
	private Object instantFlow;
	/** 信号质量 */
	private Integer csq;
	/** 信噪比 */
	private String snr;
	/** 覆盖等级 */
	private Integer cc;
	/** 密集采集(5 分钟)起始时间 */
	private String timeOfStarting;
	/** list<int> 密集采集(5 分钟)正流量 */
	private Object highRateFlow;
	/** list<int> 密集采集(5 分钟)逆流量 */
	private Object highRateReverseFlow;
	/** 日冻结（年月日） */
	private String uploadFreezeDate;
	/** list<int> 当日 48 个点累计增量 */
	// private Object dailyFlow;
	
	/** 事项上报日期 */
	private int readDate; 
	
	/** 事项上报时间 */
	private int readTime; 
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	/**
	 * @return the readDate
	 */
	public int getReadDate() {
		return readDate;
	}

	/**
	 * @return the readTime
	 */
	public int getReadTime() {
		return readTime;
	}

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
		this.timeOfReading = timeOfReading;
		this.readDate = toInt(timeOfReading.substring(0, 8));
		this.readTime = toInt(timeOfReading.substring(9, 15)) + 80000;
	}
	
	/**
	 * @return the dailyFlow
	 */
	public String getDailyFlow() {
		return dailyFlow;
	}
	/**
	 * @param dailyFlow the dailyFlow to set
	 */
	public void setDailyFlow(String dailyFlow) {
		this.dailyFlow = dailyFlow;
	}
	/**
	 * @return the dailyReverseFlow
	 */
	public String getDailyReverseFlow() {
		return dailyReverseFlow;
	}
	/**
	 * @param dailyReverseFlow the dailyReverseFlow to set
	 */
	public void setDailyReverseFlow(String dailyReverseFlow) {
		this.dailyReverseFlow = dailyReverseFlow;
	}
	/**
	 * @return the peakFlowRate
	 */
	public String getPeakFlowRate() {
		return peakFlowRate;
	}
	/**
	 * @param peakFlowRate the peakFlowRate to set
	 */
	public void setPeakFlowRate(String peakFlowRate) {
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
		this.peakFlowRateTime = peakFlowRateTime;
	}
	/**
	 * @return the intervalFlowStartingTime
	 */
	public String getIntervalFlowStartingTime() {
		return intervalFlowStartingTime;
	}
	/**
	 * @param intervalFlowStartingTime the intervalFlowStartingTime to set
	 */
	public void setIntervalFlowStartingTime(String intervalFlowStartingTime) {
		this.intervalFlowStartingTime = intervalFlowStartingTime;
	}
	/**
	 * @return the intervalFlow
	 */
	public Object getIntervalFlow() {
		return intervalFlow;
	}
	/**
	 * @param intervalFlow the intervalFlow to set
	 */
	public void setIntervalFlow(Object intervalFlow) {
		this.intervalFlow = intervalFlow;
	}
	/**
	 * @return the instantFlow
	 */
	public Object getInstantFlow() {
		return instantFlow;
	}
	/**
	 * @param instantFlow the instantFlow to set
	 */
	public void setInstantFlow(Object instantFlow) {
		this.instantFlow = instantFlow;
	}
	/**
	 * @return the csq
	 */
	public Integer getCsq() {
		return csq;
	}
	/**
	 * @param csq the csq to set
	 */
	public void setCsq(Integer csq) {
		this.csq = csq;
	}
	/**
	 * @return the snr
	 */
	public String getSnr() {
		return snr;
	}
	/**
	 * @param snr the snr to set
	 */
	public void setSnr(String snr) {
		this.snr = snr;
	}
	/**
	 * @return the cc
	 */
	public Integer getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(Integer cc) {
		this.cc = cc;
	}
	/**
	 * @return the timeOfStarting
	 */
	public String getTimeOfStarting() {
		return timeOfStarting;
	}
	/**
	 * @param timeOfStarting the timeOfStarting to set
	 */
	public void setTimeOfStarting(String timeOfStarting) {
		this.timeOfStarting = timeOfStarting;
	}
	/**
	 * @return the highRateFlow
	 */
	public Object getHighRateFlow() {
		return highRateFlow;
	}
	/**
	 * @param highRateFlow the highRateFlow to set
	 */
	public void setHighRateFlow(Object highRateFlow) {
		this.highRateFlow = highRateFlow;
	}
	/**
	 * @return the highRateReverseFlow
	 */
	public Object getHighRateReverseFlow() {
		return highRateReverseFlow;
	}
	/**
	 * @param highRateReverseFlow the highRateReverseFlow to set
	 */
	public void setHighRateReverseFlow(Object highRateReverseFlow) {
		this.highRateReverseFlow = highRateReverseFlow;
	}
	/**
	 * @return the uploadFreezeDate
	 */
	public String getUploadFreezeDate() {
		return uploadFreezeDate;
	}
	/**
	 * @param uploadFreezeDate the uploadFreezeDate to set
	 */
	public void setUploadFreezeDate(String uploadFreezeDate) {
		this.uploadFreezeDate = uploadFreezeDate;
	}
	
}
