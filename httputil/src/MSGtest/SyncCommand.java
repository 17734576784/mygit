/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/
package MSGtest;

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

       String urlReg="http://222.222.60.178:18122/api/376frame";
       
       JSONObject json = new JSONObject();
//       json.put("appId", Constant.APPID);
//       json.put("secret", Constant.SECRET);
//       json.put("nbType", 2);
//       json.put("commandType", 30);
//       json.put("imei", "866971033912192");
		json.put("deviceId", "598c9256-6b4f-43c0-b528-832464a4f8c9");

//       JSONObject param = new JSONObject();
//       param.put("cmdValue", "6832003200688000000000400063010102002716");
//       json.put("param", param.toJSONString());
		json.put("frame", "688600860068ca90800200000e7100000100be00bdbe2e0f520005081901000000030000630201b416");
       
//    paramJson : {"deviceId":"","frame":""}
       
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, null,
				json.toJSONString());
		
		String response = responseReg.getContent();
		JSONObject httpResult = JSONObject.parseObject(handleJsonStr(response));
		if (httpResult != null && !httpResult.isEmpty()) {
			System.out.println(httpResult.getInteger("status"));
		}
		
//		Map<String, String> params = new HashMap<String, String>();
//		  params.put("commandInfo", json.toJSONString());
//       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();  
//       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(urlReg, params);

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
	
	public static String handleJsonStr(String jsonStr) {
		String result = jsonStr.replace("\\", "");
		if (result.startsWith("\"")) {
			result = result.substring(1, result.length()-1);
		}
		return result;
	}
}
