/**   
* @Title: DiscountByCouponStrategy.java 
* @Package com.pile.strategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月18日 下午3:08:31 
* @version V1.0   
*/
package com.pile.strategy;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.utils.Log4jUtils;
import static com.pile.utils.ConverterUtils.*;

/** 
* @ClassName: DiscountByCouponStrategy 
* @Description: 优惠券结费策略 
* @author dbr
* @date 2018年4月18日 下午3:08:31 
*  
*/
@Component
public class DiscountByCouponStrategy implements IDiscountStrategy {
	
	/** 优惠券延期判断标准 */
	@Value("${delayCouponLimit}")
	private int delayCouponLimit;
	
	/** 优惠券默认延期天数 */
	@Value("${delayDays}")
	private int delayDays;
	
	@Autowired
	private CalculateFeeMapper calculateFeeMapper;
	
	@Value("#{${endcause}}")
	private Map<Integer, String> endCause;
	
	/** (非 Javadoc) 
	* <p>Title: discount</p> 
	* <p>Description: </p> 
    * 根据流水号从member_orders中获取优惠券编号 没有优惠券 直接返回
	* 判断是否符合优惠券条件 
	* 应付金额 = 使用优惠券之前金额  实付金额 = 使用优惠券之后金额 优惠金额 = 优惠券面值 
	* 组织折扣详情：coupon_code 券码 coupon_name 券名  consume_money最低消费金额 coupon_money 优惠券金额， introduction	券说明
	* 插入充电单折扣记录表   
	* @param chargeInfo
	* @return 
	* @see com.pile.strategy.AbstractDiscountStrategy#discount(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ChargeInfo discount(ChargeInfo chargeInfo, int discountType) {
		
		Log4jUtils.getDiscountinfo().info("[优惠券折扣结算开始]：" + chargeInfo.toString());

		StringBuffer discountDetail = new StringBuffer();
		String orderSerialNumber = chargeInfo.getOrderSerialNumber();
		int couponId = chargeInfo.getCouponId();
		try {
			Map<String, Object> couponMap = calculateFeeMapper.getCouponById(couponId, chargeInfo.getTradeDate());
			if (null == couponMap) {
				Log4jUtils.getDiscountinfo().info("订单：" + orderSerialNumber + " 未找到有效的优惠券信息。");
				return chargeInfo;
			}

			if (toInt(couponMap.get("useFlag")) != Constant.USED || !toStr(couponMap.get("useDate")).isEmpty()) {
				Log4jUtils.getDiscountinfo().info("优惠券：[" + couponId + "]为非使用态，不使用优惠券");
				return chargeInfo;
			}

			/* 券码 */
			String couponCode = toStr(couponMap.get("coupon_code")).trim();
			/* 券名 */
			String couponName = toStr(couponMap.get("coupon_name"));
			/** 最低消费金额 */
			int consumeMoney = toInt(couponMap.get("consume_money"));
			/** 优惠券金额 */
			int couponMoney = toInt(couponMap.get("coupon_money"));
			/** 应付金额 */
			int payableMoney = chargeInfo.getPayableMoney();
			
			/** 判断优惠券是否符合使用条件 */
			if (payableMoney >= consumeMoney) {

				int discountMoney = couponMoney;
				if (discountMoney > payableMoney) {
					String info = String.format("优惠券：%s折扣金额%s大于充电金额%s", couponCode, discountMoney, payableMoney);
					Log4jUtils.getDiscountinfo().info(info);
					discountMoney = 0;
				}
				int paymentMoney = payableMoney - discountMoney;

				OrderDiscountRecord record = new OrderDiscountRecord();
				record.setSerialnumber(orderSerialNumber);
				record.setPayableMoney(payableMoney);
				record.setDiscountMoney(discountMoney);
				record.setPaymentMoney(paymentMoney);
				record.setDiscountType(toByte(discountType));
				record.setTableName(orderSerialNumber);
				
				discountDetail.append("应付金额:").append(payableMoney).append("券码:").append(couponCode);
				discountDetail.append(",券名:").append(couponName).append(",最低消费金额:");
				discountDetail.append(consumeMoney).append(",优惠券金额:").append(couponMoney);
				discountDetail.append(",实际折扣金额：").append(discountMoney).append(",实际支付金额:").append(paymentMoney);
				record.setDiscountDetail(discountDetail.toString());
				// 保存充电单折扣记录表
				calculateFeeMapper.insertOrderDiscountRecord(record);
				
				// 更新会员优惠券表中使用标志和使用日期
				calculateFeeMapper.updateMemberCoupon(couponCode);

				/** 实付金额赋值给下一折扣的应付金额 */
				chargeInfo.setPayableMoney(paymentMoney);
				
			} else {
				int diffDay = toInt(couponMap.get("diffDay"));
			
				/** 非正常结束 且优惠券有效期小于7天，延长该优惠券的有效期*/
				if (!endCause.containsKey(chargeInfo.getEndCause()) && diffDay <= delayCouponLimit) {
					calculateFeeMapper.delayCoupon(couponCode,delayDays);
					discountDetail.append("订单：").append(chargeInfo.toString());
					discountDetail.append(",由于非正常结束，延长优惠券有效期:").append(couponMap.toString());
					Log4jUtils.getDiscountinfo().info(discountDetail.toString());
				} else {
					discountDetail.append("订单不符合优惠券使用条件，订单信息：").append(chargeInfo.toString());
					discountDetail.append(",优惠券信息:").append(couponMap.toString());
					Log4jUtils.getDiscountinfo().info(discountDetail.toString());
				}
				
				/** 未使用优惠券 更新充电单未使用优惠券 */
				calculateFeeMapper.updateCouponUseFlag(couponCode, Constant.NO_USE);
				
			}
			
			Log4jUtils.getChargerecord().info("[优惠券折扣结算完成]：" + chargeInfo.toString());

		} catch (Exception e) {
			discountDetail.append("订单信息：").append(chargeInfo.toString());
			discountDetail.append("优惠券折扣结算异常");
			Log4jUtils.getDiscountinfo().info(discountDetail.toString());
			e.printStackTrace();
 		}
		
		return chargeInfo;
	}

}
