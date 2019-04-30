/**   
* @Title: ITaskService.java 
* @Package com.ke.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午4:43:09 
* @version V1.0   
*/
package com.ke.service;

/**
 * @ClassName: ITaskService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月10日 下午4:43:09
 * 
 */
public interface ITaskService {
	/** 
	* @Title: pushMessageTask 
	* @Description: 消息推送任务 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void pushMessageTask();

	/** 
	* @Title: backupChargeMonitorTask 
	* @Description: 备份充电单监控
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void backupChargeMonitorTask();

	/** 
	* @Title: backUpChargeOrderTask 
	* @Description: 备份充电单 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void backUpChargeOrderTask();
}
