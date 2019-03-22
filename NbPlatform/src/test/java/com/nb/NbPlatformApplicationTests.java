package com.nb;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nb.service.chinamobile.ChinaMobileCommandService;
import com.nb.utils.CommFunc;
import com.nb.utils.Constant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbPlatformApplication.class)
@PropertySource({"classpath:config.properties" })
public class NbPlatformApplicationTests {

	@Resource
	private ChinaMobileCommandService chinaMobileCommandService;
	
	 
	@Test
	public void contextLoads() throws Exception {
//		JSONObject json = new JSONObject();
//		System.out.println(chinaMobileCommandService.asynCommand(json));
//		System.out.println(CommFunc.getCommandType(Constant.CHINA_TELECOM, Constant.COMMAND_TYPE_CAMERA).get("serviceId"));
	}

}

