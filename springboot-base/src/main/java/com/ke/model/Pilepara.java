package com.ke.model;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-18
 */
public class Pilepara extends PileparaKey {
    /**
     * ����
     */
    private String describ;

    /**
     * ׮���
     */
    private String pileCode;

    /**
     * �����ն�
     */
    private Integer rtuId;

    /**
     * �ն���ͨ�ű��
     */
    private Short commNo;

    /**
     * ʹ�ñ�־
     */
    private Byte useFlag;

    /**
     * ��ַ����
     */
    private String addr;

    /**
     * ��ֱ������ 0 ���� 1 ֱ��
     */
    private Byte curType;

    /**
     * ������� 0 ��� 1 ����
     */
    private Byte chargeType;

    /**
     * ���ǹ����
     */
    private Byte gunNum;

    /**
     * ��ǹ��־
     */
    private Byte gunFlag;

    /**
     * ���׮���� ��ͨ׮���ص�׮
     */
    private Byte pileType;

    /**
     * ���ʱ��
     */
    private Short rateId;

    /**
     * ����ʱ�α��
     */
    private Integer rateperiodId;

    /**
     * ct����
     */
    private Integer ctNumerator;

    /**
     * ct��ĸ
     */
    private Integer ctDenominator;

    /**
     * ct���
     */
    private Double ctRatio;

    /**
     * �����
     */
    private Double rp;

    /**
     * ��������
     */
    private Double alarmP;

    /**
     * �޶�����
     */
    private Double limitP;

    /**
     * ���ѹ
     */
    private Double rv;

    /**
     * �����
     */
    private Double ri;

    /**
     * ������
     */
    private Double mi;

    /**
     * ���߷�ʽ
     */
    private Byte wiringMode;

    /**
     * �ӿڱ���1
     */
    private String infCode1;

    /**
     * �ӿڱ���2
     */
    private String infCode2;

    /**
     * �ӿڱ���3
     */
    private String infCode3;

    /**
     * ��չ�ֶ�1
     */
    private String reserve1;

    private Byte bikegunNum;

    private Byte cpFlag;

    private Byte commState;

    private Short vCeil;

    private Short vFloor;

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ == null ? null : describ.trim();
    }

    public String getPileCode() {
        return pileCode;
    }

    public void setPileCode(String pileCode) {
        this.pileCode = pileCode == null ? null : pileCode.trim();
    }

    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }

    public Short getCommNo() {
        return commNo;
    }

    public void setCommNo(Short commNo) {
        this.commNo = commNo;
    }

    public Byte getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Byte useFlag) {
        this.useFlag = useFlag;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Byte getCurType() {
        return curType;
    }

    public void setCurType(Byte curType) {
        this.curType = curType;
    }

    public Byte getChargeType() {
        return chargeType;
    }

    public void setChargeType(Byte chargeType) {
        this.chargeType = chargeType;
    }

    public Byte getGunNum() {
        return gunNum;
    }

    public void setGunNum(Byte gunNum) {
        this.gunNum = gunNum;
    }

    public Byte getGunFlag() {
        return gunFlag;
    }

    public void setGunFlag(Byte gunFlag) {
        this.gunFlag = gunFlag;
    }

    public Byte getPileType() {
        return pileType;
    }

    public void setPileType(Byte pileType) {
        this.pileType = pileType;
    }

    public Short getRateId() {
        return rateId;
    }

    public void setRateId(Short rateId) {
        this.rateId = rateId;
    }

    public Integer getRateperiodId() {
        return rateperiodId;
    }

    public void setRateperiodId(Integer rateperiodId) {
        this.rateperiodId = rateperiodId;
    }

    public Integer getCtNumerator() {
        return ctNumerator;
    }

    public void setCtNumerator(Integer ctNumerator) {
        this.ctNumerator = ctNumerator;
    }

    public Integer getCtDenominator() {
        return ctDenominator;
    }

    public void setCtDenominator(Integer ctDenominator) {
        this.ctDenominator = ctDenominator;
    }

    public Double getCtRatio() {
        return ctRatio;
    }

    public void setCtRatio(Double ctRatio) {
        this.ctRatio = ctRatio;
    }

    public Double getRp() {
        return rp;
    }

    public void setRp(Double rp) {
        this.rp = rp;
    }

    public Double getAlarmP() {
        return alarmP;
    }

    public void setAlarmP(Double alarmP) {
        this.alarmP = alarmP;
    }

    public Double getLimitP() {
        return limitP;
    }

    public void setLimitP(Double limitP) {
        this.limitP = limitP;
    }

    public Double getRv() {
        return rv;
    }

    public void setRv(Double rv) {
        this.rv = rv;
    }

    public Double getRi() {
        return ri;
    }

    public void setRi(Double ri) {
        this.ri = ri;
    }

    public Double getMi() {
        return mi;
    }

    public void setMi(Double mi) {
        this.mi = mi;
    }

    public Byte getWiringMode() {
        return wiringMode;
    }

    public void setWiringMode(Byte wiringMode) {
        this.wiringMode = wiringMode;
    }

    public String getInfCode1() {
        return infCode1;
    }

    public void setInfCode1(String infCode1) {
        this.infCode1 = infCode1 == null ? null : infCode1.trim();
    }

    public String getInfCode2() {
        return infCode2;
    }

    public void setInfCode2(String infCode2) {
        this.infCode2 = infCode2 == null ? null : infCode2.trim();
    }

    public String getInfCode3() {
        return infCode3;
    }

    public void setInfCode3(String infCode3) {
        this.infCode3 = infCode3 == null ? null : infCode3.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public Byte getBikegunNum() {
        return bikegunNum;
    }

    public void setBikegunNum(Byte bikegunNum) {
        this.bikegunNum = bikegunNum;
    }

    public Byte getCpFlag() {
        return cpFlag;
    }

    public void setCpFlag(Byte cpFlag) {
        this.cpFlag = cpFlag;
    }

    public Byte getCommState() {
        return commState;
    }

    public void setCommState(Byte commState) {
        this.commState = commState;
    }

    public Short getvCeil() {
        return vCeil;
    }

    public void setvCeil(Short vCeil) {
        this.vCeil = vCeil;
    }

    public Short getvFloor() {
        return vFloor;
    }

    public void setvFloor(Short vFloor) {
        this.vFloor = vFloor;
    }

	@Override
	public String toString() {
		return "Pilepara [describ=" + describ + ", pileCode=" + pileCode + ", rtuId=" + rtuId + ", commNo=" + commNo
				+ ", useFlag=" + useFlag + ", addr=" + addr + ", curType=" + curType + ", chargeType=" + chargeType
				+ ", gunNum=" + gunNum + ", gunFlag=" + gunFlag + ", pileType=" + pileType + ", rateId=" + rateId
				+ ", rateperiodId=" + rateperiodId + ", ctNumerator=" + ctNumerator + ", ctDenominator=" + ctDenominator
				+ ", ctRatio=" + ctRatio + ", rp=" + rp + ", alarmP=" + alarmP + ", limitP=" + limitP + ", rv=" + rv
				+ ", ri=" + ri + ", mi=" + mi + ", wiringMode=" + wiringMode + ", infCode1=" + infCode1 + ", infCode2="
				+ infCode2 + ", infCode3=" + infCode3 + ", reserve1=" + reserve1 + ", bikegunNum=" + bikegunNum
				+ ", cpFlag=" + cpFlag + ", commState=" + commState + ", vCeil=" + vCeil + ", vFloor=" + vFloor + "]";
	}
    
    
}