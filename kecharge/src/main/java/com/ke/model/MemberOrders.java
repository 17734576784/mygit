package com.ke.model;

import java.util.Date;

public class MemberOrders {
    private Integer id;

    private Integer memberId;

    private Integer operatorId;

    private String serialnumber;

    private String tradeDate;

    private Integer prechargeMoney;

    private Integer prechargePrincipal;

    private Integer prechargeGive;

    private Double payRatio;

    private Integer midchargeMoney;

    private Integer prepayType;

    private Byte sourceFunds;

    private Byte appFlag;

    private Integer substId;

    private Integer pileId;

    private Integer gunId;

    private String pileCode;

    private Byte chargeState;

    private Date payDate;

    private String payCustomer;

    private Integer memberCouponId;

    private String couponCode;

    private Integer couponMoney;

    private String chargeSerialnumber;

    private Date chargebeginDate;

    private Byte beginpushFlag;

    private Date chargeendDate;

    private Double chargeDl;

    private Integer chargeDur;

    private Integer endCause;

    private Integer errorCode;

    private Integer aftchargeMoney;

    private Byte endpushFlag;

    private Integer chargeMoney;

    private Integer serviceMoney;

    private Integer serviceMoneyj;

    private Integer serviceMoneyf;

    private Integer serviceMoneyp;

    private Integer serviceMoneyg;

    private Integer discountMoney;

    private Integer tradeMoney;

    private Integer refundMoney;

    private Integer refundPrincipal;

    private Integer refundGive;

    private Integer level;

    private String leveldesc;

    private String refundSerialnumber;

    private Date refundopDate;

    private Date refundDate;

    private Integer refundErrcode;

    private String refundErrinfo;
    
    private String startReadings;
    
    private String endReadings;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    
    /**
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Integer getPrechargeMoney() {
        return prechargeMoney;
    }

    public void setPrechargeMoney(Integer prechargeMoney) {
        this.prechargeMoney = prechargeMoney;
    }

    public Integer getPrechargePrincipal() {
        return prechargePrincipal;
    }

    public void setPrechargePrincipal(Integer prechargePrincipal) {
        this.prechargePrincipal = prechargePrincipal;
    }

    public Integer getPrechargeGive() {
        return prechargeGive;
    }

    public void setPrechargeGive(Integer prechargeGive) {
        this.prechargeGive = prechargeGive;
    }

    public Double getPayRatio() {
        return payRatio;
    }

    public void setPayRatio(Double payRatio) {
        this.payRatio = payRatio;
    }

    public Integer getMidchargeMoney() {
        return midchargeMoney;
    }

    public void setMidchargeMoney(Integer midchargeMoney) {
        this.midchargeMoney = midchargeMoney;
    }

    public Integer getPrepayType() {
        return prepayType;
    }

    public void setPrepayType(Integer prepayType) {
        this.prepayType = prepayType;
    }

    public Byte getSourceFunds() {
        return sourceFunds;
    }

    public void setSourceFunds(Byte sourceFunds) {
        this.sourceFunds = sourceFunds;
    }

    public Byte getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(Byte appFlag) {
        this.appFlag = appFlag;
    }

    public Integer getSubstId() {
        return substId;
    }

    public void setSubstId(Integer substId) {
        this.substId = substId;
    }

    public Integer getPileId() {
        return pileId;
    }

    public void setPileId(Integer pileId) {
        this.pileId = pileId;
    }

    public Integer getGunId() {
        return gunId;
    }

    public void setGunId(Integer gunId) {
        this.gunId = gunId;
    }

    public String getPileCode() {
        return pileCode;
    }

    public void setPileCode(String pileCode) {
        this.pileCode = pileCode == null ? null : pileCode.trim();
    }

    public Byte getChargeState() {
        return chargeState;
    }

    public void setChargeState(Byte chargeState) {
        this.chargeState = chargeState;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayCustomer() {
        return payCustomer;
    }

    public void setPayCustomer(String payCustomer) {
        this.payCustomer = payCustomer == null ? null : payCustomer.trim();
    }

    public Integer getMemberCouponId() {
        return memberCouponId;
    }

    public void setMemberCouponId(Integer memberCouponId) {
        this.memberCouponId = memberCouponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Integer getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getChargeSerialnumber() {
        return chargeSerialnumber;
    }

    public void setChargeSerialnumber(String chargeSerialnumber) {
        this.chargeSerialnumber = chargeSerialnumber == null ? null : chargeSerialnumber.trim();
    }

    public Date getChargebeginDate() {
        return chargebeginDate;
    }

    public void setChargebeginDate(Date chargebeginDate) {
        this.chargebeginDate = chargebeginDate;
    }

    public Byte getBeginpushFlag() {
        return beginpushFlag;
    }

    public void setBeginpushFlag(Byte beginpushFlag) {
        this.beginpushFlag = beginpushFlag;
    }

    public Date getChargeendDate() {
        return chargeendDate;
    }

    public void setChargeendDate(Date chargeendDate) {
        this.chargeendDate = chargeendDate;
    }

    public Double getChargeDl() {
        return chargeDl;
    }

    public void setChargeDl(Double chargeDl) {
        this.chargeDl = chargeDl;
    }

    public Integer getChargeDur() {
        return chargeDur;
    }

    public void setChargeDur(Integer chargeDur) {
        this.chargeDur = chargeDur;
    }

    public Integer getEndCause() {
        return endCause;
    }

    public void setEndCause(Integer endCause) {
        this.endCause = endCause;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getAftchargeMoney() {
        return aftchargeMoney;
    }

    public void setAftchargeMoney(Integer aftchargeMoney) {
        this.aftchargeMoney = aftchargeMoney;
    }

    public Byte getEndpushFlag() {
        return endpushFlag;
    }

    public void setEndpushFlag(Byte endpushFlag) {
        this.endpushFlag = endpushFlag;
    }

    public Integer getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Integer chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public Integer getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Integer serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public Integer getServiceMoneyj() {
        return serviceMoneyj;
    }

    public void setServiceMoneyj(Integer serviceMoneyj) {
        this.serviceMoneyj = serviceMoneyj;
    }

    public Integer getServiceMoneyf() {
        return serviceMoneyf;
    }

    public void setServiceMoneyf(Integer serviceMoneyf) {
        this.serviceMoneyf = serviceMoneyf;
    }

    public Integer getServiceMoneyp() {
        return serviceMoneyp;
    }

    public void setServiceMoneyp(Integer serviceMoneyp) {
        this.serviceMoneyp = serviceMoneyp;
    }

    public Integer getServiceMoneyg() {
        return serviceMoneyg;
    }

    public void setServiceMoneyg(Integer serviceMoneyg) {
        this.serviceMoneyg = serviceMoneyg;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Integer tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public Integer getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Integer refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Integer getRefundPrincipal() {
        return refundPrincipal;
    }

    public void setRefundPrincipal(Integer refundPrincipal) {
        this.refundPrincipal = refundPrincipal;
    }

    public Integer getRefundGive() {
        return refundGive;
    }

    public void setRefundGive(Integer refundGive) {
        this.refundGive = refundGive;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLeveldesc() {
        return leveldesc;
    }

    public void setLeveldesc(String leveldesc) {
        this.leveldesc = leveldesc == null ? null : leveldesc.trim();
    }

    public String getRefundSerialnumber() {
        return refundSerialnumber;
    }

    public void setRefundSerialnumber(String refundSerialnumber) {
        this.refundSerialnumber = refundSerialnumber == null ? null : refundSerialnumber.trim();
    }

    public Date getRefundopDate() {
        return refundopDate;
    }

    public void setRefundopDate(Date refundopDate) {
        this.refundopDate = refundopDate;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public Integer getRefundErrcode() {
        return refundErrcode;
    }

    public void setRefundErrcode(Integer refundErrcode) {
        this.refundErrcode = refundErrcode;
    }

    public String getRefundErrinfo() {
        return refundErrinfo;
    }

    public void setRefundErrinfo(String refundErrinfo) {
        this.refundErrinfo = refundErrinfo == null ? null : refundErrinfo.trim();
    }

	/**
	 * @return the startReadings
	 */
	public String getStartReadings() {
		return startReadings;
	}

	/**
	 * @param startReadings the startReadings to set
	 */
	public void setStartReadings(String startReadings) {
		this.startReadings = startReadings;
	}

	/**
	 * @return the endReadings
	 */
	public String getEndReadings() {
		return endReadings;
	}

	/**
	 * @param endReadings the endReadings to set
	 */
	public void setEndReadings(String endReadings) {
		this.endReadings = endReadings;
	}
    
}