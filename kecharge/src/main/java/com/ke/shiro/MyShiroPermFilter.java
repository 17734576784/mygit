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

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

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
