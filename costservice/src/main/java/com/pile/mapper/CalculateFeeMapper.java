/**   
* @Title: CalculateFeeMapper.java 
* @Package com.pile.mapper 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年5月28日 下午4:44:21 
* @version V1.0   
*/
package com.pile.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.pile.model.OrderDiscountRecord;

/** 
* @ClassName: CalculateFeeMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年5月28日 下午4:44:21 
*  
*/
@Mapper
public interface CalculateFeeMapper {
	
	/** 
	 * 根据流水号获取充电单优惠券信息
	* @Title: getCouponBySerialNumber 
	* @param @param serialNumber
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getCouponBySerialNumber(@Param("serialNumber") String serialNumber);
	
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
    boolean delayCoupon(@Param("couponCode") String couponCode);
    
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
    * @Title: getChargeFeeBySerialNumber 
    * @Description: 根据流水号查询本金增金信息 
    * @param @param serialNumber    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    Map<String,Object> getChargeFeeBySerialNumber(@Param("serialNumber") String serialNumber);
    
    /** 
    * @Title: updateMemberAccount 
    * @Description: 充值赠费结算后，更新运营商会员账户信息表中当前剩余金额，当前赠送剩余金额
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
	boolean updateMemberAccount(@Param("refundGive") int refundGive, @Param("refundPrincipal") int refundPrincipal,
			@Param("serialNumber") String serialNumber);
	
	 /** 
	* @Title: getChargeDLBySerialNumber 
	* @Description: 通过流水号获取充电电量
	* @param @param serialNumber
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getChargeDLBySerialNumber(@Param("serialNumber") String serialNumber);
 
	/** 
	* @Title: listOperatorRule 
	* @Description: 获取订单对应运营商的扣费折扣规则 
	* @param @param serialNumber
	* @param @return    设定文件 
	* @return List<Integer>    返回类型 
	* @throws 
	*/
	List<Integer> listOperatorRule(@Param("serialNumber") String serialNumber);
	
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
	boolean updateMemberOrder(@Param("endpushFlag") byte endpushFlag, @Param("chargeMoney") int chargeMoney,
			@Param("payableMoney") int payableMoney, @Param("discountMoney") int discountMoney);
}
