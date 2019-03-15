/**   
* @Title: KECalculateFeeServiceImpl.java 
* @Package com.pile.serviceimpl 
* @Description: 科林自有充电流程结费实现类 
* @author dbr
* @date 2018年2月26日 下午2:08:38 
* @version V1.0   
*/
package com.pile.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.MemberOrders;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;
import static com.pile.utils.ConverterUtils.*;

import com.pile.utils.DateUtils;
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
	private Map<String, String> appFlagMap;

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
			/** 非接口充电 */
			if (chargeInfo.getAppFlag() != Constant.INTERFACE) {
				String orderSerialNumber = chargeInfo.getOrderSerialNumber();

				/** 获取该运营商的折扣规则(排除充值赠费) */
				List<Integer> discountTypeList = calculateFeeMapper.listOperatorRule(operatorId);
				for (Integer discountType : discountTypeList) {
					chargeInfo = discountContext.getDiscountInfo(chargeInfo, discountType);
				}
				
				/** 折扣，优惠券等， 可能后面没有活动了，先赋值， 如果后面还有， 则覆盖 */ 
				chargeInfo.setPaymentMoney(chargeInfo.getPayableMoney());
				int discountMoney = chargeInfo.getChargeMoney() - chargeInfo.getPaymentMoney();
				chargeInfo.setDiscountMoney(discountMoney);
				int refundMoney = chargeInfo.getPrepaidMoney() + chargeInfo.getRechargeMoney()
						- chargeInfo.getPaymentMoney();
				chargeInfo.setRefundMoney(refundMoney);
				chargeInfo.setRefundPrincipal(refundMoney);
				chargeInfo.setRefundGive(0);

				/** 最后结算充值赠费 */
				chargeInfo = discountContext.getDiscountInfo(chargeInfo, Constant.DISCOUNT_BYCHARGEFEE);
				if (chargeInfo.getPrepayType() == Constant.BALANCE_CHARGING) {
					/** 余额充电更新用户账户金额 */
					calculateFeeMapper.updateMemberAccount(chargeInfo.getRefundGive(), chargeInfo.getRefundPrincipal(),
							chargeInfo.getMemberId(), operatorId);
					/** 插入会员账户变动表 */
					insertMemberAccountChange(chargeInfo);
				}

				/** 更新redis充电单的状态 */
				String key = Constant.ORDER + orderSerialNumber;
				Map<String, String> orderMap = JedisUtils.hgetAll(key);
				orderMap.put("chargeState", toStr(Constant.CALCULATED));
				JedisUtils.hmset(key, orderMap);
				
				/** 插入个人站记录表 */
				int stationId = toInt(orderMap.get("substId"));
				int tradeMoney = chargeInfo.getPayableMoney();
				insertPersonStationChargeDetail(orderSerialNumber, operatorId, stationId, tradeMoney);
				
				/** 小程序 app 将充电单存入队列 */
				if (appFlagMap.containsKey(chargeInfo.getAppFlag())) {
					JedisUtils.lpush(appFlagMap.get(chargeInfo.getAppFlag()), chargeInfo.getOrderSerialNumber());
				}
			} else {
				chargeInfo.setPaymentMoney(chargeInfo.getChargeMoney());
				chargeInfo.setDiscountMoney(chargeInfo.getChargeMoney() - chargeInfo.getPaymentMoney());
				int refundMoney = chargeInfo.getPrepaidMoney() + chargeInfo.getRechargeMoney()
						- chargeInfo.getPaymentMoney();
				chargeInfo.setRefundMoney(refundMoney);
				chargeInfo.setRefundPrincipal(refundMoney);
				chargeInfo.setRefundGive(0);
			}
			
			/** 更新充电单金额信息 */
			flag = updateMemberOrder(chargeInfo);
		} catch (Exception e) {
			flag = false;
			Log4jUtils.getDiscountinfo().error("充电单算费异常：" + chargeInfo.toString(), e);
			e.printStackTrace();
		}

		return flag;
	}
	
	/** 
	* @Title: updateMemberOrder 
	* @Description: 更新充电单金额信息 
	* @param @param chargeInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private boolean updateMemberOrder(ChargeInfo chargeInfo) throws Exception{
		MemberOrders memberOrders = new MemberOrders();
		memberOrders.setSerialnumber(chargeInfo.getOrderSerialNumber());
		memberOrders.setEndpushFlag(Constant.NOPUSH);
		memberOrders.setChargeState((byte) Constant.CALCULATED);
		memberOrders.setTradeMoney(chargeInfo.getPaymentMoney());
		memberOrders.setDiscountMoney(chargeInfo.getDiscountMoney());
		
		memberOrders.setRefundMoney(chargeInfo.getRefundMoney());
		memberOrders.setRefundPrincipal(chargeInfo.getRefundPrincipal());
		memberOrders.setRefundGive(chargeInfo.getRefundGive());
		memberOrders.setLevel(chargeInfo.getMemberLevel());
		memberOrders.setLeveldesc(chargeInfo.getMemberLevelDesc());
		try {
			return calculateFeeMapper.updateMemberOrder(memberOrders);
		} catch (Exception e) {
			Log4jUtils.getDiscountinfo().error("更新充电单金额信息异常," + memberOrders.toString(), e);
			throw e;
		}
	}
	
	/** 
	* @Title: insertPersonStationChargeDetail 
	* @Description:  插入个人站记录表  
	* @param @param orderSerialNumber
	* @param @param operatorId
	* @param @param stationId
	* @param @param tradeMoney    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertPersonStationChargeDetail(String orderSerialNumber, int operatorId, int stationId,
			int tradeMoney) throws Exception {
		String tradeDate = orderSerialNumber.substring(0, 8);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String tableName = "chargedata.substperson_pay" + orderSerialNumber.substring(0, 4);

		try {
			Double checkRatio = calculateFeeMapper.getCheckRatio(operatorId, tradeDate, stationId);
			if (null != checkRatio && checkRatio != 0D) {
				int payMoney = (int) (tradeMoney * checkRatio);
				paramMap.put("serialNumber", orderSerialNumber);
				paramMap.put("tableName", tableName);
				paramMap.put("tradeMoney", tradeMoney);
				paramMap.put("payMoney", payMoney);
				paramMap.put("operatorId", operatorId);

				calculateFeeMapper.insertPersonStationChargeDetail(paramMap);
			}
		} catch (Exception e) {
			Log4jUtils.getChargerecord().error("插入个人站充电明细表异常,参数：" + paramMap, e);
			throw e;
		}
	}
	
	/** 
	* @Title: insertMemberAccountChange 
	* @Description: 插入会员账户变动表
	* @param @param chargeInfo
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertMemberAccountChange(ChargeInfo chargeInfo) throws Exception{
		int memberId = chargeInfo.getMemberId();
		int operatorId = chargeInfo.getOperatorId();
		try {
			Map<String,Object> memberAccount = calculateFeeMapper.getMemberCurrentAccount(memberId, operatorId);
			/** 当前剩余金额 */
			int payRemain= toInt(memberAccount.get("pay_remain")); 
			/** 当前赠送剩余金额 */
			int giveRemain = toInt(memberAccount.get("give_remain"));
			int refundPrincipal = chargeInfo.getRefundPrincipal();
			int refundGive = chargeInfo.getRefundGive();
			
			String tableName = "ChargeData.member_account_change" + DateUtils.getYear(new Date());
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("tableName", tableName);
			paramMap.put("memberId", memberId);
			paramMap.put("operatorId", operatorId);
			paramMap.put("operateType", Constant.TRANSFER_INCOME);
			paramMap.put("serialNumber", chargeInfo.getOrderSerialNumber());
			paramMap.put("principalChange", refundPrincipal);
			paramMap.put("giveChange", refundGive);
			paramMap.put("principalRemain", payRemain + refundPrincipal);
			paramMap.put("giveRemain", giveRemain + refundGive);
			calculateFeeMapper.insertMemberAccountChange(paramMap);
			
		} catch (Exception e) {
			Log4jUtils.getDiscountinfo().error("插入会员账户余额异常，参数" + chargeInfo.toString());
			e.printStackTrace();
			throw e;
		}
	}
}
