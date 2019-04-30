/**   
* @Title: ShiroConfiguration.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月20日 上午11:26:35 
* @version V1.0   
*/
package com.ke.configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.ke.shiro.CustomSessionManager;
import com.ke.shiro.MyShiroPermFilter;
import com.ke.shiro.MyShiroRealm;
import com.ke.shiro.RedisCacheManager;
import com.ke.shiro.RedisSessionDao;
import com.ke.mapper.ShiroMapper;

/**
 * @ClassName: ShiroConfiguration
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月20日 上午11:26:35
 * 
 */
@Configuration
public class ShiroConfiguration {

	@Resource
	private ShiroMapper shiroMapper;

	@Bean
	public MyShiroPermFilter myShiroPermFilter() {
		MyShiroPermFilter myShiroPermFilter = new MyShiroPermFilter();
		return myShiroPermFilter;
	}

	// 将自己的验证方式加入容器
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		return redisCacheManager;
	}

	@Bean
	public RedisSessionDao redisSessionDao() {
		RedisSessionDao redisSessionDao = new RedisSessionDao();
		return redisSessionDao;
	}

	@Bean
	public CustomSessionManager customSessionManager() {
		CustomSessionManager customSessionManager = new CustomSessionManager();
		customSessionManager.setSessionDAO(redisSessionDao());
		return customSessionManager;
	}

	// 权限管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setSessionManager(customSessionManager());
		securityManager.setCacheManager(redisCacheManager());
		return securityManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(proxy);
		return filterRegistrationBean;
	}

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		/** 自定义过滤器 */
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("perms", myShiroPermFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		// URL过滤
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 静态资源
		filterChainDefinitionMap.put("/jsp/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/components/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");


		filterChainDefinitionMap.put("/error.json", "anon");
		filterChainDefinitionMap.put("/login.json", "anon");
		filterChainDefinitionMap.put("/manage/**", "anon");
		filterChainDefinitionMap.put("/pile/**", "anon");


		List<Map<String, String>> list = shiroMapper.listUrlPermission();
		for (Map<String, String> map : list) {
			filterChainDefinitionMap.put(map.get("url"), "perms[" + map.get("permission") + "]");
		}
		// 对所有用户认证
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// 登录
		shiroFilterFactoryBean.setLoginUrl("/error.json");
		// 首页
		// shiroFilterFactoryBean.setSuccessUrl("/index.html");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error.json");
		return shiroFilterFactoryBean;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
