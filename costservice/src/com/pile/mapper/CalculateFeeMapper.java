/**   
* @Title: CalculateFeeMapper.java 
* @Package com.pile.mapper 
* @Description: 数据库查询接口
* @author dbr
* @date 2018年5月28日 下午4:44:21 
* @version V1.0   
*/
package com.pile.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pile.model.MemberOrders;
import com.pile.model.OrderDiscountRecord;
/** 
* @ClassName: CalculateFeeMapper 
* @Description: 数据库查询接口 
* @author dbr
* @date 2018年5月28日 下午4:44:21 
*  
*/
public interface CalculateFeeMapper {
	
	/** 
	 * 根据优惠券Id获取充电单优惠券信息
	* @Title: getCouponBySerialNumber 
	* @param @param serialNumber
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getCouponById(@Param("couponId") int couponId, @Param("paydate") String paydate);
	
    /** 
     * 插入充电单折扣记录表
    * @Title: insertOrderDiscountRecord 
    * @Description: 插入充电单折扣记录表 
    * @param @param orderDiscountRecord
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean insertOrderDiscountRecord(OrderDiscountRecord orderDiscountRecord);

    /** 
    * @Title: updateMemberCoupon 
    * @Description: 更新会员优惠券表中使用标志和使用日期
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean updateMemberCoupon(@Param("couponCode") String couponCode);
    
    /** 
    * @Title: delayCoupon 
    * @Description: 延长优惠券过期时间
    * @param @param couponCode
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
	boolean delayCoupon(@Param("couponCode") String couponCode, @Param("delayDays") int delayDays);
    
    /** 
    * @Title: getDiscountBySerialNumber 
    * @Description: 根据流水号查询订单打折信息 
    * @param @param serialNumber
    * @param @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    */
    Map<String,Object> getDiscountBySerialNumber(@Param("serialNumber") String serialNumber);
    
    /** 
    * @Title: getDiscountBySerialNumber 
    * @Description: 根据流水号查询订单默认的打折信息 
    * @param @param serialNumber
    * @param @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    */
    Map<String,Object> getDefDiscountBySerialNumber(@Param("serialNumber") String serialNumber);
    
    /** 
    * @Title: updateMemberAccount 
    * @Description: 充值赠费结算后，更新运营商会员账户信息表中当前剩余金额，当前赠送剩余金额
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
	boolean updateMemberAccount(@Param("refundGive") int refundGive, @Param("refundPrincipal") int refundPrincipal,
			@Param("memberId") int memberId,@Param("operatorId") int operatorId);
	
	/** 
	* @Title: listOperatorRule 
	* @Description: 获取订单对应运营商的扣费折扣规则 
	* @param @param serialNumber
	* @param @return    设定文件 
	* @return List<Integer>    返回类型 
	* @throws 
	*/
	List<Integer> listOperatorRule(@Param("operatorId") int operatorId);
	
	/** 
	* @Title: updateMemberOrder 
	* @Description: 更新会员充电单记录表 
	* @param @param endpushFlag 推送标志
	* @param @param chargeMoney 充电金额
	* @param @param payableMoney 支付金额
	* @param @param discountMoney 优惠金额
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateMemberOrder(MemberOrders memberOrders);
	
	/** 
	* @Title: backUpOrderDiscountRecord 
	* @Description: 备份充电单折扣记录表 
	* @param @param tableName
	* @param @param date
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean backUpOrderDiscountRecord(@Param("tableName") String tableName,@Param("date") String date);
	
	
	/** 
	* @Title: getMemberOrder 
	* @Description: 根据流水号获取充电单信息 (非结算态 即 charge_state!=7)
	* @param @param orderSerialNumber
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String,String> getMemberOrder(@Param("orderSerialNumber") String orderSerialNumber);
	
	/** 
	* @Title: getMemberOrder 
	* @Description: 获取充电单信息列表 
	* @param @param orderSerialNumber
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> listMemberOrder();
	
	/** 
	* @Title: isCalculated 
	* @Description: 判断该充电单是否已经结算过 
	* @param @param orderSerialNumber
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean isCalculated(@Param("orderSerialNumber") String orderSerialNumber);
	
	
	/** 
	* @Title: insertMemberAccountChange 
	* @Description: 向历史库插入会员账户变动记录 
	* @param @param param
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean insertMemberAccountChange(Map<String, Object> param);
	
	/** 
	* @Title: getMemberCurrentAccount 
	* @Description: 获取会员当前账户信息 
	* @param @param memberId
	* @param @param operatorId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String,Object> getMemberCurrentAccount(@Param("memberId") int memberId,@Param("operatorId") int operatorId);

	/** 
	* @Title: updateCouponUseFlag 
	* @Description: 修改优惠使用标志 
	* @param @param couponCode
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateCouponUseFlag(@Param("couponCode") String couponCode, @Param("useFlag") int useFlag);
	
	/** 
	* @Title: insertPersonStationChargeDetail 
	* @Description: 个人站充电明细表 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean insertPersonStationChargeDetail(Map<String, Object> paramMap);
	
	/** 
	* @Title: isPersonStation 
	* @Description: 获取个人站代理方案中的收益比例 
	* @param @param operatorId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	Double getCheckRatio(@Param("operatorId") int operatorId, @Param("tradeDate") String tradeDate,
			@Param("stationId") int stationId);
}
