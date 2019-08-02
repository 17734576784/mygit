package com.dbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqRabbitConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqRabbitConsumerApplication.class, args);
	}

}
