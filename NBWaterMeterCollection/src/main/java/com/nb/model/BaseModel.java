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
		this.eventDate = toInt(evnetTime.substring(0, 8));
		this.eventTime = toInt(evnetTime.substring(9, 15)) + 80000;
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
