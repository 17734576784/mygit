/**   
* @Title: FxMoileReportNormallyService.java 
* @Package com.nb.servicestrategy.chinamobile.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午3:26:15 
* @version V1.0   
*/
package com.nb.servicestrategy.chinamobile.fx;

import static com.nb.utils.ConverterUtils.toByte;
import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toShort;
import static com.nb.utils.ConverterUtils.toStr;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.mapper.NbDailyDataMapper;
import com.nb.model.NbBattery;
import com.nb.model.NbDailyData;
import com.nb.model.NbInstantaneous;
import com.nb.model.NbWaterMeter;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import com.thrid.party.codec.ef.ReceiveCodeEF;
import com.thrid.party.codec.ef.ReturnObject;

/** 
* @ClassName: FxMoileReportNormallyService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年5月24日 下午3:26:15 
*  
*/
@Service
public class FxMoileReportNormallyService implements IServiceStrategy {

	@Resource
	private NbDailyDataMapper nbDailyDataMapper;
	
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

		String logInfo = "府星移动 定时主动上报,设备 ：" + deviceId + ",数据：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		if (serviceMap == null || serviceMap.isEmpty()) {
			return;
		}
		try {
			ReturnObject obj = JsonUtil.GsonToBean(serviceMap.get("msg"), ReturnObject.class);
			ReceiveCodeEF receiveCode = obj.getContentObj();
			/** 根据设备Id获取表计的终端和测定id */
			Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			int rtuId = toInt(meterInfo.get("rtuId"));
			int mpId = toInt(meterInfo.get("mpId"));

			Date dateTime = DateUtils.parseTimesTampDate(receiveCode.getCurrentDateTime());
			int date = toInt(DateUtils.formatDateByFormat(dateTime, "yyyyMMdd"));
			int time = toInt(DateUtils.formatDateByFormat(dateTime, "HHmmss"));

			// 插入设备电池电压数据
			insertBattery(receiveCode, rtuId, mpId, date, time);
			// 插入设备上报日数据
			insertDailyData(rtuId, mpId, receiveCode, deviceId, date, time);
			// 插入瞬时量
			insertInstantaneous(receiveCode, rtuId, mpId, date);
			// 更新水表状态
			updateNbWaterMeter(receiveCode, rtuId, mpId);
			// 插入丢失的日结
			supplementDailyData(receiveCode, rtuId, mpId, date);

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + ",异常:" + e.getMessage());
		}
	}
	
	/** 
	* @Title: supplementDailyData 
	* @Description: 补存日数据  前 1 天~15 天日结正向水量 前 1 天~15 天日结反向水量
	* @param @param receiveCode
	* @param @param rtuId
	* @param @param mpId
	* @param @param date    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void supplementDailyData(ReceiveCodeEF receiveCode, int rtuId, int mpId, int date) {
		List<BigDecimal> daysPositiveList = receiveCode.getFifteenDaysPositiveWaterList();
		List<BigDecimal> daysReverseList = receiveCode.getFifteenDaysReverseWaterList();
		
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseTimesTampDate(toStr(date), DateUtils.DATE_PATTERN));
		c.add(Calendar.DAY_OF_YEAR, -1);
		
		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId((short) mpId);
		nbDailyData.setHms(Constant.ZERO);

		for (int i = 0; i < daysPositiveList.size(); i++) {
			int ymd = toInt(DateUtils.parseDate(c.getTime(), DateUtils.DATE_PATTERN));
			nbDailyData.setYmd(ymd);
			nbDailyData.setTableName(toStr(ymd / 100));

			nbDailyData.setDailyPositiveFlow(daysPositiveList.get(i).doubleValue());
			nbDailyData.setDailyNegativeFlow(daysReverseList.get(i).doubleValue());
			JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
			c.add(Calendar.DAY_OF_YEAR, -1);
		}
	}
	
	/** 
	* @Title: insertInstantaneous 
	* @Description: 前一天每小时正向水量
	* @param @param receiveCode
	* @param @param rtuId
	* @param @param mpId
	* @param @param date
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertInstantaneous(ReceiveCodeEF receiveCode, int rtuId, int mpId, int date) throws Exception {

		NbInstantaneous nbInstantaneous = new NbInstantaneous();
		nbInstantaneous.setRtuId(rtuId);
		nbInstantaneous.setMpId(toShort(mpId));

		/** 时间设置到当天0点0分 */
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseTimesTampDate(toStr(date), DateUtils.DATE_PATTERN));
		c.add(Calendar.DAY_OF_YEAR, -1);

		List<BigDecimal> list = receiveCode.getYesterdayPerHourPositiveWaterList();
		for (int i = 0; i < list.size(); i++) {
			int ymd = toInt(DateUtils.parseDate(c.getTime(), DateUtils.DATE_PATTERN));
			nbInstantaneous.setYmd(ymd);
			nbInstantaneous.setTableName(toStr(ymd / 100));
			
			int time = toInt(DateUtils.formatTimePattern(c.getTime()));
			nbInstantaneous.setHms(time);
			nbInstantaneous.setTotalFlow(list.get(i).doubleValue());
			JedisUtils.lpush(Constant.HISTORY_INSTAN_QUEUE, JsonUtil.jsonObj2Sting(nbInstantaneous));
			c.add(Calendar.HOUR, Constant.ONE);
		}
	}
	
	/** 
	* @Title: updateNbWaterMeter 
	* @Description: 更新水表状态
	* @param @param receiveCode
	* @param @param rtuId
	* @param @param mpId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void updateNbWaterMeter(ReceiveCodeEF receiveCode, int rtuId, int mpId) throws Exception {

		byte valveState = toByte(receiveCode.getValveState());
		valveState = (byte) (valveState == Constant.ZERO ? Constant.FOUR : Constant.TWO);

		NbWaterMeter nbWaterMeter = new NbWaterMeter();
		nbWaterMeter.setRtuId(rtuId);
		nbWaterMeter.setMpId((short) mpId);
		nbWaterMeter.setValveStatus(valveState);
		nbWaterMeter.setFirmwareVersion(receiveCode.getVersion());

		nbWaterMeter.setLargeFlowThreshold(receiveCode.getLargeFlowAlarmThreshold());
		nbWaterMeter.setLargeFlowDuration(receiveCode.getLargeFlowContinuousMonitorTime().intValue());
		nbWaterMeter.setSmallFlowThreshold(receiveCode.getLowFlowAlarmThreshold());
		nbWaterMeter.setSmallFlowDuration(receiveCode.getLowFlowContinuousMonitorTime().intValue());
		nbWaterMeter.setReportBaseTime(DateUtils.parseDate(receiveCode.getReportBaseTime(), "YYYYMMDDhhmmss"));
		nbWaterMeter.setReportIntervalTime(toInt(receiveCode.getReportIntervalHours()));
		commonMapper.updateWaterMeter(nbWaterMeter);
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
	private void insertBattery(ReceiveCodeEF receiveCode, int rtuId, int mpId, int date, int time) throws Exception {

		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(toStr(date / 100));
		nbBattery.setYmd(date);
		nbBattery.setHms(time);
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(receiveCode.getBatteryVoltage().doubleValue());

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
	private void insertDailyData(int rtuId, int mpId, ReceiveCodeEF receiveCode, String deviceId, int date, int time)
			throws Exception {

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(toStr(date / 100));
		nbDailyData.setReportType(toByte(receiveCode.getIsKeyTriggerThisReport()));
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId((short) mpId);
		nbDailyData.setYmd(date);
		nbDailyData.setHms(time);

		nbDailyData.setTotalFlow(receiveCode.getTotalPositiveCumulateWater().doubleValue());
		nbDailyData.setMonthTotalFlow(receiveCode.getMonthPositiveCumulateWater().doubleValue());
		nbDailyData.setBatteryVoltage(receiveCode.getBatteryVoltage().doubleValue());
		
		byte valveState = toByte(receiveCode.getValveState());
		valveState = (byte) (valveState == Constant.ZERO ? Constant.FOUR : Constant.TWO);
		nbDailyData.setValveStatus(valveState);

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
	}

}
