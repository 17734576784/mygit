/**   
* @Title: RedisSessionDao.java 
* @Package com.ke.shiro 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月24日 下午2:06:55 
* @version V1.0   
*/
package com.ke.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.ke.common.Constant;
import com.ke.utils.JedisUtil;
import com.ke.utils.SerializeUtil;

/** 
* @ClassName: RedisSessionDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年12月24日 下午2:06:55 
*  
*/
public class RedisSessionDao extends AbstractSessionDAO{


    private byte[] getKey(String key){
		return (Constant.SESSION_PREFIX + key).getBytes();
    }

	/** (非 Javadoc) 
	* <p>Title: update</p> 
	* <p>Description: </p> 
	* @param session
	* @throws UnknownSessionException 
	* @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session) 
	*/
	@Override
	public void update(Session session) throws UnknownSessionException {
		saveSession(session);
	}

	/** (非 Javadoc) 
	* <p>Title: delete</p> 
	* <p>Description: </p> 
	* @param session 
	* @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session.Session) 
	*/
	@Override
	public void delete(Session session) {
		if(session == null || session.getId() ==null) {
			return;
		}
		byte[] key = getKey(session.getId().toString());
		JedisUtil.del(key);
	}

	/** (非 Javadoc) 
	* <p>Title: getActiveSessions</p> 
	* <p>Description: </p> 
	* @return 
	* @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions() 
	*/
	@Override
	public Collection<Session> getActiveSessions() {
		Set<byte[]> keys = JedisUtil.keys(Constant.SESSION_PREFIX + "*");
		Set<Session> sessions = new HashSet<Session>();
		if (CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		
		for (byte[] key : keys) {
			Session session = (Session) SerializeUtil.deserialize(JedisUtil.get(key));
			sessions.add(session);
		}
		return sessions;
	}

	/** (非 Javadoc) 
	* <p>Title: doCreate</p> 
	* <p>Description: </p> 
	* @param session
	* @return 
	* @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session) 
	*/
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}
	
	private void saveSession(Session session) {
		if(session != null && session.getId() != null) {
			byte[] key = getKey(session.getId().toString());
			byte[] value = SerializeUtil.serialize(session);
			JedisUtil.set(key, value);
			JedisUtil.expire(key, Constant.SESSION_TIME_OUT);
		}
	}

	/** (非 Javadoc) 
	* <p>Title: doReadSession</p> 
	* <p>Description: </p> 
	* @param sessionId
	* @return 
	* @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable) 
	*/
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			return null;
		}
		
		System.out.println("read session");
		byte[] key = getKey(sessionId.toString());
		byte[] value = JedisUtil.get(key);
		return (Session) SerializeUtil.deserialize(value);
	}

}
