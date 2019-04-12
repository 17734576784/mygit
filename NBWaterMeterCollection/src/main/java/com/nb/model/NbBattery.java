package com.nb.model;


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
		if (date.length() == 6) {
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