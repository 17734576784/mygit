/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.Calendar;
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
import com.nb.service.IChinaMobileCommandService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.SuntrontProtocolUtil;

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
	* <p>Title: instantReadDeviceResources</p> 
	* <p>Description: </p> 
	* @param commandInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#instantReadDeviceResources(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> instantReadDeviceResources(JSONObject commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";
		Map<String, String> params = JSONObject.toJavaObject(commandInfo, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader(commandInfo));

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	/** (非 Javadoc) 
	* <p>Title: instantWriteDeviceResources</p> 
	* <p>Description: </p> 
	* @param commandInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileCommandService#instantWriteDeviceResources(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> instantWriteDeviceResources(JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";

		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("data");
		
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(commandInfo), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
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
		
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/execute/offline";
		
		Map<String, String> param = new HashMap<>();
		param = JsonUtil.jsonString2SimpleObj(command, param.getClass());
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(param);
		if (deviceInfo == null) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
			return result;
		}
		
		ResultBean<String> result = new ResultBean<String>();
		Map<String, String> commandMap = commonMapper.getCommand(param);
		if (null == commandMap || commandMap.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError("命令类型不存在");
			return result;
		}
		
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("imei", deviceInfo.getImei());
		urlParams.put("obj_id", commandMap.get("serviceId"));
		urlParams.put("obj_inst_id", commandMap.get("method"));
		urlParams.put("res_id", commandMap.get("res_id"));
 		Calendar expiredTime = Calendar.getInstance();
		expiredTime.add(Calendar.DAY_OF_YEAR, commandExpiredTime);
		urlParams.put("expired_time", DateUtils.formatDateByFormat(expiredTime.getTime(), "yyyy-MM-dd'T'HH:mm:ss"));

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, urlParams);
		
		JSONObject argsJson = new JSONObject();
		argsJson.put("args", getCommandData(deviceInfo.getManufacturerId(), command));
		
		JSONObject appInfo = new JSONObject();
		appInfo.put("appId", deviceInfo.getAppId());
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(appInfo), argsJson.toJSONString());

		
		JSONObject responseJson = JSON.parseObject(response.getContent());
		if (!responseJson.containsKey("data")) {
			result.setStatus(Constant.ERROR);
			result.setError(responseJson.toJSONString());
			return result;
		}
		
		JSONObject data = responseJson.getJSONObject("data");
		String commandId = toStr(data.getString("uuid"));
		if (commandId.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError(responseJson.toJSONString());

		} else {
			insertNbCommand(command, commandId, Constant.ASYN_COMMAND);
		}

		result = new ResultBean<>(response.getContent());
		System.out.println(result.toString());
		return result;
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
	private void insertNbCommand(JSONObject command, String commandId,
			byte commandClass) throws Exception {
		NbCommand nbCommand = new NbCommand();
		nbCommand.setRtuId(command.getInteger("rtuId"));
		nbCommand.setMpId(command.getShort("mpId"));
		nbCommand.setCommandClass(commandClass);
		nbCommand.setCommandType(command.getByte("commandId"));

		String tableNameDate = DateUtils.curDate();
		nbCommand.setCommandContent(command.toString());
		nbCommand.setOperatorId(command.getInteger("operatorId"));
		nbCommand.setTableName(tableNameDate);
		nbCommand.setCommandId(commandId);
		nbCommandMapper.insertNbCommand(nbCommand);

		// 将命令对应数据表日期存入redis
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
		if (manufacturerId.equals(Constant.SUNTRONT_OBJID)) {
			JSONObject param = command.getJSONObject("param");
			commandData = SuntrontProtocolUtil.sendVavleCommand(param.getIntValue("operate"));
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
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/observe";

		
		Map<String, Object> params = JSONObject.toJavaObject(observeInfo, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(observeInfo), observeInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

}
