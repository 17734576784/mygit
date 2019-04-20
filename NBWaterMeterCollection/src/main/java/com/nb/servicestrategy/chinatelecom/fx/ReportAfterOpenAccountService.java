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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbBattery;
import com.nb.model.NbDailyData;
import com.nb.model.fx.FXReport;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
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
public class ReportAfterOpenAccountService implements IServiceStrategy {

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
		String logInfo = "上报府星水表上报数据 ：" + deviceId + " ,内容：" + serviceMap.toString();
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
			
			FXReport fxReport = JsonUtil.map2Bean(dataMap, FXReport.class); 
			Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			int rtuId = toInt(meterInfo.get("rtuId"));
			int mpId = toInt(meterInfo.get("mpId"));

			// 插入设备电池电压数据
			insertBattery(fxReport, rtuId, mpId);
			// 插入设备上报日数据
			insertDailyData(rtuId, mpId, fxReport, deviceId);
			// 更新水表的阀门状态和固件版本
			meterInfo.put("valveState", toByte(fxReport.getValveState()));
			meterInfo.put("version", fxReport.getValveState());
			commonMapper.updateWaterMeterValve(meterInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.CALLBACK).error(logInfo + "异常" + e.getMessage());
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
	private void insertBattery(FXReport fxReport, int rtuId, int mpId) throws Exception {
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(toStr(fxReport.getDate() / 100));
		nbBattery.setYmd(fxReport.getDate());
		nbBattery.setHms(fxReport.getTime());
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(toDouble(fxReport.getBatteryVoltage()));

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
	private void insertDailyData(int rtuId, int mpId, FXReport fxReport, String deviceId) throws Exception {

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(toStr(fxReport.getDate() / 100));
		nbDailyData.setReportType(toByte(fxReport.getIsKeyTriggerThisReport()));
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId((short) mpId);
		nbDailyData.setYmd(fxReport.getDate());
		nbDailyData.setHms(fxReport.getTime());

		nbDailyData.setTotalFlow(toDouble(fxReport.getTotalCumulateWater()));
		nbDailyData.setMonthTotalFlow(toDouble(fxReport.getMonthCumulateWater()));
		nbDailyData.setBatteryVoltage(toDouble(fxReport.getBatteryVoltage()));
		nbDailyData.setValveStatus(toByte(fxReport.getValveState()));

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
	}

}
