/**   
* @Title: RedisConfig.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月19日 上午10:17:25 
* @version V1.0   
*/
package com.ke.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ke.utils.JedisUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
* @ClassName: RedisConfig 
* @Description: redis配置类 
* @author dbr
* @date 2018年12月19日 上午10:17:25 
*/
@Configuration
@ConditionalOnClass(JedisUtil.class)
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.database}")
	private int database;
	
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.jedis.pool.max-active}")
	private int maxActive;
	
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	 
	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Bean(name = "jedisPool")
	public JedisPool jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxActive);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(timeout);
		return new JedisPool(config, host, port,timeout, password,database);
	}

//	@Bean
//	@ConditionalOnMissingBean(JedisUtils.class)
//	public JedisUtils redisClient(@Qualifier("jedisPool") JedisPool pool) {
//		LoggerUtils.Logger(LogEnum.INFO).info("初始化……Redis Client==Host={},Port={}", host, port);
//		JedisUtils jedisUtils = new JedisUtils();
//		jedisUtils.setJedisPool(pool);
//		return jedisUtils;
//	}

	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
}