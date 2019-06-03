/**   
* @Title: IChinaMobileSuntrontService.java 
* @Package com.nb.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月17日 下午2:05:32 
* @version V1.0   
*/
package com.nb.service;

import com.alibaba.fastjson.JSONObject;

/** 
* @ClassName: IChinaMobileService 
* @Description: 新天科技移动平台规约解析服务 
* @author dbr
* @date 2019年5月17日 下午2:05:32 
*  
*/
public interface IChinaMobileService {
	void parseDataPointMsg(JSONObject msgJson)  throws Exception;
	
	void parseCommandMsg(JSONObject msgJson)  throws Exception;

}
