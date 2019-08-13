/**
 * 
 */
package com.ke.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.costumer.OverPushCustomerThread;
import com.ke.costumer.SocPushCustomerThread;
import com.ke.costumer.StartPushCustomerThread;

/**
 * @author dbr
 *
 */
@RestController
@RequestMapping("/pile")
public class TestController {

	@RequestMapping("/login.json")
	public String login(String queryJsonStr) {
		System.out.println("测试第三方登录：" + queryJsonStr);
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("token", "ABCDE123ASDF");
		return rtnJson.toJSONString();
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/chargeStart.json")
	public String chargeStart(String token, String queryJsonStr) throws UnsupportedEncodingException {
		System.out.println("测试第三方开始推送：" + java.net.URLDecoder.decode(queryJsonStr,"utf-8"));

		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		return rtnJson.toJSONString();
	}

	@RequestMapping("/chargeOver.json")
	public String chargeOver(String token, String queryJsonStr) {
		System.out.println("测试第三方结束推送：" + queryJsonStr);
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		return rtnJson.toJSONString();
	}
	
	@RequestMapping("/gunAlarm.json")
	public String gunAlarm(String token, String queryJsonStr) {
		System.out.println("测试第三方结束推送：" + queryJsonStr);
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		return rtnJson.toJSONString();
	}
	
	@RequestMapping("/chargeRealData.json")
	public String chargeRealData(String token, String queryJsonStr) {
		System.out.println("测试第三方直流首次推送：" + queryJsonStr);
		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		return rtnJson.toJSONString();
	}
	
	@RequestMapping("/startPush.json")
	public String startThreadPool() {
		StartPushCustomerThread.startPushCustomerRunFlag = true;
		OverPushCustomerThread.overPushCustomerRunFlag = true;
		SocPushCustomerThread.socPushCustomerRunFlag = true;

		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "启动线程池成功");
		return rtnJson.toJSONString();
	}

	@RequestMapping("/stopPush.json")
	public String stopThreadPush() {
		StartPushCustomerThread.startPushCustomerRunFlag = false;
		OverPushCustomerThread.overPushCustomerRunFlag = false;
		SocPushCustomerThread.socPushCustomerRunFlag = false;

		JSONObject rtnJson = new JSONObject();
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "暂停线程池成功");
		return rtnJson.toJSONString();
	}
}
