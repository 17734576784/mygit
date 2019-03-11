/**
 * 
 */
package com.nb.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: CommFunc
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年9月5日 上午11:23:18
 * 
 */
public class CommFunc {

	public static String getCertPath(String path) throws IOException {
		ClassPathResource resource = new ClassPathResource(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		String result = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line;
		}
		br.close();
		
		return result;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public static JSONObject result(int errorCode, String errorMsg) {
		JSONObject result = new JSONObject();
		result.put("status", errorCode);
		result.put("errorMsg", errorMsg);
		return result;
	}

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	@SuppressWarnings("restriction")
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bt;
	}

	// 图片到byte数组
	public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

	// byte数组到图片
	public static void byte2image(byte[] data, String path) {
		if (data.length < 3 || path.equals(""))
			return;
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in " + path);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}

	// byte数组到16进制字符串
	public static String byte2string(byte[] data) {
		if (data == null || data.length <= 1)
			return "0x";
		if (data.length > 200000)
			return "0x";
		StringBuffer sb = new StringBuffer();
		int buf[] = new int[data.length];
		// byte数组转化成十进制
		for (int k = 0; k < data.length; k++) {
			buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
		}
		// 十进制转化成十六进制
		for (int k = 0; k < buf.length; k++) {
			if (buf[k] < 16)
				sb.append("0" + Integer.toHexString(buf[k]));
			else
				sb.append(Integer.toHexString(buf[k]));
		}
		return "0x" + sb.toString().toUpperCase();
	}

	// System.arraycopy()方法
	public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
		byte[] bt3 = new byte[bt1.length + bt2.length];
		System.arraycopy(bt1, 0, bt3, 0, bt1.length);
		System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
		return bt3;
	}
	
	/**
	 * 对字符串md5加密(大写+数字)
	 *
	 * @param str 传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
    
	public static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对字符串md5加密(大写+数字)
	 *
	 * @param str 传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
    
	public static String getMD5(byte[] btInput) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<String, String> getChinaMobileHeader(JSONObject appInfo) {
		Map<String, String> header = new HashMap<String, String>(2);
		header.put("api-key", appInfo.getString("appId"));
		header.put("Content-Type", "application/json");
		return header;
	}
	
	public static Map<String, String> getCommandType(int nbType, int commandType) {
		@SuppressWarnings("unchecked")
		Map<String,Map<String,String>> command =  (Map<String, Map<String, String>>) JedisUtils.getByByte(Constant.COMMAND_TYPE_REIDS);
		String commandKey = ConverterUtils.toStr(nbType * 1000 + commandType);
		return command.get(commandKey);
	}
}
