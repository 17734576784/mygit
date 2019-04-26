/**   
* @Title: XtValve.java 
* @Package com.nb.model.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月26日 上午10:06:27 
* @version V1.0   
*/
package com.nb.model.xt;

import com.nb.model.BaseModel;

/** 
* @ClassName: XtValve 
* @Description:新天科技Valve服务上报数据项
* @author dbr
* @date 2019年4月26日 上午10:06:27 
*  
*/
public class XtValve extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6180931491454162305L;
	
	/** 阀门状态 */
	private Integer valveStatus; 
	
	/**
	 * @return the valveStatus
	 */
	public Integer getValveStatus() {
		return valveStatus;
	}

	/**
	 * @param valveStatus the valveStatus to set
	 */
	public void setValveStatus(Integer valveStatus) {
		this.valveStatus = valveStatus;
	}

	
}
