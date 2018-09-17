/**
 * 
 */
package com.iot.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.HttpsUtil;
import com.iot.utils.Log4jUtils;

/**
 * @ClassName: SubscribeNotifyController
 * @Description:订阅平台消息
 * @author: dbr
 * @date: 2018年9月5日 上午10:58:57
 * 
 */
@RestController
public class SubscribeNotifyController {

	/**
	 * @param notify{"notifyType":notifyType,"callbackurl":callbackurl}
	 * @return
	 */
	@RequestMapping(value = "subscribeNotify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject subscribeNotify(String  notify) {
		JSONObject rtnJson = new JSONObject();
		try {
			HttpsUtil httpsUtil = new HttpsUtil();
			httpsUtil.initSSLConfigForTwoWay();

			String jsonRequest = notify;
			String appId = Constant.APPID;
			String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
			String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

			Map<String, String> header = new HashMap<>();
			header.put(Constant.HEADER_APP_KEY, appId);
			header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

			HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);

			String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);
			rtnJson = CommFunc.result(Constant.SUCCESS, bodySubscribe);
		} catch (Exception e) {
			Log4jUtils.getError().error("订阅消息异常，订阅内容：" + notify);
			rtnJson = CommFunc.result(Constant.ERROR, "订阅异常");
		}
		return rtnJson;
	}
}
