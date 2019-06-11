/**   
* @Title: ThreadManageController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午3:21:27 
* @version V1.0   
*/
package com.nb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nb.customer.alarm.AlarmCustomerThread;
import com.nb.customer.historydatabase.BatteryDataCustomerThread;
import com.nb.customer.historydatabase.DailyDataCustomerThread;
import com.nb.customer.historydatabase.InstanceDataCustomerThread;
import com.nb.utils.Constant;

/** 
* @ClassName: ThreadManageController 
* @Description: 各种消费者线程启停控制器 
* @author dbr
* @date 2019年3月11日 下午3:21:27 
*  
*/
@RestController
public class ThreadManageController {
	
	@RequestMapping("/start")
	public String startAll() {
		DailyDataCustomerThread.historyDatabaseRunFlag = true;
		InstanceDataCustomerThread.historyDatabaseRunFlag = true;
		BatteryDataCustomerThread.historyDatabaseRunFlag = true;
		AlarmCustomerThread.alarmCustomerRunFlag = true;
		return Constant.OK;
	}
	
	@RequestMapping("/stop")
	public String stopAll() {
		DailyDataCustomerThread.historyDatabaseRunFlag = false;
		InstanceDataCustomerThread.historyDatabaseRunFlag = false;
		BatteryDataCustomerThread.historyDatabaseRunFlag = false;
		AlarmCustomerThread.alarmCustomerRunFlag = false;

		return Constant.OK;
	}
	
	@RequestMapping("/startHistoryDatabase")
	public String startHistoryDatabase() {
		DailyDataCustomerThread.historyDatabaseRunFlag = true;
		InstanceDataCustomerThread.historyDatabaseRunFlag = true;
		BatteryDataCustomerThread.historyDatabaseRunFlag = true;

		return Constant.OK;
	}

	@RequestMapping("/stopHistoryDatabase")
	public String stopHistoryDatabase() {
		DailyDataCustomerThread.historyDatabaseRunFlag = false;
		InstanceDataCustomerThread.historyDatabaseRunFlag = false;
		BatteryDataCustomerThread.historyDatabaseRunFlag = false;
		return Constant.OK;
	}
	
	@RequestMapping("/startAlarmEvent")
	public String startAlarmEvent() {
		AlarmCustomerThread.alarmCustomerRunFlag = true;
		return Constant.OK;
	}

	@RequestMapping("/stopAlarmEvent")
	public String stopAlarmEvent() {
		AlarmCustomerThread.alarmCustomerRunFlag = false;
		return Constant.OK;
	}

}
