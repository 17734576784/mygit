/**
 * 
 */
package com.nb.utils;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
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

	/** 
	* @Title: image2byte 
	* @Description: 图片到byte数组
	* @param @param path
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	*/
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

	/** 
	* @Title: byte2image 
	* @Description: byte数组到图片
	* @param @param data
	* @param @param path    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void byte2image(byte[] data, String path) {
		if (data.length < Constant.THREE || path == null || path.isEmpty()){
			return;
		}
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

	/** 
	* @Title: byte2string 
	* @Description: byte数组到16进制字符串 
	* @param @param data
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String byte2string(byte[] data) {
		if (data == null || data.length <= 1) {
			return "0x";
		}
		if (data.length > Constant.NUM_200000) {
			return "0x";
		}
		StringBuffer sb = new StringBuffer();
		int buf[] = new int[data.length];
		// byte数组转化成十进制
		for (int k = 0; k < data.length; k++) {
			buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
		}
		// 十进制转化成十六进制
		for (int k = 0; k < buf.length; k++) {
			if (buf[k] < 16) {
				sb.append("0" + Integer.toHexString(buf[k]));
			} else {
				sb.append(Integer.toHexString(buf[k]));
			}
		}
		return "0x" + sb.toString().toUpperCase();
	}

	/** 
	* @Title: byteMerger 
	* @Description: System.arraycopy()方法 
	* @param @param bt1
	* @param @param bt2
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	*/
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
	
	
	/** 
	* @Title: parseEventTime 
	* @Description: 解析上报事项时间 
	* @param @param evnetTime
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String parseEventTime(String evnetTime) {
		String date = "";
		if (!evnetTime.isEmpty()) {
			date = evnetTime.substring(0, 8) + toStr(toInt(evnetTime.substring(9, 15)) + 80000);
		}
		return date;
	}
	
	
	public static Map<String, String> getCommandType(int nbType, int commandType) {
		Map<String, Map<String, String>> command = new HashMap<>();
		command = JsonUtil.jsonString2SimpleObj(JedisUtils.get(Constant.COMMAND_TYPE_REIDS), command.getClass());
		String commandKey = ConverterUtils.toStr(nbType * 1000 + commandType);
		return command.get(commandKey);
	}
	
	/** 
	* @Title: getTaskParamList 
	* @Description: 获取补招任务执行的表名和日期列表
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public static List<Map<String, Object>> getTaskParamList() {
		/** 默认补招从T-1起 */
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);

		List<Map<String, Object>> paramList = new LinkedList<Map<String, Object>>();

		for (int i = 0; i < Constant.TASK_CALL_DAYS; i++) {
			String date = DateUtils.formatDateByFormat(endDate.getTime(), DateUtils.DATE_PATTERN);
			String tableName = "YDData.dbo.nb_daily_data_" + date.substring(0, 6);
			Map<String, Object> param = new HashMap<>();
			param.put("date", date);
			param.put("tableName", tableName);

			paramList.add(param);
			endDate.add(Calendar.DAY_OF_YEAR, -1);
		}
		return paramList;
	}
	
	/** 
	* @Title: getTaskStartTime 
	* @Description: 获取补招任务产生时间的开始时间
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getTaskStartTime() {
		/** 默认补招从T-1起 */
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);
		endDate.add(Calendar.DAY_OF_MONTH, -1 * (Constant.TASK_CALL_DAYS + 1));
		return DateUtils.parseDate(endDate.getTime(), DateUtils.UTC_PATTERN);
	}
}
