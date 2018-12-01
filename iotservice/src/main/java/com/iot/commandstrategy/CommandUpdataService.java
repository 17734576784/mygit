/**   
* @Title: UpdataService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:00 
* @version V1.0   
*/
package com.iot.commandstrategy;

import static com.iot.utils.ConverterUtils.*;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.UpGradeUtil;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.JedisUtils;
/** 
* @ClassName: CommandUpdataService 
* @Description: 远程升级上行解析服务
* @author dbr
* @date 2018年10月25日 下午2:37:00 
*  
*/
@Component
public class CommandUpdataService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.iot.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		
		String logInfo = "升级命令回复，设备id：" + deviceId + ",内容：" + commandMap.toString();
		LoggerUtils.Logger(LogName.INFO).info(logInfo);
		try {
			int receivedPackNum = toInt(commandMap.get("result")); 
			
			/** 设备升级缓存key */
			String deviceProgress = Constant.PROGRESS + deviceId;
			if (JedisUtils.hasKey(deviceProgress)) {
				JSONObject progressBody = (JSONObject) JedisUtils.get(deviceProgress);
				String fileKey = progressBody.getString("fileKey");
				short packNum = progressBody.getShortValue("packNum");
				short sendedPack = progressBody.getShortValue("sendedPack");

				System.out.println(LocalDateTime.now() + "  " + deviceId + "   receivedPackNum : " + receivedPackNum
						+ "  sendedPack :" + sendedPack);
				if (receivedPackNum < sendedPack) {
					return;
				}
				
				if (receivedPackNum == packNum - 1) {
					JedisUtils.del(deviceProgress);
					return;
				}
				
				/** 错误重传 */
				if (receivedPackNum == 0XFFFF) {
					receivedPackNum = sendedPack + 1;
					receivedPackNum = receivedPackNum == -1 ? 0 : receivedPackNum;
				} else {
					progressBody.put("sendedPack", receivedPackNum);
					receivedPackNum += 1;
				}

				
				if (receivedPackNum >= packNum || receivedPackNum < 0) {
					return;
				}

				JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
				if (upgradeFile == null || upgradeFile.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
					return;
				} 
				Thread.sleep(3000);

				String command = UpGradeUtil.getCommandParam(deviceId, fileKey, packNum, (short)receivedPackNum, upgradeFile);
				if (null == command || command.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("组建命令参数失败：" + commandMap);
					return;
				}
				
				progressBody.put("sendTime", ConverterUtils.toStr(LocalDateTime.now()));
				progressBody.put("retryCount", 0);				
				UpGradeUtil.asynCommand(command.toString());
				JedisUtils.set(deviceProgress, progressBody);
//				System.out.println("在设备：" + deviceId + "发送升级命令成功，" + command);
			} else {
				LoggerUtils.Logger(LogName.INFO).info("不存在设备：" + deviceId + ",升级进度缓存");
				System.out.println("不存在设备：" + deviceId + ",升级进度缓存");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("升级包异常" + commandMap + deviceId);
		}
	}
}
