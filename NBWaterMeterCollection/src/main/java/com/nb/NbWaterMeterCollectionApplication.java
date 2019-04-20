package com.nb;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** 
* @ClassName: NbWaterMeterCollectionApplication 
* @Description: NB水表采集启动类
* @author dbr
* @date 2019年4月18日 下午4:34:05 
*  
*/
@SpringBootApplication
@EnableTransactionManagement
@PropertySource({ "classpath:config.properties" })
public class NbWaterMeterCollectionApplication {
	
	@Value("${http.port}")
	private Integer port;

	public static void main(String[] args) {
		SpringApplication.run(NbWaterMeterCollectionApplication.class, args);
	}


	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		// 配置http
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}

	/** 
	* @Title: createStandardConnector 
	* @Description: 配置http
	* @param @return    设定文件 
	* @return Connector    返回类型 
	* @throws 
	*/
	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(port);
		return connector;
	}
}
