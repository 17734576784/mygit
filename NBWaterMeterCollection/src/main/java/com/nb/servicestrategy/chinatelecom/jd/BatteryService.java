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
import com.nb.mapper.EveMapper;
import com.nb.mapper.NbBatteryMapper;
import com.nb.model.Eve;
import com.nb.model.NbBattery;
import com.nb.model.jd.Battery;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
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
	private NbBatteryMapper nbBatteryMapper;
	
	@Resource
	private CommonMapper commonMapper;
	
	@Resource
	private EveMapper eveMapper;

	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String, java.util.Map) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub
		String logInfo = "上报竟达电池服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.Logger(LogName.CALLBACK).info(logInfo);

		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
		if (dataMap == null) {
			return;
		}

		String evnetTime = serviceMap.get("evnetTime");
		int date = toInt(evnetTime.substring(0, 8));
		int time = toInt(evnetTime.substring(9, 15)) + 80000;

		Battery battery = JsonUtil.map2Bean(dataMap, Battery.class);

		Map<String, Object> meterInfo = this.commonMapper.getNbInfoByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));
		String YM = toStr(date / 100);
		// 电池电压告警
		if (battery.isAlarm()) {
			insertEve(YM, date, time, battery, rtuId, mpId);
		} else { // 正常上报电池电压
			insertBattery(YM, date, time, battery, rtuId, mpId);
		}
	}
	
	/** 
	* @Title: insertEve 
	* @Description: 插入电池电压告警
	* @param @param YM
	* @param @param date
	* @param @param time
	* @param @param battery    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(String YM, int date, int time, Battery battery, int rtuId, int mpId) {
		Eve eve = new Eve();
		eve.setTableName(YM);
		eve.setYmd(date);
		eve.setHmsms(time * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(Constant.ALARM_2005);
		eve.setCharInfo("电池告警，电压值为：" + battery.getBatteryVoltage() + ",电压阀值为： " + battery.getBatteryvoltageThreshold());
		eveMapper.insertEve(eve);
	}

	/** 
	* @Title: insertBattery 
	* @Description: 插入电池电压上报信息  nb_battery_200808
	* @param @param YM
	* @param @param date
	* @param @param time
	* @param @param battery    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertBattery(String YM, int date, int time, Battery battery, int rtuId, int mpId) {
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(YM);
		nbBattery.setYmd(date);
		nbBattery.setHms(time);
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(battery.getBatteryVoltage());
		if (null == nbBatteryMapper.getNbBattery(nbBattery)) {
			nbBatteryMapper.insertNbBattery(nbBattery);
		}
	}

}
