package com.nb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.ChinaMobileUtil;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;

/** 
* @ClassName: ChinaMobileCallBackController 
* @Description: 中国移动平台接收订阅消息
* @author dbr
* @date 2019年1月2日 下午5:27:38 
*  
*/
@RestController
public class ChinaMobileCallBackController {

	/** 
	* @Title: URLVerification 
	* @Description:  URL&Token验证接口。如果验证成功返回msg的值，否则返回其他值
	* @param @param msg 验证消息
	* @param @param nonce 随机串
	* @param @param signature 签名
	* @param @return msg值
	* @param @throws UnsupportedEncodingException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "receivingPushMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String URLVerification(@RequestParam(value = "msg") String msg, @RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "signature") String signature) throws UnsupportedEncodingException {
		if (ChinaMobileUtil.checkToken(msg, nonce, signature, Constant.CHINA_MOBILE_TOKEN)) {
			return msg;
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "receivingPushMessages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String URLVerification(@RequestBody String pushMessages) {
		LoggerUtil.Logger(LogName.INFO).info("data receive:  body String --- " + pushMessages);
		try {
			ChinaMobileUtil.BodyObj obj = ChinaMobileUtil.resolveBody(pushMessages, false);
			LoggerUtil.Logger(LogName.INFO).info("data receive:  body Object --- " + obj);
			if (obj != null) {
				boolean dataRight = ChinaMobileUtil.checkSignature(obj, Constant.CHINA_MOBILE_TOKEN);
				if (dataRight) {
					System.out.println("msg :" + obj.getMsg().toString());
					try {
						JSONObject msgJson = JSONObject.parseObject(obj.getMsg().toString());
						parseMsg(msgJson);
					} catch (Exception e) {
						JSONArray msgArray = JSON.parseArray(obj.getMsg().toString());
//						System.out.println("msgArray : " + msgArray);
						for (Object object : msgArray) {
							JSONObject msgJson = (JSONObject) object;
							parseMsg(msgJson);
						}
					}
					LoggerUtil.Logger(LogName.INFO).info("data receive: content" + obj.toString());
				} else {
					LoggerUtil.Logger(LogName.INFO).info("data receive: signature error");
				}
			} else {
				LoggerUtil.Logger(LogName.INFO).info("data receive: body empty error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.OK;
	}
	
	private void parseMsg(JSONObject msgJson) throws Exception {

		//标识消息类型
		int type = msgJson.getIntValue("type");
		if (type == Constant.CHINA_MOBILE_DATA_MSG) {
			
			long at = msgJson.getLongValue("at");
			String date = DateUtils.stampToDate(at);
			
			JSONObject paramJson = new JSONObject();
			paramJson.put("date", date.split(" ")[0]);
			paramJson.put("time", date.split(" ")[1]);
			paramJson.put("deviceId", msgJson.getString("dev_id"));
			
			String dsId = msgJson.getString("ds_id");
			Double value = 0D;
			if (dsId.contains("3342_0_5850")) {
				value = msgJson.getDouble("value");
			} else {
				value = Long.parseLong(msgJson.getString("value"), 16) * 1.0D / 100;
			}
			
			paramJson.put("value", value);
			paramJson.put("dataStreamId", dsId);
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("param", paramJson.toJSONString());

//			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
//			String apiUrl = baseUrl + Constant.UPLOAD_DATA_URL;
//			StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(apiUrl, paramMap);
//			LoggerUtil.Logger(LogName.INFO).info(httpResponse.getContent());
		}
	}
}
