/**   
* @Title: XtBattery.java 
* @Package com.nb.model.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月26日 下午1:37:21 
* @version V1.0   
*/
package com.nb.model.xt;

import com.nb.model.BaseModel;
import com.nb.utils.Constant;

/** 
* @ClassName: SuntrontBattery 
* @Description: 新天科技Battery服务上报数据项 
* @author dbr
* @date 2019年4月26日 下午1:37:21 
*  
*/
public class SuntrontBattery  extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 4334093184256878273L;
	/** 电池电量 例：50（百分比） */
	private Double batteryLevel;
	/** 电量告警门限 单位：mV */
	private Double batteryThreshold;
	/** 电池电压 mV */
	private Double batteryVoltage;
	/**
	 * @return the batteryLevel
	 */
	public Double getBatteryLevel() {
		return batteryLevel;
	}
	/**
	 * @param batteryLevel the batteryLevel to set
	 */
	public void setBatteryLevel(Double batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	/**
	 * @return the batteryThreshold
	 */
	public Double getBatteryThreshold() {
		return batteryThreshold;
	}
	/**
	 * @param batteryThreshold the batteryThreshold to set
	 */
	public void setBatteryThreshold(Integer batteryThreshold) {
		this.batteryThreshold = batteryThreshold * 1D / Constant.NUM_1000;
	}
	/**
	 * @return the batteryVoltage
	 */
	public Double getBatteryVoltage() {
		return batteryVoltage;
	}
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(Double batteryVoltage) {
		this.batteryVoltage = batteryVoltage / Constant.NUM_1000;
	}
	
}
