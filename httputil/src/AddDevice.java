/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:10:33 
* @version V1.0   
*/


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
public class AddDevice {

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

       String urlReg="http://222.222.60.178:18130/Enterprise_MeterPay/pay/nbiot/nbNotifyAction!notifyData.action";
       
       JSONObject json = new JSONObject();
       json.put("deviceId", "517176899");
       json.put("date", "20180912");
       json.put("time", "120534");
       json.put("value", "12.3");
       json.put("dataStreamId", "12");

       Map<String, String> param = new HashMap<>();
       param.put("param", json.toJSONString());
       
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(urlReg, param);

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}

}
