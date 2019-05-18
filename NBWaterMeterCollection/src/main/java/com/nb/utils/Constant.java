/*
 * File Name: com.huawei.utils.Constant.java
 *
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.nb.utils;

import java.io.File;

/** 
* @ClassName: Constant 
* @Description: 常量定义类
* @author dbr
* @date 2019年4月18日 下午4:51:44 
*  
*/
public class Constant {

	/** 中国移动平台地址 */
	public static final String CHINA_MOBILE_BASE_URL = "http://api.heclouds.com/";
	/** 中国移动平台认证token */
	public static final String CHINA_MOBILE_TOKEN = "kldq";

	/** 中国联通平台地址 */
	public static final String CHINA_UNICOM_BASE_URL = "https://58.240.96.46:8743";
	/** 中国电信正式平台地址 */
	public static final String CHINA_TELECOM_BASE_URL = "https://device.api.ct10649.com:8743";
	/** 中国电信测试平台地址 */
//	public static final String CHINA_TELECOM_BASE_URL = "https://180.101.147.89:8743";

	// please replace the appId and secret, when you use the demo.
	/** 中国移动平台APPID */
	public static final String CHINA_MOBILE_MASTERKEY = "WKcUHaxTTLP=Pu07rtYYR5IXLac=";
	/** 中国移动平台SECRET */
	public static final String CHINA_MOBILE_SECRET = "WKcUHaxTTLP=Pu07rtYYR5IXLac=";

	/** 中国联通平台APPID */
//	public static final String CHINA_UNICOM_APPID = "YXnpwWndC3ioc15OGALXZvHLB1oa";
	/** 中国联通平台SECRET */
//	public static final String CHINA_UNICOM_SECRET = "uOL_C0yP6pl4Gals5dJkrfF1vSka";

	/** 中国电信平台APPID */
//	public static final String CHINA_TELECOM_APPID = "fn5I2QJuIRFJxsrXXbUzWXn0S3Ya";
	/** 中国电信平台SECRET */
//	public static final String CHINA_TELECOM_SECRET = "9IL8rmcruUPIbth4gRP1XGAns2Ya";

	/** 
	* @Fields CALLBACK_BASE_URL : IP and port of callback url. please replace the IP and Port of your
	 * Application deployment environment address, when you use the demo. 
	*/ 
	public static final String CALLBACK_BASE_URL = "https://222.222.60.178:18213";

	/** 
	* @Fields DEVICE_ADDED_CALLBACK_URL :complete callback url please replace uri, when you use the demo.
	*/ 
	public static final String DEVICE_ADDED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceAdded";
	public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceInfoChanged";
	public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceDataChanged";
	public static final String DEVICE_DELETED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceDeleted";
	public static final String MESSAGE_CONFIRM_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/messageConfirm";
	public static final String SERVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/serviceInfoChanged";
	public static final String COMMAND_RSP_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/commandRsp";
	public static final String DEVICE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceEvent";
	public static final String RULE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/ruleEvent";
	public static final String DEVICE_DATAS_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/chinatelecom/deviceDatasChanged";

	/**
	 * @Fields CHINA_TELECOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL : Specifies the
	 *         callback URL for the command execution result notification. For
	 *         details about the execution result notification definition.
	 *         please replace uri, when you use the demo.
	 */ 
	public static final String CHINA_TELECOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL
			+ "/chinatelecom/reportCmdExecResult";

	public static final String CHINA_UNICOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL
			+ "/chinaunicom/reportCmdExecResult";

	public static String realBasepath = new File(Constant.class.getResource("/").getPath()).getParent();

	// Paths of certificates.
	/** 中国联通证书路径 */
	public static String CHINA_UNICOM_SELFCERTPATH = "cert/CertwithKey.pkcs12";
	public static String CHINA_UNICOM_TRUSTCAPATH = "cert/ca.jks";

	// Password of certificates."classpath:cert\\outgoing.CertwithKey.pkcs12"
	/** 中国联通证书密码 */
	public static String CHINA_UNICOM_SELFCERTPWD = "IoM@1234";
	public static String CHINA_UNICOM_TRUSTCAPWD = "Huawei@123";

	/** 中国电信证书路径 */
	public static String CHINA_TELECOM_SELFCERTPATH = "cert/CertwithKey.pkcs12";
	public static String CHINA_TELECOM_TRUSTCAPATH = "cert/ca.jks";

	// Password of certificates."classpath:cert\\outgoing.CertwithKey.pkcs12"
	/** 中国电信证书密码 */
	public static String CHINA_TELECOM_SELFCERTPWD = "IoM@1234";
	public static String CHINA_TELECOM_TRUSTCAPWD = "Huawei@123";

	// *************************** The following constants do not need to be
	// modified *********************************//

	/** 
	* @Fields HEADER_APP_KEY :request header 1. HEADER_APP_KEY 2. HEADER_APP_AUTH
	*/ 
	public static final String HEADER_APP_KEY = "app_key";
	public static final String HEADER_APP_AUTH = "Authorization";

	/*
	 * Application Access Security: 1. APP_AUTH 2. REFRESH_TOKEN
	 */
	/** 中国电信鉴权地址 */
	public static final String CHINA_TELECOM_APP_AUTH = CHINA_TELECOM_BASE_URL + "/iocm/app/sec/v1.1.0/login";
	public static final String CHINA_TELECOM_REFRESH_TOKEN = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/sec/v1.1.0/refreshToken";

	/** 中国联通鉴权地址 */
	public static final String CHINA_UNICOM_APP_AUTH = CHINA_UNICOM_BASE_URL + "/iocm/app/sec/v1.1.0/login";
	public static final String CHINA_UNICOM_REFRESH_TOKEN = CHINA_UNICOM_BASE_URL + "/iocm/app/sec/v1.1.0/refreshToken";

	
	/** 
	* @Fields CHINA_TELECOM_REGISTER_DEVICE : 中国电信访问路径 Device Management: 1. REGISTER_DEVICE 2. MODIFY_DEVICE_INFO 3.
	 * QUERY_DEVICE_ACTIVATION_STATUS 4. DELETE_DEVICE 5. DISCOVER_INDIRECT_DEVICE
	 * 6. REMOVE_INDIRECT_DEVICE 
	*/ 
	public static final String CHINA_TELECOM_REGISTER_DEVICE = CHINA_TELECOM_BASE_URL + "/iocm/app/reg/v1.1.0/deviceCredentials";
	public static final String CHINA_TELECOM_MODIFY_DEVICE_INFO = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_ACTIVATION_STATUS = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_TELECOM_DELETE_DEVICE = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_TELECOM_DISCOVER_INDIRECT_DEVICE = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
	public static final String CHINA_TELECOM_REMOVE_INDIRECT_DEVICE = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

	/**
	 * Data Collection: 1. QUERY_DEVICES 2. QUERY_DEVICE_DATA 3.
	 * QUERY_DEVICE_HISTORY_DATA 4. QUERY_DEVICE_CAPABILITIES 5.
	 * SUBSCRIBE_NOTIFYCATION
	 */
	public static final String CHINA_TELECOM_QUERY_DEVICES = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_DATA = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_HISTORY_DATA = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/data/v1.2.0/deviceDataHistory";
	public static final String CHINA_TELECOM_QUERY_DEVICE_CAPABILITIES = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/data/v1.2.0/deviceCapabilities";
	public static final String CHINA_TELECOM_SUBSCRIBE_NOTIFYCATION = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/sub/v1.2.0/subscriptions";

	/**
	 * Signaling Delivery 1. POST_ASYN_CMD 2. QUERY_DEVICE_CMD 3.
	 * UPDATE_ASYN_COMMAND 4. CREATE_DEVICECMD_CANCEL_TASK 5.
	 * QUERY_DEVICECMD_CANCEL_TASK
	 *
	 */
	public static final String CHINA_TELECOM_POST_ASYN_CMD = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	
	public static final String CHINA_TELECOM_BATCHTASK_CMD = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/batchtask/v1.1.0/tasks";
	
	public static final String CHINA_TELECOM_QUERY_DEVICE_CMD = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	public static final String CHINA_TELECOM_UPDATE_ASYN_COMMAND = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands/%s";
	public static final String CHINA_TELECOM_CREATE_DEVICECMD_CANCEL_TASK = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
	public static final String CHINA_TELECOM_QUERY_DEVICECMD_CANCEL_TASK = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";

	/**
	 * 中国联通访问路径 Device Management: 1. REGISTER_DEVICE 2. MODIFY_DEVICE_INFO 3.
	 * QUERY_DEVICE_ACTIVATION_STATUS 4. DELETE_DEVICE 5. DISCOVER_INDIRECT_DEVICE
	 * 6. REMOVE_INDIRECT_DEVICE
	 */
	public static final String CHINA_UNICOM_REGISTER_DEVICE = CHINA_UNICOM_BASE_URL + "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_UNICOM_MODIFY_DEVICE_INFO = CHINA_UNICOM_BASE_URL + "iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_ACTIVATION_STATUS = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_UNICOM_DELETE_DEVICE = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_UNICOM_DISCOVER_INDIRECT_DEVICE = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
	public static final String CHINA_UNICOM_REMOVE_INDIRECT_DEVICE = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

	/**
	 * Data Collection: 1. QUERY_DEVICES 2. QUERY_DEVICE_DATA 3.
	 * QUERY_DEVICE_HISTORY_DATA 4. QUERY_DEVICE_CAPABILITIES 5.
	 * SUBSCRIBE_NOTIFYCATION
	 */
	public static final String CHINA_UNICOM_QUERY_DEVICES = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_DATA = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_HISTORY_DATA = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/data/v1.2.0/deviceDataHistory";
	public static final String CHINA_UNICOM_QUERY_DEVICE_CAPABILITIES = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/data/v1.2.0/deviceCapabilities";
	public static final String CHINA_UNICOM_SUBSCRIBE_NOTIFYCATION = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/sub/v1.1.0/subscribe";

	/**
	 * Signaling Delivery 1. POST_ASYN_CMD 2. QUERY_DEVICE_CMD 3.
	 * UPDATE_ASYN_COMMAND 4. CREATE_DEVICECMD_CANCEL_TASK 5.
	 * QUERY_DEVICECMD_CANCEL_TASK
	 *
	 */
	public static final String CHINA_UNICOM_POST_ASYN_CMD = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	public static final String CHINA_UNICOM_QUERY_DEVICE_CMD = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	public static final String CHINA_UNICOM_UPDATE_ASYN_COMMAND = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands/%s";
	public static final String CHINA_UNICOM_CREATE_DEVICECMD_CANCEL_TASK = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
	public static final String CHINA_UNICOM_QUERY_DEVICECMD_CANCEL_TASK = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";

	/**
	 * notify Type
	 * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|
	 * deviceDatasChanged
	 * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent
	 */
	public static final String DEVICE_ADDED = "deviceAdded";
	public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";
	public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";
	public static final String DEVICE_DELETED = "deviceDeleted";
	public static final String MESSAGE_CONFIRM = "messageConfirm";
	public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";
	public static final String COMMAND_RSP = "commandRsp";
	public static final String DEVICE_EVENT = "deviceEvent";
	public static final String RULE_EVENT = "ruleEvent";
	public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";

	/** 返回错误码 0：成功 1：失败 */
	public static final int SUCCESS = 0;
	public static final int ERROR = -1;

	public static final String OK = "ok";

	/** 命令发送 */
	public static final String COMMAND_SEND = "SEND";

	/** 命令交付 */
	public static final String COMMAND_DELIVERED = "DELIVERED";

	/** 命令发送 */
	public static final String COMMAND_TIMEOUT = "TIMEOUT";

	/** 命令成功 */
	public static final String COMMAND_SUCCESS = "SUCCESSFUL";

	/** 命令失败 */
	public static final String COMMAND_FAILED = "FAILED";

	/** 删除设备成功 */
	public static final int STATUS_204 = 204;

	/** 删除设备 设备不存在 */
	public static final int STATUS_404 = 404;

	/** 升级标志 0：升级 1：不升级 */
	public static final int UPGRADE_SUCCESS = 0;

	/** 升级标志 0：升级 1：不升级 */
	public static final int UPGRADE_FAILED = 1;

	/** redis 中国电信设备升级进度key前缀 */
	public static final String PROGRESS_CHINA_TELECOM = "progress_china_telecom_";

	/** redis 中国联通设备升级进度key前缀 */
	public static final String PROGRESS_CHINA_UNICOM = "progress_china_unicom_";

	/** redis 命令idkey前缀 */
	public static final String COMMAND = "command_";

	public static final String EMPTY = "";
	
	/** 中国电信服务前缀 */
	public static String CHINA_TELECOM_SERVICE = "chinaTelecom";

	/** 中国联通服务前缀 */
	public static String CHINA_UNICOM_SERVICE = "chinaUnicom";

	/** 中国电信服务前缀 */
	public static String CHINA_TELECOM_COMMAND = "chinaTelecomCommand";

	/** 中国联通服务前缀 */
	public static String CHINA_UNICOM_COMMAND = "chinaUnicomCommand";

	/** 运营商类型 */
	public static final int CHINA_MOBILE = 0;
	public static final int CHINA_TELECOM = 2;
	public static final int CHINA_UNICOM = 1;

	public static final int ZERO = 0;
	
	public static final int REDIS_TIMEOUT = 0;

	/** 命令类型 redis Key */
	public static final String COMMAND_TYPE_REIDS = "command_type";

	/** 标识消息类型 1：设备上传数据点消息 */
	public static final int CHINA_MOBILE_DATA_MSG = 1;
	/** 标识消息类型 2：设备上下线消息 */
	public static final int CHINA_MOBILE_DEVICE_MSG = 2;
	/** 标识消息类型 7：缓存命令下发后结果上报（仅支持NB设备） */
	public static final int CHINA_MOBILE_COMMAND_MSG = 7;

	/** 历史库队列 日数据redis前缀 */
	public static final String HISTORY_DAILY_QUEUE = "HISTORY_DAILY_QUEUE";
	public static final String HISTORY_DAILY_BAK_QUEUE = "HISTORY_DAILY_BAK_QUEUE";
	public static final String HISTORY_DAILY_ERROR_QUEUE = "HISTORY_DAILY_ERROR_QUEUE";

	
	/** 历史库队列 电池电压redis前缀 */
	public static final String HISTORY_BATTERY_QUEUE = "HISTORY_BATTERY_QUEUE";
	public static final String HISTORY_BATTERY_BAK_QUEUE = "HISTORY_BATTERY_BAK_QUEUE";
	public static final String HISTORY_BATTERY_ERROR_QUEUE = "HISTORY_BATTERY_ERROR_QUEUE";

	
	/** 历史库队列 瞬时量redis前缀 */
	public static final String HISTORY_INSTAN_QUEUE = "HISTORY_INSTAN_QUEUE";
	public static final String HISTORY_INSTAN_BAK_QUEUE = "HISTORY_INSTAN_BAK_QUEUE";
	public static final String HISTORY_INSTAN_ERROR_QUEUE = "HISTORY_INSTAN_ERROR_QUEUE";

	/** 告警事项队列redis前缀 */
	public static final String ALARM_EVENT_QUEUE = "ALARM_EVENT_QUEUE";
	public static final String ALARM_EVENT_BAK_QUEUE = "ALARM_EVENT_BAK_QUEUE";
	public static final String ALARM_EVENT_ERROR_QUEUE = "ALARM_EVENT_ERROR_QUEUE";
	
	/** 竟达电池电压告警标志 */
	public static final String BATTERY_ALARM = "Y";

	/** 告警 */ 
	public static final short ALARM = 1;
	/** 非告警 */ 
	public static final short NO_ALARM = 0;
	/** NB水表告警 */ 
	public static final short NB_ALARM = 20;
	/** 大流量告警 */ 
	public static final short ALARM_2001 = 2001;
	/** 小流量告警 */ 
	public static final short ALARM_2002 = 2002;
	/** 数据被篡改 */
	public static final short ALARM_2012 = 2012;
	/** 反流告警 */ 
	public static final short ALARM_2003 = 2003;
	/** 磁干扰告警 */ 
	public static final short ALARM_2004 = 2004;
	/** 电池低电压告警 */ 
	public static final short ALARM_2005 = 2005;
	/** 远传模块分离告警 */ 
	public static final short ALARM_2006 = 2006;
	/** 内部错误 */ 
	public static final short ALARM_2007 = 2007;
	/** 低压告警 */ 
	public static final short ALARM_2008 = 2008;
	/** 高压告警 */ 
	public static final short ALARM_2009 = 2009;
	/** 阀门异常 */
	public static final short ALARM_2010 = 2010;
	/** 存储器异常 */ 
	public static final short ALARM_2011 = 2011;

	/** 竟达阀门状态：1：正在开阀 2：阀门开到位 3：正在关阀 4：阀门关到位 5：半开 6:其他 */ 
	public static final byte VALVE_OPENING = 1;
	public static final byte VALVE_OPEN = 2;
	public static final byte VALVE_CLOSEING = 3;
	public static final byte VALVE_CLOSE = 4;
	public static final byte VALVE_HALF_OPEN = 5;
	public static final byte VALVE_OTHER = 6;

	/** 府星告警事项 */ 
	/** 阀门异常 */ 
	public static final short FX_VALVE_ERROR = 1;
	/** 强磁异常 */ 
	public static final short FX_MAGNETIC = 4;
	/** 低压告警 */ 
	public static final short FX_BATTERY_1 = 5;
	public static final short FX_BATTERY_2 = 6;
	/** 批量命令类型 DeviceList */ 
	public static final String TASK_DEVICELIST = "DeviceList";
	/** 批量命令类型 DeviceType */ 
	public static final String TASK_DEVICETYPE = "DeviceType";
	
	/** 任务类型 */ 
	public static final String TASK_DEVICEREG = "DeviceReg";
	public static final String TASK_DEVICECMD = "DeviceCmd";
	/** 任务超时时间 */
	public static final int TASK_TIMEOUT = 2880;
	/** 命令redis超时时间 */
	public static final int COMMAND_TIME_OUT = 60 * 60 * 36;

	/** 命令类型 */ 
	public static final byte BATCH_COMMAND = 0;
	public static final byte ASYN_COMMAND = 1;
	
	/** 数据库历史表日期 */
	public static final int TABLE_YYYYMM = 6;
	public static final int TABLE_YYYY = 4;
	
	/** 正则表达式 */ 
	public static final String REG_INT = "^-?\\d+{1}";
	public static final String REG_DIGIT = "^-?[0-9]*.?[0-9]*{1}";
	
	/** 字段定义 */ 
	public static final String COMMANDID = "commandId";
	public static final String TASKID = "taskID";
	public static final String DATA = "data";
	public static final String ENC_MSG = "enc_msg";
	public static final String MSG = "msg";
	public static final String DAY = "day";
	public static final String MONTH = "month";

	
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int NUM_200000 = 200000;
	public static final int NUM_200 = 200;
	public static final int NUM_31 = 31;

	public static final int NUM_1000 = 1000;
	public static final int NUM_48 = 48;

	/** 补招数据 从T-2日起 */
	public static final int TASK_ENDDATE = -1;
	/** 补招T-2前几日的数据 */
	public static final int TASK_CALL_DAYS = 5;

	/** 电信平台获取设备历史数据 补招前PAGE_SIZE条记录*/
	public static final int PAGE_SIZE = 100;
	/** 电信平台鉴权凭证*/
	public static final String ACCESS_TOKEN = "accessToken:";
	
	/** 新天科技移动平台上报数据项ds_id */
	public static final String SUNTRONTDSID = "3200_0_5505";
}


