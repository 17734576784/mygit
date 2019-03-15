/**   
* @Title: BackUpOrderDiscountRecord.java 
* @Package com.pile.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月18日 上午10:53:33 
* @version V1.0   
*/
package com.pile.task;

import java.util.Calendar;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.pile.mapper.CalculateFeeMapper;
import static com.pile.utils.DateUtils.*;
import com.pile.utils.Log4jUtils;

/** 
* @ClassName: BackUpOrderDiscountRecord 
* @Description: 备份充电单折扣记录  每月1号备份上个月的
* @author dbr
* @date 2018年8月18日 上午10:53:33 
*  
*/
public class BackUpOrderDiscountRecordTask extends QuartzJobBean{

	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	/** (非 Javadoc) 
	* <p>Title: executeInternal</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext) 
	*/
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//		backUpOrderDiscountRecord();
	}

	public void backUpOrderDiscountRecord(){
		try {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			String runDate = formatDateByFormat(c.getTime(), "yyyyMMdd");
			
			c.add(Calendar.MONTH, -1);
			String ym = formatDateByFormat(c.getTime(), "yyyyMM");
			String tableName = "chargedata.order_discount_record" + ym;

			calculateFeeMapper.backUpOrderDiscountRecord(tableName, runDate);
		} catch (Exception e) {
			Log4jUtils.getError().error("备份充电单折扣记录异常" + e.getMessage());
			e.printStackTrace();
		}
	}
}
