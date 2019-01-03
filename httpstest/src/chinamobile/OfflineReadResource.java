/**   
* @Title: getDeviceInfo.java 
* @Package chinamobile 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午9:56:12 
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
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月3日 上午9:56:12 
*  
*/
public class OfflineReadResource {

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
			String urlReg="https://118.24.175.15:443/chinamobile/offlineesource";
			JSONObject json = new JSONObject();
			json.put("imei", "000001956811234");
			json.put("obj_id", "1");
			json.put("obj_inst_id", "1");
			json.put("res_id", "1");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			json.put("valid_time", sdf.format(c.getTime()));
			c.add(Calendar.DATE, 10);
			json.put("expired_time", sdf.format(c.getTime()));
			json.put("retry", "3");
			json.put("trigger_msg", "1");

			Map<String, String> param = new HashMap<String, String>(1);
			param.put("commandInfo", json.toJSONString());

	       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
	       StreamClosedHttpResponse responseReg = httpsClientUtil.doGetWithParasGetStatusLine(urlReg, param, null);

	       System.out.println("RegisterDirectlyConnectedDevice, response content:");
	       System.out.print(responseReg.getStatusLine());
	       System.out.println(responseReg.getContent());
	       System.out.println();
	}

}
