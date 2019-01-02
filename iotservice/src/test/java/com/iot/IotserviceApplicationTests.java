package com.iot;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.iot.utils.JedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotserviceApplicationTests {

	@Test
	public void contextLoads() throws FileNotFoundException {
		
		File file = ResourceUtils.getFile("classpath:cert/ca.jks");
		System.out.println(file.getAbsolutePath());
//        if(file.exists()){
//            File[] files = file.listFiles();
//            if(files != null){
//                for(File childFile:files){
//                    System.out.println(childFile.getName());
//                }
//            }
//        }

//		JedisUtils.set("a", 111, 120);
//		try {
//			Thread.sleep(50000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		long expireTime = JedisUtils.getExpire("a");
//		System.out.println("expireTime ：" + expireTime);
//		
//		JedisUtils.set("a", 222, expireTime);
//		
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		expireTime = JedisUtils.getExpire("a");
//		System.out.println("expireTime ：" + expireTime);
	}

}
