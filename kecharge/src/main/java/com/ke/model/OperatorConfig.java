package com.ke.model;

import java.io.Serializable;

public class OperatorConfig implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -636493284633129168L;

	private Integer id;

    private Integer operatorId;

    private String operatorLoginname;

    private Integer memberId;

    private Byte useFlag;

    private String username;

    private String password;

    private String token;

    private String loginUrl;

    private String chargeStartUrl;

    private String chargeOverUrl;

    private String chargeDcInfoUrl;

    private String chargeHeartUrl;

    private String chargeAlramUrl;

    private String serialnumberPrefix;
    
    private String memberPhone;

    /**
	 * @return the memberPhone
	 */
	public String getMemberPhone() {
		return memberPhone;
	}

	/**
	 * @param memberPhone the memberPhone to set
	 */
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorLoginname() {
        return operatorLoginname;
    }

    public void setOperatorLoginname(String operatorLoginname) {
        this.operatorLoginname = operatorLoginname == null ? null : operatorLoginname.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Byte getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Byte useFlag) {
        this.useFlag = useFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl == null ? null : loginUrl.trim();
    }

    public String getChargeStartUrl() {
        return chargeStartUrl;
    }

    public void setChargeStartUrl(String chargeStartUrl) {
        this.chargeStartUrl = chargeStartUrl == null ? null : chargeStartUrl.trim();
    }

    public String getChargeOverUrl() {
        return chargeOverUrl;
    }

    public void setChargeOverUrl(String chargeOverUrl) {
        this.chargeOverUrl = chargeOverUrl == null ? null : chargeOverUrl.trim();
    }

    public String getChargeDcInfoUrl() {
        return chargeDcInfoUrl;
    }

    public void setChargeDcInfoUrl(String chargeDcInfoUrl) {
        this.chargeDcInfoUrl = chargeDcInfoUrl == null ? null : chargeDcInfoUrl.trim();
    }

    public String getChargeHeartUrl() {
        return chargeHeartUrl;
    }

    public void setChargeHeartUrl(String chargeHeartUrl) {
        this.chargeHeartUrl = chargeHeartUrl == null ? null : chargeHeartUrl.trim();
    }

    public String getChargeAlramUrl() {
        return chargeAlramUrl;
    }

    public void setChargeAlramUrl(String chargeAlramUrl) {
        this.chargeAlramUrl = chargeAlramUrl == null ? null : chargeAlramUrl.trim();
    }

    public String getSerialnumberPrefix() {
        return serialnumberPrefix;
    }

    public void setSerialnumberPrefix(String serialnumberPrefix) {
        this.serialnumberPrefix = serialnumberPrefix == null ? null : serialnumberPrefix.trim();
    }
}