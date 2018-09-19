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

	/** 充电记录队列Key */
	public static final String ORDERMAP = "ORDERMAP";

	public static final String DISCOUNTTYPE = "Diction:折扣策略_500";

	public static final int DELAYCOUPONLIMIT = 7;

	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNTPERCENT = 0;
	/** 折扣类型 0:百分比 1:折价 rcd50 */
	public static final int DISCOUNTPRICE = 1;
	/** redis中折扣类型key */
	public static final String STRATEGYDICTION = "Diction:500";

}
