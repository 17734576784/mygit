/**   
* @Title: DeviceParaSetting.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:42:30 
* @version V1.0   
*/
package com.nb.model.jd;

import java.io.Serializable;

import com.nb.utils.CommFunc;

/**
 * @ClassName: DeviceParaSetting
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年4月10日 上午9:42:30
 * 
 */
public class DeviceParaSetting implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 4298242801447990109L;
	/** 大流量报警阀值 单位:L/h */
	private Double peakFlowThreshold;
	/** 大流量持续时间 单位: 分钟 */
	private Integer peakFlowDuration;
	/** 服务器ip地址 */
	private String serverIp;
	/** 服务器端口 */
	private Integer serverPort;
	/** 实时上报起始时间 HH:MM */
	private String realtimeReportStartTime;
	/** 实时上报结束时间 HH:MM */
	private String realtimeReportEndTime;
	/** 周期数据采样间隔 单位:分钟 */
	private Integer periodReportInterva;
	/** 实时上报频率 单位:小时 */
	private Integer realtimeReportFreq;
	/** 上行链接重试次数 默认3次，最大5次 */
	private Integer connectRetryTimes;
	/** 最大重发时间间隔 默认20分钟 */
	private Integer retryInterval;
	/** 即时报警/ID 1表示此告警需要唤醒水表立即上报，0表示此告警在日报里一起上报 */
	private Integer alertId;
	/** APN信息 默认3次，最大5次 */
	private String apn;
	/** 恢复出厂设置 1：恢复出厂设置 */
	private Integer reset;
	/** 上报时间偏移量 单位:分钟 */
	private Integer realtimeReportOffset;
	/** :事项上报时间 */
	private String evnetTime;

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
	 * @return the peakFlowThreshold
	 */
	public Double getPeakFlowThreshold() {
		return peakFlowThreshold;
	}
	/**
	 * @param peakFlowThreshold the peakFlowThreshold to set
	 */
	public void setPeakFlowThreshold(Double peakFlowThreshold) {
		this.peakFlowThreshold = peakFlowThreshold;
	}
	/**
	 * @return the peakFlowDuration
	 */
	public Integer getPeakFlowDuration() {
		return peakFlowDuration;
	}
	/**
	 * @param peakFlowDuration the peakFlowDuration to set
	 */
	public void setPeakFlowDuration(Integer peakFlowDuration) {
		this.peakFlowDuration = peakFlowDuration;
	}
	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}
	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	/**
	 * @return the serverPort
	 */
	public Integer getServerPort() {
		return serverPort;
	}
	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	/**
	 * @return the realtimeReportStartTime
	 */
	public String getRealtimeReportStartTime() {
		return realtimeReportStartTime;
	}
	/**
	 * @param realtimeReportStartTime the realtimeReportStartTime to set
	 */
	public void setRealtimeReportStartTime(String realtimeReportStartTime) {
		this.realtimeReportStartTime = realtimeReportStartTime;
	}
	/**
	 * @return the realtimeReportEndTime
	 */
	public String getRealtimeReportEndTime() {
		return realtimeReportEndTime;
	}
	/**
	 * @param realtimeReportEndTime the realtimeReportEndTime to set
	 */
	public void setRealtimeReportEndTime(String realtimeReportEndTime) {
		this.realtimeReportEndTime = realtimeReportEndTime;
	}
	/**
	 * @return the periodReportInterva
	 */
	public Integer getPeriodReportInterva() {
		return periodReportInterva;
	}
	/**
	 * @param periodReportInterva the periodReportInterva to set
	 */
	public void setPeriodReportInterva(Integer periodReportInterva) {
		this.periodReportInterva = periodReportInterva;
	}
	/**
	 * @return the realtimeReportFreq
	 */
	public Integer getRealtimeReportFreq() {
		return realtimeReportFreq;
	}
	/**
	 * @param realtimeReportFreq the realtimeReportFreq to set
	 */
	public void setRealtimeReportFreq(Integer realtimeReportFreq) {
		this.realtimeReportFreq = realtimeReportFreq;
	}
	/**
	 * @return the connectRetryTimes
	 */
	public Integer getConnectRetryTimes() {
		return connectRetryTimes;
	}
	/**
	 * @param connectRetryTimes the connectRetryTimes to set
	 */
	public void setConnectRetryTimes(Integer connectRetryTimes) {
		this.connectRetryTimes = connectRetryTimes;
	}
	/**
	 * @return the retryInterval
	 */
	public Integer getRetryInterval() {
		return retryInterval;
	}
	/**
	 * @param retryInterval the retryInterval to set
	 */
	public void setRetryInterval(Integer retryInterval) {
		this.retryInterval = retryInterval;
	}
	/**
	 * @return the alertId
	 */
	public Integer getAlertId() {
		return alertId;
	}
	/**
	 * @param alertId the alertId to set
	 */
	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}
	/**
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}
	/**
	 * @param apn the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}
	/**
	 * @return the reset
	 */
	public Integer getReset() {
		return reset;
	}
	/**
	 * @param reset the reset to set
	 */
	public void setReset(Integer reset) {
		this.reset = reset;
	}
	/**
	 * @return the realtimeReportOffset
	 */
	public Integer getRealtimeReportOffset() {
		return realtimeReportOffset;
	}
	/**
	 * @param realtimeReportOffset the realtimeReportOffset to set
	 */
	public void setRealtimeReportOffset(Integer realtimeReportOffset) {
		this.realtimeReportOffset = realtimeReportOffset;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "DeviceParaSetting [peakFlowThreshold=" + peakFlowThreshold + ", peakFlowDuration=" + peakFlowDuration
				+ ", serverIp=" + serverIp + ", serverPort=" + serverPort + ", realtimeReportStartTime="
				+ realtimeReportStartTime + ", realtimeReportEndTime=" + realtimeReportEndTime
				+ ", periodReportInterva=" + periodReportInterva + ", realtimeReportFreq=" + realtimeReportFreq
				+ ", connectRetryTimes=" + connectRetryTimes + ", retryInterval=" + retryInterval + ", alertId="
				+ alertId + ", apn=" + apn + ", reset=" + reset + ", realtimeReportOffset=" + realtimeReportOffset
				+ ", evnetTime=" + evnetTime + "]";
	}

}
