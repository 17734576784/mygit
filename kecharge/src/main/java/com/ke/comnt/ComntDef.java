/**   
* @Title: ComntDef.java 
* @Package com.keicpms.communite 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhp
* @date 2018年12月13日 下午5:02:28 
* @version V1.0   
*/
package com.ke.comnt;

/**
 * @ClassName: ComntDef
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhp
 * @date 2018年12月13日 下午5:02:28
 * 
 */
public class ComntDef {
	// 字符长度预定义
	public static final int YD_8_STRLEN = 8; // 8字节字符
	public static final int YD_16_STRLEN = 16; // 16字节字符
	public static final int YD_32_STRLEN = 32; // 32字节字符
	public static final int YD_64_STRLEN = 64; // 64字节字符

	public static final int FALSE = 0;
	public static final int TRUE = 1;

	public static final int NO = 0;
	public static final int YES = 1;

	// 用电系统规约定义
	public static final int YD_PROTOCAL_NULL = 0; // 无效
	public static final int YD_PROTOCAL_GZSOUTH = 1; // 广州南方电网

	public static final int YD_PROTOCAL_GD376 = 3; // 国电376.1-2009规约
	public static final int YD_PROTOCAL_GD376_2013 = 6; // 国电376.1-2013规约

	// 用电系统终端类型定义
	public static final int YD_RTUTYPE_JCCOMM = 104; // 居民集抄
	public static final int YD_RTUTYPE_JCKLD = 101;

	// 计量点对象类型
	public static final int YD_JLPOBJTYPE_METER = 0; // 电表/交流采样
	public static final int YD_JLPOBJTYPE_PULSE = 1; // 脉冲量
	public static final int YD_JLPOBJTYPE_MNL = 2; // 模拟量
	public static final int YD_JLPOBJTYPE_OTHER = 3; // 其它

	// 居民用户类型
	public static final int YD_CONSTYPE_COMMON = 0; // 普通用户
	public static final int YD_CONSTYPE_IMPORTANT = 1; // 重点用户
	public static final int YD_CONSTYPE_TOTAL = 2; // 总表

	// 用电规约项所属对象类型
	public static final int YD_PROTOBJYPE_RTU = 0; // 终端
	public static final int YD_PROTOBJYPE_JLP = 1; // 计量点
	public static final int YD_PROTOBJYPE_ZJG = 2; // 总加组
	public static final int YD_PROTOBJYPE_CLC = 3; // 控制轮次
	public static final int YD_PROTOBJYPE_TSK = 4; // 任务号
	public static final int YD_PROTOBJYPE_ZML = 5; // 直流模拟量
	public static final int YD_PROTOBJYPE_ALM = 6; // 报警编码

	// 用电规约项数据类型
	public static final int YD_PROTDATACLASS_PARA = 0; // 参数
	public static final int YD_PROTDATACLASS_CTRL = 5; // 控制
	public static final int YD_PROTDATACLASS_REALDATA = 10; // 实时数据
	public static final int YD_PROTDATACLASS_MINDATA = 11; // 分钟/小时数据
	public static final int YD_PROTDATACLASS_DAYDATA = 12; // 日数据及统计数据
	public static final int YD_PROTDATACLASS_MONDATA = 13; // 月数据及统计数据
	public static final int YD_PROTDATACLASS_CBDAYDATA = 16; // 抄表日数据
	public static final int YD_PROTDATACLASS_ALARM = 20; // 事项数据
	public static final int YD_PROTDATACLASS_ERR = 21; // 错误警告
	public static final int YD_PROTDATACLASS_OTHER = 30; // 其它

	// 通讯介质
	public static final int YD_COMMDEV_COM = 0; // 串口
	public static final int YD_COMMDEV_MODEM = 1; // MODEM
	public static final int YD_COMMDEV_GSM = 2; // GSM
	public static final int YD_COMMDEV_UDP = 3; // UDP
	public static final int YD_COMMDEV_TCP_SRV = 4; // TCP服务端
	public static final int YD_COMMDEV_TCP_CLT = 5; // TCP客户端
	public static final int YD_COMMDEV_TCPCLT_GROUP = 6; // TCP客户端组

	// 终端在线类型
	public static final int YD_NETONLINETYPE_REALLINE1 = 0; // 实时在线(IP固定/发心跳)
	public static final int YD_NETONLINETYPE_REALLINE2 = 1; // 实时在线(IP固定/不发心跳)
	public static final int YD_NETONLINETYPE_REALLINE3 = 2; // 实时在线(IP不固定/发心跳)
															// 用于TCP客户端方式

	// 通道通讯状态
	public static final int YD_CHAN_COMMSTATE_NULL = 0; // 无-不在此前置运行
	public static final int YD_CHAN_COMMSTATE_NORMAL = 1; // 正常
	public static final int YD_CHAN_COMMSTATE_ERR = 2; // 异常

	// 终端通讯状态
	public static final int YD_RTU_COMMSTATE_NULL = 0; // 无-不在此前置运行
	public static final int YD_RTU_COMMSTATE_NORMAL = 1; // 正常
	public static final int YD_RTU_COMMSTATE_UNKNOWN = 2; // 未知
	public static final int YD_RTU_COMMSTATE_ERR = 3; // 不在线

	// 终端任务状态
	public static final int YD_RTU_RUNSTATE_IDLE = 0; // 空闲
	public static final int YD_RTU_RUNSTATE_AUTO = 1; // 自动任务
	public static final int YD_RTU_RUNSTATE_MAN = 2; // 手工任务

	// WEB 消息类型
	public static final int YD_WEBMSGTYPE_NULL = 0; // 无
	public static final int YD_WEBMSGTYPE_TASK = 1; // 执行任务
	public static final int YD_WEBMSGTYPE_TASKRESULT = 2; // 任务执行结果
	public static final int YD_WEBMSGTYPE_DATA = 7; // 任务数据
	public static final int YD_WEBMSGTYPE_RAWDATA = 8; // 通讯报文
	public static final int YD_WEBMSGTYPE_CANCELMANTASK = 9; // 取消手工任务
	public static final int YD_WEBMSGTYPE_QUERYRTUSTATE = 21; // 请求终端状态
	public static final int YD_WEBMSGTYPE_RTUSTATE = 22; // 返回终端状态
	public static final int YD_WEBMSGTYPE_RELOADPARA = 32; // 重载参数
	public static final int YD_WEBMSGTYPE_SYSEVENT = 41; // 系统事项

	// 充电桩消息类型
	// public static final int EVCP_WEBMSGTYPE_APP_QRYSTATE = 50; //请求充电枪状态
	public static final int EVCP_WEBMSGTYPE_APP_CHARGE = 51; // 发送充电指令
	public static final int EVCP_WEBMSGTYPE_APP_RECHARGE = 52; // 发送充值指令
	public static final int EVCP_WEBMSGTYPE_APP_ORDER = 53; // 发送预约充电指令
	public static final int EVCP_WEBMSGTYPE_APP_TRADE_PUSH = 54; // 充电消息推送
	public static final int EVCP_WEBMSGTYPE_APP_EVENT_PUSH = 55; // 事项消息推送

	public static final byte EVCP_WEBMSGTYPE_APP_CHARGE_WITH_PAY = 71; // 发送充电指令Version2
	public static final byte EVCP_WEBMSGTYPE_APP_CHARGE_CAPITAL = 72; // 发送充电指令-加盟商资金池
	public static final byte EVCP_WEBMSGTYPE_APP_CHARGE_HYDROPWER = 75; // 发送充电指令-加盟商资金池

	// 系统消息类型
	public static final int YD_MSGTYPE_NULL = 0;
	public static final int YD_MSGTYPE_TASK = 1; // 执行任务
	public static final int YD_MSGTYPE_TASKRESULT = 2; // 执行任务结果
	public static final int YD_MSGTYPE_TASKSTATE = 3; // 任务状态
	public static final int YD_MSGTYPE_CHANSTATE = 4; // 通道状态
	public static final int YD_MSGTYPE_RTUSTATE = 5; // 终端状态
	public static final int YD_MSGTYPE_CJSTATE = 6; // 采集状态
	public static final int YD_MSGTYPE_DATA = 7; // 数据
	public static final int YD_MSGTYPE_RAWDATA = 8; // 通讯报文
	public static final int YD_SGTYPE_CANCELMANTASK = 9; // 取消手工任务

	// 任务应用类型
	public static final int YD_TASKAPPTYPE_NULL = 0; //
	public static final int YD_TASKAPPTYPE_CALLREALDATA = 1; // 召实时数据
	public static final int YD_TASKAPPTYPE_CALLMINDATA = 2; // 召分钟数据
	public static final int YD_TASKAPPTYPE_CALLDAYDATA = 3; // 召日数据
	public static final int YD_TASKAPPTYPE_CALLMONTHDATA = 4; // 召月数据
	public static final int YD_TASKAPPTYPE_CALLCBDAYDATA = 5; // 召抄表日数据
	public static final int YD_TASKAPPTYPE_TASKBZ = 10; // 任务数据补召
	public static final int YD_TASKAPPTYPE_CALLEVENTDATA = 15; // 召事项数据
	public static final int YD_TASKAPPTYPE_CALLEXTDATA = 20; // 召扩展数据
	public static final int YD_TASKAPPTYPE_CALLPARA = 30; // 召参数
	public static final int YD_TASKAPPTYPE_SETPARA = 35; // 设参数
	public static final int YD_TASKAPPTYPE_CTRL = 40; // 控制
	public static final int YD_TASKAPPTYPE_TC = 50; // 透传
	public static final int YD_TASKAPPTYPE_DIAL = 60; // 拨号
	public static final int YD_TASKAPPTYPE_GSMMSG = 70; // 发短信

	// 用电任务对象范围
	public static final int YD_TASKOBJSCOPE_1_POINT = 0; // 单点任务
	public static final int YD_TASKOBJSCOPE_ALL_JLP = 1; // 所有计量点任务
	public static final int YD_TASKOBJSCOPE_ALL_ZJG = 2; // 所有总加组任务
	public static final int YD_TASKOBJSCOPE_ALL_METER = 3; // 所有电表计量点任务
	public static final int YD_TASKOBJSCOPE_CONS_COMMON = 4; // 所有普通用户任务
	public static final int YD_TASKOBJSCOPE_CONS_IMPORTJLP = 5; // 所有重点用户任务
	public static final int YD_TASKOBJSCOPE_CONS_TOTALMETER = 6; // 所有总表任务
	public static final int YD_TASKOBJSCOPE_CONS_IMPTT = 7; // 所有重点用户和总表任务

	// 任务分配类型
	public static final int YD_TASKASSIGNTYPE_AUTO = 0; // 自动任务
	public static final int YD_TASKASSIGNTYPE_MAN = 1; // 手工任务

	// 任务运行结果
	public static final int YD_TASKRESULT_NULL = 0; // 任务结果初始态
	public static final int YD_TASKRESULT_SUCCEED = 1; // 任务结果成功
	public static final int YD_TASKRESULT_FAILED = 2; // 任务结果失败

	// 任务运行状态
	public static final int YD_TASKSTATE_STOP = 0; // 任务状态：停止态
	public static final int YD_TASKSTATE_WAIT = 1; // 任务状态：等待态
	public static final int YD_TASKSTATE_RUN = 2; // 任务状态：运行态

	// 任务返回数据类型
	public static final int YD_TASK_BACKDATA_NO = 0; // 不返回数据
	public static final int YD_TASK_BACKDATA_SRC = 1; // 返回给发送端
	public static final int YD_TASK_BACKDATA_DATASAVE = 2; // 返回给数据存储程序
	public static final int YD_TASK_BACKDATA_SRC_DATASAVE = 3; // 返回给发送端和数据存储程序

	// 任务执行错误码
	public static final int YD_TASKERR_OK = 0; // 正确
	public static final int YD_TASKERR_PARA = 1; // 参数不匹配
	public static final int YD_TASKERR_MSG = 2; // 消息发送错误
	public static final int YD_TASKERR_WAIT_TIMEOUT = 3; // 等待超时
	public static final int YD_TASKERR_COMNT_TIMEOUT = 4; // 通讯超时
	public static final int YD_TASKERR_TASKBUF_FULL = 5; // 任务缓存满
	public static final int YD_TASKERR_CHAN_UNVALID = 6; // 通道无效
	public static final int YD_TASKERR_CHAN_COMNT = 7; // 通道通讯错误
	public static final int YD_TASKERR_RTU_UNVALID = 8; // 终端无效
	public static final int YD_TASKERR_RTU_COMNT = 9; // 终端通讯错误
	public static final int YD_TASKERR_MAKEFRAME = 10; // 组帧失败
	public static final int YD_TASKERR_RTU_NOREPLY = 11; // 终端无应答
	public static final int YD_TASKERR_CANCEL = 12; // 任务被取消
	public static final int YD_TASKERR_TASKSTATE = 13; // 任务状态错误
	public static final int YD_TASKERR_TASKPARA = 14; // 任务参数错误
	public static final int YD_TASKERR_ENDBYFORCE = 15; // 任务被强制结束

	public static final int EVCP_TASKERR_CAROWNER = 51; // 车主信息错误
	public static final int EVCP_TASKERR_PILEPARA = 52; // 充电桩参数错误
	public static final int EVCP_TASKERR_GUNUSE = 53; // 充电枪占用
	public static final int EVCP_TASKERR_WATERNO = 54; // 流水号错误

	public static final int YD_TASKERR_OTHER = 100; // 其他错误

	public static final String getErrCodeString(int errcode) {
		String ret_str = "未知错误";
		switch (errcode) {
		case YD_TASKERR_OK:
			ret_str = "正确";
			break;
		case YD_TASKERR_PARA:
			ret_str = "参数不匹配";
			break;
		case YD_TASKERR_MSG:
			ret_str = "消息发送错误";
			break;
		case YD_TASKERR_WAIT_TIMEOUT:
			ret_str = "等待超时";
			break;
		case YD_TASKERR_COMNT_TIMEOUT:
			ret_str = "通讯超时";
			break;
		case YD_TASKERR_TASKBUF_FULL:
			ret_str = "任务缓存满";
			break;
		case YD_TASKERR_CHAN_UNVALID:
			ret_str = "通道无效";
			break;
		case YD_TASKERR_CHAN_COMNT:
			ret_str = "通道通讯错误";
			break;
		case YD_TASKERR_RTU_UNVALID:
			ret_str = "终端无效";
			break;
		case YD_TASKERR_RTU_COMNT:
			ret_str = "终端通讯错误";
			break;
		case YD_TASKERR_MAKEFRAME:
			ret_str = "组帧失败";
			break;
		case YD_TASKERR_RTU_NOREPLY:
			ret_str = "终端无应答";
			break;
		case YD_TASKERR_CANCEL:
			ret_str = "任务被取消";
			break;
		case YD_TASKERR_TASKSTATE:
			ret_str = "任务状态错误";
			break;
		case YD_TASKERR_TASKPARA:
			ret_str = "任务参数错误";
			break;
		case YD_TASKERR_ENDBYFORCE:
			ret_str = "任务被强制结束";
			break;
		case EVCP_TASKERR_CAROWNER:
			ret_str = "车主信息错误";
			break;
		case EVCP_TASKERR_PILEPARA:
			ret_str = "充电桩参数错误";
			break;
		case EVCP_TASKERR_GUNUSE:
			ret_str = "充电枪占用";
			break;
		case EVCP_TASKERR_WATERNO:
			ret_str = "流水号错误";
			break;
		case YD_TASKERR_OTHER:
			ret_str = "其他错误";
			break;
		}
		return ret_str;
	}

	public static final String YD_TASKPROC_JWEBSERVICE_NAME = "java后台";

	// 任务进程代码
	public static final int YD_TASKPROC_NULL = 0; // 无
	public static final int YD_TASKPROC_FRONT = 1; // 前置机
	public static final int YD_TASKPROC_TASGN = 2; // 任务分配
	public static final int YD_TASKPROC_CWEBSERVICE = 3; // C++ WebService
	public static final int YD_TASKPROC_JWEBSERVICE = 10; // java WebService

	public static final String getErrProcString(int errproc) {
		String ret_str = "未知进程";
		switch (errproc) {
		case YD_TASKPROC_NULL:
			ret_str = "无";
			break;
		case YD_TASKPROC_FRONT:
			ret_str = "前置机进程";
			break;
		case YD_TASKPROC_TASGN:
			ret_str = "任务进程";
			break;
		case YD_TASKPROC_CWEBSERVICE:
			ret_str = "WEB通信进程";
			break;
		case YD_TASKPROC_JWEBSERVICE:
			ret_str = "java后台";
			break;
		}
		return ret_str;
	}
}
