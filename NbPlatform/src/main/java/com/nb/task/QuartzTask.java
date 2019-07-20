package com.nb.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.DeviceProgress;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.ChinaTelecomUpGradeUtil;
import com.nb.utils.ChinaUnicomUpGradeUtil;
import com.nb.utils.Constant;

/**
 * @ClassName: QuartzTask
 * @Description:升级命令超时重发
 * @author: dbr
 * @date: 2018年11月29日 下午6:10:29
 * 
 */
@Component
public class QuartzTask {
	/** 升级命令超时时间(秒) */
	@Value("${upgradetimeout}")
	private int upgradetimeout;

	/** 升级命令最大重发次数 */
	@Value("${retrycount}")
	private int retrycount;

	/** 
	* @Title: reSendChinaTelecomUpgrade 
	* @Description: 中国电信升级命令重复 每分钟启动 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 */5 * * * ?")
	public void reSendChinaTelecomUpgrade() {

		try {
			Set<String> keys = JedisUtils.getKeys(Constant.PROGRESS_CHINA_TELECOM + "*");
			for (String deviceProgress : keys) {
				sendChinaTelecomUpgrade(deviceProgress);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.INFO).error("命令重发任务异常", e);
		}
	}
	
	/** 
	* @Title: reSendChinaUnicomUpgrade 
	* @Description: 中国联通升级命令重复 每分钟启动 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Scheduled(cron = "0 */5 * * * ?")
	public void reSendChinaUnicomUpgrade() {

		try {
			Set<String> keys = JedisUtils.getKeys(Constant.PROGRESS_CHINA_UNICOM + "*");
			for (String deviceProgress : keys) {
				sendChinaUnicomUpgrade(deviceProgress);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.INFO).error("命令重发任务异常", e);
		}
	}
	
	/** 
	* @Title: sendChinaUnicomUpgrade 
	* @Description: 中国联通平台发送升级指令
	* @param @param deviceProgress    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendChinaUnicomUpgrade(String deviceProgress) {
		try {
			Thread.sleep(3000);

			DeviceProgress progressBody = (DeviceProgress) JedisUtils.get(deviceProgress);
			
			if (!progressBody.isReceiveFlag()) {
				return;
			}
			
			/** 重试次数达到上限不执行 */
			int retryCount = progressBody.getRetryCount();
			if (retryCount >= retrycount) {
				return;
			}

			LocalDateTime sendTime = LocalDateTime.parse((CharSequence) progressBody.getSendTime()) ;
			Duration duration = Duration.between(sendTime, LocalDateTime.now());
			long timeDiff = duration.getSeconds();
			/** 未超时不执行命令下发 */
			if (timeDiff < upgradetimeout) {
				return;
			}

			String fileKey = progressBody.getFileKey();
			short packNum = progressBody.getPackNum();
			short sendedPack = progressBody.getSendedPack();
			String deviceId = progressBody.getDeviceId();
			
			JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
			if (upgradeFile == null || upgradeFile.isEmpty()) {
				LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
				return;
			}
			
			sendedPack = (short) (progressBody.isReceiveFlag() ? sendedPack + 1 : 0); 
			/** 发送缓存已发送成功包的下一包数据 */
			if (sendedPack == packNum) {
				progressBody.setRetryCount(retrycount);
				JedisUtils.set(deviceProgress, progressBody);
				return;
			}
			String command = ChinaUnicomUpGradeUtil.getCommandParam(deviceId, fileKey, packNum, sendedPack, upgradeFile);
			if (null == command || command.isEmpty()) {
				LoggerUtils.Logger(LogName.CALLBACK).info("从发任务组建命令参数失败：");
				return;
			}
			
			retryCount += 1;
			
			progressBody.setSendTime(ConverterUtils.toStr(LocalDateTime.now()));
			progressBody.setRetryCount(retryCount);
			ChinaUnicomUpGradeUtil.asynCommand(command.toString(), progressBody.getAppId(), progressBody.getSecret());
			
			JedisUtils.set(deviceProgress, progressBody);
			LoggerUtils.Logger(LogName.INFO).info("自动任务发送命令：" + progressBody.toString());
//			System.out.println("自动任务:" + progressBody.toString());
			
		} catch (Exception e) {
			LoggerUtils.Logger(LogName.INFO).error("sendUpgradePack异常", e);
			e.printStackTrace();
		}
	}
	
	
	/** 
	* @Title: sendChinaTelecomUpgrade 
	* @Description: 中国电信发生升级指令 
	* @param @param deviceProgress    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sendChinaTelecomUpgrade(String deviceProgress) {
		try {
			Thread.sleep(3000);

			DeviceProgress progressBody = (DeviceProgress) JedisUtils.get(deviceProgress);
			
			if (!progressBody.isReceiveFlag()) {
				return;
			}
			
			/** 重试次数达到上限不执行 */
			int retryCount = progressBody.getRetryCount();
			if (retryCount >= retrycount) {
				return;
			}

			LocalDateTime sendTime = LocalDateTime.parse((CharSequence) progressBody.getSendTime()) ;
			Duration duration = Duration.between(sendTime, LocalDateTime.now());
			long timeDiff = duration.getSeconds();
			/** 未超时不执行命令下发 */
			if (timeDiff < upgradetimeout) {
				return;
			}

			String fileKey = progressBody.getFileKey();
			short packNum = progressBody.getPackNum();
			short sendedPack = progressBody.getSendedPack();
			String deviceId = progressBody.getDeviceId();
			
			JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
			if (upgradeFile == null || upgradeFile.isEmpty()) {
				LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
				return;
			}
			
			sendedPack = (short) (progressBody.isReceiveFlag() ? sendedPack + 1 : 0); 
			/** 发送缓存已发送成功包的下一包数据 */
			if (sendedPack == packNum) {
				progressBody.setRetryCount(retrycount);
				JedisUtils.set(deviceProgress, progressBody);
				return;
			}
			String command = ChinaTelecomUpGradeUtil.getCommandParam(deviceId, fileKey, packNum, sendedPack, upgradeFile);
			if (null == command || command.isEmpty()) {
				LoggerUtils.Logger(LogName.CALLBACK).info("从发任务组建命令参数失败：");
				return;
			}
			
			retryCount += 1;
			
			progressBody.setSendTime(ConverterUtils.toStr(LocalDateTime.now()));
			progressBody.setRetryCount(retryCount);
			ChinaTelecomUpGradeUtil.asynCommand(command.toString(), progressBody.getAppId(), progressBody.getSecret());
			
			JedisUtils.set(deviceProgress, progressBody);
			LoggerUtils.Logger(LogName.INFO).info("自动任务发送命令：" + progressBody.toString());
//			System.out.println("自动任务:" + progressBody.toString());
			
		} catch (Exception e) {
			LoggerUtils.Logger(LogName.INFO).error("sendUpgradePack异常", e);
			e.printStackTrace();
		}
	}
}
