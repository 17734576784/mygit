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
public class BatchCommand {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

       String urlReg="https://192.168.1.130:18213/api/batchCommand";
       
       JSONObject json = new JSONObject();
       
       json.put("nbType", "2");

       json.put("appId", "kFIBMLfUCggAtt9dwgSFeYTP9Ssa");
       json.put("secret", "PyVL5CMym0X_3XmSsfAuza2rc_Ma");
       
       List<String> deviceList = new ArrayList<>();
       deviceList.add("63c04ac0-a0ae-4e87-bd0c-9cad975202a3");
       json.put("deviceList", deviceList.toString());
       json.put("commandId", "1");
       json.put("operatorId", 3);
       String serviceId = "SettingReportPeriod";
       String method = "SET_REPORT_PERIOD";
       
       json.put("serviceId", serviceId);
       json.put("method", method);
       
       JSONObject param = new JSONObject();
       param.put("value", 1);
       
       
       json.put("param", param.toString());
       
       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

       System.out.println("RegisterDirectlyConnectedDevice, response content:");
       System.out.print(responseReg.getStatusLine());
       System.out.println(responseReg.getContent());
       System.out.println();
	}
}
