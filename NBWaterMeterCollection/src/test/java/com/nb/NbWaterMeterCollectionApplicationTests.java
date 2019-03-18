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
import com.nb.mapper.RtuparaMapper;
import com.nb.model.Rtupara;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbWaterMeterCollectionApplication.class)
@PropertySource({"classpath:config.properties" })
public class NbWaterMeterCollectionApplicationTests {

	@Resource
	private RtuparaMapper rtuparaMapper;
	
	@Test
	public void contextLoads() {
		try {
//			PageHelper.startPage(1, 0);
//		    List<Rtupara> list = rtuparaMapper.listRtupara();
//		    PageInfo<Rtupara> pageinfo = new PageInfo<>(list);
//		    System.out.println(pageinfo.getTotal() +"  "+ pageinfo.getPages());
//			System.out.println(list.size());
//		    for (Rtupara rtupara : list) {
//				System.out.println(rtupara.toString());
//			}
			for (int i = 0; i < 800000; i++) {
				Rtupara rtupara = new Rtupara();
				rtupara.setId(i);
				rtupara.setDescribe(i+"");
				rtupara.setAreaCode("2222");
				rtupara.setAssetNo("MX234234234234");
				
				JedisUtils.setByByte(rtupara.getDescribe(), rtupara);
			}
		    System.out.println("执行完成");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
