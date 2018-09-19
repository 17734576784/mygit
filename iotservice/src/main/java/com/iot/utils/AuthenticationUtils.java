/**
 * 
 */
package com.iot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AuthenticationUtils
 * @Description:鉴权工具类
 * @author: dbr
 * @date: 2018年9月5日 上午11:05:32
 * 
 */
public class AuthenticationUtils {
	
	@SuppressWarnings({"unchecked" })
	public static String getAccessToken(HttpsUtil httpsUtil) {
		String accessToken = "";
		
		String appId = Constant.APPID;
		String secret = Constant.SECRET;
		String urlLogin = Constant.APP_AUTH;

		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);
		try {
		
			StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);
			Map<String, String> data = new HashMap<>();
			data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
			accessToken = data.get("accessToken");
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtils.getError().error("鉴权异常");
		}

		return accessToken;
	}
}