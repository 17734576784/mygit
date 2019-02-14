/**   
* @Title: CommandConfig.java 
* @Package com.nb.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年2月14日 上午9:04:27 
* @version V1.0   
*/
package com.nb.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/** 
* @ClassName: CommandConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年2月14日 上午9:04:27 
*  
*/
@Configuration
@ConfigurationProperties(prefix = "data")
@PropertySource("classpath:config.properties")
public class CommandConfig {
	

}
