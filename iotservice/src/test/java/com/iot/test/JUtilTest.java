package com.iot.test;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.iot.IotserviceApplication;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.servicestrategy.CheckService;
import com.iot.utils.Constant;
import com.iot.utils.FileUtils;
import com.iot.utils.JedisUtils;
import com.iot.utils.UpGradeUtil;
/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {IotserviceApplication.class })
public class JUtilTest {

	@Autowired
	private CheckService checkService; 
	@Test
	public void StrategyTest() throws Exception {
		System.out.println(FileUtils.parseUpgradeFile("a.bin_1.00", "C:\\softversion\\a.bin", 256));;
		checkService.loadUpgradeFile("81a8a1e9-0870-48e1-ad7a-57ad16e5b0d1", "a.bin", "1.00");
		
		JSONObject param= new JSONObject();
		param.put("value", 0);
		param.put("version", "1.00");
		
		/**下发询问设备是否升级命令*/
		JSONObject command = new JSONObject();
		command.put("deviceId", "81a8a1e9-0870-48e1-ad7a-57ad16e5b0d1");
		command.put("serviceId",Constant.UPVERSIONSERVICE);
		command.put("method",Constant.UPVERSION);	
		command.put("param", param.toString());
		UpGradeUtil.asynCommand(command.toString());
		
		
//		JSONObject upgradeFile = (JSONObject) JedisUtils.get("a.bin_1.00");
//		String command = UpGradeUtil.getCommandParam("5e81d9d4-a8f1-49f6-885f-3f02d60769e2", "a.bin_1.00", (short)101, (short)1, upgradeFile);
//		if (null == command || command.isEmpty()) {
////			LoggerUtils.Logger(LogName.CALLBACK).info("组建命令参数失败：" + commandMap);
//			return;
//		}
//		UpGradeUtil.asynCommand(command.toString());
//		System.out.println("在设备：" + deviceId + "发送升级命令成功，" + command);
	}
}
