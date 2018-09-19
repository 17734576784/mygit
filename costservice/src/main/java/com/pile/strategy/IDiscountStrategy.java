/**   
* @Title: AbstractDiscountStrategy.java 
* @Package com.pile.strategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月18日 下午3:02:00 
* @version V1.0   
*/
package com.pile.strategy;

import com.pile.model.ChargeInfo;

/**
 * @ClassName: AbstractDiscountStrategy
 * @Description: 抽象打折算法
 * @author dbr
 * @date 2018年4月18日 下午3:02:00
 * 
 */
public interface IDiscountStrategy {

	/** 	
	* 打折策略方法
	* @Title: discount 
	* @param @param chargeInfo
	* @param @param discountType
	* @param @return    设定文件 
	* @return ChargeInfo    返回类型 
	* @throws 
	*/
	public abstract ChargeInfo discount(ChargeInfo chargeInfo,int discountType);
}
