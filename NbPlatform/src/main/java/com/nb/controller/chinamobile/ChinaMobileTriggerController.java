/**   
* @Title: ChinaMobileDeviceController.java 
* @Package com.nb.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
* @version V1.0   
*/
package com.nb.controller.chinamobile;

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
* @Description: 中国移动OneNET触发器备管理 
* @author dbr
* @date 2019年1月3日 上午8:44:58 
*  
*/
@RestController
@RequestMapping("/chinamobile")
public class ChinaMobileTriggerController {
	 
	/** 
	* @Title: addTrigger 
	* @Description: 新增触发器 
	* @param @param triggerInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "triggers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> addTrigger(@RequestBody JSONObject triggerInfo) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers";

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), triggerInfo.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: receiveTrigger 
	* @Description: 触发器回调
	* @param @param triggerInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "receiveTrigger", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> receiveTrigger(@RequestBody JSONObject triggerInfo) throws Exception {

		System.out.println("receiveTrigger : " + triggerInfo);
		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers";

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPostJsonGetStatusLine(url,
				CommFunc.getChinaMobileHeader(), triggerInfo.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	/** 
	* @Title: updateTrigger 
	* @Description: 更新触发器 
	* @param @param deviceId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "triggers/{triggerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> updateTrigger(@PathVariable String triggerId, @RequestBody JSONObject triggerInfo)
			throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers/" + triggerId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doPutJsonGetStatusLine(url, CommFunc.getChinaMobileHeader(),
				triggerInfo.toJSONString());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}

	/** 
	* @Title: getTrigger 
	* @Description: 查看触发器 单个
	* @param @param triggerId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "triggers/{triggerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> getTrigger(@PathVariable String triggerId) throws Exception {
		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers/" + triggerId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;

	}
	
	/** 
	* @Title: listTrigger 
	* @Description: 查看触发器 批量 
	* @param @param triggerInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "triggers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> listTrigger(@PathParam("triggerInfo") String triggerInfo) throws Exception {
		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers";

		@SuppressWarnings("unchecked")
		Map<String, Object> params = JSONObject.toJavaObject(JSONObject.parseObject(triggerInfo), Map.class);

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		url = HttpsClientUtil.setcompleteUrl(url, params);
		StreamClosedHttpResponse response = httpsClientUtil.doGetWithParasGetStatusLine(url, null,
				CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;

	}
	
	
 
	/** 
	* @Title: deleteTrigger 
	* @Description: 删除触发器 
	* @param @param triggerId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ResultBean<?>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "triggers/{triggerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean<?> deleteTrigger(@PathVariable String triggerId) throws Exception {

		String url = Constant.CHINA_MOBILE_BASE_URL + "triggers/" + triggerId;

		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		StreamClosedHttpResponse response = httpsClientUtil.doDeleteGetStatusLine(url, CommFunc.getChinaMobileHeader());

		ResultBean<?> result = new ResultBean<>(response.getContent());

		return result;
	}
	
	
}
