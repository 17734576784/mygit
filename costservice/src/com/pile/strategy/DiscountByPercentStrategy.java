/**   
* @Title: DiscountByPercentStrategy.java 
* @Package com.pile.strategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月18日 下午3:06:46 
* @version V1.0   
*/
package com.pile.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.pile.utils.ConverterUtils.*;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.utils.Log4jUtils;

/** 
* @ClassName: DiscountByPercentStrategy 
* @Description: 打折结费策略 
* @author dbr
* @date 2018年4月18日 下午3:06:46 
*  
*/
@Component
public class DiscountByPercentStrategy implements IDiscountStrategy {
	@Autowired
	private CalculateFeeMapper calculateFeeMapper;
	/** (非 Javadoc) 
	 *
	 *根据会员编号，充电桩编号从 operator_discount_promotion、member_level获取折扣信息
	 *存在折扣信息，进行折扣算费
	 *应付金额 = 打折之前  实付金额 = 打折之后 
	 *组织折扣详情：discount_type1 折扣类型  0:百分比  1:折价 rcd50 discount_rate1 折扣百分比 discount_price1 折扣金额
	 *插入充电单折扣记录表   
	 * <p>Title: discount</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.pile.strategy.AbstractDiscountStrategy#discount() 
	 */
	@Override
	public ChargeInfo discount(ChargeInfo chargeInfo, int discountType) {

		StringBuffer discountDetail = new StringBuffer();
		String orderSerialNumber = chargeInfo.getOrderSerialNumber();
		Log4jUtils.getDiscountinfo().info("[打折算费开始]："+ chargeInfo.toString());

		try {
			/** 根据流水号获取会员折扣信息 */
			Map<String, Object> discountMap = calculateFeeMapper.getDiscountBySerialNumber(orderSerialNumber);
			if (null == discountMap) {
				/** 默认折扣信息, 不用为车主指定具体的所属方案， 采用默认的方案 */
				discountMap = calculateFeeMapper.getDefDiscountBySerialNumber(orderSerialNumber);
				if (discountMap == null) {
					Log4jUtils.getDiscountinfo().info("订单：" + orderSerialNumber + " 未找到有效的折扣信息。");
				}
				return chargeInfo;
			}

			/** 折扣类型 */
			byte percentType = toByte(discountMap.get("discountType"));
			/** 折扣百分比 */
			int discountRate = toInt(discountMap.get("discountRate"));
			/** 折扣金额 */
			int discountPrice = toInt(discountMap.get("discountPrice"));
			/** 折扣方式 1 总金额折扣 2 服务费折扣 新增 */
			byte discountSubtype = toByte(discountMap.get("discountSubtype"));

			/** 应付金额 */
			int payableMoney = chargeInfo.getPayableMoney();
			/** 实付金额 */
			int paymentMoney = 0;
			/** 优惠金额 */
			int discountMoney = 0;

			//比例
			if (percentType == Constant.DISCOUNT_PERCENT) {
				
				if (discountRate < 0 || discountRate > 100)
					discountRate = 100;

				/** 服务费 打折 */
				if (discountSubtype == Constant.DISCOUNT_SERVICE) {
					discountMoney = chargeInfo.getServiceMoney() * (100 - discountRate) / 100;
					paymentMoney = payableMoney - discountMoney;
				} else {/** 总金额打折 */
					discountMoney = payableMoney * (100 - discountRate) / 100;
					paymentMoney = payableMoney - discountMoney;

				}
			} else if (percentType == Constant.DISCOUNT_PRICE) {// 折价
				double chargeDL = chargeInfo.getChargeAmount();
				discountMoney = (int) (((chargeDL < 0) ? 0 : chargeDL) * discountPrice);

				/** 服务费 打折 最多不收服务费 */
				if (discountSubtype == Constant.DISCOUNT_SERVICE) {
					if (discountMoney > chargeInfo.getServiceMoney()) {
						discountMoney = chargeInfo.getServiceMoney();
					}
				} else {
					if (discountMoney > payableMoney || discountMoney < 0) {
						discountMoney = 0;
					}
				}

				paymentMoney = payableMoney - discountMoney;
			}

			OrderDiscountRecord record = new OrderDiscountRecord();
			record.setSerialnumber(orderSerialNumber);
			record.setPayableMoney(payableMoney);
			record.setDiscountMoney(discountMoney);
			record.setPaymentMoney(paymentMoney);
			record.setDiscountType(toByte(discountType));
			record.setTableName(orderSerialNumber);
			
			discountDetail.append("应付金额:").append(payableMoney).append(",打折类型：").append(percentType);
			discountDetail.append(",折扣百分比:").append(discountRate).append(",折扣金额:").append(discountPrice);
			discountDetail.append(",实际折扣金额:").append(discountMoney).append(",实际支付金额:").append(paymentMoney);
			record.setDiscountDetail(discountDetail.toString());
			/** 保存充电单折扣记录表 */
			calculateFeeMapper.insertOrderDiscountRecord(record);
			
			/** 实付金额赋值给下一折扣的应付金额 */
			chargeInfo.setPayableMoney(paymentMoney);
			chargeInfo.setMemberLevel(toInt(discountMap.get("memberLevel")));
			chargeInfo.setMemberLevelDesc(toStr(discountMap.get("memberLevelDesc")));
			chargeInfo.setDiscountId(toInt(discountMap.get("discountId")));

	 		Log4jUtils.getDiscountinfo().info("[打折算费完成]："+ chargeInfo.toString());
		} catch (Exception e) {
			discountDetail.append("订单信息：").append(chargeInfo.toString());
			discountDetail.append(" 打折算费执行异常");
 			Log4jUtils.getDiscountinfo().info(discountDetail.toString());
 			e.printStackTrace();
		}
		
		return chargeInfo;
	}

}
