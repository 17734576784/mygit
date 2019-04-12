/**   
* @Title: PeriodReportService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午11:45:43 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toShort;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbDailyData;
import com.nb.model.NbInstantaneous;
import com.nb.model.jd.PeriodReport;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;


/** 
* @ClassName: PeriodReportService 
* @Description: 竟达水表周期数据服务 
* @author dbr
* @date 2019年4月10日 上午11:45:43 
*  
*/
@Service
public class PeriodReportService implements IServiceStrategy {

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
		String logInfo = "上报竟达水表周期数据：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.Logger(LogName.CALLBACK).info(logInfo);

		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
		if (dataMap == null) {
			return;
		}

		System.out.println(dataMap);
		
		PeriodReport periodReport = JsonUtil.map2Bean(dataMap, PeriodReport.class);
		System.out.println(periodReport.toString());

		String evnetTime = periodReport.getReadTime();
		int date = toInt(evnetTime.substring(0, 8));
		int time = toInt(evnetTime.substring(9, 15));
		String YM = toStr(date / 100);

		insertPeriodReport(YM, date, time, periodReport, deviceId);

		insertInstantaneous(YM, date, periodReport, deviceId);
	}
	
	
	/** 
	* @Title: insertInstantaneous 
	* @Description: 插入瞬时量 
	* @param @param YM
	* @param @param date
	* @param @param periodReport
	* @param @param deviceId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertInstantaneous(String YM, int date, PeriodReport periodReport, String deviceId) {

		Map<String, Object> meterInfo = this.commonMapper.getNbInfoByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbInstantaneous nbInstantaneous = new NbInstantaneous();
		nbInstantaneous.setTableName(YM);
		nbInstantaneous.setRtuId(rtuId);
		nbInstantaneous.setMpId(mpId);
		nbInstantaneous.setYmd(date);

		JSONArray arrays = JSONArray.parseArray(periodReport.getFlows().toString());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, 0);
		for (int i = 0; i < arrays.size(); i++) {
			c.add(Calendar.MINUTE, 30);
			int time = toInt(DateUtils.formatTimePattern(c.getTime()));
			nbInstantaneous.setHms(time);
			nbInstantaneous.setTotalFlow(arrays.getDouble(i));

			JedisUtils.lpush(Constant.HISTORY_INSTAN_QUEUE, JsonUtil.jsonObj2Sting(nbInstantaneous));
		}
	}
	
	/** 
	* @Title: insertPeriodReport 
	* @Description: 将周期数据插入日数据表中 
	* @param @param YM
	* @param @param date
	* @param @param time
	* @param @param periodReport
	* @param @param deviceId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertPeriodReport(String YM, int date, int time, PeriodReport periodReport, String deviceId) {

		Map<String, Object> meterInfo = this.commonMapper.getNbInfoByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(YM);
		nbDailyData.setReportType((byte) 0);
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId(mpId);
		nbDailyData.setYmd(date);
		nbDailyData.setHms(time);

		nbDailyData.setTotalFlow(periodReport.getCumulativeFlow());
		nbDailyData.setDailyPositiveFlow(periodReport.getPositiveCumulativeFlow());
		nbDailyData.setDailyNegativeFlow(periodReport.getNegativeCumulativeFlow());
		nbDailyData.setDailyMaxVelocity(periodReport.getPeakFlowRate());

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));

	}

}
