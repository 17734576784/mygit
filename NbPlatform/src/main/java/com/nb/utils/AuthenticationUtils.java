/**
 * 
 */
package com.nb.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;

/**
 * @ClassName: AuthenticationUtils
 * @Description:鉴权工具类
 * @author: dbr
 * @date: 2018年9月5日 上午11:05:32
 * 
 */
public class AuthenticationUtils {
	
	@SuppressWarnings({"unchecked" })
	public static String getAccessToken(IotHttpsUtil httpsUtil) {
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
	
	/** 
	* @Title: verificationToken 
	* @Description: 移动平台token验证 
	* @param @param msg
	* @param @param nonce
	* @param @param signature
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String verificationToken(String msg, String nonce, String signature) {
		String context = Constant.CHINA_MOBILE_TOKEN + nonce + msg;
		String MD5Context = CommFunc.getMD5(context);
		final Base64.Encoder encoder = Base64.getEncoder();
		String Base64Context = encoder.encodeToString(MD5Context.getBytes());
		try {
			String URLDecoderContext = URLDecoder.decode(Base64Context, "UTF-8");
			if (URLDecoderContext.equals(signature)) {
				return msg;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Constant.EMPTY;
	}


}
