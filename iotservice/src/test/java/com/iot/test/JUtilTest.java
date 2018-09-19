//package com.iot.test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSONObject;
//import com.iot.IotserviceApplication;
//import com.iot.utils.JedisUtils;
//import com.iot.utils.Log4jUtils;
//
///**
// * @author dbr
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = { IotserviceApplication.class })
//public class JUtilTest {
//
//	@Autowired
//	private JedisUtils jedisUtils;
//
//	@Test
//	public void StrategyTest() {
////		int totalpack = 13,packnum =1;
////		byte[] b1 = new byte[1];
////		b1[0] = 1;
////		
////		Map<Integer,byte[]> photoMap = new LinkedHashMap<Integer,byte[]>(totalpack);
////		for (int i = 0; i < totalpack; i++) {
////			photoMap.put(packnum, null);
////			packnum++;
////		}
////		photoMap.put(1, b1);
////		
//		String key ="11111";
////		
////		JSONObject photoJson = new JSONObject();
////		photoJson.put("packnum", 1);
////		photoJson.put("data", photoMap);
////		jedisUtils.set(key, photoJson, 60 * 60 * 2);
////		
////		byte[] b2 = new byte[1];
////		b2[0] = 2;
////		packnum = 2;
////
////		photoJson = (JSONObject) jedisUtils.get(key);
////		photoMap = (Map<Integer, byte[]>) photoJson.get("data");
////		
////		photoMap.put(packnum, b2);
////		
////		photoJson.put("packnum", ++packnum);
////
////		jedisUtils.set(key, photoJson, 60 * 60 * 2);
////
////		
////		byte[] b4 = new byte[1];
////		b4[0] = 4;
////		packnum = 4;
////
////		JSONObject photoJson = (JSONObject) jedisUtils.get("59219e82-5fc7-46e1-909a-979638c2295b");
////		LinkedHashMap<Integer, byte[]> photoMap = (LinkedHashMap<Integer, byte[]>) photoJson.get("data");
////		System.out.println(photoMap.size());
//
////		photoMap.put(packnum, b4);
////		
////		photoJson.put("packnum", ++packnum);
//
////		jedisUtils.set(key, photoJson, 60 * 60 * 2);
//
//	}
//}
