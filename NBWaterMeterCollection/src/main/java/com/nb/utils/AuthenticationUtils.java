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
	
	@SuppressWarnings({"unchecked" })
	public static String getChinaTelecomAccessToken(ChinaTelecomIotHttpsUtil httpsUtil, Map<String, String> appInfo) {
		String accessToken = "";
		
		String appId = appInfo.get("appId");
		String secret = appInfo.get("secret");
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
			LoggerUtil.Logger(LogName.ERROR).error("鉴权异常");
		}

		return accessToken;
	}
	
}
