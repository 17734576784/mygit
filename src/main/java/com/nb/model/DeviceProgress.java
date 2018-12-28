/**   
* @Title: DeviceProgress.java 
* @Package com.iot.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月5日 下午5:47:00 
* @version V1.0   
*/
package com.nb.model;

/**
 * @ClassName: DeviceProgress
 * @Description: 设备升级进度
 * @author dbr
 * @date 2018年12月5日 下午5:47:00
 * 
 */
public class DeviceProgress {

	/** 设备ID */
	private String deviceId;
	/** 升级文件缓存key */
	private String fileKey;
	/** 升级总包数 */
	private short packNum;
	/** 当前发送报数 */
	private short sendedPack;
	/** 最近发送时间 */
	private String sendTime;
	/** 当前包重试次数 */
	private int retryCount;
	/** 收到已送达标志 */
	private boolean receiveFlag;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public short getPackNum() {
		return packNum;
	}

	public void setPackNum(short packNum) {
		this.packNum = packNum;
	}

	public short getSendedPack() {
		return sendedPack;
	}

	public void setSendedPack(short sendedPack) {
		this.sendedPack = sendedPack;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public boolean isReceiveFlag() {
		return receiveFlag;
	}

	public void setReceiveFlag(boolean receiveFlag) {
		this.receiveFlag = receiveFlag;
	}

	@Override
	public String toString() {
		return "DeviceProgress [deviceId=" + deviceId + ", fileKey=" + fileKey + ", packNum=" + packNum
				+ ", sendedPack=" + sendedPack + ", sendTime=" + sendTime + ", retryCount=" + retryCount
				+ ", receiveFlag=" + receiveFlag + "]";
	}

	
}
