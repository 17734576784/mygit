/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
* @version V1.0   
*/
package com.nb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.model.NbCommand;
import com.nb.model.fx.FXReport;
import com.nb.model.jd.Battery;
import com.nb.model.jd.DeviceAlarm;
import com.nb.model.jd.PeriodReport;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.SerializeUtils;

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
		// TODO Auto-generated method stub
		
		FXReport fx = new FXReport();
		fx.setCurrentDateTime(DateUtils.formatTimesTampDate(new Date()));
		
		System.out.println(fx.getDate() + " " + fx.getTime());
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
