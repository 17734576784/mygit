/**   
* @Title: orderDiscountRecord.java 
* @Package com.pile.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年5月29日 上午9:31:33 
* @version V1.0   
*/
package com.pile.model;

import java.util.Date;

/**
 * @ClassName: orderDiscountRecord
 * @Description: 充电单折扣记录表
 * @author dbr
 * @date 2018年5月29日 上午9:31:33
 * 
 */
public class OrderDiscountRecord {
	private Integer id;

	private String serialnumber;

	private Integer discountOrder;

	private Byte discountType;

	private Integer payableMoney;

	private Integer discountMoney;

	private Integer paymentMoney;

	private String discountDetail;

	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber == null ? null : serialnumber.trim();
	}

	public Integer getDiscountOrder() {
		return discountOrder;
	}

	public void setDiscountOrder(Integer discountOrder) {
		this.discountOrder = discountOrder;
	}

	public Byte getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Byte discountType) {
		this.discountType = discountType;
	}

	public Integer getPayableMoney() {
		return payableMoney;
	}

	public void setPayableMoney(Integer payableMoney) {
		this.payableMoney = payableMoney;
	}

	public Integer getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Integer discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Integer getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(Integer paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getDiscountDetail() {
		return discountDetail;
	}

	public void setDiscountDetail(String discountDetail) {
		this.discountDetail = discountDetail == null ? null : discountDetail.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
