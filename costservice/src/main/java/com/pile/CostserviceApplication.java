package com.pile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.pile.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class CostserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostserviceApplication.class, args);
	}
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 创建装换对象
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		// 创建配置文件对象
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(config);

		return new HttpMessageConverters(converter);
	}
}
