/**   
* @Title: HzWaterMeterAlarm.java 
* @Package com.nb.model.hz 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月31日 上午11:17:31 
* @version V1.0   
*/
package com.nb.model.hz;

import java.io.Serializable;
import java.util.Date;

import com.nb.utils.DateUtils;

/** 
* @ClassName: HzWaterMeterAlarm 
* @Description: 汇中水表告警数据
* @author dbr
* @date 2019年5月31日 上午11:17:31 
*  
*/
public class HzWaterMeterAlarm implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	/**告警名称，定义参见告警枚举页面*/
	private String alarmName;	
	/**告警时间，时间格式为yyyyMMdd'T'HHmmss'Z' 	string*/
	private String timestamp;	
	/**0表示告警清除，1表示告警产生	int*/
	private Boolean status;
	private String date; 
	private String time;
	
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
	 * @return the alarmName
	 */
	public String getAlarmName() {
		return alarmName;
	}
	/**
	 * @param alarmName the alarmName to set
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		Date date = DateUtils.parseDate(timestamp, DateUtils.UTC_PATTERN);
		this.timestamp = DateUtils.formatDateByFormat(date, "yyyyMMdd HHmmss");
		this.date = DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN);
		this.time = DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN);	}
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}	
	
	

}
