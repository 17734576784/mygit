package com.wo.util;

public class Constant {

	//交易流水号长度
	public static int SERIALNUMBER_LENGTH = 12;

	
	public static String OK = "0";
	public static String Err = "-1";
	public static String TIMEOUT = "-2";

	public static String EMPTY = "";
	public static String SUCCESS = "success";
	public static String FAIL = "fail";
	public static String EXIST = "exist";
	public static String TRUE = "true";
	public static String FALSE = "false";
	
	// 错误码定义
	public static int STATUS_OK = 0;
	public static int STATUS_REQUEST_ERROR = -1;
	public static int STATUS_CERTIFICATION_ERROR = -2;

 
	//充电桩交直流类型
	public static final int PILECURRENTTYPE_AC			= 0;	//交流桩	
	public static final int PILECURRENTTYPE_DC			= 1;  	//直流桩
	
	//充电枪状态
	public static final byte GUN_STATE_NULL  			= 0;	//无设置
	public static final byte GUN_STATE_FREE  			= 1;    //空闲
	public static final byte GUN_STATE_CONNECTION  		= 2;    //连接
	public static final byte GUN_STATE_CHARGING  		= 3;    //充电
	public static final byte GUN_STATE_ORDER  			= 4;    //预约,暂时去掉，库中加字段了
	public static final byte GUN_STATE_ALARM  			= 5;    //报警	
	public static final byte GUN_STATE_BREAKDOWN  		= 51;   //故障
	public static final byte GUN_STATE_REPAIR  			= 52;   //维修
	public static final byte GUN_STATE_OUTLINE  		= 53;   //离线
	public static final byte GUN_STATE_WAITING			= 55;	//准备充电
	public static final byte GUN_STATE_NORIGHT  		= 101;  //无访问权限
	
	public static final int ELECTRIC_VEHICLE = 1; //电动汽车
	public static final int ELECTRIC_BICYCLE = 0; //电动汽车

	public static final int CHARGE_START = 1; //开始充电
	public static final int CHARGE_OVER = 2; //结束充电

	//使用客户端类型
	public static final byte CLIENTTYPE_APP = 1;		//科林充电APP	
	public static final byte CLIENTTYPE_WXSMALL = 2;	//微信小程序	
	public static final byte CLIENTTYPE_INTERFACE = 3;	//接口充电	
	
	public static final int NOCP = 0; //无cp信号
	public static final int HAVECP = 1; //有cp信号
	
	//实充实退方式，充电状态
	//0 初始态  1支付成功, 2等待充电 , 3正在充电 ,4 充电失败, 5 等待充电结束, 6 充电结束 , 7 退款成功, 8 退款失败 , 9转存成功  
	public static final byte CHARGINGSTATE_V2_INIT 			= 0;	//初始态		--正常流程关闭态
	public static final byte CHARGINGSTATE_V2_PAYSUCCESS 	= 1;	//支付成功             	
	public static final byte CHARGINGSTATE_V2_STARTWAITING 	= 2;    //等待充电		
	public static final byte CHARGINGSTATE_V2_CHARGING 		= 3;    //正在充电		
	public static final byte CHARGINGSTATE_V2_STARTFAIL 	= 4;    //充电失败
	public static final byte CHARGINGSTATE_V2_ENDWAITING	= 5; 	//等待充电结束
	public static final byte CHARGINGSTATE_V2_END 			= 6;    //充电结束		
	public static final byte CHARGINGSTATE_V2_REFUNDSUCCESS = 7;    //退款成功		--正常流程关闭态
	public static final byte CHARGINGSTATE_V2_REFUNDFAIL 	= 8;    //退款失败
	public static final byte CHARGINGSTATE_V2_TRANSFER 		= 9;    //转存成功		--正常流程关闭态
	
	public static final int ZERO = 0;
	/**未推送标志*/
	public static final byte NOPUSH = 0;
	/**已推送标志*/
	public static final byte PUSHED = 1;
	
	/** 单费率 */
	public static final int SINGLERATE = 1;
	/** 复费率 */
	public static final int COMPLEXRATE = 4;
	/*充电交易流水号长度*/
	public static final int PAY_SERIALNUMBER_LENGTH = 12;
	/*充电流水号长度*/
	public static final int CHARGE_SERIALNUMBER_LENGTH = 10;
	/*充电交易流水号前缀位数*/
	public static final int CHARGE_SERIALNUMBER_PREFIX = 2;
	
	/**默认重试次数*/
	public static final int RETRY = 3;
}

