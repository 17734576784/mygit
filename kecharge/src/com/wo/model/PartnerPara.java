package com.wo.model;

public class PartnerPara {
	private Integer id;

	private String partnername;

	private String describ;

	private String passwd;

	private String pchceck;

	private String mobile;

	private Integer lastloginymd;

	private Byte loginfailtimes;

	private Byte lockflag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartnername() {
		return partnername;
	}

	public void setPartnername(String partnername) {
		this.partnername = partnername == null ? null : partnername.trim();
	}

	public String getDescrib() {
		return describ;
	}

	public void setDescrib(String describ) {
		this.describ = describ == null ? null : describ.trim();
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd == null ? null : passwd.trim();
	}

	public String getPchceck() {
		return pchceck;
	}

	public void setPchceck(String pchceck) {
		this.pchceck = pchceck == null ? null : pchceck.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getLastloginymd() {
		return lastloginymd;
	}

	public void setLastloginymd(Integer lastloginymd) {
		this.lastloginymd = lastloginymd;
	}

	public Byte getLoginfailtimes() {
		return loginfailtimes;
	}

	public void setLoginfailtimes(Byte loginfailtimes) {
		this.loginfailtimes = loginfailtimes;
	}

	public Byte getLockflag() {
		return lockflag;
	}

	public void setLockflag(Byte lockflag) {
		this.lockflag = lockflag;
	}

	@Override
	public String toString() {
		return "PartnerPara [describ=" + describ + ", id=" + id
				+ ", lastloginymd=" + lastloginymd + ", lockflag=" + lockflag
				+ ", loginfailtimes=" + loginfailtimes + ", mobile=" + mobile
				+ ", partnername=" + partnername + ", passwd=" + passwd
				+ ", pchceck=" + pchceck + "]";
	}
}