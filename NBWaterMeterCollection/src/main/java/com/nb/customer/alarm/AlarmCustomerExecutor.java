/**   
* @Title: AlarmCustomerExecutor.java 
* @Package com.nb.customer.alarm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午4:06:07 
* @version V1.0   
*/
package com.nb.customer.alarm;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.EveMapper;
import com.nb.model.Eve;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: AlarmCustomerExecutor 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 下午4:06:07 
*  
*/
@Component
public class AlarmCustomerExecutor {
	
	@Resource
	private EveMapper eveMapper;

	public boolean saveAlarmEvent(Object obj) {
		boolean flag = true;
		try {
			Eve eve = JsonUtil.convertJsonStringToObject(obj.toString(), Eve.class);
			flag = eveMapper.insertEve(eve);
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.ALARM_EVENT_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			e.printStackTrace();
			LoggerUtil.Logger(LogName.ERROR).info(obj.toString() + "存库失败");
		}
		return flag;
	}
}
