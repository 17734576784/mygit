package com.nb.model;

import java.io.Serializable;

/** 
* @ClassName: NbInstantaneousKey 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:52:45 
*  
*/
public class NbInstantaneousKey implements Serializable{
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