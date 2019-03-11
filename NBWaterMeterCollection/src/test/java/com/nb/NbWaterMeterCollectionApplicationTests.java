package com.nb;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbWaterMeterCollectionApplication.class)
@PropertySource({"classpath:config.properties" })
public class NbWaterMeterCollectionApplicationTests {

	@Test
	public void contextLoads() {
		Map<String, Map<String, String>> command = new HashMap<String, Map<String, String>>();

		/** 命令类型 抄收图片 */
		Map<String, String> photoServiceMap = new HashMap<String, String>();
		photoServiceMap.put("serviceId", "PhotoService");
		photoServiceMap.put("method", "sendphotoonce");
		int commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_IMAGE;
		command.put(toStr(commandKey), photoServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_IMAGE;
		command.put(toStr(commandKey), photoServiceMap);
//		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_IMAGE;
//		command.put(toStr(commandKey), photoServiceMap);

		/** 命令类型 抄收数据命令 */
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("obj_id", "3200");
		dataMap.put("obj_inst_id", "0");
		dataMap.put("res_id", "5750");
//		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_DATA;
//		command.put(toStr(commandKey), dataMap);
//		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_DATA;
//		command.put(toStr(commandKey), dataMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), dataMap);

		/** 命令类型 开阀命令*/
		Map<String, String> openMap = new HashMap<String, String>();
		openMap.put("obj_id", "3342");
		openMap.put("obj_inst_id", "0");
		openMap.put("res_id", "5850");
//		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_OPEN;
//		command.put(toStr(commandKey), openMap);
//		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_OPEN;
//		command.put(toStr(commandKey), openMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_OPEN;
		command.put(toStr(commandKey), openMap);

		/** 命令类型 关阀命令 */
		Map<String, String> closeMap = new HashMap<String, String>();
		closeMap.put("obj_id", "3342");
		closeMap.put("obj_inst_id", "0");
		closeMap.put("res_id", "5850");
//		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_CLOSE;
//		command.put(toStr(commandKey), closeMap);
//		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_CLOSE;
//		command.put(toStr(commandKey), closeMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_CLOSE;
		command.put(toStr(commandKey), closeMap);

		/** 命令类型 设置抄表时间命令 */
		Map<String, String> timeServiceMap = new HashMap<String, String>();
		timeServiceMap.put("serviceId", "TimeService");
		timeServiceMap.put("method", "commandUpTimeService");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), timeServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), timeServiceMap);
//		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_DATA;
//		command.put(toStr(commandKey), timeServiceMap);

		/** 命令类型 设置告警开关命令 */
		Map<String, String> alarmServiceMap = new HashMap<String, String>();
		alarmServiceMap.put("serviceId", "AlarmService");
		alarmServiceMap.put("method", "openalarm");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_ALARM;
		command.put(toStr(commandKey), alarmServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_ALARM;
		command.put(toStr(commandKey), alarmServiceMap);
//		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_ALARM;
//		command.put(toStr(commandKey), alarmServiceMap);

		/** 命令类型 调整摄像头命令 */
		Map<String, String> cameraServiceMap = new HashMap<String, String>();
		cameraServiceMap.put("serviceId", "CameraService");
		cameraServiceMap.put("method", "adjustcamera");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_CAMERA;
		command.put(toStr(commandKey), cameraServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_CAMERA;
		command.put(toStr(commandKey), cameraServiceMap);
//		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_CAMERA;
//		command.put(toStr(commandKey), cameraServiceMap);

		/** 命令类型 完成激活命令 */
		Map<String, String> activateServiceMap = new HashMap<String, String>();
		activateServiceMap.put("serviceId", "ActivateService");
		activateServiceMap.put("method", "activatecmd");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
		command.put(toStr(commandKey), activateServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
		command.put(toStr(commandKey), activateServiceMap);
//		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
//		command.put(toStr(commandKey), activateServiceMap);

		JedisUtils.setByByte(Constant.COMMAND_TYPE_REIDS, command);
		System.out.println("命令加载完毕");
		
		Map<String,Map<String,String>> messageMap = (Map<String, Map<String, String>>) JedisUtils.getByByte(Constant.COMMAND_TYPE_REIDS);
		System.out.println(messageMap);

	}

}
