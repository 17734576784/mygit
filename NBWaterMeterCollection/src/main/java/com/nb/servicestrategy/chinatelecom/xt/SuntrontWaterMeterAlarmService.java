/**   
* @Title: SuntrontWaterMeterAlarmService.java 
* @Package com.nb.servicestrategy.chinatelecom.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午5:00:40 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.xt;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.Eve;
import com.nb.model.xt.SuntrontWaterMeterAlarm;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: SuntrontWaterMeterAlarmService 
* @Description: 新天科技上报SuntrontWaterMeterAlarm数据 
* @author dbr
* @date 2019年5月6日 下午5:00:40 
*  
*/
@Service
public class SuntrontWaterMeterAlarmService implements IServiceStrategy {

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
		String logInfo = "上报新天科技SuntrontWaterMeterAlarm服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
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

			SuntrontWaterMeterAlarm suntrontwatermeteralarm = JsonUtil.map2Bean(dataMap, SuntrontWaterMeterAlarm.class);
			suntrontwatermeteralarm.setEvnetTime(serviceMap);
			
			String eveInfo = "";
			Short typeNo = 0;
			if (suntrontwatermeteralarm.getHighFlowAlarm()) {
				eveInfo = "持续高流量告警";
				typeNo = Constant.ALARM_2001;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getLowBatteryAlarm()) {
				eveInfo = "电池低电压告警";
				typeNo = Constant.ALARM_2005;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getReverseFlow()) {
				eveInfo = "反流告警";
				typeNo = Constant.ALARM_2003;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getLowPressure()) {
				eveInfo = "低压告警";
				typeNo = Constant.ALARM_2008;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getHighPressure()) {
				eveInfo = "高压告警";
				typeNo = Constant.ALARM_2009;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getStorageFault()) {
				eveInfo = "存储器异常";
				typeNo = Constant.ALARM_2011;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

			if (suntrontwatermeteralarm.getMagneticInterference()) {
				eveInfo = "磁干扰告警";
				typeNo = Constant.ALARM_2004;
				insertEve(suntrontwatermeteralarm, deviceId, eveInfo, typeNo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + ",异常:" + e.getMessage());
		}
	}

	/** 
	* @Title: insertEve 
	* @Description: 插入告警事项  
	* @param @param deviceAlarm
	* @param @param deviceId
	* @param @param eveInfo
	* @param @param typeNo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(SuntrontWaterMeterAlarm suntrontwatermeteralarm, String deviceId, String eveInfo,
			short typeNo) throws Exception {
		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));

		Eve eve = new Eve();
		eve.setTableName(toStr(suntrontwatermeteralarm.getEventDate() / 100));
		eve.setYmd(suntrontwatermeteralarm.getEventDate());
		eve.setHmsms(suntrontwatermeteralarm.getEventTime() * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}
}
