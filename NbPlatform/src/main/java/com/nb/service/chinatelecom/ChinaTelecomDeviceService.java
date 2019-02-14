/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinatelecom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JsonUtil;
import com.nb.exception.ResultBean;
import com.nb.http.ChinaTelecomIotHttpsUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.Constant;

/** 
* @ClassName: ChinaMobileDeviceController 
* @Description: 中国电信设备管理 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@Service
public class ChinaTelecomDeviceService {
	
	/** 
	* @Title: registerDevice 
	* @Description: 注册设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public ResultBean<?> registerDevice(JSONObject deviceInfo) throws Exception {
		LoggerUtils.Logger(LogName.INFO).info("接收设备注册请求:" + deviceInfo);
		
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, deviceInfo);

		String appId = deviceInfo.getString("appId");
		String urlReg = Constant.CHINA_TELECOM_REGISTER_DEVICE;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		
		JSONObject registerInfo = new JSONObject();
		registerInfo.put("verifyCode", deviceInfo.getString("imei"));
		registerInfo.put("nodeId", deviceInfo.getString("imei"));
		registerInfo.put("timeout", Constant.ZERO);

		String jsonRequest = registerInfo.toJSONString();
		StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap = JsonUtil.jsonString2SimpleObj(responseReg.getContent(), responseMap.getClass());
		String deviceId = ConverterUtils.toStr(responseMap.get("deviceId"));
		if (null != deviceId && !deviceId.isEmpty()) {
			return modifyDeviceInfo(deviceId, deviceInfo);
		}else {
			ResultBean<String> result = new ResultBean<String>(Constant.ERROR, "请求错误");
			return result;
		}
	}
	
	/** 
	* @Title: modifyDeviceInfo 
	* @Description: 修改设备信息 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	public ResultBean<?> modifyDeviceInfo(String deviceId,JSONObject deviceInfo) throws Exception {
		
		LoggerUtils.Logger(LogName.INFO).info("接收修改设备信息请求：" + deviceInfo);
 	
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, deviceInfo);
	
		String appId = deviceInfo.getString("appId");
 		String urlModifyDeviceInfo = Constant.CHINA_TELECOM_MODIFY_DEVICE_INFO + "/" + deviceId;
	
		JSONObject paramModifyDevice = new JSONObject();
		paramModifyDevice.put("deviceId", deviceId);
		paramModifyDevice.put("manufacturerId", deviceInfo.getString("manufacturerId"));
		paramModifyDevice.put("manufacturerName", deviceInfo.getString("manufacturerName"));
		paramModifyDevice.put("deviceType", deviceInfo.getString("deviceType"));
		paramModifyDevice.put("model", deviceInfo.getString("model"));
		paramModifyDevice.put("protocolType", deviceInfo.getString("protocolType"));
		
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
	
	
	/** 
	* @Title: deleteDevice 
	* @Description: 删除设备 
	* @param @param deviceId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	public ResultBean<?> deleteDevice(JSONObject deviceInfo) throws Exception {
		LoggerUtils.Logger(LogName.INFO).info("接收设备删除请求:" + deviceInfo);
		
		ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil,deviceInfo);

		String appId = deviceInfo.getString("appId");
		String deviceId = deviceInfo.getString("deviceId");
		String deleteUrl = Constant.CHINA_TELECOM_DELETE_DEVICE + "/" + deviceId;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		
		StreamClosedHttpResponse response = httpsUtil.doDeleteGetStatusLine(deleteUrl, header);
		ResultBean<String> result = new ResultBean<>();

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == Constant.STATUS_404) {
			result = new ResultBean<>(Constant.ERROR, "设备不存");
		}

		return result;
	}

	
}
