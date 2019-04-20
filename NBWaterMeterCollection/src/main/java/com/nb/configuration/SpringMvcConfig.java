/**   
* @Title: SpringMVCConfig.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月19日 上午10:27:01 
* @version V1.0   
*/
package com.nb.configuration;

import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/** 
* @ClassName: SpringMVCConfig 
* @Description: SpringMVC配置类 
* @author dbr
* @date 2018年12月19日 上午10:27:01 
*  
*/
@SpringBootConfiguration
public class SpringMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureMessageConverters(converters);
		/**
		 * 1.需要先定义一个convert 转换消息的对象； 2.添加fastjson 的配置信息，比如：是否要转换格式化返回的json数据；
		 * 3.在convert中添加配置信息 4.将convert添加到converters当中
		 */
		// 1.需要先定义一个convert 转换消息的对象；
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 2.添加fastjson 的配置信息，比如：是否要转换格式化返回的json数据；
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		// 3.在convert中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);
		// 4.将convert添加到converters当中
		converters.add(fastConverter);
	}


//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		// 开启路径后缀匹配
//		WebMvcConfigurer.super.configurePathMatch(configurer);
//		configurer.setUseRegisteredSuffixPatternMatch(true);
//		// setUseSuffixPatternMatch 后缀模式匹配
//		configurer.setUseSuffixPatternMatch(true);
//		// setUseTrailingSlashMatch 自动后缀路径模式匹配
//		configurer.setUseTrailingSlashMatch(true);
//	}
}
