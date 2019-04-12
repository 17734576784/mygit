/**   
* @Title: java 
* @Package com.icpms.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年1月24日 上午11:46:08 
* @version V1.0   
*/
package com.nb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;

/**
 * @ClassName: RedisUtil
 * @Description: Redis工具类
 * @author dbr
 * @date 2018年1月24日 上午11:46:08
 * 
 */
@Component
public class JedisUtils {

	/** 
     * 成功,"OK" 
     */  
    public static final String SUCCESS_OK = "OK";  
    /** 
     * 成功,1L 
     */  
    public static final Long SUCCESS_STATUS_LONG = 1L;  
    /** 
     * 只用key不存在时才设置。Only set the key if it does not already exist 
     */  
    public static final String NX = "NX";  
    /** 
     * XX -- 只有key存在时才设置。和NX相反。Only set the key if it already exist. 
     */  
    public static final String XX = "XX";  
    /** 
     * EX|PX, 时间单位，EX是秒，PX是毫秒。expire time units: EX = seconds; PX = milliseconds 
     */  
    public static final String EX = "EX";  
    
	@Autowired
	public JedisPool jedisPool;
	
	public static JedisUtils jedisUtils;

	@PostConstruct
	private void init() {
		jedisUtils = this;
		jedisUtils.jedisPool = this.jedisPool;
	}
	
	/**
	 * 同步获取Jedis实例
	 * */
	public static Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisUtils.jedisPool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}
	
	/**
	 * 释放jedis资源
	 * */
	public static void returnRource(final Jedis jedis){
		if (jedis != null && jedisUtils.jedisPool != null) {
			jedis.close();
		}
	}
	
	/** 
     * 成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.set(key, value);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return false;
	}  
	
	/** 
     * 成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean setByByte(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.set(key.getBytes(), SerializeUtils.serialize(value));
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return false;
	}  
	
	/** 
     * 成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
	public static Object getByByte(String key) {
		Jedis jedis = null;
		Object object = null;
		try {
			jedis = getResource();
			byte[] value = null;
			value = jedis.get(key.getBytes());
			object = SerializeUtils.deserialize(value);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return null;
	}  
	
    /** 
     * 返回值 
     * @param key 
     * @return 
     */  
	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return null;
	}
      
	/** 
     * 返回值 
     * @param key 
     * @return 
     */  
	public static byte[] get(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return null;
	}
	
    /** 
     * 设置key值和过期时间 
     * @param key 
     * @param value 
     * @param seconds 秒数，不能小于0 
     * @return 
     */  
	public static boolean setByTime(String key, String value, int seconds) {
		if (seconds < 0) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.setex(key, seconds, value);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return false;
	}
      
    /** 
     *  
     * @param key 
     * @param value 
     * @param nxxx NX|XX  是否存在 
     * <li>NX -- Only set the key if it does not already exist.</li> 
     * <li>XX -- Only set the key if it already exist.</li> 
     * @param expx EX|PX, expire time units ，时间单位格式，秒或毫秒 
     * <li>EX = seconds;</li> 
     * <li>PX = milliseconds</li> 
     * @param time expire time in the units of expx，时间（long型），不能小于0 
     * @return 
     */  
	public static boolean set(String key, String value, String nxxx, String expx, long time) {
		if (time < 0) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.set(key, value, nxxx, expx, time);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
      
	 /** 
     *  
     * @param key 
     * @param value 
     * @param nxxx NX|XX  是否存在 
     * <li>NX -- Only set the key if it does not already exist.</li> 
     * <li>XX -- Only set the key if it already exist.</li> 
     * @param expx EX|PX, expire time units ，时间单位格式，秒或毫秒 
     * <li>EX = seconds;</li> 
     * <li>PX = milliseconds</li> 
     * @param time expire time in the units of expx，时间（long型），不能小于0 
     * @return 
     */  
	public static boolean set(String key, String value, int time) {
		if (time < 0) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			if (time > 0) {
				jedis.expire(key, time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
    /** 
     * 设置key 
     * @param key 
     * @param value 
     * @param nxxx NX|XX 是否需要存在 
     * <li>NX -- Only set the key if it does not already exist.</li>  
     * <li>XX -- Only set the key if it already exist.</li>  
     * @return 
     */  
	public static boolean set(String key, String value, String nxxx) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.set(key, value, nxxx);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
    /** 
     * 当key不存在时，设置值，成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean setIfNotExists(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.setnx(key, value);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
      
    /** 
     * 当key不存在时，设置值，成功返回true，同setIfNotExists 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean setNX(String key, String value) {
		return setIfNotExists(key, value);
	}
      
    /** 
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
	public static boolean setIfNotExists(String key, String value, long seconds) {
		if (seconds < 0) {
			return false;
		}
		return set(key, value, NX, EX, seconds);
	}
      
    /** 
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值，同setIfNotExists(key, value, seconds) 
     * @param key 
     * @param value 
     * @param seconds 
     * @return 
     */  
	public static boolean setNX(String key, String value, Long seconds) {
		return setIfNotExists(key, value, seconds);
	} 
      
    /** 
     * 当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean setIfExists(String key, String value) {
		return set(key, value, XX);
	}

    /** 
     * 当key存在时则设置值，成功返回true，不存在不设置值，同setIfExists 
     * @param key 
     * @param value 
     * @return 
     */  
	public static boolean setXX(String key, String value) {
		return setIfExists(key, value);
	}

    /** 
     * 仅当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
	public static boolean setIfExists(String key, String value, long seconds) {
		if (seconds < 0) {
			return false;
		}
		return set(key, value, XX, EX, seconds);
	} 
      
    /** 
     * 仅当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
	public static boolean setXX(String key, String value, long seconds) {
		return setIfExists(key, value, seconds);
	}
      
    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
	public static boolean setTime(String key, Integer seconds) {
		Jedis jedis = null;
		try {
			if (seconds == null) {
				seconds = -1;
			}
			jedis = getResource();
			Long statusCode = jedis.expire(key, seconds);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}  
		return false;
	} 
      
    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
	public static boolean setOutTime(String key, Integer seconds) {
		return setTime(key, seconds);
	}

    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 秒数，为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
	public static boolean expire(String key, Integer seconds) {
		return setTime(key, seconds);
	}
      
    /** 
     * 判断key是否存在，存在返回true 
     * @param key 
     * @return 
     */  
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return false;
	}
	
	 /** 
     * 判断key是否存在，存在返回true 
     * @param key 
     * @return 
     */  
	public static boolean exists(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return false;
	}
      
    /** 
     * 判断key是否存在，存在返回true 
     * @param key 
     * @return 
     */  
	public static boolean isExists(String key) {
		return exists(key);
	} 
      
    /** 
     * 将key设置为永久 
     * @param key 
     * @return 
     */  
	public static boolean persist(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			long time = getTime(key);
			if (time == -1) {
				return true;
			}
			// 已经是永久的，返回0
			Long statusCode = jedis.persist(key);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return false;
	} 
      
    /** 
     * 获取剩余时间（秒） 
     * @param key 
     * @return 
     */  
	public static Long getTime(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.ttl(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return -1L;
	} 
      
    /** 
     * 获取剩余时间（秒） 
     * @param key 
     * @return 
     */  
	public static Long getTtl(String key) {
		return getTime(key);
	} 
      
    /** 
     * 随机获取一个key 
     * @return 
     */  
	public static String randomKey() {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.randomKey();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}  
      
    /** 
     * 随机获取一个key 
     * @return 
     */  
	public static String random() {
		return randomKey();
	} 
      
    /** 
     * 修改 key 的名称，成功返回true，如果不存在该key，则会抛错：ERR no such key 
     * 注：如果newKey已经存在，则会进行覆盖。建议使用renameNX 
     * @param oldkey 原来的key 
     * @param newKey 新的key 
     * @return 
     */  
	public static boolean rename(String oldkey, String newKey) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.rename(oldkey, newKey);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}

    /** 
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true 
     * @param oldkey 
     * @param newKey 
     * @return 
     */  
	public static boolean renameNX(String oldkey, String newKey) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.renamenx(oldkey, newKey);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
      
    /** 
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true 
     * @param oldkey 
     * @param newKey 
     * @return 
     */  
	public static boolean renameIfNotExists(String oldkey, String newKey) {
		return renameNX(oldkey, newKey);
	}
      
	/**
	 * 返回 key 所储存的值的类型。
	 * 
	 * @param key
	 * @return
	 */
	public static String type(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.type(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
      
    /** 
     * 返回 key 所储存的值的类型。 
     * @param key 
     * @return 
     */  
	public static String getType(String key) {
		return type(key);
	}
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.del(key);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	} 
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean delete(String key) {
		return del(key);
	}
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean remove(String key) {
		return del(key);
	}
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean del(String[] keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.del(keys);
			if (statusCode > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return false;
	}
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean delete(String[] keys) {
		return del(keys);
	} 
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
	public static boolean remove(String[] keys) {
		return del(keys);
	}
	
	/**************************** redis 列表List start***************************/
	/**
	 * 将一个值插入到列表头部，value可以重复，返回列表的长度
	 * @param key
	 * @param value String
	 * @return 返回List的长度
	 */
	public static Long lpush(String key, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.lpush(key.getBytes(), SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return length;
	}
	
	/**
	 * 将一个值插入到列表头部，value可以重复，返回列表的长度
	 * @param key
	 * @param value String
	 * @return 返回List的长度
	 */
	public static Long lpush(String key, String value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return length;
	}
	
	/**
	 * 将多个值插入到列表头部，value可以重复
	 * @param key
	 * @param values String[]
	 * @return 返回List的数量size
	 */
	public static Long lpush(String key, Object[] values) {

		Jedis jedis = null;
		Long size = 0L;
		try {
			jedis = getResource();
			size = jedis.lpush(key.getBytes(), SerializeUtils.serialize(values));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return size;
	}
	
	
	/**
	 * 获取List列表
	 * @param key
	 * @param start long，开始索引
	 * @param end long， 结束索引
	 * @return List<String>
	 */
	public static List<Object> lrange(String key, long start, long end) {

		Jedis jedis = null;
		List<Object> list = new ArrayList<Object>();
		try {
			jedis = getResource();
			List<byte[]> value = jedis.lrange(key.getBytes(), start, end);
			for (byte[] bs : value) {
				list.add(SerializeUtils.deserialize(bs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return list;
	}
	
	/**
	 * 通过索引获取列表中的元素
	 * @param key
	 * @param index，索引，0表示最新的一个元素
	 * @return String
	 */
	public static Object lindex(String key, long index) {
		Object object = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			byte[] value = jedis.lindex(key.getBytes(), index);
			object = SerializeUtils.deserialize(value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return object;
	}
	
	/**
	 * 获取列表长度，key为空时返回0
	 * @param key
	 * @return Long
	 */
	public static Long llen(String key) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.llen(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 在列表的元素前或者后插入元素，返回List的长度
	 * @param key
	 * @param where LIST_POSITION
	 * @param pivot 以该元素作为参照物，是在它之前，还是之后（pivot：枢轴;中心点，中枢;[物]支点，支枢;[体]回转运动。）
	 * @param value
	 * @return Long
	 */
	public static Long linsert(String key, LIST_POSITION where, String pivot, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.linsert(key.getBytes(), where, pivot.getBytes(), SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
	 * @param key
	 * @param value String
	 * @return Long
	 */
	public static Long lpushx(String key, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.lpushx(key.getBytes(), SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
	 * @param key
	 * @param values String[]
	 * @return Long
	 */
	public static Long lpushx(String key, Object[] values){
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.lpushx(key.getBytes(), SerializeUtils.serialize(values));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 移除列表元素，返回移除的元素数量
	 * @param key
	 * @param count，标识，表示动作或者查找方向
	 * <li>当count=0时，移除所有匹配的元素；</li>
	 * <li>当count为负数时，移除方向是从尾到头；</li>
	 * <li>当count为正数时，移除方向是从头到尾；</li>
	 * @param value 匹配的元素
	 * @return Long
	 */
	public static Long lrem(String key, long count, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.lrem(key.getBytes(), count, SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 通过索引设置列表元素的值，当超出索引时会抛错。成功设置返回true
	 * @param key
	 * @param index 索引
	 * @param value
	 * @return boolean
	 */
	public static boolean lset(String key, long index, String value) {
		Jedis jedis = null;
		String statusCode = "";
		try {
			jedis = getResource();
			statusCode = jedis.lset(key.getBytes(), index, SerializeUtils.serialize(value));
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return false;
	}
	
	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * @param key
	 * @param start
	 * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
	 * <li>如果start大于end，则返回一个空的列表，即列表被清空</li>
	 * @param end
	 * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
	 * <li>可以超出索引，不影响结果</li>
	 * @return boolean
	 */
	public static boolean ltrim(String key, long start, long end) {
		Jedis jedis = null;
		String statusCode = "";
		try {
			jedis = getResource();
			statusCode = jedis.ltrim(key.getBytes(), start, end);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return false;
	}
	
	/**
	 * 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return String
	 */
	public static Object lpop(String key) {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = getResource();
			byte[] tempValue= jedis.lpop(key.getBytes());
			value = SerializeUtils.deserialize(tempValue);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return value;
	}
	
	/**
	 * 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
	 * @param key
	 * @return String
	 */
	public static Object rpop(String key) {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = getResource();
			if (isExists(key)) {
				byte[] tempValue = jedis.rpop(key.getBytes());
				if (tempValue != null && tempValue.length > 0) {
					value = SerializeUtils.deserialize(tempValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return value;
	}
	
	/**
	 * 在列表中的尾部添加一个个值，返回列表的长度
	 * @param key
	 * @param value
	 * @return Long
	 */
	public static Long rpush(String key, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.rpush(key.getBytes(),SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return length;
	}
	
	/**
	 * 在列表中的尾部添加多个值，返回列表的长度
	 * @param key
	 * @param values
	 * @return Long
	 */
	public static Long rpush(String key, Object[] values) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.rpush(key.getBytes(), SerializeUtils.serialize(values));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return length;
	}
	
	/**
	 * 仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
	 * @param key
	 * @param value
	 * @return Long
	 */
	public static Long rpushx(String key, Object value) {
		Jedis jedis = null;
		Long length = 0L;
		try {
			jedis = getResource();
			length = jedis.rpushx(key.getBytes(), SerializeUtils.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return length;
	}
	
	/**
	 * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 * @param sourceKey 源列表的key，当源key不存在时，结果返回Null
	 * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
	 * @return String
	 */
	public static Object rpopLpush(String sourceKey, String targetKey) {

		Jedis jedis = null;
		Object value = null;
		try {
			jedis = getResource();
			byte[] tempValue= jedis.rpoplpush(sourceKey.getBytes(), targetKey.getBytes());
			value = SerializeUtils.deserialize(tempValue);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return value;
	}
	
	
	/**
	 * 移出并获取列表的【最后一个元素】， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
	 * @param timeout 单位为秒
	 * @param keys
	 * <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
	 * <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
	 * <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
	 * @return List<String>
	 */
	public static Object brpop(String key, int timeout) {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = getResource();
			if (null != jedis.brpop(timeout, key)) {
				value = jedis.brpop(timeout, key).get(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return value;
	}
	
	/**
	 * 从列表中弹出列表最后一个值，将弹出的元素插入到另外一个列表中并返回它； 
	 * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
	 * @param sourceKey 源列表的key，当源key不存在时，则会进行阻塞
	 * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
	 * @param timeout 单位为秒
	 * @return String
	 */
	public static Object brpopLpush(String sourceKey, String targetKey, int timeout) {

		Jedis jedis = null;
		String value = null;
		try {
			jedis = getResource();
			value = jedis.brpoplpush(sourceKey, targetKey, timeout);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return value;
	}
	
	/**************************** redis 列表List end***************************/
	
	/***Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。***/
	
	/**
	 * 设置Hash的属性
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean hset(String key, String field, String value) {
		if (key.isEmpty() || null == field) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			// If the field already exists, and the HSET just produced an update
			// of the value, 0 is returned,
			// otherwise if a new field is created 1 is returned.
			Long statusCode = jedis.hset(key, field, value);
			if (statusCode > -1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return false;
	}
	
	/**
	 * 批量设置Hash的属性
	 * @param key
	 * @param fields
	 * @param values
	 * @return
	 */
	public static boolean hmset(String key, String[] fields, String[] values) {
		if (key.isEmpty() || fields.length == 0 || values.length == 0) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			Map<String, String> hash = new HashMap<String, String>(16);
			for (int i = 0; i < fields.length; i++) {
				hash.put(fields[i], values[i]);
			}
			String statusCode = jedis.hmset(key, hash);
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**
	 * 批量设置Hash的属性
	 * @param key
	 * @param map Map<String, String>
	 * @return
	 */
	public static boolean hmset(String key, Map<String, String> map) {
		if (key.isEmpty() || map.isEmpty()) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			String statusCode = jedis.hmset(key, map);
			jedis.close();
			if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 
		return false;
	}
	
	/**
	 * 仅当field不存在时设置值，成功返回true
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean hsetNX(String key, String field, String value) {
		if (key.isEmpty() || field.isEmpty()) {
			return false;
		}

		Jedis jedis = null;
		try {
			jedis = getResource();
			// If the field already exists, 0 is returned,
			// otherwise if a new field is created 1 is returned.
			Long statusCode = jedis.hsetnx(key, field, value);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 

		return false;
	}
	
	/**
	 * 获取属性的值
	 * @param key
	 * @param field
	 * @return
	 */
	public static String hget(String key, String field) {
		if (key.isEmpty() || field.isEmpty()) {
			return null;
		}
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		} 

		return value;
	}
	
	/**
	 * 批量获取属性的值
	 * @param key
	 * @param fields String...
	 * @return
	 */
	public static List<String> hmget(String key, String... fields) {
		if (key.isEmpty() || fields.length == 0) {
			return null;
		}
		Jedis jedis = null;
		List<String> values = null;
		try {
			jedis = getResource();
			values = jedis.hmget(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return values;
	}
	
	/**
	 * 获取在哈希表中指定 key 的所有字段和值
	 * @param key
	 * @return Map<String, String>
	 */
	public static Map<String, String> hgetAll(String key) {
		if (key.isEmpty()) {
			return null;
		}

		Jedis jedis = null;
		Map<String, String> map = null;

		try {
			jedis = getResource();
			map = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return map;
	}
	
	/**
	 * 删除hash的属性
	 * @param key
	 * @param fields
	 * @return
	 */
	public static boolean hdel(String key, String... fields) {
		if (key.isEmpty() || null == fields) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.hdel(key, fields);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return true;
	}
	
	/**
	 * 查看哈希表 key 中，指定的字段是否存在。
	 * @param key
	 * @param field
	 * @return
	 */
	public static boolean hexists(String key, String field) {
		if (key.isEmpty() || field.isEmpty()) {
			return false;
		}
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = getResource();
			result = jedis.hexists(key, field);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return result;
	}
	
	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment 。
	 * @param key
	 * @param field
	 * @param increment 正负数、0、正整数
	 * @return
	 */
	public static long hincrBy(String key, String field, long increment) {
		Jedis jedis = null;
		long result = 0L;

		try {
			jedis = getResource();
			result = jedis.hincrBy(key, field, increment);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return result;
	}
	
	/**
	 * 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。(注：如果field不存在时，会设置新的值)
	 * @param key
	 * @param field
	 * @param increment，可以为负数、正数、0
	 * @return
	 */
	public static Double hincrByFloat(String key, String field, double increment) {
		Jedis jedis = null;
		Double result = 0D;
		try {
			jedis = getResource();
			result = jedis.hincrByFloat(key, field, increment);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return result;
	}
	
	/**
	 * 获取所有哈希表中的字段
	 * @param key
	 * @return Set<String>
	 */
	public static Set<String> hkeys(String key) {
		Jedis jedis = null;
		Set<String> result = null;
		try {
			jedis = getResource();
			result = jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return result;
	}
	
	/**
	 * 获取哈希表中所有值
	 * @param key
	 * @return List<String>
	 */
	public static List<String> hvals(String key) {
		List<String> result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hvals(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return result;
	}
	
	/**
	 * 获取哈希表中字段的数量，当key不存在时，返回0
	 * @param key
	 * @return
	 */
	public static Long hlen(String key) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hlen(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return result;
	}
	
	/**
	 * 迭代哈希表中的键值对。
	 * @param key
	 * @param cursor
	 * @return ScanResult<Entry<String, String>>
	 */
	public static ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		Jedis jedis = null;
		ScanResult<Entry<String, String>> scanResult = null;

		try {
			jedis = getResource();
			scanResult = jedis.hscan(key, cursor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}

		return scanResult;
	}
	/**************************** redis Hash end***************************/
	
	/**************************** redis 集合Set start***************************/
	/**Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。**/
	
	/**
	 * 向集合添加一个或多个成员，返回添加成功的数量
	 * @param key
	 * @param members
	 * @return Long
	 */
	public static Long sadd(String key, String... members) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 获取集合的成员数
	 * @param key
	 * @return
	 */
	public static Long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 返回集合中的所有成员
	 * @param key
	 * @return Set<String>
	 */
	public static Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 判断 member 元素是否是集合 key 的成员，在集合中返回True
	 * @param key
	 * @param member
	 * @return Boolean
	 */
	public static Boolean sIsMember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 返回给定所有集合的差集（获取第一个key中与其它key不相同的值，当只有一个key时，就返回这个key的所有值）
	 * @param keys
	 * @return Set<String>
	 */
	public static Set<String> sdiff(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.sdiff(keys);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 返回给定所有集合的差集并存储在 targetKey中，类似sdiff，只是该方法把返回的差集保存到targetKey中
	 * <li>当有差集时，返回true</li>
	 * <li>当没有差集时，返回false</li>
	 * @param targetKey
	 * @param keys
	 * @return
	 */
	public static boolean sdiffStore(String targetKey, String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.sdiffstore(targetKey, keys);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**
	 * 返回给定所有集合的交集（获取第一个key中与其它key相同的值，要求所有key都要有相同的值，如果没有相同，返回Null。当只有一个key时，就返回这个key的所有值）
	 * @param keys
	 * @return Set<String>
	 */
	public static Set<String> sinter(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.sinter(keys);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 返回给定所有集合的交集并存储在 targetKey中，类似sinter
	 * @param targetKey
	 * @param keys
	 * @return boolean
	 */
	public static boolean sinterStore(String targetKey, String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long statusCode = jedis.sinterstore(targetKey, keys);
			if (SUCCESS_STATUS_LONG.equals(statusCode)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**
	 * 将 member 元素从 sourceKey 集合移动到 targetKey 集合
	 * <li>成功返回true</li>
	 * <li>当member不存在于sourceKey时，返回false</li>
	 * <li>当sourceKey不存在时，也返回false</li>
	 * @param sourceKey
	 * @param targetKey
	 * @param member
	 * @return boolean
	 */
	public static boolean smove(String sourceKey, String targetKey, String member) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Long value = jedis.smove(sourceKey, targetKey, member);
			if (value > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**
	 * 移除并返回集合中的一个随机元素
	 * <li>当set为空或者不存在时，返回Null</li>
	 * @param key
	 * @return String
	 */
	public static String spop(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.spop(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 返回集合中一个或多个随机数
	 * <li>当count大于set的长度时，set所有值返回，不会抛错。</li>
	 * <li>当count等于0时，返回[]</li>
	 * <li>当count小于0时，也能返回。如-1返回一个，-2返回两个</li>
	 * @param key
	 * @param count
	 * @return List<String>
	 */
	public static List<String> srandMember(String key, int count) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.srandmember(key, count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return null;
	}
	
	/**
	 * 移除集合中一个或多个成员
	 * @param key
	 * @param members
	 * @return
	 */
	public static boolean srem(String key, String... members) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// Integer reply, specifically: 1 if the new element was removed
			// 0 if the new element was not a member of the set
			Long value = jedis.srem(key, members);
			if (value > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**
	 * 返回所有给定集合的并集，相同的只会返回一个
	 * @param keys
	 * @return
	 */
	public static Set<String> sunion(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.sunion(keys);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		
		return null;
	}
	
	/**
	 * 所有给定集合的并集存储在targetKey集合中
	 * <li>注：合并时，只会把keys中的集合返回，不包括targetKey本身</li>
	 * <li>如果targetKey本身是有值的，合并后原来的值是没有的，因为把keys的集合重新赋值给targetKey</li>
	 * <li>要想保留targetKey本身的值，keys要包含原来的targetKey</li>
	 * @param targetKey
	 * @param keys
	 * @return
	 */
	public static boolean sunionStore(String targetKey, String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 返回合并后的长度
			Long statusCode = jedis.sunionstore(targetKey, keys);
			if (statusCode > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnRource(jedis);
		}
		return false;
	}
	
	/**************************** redis 集合Set end***************************/
}