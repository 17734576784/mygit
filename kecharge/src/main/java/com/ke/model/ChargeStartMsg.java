/**   
* @Title: ChargeStartMsg.java 
* @Package com.ke.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月30日 上午10:08:03 
* @version V1.0   
*/
package com.ke.model;

import java.io.Serializable;

/** 
* @ClassName: ChargeStartMsg 
* @Description: 充电开始消息结果
* @author dbr
* @date 2019年4月30日 上午10:08:03 
*  
*/
public class ChargeStartMsg implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 20719434324358976L;
	private String chargeSerialNumber;
	private int resut;
	private String readings;
	
	/**
	 * @return the chargeSerialNumber
	 */
	public String getChargeSerialNumber() {
		return chargeSerialNumber;
	}
	/**
	 * @param chargeSerialNumber the chargeSerialNumber to set
	 */
	public void setChargeSerialNumber(String chargeSerialNumber) {
		this.chargeSerialNumber = chargeSerialNumber;
	}
	/**
	 * @return the resut
	 */
	public int getResut() {
		return resut;
	}
	/**
	 * @param resut the resut to set
	 */
	public void setResut(int resut) {
		this.resut = resut;
	}
	/**
	 * @return the readings
	 */
	public String getReadings() {
		return readings;
	}
	/**
	 * @param readings the readings to set
	 */
	public void setReadings(String readings) {
		this.readings = readings;
	}
	
}
