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

import java.util.Date;

import com.nb.model.BaseModel;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;

/**
 * @ClassName: SuntrontWaterMeter
 * @Description: 新天科技WaterMeter服务上报数据项
 * @author dbr
 * @date 2019年4月26日 上午9:15:46
 * 
 */
public class SuntrontWaterMeter extends BaseModel {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1753212176395842175L;
	/** 数据的采集时间 yyyy-MM-ddTHH:mm:ssZ*/
	private String timeOfReading;
	
	/** 日结累计正流量 单位：L */
	private Double dailyFlow;
	/** 日结累计逆流值 单位：L */
	private Double dailyReverseFlow;
	/** 日最高流速 单位：L */
	private Double peakFlowRate;
	/** 日最高流速时间 秒*/
	private Integer peakFlowRateTime;
	/** 间隔流量起始时间 yyyy-MM-ddTHH:mm:ssZ*/
	private String intervalFlowStartingTime;
	/** list<int> 间隔流量(30 分钟)  单位：L*/
	private Object intervalFlow;
	/** list<int> 瞬时流量(30 分钟)  单位：L*/
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
	/** list<int> 累计流量 单位为 L */
	private Object monthlyDailyFlow;
	
	/** 事项上报日期 */
	private int readDate; 
	
	/** 事项上报时间 */
	private int readTime; 
	
	/** 间隔日期 */
	private int intervalDate; 
	
	/** 间隔时间 */
	private int intervalTime; 
	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	/**
	 * @return the monthlyDailyFlow
	 */
	public Object getMonthlyDailyFlow() {
		return monthlyDailyFlow;
	}

	/**
	 * @param monthlyDailyFlow the monthlyDailyFlow to set
	 */
	public void setMonthlyDailyFlow(Object monthlyDailyFlow) {
		this.monthlyDailyFlow = monthlyDailyFlow;
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
		Date date = new Date();
		if (timeOfReading != null && !timeOfReading.isEmpty()) {
			date = DateUtils.utcToLocal(timeOfReading, DateUtils.UTC_PATTERN);

		}
		this.readDate = toInt(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN));
		this.readTime = toInt(DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN));
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
	public void setDailyFlow(Integer dailyFlow) {
		this.dailyFlow = dailyFlow * 1D / Constant.NUM_1000;
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
	public void setDailyReverseFlow(Integer dailyReverseFlow) {
		this.dailyReverseFlow = dailyReverseFlow * 1D / Constant.NUM_1000;
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
	public void setPeakFlowRate(Integer peakFlowRate) {
		this.peakFlowRate = peakFlowRate * 1D / Constant.NUM_1000;
	}


	/**
	 * @return the peakFlowRateTime
	 */
	public Integer getPeakFlowRateTime() {
		return peakFlowRateTime;
	}


	/**
	 * @param peakFlowRateTime the peakFlowRateTime to set
	 */
	public void setPeakFlowRateTime(Integer peakFlowRateTime) {
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
		Date date = DateUtils.utcToLocal(intervalFlowStartingTime, DateUtils.UTC_PATTERN);
		this.intervalDate = toInt(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN));
		this.intervalTime = toInt(DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN));
	}
	
	
	/**
	 * @return the intervalDate
	 */
	public int getIntervalDate() {
		return intervalDate;
	}


	/**
	 * @return the intervalTime
	 */
	public int getIntervalTime() {
		return intervalTime;
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
