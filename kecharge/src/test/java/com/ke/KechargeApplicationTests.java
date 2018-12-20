package com.ke;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ke.mapper.PileparaMapper;
import com.ke.model.PileparaKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KechargeApplicationTests {

	@Resource
	private PileparaMapper pileparaMapper;
	@Test
	public void contextLoads() {
		PileparaKey key = new PileparaKey();
		key.setStationId(2);
		key.setId((short)1);
		System.out.println(pileparaMapper.selectByPrimaryKey(key).toString());	
	}

}

