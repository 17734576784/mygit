/**   
* @Title: LogFileName.java 
* @Package com.iot.logger 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月3日 下午1:32:35 
* @version V1.0   
*/
package com.ke.model;

/**
 * @ClassName: LogFileName
 * @Description: 日志枚举类
 * @author dbr
 * @date 2018年11月3日 下午1:32:35
 * 
 */
public enum LogEnum {
	// 配置到logback.xml中的logger name="CALLBACK"
	CHARGE("CHARGE"), INFO("INFO"), ERROR("ERROR");

	private String logFileName;

	LogEnum(String fileName) {
		this.logFileName = fileName;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public static LogEnum getAwardTypeEnum(String value) {
		LogEnum[] arr = values();
		for (LogEnum item : arr) {
			if (null != item && item.logFileName.isEmpty()) {
				return item;
			}
		}
		return null;
	}
}
