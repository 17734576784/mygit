package com.iot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iot.utils.JedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotserviceApplicationTests {

	@Test
	public void contextLoads() {
		JedisUtils.set("a", 111, 120);
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long expireTime = JedisUtils.getExpire("a");
		System.out.println("expireTime ：" + expireTime);
		
		JedisUtils.set("a", 222, expireTime);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expireTime = JedisUtils.getExpire("a");
		System.out.println("expireTime ：" + expireTime);
	}

}
