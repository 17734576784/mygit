/**
 * 
 */
package com.iot.http;

import com.alibaba.fastjson.JSONObject;

/**   
 * @ClassName:  test   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年9月1日 上午10:41:38   
 *      
 */
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String apiUrl = "https://39.106.116.158:8443/test";
		JSONObject json = new JSONObject();
		json.put("name", "dbr");
		JSONObject result = HttpsUtils.doPost(apiUrl, json);
		System.out.println(result);
	}

}
