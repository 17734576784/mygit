package com.iot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iot.IotserviceApplication;
import com.iot.utils.JedisUtils;
/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {IotserviceApplication.class })
public class JUtilTest {


	@Test
	public void StrategyTest() {
		JedisUtils.set("a", 111, 120);
//		try {
//			Thread.sleep(50000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		long expireTime = JedisUtils.getExpire("a");
		System.out.println("expireTime ：" + expireTime);
		
		JedisUtils.set("a", 222, expireTime);
//		
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		expireTime = JedisUtils.getExpire("a");
		System.out.println("expireTime ：" + expireTime);
		
	}
}
