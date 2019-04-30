/**   
* @Title: DiscountByChargeFeeStrategy.java 
* @Package com.pile.strategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月18日 下午3:09:08 
* @version V1.0   
*/
package com.pile.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.pile.utils.ConverterUtils.*;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.utils.Log4jUtils;

/**
 * @ClassName: DiscountByChargeFeeStrategy
 * @Description: 充值赠费结费策略
 * @author dbr
 * @date 2018年4月18日 下午3:09:08
 * 
 */
@Component
public class DiscountByChargeFeeStrategy implements IDiscountStrategy {

	@Autowired
	private CalculateFeeMapper calculateFeeMapper;

	/** (非 Javadoc) 
	* 
	* 根据查询流水号从充电单中获取预充本金和预充赠金  没有赠金金额 直接返回！
	* 如有退款 更新member_account表中本金 赠金字段
	* 应付金额 = 实付金额  折扣金额 = 赠金字段
	* 组织折扣详情：actual_pay、actual_give、扣费比例
	* 插入充电单折扣记录表   
	* <p>Description: </p> 
	* @param chargeInfo
	* @return 
	* @see com.pile.strategy.AbstractDiscountStrategy#discount(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ChargeInfo discount(ChargeInfo chargeInfo, int discountType) {
		
		StringBuffer discountDetail = new StringBuffer();
 		Log4jUtils.getDiscountinfo().info("[充值赠费算费开始]："+ chargeInfo.toString());

		try {
			/** 非余额充电 直接返回 */
			if (chargeInfo.getPrepayType() != Constant.BALANCE_CHARGING) {
				chargeInfo.setPaymentMoney(chargeInfo.getPayableMoney());
				int discountMoney = chargeInfo.getChargeMoney() - chargeInfo.getPaymentMoney();
				chargeInfo.setDiscountMoney(discountMoney);
				int refundMoney = chargeInfo.getPrepaidMoney() + chargeInfo.getRechargeMoney()
						- chargeInfo.getPaymentMoney();
				chargeInfo.setRefundMoney(refundMoney);
				chargeInfo.setRefundPrincipal(refundMoney);
				chargeInfo.setRefundGive(0);
				Log4jUtils.getDiscountinfo().info("[充值赠费算费完成:非余额充电]：" + chargeInfo.toString());
				return chargeInfo;
			}

			/** 订单流水号 */
			String orderSerialNumber = chargeInfo.getOrderSerialNumber();
			/** 预充赠金 */
			int prechargeGive = chargeInfo.getPrechargeGive();
			/** 预充本金--分 */
			int prechargePrincipal = chargeInfo.getPrechargePrincipal();

			/** 应付金额 */
			int payableMoney = chargeInfo.getPayableMoney();			

			/** 实际支付本金 */
			int actualPay = payableMoney;

			/** 实际支付赠金 */
			int actualGive = 0;

			/** 应退本金 */
			int refundPrincipal = 0;
			/** 应退赠金 */
			int refundGive = 0;

			/** 运营商扣费比例 */
			double payRatio = chargeInfo.getPayRatio();
			
			/** 余额充电没有预充赠金 直接结算 */
			if (prechargeGive <= 0 || payRatio > 1 || payRatio <= 0) {
				refundPrincipal = prechargePrincipal - payableMoney;
				refundPrincipal = refundPrincipal < 0 ? 0 : refundPrincipal;
				refundGive = 0;
			} else {
				/** 理论支付赠金 */
				int discountMoney = (int) (payableMoney * payRatio);

				/** 实际支付赠金 */
				actualGive = prechargeGive >= discountMoney ? discountMoney : prechargeGive;
				/** 实际支付本金 */
				actualPay = payableMoney - actualGive;

				/** 应退赠金 */
				refundGive = prechargeGive - actualGive;
				/** 应退本金 */
				refundPrincipal = prechargePrincipal - actualPay;

				OrderDiscountRecord record = new OrderDiscountRecord();
				record.setSerialnumber(orderSerialNumber);
				record.setPayableMoney(payableMoney);
				record.setDiscountMoney(discountMoney);
				record.setPaymentMoney(payableMoney);
				record.setDiscountType(toByte(discountType));
				record.setTableName(orderSerialNumber);

				discountDetail.append("应付金额:").append(payableMoney).append(",预充金额：").append(prechargePrincipal);
				discountDetail.append(",实际支付本金:").append(actualPay).append(",应退本金:").append(refundPrincipal);
				discountDetail.append(",预充赠金:").append(prechargeGive).append(",实际支付赠金:").append(actualGive);
				discountDetail.append(",应退增金:").append(refundGive);
				record.setDiscountDetail(discountDetail.toString());
				// 保存充电单折扣记录表
				calculateFeeMapper.insertOrderDiscountRecord(record);
			}
			
			chargeInfo.setPaymentMoney(actualPay + actualGive);
			int refundMoney = chargeInfo.getPrepaidMoney() + chargeInfo.getRechargeMoney() - payableMoney;
			chargeInfo.setRefundMoney(refundMoney);
			chargeInfo.setRefundPrincipal(refundPrincipal);
			chargeInfo.setRefundGive(refundGive);

			Log4jUtils.getDiscountinfo().info("[充值赠费算费完成]："+ chargeInfo.toString());

		} catch (Exception e) {
			discountDetail.append("订单信息：").append(chargeInfo.toString());
			discountDetail.append(" 充值赠费算费执行异常");
 			Log4jUtils.getDiscountinfo().info(discountDetail.toString());
 			e.printStackTrace();
		}
 		
 		return chargeInfo;
	}
	
}