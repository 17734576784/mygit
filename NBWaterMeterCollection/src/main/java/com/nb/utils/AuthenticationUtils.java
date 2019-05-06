/**
 * 
 */
package com.nb.utils;

import java.util.HashMap;
import java.util.Map;

import com.nb.httputil.ChinaTelecomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.StreamClosedHttpResponse;

/**
 * @ClassName: AuthenticationUtils
 * @Description:鉴权工具类
 * @author: dbr
 * @date: 2018年9月5日 上午11:05:32
 * 
 */
public class AuthenticationUtils {
	
	public static String getChinaTelecomAccessToken(ChinaTelecomIotHttpsUtil httpsUtil, String appId, String secret) {
		String key = Constant.ACCESS_TOKEN + appId;
		String accessToken = JedisUtils.get(key);
		if (accessToken != null) {
			return accessToken;
		}
		
		String urlLogin = Constant.CHINA_TELECOM_APP_AUTH;
		Map<String, String> appInfo = new HashMap<>();
		appInfo.put("appId", appId);
		appInfo.put("secret", secret);
		try {
			StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, appInfo);
			System.out.println("responseLogin : "+ responseLogin);
			Map<String, String> data = new HashMap<>();
			data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
			accessToken = data.get("accessToken");
			int expiresIn = ConverterUtils.toInt(data.get("expiresIn"));
			
			JedisUtils.set(key, accessToken, expiresIn);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("鉴权异常");
		}

		return accessToken;
	}
	
}
