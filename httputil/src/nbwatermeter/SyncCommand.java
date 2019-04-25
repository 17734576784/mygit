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
import java.util.HashMap;
import java.util.List;
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
public class SyncCommand {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

       String urlReg="https://192.168.1.130:18213/api/command";
       
       JSONObject json = new JSONObject();
       json.put("nbType", "2");
       json.put("rtuId", "2");
       json.put("mpId", "1");
       json.put("commandId", "4");

       JSONObject param = new JSONObject();
//       param.put("AFN", 27);
//       param.put("IMSI", "00000867726032982805");
//       param.put("CNT", 3);
//       param.put("DIR", 0);
//       param.put("ValveOperate", 1);
//       
       param.put("value", 1);
       json.put("param", param.toJSONString());
       
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
}
