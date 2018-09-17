/**
 * 
 */
package com.iot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.AuthenticationUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.HttpsUtil;
import com.iot.utils.JsonUtil;
import com.iot.utils.Log4jUtils;
import com.iot.utils.StreamClosedHttpResponse;

/**
 * @ClassName: ModifyDeviceInfoController
 * @Description:根据Profile修改设备信息
 * @author: dbr
 * @date: 2018年9月5日 下午3:07:00
 * 
 */
@RestController
public class ModifyDeviceInfoController {

	/**
	 * @param deviceInfo{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1","manufacturerId":"XLXX",
	 *        "manufacturerName":"XLXX","deviceType":"GasMeter",
	 *        "model":"XL0001","protocolType":"CoAP"}
	 * @return
	 */
	@RequestMapping(value = "modifyDeviceInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject modifyDeviceInfo(String deviceInfo) {
		JSONObject rtnJson = new JSONObject();
		JSONObject paramModifyDevice = new JSONObject();

		try {
			paramModifyDevice = JSONObject.parseObject(deviceInfo);

			HttpsUtil httpsUtil = new HttpsUtil();
			httpsUtil.initSSLConfigForTwoWay();
			String accessToken = AuthenticationUtils.getAccessToken(httpsUtil);

			String appId = Constant.APPID;
			String deviceId = ConverterUtils.toStr(paramModifyDevice.get("deviceId"));
			String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;

			String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDevice);

			Map<String, String> header = new HashMap<>();
			header.put(Constant.HEADER_APP_KEY, appId);
			header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

			StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
					header, jsonRequest);
			rtnJson = CommFunc.result(Constant.SUCCESS, responseModifyDeviceInfo.getContent());

		} catch (Exception e) {
			Log4jUtils.getError().error("修改设备信息异常，入参:" + paramModifyDevice);
			rtnJson = CommFunc.result(Constant.ERROR, "修改设备信息异常");
			e.printStackTrace();
		}

		return rtnJson;
	}
}
