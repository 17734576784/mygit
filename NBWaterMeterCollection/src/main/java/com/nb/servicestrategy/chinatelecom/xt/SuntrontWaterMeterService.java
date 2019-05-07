/**   
* @Title: SuntrontWaterMeterService.java 
* @Package com.nb.servicestrategy.chinatelecom.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午4:28:09 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.xt;

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
import com.nb.model.xt.SuntrontWaterMeter;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: SuntrontWaterMeterService 
* @Description: 新天科技上报SuntrontWaterMeter数据 
* @author dbr
* @date 2019年5月6日 下午4:28:09 
*  
*/
@Service
public class SuntrontWaterMeterService implements IServiceStrategy {

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
		String logInfo = "上报新天科技SuntrontWaterMeter服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		if (serviceMap == null || serviceMap.isEmpty()) {
			return;
		}
		
		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			if (dataMap == null) {
				return;
			}

			SuntrontWaterMeter suntrontWaterMeter = JsonUtil.map2Bean(dataMap, SuntrontWaterMeter.class);
			suntrontWaterMeter.setEvnetTime(serviceMap);
			
			insertNbDailyData(suntrontWaterMeter, meterInfo);
			insertInstantaneous(suntrontWaterMeter, meterInfo);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + "异常" + e.getMessage());
		}
	}

	
	/** 
	* @Title: insertInstantaneous 
	* @Description:  插入瞬时量
	* @param @param suntrontWaterMeter
	* @param @param deviceId
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertInstantaneous(SuntrontWaterMeter suntrontWaterMeter,Map<String, Object> meterInfo) throws Exception {

		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = toShort(meterInfo.get("mpId"));

		NbInstantaneous nbInstantaneous = new NbInstantaneous();
		nbInstantaneous.setTableName(toStr(suntrontWaterMeter.getIntervalDate() / 100));
		nbInstantaneous.setRtuId(rtuId);
		nbInstantaneous.setMpId(mpId);
		nbInstantaneous.setYmd(suntrontWaterMeter.getIntervalDate());

		JSONArray arrays = JSONArray.parseArray(suntrontWaterMeter.getInstantFlow().toString());
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseTimesTampDate(suntrontWaterMeter.getIntervalFlowStartingTime(), DateUtils.UTC_PATTERN));
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
	* @Title: insertNbDailyData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param suntrontWaterMeter
	* @param @param meterInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertNbDailyData(SuntrontWaterMeter suntrontWaterMeter, Map<String, Object> meterInfo) {
		// TODO Auto-generated method stub
		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(ConverterUtils.toStr(suntrontWaterMeter.getEventDate() / 100));
		nbDailyData.setReportType((byte) Constant.ZERO);
		nbDailyData.setRtuId(toInt(meterInfo.get("rtuId")));
		nbDailyData.setMpId(ConverterUtils.toShort(meterInfo.get("mpId")));
		nbDailyData.setYmd(suntrontWaterMeter.getEventDate());
		nbDailyData.setHms(suntrontWaterMeter.getEventTime());

		nbDailyData.setDailyPositiveFlow(suntrontWaterMeter.getDailyFlow());
		nbDailyData.setDailyNegativeFlow(suntrontWaterMeter.getDailyReverseFlow());
		nbDailyData.setDailyMaxVelocity(suntrontWaterMeter.getPeakFlowRate());
		
		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
	}

}
