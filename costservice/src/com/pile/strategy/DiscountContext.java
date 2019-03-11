/**   
* @Title: DiscountContext.java 
* @Package com.pile.strategy 
* @Description: 打折策略的上下文
* @author dbr
* @date 2018年4月18日 下午3:10:26 
* @version V1.0   
*/
package com.pile.strategy;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pile.common.Constant;
import com.pile.model.ChargeInfo;
import com.pile.utils.DictionUtil;

/**
 * @ClassName: DiscountContext
 * @Description: 打折策略的上下文
 * @author dbr
 * @date 2018年4月18日 下午3:10:26
 * 
 */
@Service
public class DiscountContext {
	/** 装载策略对象的集合 */
	@Autowired
	private Map<String, IDiscountStrategy> discountStrategy = new HashMap<String, IDiscountStrategy>();

	@Resource
	private DictionUtil dictionUtil;

	/** 提供给客户端使用的方法 */
	public ChargeInfo getDiscountInfo(ChargeInfo chargeInfo, int discountType) {
		IDiscountStrategy strategy = getDiscountStrategy(discountType);
		if (null != strategy) {
			chargeInfo = strategy.discount(chargeInfo, discountType);
		}
		return chargeInfo;
	}

	/** 
	* @Title: getDiscountStrategy 
	* @Description: 根据折扣类型获取折扣策略
	* @param @param discountType
	* @param @return    设定文件 
	* @return IDiscountStrategy    返回类型 
	* @throws 
	*/
	private IDiscountStrategy getDiscountStrategy(int discountType) {
		String strageName = dictionUtil.getItemName(Constant.DISCOUNT_TYPE, discountType);
		return this.discountStrategy.get(strageName);
	}

	/**
	 * @return the discountStrategy
	 */
	public Map<String, IDiscountStrategy> getDiscountStrategy() {
		return discountStrategy;
	}

	/**
	 * @param discountStrategy
	 *            the discountStrategy to set
	 */
	public void setDiscountStrategy(Map<String, IDiscountStrategy> discountStrategy) {
		this.discountStrategy = discountStrategy;
	}
}
