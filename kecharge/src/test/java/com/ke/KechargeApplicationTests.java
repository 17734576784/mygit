package com.ke;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ke.mapper.PileparaMapper;
import com.ke.model.LogEnum;
import com.ke.model.PileparaKey;
import com.ke.utils.JedisUtils;
import com.ke.utils.LoggerUtils;

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

