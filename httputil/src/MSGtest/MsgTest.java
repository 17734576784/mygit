/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:10:33 
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
 * @Description: TODO(������һ�仰��������������)
 * @author dbr
 * @date 2019��1��3�� ����9:10:33
 * 
 */
public class MsgTest {

	/**
	 * @Title: main @Description: TODO(������һ�仰�����������������) @param @param args
	 * �趨�ļ� @return void �������� @throws
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
			json.put("Content", "������֤��Ϊ��"+i);
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());
			System.out.println(responseReg.getContent());
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000);
	}

}
