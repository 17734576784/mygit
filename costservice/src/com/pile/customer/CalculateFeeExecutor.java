/**   
* @Title: CalculateOrder.java 
* @Package com.pile.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月22日 下午3:45:04 
* @version V1.0   
*/
package com.pile.customer;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.protobuf.format.JsonFormat;
import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.netty.message.ChargeInfoBufOuterClass.ChargeInfoBuf;
import com.pile.service.ICalculateFeeService;
import static com.pile.utils.ConverterUtils.*;

import com.pile.utils.DateUtils;
import com.pile.utils.JedisUtils;
import static com.pile.utils.JsonUtils.*;
import com.pile.utils.Log4jUtils;

/**
 * @ClassName: CalculateOrder
 * @Description: 算费主程序
 * @author dbr
 * @date 2018年8月22日 下午3:45:04
 * 
 */
@Component
public class CalculateFeeExecutor {
	
	@Autowired
	private ICalculateFeeService calculateFeeService;

	@Resource
	private CalculateFeeMapper calculateFeeMapper;

	public int calculateOrder(byte[] value) {
		
		int flag = Constant.CALCULATION_INITIAL;
		if (null == value) {
			return flag;
		}
		
		ChargeInfo chargeInfo = null;
		try {
			ChargeInfoBuf chargeInfoBuf = ChargeInfoBuf.parseFrom(value);
			String text = JsonFormat.printToString(chargeInfoBuf);
			chargeInfo = toBean(text, ChargeInfo.class);
			String orderSerialNumber = chargeInfo.getOrderSerialNumber();

			Log4jUtils.getDiscountinfo().info("充电单:[" + chargeInfo.toString() + "]结费中");
			String key = Constant.ORDER + orderSerialNumber;
			Map<String, String> orderMap = JedisUtils.hgetAll(key);
			if (null == orderMap || orderMap.isEmpty()) {
				// redis不存在，查库
				orderMap = calculateFeeMapper.getMemberOrder(orderSerialNumber);
				if (null == orderMap || orderMap.isEmpty()) {
					Log4jUtils.getDiscountinfo().error("不存在此充电单：" + orderSerialNumber);
					return flag;
				}
			}
			
			/** 已算费 直接返回 */
			int chargeState = toInt(orderMap.get("chargeState"));
			if (chargeState >= Constant.CALCULATED) {
				Log4jUtils.getDiscountinfo().info("充电单：" + orderSerialNumber + "已算费，不再算费");
				return Constant.CALCULATION_FINISHED;
			}
			
			/** 完善 chargeInfo 信息 */
			chargeInfo = improveChargeInfo(chargeInfo, orderMap);
			
			/** 验证充电单下单时间 */ 
			String tradeDate = chargeInfo.getTradeDate();
			if (tradeDate.isEmpty() || !DateUtils.checkDate(tradeDate)) {
				chargeInfo.setTradeDate(DateUtils.formatTimesTampDate(new Date()));
			}
			
			boolean execFlag = calculateFeeService.calculateChargeFee(chargeInfo);
			flag = execFlag == true ? Constant.CALCULATION_SUCCESS : Constant.CALCULATION_FAILED;
			System.out.println("充电单:[" + chargeInfo.getOrderSerialNumber() + "]结费完成");

		} catch (Exception e) {
			flag = Constant.CALCULATION_FAILED;
			System.out.println("充电单:[" + chargeInfo.getOrderSerialNumber() + "]算费异常");
			Log4jUtils.getDiscountinfo().error("算费执行异常：" + chargeInfo.toString() + ",异常原因:" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	/** 
	* @Title: improveChargeInfo 
	* @Description: 完善 chargeInfo 信息
	* @param @param chargeInfo
	* @param @return    设定文件 
	* @return ChargeInfo    返回类型 
	* @throws 
	*/
	private ChargeInfo improveChargeInfo(ChargeInfo chargeInfo, Map<String, String> orderMap) {
		chargeInfo.setPrepayType(toInt(orderMap.get("prepayType")));
		chargeInfo.setOperatorId(toInt(orderMap.get("operatorId")));
		chargeInfo.setCouponId(toInt(orderMap.get("memberCouponId")));
		chargeInfo.setPrepaidMoney(toInt(orderMap.get("prechargeMoney")));
		chargeInfo.setPrechargeGive(toInt(orderMap.get("prechargeGive")));
		chargeInfo.setChargeAmount(toDouble(orderMap.get("chargeDl")));

		chargeInfo.setPrechargePrincipal(toInt(orderMap.get("prechargePrincipal")));
		chargeInfo.setPayRatio(toDouble(orderMap.get("payRatio")));
		chargeInfo.setRechargeMoney(toInt(orderMap.get("midchargeMoney")));
		chargeInfo.setAppFlag(toInt(orderMap.get("appFlag")));
		chargeInfo.setChargeMoney(toInt(orderMap.get("chargeMoney")));

		chargeInfo.setServiceMoney(toInt(orderMap.get("serviceMoney")));
		chargeInfo.setPayableMoney(chargeInfo.getChargeMoney());
		chargeInfo.setDiscountMoney(0);
		chargeInfo.setTradeDate(toStr(orderMap.get("tradeDate")));

		chargeInfo.setMemberLevel(toInt(orderMap.get("level")));
		chargeInfo.setMemberLevelDesc(toStr(orderMap.get("leveldesc")));
		chargeInfo.setDiscountId(-1);

		return chargeInfo;
	}
	
	
}
