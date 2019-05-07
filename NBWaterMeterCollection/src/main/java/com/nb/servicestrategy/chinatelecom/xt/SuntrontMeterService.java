/**   
* @Title: SuntrontMeterService.java 
* @Package com.nb.servicestrategy.chinatelecom.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午3:51:40 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.xt;

import static com.nb.utils.ConverterUtils.toInt;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.NbDailyData;
import com.nb.model.xt.SuntrontMeter;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: SuntrontMeterService 
* @Description: 新天科技上报SuntrontMeter服务 
* @author dbr
* @date 2019年5月6日 下午3:51:40 
*  
*/
@Service
public class SuntrontMeterService implements IServiceStrategy {
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
		String logInfo = "上报新天科技SuntrontMeter服务,设备 ：" + deviceId + " ,数据：" + serviceMap.toString();
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

			SuntrontMeter suntrontMeter = JsonUtil.map2Bean(dataMap, SuntrontMeter.class);
			suntrontMeter.setEvnetTime(serviceMap);
			insertNbDailyData(suntrontMeter, deviceId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + "异常" + e.getMessage());
		}
	}
	
	/** 
	* @Title: insertNbDailyData 
	* @Description: 插入日数据的当前累计流量并更新水表版本号 
	* @param @param suntrontMeter
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertNbDailyData(SuntrontMeter suntrontMeter, String deviceId) throws Exception {

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}
		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(ConverterUtils.toStr(suntrontMeter.getEventDate() / 100));
		nbDailyData.setReportType((byte) Constant.ZERO);
		nbDailyData.setRtuId(toInt(meterInfo.get("rtuId")));
		nbDailyData.setMpId(ConverterUtils.toShort(meterInfo.get("mpId")));
		nbDailyData.setYmd(suntrontMeter.getEventDate());
		nbDailyData.setHms(suntrontMeter.getEventTime());

		nbDailyData.setTotalFlow(suntrontMeter.getCurrentReading());

		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));

		/** 更新版本号 */
		meterInfo.put("version", suntrontMeter.getSoftwareVersion());
		commonMapper.updateWaterMeterValve(meterInfo);
	}

}
