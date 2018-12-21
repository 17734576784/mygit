/**   
* @Title: MyShiroPermFilter.java 
* @Package com.dbr.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月17日 下午2:58:10 
* @version V1.0   
*/
package com.ke.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.alibaba.fastjson.JSONObject;
import com.ke.utils.JedisUtils;

/**
 * @ClassName: MyShiroPermFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月17日 下午2:58:10
 * 
 */
public class MyShiroPermFilter extends AuthorizationFilter {

	/** (非 Javadoc) 
	* <p>Title: isAccessAllowed</p> 
	* <p>Description: </p> 
	* @param request
	* @param response
	* @param mappedValue
	* @return
	* @throws Exception 
	* @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object) 
	*/
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String token = request.getParameter("token");
		if (token != null && JedisUtils.get(token) != null) {
			JSONObject json = (JSONObject) JedisUtils.get(token);

			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(json.getString("username"),
						json.getString("password"));
				usernamePasswordToken.setRememberMe(true);// 是否记住用户
				try {
					currentUser.login(usernamePasswordToken);// 执行登录
				} catch (UnknownAccountException uae) {
					throw new Exception("账户不存在");
				} catch (IncorrectCredentialsException ice) {
					throw new Exception("密码不正确");
				} catch (LockedAccountException lae) {
					throw new Exception("用户被锁定了 ");
				} catch (AuthenticationException ae) {
					ae.printStackTrace();
					throw new Exception("未知错误");
				}
			}
		}
		
		Subject subject = getSubject(request, response);
		String[] permsArray = (String[]) mappedValue;
		if (permsArray == null || permsArray.length == 0) { // 没有权限限制
			return true;
		}
		for (int i = 0; i < permsArray.length; i++) {
			// 如果是角色，就是subject.hasRole()
			// 若当前用户是permsArray中的任何一个，则有权限访问
			if (subject.isPermitted(permsArray[i])) {
				return true;
			}
		}
		return false;
	}
}
