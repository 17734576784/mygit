package com.iot.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.Constant;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;
import static com.iot.utils.ConverterUtils.*;

@RestController
public class CallBackController {

	@RequestMapping(value = "/test")
	public String test(String name) {
		System.out.println("Helo");
		return "Hello" + name;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvAddDeviceNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		/**
		 * "{notifyType=deviceAdded, deviceId=55e61296-da94-4a08-bbad-821de1a9e3f1,
		 * gatewayId=55e61296-da94-4a08-bbad-821de1a9e3f1, nodeType=GATEWAY,
		 * deviceInfo={nodeId=000001111122222, name=null, description=null,
		 * manufacturerId=null, manufacturerName=null, mac=null, location=null,
		 * deviceType=null, model=null, swVersion=null, fwVersion=null, hwVersion=null,
		 * protocolType=null, bridgeId=null, status=OFFLINE, statusDetail=NOT_ACTIVE,
		 * mute=null, supportedSecurity=null, isSecurity=null, signalStrength=null,
		 * sigVersion=null, serialNumber=null, batteryLevel=null}}"
		 */
		Log4jUtils.getInfo().info("接收addDevice" + addDevice_NotifyMessage);
		try {
			Map<String, String> data = new HashMap<String, String>();
			data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
			String notifyType = toStr(data.get("notifyType"));
			String deviceId = toStr(data.get("deviceId"));
			String gatewayId = toStr(data.get("gatewayId"));
			Object deviceInfo = data.get("deviceInfo");

			Map<String, String> data2 = new HashMap<String, String>();
			data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
			String manufacturerId = toStr(data2.get("manufacturerId"));
			String manufacturerName =toStr(data2.get("manufacturerName"));
			String model = toStr(data2.get("model"));
			String deviceType = toStr(data2.get("deviceType"));

			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("manufacturerId", manufacturerId);
			json.put("manufacturerName", manufacturerName);
			json.put("model", model);
			json.put("deviceType", deviceType);

			System.out.println(LocalDateTime.now() +"  recvAddDeviceNotify : " + json.toJSONString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("addDevice 处理消息异常");
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvInfoChangeNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		// DeviceInfoChanged "{notifyType=deviceInfoChanged,
		// deviceId=edca1cba-445f-4659-a952-5a411a5999ec,
		// gatewayId=edca1cba-445f-4659-a952-5a411a5999ec,
		// deviceInfo={nodeId=000001111122222, name=test0000, description=null,
		// manufacturerId=XLXX, manufacturerName=XLXX, mac=null, location=Shenzhen,
		// deviceType=GasMeter, model=XL0001, swVersion=null, fwVersion=null,
		// hwVersion=null, protocolType=CoAP, bridgeId=null, status=OFFLINE,
		// statusDetail=NOT_ACTIVE, mute=FALSE, supportedSecurity=null, isSecurity=null,
		// signalStrength=null, sigVersion=null, serialNumber=null, batteryLevel=null}}"
		Log4jUtils.getInfo().info("接收deviceInfoChanged" + addDevice_NotifyMessage);

		try {
			Map<String, String> data = new HashMap<String, String>();
			data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
			String notifyType = toStr(data.get("notifyType"));
			String deviceId = toStr(data.get("deviceId"));
			String gatewayId = toStr(data.get("gatewayId"));
			Object deviceInfo = data.get("deviceInfo");

			Map<String, String> data2 = new HashMap<String, String>();
			data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
			String manufacturerId = toStr(data2.get("manufacturerId"));
			String manufacturerName = toStr(data2.get("manufacturerName"));
			String model = toStr(data2.get("model"));
			String deviceType = toStr(data2.get("deviceType"));

			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("manufacturerId", manufacturerId);
			json.put("manufacturerName", manufacturerName);
			json.put("model", model);
			json.put("deviceType", deviceType);

			System.out.println(LocalDateTime.now() + "  DeviceInfoChanged   " + json.toJSONString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("deviceInfoChanged处理消息异常");
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceDataChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDataChangeNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

////	recvDataChangeNotify   "{notifyType=deviceDataChanged, 
//		deviceId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, 
//		gatewayId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, 
//		requestId=null, 
//		service={serviceId=Alarm1, serviceType=Alarm1,
//		data={broke=1, magnetic_disturb=1}, eventTime=20180903T085935Z}}"
		Log4jUtils.getInfo().info("接收deviceDataChanged:" + addDevice_NotifyMessage);
		try {
			Map<String, String> data = new HashMap<String, String>();
			data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
			String notifyType = toStr(data.get("notifyType"));
			String deviceId = toStr(data.get("deviceId"));
			String gatewayId = toStr(data.get("gatewayId"));
			Object service = data.get("service");

			Map<String, String> data2 = new HashMap<String, String>();
			data2 = JsonUtil.jsonString2SimpleObj(service, data2.getClass());
			String serviceId = toStr(data2.get("serviceId"));
			String serviceType = toStr(data2.get("serviceType"));
			String eventTime = toStr(data2.get("eventTime"));
			Object servicedata = data2.get("data");
			
			Map<String, String> data3 = new HashMap<String, String>();
			data3 = JsonUtil.jsonString2SimpleObj(servicedata, data3.getClass());
			String broke="",magnetic_disturb="",photo="";
			if ("PhotoData".equals(serviceId)) {
				photo = toStr(data3.get("photo"));
				
			}else if ("Alarm1".equals(serviceId)) {
				broke =toStr(data3.get("broke"));
				magnetic_disturb = toStr(data3.get("magnetic_disturb"));
			}
			
			JSONObject json = new JSONObject();
			json.put("notifyType", notifyType);
			json.put("deviceId", deviceId);
			json.put("gatewayId", gatewayId);
			json.put("serviceId", serviceId);
			json.put("serviceType", serviceType);
			json.put("eventTime", eventTime);
			json.put("broke", broke);
			json.put("magnetic_disturb", magnetic_disturb);
			json.put("photo", photo);
			
	 		System.out.println(LocalDateTime.now() + "    recvDataChangeNotify  : " + json.toJSONString());
			System.out.println();
		} catch (Exception e) {
			Log4jUtils.getError().error("deviceDataChanged处理消息异常");

		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * 16进制转换成为string类型字符串
	 * @param s
	 * @return
	 */
	public static String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceDeleted", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeleteDeviceNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {
		// deviceDeleted "{notifyType=deviceDeleted,
		// deviceId=4182ce81-5534-4fd9-9136-f73da674ba74,
		// gatewayId=4182ce81-5534-4fd9-9136-f73da674ba74}"
		
		Map<String, String> data = new HashMap<String, String>();
		data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
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

	@RequestMapping(value = "messageConfirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvMessageConfirmNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvMessageConfirmNotify   " + resulr);
		System.out.println();
//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "serviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvServiceInfoChangedNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvServiceInfoChangedNotify   " + resulr);
		System.out.println();

//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "commandRSP", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvCommandRspdNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvCommandRspdNotify   " + resulr);
		System.out.println();

//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "deviceEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceEventNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvDeviceEventNotify   " + resulr);
		System.out.println();

//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "ruleEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvRuleEventNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvRuleEventNotify   " + resulr);
		System.out.println();

//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "deviceDatasChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceDatasChangeDNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvDeviceDatasChangeDNotify   " + resulr);
		System.out.println();
//		recvDeviceDatasChangeDNotify   "{notifyType=deviceDatasChanged, requestId=null, deviceId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, gatewayId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, services=[{serviceId=Alarm1, serviceType=Alarm1, data={broke=1, magnetic_disturb=1}, eventTime=20180903T085935Z}]}"

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvReportCmdExeCResultDNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {

		System.out.println(LocalDateTime.now());
		String resulr = JsonUtil.jsonObj2Sting(addDevice_NotifyMessage.toString());
		System.out.println("recvDeviceDatasChangeDNotify   " + resulr);
		System.out.println();

//		String notifyType = data.get("notifyType");
//		String deviceId = data.get("deviceId");
//		String gatewayId = data.get("gatewayId");
//		String deviceInfo = data.get("deviceInfo");
//
//		Map<String, String> data2 = new HashMap<String, String>();
//		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
//		String manufacturerId = data2.get("manufacturerId");
//		String manufacturerName = data2.get("manufacturerName");
//		String model = data2.get("model");
//		String deviceType = data2.get("deviceType");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceBind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceBindNotify(@RequestBody Object addDevice_NotifyMessage)
			throws IOException {
//		recvDeviceBindNotify   "{notifyType=bindDevice,
//		deviceId=118728eb-aae8-4f55-9cdb-6c8ec6e0c489, 
//		resultCode=succeeded, 
//		deviceInfo={nodeId=000001111122222, name=test0000, description=null, 
//		manufacturerId=XLXX, manufacturerName=XLXX, mac=null, location=Shenzhen, 
//		deviceType=GasMeter, model=XL0001, swVersion=null, fwVersion=null, hwVersion=null,
//		protocolType=CoAP, bridgeId=null, status=ONLINE, statusDetail=NONE, mute=FALSE, 
//		supportedSecurity=null, isSecurity=null, signalStrength=null, sigVersion=null, 
//		serialNumber=null, batteryLevel=null}}"
		
		Map<String, String> data = new HashMap<String, String>();
		data = JsonUtil.jsonString2SimpleObj(addDevice_NotifyMessage, data.getClass());
		String notifyType = data.get("notifyType");
		String deviceId = data.get("deviceId");
		String resultCode = data.get("resultCode");
		Object deviceInfo = data.get("deviceInfo");

		Map<String, String> data2 = new HashMap<String, String>();
		data2 = JsonUtil.jsonString2SimpleObj(deviceInfo, data2.getClass());
		String manufacturerId = data2.get("manufacturerId");
		String manufacturerName = data2.get("manufacturerName");
		String model = data2.get("model");
		String location = data2.get("location");
		String deviceType = data2.get("deviceType");

		JSONObject json = new JSONObject();
		json.put("notifyType", notifyType);
		json.put("deviceId", deviceId);
		json.put("resultCode", resultCode);
		json.put("manufacturerId", manufacturerId);
		json.put("manufacturerName", manufacturerName);
		json.put("model", model);
		json.put("location", location);
		json.put("deviceType", deviceType);
		
 		System.out.println(LocalDateTime.now() + "    recvDeviceBindNotify  : " + json.toJSONString());
		System.out.println();

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
