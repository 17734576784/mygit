/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.http.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;

import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @ClassName: ChinaMobileDeviceController 
* @Description: 中国移动OneNET设备管理 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@RestController
@RequestMapping("/chinamobile")
public class ChinaMobileDeviceController {
	@RequestMapping(value = "devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> addDevice(@RequestBody JSONObject deviceInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices";

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), deviceInfo.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	@RequestMapping(value = "devices/{deviceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getDeviceInfo(@PathVariable String deviceId) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	@RequestMapping(value = "devices/{deviceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> deleteDevice(@PathVariable String deviceId) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doDeleteGetStatusLine(url, CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	
}
