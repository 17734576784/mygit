package com.ke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KechargeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KechargeApplication.class, args);
	}

	/**
	  * 设置匹配*.json后缀请求
     * @param dispatcherServlet
     * @return
     */
//    @Bean
//    public ServletRegistrationBean<DispatcherServlet> servletRegistrationBean(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
//        servletServletRegistrationBean.addUrlMappings("*.html");
//        return servletServletRegistrationBean;
//    }
}
