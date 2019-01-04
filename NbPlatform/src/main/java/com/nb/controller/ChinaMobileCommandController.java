/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.controller;

import java.util.HashMap;
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
 * @Description: 中国移动OneNET命令管理
 * @author dbr
 * @date 2019年1月3日 上午8:44:58
 * 
 */
@RestController
@RequestMapping("/chinamobile")
public class ChinaMobileCommandController {

	/** 
	* @Title: instantReadDeviceResources 
	* @Description: 即时命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/instantresource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantReadDeviceResources(@PathParam("commandInfo") String commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";
		@SuppressWarnings("unchecked")
		Map<String, String> params = JSONObject.toJavaObject(JSONObject.parseObject(commandInfo), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: 即时命令-写设备资源 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/instantresource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantWriteDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";

		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("data");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: instantSendCommand 
	* @Description: 即时命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/instantcommand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> instantSendCommand(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/execute";

		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("args");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: listResources 
	* @Description: 获取资源列表 
	* @param @param resource
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/resources", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> listResources(@PathParam("resource") String resource) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/resources";
		@SuppressWarnings("unchecked")
		Map<String, String> params = JSONObject.toJavaObject(JSONObject.parseObject(resource), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: observe 
	* @Description: 订阅 
	* @param @param observeInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/observe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> observe(@RequestBody JSONObject observeInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/observe";

		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(observeInfo, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), observeInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: offlineReadDeviceResources 
	* @Description: 缓存命令-读设备资源 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/offlineesource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> offlineReadDeviceResources(@PathParam("commandInfo") String commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline";
		@SuppressWarnings("unchecked")
		Map<String, String> params = JSONObject.toJavaObject(JSONObject.parseObject(commandInfo), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: offlineWriteDeviceResources 
	* @Description: 缓存命令-写设备资源 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/offlineesource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> offlineWriteDeviceResources(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline";
		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("data");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);

		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: offlineSendCommand 
	* @Description: 缓存命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/offlinecommand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> offlineSendCommand(@RequestBody JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/execute/offline";

		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("args");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: listOfflineCommand 
	* @Description: 查看指定设备缓存命令列表 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/listOfflineCommand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> listOfflineCommand(@PathParam("commandInfo") String commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline/history";
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(commandInfo), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: getOfflineCommand 
	* @Description: 查看指定缓存命令详情 
	* @param @param uuid
	* @param @param imei
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/getOfflineCommand/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getOfflineCommand(@PathVariable String uuid, @PathParam("imei") String imei)
			throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline/history/" + uuid;
		Map<String, String> params = new HashMap<>();
		params.put("imei", imei);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: cancelOfflineCommand 
	* @Description: 取消缓存命令 
	* @param @param uuid
	* @param @param imei
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/cancelOfflineCommand/{uuid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> cancelOfflineCommand(@PathVariable String uuid, @RequestBody JSONObject param)
			throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/offline/cancel/" + uuid;
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(param, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPutJsonGetStatusLine(url, CommFunc.getChinaMobileHeader(),
				params.toString());
		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	
}
