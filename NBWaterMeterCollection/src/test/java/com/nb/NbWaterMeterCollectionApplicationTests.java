package com.nb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbWaterMeterCollectionApplicationTests {

	@Test
	public void contextLoads() {
		LoggerUtil.Logger(LogName.CALLBACK).info("11111");
		LoggerUtil.Logger(LogName.INFO).info("2222");
		LoggerUtil.Logger(LogName.ERROR).error("3333");

	}

}
