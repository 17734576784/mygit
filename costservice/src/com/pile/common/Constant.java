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
	public static final int DISCOUNTBYCOUPON = 0;
	/** 打折折扣 */
	public static final int DISCOUNTBYPERCENT = 1;
	/** 充值赠费折扣 */
	public static final int DISCOUNTBYCHARGEFEE = 2;

	/** redis中折扣类型key */
	public static final String DISCOUNTTYPE = "Diction:折扣策略_500";

	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNTPERCENT = 0;

	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNTPRICE = 1;

	/** 充电单信息前缀 */
	public static final String ORDER = "order:";

	/** 推送标志 已推送 */
	public static final byte PUSHED = 1;

	/** 推送标志 未推送 */
	public static final byte NOPUSH = 0;

	/** 充电方式--即冲即退 */
	public static final int FLUSRETREAT = 1;
	/** 充电方式 账户余额 */
	public static final int BALANCECHARGING = 2;
	
	/** 充电记录队列Key */
	public static final String COSTQUEUE = "CostQueue";
	
	/** 充电记录队列Key */
	public static final String BAKCOSTQUEUE =  "BakCostQueue";
	
	/** 充电记录队列Key */
	public static final String ERRORCOSTQUEUE =  "ErrorCostQueue";
	
	/** App退款队列 key */
	public static final String APPREFUNDQUEUE = "appCloseAccountQueue";

	/** 小程序退款队列 key */
	public static final String WXREFUNDQUEUE = "wxminiCloseAccountQueue";
	
	/** 充电单 算费状态 */
	public static final int CALCULATED = 7;

	/** 长度32 */
	public static final int LEN_32 = 32;
	
	/** 操作类型 0:充电支出 1:充电退款 2：充值收入 3:充值退款 4:转账收入 5:转账支出 6:预约支出 7:预约退款 */
	public static final int TRANSFERINCOME = 4;
	
	/** netty服务端口号 */
	public static final int NETTYPORT = 19999;
	
	
 }
