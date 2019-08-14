/**
 * 
 */
package com.wo.task;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wo.mapper.ChargeRecordMapper;
import com.wo.util.CommFunc;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
public class BackupChargeRecordTask extends QuartzJobBean{

	@Resource
	private ChargeRecordMapper chargeRecordMapper;
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
	
		// 备份充电记录监控任务
		backUpChargeRecord();
		
		//备份充电单
		backUpChargeOrder();

	}
	
	private void backUpChargeRecord(){
		try {
			Calendar c = Calendar.getInstance();
			int nowYear = c.get(Calendar.YEAR);
			c.add(Calendar.DAY_OF_YEAR, -31);
			int lastYear = c.get(Calendar.YEAR);
			
			String tableNowYear = "cpdata.charge_record" + nowYear;
			String tableLastYear = "cpdata.charge_record" + lastYear;
			
			Map<String, Object> param = new HashMap<String, Object>();
			if (nowYear == lastYear) {
				param.put("tableName", tableNowYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeRecord(param)) {
					chargeRecordMapper.deleteChargeRecords(param);
				}
			}else {
				param.put("tableName", tableNowYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeRecord(param)) {
					chargeRecordMapper.deleteChargeRecords(param);
				}
				
				param.put("tableName", tableLastYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeRecord(param)) {
					chargeRecordMapper.deleteChargeRecords(param);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("备份充电监控记录任务失败");
			Log4jUtil.getError().error("备份充电记录监控任务失败", e);
		}
	}
	
	private void backUpChargeOrder(){
		try {
			Calendar c = Calendar.getInstance();
			int nowYear = c.get(Calendar.YEAR);
			c.add(Calendar.DAY_OF_YEAR, -31);
			int lastYear = c.get(Calendar.YEAR);
			
			String tableNowYear = "cpdata.yys_carowner_historyorder" + nowYear;
			String tableLastYear = "cpdata.yys_carowner_historyorder" + lastYear;
			
			Map<String,Object> param = new HashMap<String, Object>();
			if (nowYear == lastYear) {
				param.put("tableName", tableNowYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeOrder(param)) {
					chargeRecordMapper.deleteChargeOrder(param);
				}
			}else {
				param.put("tableName", tableNowYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeOrder(param)) {
					chargeRecordMapper.deleteChargeOrder(param);
				}
				
				param.put("tableName", tableLastYear);
				param.put("ymd", CommFunc.nowDateStr());
				if (chargeRecordMapper.backupChargeOrder(param)) {
					chargeRecordMapper.deleteChargeOrder(param);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("备份充电单任务失败");
			Log4jUtil.getError().error("备份充电单任务失败", e);
		}
		
		
	}
}
