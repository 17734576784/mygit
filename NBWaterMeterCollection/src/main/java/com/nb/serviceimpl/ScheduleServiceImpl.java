/**   
* @Title: ScheduleServiceImpl.java 
* @Package com.nb.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月12日 上午10:31:17 
* @version V1.0   
*/
package com.nb.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nb.model.ScheduleJob;
import com.nb.service.IScheduleService;

/** 
* @ClassName: ScheduleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月12日 上午10:31:17 
*  
*/
@Service
public class ScheduleServiceImpl implements IScheduleService {

	/** (非 Javadoc) 
	* <p>Title: findLegalJobList</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.nb.service.IScheduleService#findLegalJobList() 
	*/
	@Override
	public List<ScheduleJob> findLegalJobList() {
		// TODO Auto-generated method stub
		List<ScheduleJob> list = new ArrayList<ScheduleJob>();
		return list;
	}

	/** (非 Javadoc) 
	* <p>Title: findDelJobList</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.nb.service.IScheduleService#findDelJobList() 
	*/
	@Override
	public List<ScheduleJob> findDelJobList() {
		// TODO Auto-generated method stub
		List<ScheduleJob> list = new ArrayList<ScheduleJob>();
		return list;
	}

}
