/**
 * 
 */
package com.iot.servicestrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.UpGradeUtil;
import com.iot.utils.Constant;
import com.iot.utils.FileUtils;
import com.iot.utils.HttpsUtils;
import com.iot.utils.JedisUtils;
import com.iot.utils.JsonUtil;

/**   
 * @ClassName:  CheckService   
 * @Description:主动查询解析服务   
 * @author: dbr 
 * @date:   2018年10月22日 下午2:02:14   
 *      
 */
@Component
public class CheckService implements IServiceStrategy {

	/** 网站对接服务地址 */
	@Value("${website.baseurl}")
	private String baseUrl;
	
	/** 升级文件基目录 */
	@Value("${basefilepath}")
	private String baseFilePath;
	
	/** 升级文件每帧大小 */
	@Value("${packsize}")
	private int packSize;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		
		String logInfo = "上传check:设备id：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
		System.out.println(logInfo);
		String apiUrl = baseUrl + Constant.UPLOAD_CHECK_URL;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			String version = toStr(dataMap.get("version"));

			Map<String, Object> paramJson = new HashMap<>();
			paramJson.put("version", version);
			paramJson.put("deviceId", deviceId);

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject response = HttpsUtils.doPost(apiUrl, paramMap);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					int upgradeFlag = response.getIntValue("flag");
					/** 判断设备是否升级 0：升级 1：不升级 */
					if (upgradeFlag == Constant.UPGRADE_SUCCESS) {
						String filePath = response.getString("filePath");
						String newVersion = response.getString("version");
						loadUpgradeFile(deviceId, filePath, newVersion);
					}
					
					JSONObject param= new JSONObject();
					param.put("value", upgradeFlag);
					param.put("version", version);
					
					/**下发版本验证命令*/
					JSONObject command = new JSONObject();
					command.put("deviceId", deviceId);
					command.put("serviceId","UpversionService");
					command.put("method","upversion");	
					command.put("param", param.toString());
					UpGradeUtil.asynCommand(command.toString());
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("上传check处理异常," + serviceMap.toString(), e);
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
	public void loadUpgradeFile(String deviceId, String filePath, String version) {
		try {
			/** 获取升级文件 */
			String fileKey = filePath + "_" + version;
			JSONObject upgradeFile = new JSONObject();
			/** 判断升级文件是否已经缓存，未缓存生成缓存*/
			if (!JedisUtils.hasKey(fileKey)) {
				upgradeFile = FileUtils.parseUpgradeFile(filePath, version, filePath, packSize);
			} else {
				upgradeFile = (JSONObject) JedisUtils.get(fileKey);
			}
			int packNum = upgradeFile.getIntValue("packNum");
			
			/** 设备升级缓存key */
			String deviceProgress = Constant.PROGRESS + deviceId;
			if (JedisUtils.hasKey(deviceProgress)) {
				/** 存在设备升级缓存，对比当前升级信息和缓存升级信息是否一致 */
				JSONObject progress = (JSONObject) JedisUtils.get(deviceProgress);
				String progressVersion = progress.getString("version");
				/** 升级信息不一致，删除该设备缓存升级进程信息 */
				if (!fileKey.equals(progressVersion)) {
					/** 删除过期升级进度缓存 */
					JedisUtils.del(deviceProgress);
					/** 创建新升级进度缓存 */
					insertDeviceProgress(deviceId, fileKey, packNum);
				}
			} else {
				/** 创建新升级进度缓存 */
				insertDeviceProgress(deviceId, fileKey, packNum);
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
	private void insertDeviceProgress(String deviceId, String fileKey, int packNum) {
		/** 设备升级缓存key */
		String deviceProgress = Constant.PROGRESS + deviceId;
		JSONObject progressBody = new JSONObject();
		progressBody.put("deviceId", deviceId);
		progressBody.put("fileKey", fileKey);
		progressBody.put("packNum", packNum);
		progressBody.put("sendedPack", 0);

		JedisUtils.set(deviceProgress, progressBody);
	}
}
