package com.ke;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ke.mapper.PileparaMapper;
import com.ke.model.PileparaKey;
import com.ke.service.IShiroService;
import com.ke.utils.JedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KechargeApplicationTests {

	@Resource
	private IShiroService shiroService;
	@Test
	public void contextLoads() {
//        String password = shiroService.getPasswordByUserName("test");
//        System.out.println("password : "+ password);
		
		JedisUtil.set("a", "123");
		System.out.println(JedisUtil.get("a"));
		JedisUtil.del("a");
		
	}

}

