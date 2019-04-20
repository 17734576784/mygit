/**   
* @Title: CommandEnum.java 
* @Package com.nb.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月17日 上午10:33:17 
* @version V1.0   
*/
package com.nb.utils;

/** 
* @ClassName: CommandEnum 
* @Description: 命令执行结果回调枚举类 
* @author dbr
* @date 2019年4月17日 上午10:33:17 
*  
*/
public enum CommandEnum {
	/**
	 * 命令发送 SEND 1 命令交付 DELIVERED 2 命令超时 TIMEOUT 4 命令成功 SUCCESSFUL 3 命令失败 FAILED 5
	 */
	SEND("SEND", (byte) 1), DELIVERED("DELIVERED", (byte) 2), SUCCESSFUL("SUCCESSFUL", (byte) 3), TIMEOUT("TIMEOUT",
			(byte) 4), FAILED("FAILED", (byte) 5);
	
	private String resultCode;
	private Byte resultValue;

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the resultValue
	 */
	public Byte getResultValue() {
		return resultValue;
	}
	/**
	 * @param resultValue the resultValue to set
	 */
	public void setResultValue(Byte resultValue) {
		this.resultValue = resultValue;
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param resultCode
	* @param resultValue 
	*/
	private CommandEnum(String resultCode, Byte resultValue) {
		this.resultCode = resultCode;
		this.resultValue = resultValue;
	}
	
	public static Byte getresultValue(String value) {
		CommandEnum[] arr = values();
		for (CommandEnum item : arr) {
			if (item.getResultCode().equals(value)) {
				return item.getResultValue();
			}
		}
		return null;
	}
	

}
