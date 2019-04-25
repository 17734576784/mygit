/**   
* @Title: HistoryDataTask.java 
* @Package com.nb.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 上午10:22:55 
* @version V1.0   
*/
package com.nb.task;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nb.mapper.TaskMapper;
import com.nb.service.IChinaTelecomCommandService;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;

/** 
* @ClassName: FxTelecomCallDataTask 
* @Description: 府星电信平台补招任务
* @author dbr
* @date 2019年4月15日 上午10:22:55 
*  
*/
public class FxTelecomCallDataTask implements Job{
	
	@Resource
	private TaskMapper taskMapper;
	
	@Autowired
	private IChinaTelecomCommandService chinaTelecomCommandService;
	
	/** (非 Javadoc) 
	* <p>Title: execute</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String className = this.getClass().getName();
		Map<String,Object> batchCmdInfo = taskMapper.getBatchCommandByClass(className);
		
		JSONObject command = JSONObject.parseObject(JSON.toJSONString(batchCmdInfo));
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);
		
		JSONObject param = new JSONObject();
		param.put("AFN", 19);
		param.put("IMSI", "00000000000000000000");
		param.put("CNT", Constant.THREE);
		param.put("DIR", Constant.ZERO);
		param.put("ReportBaseTime", DateUtils.formatDateByFormat(new Date(), "YYYY-MM-dd HH:mm:ss"));
		param.put("ReportIntervalHours", Constant.ONE);
		for (int i = 0; i < Constant.TASK_CALL_DAYS; i++) {
			String date = DateUtils.formatDateByFormat(endDate.getTime(), DateUtils.DATE_PATTERN);
			batchCmdInfo.put("ymd", date);
			batchCmdInfo.put("tableName", "YDData.dbo.nb_daily_data_" + date.substring(0, 6));
			endDate.add(Calendar.DAY_OF_YEAR, -1);

			List<String> deviceIdList = taskMapper.listDeviceIdByAppModel(batchCmdInfo);
			if (deviceIdList.isEmpty()) {
				continue;
			}
			command.put("deviceList", deviceIdList);
			command.put("param", param.toString());
			try {
				this.chinaTelecomCommandService.batchCommand(command);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}

}
