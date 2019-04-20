/**   
* @Title: DeviceAlarmService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月9日 下午3:05:53 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.mapper.EveMapper;
import com.nb.model.Eve;
import com.nb.model.jd.DeviceAlarm;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/**
 * @ClassName: DeviceAlarmService
 * @Description: 竟达告警信息
 * @author dbr
 * @date 2019年4月9日 下午3:05:53
 * 
 */
@Service
public class DeviceAlarmService implements IServiceStrategy {

	@Resource
	private CommonMapper commonMapper;

	@Resource
	private EveMapper eveMapper;

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
		String logInfo = "上报竟达告警信息：" + deviceId + " ,内容：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		Object data = serviceMap.get("data");
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			if (dataMap == null) {
				return;
			}

			DeviceAlarm deviceAlarm = JsonUtil.map2Bean(dataMap, DeviceAlarm.class);
			deviceAlarm.setEvnetTime(serviceMap.get("eventTime"));

			String eveInfo = "";
			Short typeNo = 0;
			// 篡改告警
			if (deviceAlarm.getTampered() == Constant.ALARM) {
				eveInfo = "序列号,生产日期,累计流量至少有一项被篡改";
				typeNo = Constant.ALARM_2012;
				insertEve(deviceAlarm, deviceId, eveInfo, typeNo);
			}

			// 反流告警
			if (deviceAlarm.getReverseFlowAlarm() == Constant.ALARM) {
				eveInfo = "反流告警";
				typeNo = Constant.ALARM_2003;
				insertEve(deviceAlarm, deviceId, eveInfo, typeNo);
			}

			// 磁干扰
			if (deviceAlarm.getMagneticInterferenceAlarm() == Constant.ALARM) {
				eveInfo = "磁干扰";
				typeNo = Constant.ALARM_2004;
				insertEve(deviceAlarm, deviceId, eveInfo, typeNo);
			}

			// 远传模块分离告警
			if (deviceAlarm.getDisconnectAlarm() == Constant.ALARM) {
				eveInfo = "远传模块分离告警";
				typeNo = Constant.ALARM_2006;
				insertEve(deviceAlarm, deviceId, eveInfo, typeNo);
			}

			// 大流量告警
			if (deviceAlarm.getPeakFlow() > 0) {
				typeNo = Constant.ALARM_2001;
				eveInfo = "大流量报警 开始时间:" + deviceAlarm.getPeakFlowStartTime() + ",最大流速:" + deviceAlarm.getPeakFlow()
						+ " L/h";
				insertEve(deviceAlarm, deviceId, eveInfo, typeNo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.CALLBACK).error(logInfo + "，异常" + e.getMessage());
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
	private void insertEve(DeviceAlarm deviceAlarm, String deviceId, String eveInfo, short typeNo) throws Exception {
		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));

		Eve eve = new Eve();
		eve.setTableName(toStr(deviceAlarm.getDate() / 100));
		eve.setYmd(deviceAlarm.getDate());
		eve.setHmsms(deviceAlarm.getTime() * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}

}
