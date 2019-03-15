/**   
* @Title: LoggerUtils.java 
* @Package com.logback.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月2日 上午10:24:57 
* @version V1.0   
*/
package com.nb.logger;

import org.slf4j.LoggerFactory;

/** 
* @ClassName: LoggerUtils 
* @Description: 日志工具类 
* @author dbr
* @date 2018年11月2日 上午10:24:57 
*  
*/
public class LoggerUtil {
	  public static <T> org.slf4j.Logger Logger(Class<T> clazz) {
	        return LoggerFactory.getLogger(clazz);
	    }

	    /**
	     * 打印到指定的文件下
	     *
	     * @param desc 日志文件名称
	     * @return
	     */
	    public static org.slf4j.Logger Logger(LogName logName) {
	        return LoggerFactory.getLogger(logName.getLogFileName());
	    }
}
