/**   
* @Title: PeriodReport.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:51:58 
* @version V1.0   
*/
package com.nb.model.jd;

import static com.nb.utils.ConverterUtils.toInt;

import java.io.Serializable;

import com.nb.utils.CommFunc;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;

/**
 * @ClassName: PeriodReport
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年4月10日 上午9:51:58
 * 
 */
public class PeriodReport implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 8509566480231076636L;
	/**  数据时间 	*/ 
	private String readTime;
	/** 首流量 单位:立方米 */
	private Double cumulativeFlow;
	/** 日结累计流量 单位:立方米 */
	private Double positiveCumulativeFlow;
	/** 日结累计逆流量 单位:立方米 */
	private Double negativeCumulativeFlow;
	/** 日最高流速 单位:L/S(升/秒) */
	private Double peakFlowRate;
	/** 最高流速时间戳 HHMMSS */
	private Double peakFlowRateTime;
	/** 首流量记录时间 */
	private String startTime;
	/** 周期数据间隔 单位:分钟 */
	private Integer period;
	/** 流量数组 */
	private Object flows;
	/** 事项上报时间 */
	private String evnetTime;
	
	private Integer readYmd; 
	private Integer readHms;
	private Integer startYmd;
	
	public Object getFlows() {
		return flows;
	}
	public void setFlows(Object flows) {
		this.flows = flows;
	}
	public void setStartTime(String startTime) {
		this.startTime = DateUtils.stampToNoCharDate(ConverterUtils.toLong(startTime));
		this.startYmd = toInt(this.startTime.substring(0, 8));
	}
	public int getReadYmd() {
		return readYmd;
	}
	public int getReadHms() {
		return readHms;
	}
	public int getStartYmd() {
		return startYmd;
	}
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
		this.readTime = DateUtils.stampToNoCharDate(ConverterUtils.toLong(readTime));
		this.readYmd = toInt(this.readTime.substring(0, 8));
		this.readHms = toInt(this.readTime.substring(9, 15));
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
	public Double getPeakFlowRateTime() {
		return peakFlowRateTime;
	}
	/**
	 * @param peakFlowRateTime the peakFlowRateTime to set
	 */
	public void setPeakFlowRateTime(Double peakFlowRateTime) {
		this.peakFlowRateTime = peakFlowRateTime;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Long startTime) {
		this.startTime = DateUtils.stampToDate(ConverterUtils.toLong(startTime));;
	}
	/**
	 * @return the period
	 */
	public Integer getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "PeriodReport [readTime=" + readTime + ", cumulativeFlow=" + cumulativeFlow + ", positiveCumulativeFlow="
				+ positiveCumulativeFlow + ", negativeCumulativeFlow=" + negativeCumulativeFlow + ", peakFlowRate="
				+ peakFlowRate + ", peakFlowRateTime=" + peakFlowRateTime + ", startTime=" + startTime + ", period="
				+ period + ", flows=" + flows + ", evnetTime=" + evnetTime + "]";
	}; // 流量数组 单位:升 数据类型:List<int>
	
	
}
