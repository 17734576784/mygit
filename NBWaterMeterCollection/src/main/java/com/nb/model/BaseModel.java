/**   
* @Title: BaseModel.java 
* @Package com.nb.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月26日 下午1:40:08 
* @version V1.0   
*/
package com.nb.model;

import static com.nb.utils.ConverterUtils.toInt;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.nb.utils.DateUtils;

/**
 * @ClassName: BaseModel
 * @Description: NBModel基类
 * @author dbr
 * @date 2019年4月26日 下午1:40:08
 * 
 */
public class BaseModel implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1939921946191665076L;
	/** 事项上报日期 */
	private int eventDate;

	/** 事项上报时间 */
	private int eventTime;

	/**
	 * @param evnetTime the evnetTime to set
	 */
	public void setEvnetTime(String evnetTime) {
		Date date = new Date();
		if (evnetTime != null && !evnetTime.isEmpty()) {
			date = DateUtils.utcToLocal(evnetTime, DateUtils.UTC_PATTERN);

		}
		this.eventDate = toInt(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN));
		this.eventTime = toInt(DateUtils.formatDateByFormat(date, DateUtils.TIME_PATTERN));
	}
	
	public void setEvnetTime(Map<String, String> serviceMap) {
		if (serviceMap.containsKey("eventTime")) {
			setEvnetTime(serviceMap.get("eventTime"));
		} else if (serviceMap.containsKey("timestamp")) {
			setEvnetTime(serviceMap.get("timestamp"));
		} else {
			setEvnetTime("");
		}
	}

	/**
	 * @return the eventDate
	 */
	public int getEventDate() {
		return eventDate;
	}

	/**
	 * @return the eventTime
	 */
	public int getEventTime() {
		return eventTime;
	}

}
