package com.nb.model;


import java.math.BigDecimal;
import java.util.Date;

public class NbWaterMeter extends NbWaterMeterKey {
    private Integer appinfoId;

    private Integer deviceModelId;

    private String meterNumber;

    private Integer meterCaliber;

    private String imeiCode;

    private String imsiCode;

    private String deviceId;

    private BigDecimal lowVoltageThreshold;

    private BigDecimal lowPressureThreshold;

    private BigDecimal highPressureThreshold;

    private BigDecimal highVoltageThreshold;

    private BigDecimal largeFlowThreshold;

    private Integer largeFlowDuration;

    private Integer longTimeWaterUseThreshold;

    private BigDecimal smallFlowThreshold;

    private Integer smallFlowDuration;

    private Integer onlineDelayWaitTime;

    private Date reportBaseTime;

    private Integer reportIntervalTime;

    private Byte valveStatus;

    private String firmwareVersion;

    public Integer getAppinfoId() {
        return appinfoId;
    }

    public void setAppinfoId(Integer appinfoId) {
        this.appinfoId = appinfoId;
    }

    public Integer getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Integer deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber == null ? null : meterNumber.trim();
    }

    public Integer getMeterCaliber() {
        return meterCaliber;
    }

    public void setMeterCaliber(Integer meterCaliber) {
        this.meterCaliber = meterCaliber;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode == null ? null : imeiCode.trim();
    }

    public String getImsiCode() {
        return imsiCode;
    }

    public void setImsiCode(String imsiCode) {
        this.imsiCode = imsiCode == null ? null : imsiCode.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public BigDecimal getLowVoltageThreshold() {
        return lowVoltageThreshold;
    }

    public void setLowVoltageThreshold(BigDecimal lowVoltageThreshold) {
        this.lowVoltageThreshold = lowVoltageThreshold;
    }

    public BigDecimal getLowPressureThreshold() {
        return lowPressureThreshold;
    }

    public void setLowPressureThreshold(BigDecimal lowPressureThreshold) {
        this.lowPressureThreshold = lowPressureThreshold;
    }

    public BigDecimal getHighPressureThreshold() {
        return highPressureThreshold;
    }

    public void setHighPressureThreshold(BigDecimal highPressureThreshold) {
        this.highPressureThreshold = highPressureThreshold;
    }

    public BigDecimal getHighVoltageThreshold() {
        return highVoltageThreshold;
    }

    public void setHighVoltageThreshold(BigDecimal highVoltageThreshold) {
        this.highVoltageThreshold = highVoltageThreshold;
    }

    public BigDecimal getLargeFlowThreshold() {
        return largeFlowThreshold;
    }

    public void setLargeFlowThreshold(BigDecimal largeFlowThreshold) {
        this.largeFlowThreshold = largeFlowThreshold;
    }

    public Integer getLargeFlowDuration() {
        return largeFlowDuration;
    }

    public void setLargeFlowDuration(Integer largeFlowDuration) {
        this.largeFlowDuration = largeFlowDuration;
    }

    public Integer getLongTimeWaterUseThreshold() {
        return longTimeWaterUseThreshold;
    }

    public void setLongTimeWaterUseThreshold(Integer longTimeWaterUseThreshold) {
        this.longTimeWaterUseThreshold = longTimeWaterUseThreshold;
    }

    public BigDecimal getSmallFlowThreshold() {
        return smallFlowThreshold;
    }

    public void setSmallFlowThreshold(BigDecimal smallFlowThreshold) {
        this.smallFlowThreshold = smallFlowThreshold;
    }

    public Integer getSmallFlowDuration() {
        return smallFlowDuration;
    }

    public void setSmallFlowDuration(Integer smallFlowDuration) {
        this.smallFlowDuration = smallFlowDuration;
    }

    public Integer getOnlineDelayWaitTime() {
        return onlineDelayWaitTime;
    }

    public void setOnlineDelayWaitTime(Integer onlineDelayWaitTime) {
        this.onlineDelayWaitTime = onlineDelayWaitTime;
    }

    public Date getReportBaseTime() {
        return reportBaseTime;
    }

    public void setReportBaseTime(Date reportBaseTime) {
        this.reportBaseTime = reportBaseTime;
    }

    public Integer getReportIntervalTime() {
        return reportIntervalTime;
    }

    public void setReportIntervalTime(Integer reportIntervalTime) {
        this.reportIntervalTime = reportIntervalTime;
    }

    public Byte getValveStatus() {
        return valveStatus;
    }

    public void setValveStatus(Byte valveStatus) {
        this.valveStatus = valveStatus;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion == null ? null : firmwareVersion.trim();
    }
}