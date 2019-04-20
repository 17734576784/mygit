/**   
* @Title: BatteryService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月9日 下午2:31:53 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.Eve;
import com.nb.model.NbBattery;
import com.nb.model.jd.Battery;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: BatteryService 
* @Description: 竟达电池服务 
* @author dbr
* @date 2019年4月9日 下午2:31:53 
*  
*/
@Service
public class BatteryService implements IServiceStrategy {
	
	
	@Resource
	private CommonMapper commonMapper;
	

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String, java.util.Map) 
	*/
	
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub
		String logInfo = "上报竟达电池服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		if (serviceMap == null || serviceMap.isEmpty()) {
			return;
		}
		
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			if (dataMap == null) {
				return;
			}

			Battery battery = JsonUtil.map2Bean(dataMap, Battery.class);
			battery.setEvnetTime(serviceMap.get("eventTime"));
			
			Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			int rtuId = toInt(meterInfo.get("rtuId"));
			int mpId = toInt(meterInfo.get("mpId"));
			
			// 电池电压告警
			if (battery.isAlarm()) {
				insertEve(battery, rtuId, mpId);
			} else { // 正常上报电池电压
				insertBattery(battery, rtuId, mpId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.CALLBACK).error(logInfo + "异常" + e.getMessage());
		}
		
	}
	
	/** 
	* @Title: insertEve 
	* @Description: 插入电池电压告警 
	* @param @param battery
	* @param @param rtuId
	* @param @param mpId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(Battery battery, int rtuId, int mpId) throws Exception{
		Eve eve = new Eve();
		eve.setTableName(toStr(battery.getDate() / 100));
		eve.setYmd(battery.getDate());
		eve.setHmsms(battery.getTime() * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(Constant.ALARM_2005);
		eve.setCharInfo("电池告警，电压值为：" + battery.getBatteryVoltage() + ",电压阀值为： " + battery.getBatteryvoltageThreshold());
		
		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}

	/** 
	* @Title: insertBattery 
	* @Description: 插入电池电压上报信息  nb_battery_200808
	* @param @param battery
	* @param @param rtuId
	* @param @param mpId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertBattery(Battery battery, int rtuId, int mpId) throws Exception{
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(toStr(battery.getDate() / 100));
		nbBattery.setYmd(battery.getDate());
		nbBattery.setHms(battery.getTime());
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(battery.getBatteryVoltage());
		
		JedisUtils.lpush(Constant.HISTORY_BATTERY_QUEUE, JsonUtil.jsonObj2Sting(nbBattery));
	}

}
