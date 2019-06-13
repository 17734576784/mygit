/**   
* @Title: AddDevice.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:10:33 
* @version V1.0   
*/
package nbwatermeter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;

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

       String urlReg="https://192.168.1.130:18213/api/command";
       
       JSONObject json = new JSONObject();
       json.put("nbType", "2");
       json.put("rtuId", "2");
       json.put("mpId", "1");
       json.put("commandId", "3");
       json.put("operatorId", "1");
       json.put("meterAddr", "300000000008");
       json.put("control", "14");

       
       

       JSONObject param = new JSONObject();
//       param.put("AFN", 27);
//       param.put("IMSI", "00000867726032982805");
//       param.put("CNT", 3);
//       param.put("DIR", 0);
//       param.put("ValveOperate", 1);
//       
//       param.put("CurrentDateTime", DateUtils.formatDate(new Date(), "YYYY-MM-dd HH:mm:ss"));
       json.put("param", param.toJSONString());
       
      	Map<String, String> params = new HashMap<String, String>();
	  params.put("param", json.toJSONString());
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doGetWithParasGetStatusLine(urlReg, params, null);

       
//       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
//       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
}
