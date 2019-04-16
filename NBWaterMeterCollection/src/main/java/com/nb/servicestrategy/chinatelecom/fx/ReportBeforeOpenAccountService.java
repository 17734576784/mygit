/**   
* @Title: ReportBeforeOpenAccountService.java 
* @Package com.nb.servicestrategy.chinatelecom.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 下午4:24:35 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.fx;

import static com.nb.utils.ConverterUtils.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbBattery;
import com.nb.model.NbDailyData;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ReportBeforeOpenAccountService 
* @Description: 开户前数据上报内容 
* @author dbr
* @date 2019年4月15日 下午4:24:35 
*  
*/
@Service
public class ReportBeforeOpenAccountService implements IServiceStrategy {

	@Resource
	private CommonMapper commonMapper;
	
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
		String logInfo = "上报府星水表上报数据 ：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.Logger(LogName.CALLBACK).info(logInfo);
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
			
			Map<String, Object> meterInfo = this.commonMapper.getNbInfoByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			int rtuId = toInt(meterInfo.get("rtuId"));
			int mpId = toInt(meterInfo.get("mpId"));
			
			Date dateTime = DateUtils.parseTimesTampDate(dataMap.get("CurrentDateTime"));
			int date = toInt(DateUtils.formatDateByFormat(dateTime, "yyyyMMdd"));
			int time = toInt(DateUtils.formatDateByFormat(dateTime, "HHmmss"));

			// 插入设备电池电压数据
			insertBattery(dataMap, rtuId, mpId, date, time);
			// 插入设备上报日数据
			insertDailyData(rtuId, mpId, dataMap, deviceId, date, time);
			// 更新水表的阀门状态和固件版本
			meterInfo.put("valveState", toByte(dataMap.get("ValveState")));
			meterInfo.put("version", dataMap.get("Version"));
			commonMapper.updateWaterMeterValve(meterInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.Logger(LogName.CALLBACK).error(logInfo + "异常" + e.getMessage());
		}
	}
	
	
	/** 
	* @Title: insertBattery 
	* @Description: 插入电池数据
	* @param @param fxReport
	* @param @param rtuId
	* @param @param mpId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertBattery(Map<String, String> dataMap, int rtuId, int mpId, int date, int time) throws Exception {
		
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(toStr(date / 100));
		nbBattery.setYmd(date);
		nbBattery.setHms(time);
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(toDouble(dataMap.get("BatteryVoltage")));

		JedisUtils.lpush(Constant.HISTORY_BATTERY_QUEUE, JsonUtil.jsonObj2Sting(nbBattery));
	}
	
	/** 
	* @Title: insertDailyData 
	* @Description: 插入日数据数据
	* @param @param rtuId
	* @param @param mpId
	* @param @param fxReport
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertDailyData(int rtuId, int mpId, Map<String, String> dataMap, String deviceId, int date, int time) throws Exception {

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(toStr(date / 100));
		nbDailyData.setReportType(toByte(dataMap.get("IsKeyTriggerThisReport")));
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId((short) mpId);
		nbDailyData.setYmd(date);
		nbDailyData.setHms(time);

		nbDailyData.setTotalFlow(toDouble(dataMap.get("TotalCumulateWater")));
		nbDailyData.setMonthTotalFlow(toDouble(dataMap.get("MonthCumulateWater")));
		nbDailyData.setBatteryVoltage(toDouble(dataMap.get("BatteryVoltage")));
		nbDailyData.setValveStatus(toByte(dataMap.get("ValveState")));

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
	}

}
