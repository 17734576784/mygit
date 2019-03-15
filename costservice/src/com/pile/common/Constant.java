/**   
* @Title: Constant.java 
* @Package com.pile.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年3月26日 下午5:22:18 
* @version V1.0   
*/
package com.pile.common;

/**
 * @ClassName: Constant
 * @Description: 常量定义类
 * @author dbr
 * @date 2018年3月26日 下午5:22:18
 * 
 */
public class Constant {

	/** 优惠券折扣 */
	public static final int DISCOUNT_BYCOUPON = 0;
	/** 打折折扣 */
	public static final int DISCOUNT_BYPERCENT = 1;
	/** 充值赠费折扣 */
	public static final int DISCOUNT_BYCHARGEFEE = 2;

	
	/** redis中折扣类型key */
	public static final String DISCOUNT_TYPE = "Diction:折扣策略_500";

	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNT_PERCENT = 0;

	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNT_PRICE = 1;
	
	
	/** 折扣方式1  总金额折扣    2  服务费折扣  rcdxxx*/
	public static final int DISCOUNT_ALL_MONEY = 1;

	/** 折扣方式1  总金额折扣    2  服务费折扣  rcdxxx */
	public static final int DISCOUNT_SERVICE = 2;

	/** 充电单信息前缀 */
	public static final String ORDER = "order:";

	/** 推送标志 已推送 */
	public static final byte PUSHED = 1;

	/** 推送标志 未推送 */
	public static final byte NOPUSH = 0;

	/** 充电方式--即冲即退 */
	public static final int FLUSRE_TREAT = 1;
	/** 充电方式 账户余额 */
	public static final int BALANCE_CHARGING = 2;
	
	/** 充电记录队列Key */
	public static final String COST_QUEUE = "MEM_INF_MSG_SETTLE";
	
	/** 充电记录队列Key */
	public static final String BAK_COST_QUEUE =  "BakCostQueue";
	
	/** 充电记录队列Key */
	public static final String ERROR_COST_QUEUE =  "ErrorCostQueue";
	
	/** App退款队列 key */
	public static final String APPR_EFUND_QUEUE = "appCloseAccountQueue";

	/** 小程序退款队列 key */
	public static final String WX_REFUND_QUEUE = "wxminiCloseAccountQueue";
	
	/** 充电单 算费状态 */
	public static final int CALCULATED = 7;

	/** 长度32 */
	public static final int LEN_32 = 32;
	
	/** 操作类型 0:充电支出 1:充电退款 2：充值收入 3:充值退款 4:转账收入 5:转账支出 6:预约支出 7:预约退款 */
	public static final int TRANSFER_INCOME = 4;
	
	/** netty服务端口号 */
	public static final int NETTY_PORT = 19999;
	
	/** 接收标志 成功 1 */
	public static final int SUCCESS = 1;
	
	/** 接收标志 失败 0 */
	public static final int FAILED = 0;
	
	/** 1:APP 2:微信小程序 3:接口 */
	public static final int INTERFACE = 3;
	
	/** 未算费 */
	public static final int CALCULATION_INITIAL = 0;
	/** 算费成功 */
	public static final int CALCULATION_SUCCESS = 1;
	/** 算费失败 */
	public static final int CALCULATION_FAILED = 2;
	/** 已算费 */
	public static final int CALCULATION_FINISHED = 3;
	
	/** 未使用 */
	public static final int NO_USE = 0;
	/** 已使用 */
	public static final int USED = 1;
	
 }
