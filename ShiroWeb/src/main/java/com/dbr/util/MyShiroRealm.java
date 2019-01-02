/**
 * 
 */
package com.dbr.util;

/**   
 * @ClassName:  MyShiroRealm   
 * @Description:TODO(������һ�仰��������������)   
 * @author: dbr 
 * @date:   2018��9��8�� ����8:51:59   
 *      
 */
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.dbr.service.ShiroService;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    private ShiroService shiroService;

    public void setShiroService(ShiroService shiroService) {
        this.shiroService = shiroService;
    }
    
    

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //�����Լ��������д��ȡ��Ȩ��Ϣ������򻯴����ȡ���û���Ӧ������Ȩ��
        String username = (String) principalCollection.fromRealm(getName()).iterator().next();
        if (username != null) {
            List<String> perms = shiroService.getPermissionByUserName(username);
            if (perms != null && !perms.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : perms) {
                    //��Ȩ����Դ��ӵ��û���Ϣ��
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
        // ͨ�������յ��û���������currentUser.login��ʱ��ִ��
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            //��ѯ����
            String password = shiroService.getPasswordByUserName(username);
            if (password != null) {
                return new SimpleAuthenticationInfo(username, password, getName());
            }
        }
        return null;
    }
}
