package com.wo.model;

public class PartnerConfig {
    private Integer id;

    private Integer partnerId;

    private Byte useFlag;

    private String username;

    private String password;

    private Integer carownerId;

    private String token;

    private String loginUrl;

    private String chargeStartUrl;

    private String chargeOverUrl;
    
    private String DCChargeInfoUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
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

    public Integer getCarownerId() {
        return carownerId;
    }

    public void setCarownerId(Integer carownerId) {
        this.carownerId = carownerId;
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

	public String getDCChargeInfoUrl() {
		return DCChargeInfoUrl;
	}

	public void setDCChargeInfoUrl(String dCChargeInfoUrl) {
		DCChargeInfoUrl = dCChargeInfoUrl;
	}

	@Override
	public String toString() {
		return "PartnerConfig [carownerId=" + carownerId + ", chargeOverUrl="
				+ chargeOverUrl + ", chargeStartUrl=" + chargeStartUrl
				+ ", id=" + id + ", loginUrl=" + loginUrl + ", partnerId="
				+ partnerId + ", password=" + password + ", token=" + token
				+ ", useFlag=" + useFlag + ", username=" + username + "]";
	}
}