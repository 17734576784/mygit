package com.ke.model;

import java.util.Date;

public class ChargeMonitor {
    private Integer id;

    private String serialnumber;

    private Integer chargeMoney;

    private String pileCode;

    private Integer gunId;

    private Date startDate;

    private Date startReceiveTime;

    private Byte startFlag;

    private Byte startPush;

    private Date startPushTime;

    private Date endDate;

    private Date endReceiveTime;

    private Byte endPush;

    private Date endPushTime;

    private Byte socPush;

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

    public Integer getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Integer chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getPileCode() {
        return pileCode;
    }

    public void setPileCode(String pileCode) {
        this.pileCode = pileCode == null ? null : pileCode.trim();
    }

    public Integer getGunId() {
        return gunId;
    }

    public void setGunId(Integer gunId) {
        this.gunId = gunId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartReceiveTime() {
        return startReceiveTime;
    }

    public void setStartReceiveTime(Date startReceiveTime) {
        this.startReceiveTime = startReceiveTime;
    }

    public Byte getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Byte startFlag) {
        this.startFlag = startFlag;
    }

    public Byte getStartPush() {
        return startPush;
    }

    public void setStartPush(Byte startPush) {
        this.startPush = startPush;
    }

    public Date getStartPushTime() {
        return startPushTime;
    }

    public void setStartPushTime(Date startPushTime) {
        this.startPushTime = startPushTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndReceiveTime() {
        return endReceiveTime;
    }

    public void setEndReceiveTime(Date endReceiveTime) {
        this.endReceiveTime = endReceiveTime;
    }

    public Byte getEndPush() {
        return endPush;
    }

    public void setEndPush(Byte endPush) {
        this.endPush = endPush;
    }

    public Date getEndPushTime() {
        return endPushTime;
    }

    public void setEndPushTime(Date endPushTime) {
        this.endPushTime = endPushTime;
    }

    public Byte getSocPush() {
        return socPush;
    }

    public void setSocPush(Byte socPush) {
        this.socPush = socPush;
    }
}