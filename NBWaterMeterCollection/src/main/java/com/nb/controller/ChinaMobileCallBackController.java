package com.nb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

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
import com.nb.mapper.NbCommandMapper;
import com.nb.model.NbCommand;
import com.nb.service.chinamobileimpl.ChinaMobileFxServiceImpl;
import com.nb.service.chinamobileimpl.ChinaMobileSuntrontServiceImpl;
import com.nb.utils.ChinaMobileUtil;
import com.nb.utils.CommandEnum;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;

/** 
* @ClassName: ChinaMobileCallBackController 
* @Description: 中国移动平台接收订阅消息
* @author dbr
* @date 2019年1月2日 下午5:27:38 
*  
*/
@RestController
public class ChinaMobileCallBackController {

	@Autowired
	private ChinaMobileSuntrontServiceImpl chinaMobileSuntrontService;

	@Autowired
	private ChinaMobileFxServiceImpl chinaMobileFxService;

	@Resource
	private NbCommandMapper nbCommandMapper;
	
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
			System.out.println(pushMessages);
			LoggerUtil.logger(LogName.ERROR).error(pushMessages);
		}
		return Constant.OK;
	}
	
	/** 
	* @Title: parseMsg 
	* @Description: 根据上报的ds_id适配解析工具 
	* @param @param msgJson
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void parseMsg(JSONObject msgJson) throws Exception {
		
		int type = msgJson.getIntValue("type");
		/** 设备上传数据点消息 */
		if (type == Constant.CHINA_MOBILE_DATA_MSG) {
			String dsId = msgJson.getString("ds_id");
			switch (dsId) {
			case Constant.SUNTRONT_DSID:
				chinaMobileSuntrontService.parseDataPointMsg(msgJson);
				break;
			case Constant.FX_DSID:
				chinaMobileFxService.parseDataPointMsg(msgJson);
				break;

			default:
				break;
			}
		}
		/** 缓存命令下发后结果上报 */
		else if (type == Constant.CHINA_MOBILE_COMMAND_MSG) {
			String commandId = msgJson.getString("cmd_id");
			int confirmStatus = msgJson.getIntValue("confirm_status");
			long confirmTime = msgJson.getLongValue("confirm_time");
			if (confirmStatus == Constant.ZERO) {
				confirmStatus = CommandEnum.SUCCESSFUL.getResultValue();
			} else {
				confirmStatus = CommandEnum.FAILED.getResultValue();
			}

			String tableNameDate = JedisUtils.get(Constant.COMMAND + commandId);
			if (tableNameDate != null) {
				NbCommand nbCommand = new NbCommand();
				nbCommand.setTableName(tableNameDate);
				nbCommand.setCommandId(commandId);
				nbCommand.setExecuteResult((byte) confirmStatus);
				nbCommand.setReportTime(new Date(confirmTime));
				nbCommandMapper.updateNbCommand(nbCommand);
				JedisUtils.del(Constant.COMMAND + commandId);
			}
		}
	}
	
}
