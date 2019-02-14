/**   
* @Title: CommandConfig.java 
* @Package com.nb.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月14日 上午10:04:00 
* @version V1.0   
*/
package com.nb.configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import static com.nb.utils.ConverterUtils.*;

/**
 * @ClassName: CommandConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年2月14日 上午10:04:00
 * 
 */
@Component
@DependsOn("jedisUtils")
public class CommandConfig {

	@PostConstruct
	public void init() {
		Map<String, Map<String, Object>> command = new LinkedHashMap<String, Map<String, Object>>();

		/** 命令类型 抄收图片 */
		Map<String, Object> photoServiceMap = new HashMap<String, Object>();
		photoServiceMap.put("serviceId", "PhotoService");
		photoServiceMap.put("method", "sendphotoonce");
		int commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_IMAGE;
		command.put(toStr(commandKey), photoServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_IMAGE;
		command.put(toStr(commandKey), photoServiceMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_IMAGE;
		command.put(toStr(commandKey), photoServiceMap);

		/** 命令类型 抄收数据命令 */
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("obj_id", 1);
		dataMap.put("obj_inst_id", 2);
		dataMap.put("res_id", 3);
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), dataMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), dataMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), dataMap);

		/** 命令类型 开阀命令 */
		Map<String, Object> openMap = new HashMap<String, Object>();
		openMap.put("obj_id", 1);
		openMap.put("obj_inst_id", 2);
		openMap.put("res_id", 3);
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_OPEN;
		command.put(toStr(commandKey), openMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_OPEN;
		command.put(toStr(commandKey), openMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_OPEN;
		command.put(toStr(commandKey), openMap);

		/** 命令类型 关阀命令 */
		Map<String, Object> closeMap = new HashMap<String, Object>();
		closeMap.put("obj_id", 1);
		closeMap.put("obj_inst_id", 2);
		closeMap.put("res_id", 3);
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_CLOSE;
		command.put(toStr(commandKey), closeMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_CLOSE;
		command.put(toStr(commandKey), closeMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_CLOSE;
		command.put(toStr(commandKey), closeMap);

		/** 命令类型 设置抄表时间命令 */
		Map<String, Object> timeServiceMap = new HashMap<String, Object>();
		timeServiceMap.put("serviceId", "TimeService");
		timeServiceMap.put("method", "commandUpTimeService");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), timeServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), timeServiceMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_DATA;
		command.put(toStr(commandKey), timeServiceMap);

		/** 命令类型 设置告警开关命令 */
		Map<String, Object> alarmServiceMap = new HashMap<String, Object>();
		alarmServiceMap.put("serviceId", "AlarmService");
		alarmServiceMap.put("method", "openalarm");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_ALARM;
		command.put(toStr(commandKey), alarmServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_ALARM;
		command.put(toStr(commandKey), alarmServiceMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_ALARM;
		command.put(toStr(commandKey), alarmServiceMap);

		/** 命令类型 调整摄像头命令 */
		Map<String, Object> cameraServiceMap = new HashMap<String, Object>();
		cameraServiceMap.put("serviceId", "CameraService");
		cameraServiceMap.put("method", "adjustcamera");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_CAMERA;
		command.put(toStr(commandKey), cameraServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_CAMERA;
		command.put(toStr(commandKey), cameraServiceMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_CAMERA;
		command.put(toStr(commandKey), cameraServiceMap);

		/** 命令类型 完成激活命令 */
		Map<String, Object> activateServiceMap = new HashMap<String, Object>();
		activateServiceMap.put("serviceId", "ActivateService");
		activateServiceMap.put("method", "activatecmd ");
		commandKey = Constant.CHINA_TELECOM * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
		command.put(toStr(commandKey), activateServiceMap);
		commandKey = Constant.CHINA_UNICOM * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
		command.put(toStr(commandKey), activateServiceMap);
		commandKey = Constant.CHINA_MOBILE * 1000 + Constant.COMMAND_TYPE_ACTIVATE;
		command.put(toStr(commandKey), activateServiceMap);

		JedisUtils.set(Constant.COMMAND_TYPE_REIDS, command);
		System.out.println("命令加载完毕");
	}

}
