package com.nb.model;

import java.math.BigDecimal;

public class Eve {
    private Short classno;

    private Short typeno;

    private Integer ymd;

    private Integer hmsms;

    private Byte voltgrade;

    private Byte output;

    private Integer memberId0;

    private Integer memberId1;

    private Integer memberId2;

    private BigDecimal doubleValue0;

    private BigDecimal doubleValue1;

    private BigDecimal doubleValue2;

    private BigDecimal doubleValue3;

    private Byte stateValue0;

    private Byte stateValue1;

    private Byte stateValue2;

    private Byte stateValue3;

    private String groupName;

    private String memberName0;

    private String memberName1;

    private String memberName2;

    private String charInfo;
    
    private String tableName;

    /**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String date) {
		this.tableName = "yddata.dbo.eve" + date;
	}
	

    public Short getClassno() {
        return classno;
    }

    public void setClassno(Short classno) {
        this.classno = classno;
    }

    public Short getTypeno() {
        return typeno;
    }

    public void setTypeno(Short typeno) {
        this.typeno = typeno;
    }

    public Integer getYmd() {
        return ymd;
    }

    public void setYmd(Integer ymd) {
        this.ymd = ymd;
    }

    public Integer getHmsms() {
        return hmsms;
    }

    public void setHmsms(Integer hmsms) {
        this.hmsms = hmsms;
    }

    public Byte getVoltgrade() {
        return voltgrade;
    }

    public void setVoltgrade(Byte voltgrade) {
        this.voltgrade = voltgrade;
    }

    public Byte getOutput() {
        return output;
    }

    public void setOutput(Byte output) {
        this.output = output;
    }

    public Integer getMemberId0() {
        return memberId0;
    }

    public void setMemberId0(Integer memberId0) {
        this.memberId0 = memberId0;
    }

    public Integer getMemberId1() {
        return memberId1;
    }

    public void setMemberId1(Integer memberId1) {
        this.memberId1 = memberId1;
    }

    public Integer getMemberId2() {
        return memberId2;
    }

    public void setMemberId2(Integer memberId2) {
        this.memberId2 = memberId2;
    }

    public BigDecimal getDoubleValue0() {
        return doubleValue0;
    }

    public void setDoubleValue0(BigDecimal doubleValue0) {
        this.doubleValue0 = doubleValue0;
    }

    public BigDecimal getDoubleValue1() {
        return doubleValue1;
    }

    public void setDoubleValue1(BigDecimal doubleValue1) {
        this.doubleValue1 = doubleValue1;
    }

    public BigDecimal getDoubleValue2() {
        return doubleValue2;
    }

    public void setDoubleValue2(BigDecimal doubleValue2) {
        this.doubleValue2 = doubleValue2;
    }

    public BigDecimal getDoubleValue3() {
        return doubleValue3;
    }

    public void setDoubleValue3(BigDecimal doubleValue3) {
        this.doubleValue3 = doubleValue3;
    }

    public Byte getStateValue0() {
        return stateValue0;
    }

    public void setStateValue0(Byte stateValue0) {
        this.stateValue0 = stateValue0;
    }

    public Byte getStateValue1() {
        return stateValue1;
    }

    public void setStateValue1(Byte stateValue1) {
        this.stateValue1 = stateValue1;
    }

    public Byte getStateValue2() {
        return stateValue2;
    }

    public void setStateValue2(Byte stateValue2) {
        this.stateValue2 = stateValue2;
    }

    public Byte getStateValue3() {
        return stateValue3;
    }

    public void setStateValue3(Byte stateValue3) {
        this.stateValue3 = stateValue3;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getMemberName0() {
        return memberName0;
    }

    public void setMemberName0(String memberName0) {
        this.memberName0 = memberName0 == null ? null : memberName0.trim();
    }

    public String getMemberName1() {
        return memberName1;
    }

    public void setMemberName1(String memberName1) {
        this.memberName1 = memberName1 == null ? null : memberName1.trim();
    }

    public String getMemberName2() {
        return memberName2;
    }

    public void setMemberName2(String memberName2) {
        this.memberName2 = memberName2 == null ? null : memberName2.trim();
    }

    public String getCharInfo() {
        return charInfo;
    }

    public void setCharInfo(String charInfo) {
        this.charInfo = charInfo == null ? null : charInfo.trim();
    }

	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Eve [classno=" + classno + ", typeno=" + typeno + ", ymd=" + ymd + ", hmsms=" + hmsms + ", voltgrade="
				+ voltgrade + ", output=" + output + ", memberId0=" + memberId0 + ", memberId1=" + memberId1
				+ ", memberId2=" + memberId2 + ", doubleValue0=" + doubleValue0 + ", doubleValue1=" + doubleValue1
				+ ", doubleValue2=" + doubleValue2 + ", doubleValue3=" + doubleValue3 + ", stateValue0=" + stateValue0
				+ ", stateValue1=" + stateValue1 + ", stateValue2=" + stateValue2 + ", stateValue3=" + stateValue3
				+ ", groupName=" + groupName + ", memberName0=" + memberName0 + ", memberName1=" + memberName1
				+ ", memberName2=" + memberName2 + ", charInfo=" + charInfo + ", tableName=" + tableName + "]";
	}
    
    
}