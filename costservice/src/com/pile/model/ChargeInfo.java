/**   
* @Title: ChargeInfo.java 
* @Package com.pile.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月18日 下午4:16:30 
* @version V1.0   
*/
package com.pile.model;

import java.io.Serializable;

/**
 * @ClassName: ChargeInfo
 * @Description: 充电信息
 * @author dbr
 * @date 2018年4月18日 下午4:16:30
 * 
 */
public class ChargeInfo implements Serializable {
	/**
	 * @Fields serialVersionUID : serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 会员编号 */
	private int memberId;
	/** 充电桩桩体号 */
	private String pileCode;
	/** 充电桩id */
	private int pileId;
	/** 充电枪id */
	private int gunId;
	/** 订单流水号 */
	private String orderSerialNumber;
	/** 服务费 */
	private int serviceMoney;
	/** 实际充电金额 */
	private int chargeMoney;
	/** 实际充电电量 */
	private double chargeAmount;
	/** 结束原因 */
	private int endCause;

	/** 下单日期 */
	private String tradeDate;

	/** 客户端类型 */
	private int appFlag;
	/** 充电方式 */
	private int prepayType;
	/** 运营商id */
	private int operatorId;
	/* 优惠券id */
	private int couponId;
	/** 预付金额 */
	private int prepaidMoney;
	/** 预充赠金 */
	private int prechargeGive;
	/** 预充本金--分 */
	private int prechargePrincipal;
	/** 运营商扣费比例 */
	private double payRatio;
	/** 充值金额 */
	private int rechargeMoney;
	/** 应付金额 对应 支付金额 trade_money */
	private int payableMoney;
	/** 优惠金额 对应 discount_money */
	private int discountMoney;
	/** 实付金额 */
	private int paymentMoney;
	/** 退款金额 */
	private int refundMoney;
	/** 退款本金--分 */
	private int refundPrincipal;
	/** 退款赠金--分 */
	private int refundGive;
	/** 会员等级 */
	private Integer memberLevel;
	/** 会员等级描述 */
	private String memberLevelDesc;

	/**
	 * @return the orderSerialNumber
	 */
	public String getOrderSerialNumber() {
		return orderSerialNumber;
	}

	/**
	 * @param orderSerialNumber
	 *            the orderSerialNumber to set
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}

	/**
	 * @return the pileCode
	 */
	public String getPileCode() {
		return pileCode;
	}

	/**
	 * @param pileCode
	 *            the pileCode to set
	 */
	public void setPileCode(String pileCode) {
		this.pileCode = pileCode;
	}

	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the pileId
	 */
	public int getPileId() {
		return pileId;
	}

	/**
	 * @param pileId
	 *            the pileId to set
	 */
	public void setPileId(int pileId) {
		this.pileId = pileId;
	}

	/**
	 * @return the gunId
	 */
	public int getGunId() {
		return gunId;
	}

	/**
	 * @param gunId
	 *            the gunId to set
	 */
	public void setGunId(int gunId) {
		this.gunId = gunId;
	}

	/**
	 * @return the serviceMoney
	 */
	public int getServiceMoney() {
		return serviceMoney;
	}

	/**
	 * @param serviceMoney
	 *            the serviceMoney to set
	 */
	public void setServiceMoney(int serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	/**
	 * @return the chargeMoney
	 */
	public int getChargeMoney() {
		return chargeMoney;
	}

	/**
	 * @param chargeMoney
	 *            the chargeMoney to set
	 */
	public void setChargeMoney(int chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	/**
	 * @return the chargeAmount
	 */
	public double getChargeAmount() {
		return chargeAmount;
	}

	/**
	 * @param chargeAmount
	 *            the chargeAmount to set
	 */
	public void setChargeAmount(int chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	/**
	 * @return the prepaidMoney
	 */
	public int getPrepaidMoney() {
		return prepaidMoney;
	}

	/**
	 * @param prepaidMoney
	 *            the prepaidMoney to set
	 */
	public void setPrepaidMoney(int prepaidMoney) {
		this.prepaidMoney = prepaidMoney;
	}

	/**
	 * @return the rechargeMoney
	 */
	public int getRechargeMoney() {
		return rechargeMoney;
	}

	/**
	 * @param rechargeMoney
	 *            the rechargeMoney to set
	 */
	public void setRechargeMoney(int rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	/**
	 * @return the payableMoney
	 */
	public int getPayableMoney() {
		return payableMoney;
	}

	/**
	 * @param payableMoney
	 *            the payableMoney to set
	 */
	public void setPayableMoney(int payableMoney) {
		this.payableMoney = payableMoney;
	}

	/**
	 * @return the discountMoney
	 */
	public int getDiscountMoney() {
		return discountMoney;
	}

	/**
	 * @param discountMoney
	 *            the discountMoney to set
	 */
	public void setDiscountMoney(int discountMoney) {
		this.discountMoney = discountMoney;
	}

	/**
	 * @return the paymentMoney
	 */
	public int getPaymentMoney() {
		return paymentMoney;
	}

	/**
	 * @param paymentMoney
	 *            the paymentMoney to set
	 */
	public void setPaymentMoney(int paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	/**
	 * @return the endCause
	 */
	public int getEndCause() {
		return endCause;
	}

	/**
	 * @param endCause
	 *            the endCause to set
	 */
	public void setEndCause(int endCause) {
		this.endCause = endCause;
	}

	/**
	 * @return the operatorId
	 */
	public int getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId
	 *            the operatorId to set
	 */
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * @return the couponId
	 */
	public int getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the prechargeGive
	 */
	public int getPrechargeGive() {
		return prechargeGive;
	}

	/**
	 * @param prechargeGive
	 *            the prechargeGive to set
	 */
	public void setPrechargeGive(int prechargeGive) {
		this.prechargeGive = prechargeGive;
	}

	/**
	 * @return the prechargePrincipal
	 */
	public int getPrechargePrincipal() {
		return prechargePrincipal;
	}

	/**
	 * @param prechargePrincipal
	 *            the prechargePrincipal to set
	 */
	public void setPrechargePrincipal(int prechargePrincipal) {
		this.prechargePrincipal = prechargePrincipal;
	}

	/**
	 * @return the payRatio
	 */
	public double getPayRatio() {
		return payRatio;
	}

	/**
	 * @param payRatio
	 *            the payRatio to set
	 */
	public void setPayRatio(double payRatio) {
		this.payRatio = payRatio;
	}

	/**
	 * @param chargeAmount
	 *            the chargeAmount to set
	 */
	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	/**
	 * @return the prepayType
	 */
	public int getPrepayType() {
		return prepayType;
	}

	/**
	 * @param prepayType
	 *            the prepayType to set
	 */
	public void setPrepayType(int prepayType) {
		this.prepayType = prepayType;
	}

	/**
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate
	 *            the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public int getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(int refundMoney) {
		this.refundMoney = refundMoney;
	}

	public int getRefundPrincipal() {
		return refundPrincipal;
	}

	public void setRefundPrincipal(int refundPrincipal) {
		this.refundPrincipal = refundPrincipal;
	}

	public int getRefundGive() {
		return refundGive;
	}

	public void setRefundGive(int refundGive) {
		this.refundGive = refundGive;
	}

	/**
	 * @return the appFlag
	 */
	public int getAppFlag() {
		return appFlag;
	}

	/**
	 * @param appFlag
	 *            the appFlag to set
	 */
	public void setAppFlag(int appFlag) {
		this.appFlag = appFlag;
	}

	/**
	 * @return the memberLevel
	 */
	public Integer getMemberLevel() {
		return memberLevel;
	}

	/**
	 * @param memberLevel the memberLevel to set
	 */
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	/**
	 * @return the memberLevelDesc
	 */
	public String getMemberLevelDesc() {
		return memberLevelDesc;
	}

	/**
	 * @param memberLevelDesc the memberLevelDesc to set
	 */
	public void setMemberLevelDesc(String memberLevelDesc) {
		this.memberLevelDesc = memberLevelDesc;
	}

	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ChargeInfo [memberId=" + memberId + ", pileCode=" + pileCode + ", pileId=" + pileId + ", gunId=" + gunId
				+ ", orderSerialNumber=" + orderSerialNumber + ", serviceMoney=" + serviceMoney + ", chargeMoney="
				+ chargeMoney + ", chargeAmount=" + chargeAmount + ", endCause=" + endCause + ", tradeDate=" + tradeDate
				+ ", appFlag=" + appFlag + ", prepayType=" + prepayType + ", operatorId=" + operatorId + ", couponId="
				+ couponId + ", prepaidMoney=" + prepaidMoney + ", prechargeGive=" + prechargeGive
				+ ", prechargePrincipal=" + prechargePrincipal + ", payRatio=" + payRatio + ", rechargeMoney="
				+ rechargeMoney + ", payableMoney=" + payableMoney + ", discountMoney=" + discountMoney
				+ ", paymentMoney=" + paymentMoney + ", refundMoney=" + refundMoney + ", refundPrincipal="
				+ refundPrincipal + ", refundGive=" + refundGive + ", memberLevel=" + memberLevel + ", memberLevelDesc="
				+ memberLevelDesc + "]";
	}
}
