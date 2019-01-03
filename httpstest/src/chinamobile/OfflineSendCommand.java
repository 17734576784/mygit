/**   
* @Title: getDeviceInfo.java 
* @Package chinamobile 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��3�� ����9:56:12 
* @version V1.0   
*/
package chinamobile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import httpUtil.HttpsClientUtil;
import httpUtil.StreamClosedHttpResponse;

/** 
* @ClassName: getDeviceInfo 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2019��1��3�� ����9:56:12 
*  
*/
public class OfflineSendCommand {

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
			String urlReg="https://118.24.175.15:443/chinamobile/offlinecommand";
			JSONObject json = new JSONObject();
			json.put("imei", "000001956811234");
			json.put("obj_id", "1");
			json.put("obj_inst_id", "1");
			json.put("res_id", "2");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			json.put("valid_time", sdf.format(c.getTime()));
			c.add(Calendar.DATE, 10);
			json.put("expired_time", sdf.format(c.getTime()));
			json.put("retry", "3");
			json.put("trigger_msg", "1");
			
			json.put("args", "hadkjflkdjkl");
			
	       HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
	       StreamClosedHttpResponse responseReg = httpsClientUtil.doPostJsonGetStatusLine(urlReg, json.toJSONString());

	       System.out.println("RegisterDirectlyConnectedDevice, response content:");
	       System.out.print(responseReg.getStatusLine());
	       System.out.println(responseReg.getContent());
	       System.out.println();
	}

}