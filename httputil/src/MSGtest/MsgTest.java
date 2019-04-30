/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/
package MSGtest;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import httpUtil.HttpsClientUtil;
import httpUtil.StreamClosedHttpResponse;

/**
 * @ClassName: AddDevice
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月3日 上午9:10:33
 * 
 */
public class MsgTest {

	/**
	 * @Title: main @Description: TODO(这里用一句话描述这个方法的作用) @param @param args
	 * 设定文件 @return void 返回类型 @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String urlReg = "https://sdkulink.028lk.com:8082/Api/SendSms";

		JSONObject json = new JSONObject();
		json.put("LoginName", "SN00046");
		json.put("Pwd", "ul@2019");
		json.put("FeeType", "2");
		json.put("Mobile", "15373022041");
		 
		long start = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			json.put("Content", "您的验证码为："+i);
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());
			System.out.println(responseReg.getContent());
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000);
	}

}
