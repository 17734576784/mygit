package com.iot.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.FileUtils;
import com.iot.utils.HttpsUtil;
import com.iot.utils.JedisUtils;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;

import static com.iot.utils.ConverterUtils.*;

@RestController
public class CallBackController {

	@Autowired
	private JedisUtils jedisUtils;

	@RequestMapping(value = "/test")
	public String test(String name) {
		System.out.println("Helo");
		return "Hello" + name;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvAddDeviceNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		Log4jUtils.getInfo().info("接收addDevice" + addDevice_NotifyMessage);
		try {
			Map<String, String> MessageMap = new HashMap<String, String>();
			MessageMap = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, MessageMap.getClass());
			String notifyType = toStr(MessageMap.get("notifyType"));
			String deviceId = toStr(MessageMap.get("deviceId"));
			String gatewayId = toStr(MessageMap.get("gatewayId"));
			Object deviceInfo = MessageMap.get("deviceInfo");

			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(deviceInfo, dataMap.getClass());
			String manufacturerId = toStr(dataMap.get("manufacturerId"));
			String manufacturerName = toStr(dataMap.get("manufacturerName"));
			String model = toStr(dataMap.get("model"));
			String deviceType = toStr(dataMap.get("deviceType"));

			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("manufacturerId", manufacturerId);
			json.put("manufacturerName", manufacturerName);
			json.put("model", model);
			json.put("deviceType", deviceType);

			System.out.println(LocalDateTime.now() + "  recvAddDeviceNotify : " + MessageMap.toString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("addDevice 处理消息异常");
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateDeviceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvInfoChangeNotify(@RequestBody Object updateDeviceInfo_NotifyMessage)
			throws IOException {

		Log4jUtils.getInfo().info("接收deviceInfoChanged" + updateDeviceInfo_NotifyMessage);

		try {
			Map<String, String> messageMap = new HashMap<String, String>();
			messageMap = JsonUtil.jsonString2SimpleObj(updateDeviceInfo_NotifyMessage, messageMap.getClass());
			String notifyType = toStr(messageMap.get("notifyType"));
			String deviceId = toStr(messageMap.get("deviceId"));
			String gatewayId = toStr(messageMap.get("gatewayId"));
			Object deviceInfo = messageMap.get("deviceInfo");

			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(deviceInfo, dataMap.getClass());
			String manufacturerId = toStr(dataMap.get("manufacturerId"));
			String manufacturerName = toStr(dataMap.get("manufacturerName"));
			String model = toStr(dataMap.get("model"));
			String deviceType = toStr(dataMap.get("deviceType"));

			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("manufacturerId", manufacturerId);
			json.put("manufacturerName", manufacturerName);
			json.put("model", model);
			json.put("deviceType", deviceType);

			System.out.println(LocalDateTime.now() + "  DeviceInfoChanged   " + messageMap.toString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("deviceInfoChanged处理消息异常");
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDataChangeNotify(@RequestBody Object updateDeviceData_NotifyMessage)
			throws IOException {

		Log4jUtils.getInfo().info("接收updateDeviceData:" + updateDeviceData_NotifyMessage);
		try {
			Map<String, String> messageMap = new HashMap<String, String>();
			messageMap = JsonUtil.jsonString2SimpleObj(updateDeviceData_NotifyMessage, messageMap.getClass());
			String notifyType = toStr(messageMap.get("notifyType"));
			String deviceId = toStr(messageMap.get("deviceId"));
			String gatewayId = toStr(messageMap.get("gatewayId"));
			Object service = messageMap.get("service");

			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(service, dataMap.getClass());
			String serviceId = toStr(dataMap.get("serviceId"));
			String serviceType = toStr(dataMap.get("serviceType"));
			String eventTime = toStr(dataMap.get("eventTime"));

			Object servicedata = dataMap.get("data");

			Map<String, String> serviceMap = new HashMap<String, String>();
			serviceMap = JsonUtil.jsonString2SimpleObj(servicedata, serviceMap.getClass());
			String broke = "", magnetic_disturb = "", photo = "", isdata = "";
			byte[] photoByte = null;
//			int packnum = 0;
//			int totalpack = 0;

			if (Constant.PHOTOSERVICE.equals(serviceId)) {
				// 当前包号
				int packnum = toInt(serviceMap.get("packnum"));
				// 消息总包数
				int totalpack = toInt(serviceMap.get("totalpack"));
				// 照片数据
				photo = toStr(serviceMap.get("rawdata"));
				photoByte = CommFunc.decode(photo);
				System.out.println(" packnum : " + packnum + "  totalpack : " + totalpack + " deviceId :/" + deviceId);

				Log4jUtils.getError().info(" packnum : " + packnum + "  totalpack : " + totalpack + " deviceId :/" + deviceId);
				JSONObject photoJson = new JSONObject();

				if (jedisUtils.hasKey(deviceId)) {
					// 获取该设备之前获取的数据
					photoJson = (JSONObject) jedisUtils.get(deviceId);
					// 获取该设备之前获取的照片数据
					LinkedHashMap<String, byte[]> photoMap = (LinkedHashMap<String, byte[]>) photoJson.get("data");
					if (totalpack == photoMap.size()) {
						photoMap.put(toStr(packnum), photoByte);
						photoJson.put("packnum", packnum);
						jedisUtils.set(deviceId, photoJson, 60 * 60 * 2);
						if (generateImage(photoMap, deviceId)) {
							jedisUtils.del(deviceId);
						}
					} else {
						jedisUtils.del(deviceId);
						insertDevicePhoto(deviceId, totalpack, packnum, photoByte);
					}
				} else {
					/** 不存在 该包存入redis */
					insertDevicePhoto(deviceId, totalpack, packnum, photoByte);
				}
			} else if (Constant.ALARMSERVICE.equals(serviceId)) {
				broke = toStr(serviceMap.get("broke"));
				magnetic_disturb = toStr(serviceMap.get("magnetic_disturb"));
			} else if (Constant.CHECKSERVICE.equals(serviceId)) {
				isdata = toStr(serviceMap.get("isdata"));
				System.out.println("isdata : "+ isdata);
				if (isdata.equals("1")) {
					HttpsUtil httpsUtil = new HttpsUtil();
					httpsUtil.initSSLConfigForTwoWay();
					String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

					String urlPostAsynCmd = Constant.POST_ASYN_CMD;
					String appId = Constant.APPID;
					String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
					
			        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"1\"}");

					Map<String, Object> paramCommand = new HashMap<>();
					paramCommand.put("serviceId", "PhotoData");
					paramCommand.put("method", "SendPhoto_once");
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
					CommFunc.result(Constant.SUCCESS, responseBody);
					System.out.println("isdata : 下发成功");

				}
			}

			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("serviceId", serviceId);
//			json.put("serviceId", totalpack);
			json.put("serviceType", serviceType);
			json.put("eventTime", eventTime);
			json.put("broke", broke);
			json.put("magnetic_disturb", magnetic_disturb);
			json.put("photo", photoByte);
//			json.put("packnum", packnum);
			json.put("packnum", isdata);

//			System.out.println(LocalDateTime.now() + "    recvDataChangeNotify  : " + messageMap.toString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("deviceDataChanged处理消息异常");
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * 缓存中不存在，放入缓存
	 * 
	 * @param deviceId
	 * @param totalpack
	 * @param packnum
	 * @param photoByte
	 */
	public void insertDevicePhoto(String deviceId, int totalpack, int packnum, byte[] photoByte) {
		LinkedHashMap<String, byte[]> photoMap = new LinkedHashMap<String, byte[]>(totalpack);
		for (int i = 0; i < totalpack; i++) {
			photoMap.put(toStr((i + 1)), new byte[0]);
		}
		photoMap.put(toStr(packnum), photoByte);
		JSONObject photoJson = new JSONObject();
		photoJson.put("packnum", packnum);
		photoJson.put("data", photoMap);
		jedisUtils.set(deviceId, photoJson, 60 * 60 * 2);
	}

	public boolean generateImage(LinkedHashMap<String, byte[]> photoMap, String deviceId) {

		try {
			String filePath = "d://" + deviceId + "_" + LocalDateTime.now().getNano() + ".jpeg";
			byte[] tmp = new byte[0];
			Iterator<Entry<String, byte[]>> iterator = photoMap.entrySet().iterator();

			while (iterator.hasNext()) {
				Entry<String, byte[]> entry = iterator.next();
				byte[] photoByte = entry.getValue();
				if (photoByte.length != 0) {
					System.out.println(entry.getKey() + "    " + photoByte.length);
					tmp = CommFunc.byteMerger(tmp, photoByte);
				} else {
					return false;
				}
			}
			CommFunc.byte2image(tmp, filePath);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			FileUtils.upload(Constant.UPLOADIMAGEURL, filePath, date, deviceId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deletedDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeleteDeviceNotify(@RequestBody Object deletedDevice_NotifyMessage)
			throws IOException {

		Map<String, String> data = new HashMap<String, String>();
		data = JsonUtil.jsonString2SimpleObj(deletedDevice_NotifyMessage, data.getClass());
		String notifyType = data.get("notifyType");
		String deviceId = data.get("deviceId");
		String gatewayId = data.get("gatewayId");

		JSONObject json = new JSONObject();
		json.put("notifyType", notifyType);
		json.put("deviceId", deviceId);
		json.put("gatewayId", gatewayId);

		System.out.println(LocalDateTime.now() + "    deviceDeleted  : " + json.toJSONString());
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "commandConfirmData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvMessageConfirmNotify(@RequestBody Object messageConfirm_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(messageConfirm_NotifyMessage.toString());
		System.out.println("recvMessageConfirmNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "updateServiceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvServiceInfoChangedNotify(@RequestBody Object updateServiceInfo_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(updateServiceInfo_NotifyMessage.toString());
		System.out.println("recvServiceInfoChangedNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "commandRspData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvCommandRspdNotify(@RequestBody Object commandRspData_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(commandRspData_NotifyMessage.toString());
		System.out.println("recvCommandRspdNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "deviceEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceEventNotify(@RequestBody Object deviceEvent_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(deviceEvent_NotifyMessage.toString());
		System.out.println("recvDeviceEventNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "ruleEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvRuleEventNotify(@RequestBody Object ruleEvent_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(ruleEvent_NotifyMessage.toString());
		System.out.println("recvRuleEventNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "updateDeviceDatas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceDatasChangeDNotify(@RequestBody Object updateDeviceDatas_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(updateDeviceDatas_NotifyMessage.toString());
		System.out.println("recvDeviceDatasChangeDNotify   " + resulr);
		System.out.println();
//		recvDeviceDatasChangeDNotify   "{notifyType=deviceDatasChanged, requestId=null, deviceId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, gatewayId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, services=[{serviceId=Alarm1, serviceType=Alarm1, data={broke=1, magnetic_disturb=1}, eventTime=20180903T085935Z}]}"

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "reportCmdExecResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvReportCmdExeCResultDNotify(
			@RequestBody Object reportCmdExecResult_NotifyMessage) throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(reportCmdExecResult_NotifyMessage.toString());
		System.out.println("recvDeviceDatasChangeDNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceBind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceBindNotify(@RequestBody Object deviceBind_NotifyMessage)
			throws IOException {

		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap = JsonUtil.jsonString2SimpleObj(deviceBind_NotifyMessage, messageMap.getClass());
		String notifyType = messageMap.get("notifyType");
		String deviceId = messageMap.get("deviceId");
		String resultCode = messageMap.get("resultCode");
		Object deviceInfo = messageMap.get("deviceInfo");

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(deviceInfo, dataMap.getClass());
		String manufacturerId = toStr(dataMap.get("manufacturerId"));
		String manufacturerName = toStr(dataMap.get("manufacturerName"));
		String model = toStr(dataMap.get("model"));
		String location = toStr(dataMap.get("location"));
		String deviceType = toStr(dataMap.get("deviceType"));

		JSONObject json = new JSONObject();
		json.put("notifyType", notifyType);
		json.put("deviceId", deviceId);
		json.put("resultCode", resultCode);
		json.put("manufacturerId", manufacturerId);
		json.put("manufacturerName", manufacturerName);
		json.put("model", model);
		json.put("location", location);
		json.put("deviceType", deviceType);

		System.out.println(LocalDateTime.now() + "    recvDeviceBindNotify  : " + messageMap.toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
