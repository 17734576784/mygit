/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月14日 上午9:42:25 
* @version V1.0   
*/
package com.nb;

import static org.assertj.core.api.Assertions.contentOf;

import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年2月14日 上午9:42:25 
*  
*/
public class MainTest {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Map<String,Object>> command =  new HashMap<String,Map<String,Object>>();
		
		Map<String,Object> TimeServiceMap = new HashMap<String,Object>();
		TimeServiceMap.put("serviceId", "TimeService");
		TimeServiceMap.put("method", "commandUpTimeService");		
		command.put("1001", TimeServiceMap);
		command.put("2001", TimeServiceMap);
		
		Map<String,Object> AlarmServiceMap = new HashMap<String,Object>();
		AlarmServiceMap.put("serviceId", "AlarmService");
		AlarmServiceMap.put("method", "openalarm");		
		command.put("1002", AlarmServiceMap);
		command.put("2002", AlarmServiceMap);
		
		
		Map<String,Object> PhotoServiceMap = new HashMap<String,Object>();
		PhotoServiceMap.put("serviceId", "PhotoService");
		PhotoServiceMap.put("method", "sendphotoonce");		
		command.put("1003", PhotoServiceMap);
		command.put("2003", PhotoServiceMap);
		
		
		Map<String,Object> CameraServiceMap = new HashMap<String,Object>();
		CameraServiceMap.put("serviceId", "CameraService");
		CameraServiceMap.put("method", "adjustcamera ");		
		command.put("1004", CameraServiceMap);
		command.put("2004", CameraServiceMap);
		
		
		Map<String,Object> TimeServiceMapMobile = new HashMap<String,Object>();
		TimeServiceMapMobile.put("obj_id", "1");
		TimeServiceMapMobile.put("obj_inst_id", "2");
		TimeServiceMapMobile.put("res_id", "3");
		command.put("3001", TimeServiceMapMobile);
		
		System.out.println(command);
	 
	}

}
