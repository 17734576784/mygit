/**   
* @Title: SuntrontValveService.java 
* @Package com.nb.servicestrategy.chinatelecom.xt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月6日 下午5:22:09 
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
import com.nb.model.xt.SuntrontValve;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: SuntrontValveService 
* @Description: 上报新天科技SuntrontValveService服务 
* @author dbr
* @date 2019年5月6日 下午5:22:09 
*  
*/
@Service
public class SuntrontValveService implements IServiceStrategy {

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
		String logInfo = "上报新天科技SuntrontValveService服务 ：" + deviceId + " ,内容：" + serviceMap.toString();
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

			SuntrontValve suntrontValve = JsonUtil.map2Bean(dataMap, SuntrontValve.class);
			suntrontValve.setEvnetTime(serviceMap);
			
			Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			/** 更新表计阀门状态 */
			meterInfo.put("valveState", suntrontValve.getValveStatus() * Constant.TWO);
			commonMapper.updateWaterMeterValve(meterInfo);

			/** 保存阀门异常 */
			if (suntrontValve.getValveStatus() >= Constant.THREE) {
				String eveInfo = "阀门异常";
				Short typeNo = Constant.ALARM_2010;
				insertEve(suntrontValve, meterInfo, eveInfo, typeNo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + "异常" + e.getMessage());
		}
	}
	
	/** 
	* @Title: insertEve 
	* @Description: 插入告警事项
	* @param @param suntrontValve
	* @param @param meterInfo
	* @param @param eveInfo
	* @param @param typeNo
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(SuntrontValve suntrontValve, Map<String, Object> meterInfo, String eveInfo, short typeNo)
			throws Exception {

		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));

		Eve eve = new Eve();
		eve.setTableName(toStr(suntrontValve.getEventDate() / 100));
		eve.setYmd(suntrontValve.getEventDate());
		eve.setHmsms(suntrontValve.getEventTime() * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}

}
