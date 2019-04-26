/**   
* @Title: Connectivity.java 
* @Package com.nb.model.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午9:09:45 
* @version V1.0   
*/
package com.nb.model.jd;
import com.nb.model.BaseModel;

/** 
* @ClassName: Connectivity 
* @Description: 竞达Connectivity服务上报数据项
* @author dbr
* @date 2019年4月10日 上午9:09:45 
*  
*/
public class Connectivity extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 5727159985159293249L;
	/** 参考信号接收功率 单位:dBm */
	private Integer rsrp;
	/** 信噪比 取值范围 -20 ~ 30 单位 dB */
	private Integer sinr;
	/** 覆盖等级 取值范围 0 ~ 2 */
	private Integer signalECL;
	/** 枚举类型 */
	private String pci;
	/** 小区ID */
	private Integer cellId;
	
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
				+ ", cellId=" + cellId + "]";
	}
	
}
