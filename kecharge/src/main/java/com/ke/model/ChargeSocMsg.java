/**   
* @Title: ChargeOverMsg.java 
* @Package com.ke.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月30日 上午10:12:56 
* @version V1.0   
*/
package com.ke.model;

import java.io.Serializable;

/** 
* @ClassName: ChargeSocMsg 
* @Description: 充电首次SOC推送消息结构
* @author dbr
* @date 2019年4月30日 上午10:12:56 
*  
*/
public class ChargeSocMsg implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6069949918422778383L;
	private String chargeSerialNumber;
	private Integer remainSecond;
	private Integer SOC;
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
	 * @return the remainSecond
	 */
	public Integer getRemainSecond() {
		return remainSecond;
	}
	/**
	 * @param remainSecond the remainSecond to set
	 */
	public void setRemainSecond(Integer remainSecond) {
		this.remainSecond = remainSecond;
	}
	/**
	 * @return the sOC
	 */
	public Integer getSOC() {
		return SOC;
	}
	/**
	 * @param sOC the sOC to set
	 */
	public void setSOC(Integer sOC) {
		SOC = sOC;
	}
	
}
