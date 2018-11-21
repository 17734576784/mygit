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
			String version = toStr(dataMap.get("ver"));

			Map<String, Object> paramJson = new HashMap<>();
			paramJson.put("version", version);
			paramJson.put("deviceId", deviceId);

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject response = HttpsUtils.doPost(apiUrl, paramMap);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					int upgradeFlag = response.getIntValue("flag");
					/** 判断设备是否升级 */
					if (upgradeFlag == Constant.UPGRADE) {
						String filePath = response.getString("filePath");
						String newVersion = response.getString("version");
						deviceUpgrade(deviceId, filePath, newVersion);
					}
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void deviceUpgrade(String deviceId, String filePath, String version) {
		try {
			/** 获取升级文件 */
			JSONObject upgradeFileJson = loadUpgradeFile(deviceId, filePath, version);
			if (upgradeFileJson != null && !upgradeFileJson.isEmpty()) {
				/** 下发命令 */
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("开始设备升级异常，" + deviceId + "," + filePath + "," + version);
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
	public JSONObject loadUpgradeFile(String deviceId, String filePath, String version) {
		JSONObject json = new JSONObject();
		String fileKey = filePath + "_" + version;
		/** 设备升级缓存key */
		String deviceProgress = Constant.PROGRESS + deviceId;
		try {
			if (JedisUtils.hasKey(deviceProgress)) {
				/**存在设备升级缓存，对比当前升级信息和缓存升级信息是否一致*/
				JSONObject progress = (JSONObject) JedisUtils.get(deviceProgress);
				String progressVersion = progress.getString("version");
				/** 升级信息不一致，删除该设备缓存升级进程信息 */
				if (!fileKey.equals(progressVersion)) {
					JedisUtils.del(deviceProgress);
				}
			}
			
			/** 存在直接返回 */
			if (JedisUtils.hasKey(fileKey)) {
				json = (JSONObject) JedisUtils.get(fileKey);
				return json;
			} 
			
			json = FileUtils.parseUpgradeFile(filePath, version, filePath, packSize);
			
		} catch (Exception e) {
			LoggerUtils.Logger(LogName.CALLBACK).error("加载升级文件异常！" + fileKey);
			e.printStackTrace();
		}

		return json;
	}
	
}
