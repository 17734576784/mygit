/**   
* @Title: RealtimeReportService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 上午11:48:09 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;
import static com.nb.utils.ConverterUtils.toShort;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbDailyData;
import com.nb.model.jd.RealtimeReport;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/**
 * @ClassName: RealtimeReportService
 * @Description: 竟达水表实时数据服务
 * @author dbr
 * @date 2019年4月10日 上午11:48:09
 * 
 */
@Service
public class RealtimeReportService implements IServiceStrategy {

	@Resource
	private CommonMapper commonMapper;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: parse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param deviceId
	 * @param serviceMap
	 * @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String,
	 *      java.util.Map)
	 */
	
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub
		String logInfo = "上报竟达水表实时数据：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			if (dataMap == null) {
				return;
			}

			RealtimeReport realtimeReport = JsonUtil.map2Bean(dataMap, RealtimeReport.class);
			System.out.println(realtimeReport.toString());

			String evnetTime = realtimeReport.getReadTime();
			int date = toInt(evnetTime.substring(0, 8));
			int time = toInt(evnetTime.substring(9, 15));
			String tableNameDate = toStr(date / 100);

			insertRealReport(tableNameDate, date, time, realtimeReport, deviceId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.CALLBACK).error(logInfo + "，异常" + e.getMessage());
		}

	}

	/**
	 * @Title: insertRealReport @Description: 插入实时数据上报 @param @param
	 * YM @param @param date @param @param time @param @param
	 * realtimeReport @param @param deviceId 设定文件 @return void 返回类型 @throws
	 */
	private void insertRealReport(String tableNameDate, int date, int time, RealtimeReport realtimeReport, String deviceId)
			throws Exception {

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(tableNameDate);
		nbDailyData.setReportType((byte) Constant.ZERO);
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId(mpId);
		nbDailyData.setYmd(date);
		nbDailyData.setHms(time);

		nbDailyData.setTotalFlow(realtimeReport.getCumulativeFlow());
		nbDailyData.setDailyPositiveFlow(realtimeReport.getPositiveCumulativeFlow());
		nbDailyData.setDailyNegativeFlow(realtimeReport.getNegativeCumulativeFlow());
		nbDailyData.setDailyMaxVelocity(realtimeReport.getPeakFlowRate());

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
	}
}
