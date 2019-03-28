/**
 * 
 */
package com.nb.servicestrategy.chinatelecom;

import static com.nb.utils.ConverterUtils.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.DeviceProgress;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.ChinaTelecomUpGradeUtil;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.FileUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/**   
 * @ClassName:  ChinaTelecomCheckService   
 * @Description:主动查询解析服务   
 * @author: dbr 
 * @date:   2018年10月22日 下午2:02:14   
 *      
 */
@Component
public class ChinaTelecomCheckService implements IServiceStrategy {

	/** 网站对接服务地址 */
	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** 升级文件基目录 */
	@Value("${basefilepath}")
	private String baseFilePath;
	
	/** 升级文件每帧大小 */
	@Value("${packsize}")
	private int packSize;
	
	/** 升级文件每帧大小 */
	@Value("${checkTime}")
	private int checkTime;
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		
		String logInfo = "上传check:设备id：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		String checkKey = Constant.CHECK + "_" + deviceId;
		if (JedisUtils.hasKey(checkKey)) {
			return;
		} else {
			JedisUtils.set(checkKey, checkKey, checkTime);
		}
		
		String apiUrl = baseUrl + Constant.UPLOAD_CHECK_URL;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			String version = toStr(dataMap.get("version"));

			JSONObject paramJson = new JSONObject();
			paramJson.put("version", version);
			paramJson.put("deviceId", deviceId);
//			System.out.println(LocalDateTime.now() +" response ： ??? ");

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("param", paramJson.toJSONString());
			HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
			StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(apiUrl, paramMap);

			JSONObject response =  JSONObject.parseObject(CommFunc.handleJsonStr(httpResponse.getContent()));
			System.out.println(LocalDateTime.now() +" response ： "+ response);
			LoggerUtils.Logger(LogName.INFO).info("response: "+response);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					/** 判断设备是否升级 0:升级 1：不升级 */
					int upgradeFlag = response.getIntValue("flag");
					String appId = response.getString("appId");
					String secret = response.getString("secret");
					
					if (upgradeFlag == Constant.UPGRADE_SUCCESS) {
						String filePath = response.getString("filePath");
						String newVersion = response.getString("version");

						loadUpgradeFile(deviceId, filePath, newVersion, appId, secret);
						version = newVersion;
					}
					
					JSONObject param= new JSONObject();
					param.put("value", upgradeFlag);
					param.put("version", version);
					
					/**下发询问设备是否升级命令*/
					JSONObject command = new JSONObject();
					command.put("deviceId", deviceId);
					command.put("serviceId",Constant.UPVERSIONSERVICE);
					command.put("method",Constant.UPVERSION);	
					command.put("param", param.toString());
					ChinaTelecomUpGradeUtil.asynCommand(command.toString(), appId, secret);
					System.out.println(LocalDateTime.now()  + " 下发询问设备是否升级命令: " + command.toString());
				} 
			} else {
				LoggerUtils.Logger(LogName.CALLBACK).info("发送主动查询解析服务返回结果为空，" + logInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("上传check处理异常," + logInfo, e);
		}	
	}
	
	
	
	/** 
	* @Title: loadUpgradeFile 
	* @Description: 加载升级文件
	* @param @param filePath
	* @param @param version
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public void loadUpgradeFile(String deviceId, String filePath, String version, String appId, String secret) {
		try {
			/** split里面必须是正则表达式，"\\"的作用是对字符串转义 */
			String temp[] = filePath.split("/"); 
			String fileName = temp[temp.length - 1];
	        
			filePath = baseFilePath + filePath;
			/** 获取升级文件 */
			String fileKey = fileName + "_" + version;
			JSONObject upgradeFile = new JSONObject();
			/** 判断升级文件是否已经缓存，未缓存生成缓存*/
			if (!JedisUtils.hasKey(fileKey)) {
				upgradeFile = FileUtils.parseUpgradeFile(fileKey, filePath, packSize);
			} else {
				upgradeFile = (JSONObject) JedisUtils.get(fileKey);
			}
			/** 升级文件总包数 */
			short packNum = upgradeFile.getShortValue("packNum");
			
			/** 设备升级缓存key */
			String deviceProgress = Constant.PROGRESS_CHINA_TELECOM + deviceId;
			if (JedisUtils.hasKey(deviceProgress)) {
				/** 存在设备升级缓存，对比当前升级信息和缓存升级信息是否一致 */
				DeviceProgress progress = (DeviceProgress) JedisUtils.get(deviceProgress);
				String progressVersion = progress.getFileKey();
				/** 升级信息不一致，删除该设备缓存升级进程信息 */
				if (!fileKey.equals(progressVersion)) {
					/** 删除过期升级进度缓存 */
					JedisUtils.del(deviceProgress);
					/** 创建新升级进度缓存 */
					insertDeviceProgress(deviceId, fileKey, packNum, appId, secret);
				}
			} else {
				/** 创建新升级进度缓存 */
				insertDeviceProgress(deviceId, fileKey, packNum, appId, secret);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("获取设备升级异常，" + deviceId + "," + filePath + "," + version);
		}
	}
	
	/** 
	* @Title: insertDeviceProgress 
	* @Description: 插入设备升级进度缓存
	* @param @param deviceId
	* @param @param fileKey
	* @param @param packNum    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertDeviceProgress(String deviceId, String fileKey, short packNum, String appId, String secret) {
		/** 设备升级缓存key */
		String deviceProgress = Constant.PROGRESS_CHINA_TELECOM + deviceId;
		DeviceProgress progress =  new DeviceProgress(); 
		progress.setDeviceId(deviceId);
		progress.setFileKey(fileKey);
		progress.setPackNum(packNum);
		progress.setSendedPack(toShort(-1));
		progress.setSendTime(toStr(LocalDateTime.now()));
		progress.setRetryCount(0);
		progress.setAppId(appId);
		progress.setSecret(secret);
		
		JedisUtils.set(deviceProgress, progress);
	}
}
