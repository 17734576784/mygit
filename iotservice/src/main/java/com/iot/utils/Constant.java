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
package com.iot.utils;

import java.io.File;

public class Constant {

    //please replace the IP and Port, when you use the demo.
//    public static final String BASE_URL = "https://180.101.147.89:8743";
    public static final String BASE_URL = "https://58.240.96.46:8743";


    //please replace the appId and secret, when you use the demo.
//    public static final String APPID = "fn5I2QJuIRFJxsrXXbUzWXn0S3Ya";
//    public static final String SECRET = "9IL8rmcruUPIbth4gRP1XGAns2Ya";
    public static final String APPID = "YXnpwWndC3ioc15OGALXZvHLB1oa";
    public static final String SECRET = "uOL_C0yP6pl4Gals5dJkrfF1vSka";

    /*
     *IP and port of callback url.
     *please replace the IP and Port of your Application deployment environment address, when you use the demo.
     */
//    public static final String CALLBACK_BASE_URL = "https://129.28.69.163:8443";
    public static final String CALLBACK_BASE_URL = "https://129.28.69.163:443";


    /*
     * complete callback url锛�
     * please replace uri, when you use the demo.
     */
	public static final String DEVICE_ADDED_CALLBACK_URL = CALLBACK_BASE_URL + "/addDevice";
	public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/updateDeviceInfo";
	public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/updateDeviceData";
	public static final String DEVICE_DELETED_CALLBACK_URL = CALLBACK_BASE_URL + "/deletedDevice";
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
	public static final String REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL + "/reportCmdExecResult";

	public static String realBasepath = new File(Constant.class.getResource("/").getPath()).getParent();

	//Paths of certificates.
	public static String SELFCERTPATH = "cert/CertwithKey.pkcs12";
	public static String TRUSTCAPATH = "cert/ca.jks";
    
    //Password of certificates."classpath:cert\\outgoing.CertwithKey.pkcs12"
    public static String SELFCERTPWD = "IoM@1234";
    public static String TRUSTCAPWD = "Huawei@123";






    //*************************** The following constants do not need to be modified *********************************//

    /*
     * request header
     * 1. HEADER_APP_KEY
     * 2. HEADER_APP_AUTH
     */
    public static final String HEADER_APP_KEY = "app_key";
    public static final String HEADER_APP_AUTH = "Authorization";
    
    /*
     * Application Access Security:
     * 1. APP_AUTH
     * 2. REFRESH_TOKEN
     */
    public static final String APP_AUTH = BASE_URL + "/iocm/app/sec/v1.1.0/login";
    public static final String REFRESH_TOKEN = BASE_URL + "/iocm/app/sec/v1.1.0/refreshToken";
    

    /*
     * Device Management:
     * 1. REGISTER_DEVICE
     * 2. MODIFY_DEVICE_INFO
     * 3. QUERY_DEVICE_ACTIVATION_STATUS
     * 4. DELETE_DEVICE
     * 5. DISCOVER_INDIRECT_DEVICE
     * 6. REMOVE_INDIRECT_DEVICE
     */
    public static final String REGISTER_DEVICE = BASE_URL + "/iocm/app/reg/v1.1.0/devices";
    public static final String MODIFY_DEVICE_INFO = BASE_URL + "/iocm/app/dm/v1.1.0/devices";
    public static final String QUERY_DEVICE_ACTIVATION_STATUS = BASE_URL + "/iocm/app/reg/v1.1.0/devices";
    public static final String DELETE_DEVICE = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String DISCOVER_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String REMOVE_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

    /*
     * Data Collection:
     * 1. QUERY_DEVICES
     * 2. QUERY_DEVICE_DATA
     * 3. QUERY_DEVICE_HISTORY_DATA
     * 4. QUERY_DEVICE_CAPABILITIES
     * 5. SUBSCRIBE_NOTIFYCATION
     */
    public static final String QUERY_DEVICES = BASE_URL + "/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_DATA = BASE_URL + "/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_HISTORY_DATA = BASE_URL + "/iocm/app/data/v1.1.0/deviceDataHistory";
    public static final String QUERY_DEVICE_CAPABILITIES = BASE_URL + "/iocm/app/data/v1.1.0/deviceCapabilities";
    public static final String SUBSCRIBE_NOTIFYCATION = BASE_URL + "/iocm/app/sub/v1.1.0/subscribe";
    
    
    /*
     * Signaling Delivery锛�
     * 1. POST_ASYN_CMD
     * 2. QUERY_DEVICE_CMD
     * 3. UPDATE_ASYN_COMMAND
     * 4. CREATE_DEVICECMD_CANCEL_TASK
     * 5. QUERY_DEVICECMD_CANCEL_TASK
     *
     */
    public static final String POST_ASYN_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String QUERY_DEVICE_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String UPDATE_ASYN_COMMAND = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands/%s";
    public static final String CREATE_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String QUERY_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";


    /*
     * notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
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
    
	public static final String UPLOAD_PIC = "1";
	/** 上传图片url */
	public static final String UPLOAD_IMAGE_URL = "nbNotifyAction!notifyToWeb.action";

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
	
	/** redis 设备升级进度key前缀 */
	public static final String PROGRESS = "progress_";
	
	/** redis 命令idkey前缀 */
	public static final String COMMAND = "command_";
	
	/**询问设备是否升级服务名*/
	public static String UPVERSIONSERVICE = "UpversionService";
	
	/**询问设备是否升级方法名*/
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
	
}
