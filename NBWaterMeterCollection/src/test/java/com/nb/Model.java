/**   
* @Title: Model.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午4:25:55 
* @version V1.0   
*/
package com.nb;

/** 
* @ClassName: Model 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年5月24日 下午4:25:55 
*  
*/
public class Model {
    public Double SettingResponseState; 
    public Double BatteryVoltage;
	/**
	 * @return the settingResponseState
	 */
	public Double getSettingResponseState() {
		return SettingResponseState;
	}
	/**
	 * @param settingResponseState the settingResponseState to set
	 */
	public void setSettingResponseState(Double settingResponseState) {
		SettingResponseState = settingResponseState;
	}
	/**
	 * @return the batteryVoltage
	 */
	public Double getBatteryVoltage() {
		return BatteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(Double batteryVoltage) {
		BatteryVoltage = batteryVoltage;
	} 
    
}
