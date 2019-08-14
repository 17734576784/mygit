package com.wo.cachemanager;

import com.wo.model.LoginUser;

/**
 * @author dbr
 * 
 */
public class CacheBean {

	private String key; 		// token
	private Object value; 		// 用户名+登录类型
	private LoginUser loginUser;//登录用户信息
	private long timeOut; 		// 更新时间
	private boolean expired; 	// 是否终止

	public CacheBean() {
		super();
	}

	public CacheBean(String key, Object value, LoginUser loginUser,
			long timeOut, boolean expired) {
		this.key = key;
		this.value = value;
		this.loginUser = loginUser;
		this.timeOut = timeOut;
		this.expired = expired;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "CacheBean [expired=" + expired + ", key=" + key
				+ ", loginUser=" + loginUser + ", timeOut=" + timeOut
				+ ", value=" + value + "]";
	}
}
