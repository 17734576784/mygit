/**   
* @Title: ReportExceptionService.java 
* @Package com.nb.servicestrategy.chinatelecom.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月15日 下午5:08:36 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.fx;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;
import static com.nb.utils.ConverterUtils.toShort;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.Eve;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ReportExceptionService 
* @Description: 府星水表异常数据上报内容
* @author dbr
* @date 2019年4月15日 下午5:08:36 
*  
*/
@Service
public class ReportExceptionService implements IServiceStrategy {

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
		String logInfo = "府星异常数据上报,设备 ：" + deviceId + ",数据：" + serviceMap.toString();
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
			
			Short typeNo = toShort(dataMap.get("ErrorNo"));
			String eveInfo = "";
			
			switch (typeNo) {
			case Constant.FX_VALVE_ERROR:
				eveInfo = "阀门异常";
				typeNo = Constant.ALARM_2010;
				break;
			case Constant.FX_MAGNETIC:
				eveInfo = "强磁异常（双干簧管异常），连续10秒2个干簧管都吸合，关阀门并自动上报，有5次按键打开阀门的机会，第5次按键打卡阀门后，如果还是出现强磁只有服务器下发“开阀命令”才能打开阀门。";
				typeNo = Constant.ALARM_2004;
				break;
			case Constant.FX_BATTERY_1:
			case Constant.FX_BATTERY_2:
				eveInfo = "电池电压低压告警，电压值:" + dataMap.get("BatteryVoltage");
				typeNo = Constant.ALARM_2008;
				break;
			default:
				break;
			}
			 
			if (!eveInfo.isEmpty()) {
				insertEve(dataMap, deviceId, eveInfo, typeNo);
			}
 			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + "异常" + e.getMessage());
		}
	}
	
	/** 
	* @Title: insertEve 
	* @Description: 插入告警事项 
	* @param @param fxReport
	* @param @param deviceId
	* @param @param eveInfo
	* @param @param typeNo
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(Map<String, String> dataMap, String deviceId, String eveInfo, short typeNo)
			throws Exception {
		
		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}
		
		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));

		Date dateTime = DateUtils.parseTimesTampDate(dataMap.get("CurrentDateTime"));
		int date = toInt(DateUtils.formatDateByFormat(dateTime, DateUtils.DATE_PATTERN));
		int time = toInt(DateUtils.formatDateByFormat(dateTime, DateUtils.TIME_PATTERN));

		Eve eve = new Eve();
		eve.setTableName(toStr(date / 100));
		eve.setYmd(date);
		eve.setHmsms(time * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}


}
