/**   
* @Title: IDiscountStrategy.java 
* @Package com.pile.strategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月13日 下午4:17:29 
* @version V1.0   
*/
package com.pile.strategy;

import com.pile.model.ChargeInfo;

/** 
* @ClassName: IDiscountStrategy 
* @Description: 打折算法接口 
* @author dbr
* @date 2018年8月13日 下午4:17:29 
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
