package com.nb;



import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.NbCommandMapper;
import com.nb.mapper.NbDailyDataMapper;
import com.nb.mapper.ScheduleJobMapper;
import com.nb.model.NbCommand;
import com.nb.model.NbCommandKey;
import com.nb.model.NbDailyData;
import com.nb.model.ScheduleJob;
import com.nb.service.IScheduleService;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbWaterMeterCollectionApplication.class)
@PropertySource({"classpath:config.properties" })
public class NbWaterMeterCollectionApplicationTests {

	
	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	
	@Resource
	private IScheduleService scheduleService;
	
	@Resource
	private NbCommandMapper nbCommandMapper;
	

	@Resource
	private NbDailyDataMapper nbDailyDataMapper;
	@Test
	public void contextLoads() {
		NbDailyData key = new NbDailyData();
		key.setTableName("yddata.dbo.nb_daily_data_200808");
		key.setRtuId(1);
		key.setMpId((short)1);
		key.setYmd(0);
		key.setHms(0);
		System.out.println(nbDailyDataMapper.getNbDailyData(key).getDailyMaxVelocity());
//		ScheduleJob scheduleJob = new ScheduleJob();
//		scheduleJob.setCronExpression("");
//		scheduleJob.setJobGroup("task");
//		scheduleJob.setJobName("task2");
//		scheduleJob.setJobStatus((byte) 0);
//		scheduleJob.setQuartzClass("");
//		scheduleJobMapper.insertSelective(scheduleJob);

	}

}
