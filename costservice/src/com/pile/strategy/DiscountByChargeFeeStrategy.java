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
import org.springframework.transaction.annotation.Transactional;

import static com.pile.utils.ConverterUtils.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.utils.DateUtils;
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
			String orderSerialNumber = chargeInfo.getOrderSerialNumber();
			/** 预充赠金 */
			int prechargeGive = chargeInfo.getPrechargeGive();
			/** 预充本金--分 */
			int prechargePrincipal = chargeInfo.getPrechargePrincipal();
			
			/** 应付金额 */
			int payableMoney = chargeInfo.getPayableMoney();
			
			/** 应退本金 */
			int refundPrincipal = 0;
			/** 应退赠金 */
			int refundGive = 0;
			
			/** 非余额充电 直接返回 */
			if (chargeInfo.getPrepayType() != Constant.BALANCECHARGING) {
				Log4jUtils.getDiscountinfo().info("[充值赠费算费完成:非余额充电]：" + chargeInfo.toString());
				return chargeInfo;
			}
			
			int memberId = chargeInfo.getMemberId();
			int operatorId = chargeInfo.getOperatorId();
			/** 余额充电没有预充赠金 直接结算 */
			if (prechargeGive == 0) {
				refundPrincipal = prechargePrincipal - payableMoney;
				refundGive = 0;
				/** 更新账户余额 操作类型待议 */
				calculateFeeMapper.updateMemberAccount(refundGive, refundPrincipal, memberId, operatorId);
			} else {
				/** 运营商扣费比例 */
				double payRatio = chargeInfo.getPayRatio();

				/** 理论支付赠金 */
				int discountMoney = (int) (payableMoney * payRatio);

				/** 实际支付赠金 */
				int actualGive = prechargeGive >= discountMoney ? discountMoney : prechargeGive;
				/** 实际支付本金 */
				int actualPay = payableMoney - actualGive;

				/** 应退赠金 */
				refundGive = prechargeGive - actualGive;
				/** 应退本金 */
				refundPrincipal = prechargePrincipal - actualPay;

				/** 更新账户余额 操作类型待议 */
				calculateFeeMapper.updateMemberAccount(refundGive, refundPrincipal, memberId, operatorId);

				OrderDiscountRecord record = new OrderDiscountRecord();
				record.setSerialnumber(orderSerialNumber);
				record.setPayableMoney(payableMoney);
				record.setDiscountMoney(discountMoney);
				record.setPaymentMoney(payableMoney);
				record.setDiscountType(toByte(discountType));

				discountDetail.append("应付金额:").append(payableMoney).append(",预充金额：").append(prechargePrincipal);
				discountDetail.append(",实际支付本金:").append(actualPay).append(",应退本金:").append(refundPrincipal);
				discountDetail.append(",预充赠金:").append(prechargeGive).append(",实际支付赠金:").append(actualGive);
				discountDetail.append(",应退增金:").append(refundGive);
				record.setDiscountDetail(discountDetail.toString());
				// 保存充电单折扣记录表
				calculateFeeMapper.insertOrderDiscountRecord(record);
			}
			/** 插入会员账户变动表 */
			insertMemberAccountChange(refundPrincipal, refundGive, chargeInfo);
			
			Log4jUtils.getDiscountinfo().info("[充值赠费算费完成]："+ chargeInfo.toString());

		} catch (Exception e) {
			discountDetail.append("订单信息：").append(chargeInfo.toString());
			discountDetail.append(" 充值赠费算费执行异常");
 			Log4jUtils.getDiscountinfo().info(discountDetail.toString());
 			e.printStackTrace();
		}
 		
 		return chargeInfo;
	}
	
	@Transactional
	private void insertMemberAccountChange(int refundPrincipal, int refundGive, ChargeInfo chargeInfo) {
		try {
			int memberId = chargeInfo.getMemberId();
			int operatorId = chargeInfo.getOperatorId();
			Map<String,Object> memberAccount = calculateFeeMapper.getMemberCurrentAccount(memberId, operatorId);
			/** 当前剩余金额 */
			int payRemain= toInt(memberAccount.get("pay_remain")); 
			/** 当前赠送剩余金额 */
			int giveRemain = toInt(memberAccount.get("give_remain"));
			
			String tableName = "ChargeData.member_account_change" + DateUtils.getYear(new Date());
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("tableName", tableName);
			paramMap.put("memberId", memberId);
			paramMap.put("operatorId", operatorId);
			paramMap.put("operateType", Constant.TRANSFERINCOME);
			paramMap.put("serialNumber", chargeInfo.getOrderSerialNumber());
			paramMap.put("principalChange", refundPrincipal);
			paramMap.put("giveChange", refundGive);
			paramMap.put("principalRemain", payRemain + refundPrincipal);
			paramMap.put("giveRemain", giveRemain + refundGive);
			calculateFeeMapper.insertMemberAccountChange(paramMap);
			
		} catch (Exception e) {
			Log4jUtils.getDiscountinfo().error("插入会员账户余额异常，参数" + chargeInfo.toString());
			e.printStackTrace();
		}
	}
}