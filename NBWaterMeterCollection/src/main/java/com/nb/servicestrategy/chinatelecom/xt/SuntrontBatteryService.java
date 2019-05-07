/**   
* @Title: SuntrontBatteryService.java 
* @Package com.nb.servicestrategy.chinatelecom.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午3:46:21 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.xt;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbBattery;
import com.nb.model.xt.SuntrontBattery;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: SuntrontBatteryService 
* @Description: 新天电池服务上报数据
* @author dbr
* @date 2019年5月6日 下午3:46:21 
*  
*/
@Service
public class SuntrontBatteryService implements IServiceStrategy {
	
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
		String logInfo = "上报新天科技电池服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
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

			SuntrontBattery battery = JsonUtil.map2Bean(dataMap, SuntrontBattery.class);
			battery.setEvnetTime(serviceMap);
		
			insertBattery(battery, deviceId);

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + ",异常:" + e.getMessage());
		}
		
	}


	/** 
	* @Title: insertBattery 
	* @Description:  插入电池电压上报信息  nb_battery_200808 
	* @param @param battery
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertBattery(SuntrontBattery battery, String deviceId) throws Exception {
		
		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = ConverterUtils.toInt(meterInfo.get("rtuId"));
		int mpId =  ConverterUtils.toInt(meterInfo.get("mpId"));
		
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(toStr(battery.getEventDate() / 100));
		nbBattery.setYmd(battery.getEventDate());
		nbBattery.setHms(battery.getEventTime());
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(battery.getBatteryVoltage());

		JedisUtils.lpush(Constant.HISTORY_BATTERY_QUEUE, JsonUtil.jsonObj2Sting(nbBattery));
	}

}
