package com.ke.model;

import java.io.Serializable;
import java.util.Date;

public class Pilepara implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -3061020964366900049L;

	private Integer id;

    private Integer substId;

    private String name;

    private Byte useFlag;

    private String serialCode;

    private Integer rtuId;

    private String pileaddr;

    private String imageUrl;

    private Byte type;

    private Byte gunNum;

    private Byte bikeGunNum;

    private Byte cpFlag;

    private Byte gunFlag;

    private Integer factoryId;

    private Integer rateId;

    private Integer rateperiodId;

    private Float limitP;

    private Integer rv;

    private Float ri;

    private Byte wiringMode;

    private Integer vUp;

    private Integer vDown;

    private Date runDate;

    private Date createDate;

    private Date updateDate;

    private Integer pileIdx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubstId() {
        return substId;
    }

    public void setSubstId(Integer substId) {
        this.substId = substId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Byte useFlag) {
        this.useFlag = useFlag;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode == null ? null : serialCode.trim();
    }

    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }

    public String getPileaddr() {
        return pileaddr;
    }

    public void setPileaddr(String pileaddr) {
        this.pileaddr = pileaddr == null ? null : pileaddr.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getGunNum() {
        return gunNum;
    }

    public void setGunNum(Byte gunNum) {
        this.gunNum = gunNum;
    }

    public Byte getBikeGunNum() {
        return bikeGunNum;
    }

    public void setBikeGunNum(Byte bikeGunNum) {
        this.bikeGunNum = bikeGunNum;
    }

    public Byte getCpFlag() {
        return cpFlag;
    }

    public void setCpFlag(Byte cpFlag) {
        this.cpFlag = cpFlag;
    }

    public Byte getGunFlag() {
        return gunFlag;
    }

    public void setGunFlag(Byte gunFlag) {
        this.gunFlag = gunFlag;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public Integer getRateperiodId() {
        return rateperiodId;
    }

    public void setRateperiodId(Integer rateperiodId) {
        this.rateperiodId = rateperiodId;
    }

    public Float getLimitP() {
        return limitP;
    }

    public void setLimitP(Float limitP) {
        this.limitP = limitP;
    }

    public Integer getRv() {
        return rv;
    }

    public void setRv(Integer rv) {
        this.rv = rv;
    }

    public Float getRi() {
        return ri;
    }

    public void setRi(Float ri) {
        this.ri = ri;
    }

    public Byte getWiringMode() {
        return wiringMode;
    }

    public void setWiringMode(Byte wiringMode) {
        this.wiringMode = wiringMode;
    }

    public Integer getvUp() {
        return vUp;
    }

    public void setvUp(Integer vUp) {
        this.vUp = vUp;
    }

    public Integer getvDown() {
        return vDown;
    }

    public void setvDown(Integer vDown) {
        this.vDown = vDown;
    }

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPileIdx() {
        return pileIdx;
    }

    public void setPileIdx(Integer pileIdx) {
        this.pileIdx = pileIdx;
    }
}