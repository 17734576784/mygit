/**   
* @Title: RedisCacheManager.java 
* @Package com.ke.shiro 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 下午7:13:45 
* @version V1.0   
*/
package com.ke.shiro;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* @ClassName: RedisCacheManager 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年12月21日 下午7:13:45 
*  
*/
public class RedisCacheManager implements CacheManager{

	@Autowired
	private RedisCache redisCache;
	/** (非 Javadoc) 
	* <p>Title: getCache</p> 
	* <p>Description: </p> 
	* @param arg0
	* @return
	* @throws CacheException 
	* @see org.apache.shiro.cache.CacheManager#getCache(java.lang.String) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
		// TODO Auto-generated method stub
		return this.redisCache;
	}

}
