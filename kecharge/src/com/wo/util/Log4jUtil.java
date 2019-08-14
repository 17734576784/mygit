package com.wo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

@Component
public class Log4jUtil {
	private static final Logger info = Logger.getLogger("info");
	private static final Logger error = Logger.getLogger("error");
	private static final Logger chargeinfo = Logger.getLogger("chargeinfo");
      
	/** 构造函数，用于初始化Logger配置需要的属性 */
	public Log4jUtil() {
		// 获得当前目录路径
		String realBasepath = new File(Log4jUtil.class.getResource("/").getPath()).getParent();

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

	public static Logger getChargeinfo() {
		return chargeinfo;
	}

}
