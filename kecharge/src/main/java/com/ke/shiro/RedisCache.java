/**   
* @Title: RedisCache.java 
* @Package com.ke.shiro 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 下午7:06:13 
* @version V1.0   
*/
package com.ke.shiro;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;

import com.ke.common.Constant;
import com.ke.utils.JedisUtil;
import com.ke.utils.SerializeUtil;

/** 
* @ClassName: RedisCache 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年12月21日 下午7:06:13 
*  
*/
@Component
public class RedisCache<K, V> implements Cache<K, V> {

	private byte[] getByteKey(K k){
	    if(k instanceof String){
			String key = Constant.CACHE_PREFIX + k;
	        return key.getBytes();
	    }else {
	        return SerializeUtil.serialize(k);
	    }
	}
	/** (非 Javadoc) 
	* <p>Title: clear</p> 
	* <p>Description: </p> 
	* @throws CacheException 
	* @see org.apache.shiro.cache.Cache#clear() 
	*/
	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	/** (非 Javadoc) 
	* <p>Title: get</p> 
	* <p>Description: </p> 
	* @param arg0
	* @return
	* @throws CacheException 
	* @see org.apache.shiro.cache.Cache#get(java.lang.Object) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public V get(K k) throws CacheException {
		// TODO Auto-generated method stub
		System.out.println("redis读取");
		byte[] value = JedisUtil.get(getByteKey(k));
		if (value != null) {
			return (V) SerializeUtil.deserialize(value);
		}
		return null;
	}

	/** (非 Javadoc) 
	* <p>Title: keys</p> 
	* <p>Description: </p> 
	* @return 
	* @see org.apache.shiro.cache.Cache#keys() 
	*/
	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	/** (非 Javadoc) 
	* <p>Title: put</p> 
	* <p>Description: </p> 
	* @param arg0
	* @param arg1
	* @return
	* @throws CacheException 
	* @see org.apache.shiro.cache.Cache#put(java.lang.Object, java.lang.Object) 
	*/
	@Override
	public V put(K k, V v) throws CacheException {
		// TODO Auto-generated method stub
		byte[] key = getByteKey(k);
		byte[] value = SerializeUtil.serialize(v);
		JedisUtil.set(key, value);
		JedisUtil.expire(key, Constant.CACHE_TIME_OUT);
		return v;
	}

	/** (非 Javadoc) 
	* <p>Title: remove</p> 
	* <p>Description: </p> 
	* @param arg0
	* @return
	* @throws CacheException 
	* @see org.apache.shiro.cache.Cache#remove(java.lang.Object) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public V remove(K k) throws CacheException {
		// TODO Auto-generated method stub
		byte[] key = getByteKey(k);
		byte[] value = JedisUtil.get(key);
		JedisUtil.del(key);
		if(value != null) {
			return (V) SerializeUtil.deserialize(value);
		}
		return null;
	}

	/** (非 Javadoc) 
	* <p>Title: size</p> 
	* <p>Description: </p> 
	* @return 
	* @see org.apache.shiro.cache.Cache#size() 
	*/
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** (非 Javadoc) 
	* <p>Title: values</p> 
	* <p>Description: </p> 
	* @return 
	* @see org.apache.shiro.cache.Cache#values() 
	*/
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
