/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:10:33 
* @version V1.0   
*/
package chinamobile;

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
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args    �趨�ļ� 
	* @return void    �������� 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

       String urlReg="https://118.24.175.15:443/chinamobile/devices";
       
       JSONObject json = new JSONObject();
       json.put("title", "mydevice");
       json.put("desc", "Ke Test Device");
       String[] tags = new String[] {"china", "mobile"}; 
       json.put("tags",tags);
       json.put("protocol","LWM2M");
       JSONObject location= new JSONObject();
       location.put("lon", 106);
       location.put("lat", 29);
       location.put("ele", 370);
       json.put("location",location);
      
       JSONObject auth_info= new JSONObject();
       auth_info.put("1234", "3242");
       json.put("auth_info",auth_info);
       
       json.put("private",true);
       
       JSONObject other= new JSONObject();
       other.put("version",  "1.0.0");
       other.put("manu",  "china mobile");
       json.put("other",other);
       json.put("chip", 1);

       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}

}
