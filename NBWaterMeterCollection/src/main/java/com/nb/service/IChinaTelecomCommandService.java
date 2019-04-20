/**   
* @Title: IChinaTelecomCommandService.java 
* @Package com.nb.service.chinatelecomimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月17日 下午3:46:59 
* @version V1.0   
*/
package com.nb.service;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;

/** 
* @ClassName: IChinaTelecomCommandService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月17日 下午3:46:59 
*  
*/
public interface IChinaTelecomCommandService {

	/**
	 * @param command{"rtuId":"1","mpId":"1","commandClass":"1","appId":"YXnpwWndC3ioc15OGALXZvHLB1oa",
	 * "deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","commandId":"1",operatorId":"1",
	 * "param":"{\"value\":\"12\"}"
	 *            }
	 * @return
	 * @throws Exception
	 */
	ResultBean<?> asynCommand(JSONObject command) throws Exception;

	
	/** 
	* @Title: batchCommand 
	* @Description: 批量下发命令
	* @param @param command
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	ResultBean<?> batchCommand(JSONObject command) throws Exception;

}