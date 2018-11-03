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

import com.iot.exception.ResultBean;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.Constant;
import com.iot.utils.IotHttpsUtil;

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
	 * @throws Exception 
	 */
	@RequestMapping(value = "subscribeNotify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> subscribeNotify(String  notify) throws Exception {
		
		LoggerUtils.Logger(LogName.INFO).info("接收订阅请求:" + notify);
		IotHttpsUtil httpsUtil = new IotHttpsUtil();
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
		
		ResultBean<String> result = new ResultBean<String>();
		result.setData(bodySubscribe);
		
		return result;
	}
}
