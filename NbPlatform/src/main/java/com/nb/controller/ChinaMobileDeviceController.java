/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

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
	/** 
	* @Title: addDevice 
	* @Description: 创建设备 
	* @param @param deviceInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> addDevice(@RequestBody JSONObject deviceInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices";

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), deviceInfo.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: getDeviceInfo 
	* @Description: 查看单个设备 
	* @param @param deviceId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "devices/{deviceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getDeviceInfo(@PathVariable String deviceId) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

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
	@RequestMapping(value = "devices/{deviceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> deleteDevice(@PathVariable String deviceId) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doDeleteGetStatusLine(url, CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	 
	/** 
	* @Title: listDeviceStatus 
	* @Description: 批量查询设备状态 
	* @param @param devIds
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "deviceStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> listDeviceStatus(@PathParam("devIds") String devIds) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/status";
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(devIds), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: listDeviceDatapoint 
	* @Description: 批量查询设备最新数据 
	* @param @param devIds
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "datapoints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> listDeviceDatapoint(@PathParam("devIds") String devIds) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/datapoints";
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(devIds), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	@RequestMapping(value = "datapoints/{deviceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getDeviceDatapoint(@PathVariable String deviceId, @PathParam("param") String param)
			throws Exception {
		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/" + deviceId + "/datapoints";
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(param), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	@RequestMapping(value = "devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getDeviceInfoByIMEI(@PathParam("imei") String imei) throws Exception {
		String url = Constant.CHINA_MOBILE_BASE_URL + "devices/getbyimei";
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(imei), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
}
