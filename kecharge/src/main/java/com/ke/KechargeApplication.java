package com.ke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KechargeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KechargeApplication.class, args);
	}

}

