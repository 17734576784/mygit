/**   
* @Title: Log4jUtil.java 
* @Package com.icpms.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年1月19日 下午4:39:43 
* @version V1.0   
*/
package com.pile.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Log4jUtil
 * @Description: Log工具类
 * @author dbr
 * @date 2018年1月19日 下午4:39:43
 * 
 */
@Component
public class Log4jUtils {
	private static final Logger info = Logger.getLogger("info");
	private static final Logger error = Logger.getLogger("error");
	private static final Logger chargerecord = Logger.getLogger("chargerecord");
	private static final Logger discountInfo = Logger.getLogger("discountInfo");
	private static final Logger nettyServer = Logger.getLogger("nettyServer");
	
	/** 构造函数，用于初始化Logger配置需要的属性 */
	public Log4jUtils() {
		// 获得当前目录路径
		String realBasepath = new File(Log4jUtils.class.getResource("/").getPath()).getParent();

		try {
			realBasepath = URLDecoder.decode(realBasepath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 找到log4j.properties配置文件所在的目录(已经创建好)
		String filePath = realBasepath + "\\classes\\config\\log4j.properties";

		// logger所需的配置文件路径
		PropertyConfigurator.configure(filePath);

	}

	/**
	 * @return info
	 */
	public static Logger getInfo() {
		return info;
	}

	/**
	 * @return error
	 */
	public static Logger getError() {
		return error;
	}

	/**
	 * @return the chargerecord
	 */
	public static Logger getChargerecord() {
		return chargerecord;
	}

	/**
	 * @return the discountinfo
	 */
	public static Logger getDiscountinfo() {
		return discountInfo;
	}

	/**
	 * @return the nettyserver
	 */
	public static Logger getNettyserver() {
		return nettyServer;
	}
	
}
