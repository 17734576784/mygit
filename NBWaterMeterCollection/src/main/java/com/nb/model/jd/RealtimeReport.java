/**   
* @Title: RealtimeReport.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午11:07:45 
* @version V1.0   
*/
package com.nb.model.jd;

import java.io.Serializable;

import com.nb.utils.CommFunc;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;

/**
 * @ClassName: RealtimeReport
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年4月10日 上午11:07:45
 * 
 */
public class RealtimeReport implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7266659071610204155L;
	private String readTime; // 数据时间
	private Double cumulativeFlow; // 实时累计流量 单位:立方米
	private Double positiveCumulativeFlow;// 日结累计流量 单位:立方米
	private Double negativeCumulativeFlow;// 日结累计逆流量 单位:立方米
	private Double peakFlowRate; // 日最高流速 单位:L/S(升/秒)
	private String peakFlowRateTime; // 最高流速时间戳 HHMMSS
	private Integer pulseEq; // 脉冲当量 单位:L
	private String evnetTime; // 事项上报时间
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
	public String getPeakFlowRateTime() {
		return peakFlowRateTime;
	}
	/**
	 * @param peakFlowRateTime the peakFlowRateTime to set
	 */
	public void setPeakFlowRateTime(String peakFlowRateTime) {
		this.peakFlowRateTime = DateUtils.stampToNoCharDate(ConverterUtils.toLong(peakFlowRateTime));
	}
	/**
	 * @return the pulseEq
	 */
	public Integer getPulseEq() {
		return pulseEq;
	}
	/**
	 * @param pulseEq the pulseEq to set
	 */
	public void setPulseEq(Integer pulseEq) {
		this.pulseEq = pulseEq;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "RealtimeReport [readTime=" + readTime + ", cumulativeFlow=" + cumulativeFlow
				+ ", positiveCumulativeFlow=" + positiveCumulativeFlow + ", negativeCumulativeFlow="
				+ negativeCumulativeFlow + ", peakFlowRate=" + peakFlowRate + ", peakFlowRateTime=" + peakFlowRateTime
				+ ", pulseEq=" + pulseEq + ", evnetTime=" + evnetTime + "]";
	}

}
