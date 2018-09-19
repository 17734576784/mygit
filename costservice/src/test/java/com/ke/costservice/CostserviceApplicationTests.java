package com.ke.costservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pile.utils.JedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostserviceApplicationTests {

	@Autowired
	private JedisUtils jedisUtils;
	@Test
	public void contextLoads() {
		jedisUtils.set("dbr", "dfdads");
		System.out.println(jedisUtils.get("dbr"));
		
	}

}
