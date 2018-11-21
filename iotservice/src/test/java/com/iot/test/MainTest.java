package com.iot.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.springframework.data.redis.util.ByteUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.BytesUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.DateUtils;
import com.iot.utils.FileUtils;
import com.iot.utils.HttpsUtils;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException { 
		
//        String dateHex = String.format("%06x",1577237);
//        System.out.println(dateHex);
		byte[] start = BytesUtils.getBytes((byte) 0X68); // 一个字节
		byte[] dataLen = BytesUtils.getBytes((short) 2000);// 2个字节
		byte[] fileFlag = BytesUtils.getBytes((byte) 0x00);// 一个字节
		byte[] fileAttr = BytesUtils.getBytes((byte) 0x01);// 一个字节
		byte[] totalPack = BytesUtils.getBytes((short) 35);// 2个字节
		byte[] curPack = BytesUtils.getBytes((short)3);
		byte[] data = new byte[256];		
		byte[] cs = BytesUtils.getBytes((byte)2); // 2个字节
		byte[] end = BytesUtils.getBytes((byte) 0X16);// 2个字节
		
		byte[] tmp = BytesUtils.byteMergerAll(start, dataLen, fileFlag, fileAttr, totalPack, curPack, data, cs, end);
		
//		System.out.println(tmp.length);
//		for (byte b : tmp) {
//			System.out.print(b +"  ");	
//		}
        int length = 0;
		byte[] rstart = new byte[1];
		System.arraycopy(tmp, 0, rstart, 0, rstart.length);
		length += rstart.length;
		System.out.println(BytesUtils.bytesToHex(rstart));
		
		byte[] rdataLen = new byte[2];
		System.arraycopy(tmp, length, rdataLen, 0, rdataLen.length);
		length += rdataLen.length;
		short dataLenr = BytesUtils.getShort(rdataLen);
		System.out.println(dataLenr);
		
		
		
		
		String base =  "http://222.222.60.178:18130/Enterprise_EnnGas/enngas/message/";
		String apiUrl = base + Constant.UPLOAD_ALARMCOMMAND_URL;
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
//			JSONObject httpResult = HttpsUtils.doPost(apiUrl, urlMap);
//			System.out.println(httpResult);
//			if (httpResult != null && !httpResult.isEmpty()) {
//				if (httpResult.getInteger("status") == Constant.ERROR) {
//					LoggerUtils.Logger(LogName.INFO).info("推送设置告警命令回复失败，设备id：" );
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
//		String txt="{\"status\":0,\"error\":\"\"}";
//		
//		System.out.println(JSON.parseObject(txt));
	}
}
