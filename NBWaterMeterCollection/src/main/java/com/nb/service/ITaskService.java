/**   
* @Title: ITaskService.java 
* @Package com.nb.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月5日 下午4:28:36 
* @version V1.0   
*/
package com.nb.service;

/** 
* @ClassName: ITaskService 
* @Description: 定时任务执行类接口
* @author dbr
* @date 2019年5月5日 下午4:28:36 
*  
*/
public interface ITaskService {
	/** 
	* @Title: callHistoryData 
	* @Description: 根据任务类名补招历史数据
	* @param @param className    设定文件
	* @return void    返回类型 
	* @throws 
	*/
	void callHistoryData(String className);
}
