package com.nb.controller;

import org.springframework.http.MediaType;
import java.io.UnsupportedEncodingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
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
	public String urlVerification(@RequestParam(value = "msg") String msg, @RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "signature") String signature) throws UnsupportedEncodingException {
		if (ChinaMobileUtil.checkToken(msg, nonce, signature, Constant.CHINA_MOBILE_TOKEN)) {
			return msg;
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "receivingPushMessages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String urlVerification(@RequestBody String pushMessages) {
		LoggerUtil.logger(LogName.INFO).info("data receive:  body String --- " + pushMessages);
		try {
			ChinaMobileUtil.BodyObj obj = ChinaMobileUtil.resolveBody(pushMessages, false);
			LoggerUtil.logger(LogName.INFO).info("data receive:  body Object --- " + obj);
			if (obj != null) {
				boolean dataRight = ChinaMobileUtil.checkSignature(obj, Constant.CHINA_MOBILE_TOKEN);
				if (dataRight) {
					try {
						JSONObject msgJson = JSONObject.parseObject(obj.getMsg().toString());
						parseMsg(msgJson);
					} catch (Exception e) {
						JSONArray msgArray = JSON.parseArray(obj.getMsg().toString());
						for (Object object : msgArray) {
							JSONObject msgJson = (JSONObject) object;
							parseMsg(msgJson);
						}
					}
					LoggerUtil.logger(LogName.INFO).info("data receive: content" + obj.toString());
				} else {
					LoggerUtil.logger(LogName.INFO).info("data receive: signature error");
				}
			} else {
				LoggerUtil.logger(LogName.INFO).info("data receive: body empty error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.OK;
	}
	
	/** 
	* @Title: parseMsg 
	* @Description: 解析移动NB平台推送的数据 
	* @param @param msgJson
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void parseMsg(JSONObject msgJson) throws Exception {

		// 标识消息类型
		int msgType = msgJson.getIntValue("type");
		// 数据点消息
		if (msgType == Constant.CHINA_MOBILE_DATA_MSG) {
			parseDateMsg(msgJson);

		}
		// 下行命令的应答（仅限NB设备）
		else if (msgType == Constant.CHINA_MOBILE_COMMAND_MSG) {
			parseCommandMsg(msgJson);
		}
	}
	
	
	/** 
	* @Title: parseDateMsg 
	* @Description: 解析数据点信息 不同的数据存入对应的的redis队列中 
	* @param @param msgJson    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void parseDateMsg(JSONObject msgJson){
		long at = msgJson.getLongValue("at");
		String date = DateUtils.stampToDate(at);
		double value = Long.parseLong(msgJson.getString("value"), 16) * 1.0D / 100;
		

	}
	
	/** 
	* @Title: parseCommandMsg 
	* @Description: 解析下行命令上报结果信息，更新命令的执行状态
	* @param @param msgJson    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void parseCommandMsg(JSONObject msgJson){
		String cmdID = msgJson.getString("cmd_id");
		String imei = msgJson.getString("imei");
		String deviceId = msgJson.getString("dev_id");
		int confirmStatus = msgJson.getIntValue("confirm_status");
		long confirmTime = msgJson.getLong("confirm_time");
		String confirmDate = DateUtils.stampToDate(confirmTime);
	}
	
}
