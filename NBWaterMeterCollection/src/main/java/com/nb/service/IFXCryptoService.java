/**   
* @Title: IFXCryptoService.java 
* @Package com.nb.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午2:41:16 
* @version V1.0   
*/
package com.nb.service;

import com.sun.jna.Library;
import com.sun.jna.Native;

/** 
* @ClassName: IFXCryptoService 
* @Description: 府星移动平台dll规约解析服务 
* @author dbr
* @date 2019年5月24日 下午2:41:16 
*  
*/
public interface IFXCryptoService extends Library {
	IFXCryptoService instance = (IFXCryptoService) Native.loadLibrary("dll/FXCrypto", IFXCryptoService.class);

	public String ServerReceiveData(String data, int hasmore, int mid);
	
	public String ServerSendData(String data, int hasmore, int mid, String serviceId);

}
