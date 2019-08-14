/**   
* @Title: TESt.java 
* @Package httpUtil 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月26日 下午7:55:31 
* @version V1.0   
*/
package httpUtil;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: TESt
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月26日 下午7:55:31
 * 
 */
public class TESt {
	public static void main(String[] args) throws Exception {
		Map<String, String> paramReg = new HashMap<>();
//		paramReg.put("userName", "lyadmin");
//		paramReg.put("passWord", "E10ADC3949BA59ABBE56E057F20F883E");
//	        paramReg.put("queryJsonStr", "123456789");
//	        paramReg.put("token", "123456789");

		String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
		String urlReg = "http://pile.luyoufc.com/dc-serv/pile/chargeStart.json";
//		urlReg = "http://39.106.116.158:8080/kecharge/login.json";
//	        Map<String, String> header = new HashMap<>();
//	        header.put(Constant.HEADER_APP_KEY, appId);
//	        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		HttpsClientUtil httpsUtil = new HttpsClientUtil();
		JSONObject json = new JSONObject();
		json.put("userName", "lyadmin");
		json.put("passWord", "E10ADC3949BA59ABBE56E057F20F883E");
		paramReg.put("queryJsonStr", json.toJSONString());
//		StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());
	    StreamClosedHttpResponse responseReg = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlReg, paramReg);

		System.out.println("RegisterDirectlyConnectedDevice, response content:");
		System.out.print(responseReg.getStatusLine());
		System.out.println(responseReg.getContent());
		System.out.println();
	}
}
