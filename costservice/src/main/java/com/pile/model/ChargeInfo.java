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

	/** 充电流水号 */
	private String serialNumber;
	/** 充电桩桩体号 */
	private String pileCode;
	/** 会员编号 */
	private int memberId;
	/** 预付金额 */
	private int prepaidMoney;
	/** 充值金额 */
	private int rechargeMoney;
	/** 交易后余额 */
	private int remainingMoney;
	/** 应付金额 */
	private int payableMoney;
	/** 优惠金额 */
	private int discountMoney;
	/** 实付金额 */
	private int paymentMoney;
	/** 结束原因*/
	private int endCause;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	 * @return the remainingMoney
	 */
	public int getRemainingMoney() {
		return remainingMoney;
	}

	/**
	 * @param remainingMoney
	 *            the remainingMoney to set
	 */
	public void setRemainingMoney(int remainingMoney) {
		this.remainingMoney = remainingMoney;
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
	 * @param endCause the endCause to set
	 */
	public void setEndCause(int endCause) {
		this.endCause = endCause;
	}

	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ChargeInfo [serialNumber=" + serialNumber + ", pileCode=" + pileCode + ", memberId=" + memberId
				+ ", prepaidMoney=" + prepaidMoney + ", rechargeMoney=" + rechargeMoney + ", remainingMoney="
				+ remainingMoney + ", payableMoney=" + payableMoney + ", discountMoney=" + discountMoney
				+ ", paymentMoney=" + paymentMoney + ", endCause=" + endCause + "]";
	}

}
