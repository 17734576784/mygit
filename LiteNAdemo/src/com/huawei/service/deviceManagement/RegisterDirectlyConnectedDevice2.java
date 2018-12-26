package com.huawei.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsClientUtil;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.JsonUtil;
import com.huawei.utils.StreamClosedHttpResponse;

/**
 * Register Directly Connected Device :
 * This interface is used by NAs to register devices on the IoT platform.
 * After the registration is successful,
 * the IoT platform allocates a device ID for the device,which is used as the unique identifier of the device.
 * Unregistered devices are not allowed to access the IoT platform.
 * In NB-IoT scenarios, the Set device info interface needs to be invoked to set device information after the registration is successful.
 */
public class RegisterDirectlyConnectedDevice2 {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsClientUtil httpsUtil = new HttpsClientUtil();
//        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
//        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
		String urlReg = Constant.REGISTER_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you use the demo.
        String verifyCode = "8690290334323931";
		String nodeId = verifyCode;
        Integer timeout = 0;

        Map<String, String> paramReg = new HashMap<>();
        paramReg.put("username", "admin");
        paramReg.put("password", "123456");
//        paramReg.put("queryJsonStr", "123456789");
//        paramReg.put("token", "123456789");

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
        urlReg = "http://127.0.0.1:8080/login.json";
//        Map<String, String> header = new HashMap<>();
//        header.put(Constant.HEADER_APP_KEY, appId);
//        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, paramReg.toString());

        System.out.println("RegisterDirectlyConnectedDevice, response content:");
        System.out.print(responseReg.getStatusLine());
        System.out.println(responseReg.getContent());
        System.out.println();
    }

    /**
     * Authentication，get token
     * */
    @SuppressWarnings("unchecked")
    public static String login(HttpsClientUtil httpsUtil) throws Exception {

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

        System.out.println("app auth success,return accessToken:");
        System.out.print(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}
