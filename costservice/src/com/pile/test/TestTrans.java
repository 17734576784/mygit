/**   
* @Title: TestTrans.java 
* @Package com.pile.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月26日 上午11:57:19 
* @version V1.0   
*/
package com.pile.test;

import static com.pile.utils.ConverterUtils.toInt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.utils.DateUtils;
import com.pile.utils.Log4jUtils;

/** 
* @ClassName: TestTrans 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年12月26日 上午11:57:19 
*  
*/
@Component
public class TestTrans {
	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	@Transactional
	public void testT() throws Exception{
		System.out.println(calculateFeeMapper.updateMemberAccount(100, 200, 1, 1));
		/** 插入会员账户变动表 */
		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setMemberId(1);
		chargeInfo.setOperatorId(1);
		chargeInfo.setRefundPrincipal(200);
		chargeInfo.setRefundGive(100);
//		insertMemberAccountChange(chargeInfo);
	}

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
