package com.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class NettyserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyserverApplication.class, args);
	}
}
