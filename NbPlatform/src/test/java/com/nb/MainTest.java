/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月14日 上午9:42:25 
* @version V1.0   
*/
package com.nb;

import static org.assertj.core.api.Assertions.contentOf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.DateUtils;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年2月14日 上午9:42:25 
*  
*/
public class MainTest {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("nbType", 1);
		paramJson.put("version", 2);
		paramJson.put("deviceId", 3);

		String apiUrl = "http://127.0.0.1:8080/api/register";
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostJsonGetStatusLine(apiUrl,
		paramJson.toJSONString());
		JSONObject response = JSONObject.parseObject(httpResponse.getContent());
		System.out.println(response);
//		String date = DateUtils.stampToDate(1546939347401L);
//
//		System.out.println(date.split(" ")[0] + "   " + date.split(" ")[1]);
	}
	

}
