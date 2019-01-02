/**   
* @Title: CustomSessionManager.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月24日 下午2:00:25 
* @version V1.0   
*/
package com.ke.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* @ClassName: CustomSessionManager 
* @Description: 自定义shiro的sessionManager
* @author dbr
* @date 2018年12月24日 下午2:00:25 
*  
*/
public class CustomSessionManager extends DefaultWebSessionManager {

	@Autowired
	private RedisSessionDao redisSessionDao;
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		// TODO Auto-generated method stub
		Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if(sessionId instanceof WebSessionKey){
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
		if (request != null && sessionId != null) {
			Session session = (Session) request.getAttribute(sessionId.toString());
			if (session != null) {
				return session;
			}
		}

        Session session = super.retrieveSession(sessionKey);
        if(request !=null && sessionId != null){
            request.setAttribute(sessionId.toString(),session);
        }

        return session;
	}

	@Override
	protected Session retrieveSessionFromDataSource(Serializable sessionId) throws UnknownSessionException {
		// TODO Auto-generated method stub
		return redisSessionDao.readSession(sessionId);
	}	
	
	
}
