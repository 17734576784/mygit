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
		this.tableName = "yddata.dbo.nb_battery_" + date;
	}
	
    public Double getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }
}