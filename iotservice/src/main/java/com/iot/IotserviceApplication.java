package com.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "classpath:config.properties" })
public class IotserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(IotserviceApplication.class, args);
	}

}
