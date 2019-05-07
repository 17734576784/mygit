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
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub
		String logInfo = "上报竟达水表周期数据：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			if (dataMap == null) {
				return;
			}

			PeriodReport periodReport = JsonUtil.map2Bean(dataMap, PeriodReport.class);

			insertDailyData(periodReport, deviceId);
			insertInstantaneous(periodReport, deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("解析竟达水表周期数据异常" + e.getMessage());
		}

	}

	/** 
	* @Title: insertInstantaneous 
	* @Description: 插入瞬时量
	* @param @param periodReport
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertInstantaneous(PeriodReport periodReport, String deviceId) throws Exception {

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbInstantaneous nbInstantaneous = new NbInstantaneous();
		nbInstantaneous.setTableName(toStr(periodReport.getStartYmd() / 100));
		nbInstantaneous.setRtuId(rtuId);
		nbInstantaneous.setMpId(mpId);
		nbInstantaneous.setYmd(periodReport.getStartYmd());

		JSONArray arrays = JSONArray.parseArray(periodReport.getFlows().toString());
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseTimesTampDate(toStr(periodReport.getStartYmd()), DateUtils.DATE_PATTERN));
		c.set(Calendar.MINUTE, 0);
		for (int i = 0; i < arrays.size(); i++) {
			int time = toInt(DateUtils.formatTimePattern(c.getTime()));
			nbInstantaneous.setHms(time);
			nbInstantaneous.setTotalFlow(arrays.getDouble(i) / Constant.NUM_1000);
			JedisUtils.lpush(Constant.HISTORY_INSTAN_QUEUE, JsonUtil.jsonObj2Sting(nbInstantaneous));
			c.add(Calendar.MINUTE, 30);

		}
	}

	/** 
	* @Title: insertDailyData 
	* @Description: 将周期数据插入日数据表中 
	* @param @param periodReport
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertDailyData(PeriodReport periodReport, String deviceId) throws Exception {

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(toStr(periodReport.getReadYmd() / 100));
		nbDailyData.setReportType((byte) 0);
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId(mpId);
		nbDailyData.setYmd(periodReport.getReadYmd());
		nbDailyData.setHms(periodReport.getReadHms());

		nbDailyData.setTotalFlow(periodReport.getCumulativeFlow());
		nbDailyData.setDailyPositiveFlow(periodReport.getPositiveCumulativeFlow());
		nbDailyData.setDailyNegativeFlow(periodReport.getNegativeCumulativeFlow());
		nbDailyData.setDailyMaxVelocity(periodReport.getPeakFlowRate());

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));

	}

}
