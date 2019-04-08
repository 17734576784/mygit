package com.nb.model;

import java.io.Serializable;

public class NbDailyDataKey implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private Integer rtuId;

    private Short mpId;

    private Integer ymd;

    private Integer hms;

    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }

    public Short getMpId() {
        return mpId;
    }

    public void setMpId(Short mpId) {
        this.mpId = mpId;
    }

    public Integer getYmd() {
        return ymd;
    }

    public void setYmd(Integer ymd) {
        this.ymd = ymd;
    }

    public Integer getHms() {
        return hms;
    }

    public void setHms(Integer hms) {
        this.hms = hms;
    }
}