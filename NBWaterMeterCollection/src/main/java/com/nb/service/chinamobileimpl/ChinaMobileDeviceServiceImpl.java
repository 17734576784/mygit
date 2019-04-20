/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.httputil.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.IChinaMobileDeviceService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;

/** 
* @ClassName: ChinaMobileDeviceServiceImpl 
* @Description: 中国移动OneNET设备管理 实现类
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@Service
public class ChinaMobileDeviceServiceImpl implements IChinaMobileDeviceService {
	/** (非 Javadoc) 
	* <p>Title: registerDevice</p> 
	* <p>Description: </p> 
	* @param deviceInfo
	* @return
	* @throws Exception 
	* @see com.nb.service.IChinaMobileDeviceService#registerDevice(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public ResultBean<?> registerDevice(JSONObject deviceInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices";

		JSONObject registerJson = new JSONObject();
		registerJson.put("title", deviceInfo.getString("title"));
		registerJson.put("protocol", deviceInfo.getString("protocolType"));
		JSONObject authInfo = new JSONObject();
		authInfo.put(deviceInfo.getString("imei"), deviceInfo.getString("imsi"));
		registerJson.put("auth_info", authInfo);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(deviceInfo), registerJson.toJSONString());

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
