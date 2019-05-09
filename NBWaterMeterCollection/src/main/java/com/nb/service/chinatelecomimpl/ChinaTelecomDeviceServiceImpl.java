/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinatelecomimpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JsonUtil;
import com.nb.exception.ResultBean;
import com.nb.httputil.ChinaTelecomIotHttpsUtil;
import com.nb.model.DeviceInfo;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.IChinaTelecomDeviceService;
import com.nb.utils.Constant;

/** 
* @ClassName: ChinaTelecomDeviceServiceImpl 
* @Description: 中国电信设备管理 实现类
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@Service
public class ChinaTelecomDeviceServiceImpl implements IChinaTelecomDeviceService {
	
	@Resource
	private CommonMapper commonMapper;
	
	
	/** (非 Javadoc) 
	* <p>Title: registerDevice</p> 
	* <p>Description: </p> 
	* @param deviceInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaTelecomDeviceService#registerDevice(com.alibaba.fastjson.JSONObject) 
	* {"rtuId":"1","mpId":"1"}"
	*/
	@Override
	
	public ResultBean<?> registerDevice(JSONObject deviceMsg) throws Exception {
		LoggerUtil.logger(LogName.INFO).info("接收设备注册请求:" + deviceMsg);
		
		Map<String, String> deviceMap = new HashMap<>();
		deviceMap = JsonUtil.jsonString2SimpleObj(deviceMsg, deviceMap.getClass());
		
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(deviceMap);
		if (deviceInfo == null) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
			return result;
		}
		
		String deviceId = deviceInfo.getDeviceId();
		if (deviceId != null && !deviceId.isEmpty()) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "该设备已被注册");
			return result;
		}
		String appId = deviceInfo.getAppId();

		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, appId, deviceInfo.getSecret());

		String urlReg = Constant.CHINA_TELECOM_REGISTER_DEVICE;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		
		JSONObject register = new JSONObject();
		register.put("verifyCode", deviceInfo.getImei());
		register.put("nodeId", deviceInfo.getImei());
		register.put("timeout", Constant.ZERO);

		JSONObject paramModifyDevice = new JSONObject();
		paramModifyDevice.put("manufacturerId", deviceInfo.getManufacturerId());
		paramModifyDevice.put("manufacturerName", deviceInfo.getManufacturerName());
		paramModifyDevice.put("deviceType", deviceInfo.getDeviceType());
		paramModifyDevice.put("model", deviceInfo.getModel());
		paramModifyDevice.put("protocolType", deviceInfo.getProtocolType());
		
		register.put("deviceInfo", paramModifyDevice);

		String jsonRequest = register.toJSONString();
		
		StreamClosedHttpResponse httpResponse = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap = JsonUtil.jsonString2SimpleObj(httpResponse.getContent(), responseMap.getClass());
		deviceId = ConverterUtils.toStr(responseMap.get("deviceId"));
		if (null != deviceId && !deviceId.isEmpty()) {
			ResultBean<JSONObject> result = new ResultBean<>();
			JSONObject rtnJson = new JSONObject();
			rtnJson.put("deviceId", deviceId);
			result.setData(rtnJson);
			return result;
		} else {
			ResultBean<String> result = new ResultBean<String>(Constant.ERROR, "请求错误");
			return result;
		}
	}
	
	/** (非 Javadoc) 
	* <p>Title: modifyDeviceInfo</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param registerInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaTelecomDeviceService#modifyDeviceInfo(java.lang.String, java.util.Map) 
	*/
	@Override
	public ResultBean<?> modifyDeviceInfo(String deviceId,DeviceInfo deviceInfo) throws Exception {
		
		LoggerUtil.logger(LogName.INFO).info("接收修改设备信息请求：" + deviceInfo);
 	
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String appId = deviceInfo.getAppId();

		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, appId, deviceInfo.getSecret());
	
 		String urlModifyDeviceInfo = Constant.CHINA_TELECOM_MODIFY_DEVICE_INFO + "/" + deviceId;
	
		JSONObject paramModifyDevice = new JSONObject();
		paramModifyDevice.put("deviceId", deviceId);
		paramModifyDevice.put("manufacturerId", deviceInfo.getManufacturerId());
		paramModifyDevice.put("manufacturerName", deviceInfo.getManufacturerName());
		paramModifyDevice.put("deviceType", deviceInfo.getDeviceType());
		paramModifyDevice.put("model", deviceInfo.getModel());
		paramModifyDevice.put("protocolType", deviceInfo.getProtocolType());
		
		String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDevice);
	
		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
	
		StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
				header, jsonRequest);
 
		JSONObject response = JSONObject.parseObject(responseModifyDeviceInfo.getContent());
 			
 		System.out.println(response);
 		
		ResultBean<JSONObject> result = new ResultBean<JSONObject>();
		JSONObject rtnJson = new JSONObject();
		rtnJson.put("deviceId", deviceId);
		result.setData(rtnJson);

		return result;
	}
	
	
	/** (非 Javadoc) 
	* <p>Title: deleteDevice</p> 
	* <p>Description: </p> 
	* @param deviceInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaTelecomDeviceService#deleteDevice(com.alibaba.fastjson.JSONObject) 
	* {"rtuId":"1","mpId":"1"}"
	*/
	@Override
	
	public ResultBean<?> deleteDevice(JSONObject deviceMsg) throws Exception {
		LoggerUtil.logger(LogName.INFO).info("接收设备删除请求:" + deviceMsg);
		
		
		Map<String, String> deviceMap = new HashMap<>();
		deviceMap = JsonUtil.jsonString2SimpleObj(deviceMsg, deviceMap.getClass());
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(deviceMap);
		if (deviceInfo == null) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
			return result;
		}
		
		String appId = deviceInfo.getAppId();
		String deviceId = deviceInfo.getDeviceId();

		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, appId, deviceInfo.getSecret());

		String deleteUrl = Constant.CHINA_TELECOM_DELETE_DEVICE + "/" + deviceId;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

		StreamClosedHttpResponse response = httpsUtil.doDeleteGetStatusLine(deleteUrl, header);
		ResultBean<String> result = new ResultBean<>();

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == Constant.STATUS_404) {
			result = new ResultBean<>(Constant.ERROR, "设备不存在");
		}

		return result;
	}

	
}
