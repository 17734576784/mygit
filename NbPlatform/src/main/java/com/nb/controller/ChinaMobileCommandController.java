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
}
