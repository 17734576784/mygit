/**   
* @Title: LoggerUtils.java 
* @Package com.logback.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月2日 上午10:24:57 
* @version V1.0   
*/
package com.ke.utils;

import org.slf4j.LoggerFactory;

import com.ke.model.LogEnum;

/** 
* @ClassName: LoggerUtils 
* @Description: 日志工具类 
* @author dbr
* @date 2018年11月2日 上午10:24:57 
*  
*/
public class LoggerUtils {
	  public static <T> org.slf4j.Logger Logger(Class<T> clazz) {
	        return LoggerFactory.getLogger(clazz);
	    }

	    /**
	     * 打印到指定的文件下
	     *
	     * @param desc 日志文件名称
	     * @return
	     */
	    public static org.slf4j.Logger Logger(LogEnum logEnum) {
	        return LoggerFactory.getLogger(logEnum.getLogFileName());
	    }
}
