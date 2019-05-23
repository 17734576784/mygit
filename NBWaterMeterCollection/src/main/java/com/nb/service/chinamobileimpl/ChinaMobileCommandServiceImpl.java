/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.httputil.HttpsClientUtil;
import com.nb.mapper.CommonMapper;
import com.nb.mapper.NbCommandMapper;
import com.nb.model.DeviceInfo;
import com.nb.model.NbCommand;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.protocolutil.SuntrontProtocolUtil;
import com.nb.service.IChinaMobileCommandService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/**
 * @ClassName: ChinaMobileDeviceController
 * @Description: 中国移动OneNET命令管理
 * @author dbr
 * @date 2019年1月3日 上午8:44:58
 * 
 */
@Service
public class ChinaMobileCommandServiceImpl implements IChinaMobileCommandService {
	
	/** 命令缓存时间（天） */
	@Value("${commandExpiredTime}")
	private int commandExpiredTime;
	
	@Resource
	private CommonMapper commonMapper;
	
	@Resource
	private NbCommandMapper nbCommandMapper;

	/** (非 Javadoc) 
	* <p>Title: offlineReadDeviceResources</p> 
	* <p>Description: </p> 
	* @param commandInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#instantReadDeviceResources(com.alibaba.fastjson.JSONObject)
	*/
	@Override
	public ResultBean<?> offlineReadDeviceResources(JSONObject commandInfo) throws Exception {

		Map<String, String> deviceMap = new HashMap<>();
		deviceMap = JsonUtil.jsonString2SimpleObj(commandInfo, deviceMap.getClass());
		/** 获取设备信息 */
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(deviceMap);
		if (null == deviceInfo) {
			return new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
		}
		/** 获取命令信息 */
		Map<String, String> commandMap = commonMapper.getCommand(deviceMap);
		if (null == commandMap || commandMap.isEmpty()) {
			return new ResultBean<JSONObject>(Constant.ERROR, "命令类型不存在");
		}
		/** url参数 */
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("imei", deviceInfo.getImei());
		urlParams.put("obj_id", commandMap.get("serviceId"));
		Date expiredTime = DateUtils.addDate(new Date(), commandExpiredTime);
		urlParams.put("expired_time", DateUtils.formatDateByFormat(expiredTime, "yyyy-MM-dd'T'HH:mm:ss"));

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline";
		url = HttpsClientUtil.setcompleteUrl(url, urlParams);

		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader(deviceInfo.getAppId()));
		return commandResult(response, commandInfo);
	}

	/** (非 Javadoc) 
	* <p>Title: offlineWriteDeviceResources</p> 
	* <p>Description: </p> 
	* @param commandInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#instantWriteDeviceResources(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> offlineWriteDeviceResources(JSONObject commandInfo) throws Exception {

		Map<String, String> deviceMap = new HashMap<>();
		deviceMap = JsonUtil.jsonString2SimpleObj(commandInfo, deviceMap.getClass());
		/** 获取设备信息 */
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(deviceMap);
		if (deviceInfo == null) {
			return new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
		}
		
		/** 获取命令信息 */
		Map<String, String> commandMap = commonMapper.getCommand(deviceMap);
		if (null == commandMap || commandMap.isEmpty()) {
			return new ResultBean<JSONObject>(Constant.ERROR, "命令类型不存在");
		}
		/** url参数 */
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("imei", deviceInfo.getImei());
		urlParams.put("obj_id", commandMap.get("serviceId"));
		urlParams.put("obj_inst_id", commandMap.get("method"));
		urlParams.put("mode", Constant.ONE);

		Date expiredTime = DateUtils.addDate(new Date(), commandExpiredTime);
		urlParams.put("expired_time", DateUtils.formatDateByFormat(expiredTime, "yyyy-MM-dd'T'HH:mm:ss"));

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline";
		url = HttpsClientUtil.setcompleteUrl(url, urlParams);
		
		JSONObject param = new JSONObject();
		param.put("data", commandInfo.getJSONArray("data").toJSONString());
		
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(deviceInfo.getAppId()), param.toJSONString());

		return commandResult(response, commandInfo);
	}

	/** 
	* @Title: commandResult 
	* @Description: 命令返回结果处理
	* @param @param response
	* @param @param command
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	private ResultBean<?> commandResult(StreamClosedHttpResponse response, JSONObject command) throws Exception {
		ResultBean<?> result = new ResultBean<>();
		JSONObject responseJson = JSON.parseObject(response.getContent());
		if (!responseJson.containsKey("data")) {
			result.setStatus(Constant.ERROR);
			result.setError(responseJson.toJSONString());
			return result;
		}
		String commandId = command.getString("commandId");
		if (null == commandId || commandId.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError(responseJson.toJSONString());
		} else {
			insertNbCommand(command, commandId, Constant.ASYN_COMMAND);
		}

		return new ResultBean<>(response.getContent());
	}
	
	/** (非 Javadoc) 
	* <p>Title: asynCommand</p> 
	* <p>Description: </p> 
	* @param commandInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#asynCommand(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> asynCommand(JSONObject command) throws Exception {
		Map<String, String> param = new HashMap<>();
		param = JsonUtil.jsonString2SimpleObj(command, param.getClass());
		/** 获取设备信息 */
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(param);
		if (deviceInfo == null) {
			return new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
		}
		
		/** 获取设备信息 */
		Map<String, String> commandMap = commonMapper.getCommand(param);
		if (null == commandMap || commandMap.isEmpty()) {
			return new ResultBean<JSONObject>(Constant.ERROR, "命令类型不存在");
		}
		/** url参数 */
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("imei", deviceInfo.getImei());
		urlParams.put("obj_id", commandMap.get("serviceId"));
		urlParams.put("obj_inst_id", commandMap.get("method"));
		urlParams.put("res_id", commandMap.get("res_id"));
		Date expiredTime = DateUtils.addDate(new Date(), commandExpiredTime);
		urlParams.put("expired_time", DateUtils.formatDateByFormat(expiredTime, "yyyy-MM-dd'T'HH:mm:ss"));

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/execute/offline";
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, urlParams);
		
		JSONObject argsJson = new JSONObject();
		/** 将传过来的命令参数，根据规约转成16进制字符串 */
		String commandContext = getCommandData(deviceInfo.getManufacturerId(), command);
		String commandId = commandContext.substring(commandContext.length()-Constant.EIGHT, commandContext.length());
		argsJson.put("args", commandContext);

		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(deviceInfo.getAppId()), argsJson.toJSONString());
		command.put("commandId", commandId);
		return commandResult(response, command);
	}
	
	/** 
	* @Title: insertNbCommand 
	* @Description: 插入nb命令 
	* @param @param param
	* @param @param paramCommand
	* @param @param commandId
	* @param @param commandClass
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertNbCommand(JSONObject command, String commandId, byte commandClass) throws Exception {
		NbCommand nbCommand = new NbCommand();
		nbCommand.setRtuId(command.getInteger("rtuId"));
		nbCommand.setMpId(command.getShort("mpId"));
		nbCommand.setCommandClass(commandClass);
		nbCommand.setCommandId(command.getString("commandId"));
		nbCommand.setCommandType((byte)Constant.ZERO);
		
		String tableNameDate = DateUtils.curDate().substring(0, 6);
		nbCommand.setCommandContent(command.toString());
		nbCommand.setOperatorId(command.getInteger("operatorId"));
		nbCommand.setTableName(tableNameDate);
		nbCommand.setCommandId(commandId);
		nbCommandMapper.insertNbCommand(nbCommand);

		/** 将命令对应数据表日期存入redis */ 
		JedisUtils.set(Constant.COMMAND + commandId, tableNameDate, Constant.COMMAND_TIME_OUT);
	}

	/** 
	* @Title: getCommandData 
	* @Description: 根据nb_device_model中的manufacturer_id判断是厂家，然后调用对应的解析
	* @param @param manufacturerId
	* @param @param command
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getCommandData(String manufacturerId, JSONObject command) {
		String commandData = "";
		/** 新天科技移动规约 */
		if (manufacturerId.equals(Constant.SUNTRONT_OBJID)) {
			JSONObject param = command.getJSONObject("param");
			String meterAddr = command.getString("meterAddr");
			commandData = SuntrontProtocolUtil.sendVavleCommand(param.getIntValue("operate"), meterAddr);
		}
		return commandData;
	}

	/** (非 Javadoc) 
	* <p>Title: observe</p> 
	* <p>Description: </p> 
	* @param observeInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#observe(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> observe(JSONObject observeInfo) throws Exception {
		ResultBean<?> result = null;
//		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/observe";
//		Map<String, Object> params = JSONObject.toJavaObject(observeInfo, Map.class);
//
//		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
//		url = HttpsClientUtil.setcompleteUrl(url, params);
//		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
//				CommFunc.getChinaMobileHeader(observeInfo), observeInfo.toJSONString());
//
//		result = new ResultBean<>(response.getContent());

		return result;
	}

}
