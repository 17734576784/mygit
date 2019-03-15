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
	
	// 获取最新任务列表
	List<ScheduleJob> findLegalJobList();

	// 获取最新删除(禁用)任务列表
	List<ScheduleJob> findDelJobList();

}
