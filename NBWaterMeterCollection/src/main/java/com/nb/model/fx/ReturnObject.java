/**   
* @Title: ReturnObject.java 
* @Package com.nb.model.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午4:59:10 
* @version V1.0   
*/
package com.nb.model.fx;

/** 
* @ClassName: ReturnObject 
* @Description: 府星移动接口统一返回值 json 对应数据模型
* @author dbr
* @date 2019年5月24日 下午4:59:10 
*  
*/
public class ReturnObject {
	private boolean IsError;
	private String ErrMessage;
	/// 命令
	private String Command;
	/// 服务Id
	private String ServiceId;

	private String AFN;
	private String DIR;
	private int CNT;
	private String IMSI;

	/// 报文对象
	private ReceiveCode ContentObj;

	/**
	 * @return the isError
	 */
	public boolean isIsError() {
		return IsError;
	}

	/**
	 * @param isError the isError to set
	 */
	public void setIsError(boolean isError) {
		IsError = isError;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return ErrMessage;
	}

	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		ErrMessage = errMessage;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return Command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		Command = command;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return ServiceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		ServiceId = serviceId;
	}

	/**
	 * @return the aFN
	 */
	public String getAFN() {
		return AFN;
	}

	/**
	 * @param aFN the aFN to set
	 */
	public void setAFN(String aFN) {
		AFN = aFN;
	}

	/**
	 * @return the dIR
	 */
	public String getDIR() {
		return DIR;
	}

	/**
	 * @param dIR the dIR to set
	 */
	public void setDIR(String dIR) {
		DIR = dIR;
	}

	/**
	 * @return the cNT
	 */
	public int getCNT() {
		return CNT;
	}

	/**
	 * @param cNT the cNT to set
	 */
	public void setCNT(int cNT) {
		CNT = cNT;
	}

	/**
	 * @return the iMSI
	 */
	public String getIMSI() {
		return IMSI;
	}

	/**
	 * @param iMSI the iMSI to set
	 */
	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	/**
	 * @return the contentObj
	 */
	public ReceiveCode getContentObj() {
		return ContentObj;
	}

	/**
	 * @param contentObj the contentObj to set
	 */
	public void setContentObj(ReceiveCode contentObj) {
		ContentObj = contentObj;
	}

	
}
