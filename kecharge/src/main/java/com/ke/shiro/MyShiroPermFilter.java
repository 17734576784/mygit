/**   
* @Title: MyShiroPermFilter.java 
* @Package com.dbr.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月17日 下午2:58:10 
* @version V1.0   
*/
package com.ke.shiro;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import com.ke.model.LoginUser;
import com.ke.utils.Constant;
import com.ke.utils.ConverterUtils;
import com.ke.utils.JedisUtils;
import com.ke.utils.SerializeUtils;

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
		Subject subject = getSubject(request, response);
		String[] permsArray = (String[]) mappedValue;
		if (permsArray == null || permsArray.length == 0) { // 没有权限限制
			return true;
		}
		
		/**接口使用，web不必*/
		String token = ConverterUtils.toStr(request.getParameter("token"));
		byte[] value = JedisUtils.get((Constant.TOKEN_PREFIX + token).getBytes());
		if (null != value) {
			LoginUser loginUser = (LoginUser) SerializeUtils.deserialize(value);
			List<String> prems = loginUser.getPermList();
			for (String prem : prems) {
				if (Arrays.asList(permsArray).contains(prem)) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < permsArray.length; i++) {
				// 如果是角色，就是subject.hasRole()
				// 若当前用户是permsArray中的任何一个，则有权限访问
				if (subject.isPermitted(permsArray[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
}
