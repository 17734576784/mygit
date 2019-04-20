package com.nb.model;

import com.nb.utils.Constant;

/** 
* @ClassName: NbBattery 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:35:47 
*  
*/
public class NbBattery extends NbBatteryKey {
    private Double batteryVoltage;

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
			this.tableName = "yddata.dbo.nb_battery_" + date;
		} else {
			this.tableName = date;
		}
	}
	
    public Double getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }
}