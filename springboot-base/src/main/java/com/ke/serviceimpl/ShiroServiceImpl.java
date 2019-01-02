/**   
* @Title: ShiroServiceImpl.java 
* @Package com.ke.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月20日 上午11:19:37 
* @version V1.0   
*/
package com.ke.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ke.mapper.ShiroMapper;
import com.ke.model.LogEnum;
import com.ke.model.LoginUser;
import com.ke.service.IShiroService;
import com.ke.utils.Constant;
import com.ke.utils.JedisUtils;
import com.ke.utils.LoggerUtils;
import com.ke.utils.SerializeUtils;

/** 
* @ClassName: ShiroServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年12月20日 上午11:19:37 
*  
*/
@Service
public class ShiroServiceImpl implements IShiroService {

	@Resource
	private ShiroMapper shiroMapper;
	/** (非 Javadoc) 
	* <p>Title: getPermissionByUserName</p> 
	* <p>Description: </p> 
	* @param username
	* @return 
	* @see com.ke.service.IShiroService#getPermissionByUserName(java.lang.String) 
	*/
	@Override
	public List<String> getPermissionByUserName(String username) {
		// TODO Auto-generated method stub
		return shiroMapper.getPermissionByUserName(username);
	}

	/** (非 Javadoc) 
	* <p>Title: getPasswordByUserName</p> 
	* <p>Description: </p> 
	* @param username
	* @return 
	* @see com.ke.service.IShiroService#getPasswordByUserName(java.lang.String) 
	*/
	@Override
	public String getPasswordByUserName(String username) {
		// TODO Auto-generated method stub
		return shiroMapper.getPasswordByUserName(username);
	}

	/** (非 Javadoc) 
	* <p>Title: doLogin</p> 
	* <p>Description: </p> 
	* @param username
	* @param password 
	 * @throws Exception 
	* @see com.ke.service.IShiroService#doLogin(java.lang.String, java.lang.String) 
	*/
	@Override
	public JSONObject doLogin(String username, String password) {
		JSONObject rtnJson = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);// 是否记住用户
			try {
				currentUser.login(token);// 执行登录
				
				rtnJson.put("token", "123456789");
				rtnJson.put("username", username);
				rtnJson.put("password", password);
				List<String> perms = getPermissionByUserName(username);

				LoginUser loginUser = new LoginUser();
				loginUser.setLoginName(username);
				loginUser.setPermList(perms);
				byte[] key = (Constant.TOKEN_PREFIX + "123456789").getBytes();
				JedisUtils.set(key, SerializeUtils.serialize(loginUser));
				JedisUtils.expire(key, Constant.CACHE_TIME_OUT);
			} catch (UnknownAccountException uae) {
				rtnJson.put(Constant.RESULT_CODE, Constant.REQUEST_BAD);
				rtnJson.put(Constant.RESULT_DETAIL, "账户不存在");
			} catch (IncorrectCredentialsException ice) {
				rtnJson.put(Constant.RESULT_CODE, Constant.REQUEST_BAD);
				rtnJson.put(Constant.RESULT_DETAIL, "密码不正确");
			} catch (LockedAccountException lae) {
				rtnJson.put(Constant.RESULT_CODE, Constant.REQUEST_BAD);
				rtnJson.put(Constant.RESULT_DETAIL, "用户被锁定了 ");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				rtnJson.put(Constant.RESULT_CODE, Constant.REQUEST_BAD);
				rtnJson.put(Constant.RESULT_DETAIL, "未知错误");
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.Logger(LogEnum.ERROR).error("登录异常" + e.getMessage());
				rtnJson.put(Constant.RESULT_CODE, Constant.REQUEST_BAD);
				rtnJson.put(Constant.RESULT_DETAIL, "请求错误");
			}
		}
		return rtnJson;
	}

}
