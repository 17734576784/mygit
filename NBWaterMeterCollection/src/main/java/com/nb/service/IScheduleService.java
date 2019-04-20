/**   
* @Title: IScheduleService.java 
* @Package com.nb.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月12日 上午10:27:24 
* @version V1.0   
*/
package com.nb.service;

import java.util.List;

import com.nb.model.ScheduleJob;

/**
 * @ClassName: IScheduleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年3月12日 上午10:27:24
 * 
 */
public interface IScheduleService {
	
	/** 
	* @Title: findLegalJobList 
	* @Description: 获取最新任务列表 
	* @param @return    设定文件 
	* @return List<ScheduleJob>    返回类型 
	* @throws 
	*/
	List<ScheduleJob> findLegalJobList();

	/** 
	* @Title: findDelJobList 
	* @Description: 获取最新删除(禁用)任务列表
	* @param @return    设定文件 
	* @return List<ScheduleJob>    返回类型 
	* @throws 
	*/
	List<ScheduleJob> findDelJobList();
	
	/** 
	* @Title: test 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void test();

}
