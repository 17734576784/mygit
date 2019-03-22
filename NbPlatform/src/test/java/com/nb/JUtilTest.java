package com.nb;

import static com.nb.utils.ConverterUtils.toShort;
import static com.nb.utils.ConverterUtils.toStr;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.nb.http.HttpsClientUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.model.DeviceProgress;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.chinamobile.ChinaMobileCommandService;
import com.nb.utils.ChinaUnicomUpGradeUtil;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.FileUtils;
import com.nb.utils.JedisUtils;
/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {NbPlatformApplication.class })
public class JUtilTest {

	@Autowired
	private ChinaMobileCommandService chinaMobileCommandService;
	 
	@Test
	public void contextLoads() throws Exception {
//		String apiUrl = "http://222.222.60.178:18130/Enterprise_MeterPay/pay/nbiot/" + "nbNotifyAction!checkExistOrders.action";
//
//		String deviceId="e0347732-5c0b-4549-8acb-3247a811ac05";
//		String version="1.001.2019022803110000";
//
//		JSONObject paramJson = new JSONObject();
//		paramJson.put("version", "1.001.2019022803110000");
//		paramJson.put("deviceId", "e0347732-5c0b-4549-8acb-3247a811ac05");
//
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("param", paramJson.toJSONString());
//		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
//		StreamClosedHttpResponse httpResponse = httpsClientUtil.doPostFormUrlEncodedGetStatusLine(apiUrl, paramMap);
//
//		JSONObject response = JSONObject.parseObject(CommFunc.handleJsonStr(httpResponse.getContent()));
//		System.out.println("response ： "+ response);
//		LoggerUtils.Logger(LogName.INFO).info("response: "+response);
//		if (response != null && !response.isEmpty()) {
//			if (response.getInteger("status") == Constant.SUCCESS) {
//				/** 判断设备是否升级 0:升级 1：不升级 */
//				int upgradeFlag = response.getIntValue("flag");
//				String appId = response.getString("appId");
//				String secret = response.getString("secret");
//				
//				if (upgradeFlag == Constant.UPGRADE_SUCCESS) {
//					String filePath = response.getString("filePath");
//					String newVersion = response.getString("version");
//					loadUpgradeFile(deviceId, filePath, newVersion, appId, secret);
//					version = newVersion;
//				}
//				
//				JSONObject param= new JSONObject();
//				param.put("value", upgradeFlag);
//				param.put("version", version);
//				
//				/**下发询问设备是否升级命令*/
//				JSONObject command = new JSONObject();
//				command.put("deviceId", deviceId);
//				command.put("serviceId",Constant.UPVERSIONSERVICE);
//				command.put("method",Constant.UPVERSION);	
//				command.put("param", param.toString());
//				ChinaUnicomUpGradeUtil.asynCommand(command.toString(), appId, secret);
//			} 
//		}
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
	public static void loadUpgradeFile(String deviceId, String filePath, String version, String appId, String secret) {
		try {
			/** split里面必须是正则表达式，"\\"的作用是对字符串转义 */
			String temp[] = filePath.split("/"); 
			String fileName = temp[temp.length - 1];
	        
			filePath = "http://222.222.60.178:18130/Enterprise_MeterPay/softversion/" + filePath;
			/** 获取升级文件 */
			String fileKey = fileName + "_" + version;
			JSONObject upgradeFile = new JSONObject();
			/** 判断升级文件是否已经缓存，未缓存生成缓存*/
			if (!JedisUtils.hasKey(fileKey)) {
				upgradeFile = FileUtils.parseUpgradeFile(fileKey, filePath, 256);
			} else {
				upgradeFile = (JSONObject) JedisUtils.get(fileKey);
			}
			/** 升级文件总包数 */
			short packNum = upgradeFile.getShortValue("packNum");
			
			/** 设备升级缓存key */
			String deviceProgress = Constant.PROGRESS_CHINA_UNICOM + deviceId;
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
	private static void insertDeviceProgress(String deviceId, String fileKey, short packNum,String appId,String secret) {
		/** 设备升级缓存key */
		String deviceProgress = Constant.PROGRESS_CHINA_UNICOM + deviceId;
		DeviceProgress progress =  new DeviceProgress(); 
		progress.setDeviceId(deviceId);
		progress.setAppId(appId);
		progress.setSecret(secret);
		progress.setFileKey(fileKey);
		progress.setPackNum(packNum);
		progress.setSendedPack(toShort(-1));
		progress.setSendTime(toStr(LocalDateTime.now()));
		progress.setRetryCount(0);

		JedisUtils.set(deviceProgress, progress);
	}
}
