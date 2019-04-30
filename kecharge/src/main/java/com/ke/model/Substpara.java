package com.ke.model;

import java.util.Date;

public class Substpara {
    private Integer id;

    private String substName;

    private String description;

    private String serialCode;

    private Integer operatorId;

    private Byte model;

    private Double longitude;

    private Double latitude;

    private Integer area;

    private String addr;

    private Byte officeFlag;

    private String fzMan;

    private String telphone;

    private String imageUrl;

    private Integer viewCount;

    private Integer upvoteCount;

    private Integer chargeCount;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubstName() {
        return substName;
    }

    public void setSubstName(String substName) {
        this.substName = substName == null ? null : substName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode == null ? null : serialCode.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Byte getModel() {
        return model;
    }

    public void setModel(Byte model) {
        this.model = model;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Byte getOfficeFlag() {
        return officeFlag;
    }

    public void setOfficeFlag(Byte officeFlag) {
        this.officeFlag = officeFlag;
    }

    public String getFzMan() {
        return fzMan;
    }

    public void setFzMan(String fzMan) {
        this.fzMan = fzMan == null ? null : fzMan.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getChargeCount() {
        return chargeCount;
    }

    public void setChargeCount(Integer chargeCount) {
        this.chargeCount = chargeCount;
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
}