package com.ke.shiro;

/**   
 * @ClassName:  MyShiroRealm   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年12月18日 上午8:51:59   
 *      
 */
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.ke.service.IShiroService;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
   
	@Autowired
	private IShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据自己的需求编写获取授权信息，这里简化代码获取了用户对应的所有权限
		String username = (String) principalCollection.fromRealm(getName()).iterator().next();
		if (username != null) {
			List<String> perms = shiroService.getPermissionByUserName(username);
			if (perms != null && !perms.isEmpty()) {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				for (String each : perms) {
					// 将权限资源添加到用户信息中
					info.addStringPermission(each);
				}
				return info;
			}
		}
		return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo
        (AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名，调用currentUser.login的时候执行
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            //查询密码
            String password = shiroService.getPasswordByUserName(username);
            if (password != null) {
				return new SimpleAuthenticationInfo(username, password, getName());
            }
        }
        return null;
    }
}
