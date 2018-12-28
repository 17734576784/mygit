/**
 * 
 */
package com.dbr.service;

/**   
 * @ClassName:  ShiroService   
 * @Description:TODO(������һ�仰��������������)   
 * @author: dbr 
 * @date:   2018��9��8�� ����8:48:45   
 *      
 */
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.dbr.dao.ShiroDAO;

public class ShiroService {
    private ShiroDAO shiroDAO;

    public void setShiroDAO(ShiroDAO shiroDAO) {
        this.shiroDAO = shiroDAO;
    }

    /**
     * ��¼
     */
    public void doLogin(String username, String password) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);//�Ƿ��ס�û�
            try {
                currentUser.login(token);//ִ�е�¼
            } catch (UnknownAccountException uae) {
                throw new Exception("�˻�������");
            } catch (IncorrectCredentialsException ice) {
                throw new Exception("���벻��ȷ");
            } catch (LockedAccountException lae) {
                throw new Exception("�û��������� ");
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
                throw new Exception("δ֪����");
            }
        }
    }

    /**
     * �����û�����ѯ����
     */
    public String getPasswordByUserName(String username) {
        return shiroDAO.getPasswordByUserName(username);
    }

    /**
     * ��ѯ�û�����Ȩ��
     */
    public List<String> getPermissionByUserName(String username) {
        return shiroDAO.getPermissionByUserName(username);
    }
}

