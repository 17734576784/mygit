package com.nb;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.nb.service.chinamobile.ChinaMobileCommandService;
/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {NbPlatformApplication.class })
public class JUtilTest {

	@Autowired
	private ChinaMobileCommandService chinaMobileCommandService;
	 
	@Test
	public void contextLoads() throws Exception {
		JSONObject json = new JSONObject();
		System.out.println(chinaMobileCommandService.asynCommand(json));
	}

}
