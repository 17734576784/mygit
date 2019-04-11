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

	// 配置http
	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(port);
		return connector;
	}
}
