/**
 * 
 */
package com.nb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

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
	
	  private static MessageDigest mdInst;

	    static {
	        try {
	            mdInst = MessageDigest.getInstance("MD5");
	            Security.addProvider(new BouncyCastleProvider());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	    }
	    
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
	public static boolean verificationToken(String msg, String nonce, String signature) {
		// 计算接受到的消息的摘要
		// token长度 + 8B随机字符串长度 + 消息长度
		String token = Constant.CHINA_MOBILE_TOKEN;
		byte[] signatures = new byte[token.length() + 8 + msg.length()];
		System.arraycopy(token.getBytes(), 0, signatures, 0, token.length());
		System.arraycopy(nonce.getBytes(), 0, signatures, token.length(), 8);
		System.arraycopy(msg.getBytes(), 0, signatures, token.length() + 8, msg.length());
		String calSig = Base64.encodeBase64String(mdInst.digest(signatures));

		return calSig.equals(signature);
	}

}
