/**   
* @Title: PhotoService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:36:02 
* @version V1.0   
*/
package com.nb.commandstrategy.chinatelecom;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.nb.commandstrategy.ICommandService;

/** 
* @ClassName: ChinaTelecomCommandPhotoService 
* @Description: 上传照片命令上行解析服务
* @author dbr
* @date 2018年10月25日 下午2:36:02 
*  
*/
@Component
public class ChinaTelecomCommandPhotoService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub

	}

}
