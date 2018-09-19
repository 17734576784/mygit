/**   
* @Title: DiscoutTypeEnum.java 
* @Package com.pile.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年5月29日 下午3:11:28 
* @version V1.0   
*/
package com.pile.common;

/** 
* @ClassName: DiscoutTypeEnum 
* @Description: 
* @author dbr
* @date 2018年5月29日 下午3:11:28 
*  
*/
public enum DiscoutTypeEnum {

	/** 
	* @Fields Coupon : 优惠券折扣 
	*/ 
	Coupon(0,"com.pile.strategy.DiscountByCouponStrategy"),
	/** 
	* @Fields Percent : 打折折扣
	*/ 
	Percent(1,"com.pile.strategy.DiscountByPercentStrategy"),
	/** 
	* @Fields ChargeFee : 充值赠费折扣
	*/ 
	ChargeFee(2,"com.pile.strategy.DiscountByChargeFeeStrategy");
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param discoutType
	* @param className 
	*/
	private DiscoutTypeEnum(int discoutType, String className) {
		this.discoutType = discoutType;
		this.className = className;
	}
	
	private int discoutType;
	/** 
	* @Fields ClassName : 类路径
	*/ 
	private String className;
	
	/**
	 * @return the discoutType
	 */
	public int getDiscoutType() {
		return discoutType;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	
}
