package com.wo.cachemanager;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.wo.model.LoginUser;
import com.wo.util.WebConfig;

/**
 * 管理缓存
 * 可扩展的功能：当chche到内存溢出时必须清除掉最早期的一些缓存对象，这就要求对每个缓存对象保存创建时间
 * @author dbr
 *
 */
public class CacheManager {
	private static ConcurrentHashMap<String, CacheBean> cacheMap;
	
	@SuppressWarnings("unchecked")
	public static void init() {
		cacheMap = new ConcurrentHashMap();
	}

	// 单实例构造方法
	private CacheManager() {
		super();
	}

	//得到缓存。同步静态方法
	private synchronized static CacheBean getCache(String key){
		return (CacheBean) cacheMap.get(key);
	}
	
	//判断是否存在key的缓存
	private synchronized static boolean hasCache(String key){
		return cacheMap.containsKey(key);
	}
	
	//清除所有缓存
	public synchronized static void clearAll(){
		cacheMap.clear();
	}
	
	//清除指定的缓存
	public synchronized static void clearOnly(String key){
		cacheMap.remove(key);
	}
	
	//载入缓存
	public static void putCacheInfo(String key,CacheBean obj){
		cacheMap.put(key, obj);
	}
	
	// 判断缓存是否终止 true:过期 false:没过期
	public static boolean cacheExpired(CacheBean cache) {
		// 传入的缓存不存在
		if (null == cache) { 
			return false;
		}

		long nowDt = System.currentTimeMillis();// 系统当前的毫秒数
		long cacheDt = cache.getTimeOut(); 		// 缓存内的过期毫秒�?
		if (cacheDt <= 0 || cacheDt > nowDt) {  // 过期时间小于等于0
												// 或过期时间大于当前时间时，则为False
			return false;
		} else { // 大于过期时间 即过期
			return true;
		}
	}
	
	//获取缓存信息
	public static CacheBean getCacheInfo(String key) {

		if (hasCache(key)) {
			CacheBean cache = getCache(key);
			// 调用判断是否终止方法 true：终止清理缓�?返回null
			if (cacheExpired(cache)) { 
				cache.setExpired(true);
				clearOnly(key);
				return null;
			} else {
				// 重新给cache计时
				cache.setTimeOut(System.currentTimeMillis()	+ WebConfig.cacheTimeOut);
				return cache;
			}

		} else {
			return null;
		}
	}
	
	//根据value获取缓存信息 用户名+设备id
	@SuppressWarnings("unchecked")
	public static CacheBean getCacheInfoByValue(String value) {
		CacheBean cache = null;
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				cache = (CacheBean) entry.getValue();
				if (value.equals(cache.getValue()) ) {
					cache.setTimeOut(System.currentTimeMillis()	+ WebConfig.cacheTimeOut);
					break;
				}else{
					cache = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return cache;
	}
	
	//载入缓存信息
	public static void putCacheInfo(String key,Object obj,LoginUser loginUser,long dt,boolean expired) {
		CacheBean cache = new CacheBean();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis());  //设置多久后更新缓存
		cache.setValue(obj);
		cache.setLoginUser(loginUser);
		cache.setExpired(expired);		//缓存默认载入时，终止状态为false
		cacheMap.put(key, cache);
	}
	
	// 重写载入缓存信息方法
	public static void putCacheInfo(String key, Object obj,	LoginUser loginUser, long dt) {
		removeKeyByValue(obj);
		CacheBean cache = new CacheBean();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis()); // 设置多久后更新缓存
		cache.setValue(obj);
		cache.setLoginUser(loginUser);
		cache.setExpired(false); // 缓存默认载入时，终止状态为false
		cacheMap.put(key, cache);
	}
	
	// 获取缓存中的大小
	public static int getCacheSize() {
		return cacheMap.size();
	}
	
	//根据value�?用户�?，判断是否已存在token 存在清空      
	@SuppressWarnings({ "unchecked" })
	public synchronized static void clearExistedToken(String value) {
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				CacheBean cache = (CacheBean) entry.getValue();
				if (value.equals(cache.getValue())) {
					i.remove();// 清除Token
 					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
 		}
	}
	
	//根据value值(用户名)，判断是否已存在token 存在清空      
	@SuppressWarnings({ "unchecked" })
	public static String getKeyByValue(String value) {
		String key = "";
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				CacheBean cache = (CacheBean) entry.getValue();
				if (value.equals(cache.getValue())) {
					key = (String) entry.getKey();
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return key;
	}
	
	//根据value值(用户名)，判断是否已存在token 存在清空      
	@SuppressWarnings({ "unchecked" })
	public static String removeKeyByValue(Object obj) {
		String key = "";
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				CacheBean cache = (CacheBean) entry.getValue();
				if (obj.equals(cache.getValue())) {
					key = (String) entry.getKey();
					cacheMap.remove(key);
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return key;
	}

	public synchronized static ConcurrentHashMap<String, CacheBean> getCacheMap() {
		return cacheMap;
	}
}
