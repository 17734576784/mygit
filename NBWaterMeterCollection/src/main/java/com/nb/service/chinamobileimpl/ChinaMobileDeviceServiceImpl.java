/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.httputil.HttpsClientUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.DeviceInfo;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.IChinaMobileDeviceService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ChinaMobileDeviceServiceImpl 
* @Description: 中国移动OneNET设备管理 实现类
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@Service
public class ChinaMobileDeviceServiceImpl implements IChinaMobileDeviceService {
	
	@Resource
	private CommonMapper commonMapper;
	
	/** (非 Javadoc) 
	* <p>Title: registerDevice</p> 
	* <p>Description: </p> 
	* @param deviceInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileDeviceService#registerDevice(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> registerDevice(JSONObject deviceMsg) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices";

		Map<String, String> deviceMap = new HashMap<>();
		deviceMap = JsonUtil.jsonString2SimpleObj(deviceMsg, deviceMap.getClass());
		
		DeviceInfo deviceInfo = commonMapper.getDeviceInfo(deviceMap);
		if (deviceInfo == null) {
			ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
			return result;
		}
		
		JSONObject registerJson = new JSONObject();
		registerJson.put("title", deviceInfo.getImei());
		registerJson.put("protocol", Constant.LWM2M);
		JSONObject authInfo = new JSONObject();
		authInfo.put(deviceInfo.getImei(), deviceInfo.getImsi());
		registerJson.put("auth_info", authInfo);
		

		JSONObject appInfo = new JSONObject();
		appInfo.put("appId", deviceInfo.getAppId());
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(appInfo), registerJson.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	

	/** (非 Javadoc) 
	* <p>Title: deleteDevice</p> 
	* <p>Description: </p> 
	* @param deviceInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileDeviceService#deleteDevice(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> deleteDevice(JSONObject deviceInfo) throws Exception {
		String deviceId = deviceInfo.getString("deviceId");
		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doDeleteGetStatusLine(url, CommFunc.getChinaMobileHeader(deviceInfo));

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
}
