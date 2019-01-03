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
public class ListOfflineCommand {

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
			String urlReg="https://118.24.175.15:443/chinamobile/listOfflineCommand";
			JSONObject json = new JSONObject();
			json.put("imei", "000001956811234");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			json.put("start", sdf.format(c.getTime()));
			c.add(Calendar.DATE, 1);
			json.put("end", sdf.format(c.getTime()));
			json.put("page", "1");
			json.put("per_page", "10");
//			json.put("sort", "DESC");

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
