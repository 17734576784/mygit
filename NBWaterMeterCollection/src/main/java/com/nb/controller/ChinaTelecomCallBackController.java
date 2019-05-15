package com.nb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.mapper.NbCommandMapper;
import com.nb.model.NbCommand;
import com.nb.servicestrategy.ChinaTelecomServiceContext;
import com.nb.utils.CommandEnum;
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

	@Resource
	private CommonMapper commonMapper;
	
	@Resource
	private NbCommandMapper nbCommandMapper;
	
	@RequestMapping(value = "deviceAdded", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvAddDeviceNotify(@RequestBody Object addDeviceNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收addDevice" + addDeviceNotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		try {
			messageMap = JsonUtil.jsonString2SimpleObj(addDeviceNotifyMessage, messageMap.getClass());
			String deviceId = messageMap.get("deviceId");
			Object deviceInfo = messageMap.get("deviceInfo");

			Map<String, String> deviceInfoMap = new HashMap<String, String>();
			deviceInfoMap = JsonUtil.jsonString2SimpleObj(deviceInfo, deviceInfoMap.getClass());
			String nodeId = deviceInfoMap.get("nodeId");

			// 收到添加设备命令 根据imei更新设备的deviceId(用于批量注册设备)
			commonMapper.updateDeviceIdByImei(deviceId, nodeId);
		} catch (Exception e) {
			LoggerUtil.logger(LogName.CALLBACK).error("接收addDevice异常," + addDeviceNotifyMessage);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvInfoChangeNotify(@RequestBody Object updateDeviceInfoNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收deviceInfoChanged" + updateDeviceInfoNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "deviceDataChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDataChangeNotify(@RequestBody Object updateDeviceDataNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收updateDeviceData:" + updateDeviceDataNotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		try {
			messageMap = JsonUtil.jsonString2SimpleObj(updateDeviceDataNotifyMessage, messageMap.getClass());
			String deviceId = toStr(messageMap.get("deviceId"));
			Object service = messageMap.get("service");
			
			Map<String, String> serviceMap = new HashMap<String, String>();
			serviceMap = JsonUtil.jsonString2SimpleObj(service, serviceMap.getClass());
			chinaTelecomServiceContext.parseService(deviceId, serviceMap);
		} catch (Exception e) {
			LoggerUtil.logger(LogName.CALLBACK).error("接收updateDeviceData异常," + updateDeviceDataNotifyMessage);
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceDeleted", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeleteDeviceNotify(@RequestBody Object deletedDeviceNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收deletedDevice:" + deletedDeviceNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "messageConfirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvMessageConfirmNotify(@RequestBody Object messageConfirmNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收messageConfirm:" + messageConfirmNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "serviceInfoChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvServiceInfoChangedNotify(
			@RequestBody Object updateServiceInfoNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收serviceInfoChanged:" + updateServiceInfoNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "commandRsp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvCommandRspdNotify(@RequestBody Object commandRspDataNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收commandRsp:" + commandRspDataNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceEventNotify(@RequestBody Object deviceEventNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收deviceEvent:" + deviceEventNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "ruleEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvRuleEventNotify(@RequestBody Object ruleEventNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收ruleEvent:" + ruleEventNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "deviceDatasChanged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceDatasChangeDNotify(
			@RequestBody Object updateDeviceDatasNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("接收deviceDatasChanged:" + updateDeviceDatasNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/** 
	* @Title: reportCmdExecResult 
	* @Description: 命令回调 
	* @param @param reportCmdExecResultNotifyMessage
	* @param @return    设定文件 
	* @return ResponseEntity<HttpStatus>    返回类型 
	* @throws 
	* {result={resultCode=SENT, resultDetail=null}, deviceId=92d70872-6bdc-4dd2-9297-cc386f97222c, 
	* commandId=4613a3e9ce9241cc832dafcca193c87f}
	*/
	
	@RequestMapping(value = "reportCmdExecResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> reportCmdExecResult(@RequestBody Object reportCmdExecResultNotifyMessage){

		LoggerUtil.logger(LogName.CALLBACK).info("接收命令响应：" + reportCmdExecResultNotifyMessage);
		Map<String, String> messageMap = new HashMap<String, String>();
		try {
			messageMap = JsonUtil.jsonString2SimpleObj(reportCmdExecResultNotifyMessage, messageMap.getClass());
			String commandId = "";
			if (messageMap.containsKey(Constant.COMMANDID)) {
				commandId = toStr(messageMap.get("commandId"));
			} else if (messageMap.containsKey(Constant.TASKID)) {
				commandId = toStr(messageMap.get(Constant.TASKID));
			}
			
			Object result = messageMap.get("result");

			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(result, dataMap.getClass());
			String resultCode = toStr(dataMap.get("resultCode"));
			// Object resultDetail = dataMap.get("resultDetail");
			// 根据下行命令上报结果信息更新命令状态
			Byte resultValue = CommandEnum.getResultValue(resultCode);
			String tableNameDate = JedisUtils.get(Constant.COMMAND + commandId);
			if (tableNameDate != null && resultValue != null) {
				NbCommand nbCommand = new NbCommand();
				nbCommand.setTableName(tableNameDate);
				nbCommand.setCommandId(commandId);
				nbCommand.setExecuteResult(resultValue);
				nbCommandMapper.updateNbCommand(nbCommand);
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.CALLBACK).error("接收命令响应异常," + reportCmdExecResultNotifyMessage);
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "bindDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> recvDeviceBindNotify(@RequestBody Object deviceBindNotifyMessage) {

		LoggerUtil.logger(LogName.CALLBACK).info("bindDevice：" + deviceBindNotifyMessage);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
