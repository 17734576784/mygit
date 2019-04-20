package com.nb.model;

/** 
* @ClassName: NbBatteryKey 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:35:54 
*  
*/
public class NbBatteryKey {
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