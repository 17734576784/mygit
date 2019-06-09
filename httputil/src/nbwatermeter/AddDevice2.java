/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/
package nbwatermeter;

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
public class AddDevice2 {

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

       String urlReg="https://192.168.1.130:18213/api/register";
       
       JSONObject json = new JSONObject();
       json.put("nbType", "0");
       json.put("rtuId", "6");
       json.put("mpId", "1");
		Map<String, String> params = new HashMap<String, String>();
		params.put("param", json.toJSONString());
       
//       urlReg = HttpsClientUtil.setcompleteUrl(urlReg, params);
       
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doGetWithParasGetStatusLine(urlReg, params, null);

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
}
