/**   
* @Title: CommandUpversionService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月22日 上午9:08:53 
* @version V1.0   
*/
package com.iot.commandstrategy;

import static com.iot.utils.ConverterUtils.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.UpGradeUtil;
import com.iot.utils.Constant;
import com.iot.utils.HttpsUtils;
import com.iot.utils.JedisUtils;

/** 
* @ClassName: CommandUpversionService 
* @Description: 升级版本确认命令回复 
* @author dbr
* @date 2018年11月22日 上午9:08:53 
*  
*/
@Component
public class CommandUpversionService implements ICommandService {
	
	/** 网站对接服务地址 */
	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param commandMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		LoggerUtils.Logger(LogName.INFO).info("下发升级版本回复，设备id：" + deviceId + " ,内容：" + commandMap.toString());
		System.out.println("下发升级版本回复，设备id：" + deviceId + " ,内容：" + commandMap.toString());
		try {
			/** 0:升级 1：拒绝升级 */
			int result = toInt(commandMap.get("result"));
			if (result == Constant.UPGRADE_SUCCESS) {
				/** 设备升级缓存key */
				String deviceProgress = Constant.PROGRESS + deviceId;
				if (JedisUtils.hasKey(deviceProgress)) {
					JSONObject progressBody = (JSONObject) JedisUtils.get(deviceProgress);
					
					String fileKey = progressBody.getString("fileKey");
					short packNum = progressBody.getShortValue("packNum");
					short sendedPack = progressBody.getShortValue("sendedPack");
					
					JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
					if (upgradeFile == null || upgradeFile.isEmpty()) {
						LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
						return;
					} 
					
					String command = UpGradeUtil.getCommandParam(deviceId, fileKey, packNum, sendedPack, upgradeFile);
					if (null == command || command.isEmpty()) {
						LoggerUtils.Logger(LogName.CALLBACK).info("组建命令参数失败：" + commandMap);
						return;
					}
					UpGradeUtil.asynCommand(command.toString());
					System.out.println("在设备：" + deviceId + "发送升级命令成功，" + command);
				} else {
					LoggerUtils.Logger(LogName.INFO).info("不存在设备：" + deviceId + ",升级进度缓存");
					System.out.println("不存在设备：" + deviceId + ",升级进度缓存");
				}
			} else {
				//推送升级失败
				sendUpResult(deviceId);
			}
		} catch (Exception e) {
			LoggerUtils.Logger(LogName.INFO).error("下发升级版本回复处理异常，设备id：" + deviceId + " ,内容：" + commandMap.toString());
			e.printStackTrace();
		}
	}
	
	public void sendUpResult(String deviceId) {
		String apiUrl = baseUrl + Constant.UPLOAD_UPGRADERESULT_URL;
		try {
			Map<String, Object> paramJson = new HashMap<>();
			paramJson.put("status", Constant.UPGRADE_FAILED);
			paramJson.put("version", "");
			paramJson.put("deviceId", deviceId);

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject response = HttpsUtils.doPost(apiUrl, paramMap);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					LoggerUtils.Logger(LogName.CALLBACK).info(deviceId + "升级失败发送成功");
				} else {
					LoggerUtils.Logger(LogName.CALLBACK).info(deviceId + "升级失败发送失败：");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("升级成功发送异常，设备id：" + deviceId);
		}
	}
	
}
