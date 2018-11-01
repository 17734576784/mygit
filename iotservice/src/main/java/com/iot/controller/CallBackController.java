package com.iot.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.iot.commandstrategy.CommandContext;
import com.iot.servicestrategy.ServiceContext;
import com.iot.utils.Constant;
import com.iot.utils.JedisUtils;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;
import static com.iot.utils.ConverterUtils.*;

@RestController
public class CallBackController {
	@Resource
	private ServiceContext serviceContext;
	
	@Autowired
	private CommandContext commandContext;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvAddDeviceNotify(@RequestBody Object addDevice_NotifyMessage) {

		Log4jUtils.getInfo().info("接收addDevice" + addDevice_NotifyMessage);
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

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateDeviceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvInfoChangeNotify(@RequestBody Object updateDeviceInfo_NotifyMessage) {

		Log4jUtils.getInfo().info("接收deviceInfoChanged" + updateDeviceInfo_NotifyMessage);

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

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDataChangeNotify(@RequestBody Object updateDeviceData_NotifyMessage) {

		Log4jUtils.getInfo().info("接收updateDeviceData:" + updateDeviceData_NotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap = JsonUtil.jsonString2SimpleObj(updateDeviceData_NotifyMessage, messageMap.getClass());
		String deviceId = toStr(messageMap.get("deviceId"));
		Object service = messageMap.get("service");

		Map<String, String> serviceMap = new HashMap<String, String>();
		serviceMap = JsonUtil.jsonString2SimpleObj(service, serviceMap.getClass());
		String serviceId = toStr(serviceMap.get("serviceId"));
		serviceContext.parseService(serviceId, deviceId, serviceMap);

		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deletedDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeleteDeviceNotify(@RequestBody Object deletedDevice_NotifyMessage) {

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
	public ResponseEntity<HttpStatus> recvMessageConfirmNotify(@RequestBody Object messageConfirm_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(messageConfirm_NotifyMessage.toString());
		System.out.println("recvMessageConfirmNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "updateServiceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvServiceInfoChangedNotify(
			@RequestBody Object updateServiceInfo_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(updateServiceInfo_NotifyMessage.toString());
		System.out.println("recvServiceInfoChangedNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "commandRspData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvCommandRspdNotify(@RequestBody Object commandRspData_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(commandRspData_NotifyMessage.toString());
		System.out.println("recvCommandRspdNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "deviceEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceEventNotify(@RequestBody Object deviceEvent_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(deviceEvent_NotifyMessage.toString());
		System.out.println("recvDeviceEventNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "ruleEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvRuleEventNotify(@RequestBody Object ruleEvent_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(ruleEvent_NotifyMessage.toString());
		System.out.println("recvRuleEventNotify   " + resulr);
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "updateDeviceDatas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceDatasChangeDNotify(
			@RequestBody Object updateDeviceDatas_NotifyMessage) {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(updateDeviceDatas_NotifyMessage.toString());
		System.out.println("recvDeviceDatasChangeDNotify   " + resulr);
		System.out.println();
//		recvDeviceDatasChangeDNotify   "{notifyType=deviceDatasChanged, requestId=null, deviceId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, gatewayId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, services=[{serviceId=Alarm1, serviceType=Alarm1, data={broke=1, magnetic_disturb=1}, eventTime=20180903T085935Z}]}"

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "reportCmdExecResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> reportCmdExecResult(@RequestBody Object reportCmdExecResult_NotifyMessage){

		Log4jUtils.getInfo().info("接受命令响应：" + reportCmdExecResult_NotifyMessage);

		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap = JsonUtil.jsonString2SimpleObj(reportCmdExecResult_NotifyMessage, messageMap.getClass());
		String deviceId = toStr(messageMap.get("deviceId"));
		String commandId = toStr(messageMap.get("commandId"));
		Object result = messageMap.get("result");
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(result, dataMap.getClass());
		String resultCode = toStr(dataMap.get("resultCode"));
		Object resultDetail = dataMap.get("resultDetail");
		System.out.println(deviceId + "  " + commandId + "  resultCode : " + resultCode);
		if (resultCode.equals(Constant.COMMAND_SUCCESS)) {
			String serviceName = toStr(JedisUtils.get(commandId));
			Map<String, String> commandMap = new HashMap<String, String>();
			commandMap = JsonUtil.jsonString2SimpleObj(resultDetail, dataMap.getClass());
			commandContext.parseCommand(serviceName, deviceId, commandMap);

		} else if (resultCode.equals(Constant.COMMAND_FAILED) || resultCode.equals(Constant.COMMAND_TIMEOUT)) {
			JedisUtils.del(commandId);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceBind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceBindNotify(@RequestBody Object deviceBind_NotifyMessage) {

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
