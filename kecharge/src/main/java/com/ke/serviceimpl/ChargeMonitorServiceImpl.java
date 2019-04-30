/**   
* @Title: ChargeMonitorServiceImpl.java 
* @Package com.ke.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午3:46:15 
* @version V1.0   
*/
package com.ke.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.model.ChargeMonitor;
import com.ke.service.IChargeMonitorService;
import com.ke.utils.DateUtil;

/** 
* @ClassName: ChargeMonitorServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月10日 下午3:46:15 
*  
*/
@Service
public class ChargeMonitorServiceImpl implements IChargeMonitorService {

	@Resource
	private ChargeMonitorMapper chargeMonitorMapper;
	/** (非 Javadoc) 
	* <p>Title: listChargeMonitor</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return 
	* @see com.ke.service.IChargeMonitorService#listChargeMonitor(java.lang.String) 
	*/
	@Override
	public JSONObject listChargeMonitor(String queryJsonStr) {

		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		int beginYear = json.getIntValue("beginDate") / 10000;
		int endYear = json.getIntValue("endDate") / 10000;

		List<Integer> yearList = new ArrayList<Integer>(2);
		yearList.add(beginYear);
		if (beginYear != endYear) {
			yearList.add(endYear);
		}
		param.put("yearList", yearList);
		param.putAll(json);

		List<ChargeMonitor> list = chargeMonitorMapper.listChargeMonitor(param);

		List<JSONObject> chargeMonitorList = list.stream().filter(chargeMonitor -> chargeMonitor != null)
				.map(chargeMonitor -> getChargeMonitor(chargeMonitor)).collect(Collectors.toList());

		JSONObject rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", chargeMonitorList);

		return rtnJson;
	}

	/** 
	* @Title: getChargeMonitor 
	* @Description: 组建充电单监控信息 
	* @param @param chargeMonitor
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private JSONObject getChargeMonitor(ChargeMonitor chargeMonitor) {
		JSONObject jsonItem = new JSONObject();

		jsonItem.put("serialnumber", chargeMonitor.getSerialnumber());
		jsonItem.put("charge_money", chargeMonitor.getChargeMoney());
		jsonItem.put("pile_code", chargeMonitor.getPileCode());
		jsonItem.put("gun_id", chargeMonitor.getGunId());

		jsonItem.put("start_date", DateUtil.formatTimesTampDate(chargeMonitor.getStartDate()));
		jsonItem.put("start_receive_time", DateUtil.formatTimesTampDate(chargeMonitor.getStartReceiveTime()));
		jsonItem.put("start_flag", chargeMonitor.getStartFlag());
		jsonItem.put("start_push", chargeMonitor.getStartPush());

		jsonItem.put("start_push_time", DateUtil.formatTimesTampDate(chargeMonitor.getStartPushTime()));
		jsonItem.put("end_date", DateUtil.formatTimesTampDate(chargeMonitor.getEndDate()));
		jsonItem.put("end_receive_time", DateUtil.formatTimesTampDate(chargeMonitor.getEndReceiveTime()));
		jsonItem.put("end_push", chargeMonitor.getEndPush());
		jsonItem.put("end_push_time", DateUtil.formatTimesTampDate(chargeMonitor.getEndPushTime()));

		return jsonItem;
	}
}
