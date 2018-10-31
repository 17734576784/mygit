/**
 * 
 */
package com.wo.service;

import net.sf.json.JSONObject;

/**
 * @author dbr
 * 
 */
public interface ILoginService {
	// 验证用户登录
	JSONObject checkUserLogin(String queryJsonStr);

	// 获取登录token
	String sendLoginTokenRequest(JSONObject json);
}
