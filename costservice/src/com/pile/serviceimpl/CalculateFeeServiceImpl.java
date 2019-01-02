/**   
* @Title: KECalculateFeeServiceImpl.java 
* @Package com.pile.serviceimpl 
* @Description: 科林自有充电流程结费实现类 
* @author dbr
* @date 2018年2月26日 下午2:08:38 
* @version V1.0   
*/
package com.pile.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;
import com.pile.utils.JedisUtils;
import com.pile.utils.Log4jUtils;

/** 
* @ClassName: KECalculateFeeServiceImpl 
* @Description: 科林自有充电流程结费实现类
* @author dbr
* @date 2018年2月26日 下午2:08:38 
*  
*/
@Service
public class CalculateFeeServiceImpl implements ICalculateFeeService{

	@Resource
	private DiscountContext discountContext;

	@Resource
	private CalculateFeeMapper calculateFeeMapper;

	@Value("#{${appFlag}}")
	private Map<Integer, String> appFlagMap;

	/** (非 Javadoc) 
	* <p>Title: calculateChargeFee</p> 
	* <p>Description: </p>  
	* @see com.pile.service.ICalculateFeeService#calculateChargeFee() 
	*/
	@Override
	@Transactional
	public boolean calculateChargeFee(ChargeInfo chargeInfo) {
		/**
		 * 1 解析充电记录
		 * 2 从充电单中获取车主充电预付款信息和充电折扣信息(member_orders)
		 * 获取运营商折扣规则表（operator_discount_rule） 决定折扣算费顺序
		 * 3 进行算费   更新车主余额
		 * 4 调用退款接口
		 * 6 更新充电单信息
		 * 8 充电结束消息推送
		*/
		boolean flag = false;
		try {
			int operatorId = chargeInfo.getOperatorId();
			/** 获取该运营商的折扣规则(排除充值赠费) */
			List<Integer> discountTypeList = calculateFeeMapper.listOperatorRule(operatorId);
			for (Integer discountType : discountTypeList) {
				chargeInfo = discountContext.getDiscountInfo(chargeInfo, discountType);
			}

			/** 最后结算充值赠费 */
			chargeInfo = discountContext.getDiscountInfo(chargeInfo, Constant.DISCOUNTBYCHARGEFEE);

			/** 充电金额 */
			int chargeMoney = chargeInfo.getChargeMoney();
			/** 应付金额 */
			int payableMoney = chargeInfo.getPayableMoney();
			/** 优惠金额 */
			int discountMoney = chargeMoney - payableMoney;
			
			/** 更新充电单金额信息*/
			calculateFeeMapper.updateMemberOrder(Constant.NOPUSH, chargeMoney, payableMoney, discountMoney,
					chargeInfo.getOrderSerialNumber());
			
			//member_account_change2018
			/** 小程序 app 将充电单存入队列 */
			if (appFlagMap.containsKey(chargeInfo.getAppFlag())) {
				JedisUtils.lpush(appFlagMap.get(chargeInfo.getAppFlag()), chargeInfo.getOrderSerialNumber());
			}
			
			flag = true;
		} catch (Exception e) {
			flag = false;
			Log4jUtils.getDiscountinfo().error("充电单算费异常：" + chargeInfo.toString());
			e.printStackTrace();
		}

		return flag;
	}
}
