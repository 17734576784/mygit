/**
 * 
 */
package com.nb.utils;

import java.util.HashMap;
import java.util.Map;

import com.nb.http.ChinaTelecomIotHttpsUtil;
import com.nb.http.ChinaUnicomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.StreamClosedHttpResponse;

/**
 * @ClassName: AuthenticationUtils
 * @Description:鉴权工具类
 * @author: dbr
 * @date: 2018年9月5日 上午11:05:32
 * 
 */
public class AuthenticationUtils {
	
	@SuppressWarnings({"unchecked" })
	public static String getChinaTelecomAccessToken(ChinaTelecomIotHttpsUtil httpsUtil) {
		String accessToken = "";
		
		String appId = Constant.CHINA_TELECOM_APPID;
		String secret = Constant.CHINA_TELECOM_SECRET;
		String urlLogin = Constant.CHINA_TELECOM_APP_AUTH;

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
			LoggerUtils.Logger(LogName.ERROR).error("鉴权异常");
		}

		return accessToken;
	}
	
	@SuppressWarnings({"unchecked" })
	public static String getChinaUnicomAccessToken(ChinaUnicomIotHttpsUtil httpsUtil) {
		String accessToken = "";
		
		String appId = Constant.CHINA_UNICOM_APPID;
		String secret = Constant.CHINA_UNICOM_SECRET;
		String urlLogin = Constant.CHINA_UNICOM_APP_AUTH;

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
			LoggerUtils.Logger(LogName.ERROR).error("鉴权异常");
		}

		return accessToken;
	}
}
