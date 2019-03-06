/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/


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
public class AddDevice {

	/**
	 * @throws Exception  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
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
