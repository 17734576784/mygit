/**   
* @Title: getDeviceInfo.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:56:12 
* @version V1.0   
*/
package chinamobile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class getDeviceDatapoint {

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
			String urlReg="https://118.24.175.15:443/chinamobile/datapoints/513635226";

			JSONObject json = new JSONObject();
			json.put("datastream_id", "3,5,4");
			json.put("start", "2018-01-01T08:00:35");
			json.put("end", "2018-01-10T08:00:35");
			json.put("limit", "10");
//			json.put("cursor", "12");
	
			Map<String, String> param = new HashMap<String, String>(1);
			param.put("param", json.toJSONString());
			

	       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
	       StreamClosedHttpResponse responseReg = httpsClientUtil.doGetWithParasGetStatusLine(urlReg, param, null);

	       System.out.println("RegisterDirectlyConnectedDevice, response content:");
	       System.out.print(responseReg.getStatusLine());
	       System.out.println(responseReg.getContent());
	       System.out.println();
	}

}
