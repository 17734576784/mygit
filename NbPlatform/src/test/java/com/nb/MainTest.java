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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
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
	 * @throws Exception  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) throws Exception {
		String apiUrl = "http://222.222.60.178:18130/Enterprise_MeterPay/pay/nbiot/" + Constant.UPLOAD_ALARM_URL;

		JSONObject paramJson = new JSONObject();
		paramJson.put("slope", 1);
		paramJson.put("magnetic", 2);
		paramJson.put("alarmtype", 3);
		paramJson.put("deviceId", "a278184c-5327-4c42-a6e7-f687fad7ae3c");
		paramJson.put("date", 20190315);
		paramJson.put("time", 120000);

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("param", paramJson.toJSONString());
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(apiUrl, paramMap);
		
		//"{\"status\":-1,\"error\":\"重复告警\"}"
		String response = httpResponse.getContent().toString();
 		JSONObject httpResult = JSONObject.parseObject(CommFunc.handleJsonStr(response));
		if (httpResult != null && !httpResult.isEmpty()) {
			if (httpResult.getInteger("status") == Constant.ERROR) {
			}
		}
	}
	

}
