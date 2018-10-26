/**   
* @Title: ErrorCodeEnum.java 
* @Package com.iot.exception 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月24日 下午5:33:57 
* @version V1.0   
*/
package com.iot.exception;

/** 
* @ClassName: ErrorCodeEnum 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年10月24日 下午5:33:57 
*  
*/
public enum ErrorCodeEnum {
	
	/** 请求错误 */
	FAILED(-1, "请求错误"),

	/** 执行成功 */
	SUCCESS(0,"执行成功"),

	/** 执行异常 */
	EXCEPTION(-2,"执行异常");
	
	/** 错误码 */
	private Integer status;
	
	/** 错误信息 */
	private String error;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param code
	* @param message 
	*/
	private ErrorCodeEnum(Integer status, String error) {
		this.status = status;
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}


	