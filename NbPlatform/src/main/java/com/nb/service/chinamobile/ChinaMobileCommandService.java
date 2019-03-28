/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.service.chinamobile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.http.HttpsClientUtil;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;

/**
 * @ClassName: ChinaMobileDeviceController
 * @Description: 中国移动OneNET命令管理
 * @author dbr
 * @date 2019年1月3日 上午8:44:58
 * 
 */
@Service
public class ChinaMobileCommandService {
	
	/** 命令缓存时间（天） */
	@Value("${commandExpiredTime}")
	private int commandExpiredTime;

	/** 
	* @Title: instantReadDeviceResources 
	* @Description: 即时命令-读设备资源
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	public ResultBean<?> instantReadDeviceResources(JSONObject commandInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";
		@SuppressWarnings("unchecked")
		Map<String, String> params = JSONObject.toJavaObject(commandInfo, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, params,
				CommFunc.getChinaMobileHeader(commandInfo));

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
	public ResultBean<?> instantWriteDeviceResources(JSONObject commandInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot";

		JSONObject urlJson = (JSONObject) commandInfo.clone();
		urlJson.remove("data");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(urlJson, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(commandInfo), commandInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: asynCommand 
	* @Description: 即时命令-命令下发 
	* @param @param commandInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	public ResultBean<?> asynCommand(JSONObject commandInfo) throws Exception {
		
		ResultBean<?> result = new ResultBean<>();
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/execute/offline";
		
		int commandType = commandInfo.getIntValue("commandType");
		Map<String, String> commandMap = CommFunc.getCommandType(Constant.CHINA_MOBILE, commandType);
		if (null == commandMap || commandMap.isEmpty()) {
			result.setStatus(Constant.ERROR);
			result.setError("命令类型不存在");
			return result;
		}
		
		Map<String, Object> urlParams = new HashMap<String, Object>();
		urlParams.put("imei", commandInfo.getString("imei"));
		urlParams.putAll(commandMap);
		Calendar expiredTime = Calendar.getInstance();
		expiredTime.add(Calendar.DAY_OF_YEAR, commandExpiredTime);
		urlParams.put("expired_time", DateUtils.formatDateByFormat(expiredTime.getTime(), "yyyy-MM-dd'T'HH:mm:ss"));

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, urlParams);
		JSONObject paramJson = commandInfo.getJSONObject("param");
		
		JSONObject argsJson = new JSONObject();
		argsJson.put("args", paramJson.getString("args"));
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(commandInfo), argsJson.toJSONString());

		result = new ResultBean<>(response.getContent());
//		System.out.println(result.toString());
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
	public ResultBean<?> observe(JSONObject observeInfo) throws Exception {
		ResultBean<?> result = null;
		String url = Constant.CHINA_MOBILE_BASE_URL + "nbiot/observe";

		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(observeInfo, Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(observeInfo), observeInfo.toJSONString());

		result = new ResultBean<>(response.getContent());

		return result;
	}

}
