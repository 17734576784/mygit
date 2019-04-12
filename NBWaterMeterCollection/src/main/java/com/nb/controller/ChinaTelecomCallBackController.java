package com.nb.controller;

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

import com.nb.commandstrategy.chinatelecom.ChinaTelecomCommandContext;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.servicestrategy.ChinaTelecomServiceContext;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import static com.nb.utils.ConverterUtils.*;

/** 
* @ClassName: ChinaTelecomCallBackController 
* @Description: 中国电信平台回调管理 
* @author dbr
* @date 2019年1月9日 上午10:04:13 
*  
*/
@RestController
@RequestMapping("/chinatelecom")
public class ChinaTelecomCallBackController {
	@Resource
	private ChinaTelecomServiceContext chinaTelecomServiceContext;
	
	@Autowired
	private ChinaTelecomCommandContext chinaTelecomCommandContext;
	
	@RequestMapping(value = "deviceAdded", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvAddDeviceNotify(@RequestBody Object addDevice_NotifyMessage) {

		System.out.println("接收addDevice" + addDevice_NotifyMessage);
		LoggerUtil.Logger(LogName.CALLBACK).info("接收addDevice" + addDevice_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvInfoChangeNotify(@RequestBody Object updateDeviceInfo_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收deviceInfoChanged" + updateDeviceInfo_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deviceDataChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDataChangeNotify(@RequestBody Object updateDeviceData_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收updateDeviceData:" + updateDeviceData_NotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		try {
			messageMap = JsonUtil.jsonString2SimpleObj(updateDeviceData_NotifyMessage, messageMap.getClass());
			String deviceId = toStr(messageMap.get("deviceId"));
			Object service = messageMap.get("service");
			System.out.println(service);
			
			Map<String, String> serviceMap = new HashMap<String, String>();
			serviceMap = JsonUtil.jsonString2SimpleObj(service, serviceMap.getClass());
			String serviceId = toStr(serviceMap.get("serviceId"));
			chinaTelecomServiceContext.parseService(serviceId, deviceId, serviceMap);
		} catch (Exception e) {
			LoggerUtil.Logger(LogName.CALLBACK).error("接收updateDeviceData异常," + updateDeviceData_NotifyMessage);
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceDeleted", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeleteDeviceNotify(@RequestBody Object deletedDevice_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收deletedDevice:" + deletedDevice_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "commandConfirmData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvMessageConfirmNotify(@RequestBody Object messageConfirm_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收commandConfirmData:" + messageConfirm_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "updateServiceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvServiceInfoChangedNotify(
			@RequestBody Object updateServiceInfo_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收updateServiceInfo:" + updateServiceInfo_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "commandRspData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvCommandRspdNotify(@RequestBody Object commandRspData_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收commandRspData:" + commandRspData_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceEventNotify(@RequestBody Object deviceEvent_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收deviceEvent:" + deviceEvent_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "ruleEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvRuleEventNotify(@RequestBody Object ruleEvent_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收ruleEvent:" + ruleEvent_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceDatasChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceDatasChangeDNotify(
			@RequestBody Object updateDeviceDatas_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("接收updateDeviceDatas:" + updateDeviceDatas_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "reportCmdExecResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> reportCmdExecResult(@RequestBody Object reportCmdExecResult_NotifyMessage){

		LoggerUtil.Logger(LogName.CALLBACK).info("接收命令响应：" + reportCmdExecResult_NotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		try {
			messageMap = JsonUtil.jsonString2SimpleObj(reportCmdExecResult_NotifyMessage, messageMap.getClass());
			String deviceId = toStr(messageMap.get("deviceId"));
			String commandId = toStr(messageMap.get("commandId"));
			Object result = messageMap.get("result");
			
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(result, dataMap.getClass());
			String resultCode = toStr(dataMap.get("resultCode"));
			Object resultDetail = dataMap.get("resultDetail");
			/**
			 * 根据下行命令上报结果信息更新命令状态
			 */
//			System.out.println(LocalDateTime.now()+"  "+deviceId + "  " + commandId + "  resultCode : " + resultCode);
			if (resultCode.equals(Constant.COMMAND_SUCCESS)) {
				String serviceName = toStr(JedisUtils.get(Constant.COMMAND + commandId));
				if (!serviceName.isEmpty()) {
					Map<String, String> commandMap = new HashMap<String, String>();
					commandMap = JsonUtil.jsonString2SimpleObj(resultDetail, dataMap.getClass());
					chinaTelecomCommandContext.parseCommand(serviceName, deviceId, commandMap);
					delCommand(commandId);
				}
			} else if (resultCode.equals(Constant.COMMAND_FAILED) || resultCode.equals(Constant.COMMAND_TIMEOUT)) {
				delCommand(commandId);
			}
		} catch (Exception e) {
			LoggerUtil.Logger(LogName.CALLBACK).error("接收命令响应异常," + reportCmdExecResult_NotifyMessage);
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void delCommand(String commandId) {
		JedisUtils.del(Constant.COMMAND + commandId);
	}

	@RequestMapping(value = "deviceBind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceBindNotify(@RequestBody Object deviceBind_NotifyMessage) {

		LoggerUtil.Logger(LogName.CALLBACK).info("deviceBind：" + deviceBind_NotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
