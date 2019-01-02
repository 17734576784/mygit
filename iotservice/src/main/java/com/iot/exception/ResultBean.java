/**   
* @Title: ResultBean.java 
* @Package com.iot.exception 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月24日 下午5:30:49 
* @version V1.0   
*/
package com.iot.exception;

import java.io.Serializable;

/** 
* @ClassName: ResultBean 
* @Description: 返回结果类
* @author dbr
* @date 2018年10月24日 下午5:30:49 
*  
*/
public class ResultBean<T> implements Serializable{

	/** 
	* @Fields serialVersionUID : serialVersionUID 
	*/ 
	private static final long serialVersionUID = 1L;
	/** 错误码 */
	private int status;
	/** 错误描述 */
	private String error;
	/** 返回数据 */
	private T data;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultBean(int status, String error, T data) {
		super();
		this.error = error;
		this.status = status;
		this.data = data;
	}
	
	
	public ResultBean(int status, String error) {
		super();
		this.error = error;
		this.status = status;
	}
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public ResultBean() {
		super();
		this.status = ErrorCodeEnum.SUCCESS.getStatus();
		this.error = ErrorCodeEnum.SUCCESS.getError();
	}
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param e 
	*/
	public ResultBean(Throwable e) {
		super();
		this.error = e.toString();
		this.status = ErrorCodeEnum.FAILED.getStatus();
	}

}
