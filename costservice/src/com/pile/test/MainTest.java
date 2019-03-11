/**   
* @Title: MainTest.java 
* @Package com.pile.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年3月26日 下午5:05:02 
* @version V1.0   
*/
package com.pile.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.pile.common.Constant;
import com.pile.model.ChargeInfo;
import com.pile.netty.message.ChargeInfoBufOuterClass;
import com.pile.netty.message.ChargeInfoBufOuterClass.ChargeInfoBuf;
import com.pile.netty.message.ResultOuterClass;
import com.pile.utils.DateUtils;
import com.pile.utils.JedisUtils;
import com.pile.utils.JsonUtils;
import com.pile.utils.SerializeUtils;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年3月26日 下午5:05:02 
*  
*/
public class MainTest {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		try {
			a();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void a() throws Exception {
		try {
			b();
			c();
		} catch (Exception e) {
			System.out.println("a");
			throw e;
		}
	}
	
	public static void b() throws Exception{
		try {
			int a = 3/0;
		} catch (Exception e) {
			System.out.println("b");
			throw e;
		}
	}
	
	public static void c() throws Exception{
		try {
			int a = 3/0;
		} catch (Exception e) {
			System.out.println("c");
			throw e;
		}
	}
}
