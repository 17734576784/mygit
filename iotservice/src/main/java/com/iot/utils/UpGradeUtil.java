/**   
* @Title: AsynCommandUtil.java 
* @Package com.iot.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月22日 上午9:19:26 
* @version V1.0   
*/
package com.iot.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;

/**
 * @ClassName: AsynCommandUtil
 * @Description: 下发命令工具类
 * @author dbr
 * @date 2018年11月22日 上午9:19:26
 * 
 */
public class UpGradeUtil {

	public static boolean asynCommand(String command) throws Exception {

		LoggerUtils.Logger(LogName.INFO).info("工具类接收下发命令请求：" + command);
		JSONObject paramAsynCommand = new JSONObject();
		try {
			paramAsynCommand = JSONObject.parseObject(command);

			IotHttpsUtil httpsUtil = new IotHttpsUtil();
			httpsUtil.initSSLConfigForTwoWay();
			String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

			String urlPostAsynCmd = Constant.POST_ASYN_CMD;
			String appId = Constant.APPID;
			String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

			String deviceId = ConverterUtils.toStr(paramAsynCommand.get("deviceId"));// "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
			String serviceId = ConverterUtils.toStr(paramAsynCommand.get("serviceId"));// "WaterMeter";
			String method = ConverterUtils.toStr(paramAsynCommand.get("method"));// "SET_TEMPERATURE_READ_PERIOD";
			ObjectNode paras = JsonUtil.convertObject2ObjectNode(ConverterUtils.toStr(paramAsynCommand.get("param")));// "{\"value\":\"12\"}"

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

			JedisUtils.set(commandId, serviceId, 172800);
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
	public static String getCommandParam(String deviceId, String fileKey, int packNum, int sendedPack, JSONObject upgradeFile)
			throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, byte[]> fileMap = (Map<String, byte[]>) upgradeFile.get("data");
		byte[] data = fileMap.get(String.valueOf(sendedPack));

		byte fileFlag = 0X00; // 文件标识
		byte fileAttribute = 0x00;// 文件属性
		if (sendedPack > 0) {
			fileFlag = 0X01;
		}

		if ((sendedPack + 1) == packNum) {
			fileAttribute = 0X01;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		int dateLength = data.length;
		dos.writeByte(fileFlag);
		dos.writeByte(fileAttribute);
		dos.writeByte(packNum & 0XFF);
		dos.writeByte(packNum >> 8 & 0XFF);
		
		dos.writeByte(sendedPack & 0XFF);
		dos.writeByte(sendedPack >> 8 & 0XFF);
		dos.writeByte(dateLength & 0XFF);
		dos.writeByte(dateLength >> 8 & 0XFF);
		
		dos.write(data);
		int crc = getCRC(baos.toByteArray());
		dos.writeShort(crc);

		JSONObject param = new JSONObject();
		param.put("rawdata", baos);

		/** 下发版本验证命令 */
		JSONObject command = new JSONObject();
		command.put("deviceId", deviceId);
		command.put("serviceId", "UpdataService");
		command.put("method", "updatacmd");
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
