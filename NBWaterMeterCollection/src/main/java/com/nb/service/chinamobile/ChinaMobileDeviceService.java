/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobile;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.httputil.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;

/** 
* @ClassName: ChinaMobileDeviceController 
* @Description: 中国移动OneNET设备管理 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@Service
public class ChinaMobileDeviceService {
	/** 
	* @Title: addDevice 
	* @Description: 创建设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	public ResultBean<?> registerDevice(JSONObject deviceInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices";

		JSONObject registerJson = new JSONObject();
		registerJson.put("title", deviceInfo.getString("title"));
		registerJson.put("protocol", deviceInfo.getString("protocolType"));
		JSONObject auth_info = new JSONObject();
		auth_info.put(deviceInfo.getString("imei"), deviceInfo.getString("imsi"));
		registerJson.put("auth_info", auth_info);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(deviceInfo), registerJson.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

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
		String deviceId = deviceInfo.getString("deviceId");
		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doDeleteGetStatusLine(url, CommFunc.getChinaMobileHeader(deviceInfo));

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
}
