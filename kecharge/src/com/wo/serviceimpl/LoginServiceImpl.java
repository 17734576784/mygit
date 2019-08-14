package com.wo.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;

import com.wo.http.HttpUtil;
import com.wo.cachemanager.CacheManager;
import com.wo.mapper.PartnerParaMapper;
import com.wo.model.LoginUser;
import com.wo.model.PartnerPara;
import com.wo.service.ILoginService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.GenerateToken;
import com.wo.util.Log4jUtil;
import com.wo.util.WebConfig;

/**
 * @author dbr
 *
 */
@Service
public class LoginServiceImpl implements ILoginService{

	@Resource
	private PartnerParaMapper partnerParaMapper;
	
	/* (non-Javadoc)
	 * @see com.wo.service.ILoginService#checkUserLogin(java.lang.String)
	 */
	public JSONObject checkUserLogin(String queryJsonStr) {
		String token = Constant.EMPTY; // 错误编码
		JSONObject rtnJson = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String userName = json.optString("userName");
			String passWord = json.optString("passWord");
		
			PartnerPara partner = this.partnerParaMapper.getPartnerPara(userName);
			if (partner == null) {
				rtnJson = CommFunc.errorInfo(Constant.TIMEOUT, "不存在该用户");
				return rtnJson;
			}
			
			String dbPwd = partner.getPasswd();
			if (passWord.equals(dbPwd)) {
				// 判断该用户是否已存在token 若存在清空原Token 生成新token
				CacheManager.clearExistedToken(userName);
				LoginUser loginUser = new LoginUser();
				loginUser.setPartnerId(partner.getId());
				loginUser.setDescribe(partner.getDescrib());
				loginUser.setLoginName(userName);
				
				//根据用户名和当前时间 生成token值
				token = GenerateToken.generageToken(userName + System.currentTimeMillis());
				//将新生成的token放入Cache列表中
				CacheManager.putCacheInfo(token, userName, loginUser, WebConfig.cacheTimeOut);
				Log4jUtil.getInfo().info(userName + "获取token成功！token:" + token);		
				rtnJson = CommFunc.errorInfo(Constant.OK, "");
				rtnJson.put("token", token);
			} else {
				rtnJson = CommFunc.errorInfo(Constant.TIMEOUT, "用户名密码不匹配");
			}
		} catch (Exception e) {
			rtnJson = CommFunc.errorInfo(Constant.TIMEOUT, "用户登录异常");
			Log4jUtil.getError().error("用户登录异常" + queryJsonStr, e);
			e.printStackTrace();
		}
		
		return rtnJson;
	}
	
	/* (non-Javadoc)
	 * @see com.wo.service.ILoginService#sendLoginTokenRequest()
	 */
	public String sendLoginTokenRequest(JSONObject jsonPara) {
		String token = "";
 		Map<String,String> param = new HashMap<String,String>();
		JSONObject json = new JSONObject();
		json.put("userName", jsonPara.optString("userName"));
		json.put("password", jsonPara.optString("passWord"));
		param.put("queryJsonStr", json.toString());
		
		String sendLoginUrl = jsonPara.optString("loginUrl");
		if (sendLoginUrl.isEmpty()) {
			return token;
		}
		
		String rtnJson = CommFunc.objToStr(HttpUtil.doPost(sendLoginUrl,param));
		if (rtnJson.isEmpty()) {
			return token;
		}
		
		json = JSONObject.fromObject(rtnJson);
		if(json.getInt("status") == Constant.STATUS_OK){
			//获取token
			if(json.containsKey("token")){
				token = CommFunc.objToStr(json.get("token"));
				Log4jUtil.getInfo().info("请求token成功：token：" + token);
			}
			else{
				Log4jUtil.getInfo().info("没有返回token");
			}
		} 
		else{
			Log4jUtil.getInfo().error("状态码："+json.getInt("status") +" 错误描述："+json.getString("error"));
		}
			
		return token;
	}
}
