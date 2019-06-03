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
import com.nb.servicestrategy.ServiceStrategyContext;
import com.nb.utils.JsonUtil;
import com.thrid.party.codec.ef.Out;
import com.thrid.party.codec.ef.ReturnObject;
import com.thrid.party.codec.ef.SendReceiveHelper;
import com.thrid.party.codec.ef.SendReceiveHelperEF;

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
	
	
	/** (非 Javadoc) 
	* <p>Title: parseDataPointMsg</p> 
	* <p>Description: </p> 
	* @param msgJson 
	* @see com.nb.service.IChinaMobileService#parseDataPointMsg(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public void parseDataPointMsg(JSONObject msgJson) throws Exception{
		// 标识消息类型
		String deviceId = msgJson.getString("dev_id");
		String data = msgJson.getString("value");
		// 是否还有后续数据
		Out<Integer> hasmore = new Out<>();
		// 消息ID
		Out<Integer> mid = new Out<>();
	    SendReceiveHelper helper = new SendReceiveHelperEF();
		String msg = null;
		/** 解码 */ 
		try {
			msg = helper.ServerReceiveData(data, hasmore, mid);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		ReturnObject obj = JsonUtil.GsonToBean(msg, ReturnObject.class);
		
		Map<String, String> serviceMap = new HashMap<>();
		serviceMap.put("serviceId", "FxMoile" + obj.getServiceId());
		serviceMap.put("msg", msg);
		serviceStrategyContext.parseService(deviceId, serviceMap);

	}

	/** (非 Javadoc) 
	* <p>Title: parseCommandMsg</p> 
	* <p>Description: </p> 
	* @param msgJson 
	* @see com.nb.service.IChinaMobileService#parseCommandMsg(com.alibaba.fastjson.JSONObject) 
	*/
	@Override
	public void parseCommandMsg(JSONObject msgJson) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
