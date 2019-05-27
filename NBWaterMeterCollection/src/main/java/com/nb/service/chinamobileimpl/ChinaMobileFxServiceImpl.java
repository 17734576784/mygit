/**   
* @Title: ChinaMobileSuntrontServiceImpl.java 
* @Package com.nb.service.chinamobileimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月17日 下午2:07:45 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nb.service.IChinaMobileService;
import com.nb.service.IFXCryptoService;
import com.nb.servicestrategy.ServiceStrategyContext;
import com.nb.utils.JsonUtil;
import com.nb.utils.StringUtil;

/**
 * @ClassName: ChinaMobileFxServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年5月17日 下午2:07:45
 * 
 */
@Service
public class ChinaMobileFxServiceImpl implements IChinaMobileService {

	@Resource
	private ServiceStrategyContext serviceStrategyContext;
	
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: parseMsg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param msgJson
	 * @see com.nb.service.IChinaMobileService#parseMsg(com.alibaba.fastjson.JSONObject)
	 */
	@Override
	public void parseDataPointMsg(JSONObject msgJson) {
		// 标识消息类型
		String deviceId = msgJson.getString("dev_id");
		String data = msgJson.getString("value");
		int hasmore = 0, mid = 0;
		String msg = IFXCryptoService.instance.ServerReceiveData(data, hasmore, mid);
		
		JSONObject json = JSONObject.parseObject(msg);
		String serviceId = json.getString("ServiceId");
		serviceId = "FxMoile" + StringUtil.toLowerCaseFirstOne(serviceId);
		json.put("ServiceId", serviceId);
		
		Map<String, String> serviceMap = new HashMap<>();
		serviceMap = JsonUtil.jsonString2SimpleObj(json, serviceMap.getClass());
		serviceStrategyContext.parseService(deviceId, serviceMap);

	}

	/** (非 Javadoc) 
	* <p>Title: parseCommandMsg</p> 
	* <p>Description: </p> 
	* @param msgJson 
	* @see com.nb.service.IChinaMobileService#parseCommandMsg(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public void parseCommandMsg(JSONObject msgJson) {
		// TODO Auto-generated method stub
		
	}

}
