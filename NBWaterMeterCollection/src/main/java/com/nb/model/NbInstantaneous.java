package com.nb.model;

import com.nb.utils.Constant;

/** 
* @ClassName: NbInstantaneous 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:52:51 
*  
*/
public class NbInstantaneous extends NbInstantaneousKey {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private Double totalFlow;

    private Double dailyPositiveFlow;

    private Double dailyNegativeFlow;

    private Double hydraulicPressure;

    private Double dailyMaxVelocity;

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
	public void setTableName(String date) {
		if (date.length() == Constant.TABLE_YYYYMM) {
			this.tableName = "yddata.dbo.nb_instantaneous_" + date;
		} else {
			this.tableName = date;
		}
	}

	public Double getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Double totalFlow) {
        this.totalFlow = totalFlow;
    }

    public Double getDailyPositiveFlow() {
        return dailyPositiveFlow;
    }

    public void setDailyPositiveFlow(Double dailyPositiveFlow) {
        this.dailyPositiveFlow = dailyPositiveFlow;
    }

    public Double getDailyNegativeFlow() {
        return dailyNegativeFlow;
    }

    public void setDailyNegativeFlow(Double dailyNegativeFlow) {
        this.dailyNegativeFlow = dailyNegativeFlow;
    }

    public Double getHydraulicPressure() {
        return hydraulicPressure;
    }

    public void setHydraulicPressure(Double hydraulicPressure) {
        this.hydraulicPressure = hydraulicPressure;
    }

    public Double getDailyMaxVelocity() {
        return dailyMaxVelocity;
    }

    public void setDailyMaxVelocity(Double dailyMaxVelocity) {
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