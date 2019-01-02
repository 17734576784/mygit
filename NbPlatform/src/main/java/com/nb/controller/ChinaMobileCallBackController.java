package com.nb.controller;

import org.springframework.http.MediaType;
import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.ChinaMobileUtil;
import com.nb.utils.Constant;

/** 
* @ClassName: ChinaMobileCallBackController 
* @Description: 中国移动平台接收订阅消息
* @author dbr
* @date 2019年1月2日 下午5:27:38 
*  
*/
@RestController
public class ChinaMobileCallBackController {

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
		System.out.println("pushMessages : " + pushMessages);
		LoggerUtils.Logger(LogName.INFO).info("data receive:  body String --- " + pushMessages);
		/************************************************
		 * 解析数据推送请求，非加密模式。 如果是明文模式使用以下代码
		 **************************************************/
		/************* 明文模式 start ****************/
		ChinaMobileUtil.BodyObj obj = ChinaMobileUtil.resolveBody(pushMessages, false);
		LoggerUtils.Logger(LogName.INFO).info("data receive:  body Object --- " + obj);
		if (obj != null) {
			boolean dataRight = ChinaMobileUtil.checkSignature(obj, Constant.CHINA_MOBILE_TOKEN);
			if (dataRight) {
				LoggerUtils.Logger(LogName.INFO).info("data receive: content" + obj.toString());
			} else {
				LoggerUtils.Logger(LogName.INFO).info("data receive: signature error");
			}

		} else {
			LoggerUtils.Logger(LogName.INFO).info("data receive: body empty error");
		}
		return Constant.OK;
	}
}
