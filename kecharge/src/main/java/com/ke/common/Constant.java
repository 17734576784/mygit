/**   
* @Title: Constant.java 
* @Package com.ke.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 上午10:14:07 
* @version V1.0   
*/
package com.ke.common;

/**
 * @ClassName: Constant
 * @Description: 常量定义类
 * @author dbr
 * @date 2018年12月21日 上午10:14:07
 * 
 */
public class Constant {

	public static String SELFCERTPATH = "";

	public static String TRUSTCAPATH = "";

	public static String SELFCERTPWD = "";

	public static String TRUSTCAPWD = "";

	/** cache redis前缀 */
	public static String CACHE_PREFIX = "cache:";

	/** cache redis 缓存有效期 */
	public static int CACHE_TIME_OUT = 7200;

	/** session redis前缀 */
	public static String SESSION_PREFIX = "session:";

	/** session redis 缓存有效期 */
	public static int SESSION_TIME_OUT = 600;
	
	/** session:token redis前缀 */
	public static String SESSION_TOKEN_PREFIX = "session:token:";

	/** Token redis前缀 */
	public static String TOKEN_PREFIX = "token:";

	/** 返回结果码描述 */
	public static String RESULT_CODE = "status";

	/** 返回结果原因描述 */
	public static String RESULT_DETAIL = "error";
	/** 返回结果码 成功 */
	public static int SUCCESS = 0;
	/** 返回结果码 请求错误 */
	public static int REQUEST_BAD = -1;
	/** 返回结果码 登录超时 */
	public static int TIME_OUT = -2;

	/** 流水号前缀前缀 */
	public static String SERIALNUMBER_PREFIX ="Serialnumber_Prefix";

	// 充电桩交直流类型
	public static final int PILECURRENTTYPE_AC = 0; // 交流桩
	public static final int PILECURRENTTYPE_DC = 1; // 直流桩

	// 充电枪状态
	public static final byte GUN_STATE_NULL = 0; // 无设置
	public static final byte GUN_STATE_FREE = 1; // 空闲
	public static final byte GUN_STATE_CONNECTION = 2; // 连接
	public static final byte GUN_STATE_CHARGING = 3; // 充电
	public static final byte GUN_STATE_ORDER = 4; // 预约,暂时去掉，库中加字段了
	public static final byte GUN_STATE_ALARM = 5; // 报警
	public static final byte GUN_STATE_BREAKDOWN = 51; // 故障
	public static final byte GUN_STATE_REPAIR = 52; // 维修
	public static final byte GUN_STATE_OUTLINE = 53; // 离线
	public static final byte GUN_STATE_WAITING = 55; // 准备充电
	public static final byte GUN_STATE_NORIGHT = 101; // 无访问权限

	public static final int ELECTRIC_VEHICLE = 1; // 电动汽车
	public static final int ELECTRIC_BICYCLE = 0; // 电动汽车

	public static final int CHARGE_START = 1; // 开始充电
	public static final int CHARGE_OVER = 2; // 结束充电

	// 使用客户端类型
	public static final byte CLIENTTYPE_APP = 1; // 科林充电APP
	public static final byte CLIENTTYPE_WXSMALL = 2; // 微信小程序
	public static final byte CLIENTTYPE_INTERFACE = 3; // 接口充电

	public static final int NOCP = 0; // 无cp信号
	public static final int HAVECP = 1; // 有cp信号

	// 实充实退方式，充电状态
	// 0 初始态 1支付成功, 2等待充电 , 3正在充电 ,4 充电失败, 5 等待充电结束, 6 充电结束 , 7 退款成功, 8 退款失败 ,
	// 9转存成功
	public static final byte CHARGINGSTATE_V2_INIT = 0; // 初始态 --正常流程关闭态
	public static final byte CHARGINGSTATE_V2_PAYSUCCESS = 1; // 支付成功
	public static final byte CHARGINGSTATE_V2_STARTWAITING = 2; // 等待充电
	public static final byte CHARGINGSTATE_V2_CHARGING = 3; // 正在充电
	public static final byte CHARGINGSTATE_V2_STARTFAIL = 4; // 充电失败
	public static final byte CHARGINGSTATE_V2_ENDWAITING = 5; // 等待充电结束
	public static final byte CHARGINGSTATE_V2_END = 6; // 充电结束
	public static final byte CHARGINGSTATE_V2_REFUNDSUCCESS = 7; // 退款成功
	public static final byte CHARGINGSTATE_V2_REFUNDFAIL = 8; // 退款失败
	public static final byte CHARGINGSTATE_V2_TRANSFER = 9; // 转存成功 --正常流程关闭态

	public static final int ZERO = 0;
	/** 未推送标志 */
	public static final byte NOPUSH = 0;
	/** 已推送标志 */
	public static final byte PUSHED = 1;

	/** 单费率 */
	public static final int SINGLERATE = 0;
	/** 复费率 */
	public static final int COMPLEXRATE = 1;
	/* 充电交易流水号长度 */
	public static final int PAY_SERIALNUMBER_LENGTH = 12;
	/* 充电流水号长度 */
	public static final int CHARGE_SERIALNUMBER_LENGTH = 10;
	/* 充电交易流水号前缀位数 */
	public static final int CHARGE_SERIALNUMBER_PREFIX = 2;

	/** 默认重试次数 */
	public static final int RETRY = 3;

	/** 终端在线key前缀 */
	public static final String RTUSTATE = "rtustate:";
	
	/** 充电方式--即冲即退 */
	public static final int FLUSRE_TREAT = 1;
	/** 充电方式 账户余额 */
	public static final int BALANCE_CHARGING = 2;
	
	/** 充电方式 接口 账户余额 */
	public static final int INTERFACE_CHARGING = 3;
	
	// 1:金额 2:电量
	public static final int PAY_MOENY = 1;
	public static final int PAY_AMOUNT = 2;

	/** 充电单redis前缀 */
	public static final String ORDER = "order:";
	
	/** 充电枪状态redis前缀 */
	public static final String GUNSTATE = "gunstate:";
	
	/** 充电超时时间默认60秒 */
	public static final int CHARGE_TIMEOUT = 60;
	
	/** 运营商配置reids前缀 */
	public static final String OPERATORCONFIG_PREFIX = "OperatorConfig:"; 
	
	/** 程序运行元年 */
	public static final int INIT_YEAR = 2019;
	
	
	/** 充电结束原因reids */
	public static final String ENDCAUSE_DICTION = "Diction:充电结束原因_235";

	public static final int REDIS_TIMEOUT = 0;
	/** 充电开始推送队列 */
	public static final String CHARGE_START_QUEUE = "CHARGE_START_QUEUE";
	/** 充电结束推送队列 */
	public static final String CHARGE_OVER_QUEUE = "CHARGE_OVER_QUEUE";
	/** 充电SOC推送队列 */
	public static final String CHARGE_SOC_QUEUE = "CHARGE_SOC_QUEUE";

	
}
