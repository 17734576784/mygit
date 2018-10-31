package com.wo.model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	/** 描述 (@author: dbr) */
	private static final long serialVersionUID = -55547776155050822L;

	private String loginName;
	private String describe;
	private Integer partnerId;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "LoginUser [describe=" + describe + ", loginName=" + loginName
				+ ", partnerId=" + partnerId + "]";
	}

}
