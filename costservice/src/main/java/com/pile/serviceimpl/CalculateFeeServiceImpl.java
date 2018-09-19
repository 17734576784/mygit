/**   
* @Title: KECalculateFeeServiceImpl.java 
* @Package com.pile.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年2月26日 下午2:08:38 
* @version V1.0   
*/
package com.pile.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;

/** 
* @ClassName: KECalculateFeeServiceImpl 
* @Description: 科林自有充电流程结费实现类
* @author dbr
* @date 2018年2月26日 下午2:08:38 
*  
*/
@Service
public class CalculateFeeServiceImpl implements ICalculateFeeService{

	@Autowired
	private DiscountContext discountContext;
	
	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	/** (非 Javadoc) 
	* <p>Title: calculateChargeFee</p> 
	* <p>Description: </p>  
	* @see com.pile.service.ICalculateFeeService#calculateChargeFee() 
	*/
	@Override
	@Transactional
	public void calculateChargeFee(ChargeInfo chargeInfo) {
		/**
		 * 1 解析充电记录
		 * 2 从充电单中获取车主充电预付款信息和充电折扣信息(member_orders)
		 * 获取运营商折扣规则表（operator_discount_rule） 决定折扣算费顺序
		 * 3 进行算费   更新车主余额
		 * 4 调用退款接口
		 * 6 更新充电单信息
		 * 8 充电结束消息推送
		*/
		String serialNumber = chargeInfo.getSerialNumber();
		List<Integer> ruleList = calculateFeeMapper.listOperatorRule(serialNumber);
		for (Integer rule : ruleList) {
			chargeInfo = discountContext.getDiscountInfo(chargeInfo, rule);
		}
		
		/** 预付金额 */
		int prepaidMoney = chargeInfo.getPrepaidMoney();
		/** 充值金额 */
		int rechargeMoney = chargeInfo.getRechargeMoney();
		/** 交易后余额 */
		int remainingMoney = chargeInfo.getRemainingMoney();
		/** 充电金额 */
		int chargeMoney = prepaidMoney - rechargeMoney - remainingMoney;
		/** 应付金额 */
		int payableMoney = chargeInfo.getPayableMoney();
		/** 优惠金额 */
		int discountMoney = chargeMoney - payableMoney;
		/** 推送标志 */
		byte endpushFlag = 0;
		
		calculateFeeMapper.updateMemberOrder(endpushFlag, chargeMoney, payableMoney, discountMoney);
		

		
	}
}
