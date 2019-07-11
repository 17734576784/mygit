/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/
package MSGtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;

import com.alibaba.fastjson.JSONObject;

import httpUtil.HttpsClientUtil;
import httpUtil.StreamClosedHttpResponse;
import utils.Constant;

/** 
* @ClassName: AddDevice 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
*  
*/
public class SyncCommand {

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

       String urlReg="https://39.105.158.202:443/api/command";
       
       JSONObject json = new JSONObject();
       json.put("appId", Constant.APPID);
       json.put("secret", Constant.SECRET);
       json.put("nbType", 2);
       json.put("commandType", 30);
       json.put("imei", "866971033912192");
       json.put("deviceId", "bde6a88c-9c16-4f2d-b856-a16d860040ff");

       JSONObject param = new JSONObject();
       param.put("cmdValue", "6832003200688000000000400063010102002716");
       json.put("param", param.toJSONString());
       
//    Map<String, String> params = new HashMap<String, String>();
//	  params.put("commandInfo", json.toJSONString());
       
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, null,
				json.toJSONString());
       
//       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
//       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
}
