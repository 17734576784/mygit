package com.iot.test;


import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iot.IotserviceApplication;
import com.iot.utils.Log4jUtils;

/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {IotserviceApplication.class})
public class JUtilTest{

  
	@Test
	public void StrategyTest(){
		Log4jUtils.getError().error("dfds");
	}	
	
	
	
}
