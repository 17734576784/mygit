/**   
* @Title: TESt.java 
* @Package httpUtil 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��12��26�� ����7:55:31 
* @version V1.0   
*/
package httpUtil;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: TESt
 * @Description: TODO(������һ�仰��������������)
 * @author dbr
 * @date 2018��12��26�� ����7:55:31
 * 
 */
public class TESt {
	public static void main(String[] args) throws Exception {
		Map<String, String> paramReg = new HashMap<>();
		paramReg.put("username", "admin");
		paramReg.put("password", "123456");
//	        paramReg.put("queryJsonStr", "123456789");
//	        paramReg.put("token", "123456789");

		String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
		String urlReg = "http://127.0.0.1:8080/login.json";
//	        Map<String, String> header = new HashMap<>();
//	        header.put(Constant.HEADER_APP_KEY, appId);
//	        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		HttpsClientUtil httpsUtil = new HttpsClientUtil();
		JSONObject json = new JSONObject();
		json.put("username", "test");
		json.put("password", "123456");
		StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, jsonRequest);
//	    StreamClosedHttpResponse responseReg = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlReg, paramReg);

		System.out.println("RegisterDirectlyConnectedDevice, response content:");
		System.out.print(responseReg.getStatusLine());
		System.out.println(responseReg.getContent());
		System.out.println();
	}
}
