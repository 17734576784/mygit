/**   
* @Title: MainTest.java 
* @Package com.ke 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月15日 上午8:56:52 
* @version V1.0   
*/
package com.ke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.ke.http.HttpsClientUtil;
import com.ke.http.StreamClosedHttpResponse;
import com.ke.model.MemberOrders;
import com.ke.model.Substpara;
import com.ke.model.TradeMsgOuterClass;
import com.ke.utils.ConverterUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.JsonUtil;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月15日 上午8:56:52 
*  
*/
public class MainTest {

	/**
	 * @throws Exception  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) throws Exception {
		
		
//		byte[] tt = new byte[] {10, 12, 75, 69, 48, 48, 48, 48, 48, 48, 48, 48, 54, 51, 16, 1, 26, 12, 48, 48, 56, 57,
//				48, 48, 48, 48, 48, 48, 57, 48, 41, 118, 113, 27, 13, -17, -65, -67, 45, -17, -65, -67, 63};	
		byte[] tt = new byte[] {10, 12, 75, 69, 48, 48, 48, 48, 48, 48, 48, 48, 54, 51, 16, 1, 26, 10, 48, 48, 56, 57, 48, 48, 48, 48, 57,
		 48, 41, 118, 113, 27, 13, -17, -65, -67, 45, -17, -65, -67, 63};
		try {
			TradeMsgOuterClass.TradeMsgChargeBegin_Inf i = TradeMsgOuterClass.TradeMsgChargeBegin_Inf.parseFrom(tt);
			System.out.println(i.getPaySerial() +"  "+ i.getFlag() +"  "+i.getReadings());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		 HttpsClientUtil http = new HttpsClientUtil();
		 String url = "http://127.0.0.1:19999/ydsupport/excel/export";
		 Map<String,String> param = new HashMap<>();
		 param.put("title", "test");
		 param.put("fileName", "角色档案1");
//		 param.put("head", new String[]{"序号","角色名称","角色描述"});
//		 param.put("beanPropertys", .toString());
		 param.put("className", "com.support.serviceimpl.system.RoleFlowServiceImpl");
		 param.put("methodName", "listRoleFlow");
		 param.put("methodParam", "");
  		 	 
		 StreamClosedHttpResponse re = http.doGetWithParasGetStatusLine(url, param, null);
		 System.out.println(re.getContent());
//
//		int size = 50000;
//		List<Substpara> list = new LinkedList<Substpara>();
//		System.out.println("开始生成：");
//		for (int i = 0; i < size; i++) {
//			Substpara s = new Substpara();
//			s.setId(i);
//			s.setSubstName(""+i);
//			s.setLongitude(i * 1D);
//			s.setLatitude(i*1D);
//			list.add(s);
//		}
//		System.out.println("生成结束");
//		System.out.println("开始串行处理");
//		long start = System.nanoTime();
//		List<JSONObject> count = list.stream()	
//				.filter(substpara -> substpara!= null)
//				.map(substpara -> getSubstpara(substpara)).collect(Collectors.toList());
//				
//		long end = System.nanoTime();
//		long dis = TimeUnit.NANOSECONDS.toMillis(end - start);
//		System.out.println("串行结束 耗时：" + dis);
		
		
//		System.out.println("开始并行");
//		long start = System.nanoTime();
//		List<JSONObject> count = list.stream().parallel().filter(substpara -> substpara != null).map(substpara -> getSubstpara(substpara))
//				.collect(Collectors.toList());
//	
//		for (int i =0; i < list.size(); i++) {
//			count.add(getSubstpara(list.get(i)));
//		}
		
//		long end = System.nanoTime();
//		long dis = TimeUnit.NANOSECONDS.toMillis(end - start);
//		System.out.println("bing行结束 耗时：" + dis);
	}
	
	public static JSONObject getSubstpara(Substpara substpara) {
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("stationNo", ConverterUtil.toStr(substpara.getId()));
		jsonItem.put("stationName", ConverterUtil.toStr(substpara.getSubstName()));
		jsonItem.put("lng", ConverterUtil.toStr(substpara.getLongitude()));
		jsonItem.put("lat", ConverterUtil.toStr(substpara.getLatitude()));
		return jsonItem;
	}
}
