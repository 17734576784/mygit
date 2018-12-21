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

import com.ke.mapper.ShiroMapper;
import com.ke.service.IShiroService;

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
	public void doLogin(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		   Subject currentUser = SecurityUtils.getSubject();
	        if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            token.setRememberMe(true);//是否记住用户
	            try {
	                currentUser.login(token);//执行登录
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

}
