/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
* @version V1.0   
*/
package com.nb;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.model.NbCommand;
import com.nb.model.fx.FXReport;
import com.nb.model.jd.Battery;
import com.nb.model.jd.DeviceAlarm;
import com.nb.model.jd.PeriodReport;
import com.nb.model.xt.SuntrontBattery;
import com.nb.model.xt.SuntrontWaterMeterAlarm;
import com.nb.task.FxTelecomCallDataTask;
import com.nb.utils.CommFunc;
import com.nb.utils.CommandEnum;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.SerializeUtils;

import io.lettuce.core.protocol.CommandEncoder;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
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
		
		System.out.println(new Date(1557158400986L));
		
//		Map<String, String> dataMap = new HashMap<String, String>();
//		dataMap.put("highFlowAlarm", "0");
//		SuntrontWaterMeterAlarm suntrontwatermeteralarm = JsonUtil.map2Bean(dataMap, SuntrontWaterMeterAlarm.class);
//			
//		System.out.println(suntrontwatermeteralarm.getHighFlowAlarm());
//		XtBattery x = new XtBattery();
//		x.setEvnetTime("20180417T121245Z");
//		System.out.println(x.getEventDate() + "  " + x.getEventTime());
//		
//		
//		Date date = DateUtils.utcToLocal("20190428T012017Z", DateUtils.UTC_PATTERN);
//		System.out.println(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN)+" " + DateUtils.formatDateByFormat(date,DateUtils.TIME_PATTERN));
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);
//		System.out.println(DateUtils.formatDateByFormat(endDate.getTime(), DateUtils.DATE_PATTERN));
//		System.out.println(FxTelecomCallDataTask.class.getName());
		
//		System.out.println(DateUtils.parseTimesTampDate("20190424", DateUtils.DATE_PATTERN));
		// TODO Auto-generated method stub
		
//		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//		        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());   
		
		
//		Date ymd = DateUtils.parseDate(toStr(20190423), DateUtils.DATE_PATTERN);
//		Calendar cc = Calendar.getInstance();
//		cc.setTime(ymd);
//		cc.add(Calendar.DAY_OF_MONTH, -1);
//		
//		int date = toInt(DateUtils.parseDate(cc.getTime(), DateUtils.DATE_PATTERN));
//		
//		System.out.println(date);
//		Date date = new Date(1556153491636L);
//		System.out.println(date);
//		Map<String, Object> map = new HashMap<>(6);
//		System.out.println(map.size());
//		for (int i = 0; i < 100; i++) {
//			map.put("" + i, i);
//			i++;
//		}
//		System.out.println(map.size());

//		executorService.execute(command);
		
//		System.out.println(CommandEnum.getresultValue("SEND"));
//		Map<String, String> registerInfo = new HashMap<>();
//		registerInfo.put("deviceId", "232321");
//		
//		
//		String deviceId = registerInfo.get("deviceId");
//		System.out.println(deviceId != null && !deviceId.isEmpty());

//		ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
//		System.out.println(result);
		
		
//		JSONObject deviceInfo = new JSONObject();
//		deviceInfo.put("rtuId", 11);
//		deviceInfo.put("mpId", 2);
//		
//		Map<String, String> registerInfo = new HashMap<>();
//		registerInfo = JsonUtil.jsonString2SimpleObj(deviceInfo, registerInfo.getClass());
//		
//		System.out.println("registerInfo : "+ registerInfo);
		
//		FXReport fx = new FXReport();
//		fx.setCurrentDateTime(DateUtils.formatTimesTampDate(new Date()));
//		
//		System.out.println(fx.getDate() + " " + fx.getTime());
//		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		
//		PeriodReport r = new PeriodReport();
//		r.setFlows(list);
//		
//		System.out.println(r.getFlows());
//		Map<String,String> map = new HashMap<>();
//		map.put("readTime", "1555084800338");
//		map.put("cumulativeFlow", "102");
//		map.put("positiveCumulativeFlow", "2");
//		map.put("negativeCumulativeFlow", "3");
//		map.put("peakFlowRateTime", "4");
//		map.put("startTime", "1");
//		map.put("flows", "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
//
//		int index =0;
//			PeriodReport periodReport = null;
//			try {
//				periodReport = JsonUtil.map2Bean(map, PeriodReport.class);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				index ++;
//			}
//			System.out.println(periodReport.toString());
//		
//		System.out.println("index : " + index);
		
		
		
//		System.out.println(DateUtils.stampToDate(System.currentTimeMillis()));
		
//		DeviceAlarm b = JsonUtil.map2Bean(map, DeviceAlarm.class);
		
//		System.out.println(b.toString());
		Long s = null;
//		System.out.println( DateUtils.stampToDate(ConverterUtils.toLong(null)));
		
//		NbCommand command = new NbCommand();
//		command.setRtuId(1);
//		command.setCommandContent("dfdfdfsdf");
//		String jsonStr = JsonUtil.jsonObj2Sting(command);
//		System.out.println(jsonStr);
//		
//		command = JsonUtil.jsonString2SimpleObj(jsonStr, NbCommand.class);
//		System.out.println(command.getRtuId());
	}

}
