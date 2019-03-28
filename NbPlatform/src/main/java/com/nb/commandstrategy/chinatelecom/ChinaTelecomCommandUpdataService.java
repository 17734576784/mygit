/**   
* @Title: UpdataService.java 
* @Package com.nb.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:37:00 
* @version V1.0   
*/
package com.nb.commandstrategy.chinatelecom;

import static com.nb.utils.ConverterUtils.*;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.commandstrategy.ICommandService;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.DeviceProgress;
import com.nb.utils.ChinaTelecomUpGradeUtil;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
/** 
* @ClassName: ChinaTelecomCommandUpdataService 
* @Description: 远程升级上行解析服务
* @author dbr
* @date 2018年10月25日 下午2:37:00 
*  
*/
@Component
public class ChinaTelecomCommandUpdataService implements ICommandService {

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		
		String logInfo = "升级命令回复，设备id：" + deviceId + ",内容：" + commandMap.toString();
		LoggerUtils.Logger(LogName.INFO).info(logInfo);
		try {
			int receivedPackNum = toInt(commandMap.get("result")); 
			
			/** 设备升级缓存key */
			String deviceProgress = Constant.PROGRESS_CHINA_TELECOM + deviceId;
			if (JedisUtils.hasKey(deviceProgress)) {
				DeviceProgress progressBody = (DeviceProgress) JedisUtils.get(deviceProgress);
				String fileKey = progressBody.getFileKey();
				short packNum = progressBody.getPackNum();
				short sendedPack = progressBody.getSendedPack();

				System.out.println(LocalDateTime.now() + "  " + deviceId + "   收到： " + receivedPackNum+ "  sendedPack :" + sendedPack);
				if (receivedPackNum < sendedPack) {
					return;
				}
				
				if (receivedPackNum == packNum - 1) {
					JedisUtils.del(deviceProgress);
					return;
				}
				

				/** 错误重传 */
				if (receivedPackNum == 0XFFFF) {
					if (sendedPack == 0) {
						receivedPackNum = 0;	
					}else {
						receivedPackNum = (short) (sendedPack + 1);
						receivedPackNum = receivedPackNum == -1 ? 0 : receivedPackNum;
					}
				} else {
					progressBody.setSendedPack((short)receivedPackNum);
					receivedPackNum += 1;
				}

				progressBody.setReceiveFlag(true);

				if (receivedPackNum >= packNum || receivedPackNum < 0) {
					return;
				}

				JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
				if (upgradeFile == null || upgradeFile.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
					return;
				} 
				Thread.sleep(4000);
				System.out.println(LocalDateTime.now() + "  下发 : " + receivedPackNum );

				String command = ChinaTelecomUpGradeUtil.getCommandParam(deviceId, fileKey, packNum, (short)receivedPackNum, upgradeFile);
				if (null == command || command.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("组建命令参数失败：" + commandMap);
					return;
				}
				
				progressBody.setSendTime(ConverterUtils.toStr(LocalDateTime.now()));
				progressBody.setRetryCount(0);				
				ChinaTelecomUpGradeUtil.asynCommand(command.toString(), progressBody.getAppId(),
						progressBody.getSecret());
				JedisUtils.set(deviceProgress, progressBody);
			} else {
				LoggerUtils.Logger(LogName.INFO).info("不存在设备：" + deviceId + ",升级进度缓存");
				System.out.println("不存在设备：" + deviceId + ",升级进度缓存");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("升级包异常" + commandMap + deviceId);
		}
	}
}
