package com.nb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbPlatformApplicationTests.class)
@PropertySource({"classpath:config.properties" })
public class NbPlatformApplicationTests {

	@Value("#{${blog-top-links}}")
    private Map<String, String> topLinks;
	
	@Test
	public void contextLoads() {
		System.out.println(topLinks);
		System.out.println(11);
	}

}

