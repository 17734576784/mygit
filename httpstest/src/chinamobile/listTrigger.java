/**   
* @Title: getDeviceInfo.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:56:12 
* @version V1.0   
*/
package chinamobile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import httpUtil.HttpsClientUtil;
import httpUtil.StreamClosedHttpResponse;

/** 
* @ClassName: getDeviceInfo 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2019��1��3�� ����9:56:12 
*  
*/
public class listTrigger {

	/**
	 * @throws Exception  
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args    �趨�ļ� 
	* @return void    �������� 
	* @throws 
	*/
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String urlReg = "https://118.24.175.15:443/chinamobile/triggers";

		Map<String, String> params = new HashMap<String, String>();

		JSONObject json = new JSONObject();
		json.put("title", "mytrigger");
		json.put("page", "1");
		json.put("per_page", "10");
		
		params.put("triggerInfo", json.toJSONString());
			
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse responseReg = httpsClientUtil.doGetWithParasGetStatusLine(urlReg, params, null);

		System.out.println("RegisterDirectlyConnectedDevice, response content:");
		System.out.print(responseReg.getStatusLine());
		System.out.println(responseReg.getContent());
		System.out.println();
	}

}
