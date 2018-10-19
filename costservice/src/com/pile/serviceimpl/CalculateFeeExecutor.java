/**   
* @Title: CalculateOrder.java 
* @Package com.pile.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月22日 下午3:45:04 
* @version V1.0   
*/
package com.pile.serviceimpl;

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
	private JedisUtils jedisUtils;

	@Autowired
	private ICalculateFeeService calculateFeeService;

	@Resource
	private CalculateFeeMapper calculateFeeMapper;

	public boolean calculateOrder(Object value) throws Exception {
		
		boolean flag = false;
		if (null == value) {
			return flag;
		}
		
		ChargeInfo chargeInfo = null;
		
		try {
			ChargeInfoBuf chargeInfoBuf = (ChargeInfoBuf)value;
			String text = JsonFormat.printToString(chargeInfoBuf);
			chargeInfo = toBean(text, ChargeInfo.class);
			System.out.println("充电单:[" + chargeInfo.getOrderSerialNumber() + "]结费中");
			
			String orderSerialNumber = chargeInfo.getOrderSerialNumber();
			String key = Constant.ORDER + orderSerialNumber;
			Map<String, String> orderMap = jedisUtils.hgetAll(key);
			if (null == orderMap || orderMap.isEmpty()) {
				// redis不存在，查库
				orderMap = calculateFeeMapper.getMemberOrder(orderSerialNumber);
				if (null != orderMap && !orderMap.isEmpty()) {
					/** 已算费 直接返回 */
					int chargeState = toInt(orderMap.get("chargeState")); 
					if (chargeState == Constant.CALCULATED) {
						return flag;
					}
					/** 补全chargeInfo 信息 */
					chargeInfo.setPrepayType(toInt(orderMap.get("prepayType")));
					chargeInfo.setOperatorId(toInt(orderMap.get("operatorId")));
					chargeInfo.setCouponId(toInt(orderMap.get("memberCouponId")));
					chargeInfo.setPrepaidMoney(toInt(orderMap.get("prechargeMoney")));
					chargeInfo.setPrechargeGive(toInt(orderMap.get("prechargeGive")));

					chargeInfo.setPrechargePrincipal(toInt(orderMap.get("prechargePrincipal")));
					chargeInfo.setPayRatio(toDouble(orderMap.get("payRatio")));
					chargeInfo.setRechargeMoney(toInt(orderMap.get("midchargeMoney")));
					chargeInfo.setAppFlag(toInt(orderMap.get("appFlag")));
				} else {
					Log4jUtils.getDiscountinfo().error("不存在此充电单：" + orderSerialNumber);
					return flag;
				}
			}

			flag = calculateFeeService.calculateChargeFee(chargeInfo);
			System.out.println("充电单:[" + chargeInfo.getOperatorId() + "]结费完成");

		} catch (Exception e) {
			Log4jUtils.getDiscountinfo().error("算费执行异常：" + chargeInfo.toString() + ",异常原因:" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	
}
