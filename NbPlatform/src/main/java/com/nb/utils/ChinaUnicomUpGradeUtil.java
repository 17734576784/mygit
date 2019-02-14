/**   
* @Title: AsynCommandUtil.java 
* @Package com.iot.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月22日 上午9:19:26 
* @version V1.0   
*/
package com.nb.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import static com.nb.utils.BytesUtils.getBytesReserve;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nb.http.ChinaUnicomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;

/**
 * @ClassName: AsynCommandUtil
 * @Description: 下发命令工具类
 * @author dbr
 * @date 2018年11月22日 上午9:19:26
 * 
 */
public class ChinaUnicomUpGradeUtil {

	public static boolean asynCommand(String command, String appId, String secret) throws Exception {

		LoggerUtils.Logger(LogName.INFO).info("工具类接收下发命令请求：" + command);
		JSONObject paramAsynCommand = new JSONObject();
		try {
			paramAsynCommand = JSONObject.parseObject(command);

			

			String deviceId = ConverterUtils.toStr(paramAsynCommand.get("deviceId"));// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
			String serviceId = ConverterUtils.toStr(paramAsynCommand.get("serviceId"));// "WaterMeter";
			String method = ConverterUtils.toStr(paramAsynCommand.get("method"));// "SET_TEMPERATURE_READ_PERIOD";
			ObjectNode paras = JsonUtil.convertObject2ObjectNode(ConverterUtils.toStr(paramAsynCommand.get("param")));// "{\"value\":\"12\"}"

			
//			String deviceProgress = Constant.PROGRESS_CHINA_TELECOM + deviceId;
//			DeviceProgress progress = (DeviceProgress) JedisUtils.get(deviceProgress);
			JSONObject appInfo = new JSONObject();
			appInfo.put("appId", appId);
			appInfo.put("secret", secret);
			
			ChinaUnicomIotHttpsUtil httpsUtil = new ChinaUnicomIotHttpsUtil();
			httpsUtil.initSSLConfigForTwoWay();
			String accessToken = AuthenticationUtils.getChinaUnicomAccessToken(httpsUtil, appInfo);

			String urlPostAsynCmd = Constant.CHINA_UNICOM_POST_ASYN_CMD;
//			String appId = progress.getAppId();
			String callbackUrl = Constant.CHINA_UNICOM_REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
			
			Map<String, Object> paramCommand = new HashMap<>();
			paramCommand.put("serviceId", serviceId);
			paramCommand.put("method", method);
			paramCommand.put("paras", paras);

			Map<String, Object> paramPostAsynCmd = new HashMap<>();
			paramPostAsynCmd.put("deviceId", deviceId);
			paramPostAsynCmd.put("command", paramCommand);
			paramPostAsynCmd.put("callbackUrl", callbackUrl);

			String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

			Map<String, String> header = new HashMap<>();
			header.put(Constant.HEADER_APP_KEY, appId);
			header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

			HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);
			String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);

			JSONObject responseJson = JSON.parseObject(responseBody);
			String commandId = ConverterUtils.toStr(responseJson.getString("commandId"));

			if (commandId.isEmpty()) {
				return false;
			}

			JedisUtils.set(Constant.COMMAND + commandId, serviceId, 60 * 10 * 3);
//			if (serviceId.equals(Constant.UPDATASERVICE)) {
//				JedisUtils.set(Constant.UPGRADE + commandId, serviceId, 60 * 10);
//			}
		} catch (Exception e) {
			LoggerUtils.Logger(LogName.INFO).error("工具类接收下发命令异常：" + command);
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/** 
	* @Title: getCommandParam 
	* @Description: 创建命令参数 
	* @param @param deviceId
	* @param @param fileKey
	* @param @param packNum
	* @param @param sendedPack
	* @param @param upgradeFile
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getCommandParam(String deviceId, String fileKey, short packNum, short sendedPack, JSONObject upgradeFile)
			throws IOException {
		if (packNum <= sendedPack) {
			return null;
		}
		if (sendedPack < 0) {
			sendedPack = 0;
		}
		@SuppressWarnings("unchecked")
		Map<String, byte[]> fileMap = (Map<String, byte[]>) upgradeFile.get("data");
		byte[] data = fileMap.get(String.valueOf(sendedPack));

		byte fileFlag = 0X00; // 文件标识 0x00 清除传输文件，恢复到升级前状态
		byte fileAttribute = 0x00;// 文件属性 0x00 起始 中间帧 0X01 结束帧
		if (sendedPack > 0) {
			fileFlag = 0X01;
		}
		/** 结束帧发生0X01 */
		if ((sendedPack + 1) == packNum) {
			fileAttribute = 0X01;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		short dateLength = (short) data.length;
		dos.writeByte(fileFlag);
		dos.writeByte(fileAttribute);
		dos.write(getBytesReserve(packNum));
		dos.write(getBytesReserve(sendedPack));
		dos.write(getBytesReserve(dateLength));

		dos.write(data);
		short crc = (short) getCRC(baos.toByteArray());
		dos.write(getBytesReserve(crc));

		JSONObject param = new JSONObject();
		byte[] rawdata = baos.toByteArray();
		param.put("rawdata", rawdata);

		/** 下发版本验证命令 */
		JSONObject command = new JSONObject();
		command.put("deviceId", deviceId);
		command.put("serviceId", Constant.UPDATASERVICE);
		command.put("method", Constant.UPDATACMD);
		command.put("param", param.toString());
		return command.toString();
	}
	
	 /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
	public static int getCRC(byte[] bytes) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;
		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}
		return CRC;
	}
}
