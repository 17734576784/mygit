package com.ke.common;

/**
 * 
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSONObject;
import com.ke.model.LoginUser;
import com.ke.model.OperatorConfig;
import com.ke.utils.ConverterUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.JsonUtil;
import com.ke.utils.SerializeUtil;

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
	 * 根据错误码将错误信息以json格式返回
	 * @param errorCode
	 * @return {@link JSONObject}
	 * */
	public static JSONObject errorInfo(int errorCode,String errorInfo) {
		JSONObject json = new JSONObject();
		json.put("status", errorCode);
		json.put("error", errorInfo);
		
		return json;
	}
	
	public static LoginUser getLoginUserByToken(String token) {
		String key = Constant.TOKEN_PREFIX + token;

		String value = JedisUtil.get(key);
		if (value == null) {
			return null;
		}

		LoginUser loginUser = JsonUtil.jsonString2SimpleObj(value, LoginUser.class);
		return loginUser;
	}
	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", ConverterUtil.toLong(code));
        return result;
    }
    
	/**
	 * 判断终端是否在线 在线返回true 不在线返回false
	 */
	public static boolean rtuOnlineFlag(int rtuId) {
		boolean rtnFlag = false;
		String key = Constant.RTUSTATE + rtuId;
		Map<String, String> rtuState = JedisUtil.hgetAll(key);
		if (null != rtuState) {
			byte state = ConverterUtil.toByte(rtuState.get("status"));
			rtnFlag = state == (byte) 1 ? true : false;

		}
		return rtnFlag;
	}
	
	/** 
	* @Title: initOperatorConfig 
	* @Description: 初始化运营商配置信息 
	* @param @param operatorConfig    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void initOperatorConfig(OperatorConfig operatorConfig) {
		String key = Constant.OPERATORCONFIG_PREFIX + operatorConfig.getOperatorId();
		JedisUtil.set(key.getBytes(), SerializeUtil.serialize(operatorConfig));
		
		key = Constant.SERIALNUMBER_PREFIX;
		JedisUtil.hset(key, operatorConfig.getSerialnumberPrefix(), operatorConfig.getSerialnumberPrefix());
	}

	/**
	 * 验证流水号
	 */
	public static boolean checkWasteno(String wasteno) {
		if (null == wasteno || wasteno.length() > Constant.PAY_SERIALNUMBER_LENGTH) {
			return false;
		}

		wasteno = ConverterUtil.toStr(ConverterUtil.toLong(wasteno));
		if (wasteno.length() != Constant.CHARGE_SERIALNUMBER_LENGTH) {
			return false;
		}

		String prefix = wasteno.substring(Constant.ZERO, Constant.CHARGE_SERIALNUMBER_PREFIX);

		String key = Constant.SERIALNUMBER_PREFIX;
		Map<String, String> map = JedisUtil.hgetAll(key);
		if (!map.containsKey(prefix)) {
			return false;
		} else {
			return true;
		}
	}
	/** 
	* @Title: beanToHMap 
	* @Description: 将java实体类转换为redis存储的HMap类型
	* @param @param javaBean
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	public static Map<String, String> beanToHMap(Object javaBean) {
		Map<String, String> result = new HashMap<String, String>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, ConverterUtil.toStr(value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
