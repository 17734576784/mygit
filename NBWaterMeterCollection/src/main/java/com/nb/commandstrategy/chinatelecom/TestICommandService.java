/**   
* @Title: TestICommandService.java 
* @Package com.nb.commandstrategy.chinatelecom 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午11:43:40 
* @version V1.0   
*/
package com.nb.commandstrategy.chinatelecom;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nb.commandstrategy.ICommandService;

/** 
* @ClassName: TestICommandService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 上午11:43:40 
*  
*/
@Service
public class TestICommandService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param commandMap 
	* @see com.nb.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		// TODO Auto-generated method stub

	}

}
