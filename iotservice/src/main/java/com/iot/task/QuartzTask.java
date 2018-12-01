package com.iot.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.ConverterUtils;
import com.iot.utils.JedisUtils;
import com.iot.utils.UpGradeUtil;

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

//  每分钟启动
	@Scheduled(cron = "0 0/1 * * * ?")
	public void reSendUpgrade() {

		try {
			Set<String> keys = JedisUtils.getKeys("progress_*");
			for (String deviceProgress : keys) {
				JSONObject progressBody = (JSONObject) JedisUtils.get(deviceProgress);
				short retryCount = progressBody.getShortValue("retryCount");
				System.out.println(LocalDateTime.now() +"   retryCount :" + retryCount);

				if (retryCount >= retrycount) {
					continue;
				}

				LocalDateTime sendTime = LocalDateTime.parse((CharSequence) progressBody.get("sendTime")) ;
				Duration duration = Duration.between(sendTime, LocalDateTime.now());
				long timeDiff = duration.getSeconds();
				System.out.println(LocalDateTime.now() +"   timeDiff :" + timeDiff);
				if (timeDiff < upgradetimeout) {
					continue;
				}

				System.out.println("自动任务" + progressBody);
				String fileKey = progressBody.getString("fileKey");
				short packNum = progressBody.getShortValue("packNum");
				short sendedPack = progressBody.getShortValue("sendedPack");
				String deviceId = deviceProgress.split("_")[1];

				JSONObject upgradeFile = (JSONObject) JedisUtils.get(fileKey);
				if (upgradeFile == null || upgradeFile.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("升级文件：" + fileKey + "不存在");
					return;
				}

				/** 发送缓存已发送成功包的下一包数据 */
				sendedPack += 1;
				String command = UpGradeUtil.getCommandParam(deviceId, fileKey, packNum, sendedPack, upgradeFile);
				if (null == command || command.isEmpty()) {
					LoggerUtils.Logger(LogName.CALLBACK).info("从发任务组建命令参数失败：");
					return;
				}
				progressBody.put("sendTime", ConverterUtils.toStr(LocalDateTime.now()));
				progressBody.put("retryCount", ++retryCount);
				UpGradeUtil.asynCommand(command.toString());
				JedisUtils.set(deviceProgress, progressBody);
				LoggerUtils.Logger(LogName.INFO).info("自动任务发送命令：" + progressBody);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.INFO).error("命令重发任务异常", e);
		}
	}
}
