/**   
* @Title: CmdResult.java 
* @Package com.nb.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月30日 下午4:11:35 
* @version V1.0   
*/
package com.nb.model;

/** 
* @ClassName: CmdResult 
* @Description:  命令下发结果类
* @author dbr
* @date 2019年5月30日 下午4:11:35 
*  
*/
public class CmdResult {
	private String rowId;
	private int status;
	private String error;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param rowId
	* @param status
	* @param error 
	*/
	public CmdResult(String rowId, int status, String error) {
		super();
		this.rowId = rowId;
		this.status = status;
		this.error = error;
	}

	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = "mp_" + rowId;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

}
