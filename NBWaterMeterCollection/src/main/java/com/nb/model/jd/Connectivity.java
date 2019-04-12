/**   
* @Title: Connectivity.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:09:45 
* @version V1.0   
*/
package com.nb.model.jd;

import java.io.Serializable;

import com.nb.utils.CommFunc;

/** 
* @ClassName: Connectivity 
* @Description: 竟达水表通讯类
* @author dbr
* @date 2019年4月10日 上午9:09:45 
*  
*/
public class Connectivity implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 210862303120122781L;

	private Integer rsrp; // 参考信号接收功率 单位:dBm
	private Integer sinr; // 信噪比 取值范围 -20 ~ 30 单位 dB
	private Integer signalECL;// 覆盖等级 取值范围 0 ~ 2
	private String pci; // PCI 枚举类型
	private Integer cellId; // 小区ID	
	private String evnetTime; // 事项上报时间
	/**
	 * @return the evnetTime
	 */
	public String getEvnetTime() {
		return evnetTime;
	}
	/**
	 * @param evnetTime the evnetTime to set
	 */
	public void setEvnetTime(String evnetTime) {
		this.evnetTime = CommFunc.parseEventTime(evnetTime);
	}
	
	/**
	 * @return the rsrp
	 */
	public Integer getRsrp() {
		return rsrp;
	}
	/**
	 * @param rsrp the rsrp to set
	 */
	public void setRsrp(Integer rsrp) {
		this.rsrp = rsrp;
	}
	/**
	 * @return the sinr
	 */
	public Integer getSinr() {
		return sinr;
	}
	/**
	 * @param sinr the sinr to set
	 */
	public void setSinr(Integer sinr) {
		this.sinr = sinr;
	}
	/**
	 * @return the signalECL
	 */
	public Integer getSignalECL() {
		return signalECL;
	}
	/**
	 * @param signalECL the signalECL to set
	 */
	public void setSignalECL(Integer signalECL) {
		this.signalECL = signalECL;
	}
	/**
	 * @return the pci
	 */
	public String getPci() {
		return pci;
	}
	/**
	 * @param pci the pci to set
	 */
	public void setPci(String pci) {
		this.pci = pci;
	}
	/**
	 * @return the cellId
	 */
	public Integer getCellId() {
		return cellId;
	}
	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}
	/** (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Connectivity [rsrp=" + rsrp + ", sinr=" + sinr + ", signalECL=" + signalECL + ", pci=" + pci
				+ ", cellId=" + cellId + ", evnetTime=" + evnetTime + "]";
	}
	
}