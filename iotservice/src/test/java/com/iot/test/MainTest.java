package com.iot.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.DateUtils;
import com.iot.utils.HttpsUtils;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException { 
		String base =  "http://222.222.60.178:18130/Enterprise_EnnGas/enngas/message/";
		String apiUrl = base + Constant.UPLOAD_ALARMCOMMAND_URL;
String a="ddddddddddddddddd";
		Map<String, Object> paramJson =  new HashMap<>();
		paramJson.put("slope", 1);
		paramJson.put("magnetic", 1);
		paramJson.put("alarmtype", 1);
		
		paramJson.put("date", DateUtils.curDate());
		paramJson.put("time", DateUtils.curTime());
		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
		
		apiUrl = base + "nbNotifyAction!notifyAlarm.action";
		
//		paramJson.put("cycle", 0);
//		paramJson.put("devicetime", 13);
//		paramJson.put("status", 0);
//		paramJson.put("date", DateUtils.curDate());
//		paramJson.put("time", DateUtils.curTime());
//		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
//		apiUrl = base + Constant.UPLOAD_TIMECOMMAND_URL;
		
//		paramJson.put("check", 0);
//		paramJson.put("date", DateUtils.curDate());
//		paramJson.put("time", DateUtils.curTime());
//		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
//		apiUrl = base + "nbNotifyAction!checkExistOrders.action";

		Map<String, Object> urlMap = new HashMap<>();
		urlMap.put("param", paramJson.toString());
		try {
			JSONObject httpResult = HttpsUtils.doPost(apiUrl, urlMap);
			System.out.println(httpResult);
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.INFO).info("推送设置告警命令回复失败，设备id：" );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
//		String txt="{\"status\":0,\"error\":\"\"}";
//		
//		System.out.println(JSON.parseObject(txt));
	}
}
