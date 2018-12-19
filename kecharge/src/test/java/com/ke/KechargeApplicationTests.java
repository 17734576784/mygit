package com.ke;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ke.model.LogEnum;
import com.ke.utils.JedisUtils;
import com.ke.utils.LoggerUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KechargeApplicationTests {

	@Test
	public void contextLoads() {
		JedisUtils.set("a", "23232");
		System.out.println(JedisUtils.get("a"));
		JedisUtils.del("a");
	}

}

