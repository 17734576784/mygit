/**   
* @Title: ValveService.java 
* @Package com.nb.servicestrategy.chinatelecom.jd 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月11日 上午10:44:43 
* @version V1.0   
*/
package com.nb.servicestrategy.chinatelecom.jd;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: ValveService 
* @Description: 阀门上报服务
* @author dbr
* @date 2019年4月11日 上午10:44:43 
*  
*/
@Service
public class ValveService implements IServiceStrategy {


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
		String logInfo = "上报竟达阀门服务数据，设备：" + deviceId + "，数据：" + serviceMap.toString();
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
		dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
		if (dataMap == null) {
			return;
		}

		byte valveState = Constant.VALVE_OTHER;
		switch (dataMap.get("valveState")) {
		case "O":
			valveState = Constant.VALVE_OPEN;
			break;

		case "C":
			valveState = Constant.VALVE_CLOSE;
			break;
		case "H":
			valveState = Constant.VALVE_HALF_OPEN;
			break;
		default:
			break;
		}

		try {
			meterInfo.put("valveState", valveState);
			commonMapper.updateWaterMeterValve(meterInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
