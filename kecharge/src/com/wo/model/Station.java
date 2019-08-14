package com.wo.model;

public class Station {
	private Integer id;

	private String describ;

	private Integer yysId;

	private Integer province;

	private Integer city;

	private Integer county;

	private Byte busiModel;

	private String longitude;

	private String latitude;

	private String addr;

	private String serviceFee;

	private String chargingFee;

	private String salestime;

	private String fzMan;

	private String telno1;

	private String telno2;

	private Byte powerType;

	private Byte voltGrade;

	private String remark;

	private String reserve1;
	private String stationNo;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrib() {
		return describ;
	}

	public void setDescrib(String describ) {
		this.describ = describ == null ? null : describ.trim();
	}

	public Integer getYysId() {
		return yysId;
	}

	public void setYysId(Integer yysId) {
		this.yysId = yysId;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public Byte getBusiModel() {
		return busiModel;
	}

	public void setBusiModel(Byte busiModel) {
		this.busiModel = busiModel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee == null ? null : serviceFee.trim();
	}

	public String getChargingFee() {
		return chargingFee;
	}

	public void setChargingFee(String chargingFee) {
		this.chargingFee = chargingFee == null ? null : chargingFee.trim();
	}

	public String getSalestime() {
		return salestime;
	}

	public void setSalestime(String salestime) {
		this.salestime = salestime == null ? null : salestime.trim();
	}

	public String getFzMan() {
		return fzMan;
	}

	public void setFzMan(String fzMan) {
		this.fzMan = fzMan == null ? null : fzMan.trim();
	}

	public String getTelno1() {
		return telno1;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1 == null ? null : telno1.trim();
	}

	public String getTelno2() {
		return telno2;
	}

	public void setTelno2(String telno2) {
		this.telno2 = telno2 == null ? null : telno2.trim();
	}

	public Byte getPowerType() {
		return powerType;
	}

	public void setPowerType(Byte powerType) {
		this.powerType = powerType;
	}

	public Byte getVoltGrade() {
		return voltGrade;
	}

	public void setVoltGrade(Byte voltGrade) {
		this.voltGrade = voltGrade;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1 == null ? null : reserve1.trim();
	}

	@Override
	public String toString() {
		return "Station [addr=" + addr + ", busiModel=" + busiModel
				+ ", chargingFee=" + chargingFee + ", city=" + city
				+ ", county=" + county + ", describ=" + describ + ", fzMan="
				+ fzMan + ", id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", powerType=" + powerType
				+ ", province=" + province + ", remark=" + remark
				+ ", reserve1=" + reserve1 + ", salestime=" + salestime
				+ ", serviceFee=" + serviceFee + ", telno1=" + telno1
				+ ", telno2=" + telno2 + ", voltGrade=" + voltGrade
				+ ", yysId=" + yysId + "]";
	}

}