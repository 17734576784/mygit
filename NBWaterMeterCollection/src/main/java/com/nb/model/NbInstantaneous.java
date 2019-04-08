package com.nb.model;

import java.math.BigDecimal;

public class NbInstantaneous extends NbInstantaneousKey {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private BigDecimal totalFlow;

    private BigDecimal dailyPositiveFlow;

    private BigDecimal dailyNegativeFlow;

    private BigDecimal hydraulicPressure;

    private BigDecimal dailyMaxVelocity;

    private Integer totalOnlineSuccess;

    private Integer totalOnlineFailure;

    private Byte valveStatus;
    
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
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(BigDecimal totalFlow) {
        this.totalFlow = totalFlow;
    }

    public BigDecimal getDailyPositiveFlow() {
        return dailyPositiveFlow;
    }

    public void setDailyPositiveFlow(BigDecimal dailyPositiveFlow) {
        this.dailyPositiveFlow = dailyPositiveFlow;
    }

    public BigDecimal getDailyNegativeFlow() {
        return dailyNegativeFlow;
    }

    public void setDailyNegativeFlow(BigDecimal dailyNegativeFlow) {
        this.dailyNegativeFlow = dailyNegativeFlow;
    }

    public BigDecimal getHydraulicPressure() {
        return hydraulicPressure;
    }

    public void setHydraulicPressure(BigDecimal hydraulicPressure) {
        this.hydraulicPressure = hydraulicPressure;
    }

    public BigDecimal getDailyMaxVelocity() {
        return dailyMaxVelocity;
    }

    public void setDailyMaxVelocity(BigDecimal dailyMaxVelocity) {
        this.dailyMaxVelocity = dailyMaxVelocity;
    }

    public Integer getTotalOnlineSuccess() {
        return totalOnlineSuccess;
    }

    public void setTotalOnlineSuccess(Integer totalOnlineSuccess) {
        this.totalOnlineSuccess = totalOnlineSuccess;
    }

    public Integer getTotalOnlineFailure() {
        return totalOnlineFailure;
    }

    public void setTotalOnlineFailure(Integer totalOnlineFailure) {
        this.totalOnlineFailure = totalOnlineFailure;
    }

    public Byte getValveStatus() {
        return valveStatus;
    }

    public void setValveStatus(Byte valveStatus) {
        this.valveStatus = valveStatus;
    }
}