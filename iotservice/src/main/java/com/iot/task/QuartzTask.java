package com.iot.task;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
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
//  每分钟启动
	@Scheduled(cron = "0 0/1 * * * ?")
	public void reSendUpgrade() {

		try {
			Set<String> keys = JedisUtils.getKeys("progress_*");
			for (String deviceProgress : keys) {
				JSONObject progressBody = (JSONObject) JedisUtils.get(deviceProgress);
				short retryCount = progressBody.getShortValue("retryCount");
				if (retryCount >= 3) {
					continue;
				}
				
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
				progressBody.put("sendTime", LocalDateTime.now());
				progressBody.put("retryCount", ++retryCount);
				UpGradeUtil.asynCommand(command.toString());
				JedisUtils.set(deviceProgress, progressBody);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.INFO).error("命令重发任务异常", e);
		}
	}
}
