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

public class Constant {

	/** 中国移动平台地址 */
	public static final String CHINA_MOBILE_BASE_URL = "http://api.heclouds.com/";
	/** 中国移动平台认证token */
	public static final String CHINA_MOBILE_TOKEN = "kldq";

	/** 中国联通平台地址 */
	public static final String CHINA_UNICOM_BASE_URL = "https://58.240.96.46:8743";
	/** 中国电信平台地址 */
	public static final String CHINA_TELECOM_BASE_URL = "https://180.101.147.89:8743";

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

	/*
	 * IP and port of callback url. please replace the IP and Port of your
	 * Application deployment environment address, when you use the demo.
	 */
	public static final String CALLBACK_BASE_URL = "https://39.105.158.202:443";

	/*
	 * complete callback url please replace uri, when you use the demo.
	 */
	public static final String DEVICE_ADDED_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceAdded";
	public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceInfoChanged";
	public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceDataChanged";
	public static final String DEVICE_DELETED_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceDeleted";
	public static final String MESSAGE_CONFIRM_CALLBACK_URL = CALLBACK_BASE_URL + "/commandConfirmData";
	public static final String SERVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/updateServiceInfo";
	public static final String COMMAND_RSP_CALLBACK_URL = CALLBACK_BASE_URL + "/commandRspData";
	public static final String DEVICE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/deviceEvent";
	public static final String RULE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/ruleEvent";
	public static final String DEVICE_DATAS_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/updateDeviceDatas";

	/*
	 * Specifies the callback URL for the command execution result notification. For
	 * details about the execution result notification definition.
	 *
	 * please replace uri, when you use the demo.
	 */
	public static final String CHINA_TELECOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL
			+ "/chinatelecom/reportCmdExecResult";
	

	public static final String CHINA_UNICOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL
			+ "/chinaunicom/reportCmdExecResult";

	public static String realBasepath = new File(Constant.class.getResource("/").getPath()).getParent();

	// Paths of certificates.
	/** 中国联通证书路径 */
	public static String CHINA_UNICOM_SELFCERTPATH = "cert/unicom/CertwithKey.pkcs12";
	public static String CHINA_UNICOM_TRUSTCAPATH = "cert/unicom/ca.jks";

	// Password of certificates."classpath:cert\\outgoing.CertwithKey.pkcs12"
	/** 中国联通证书密码 */
	public static String CHINA_UNICOM_SELFCERTPWD = "IoM@1234";
	public static String CHINA_UNICOM_TRUSTCAPWD = "Huawei@123";

	/** 中国电信证书路径 */
	public static String CHINA_TELECOM_SELFCERTPATH = "cert/telecom/CertwithKey.pkcs12";
	public static String CHINA_TELECOM_TRUSTCAPATH = "cert/telecom/ca.jks";

	// Password of certificates."classpath:cert\\outgoing.CertwithKey.pkcs12"
	/** 中国电信证书密码 */
	public static String CHINA_TELECOM_SELFCERTPWD = "IoM@1234";
	public static String CHINA_TELECOM_TRUSTCAPWD = "Huawei@123";

	// *************************** The following constants do not need to be
	// modified *********************************//

	/*
	 * request header 1. HEADER_APP_KEY 2. HEADER_APP_AUTH
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

	/*
	 * 中国电信访问路径 Device Management: 1. REGISTER_DEVICE 2. MODIFY_DEVICE_INFO 3.
	 * QUERY_DEVICE_ACTIVATION_STATUS 4. DELETE_DEVICE 5. DISCOVER_INDIRECT_DEVICE
	 * 6. REMOVE_INDIRECT_DEVICE
	 */
	public static final String CHINA_TELECOM_REGISTER_DEVICE = CHINA_TELECOM_BASE_URL + "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_TELECOM_MODIFY_DEVICE_INFO = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/dm/v1.1.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_ACTIVATION_STATUS = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_TELECOM_DELETE_DEVICE = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_TELECOM_DISCOVER_INDIRECT_DEVICE = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
	public static final String CHINA_TELECOM_REMOVE_INDIRECT_DEVICE = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

	/*
	 * Data Collection: 1. QUERY_DEVICES 2. QUERY_DEVICE_DATA 3.
	 * QUERY_DEVICE_HISTORY_DATA 4. QUERY_DEVICE_CAPABILITIES 5.
	 * SUBSCRIBE_NOTIFYCATION
	 */
	public static final String CHINA_TELECOM_QUERY_DEVICES = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_DATA = CHINA_TELECOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_TELECOM_QUERY_DEVICE_HISTORY_DATA = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/data/v1.1.0/deviceDataHistory";
	public static final String CHINA_TELECOM_QUERY_DEVICE_CAPABILITIES = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/data/v1.1.0/deviceCapabilities";
	public static final String CHINA_TELECOM_SUBSCRIBE_NOTIFYCATION = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/sub/v1.1.0/subscribe";

	/*
	 * Signaling Delivery 1. POST_ASYN_CMD 2. QUERY_DEVICE_CMD 3.
	 * UPDATE_ASYN_COMMAND 4. CREATE_DEVICECMD_CANCEL_TASK 5.
	 * QUERY_DEVICECMD_CANCEL_TASK
	 *
	 */
	public static final String CHINA_TELECOM_POST_ASYN_CMD = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	public static final String CHINA_TELECOM_QUERY_DEVICE_CMD = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands";
	public static final String CHINA_TELECOM_UPDATE_ASYN_COMMAND = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommands/%s";
	public static final String CHINA_TELECOM_CREATE_DEVICECMD_CANCEL_TASK = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
	public static final String CHINA_TELECOM_QUERY_DEVICECMD_CANCEL_TASK = CHINA_TELECOM_BASE_URL
			+ "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";

	/*
	 * 中国联通访问路径 Device Management: 1. REGISTER_DEVICE 2. MODIFY_DEVICE_INFO 3.
	 * QUERY_DEVICE_ACTIVATION_STATUS 4. DELETE_DEVICE 5. DISCOVER_INDIRECT_DEVICE
	 * 6. REMOVE_INDIRECT_DEVICE
	 */
	public static final String CHINA_UNICOM_REGISTER_DEVICE = CHINA_UNICOM_BASE_URL + "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_UNICOM_MODIFY_DEVICE_INFO = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.1.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_ACTIVATION_STATUS = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/reg/v1.1.0/devices";
	public static final String CHINA_UNICOM_DELETE_DEVICE = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.4.0/devices";
	public static final String CHINA_UNICOM_DISCOVER_INDIRECT_DEVICE = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
	public static final String CHINA_UNICOM_REMOVE_INDIRECT_DEVICE = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

	/*
	 * Data Collection: 1. QUERY_DEVICES 2. QUERY_DEVICE_DATA 3.
	 * QUERY_DEVICE_HISTORY_DATA 4. QUERY_DEVICE_CAPABILITIES 5.
	 * SUBSCRIBE_NOTIFYCATION
	 */
	public static final String CHINA_UNICOM_QUERY_DEVICES = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_DATA = CHINA_UNICOM_BASE_URL + "/iocm/app/dm/v1.3.0/devices";
	public static final String CHINA_UNICOM_QUERY_DEVICE_HISTORY_DATA = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/data/v1.1.0/deviceDataHistory";
	public static final String CHINA_UNICOM_QUERY_DEVICE_CAPABILITIES = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/data/v1.1.0/deviceCapabilities";
	public static final String CHINA_UNICOM_SUBSCRIBE_NOTIFYCATION = CHINA_UNICOM_BASE_URL
			+ "/iocm/app/sub/v1.1.0/subscribe";

	/*
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

	/*
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

	public static final String UPLOAD_PIC = "1";
	/** 上传图片url */
	public static final String UPLOAD_IMAGE_URL = "nbNotifyAction!notifyImage.action";

	/** 上传告警 */
	public static final String UPLOAD_ALARM_URL = "nbNotifyAction!notifyAlarm.action";

	/** 警开关开闭命令响应 */
	public static final String UPLOAD_ALARMCOMMAND_URL = "nbNotifyAction!notifyAlarmConfigResult.action";

	/** 上传设定时间命令响应 */
	public static final String UPLOAD_TIMECOMMAND_URL = "nbNotifyAction!notifyReadTimeResult.action";

	/** 上传主动查询 */
	public static final String UPLOAD_CHECK_URL = "nbNotifyAction!checkExistOrders.action";

	/** 上传升级成功地址 */
	public static final String UPLOAD_UPGRADERESULT_URL = "nbNotifyAction!notifyUploadResult.action";

	/** 上传抄表数据地址 */
	public static final String UPLOAD_DATA_URL = "nbNotifyAction!notifyData.action";

	/** 上传告警地址 */
	public static final String UPLOAD_EVENT_URL = "nbNotifyAction!notifyEvent.action";
	
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

	/** 询问设备是否升级服务名 */
	public static String UPVERSIONSERVICE = "UpversionService";

	/** 询问设备是否升级方法名 */
	public static String UPVERSION = "upversion";

	/** 升级服务名 */
	public static String UPDATASERVICE = "UpdataService";

	/** 升级服务方法名 */
	public static String UPDATACMD = "updatacmd";

	/** CHECK上报缓存键前缀 */
	public static String CHECK = "CHECK";

	/** 升级文件根目录 */
	public static String BaseFilePath = "c://";

	/** 升级文件根目录 */
	public static int FILETIMEOUT = 60 * 60 * 24 * 180;

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

	/** 命令类型 抄收图片 */
	public static final int COMMAND_TYPE_IMAGE = 1;
	/** 命令类型 抄收数据命令 */
	public static final int COMMAND_TYPE_DATA = 2;
	/** 命令类型 开阀命令 */
	public static final int COMMAND_TYPE_OPEN = 10;
	/** 命令类型 关阀命令 */
	public static final int COMMAND_TYPE_CLOSE = 11;
	/** 命令类型 设置抄表时间命令 */
	public static final int COMMAND_TYPE_CBTIME = 20;
	/** 命令类型 设置告警开关命令 */
	public static final int COMMAND_TYPE_ALARM = 21;
	/** 命令类型 调整摄像头命令 */
	public static final int COMMAND_TYPE_CAMERA = 22;
	/** 命令类型 完成激活命令 */
	public static final int COMMAND_TYPE_ACTIVATE = 99;

	/** 命令类型 redis Key */
	public static final String COMMAND_TYPE_REIDS = "command_type";

	/** 标识消息类型 1：设备上传数据点消息 */
	public static final int CHINA_MOBILE_DATA_MSG = 1;
	/** 标识消息类型 2：设备上下线消息 */
	public static final int CHINA_MOBILE_DEVICE_MSG = 2;
	/** 标识消息类型 7：缓存命令下发后结果上报（仅支持NB设备） */
	public static final int CHINA_MOBILE_COMMAND_MSG = 3;
	
	public static final int ALARM_TYPE_LOWFLOWALARM = 100;// 持续低流量告警
	public static final int ALARM_TYPE_HIGHFLOWALARM = 101;// 持续高流量告警
	public static final int ALARM_TYPE_LOWBATTERYALARM = 102;// 低电压告警
	public static final int ALARM_TYPE_BATTERYRUNOUTALARM = 103;// 电量严重不足即将耗尽
	public static final int ALARM_TYPE_HIGHTEMPERATUREALARM = 104;// 高温告警
	public static final int ALARM_TYPE_LOWTEMPERATUREALARM = 105;// 低温告警
	public static final int ALARM_TYPE_INNERERRORALARM = 106;// 内部错误告警
	public static final int ALARM_TYPE_STORAGEFAULT = 107;// 存储故障告警
	public static final int ALARM_TYPE_WATERTEMPRATURESENSORFAULT = 108;// 水温传感器故障告警
	public static final int ALARM_TYPE_COMMUNICATIONALARM = 109;// 通讯异常告警
	public static final int TWO = 2;
	
	public static final String DataStream_bddata  = "3200_0_5750";
	public static final String DataStream_trip  = "3342_0_5850";

}
