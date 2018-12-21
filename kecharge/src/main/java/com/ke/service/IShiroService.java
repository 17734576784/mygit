/**   
* @Title: IShiroService.java 
* @Package com.ke.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月20日 上午11:19:17 
* @version V1.0   
*/
package com.ke.service;

import java.util.List;

/**
 * @ClassName: IShiroService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月20日 上午11:19:17
 * 
 */
public interface IShiroService {
	List<String> getPermissionByUserName(String username);

	String getPasswordByUserName(String username);
	void doLogin(String username, String password) throws Exception;
}
