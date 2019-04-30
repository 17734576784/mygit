/**
 * 
 */
package com.ke.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.SubstparaMapper;
import com.ke.model.LoginUser;
import com.ke.model.Substpara;
import com.ke.service.IStaionService;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;

/**
 * @author dbr
 *
 */
@Service
public class StaionServiceImpl implements IStaionService {

	@Resource
	private SubstparaMapper substparaMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wo.service.IStaionService#listStationGPS(java.lang.Integer)
	 */
	public JSONObject listStationGPS(String token) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoginUser loginUser = CommFunc.getLoginUserByToken(token);
		if (loginUser == null) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "token无效");
			return rtnJson;
		}
		
		Integer operatorId = loginUser.getOperatorId();
		if (operatorId == null) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		List<Substpara> list = this.substparaMapper.listStationGPS(operatorId);
		if (list.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "用户没有创建充电站");
			return rtnJson;
		}

		List<JSONObject> stationList = new ArrayList<JSONObject>();
		for (Substpara substpara : list) {
			if (substpara == null) {
				continue;
			}
			stationList.add(getSubstpara(substpara));
		}

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", stationList);

		return rtnJson;
	}
	
	/** 
	* @Title: getSubstpara 
	* @Description: 组建返回电站信息 
	* @param @param substpara
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private JSONObject getSubstpara(Substpara substpara) {
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("stationNo", ConverterUtil.toStr(substpara.getSerialCode()));
		jsonItem.put("stationName", ConverterUtil.toStr(substpara.getSubstName()));
		jsonItem.put("lng", ConverterUtil.toStr(substpara.getLongitude()));
		jsonItem.put("lat", ConverterUtil.toStr(substpara.getLatitude()));
		return jsonItem;
	}

	/** (非 Javadoc) 
	* <p>Title: listChargeOrders</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return
	* @throws Exception 
	* @see com.ke.service.IStaionService#listChargeOrders(java.lang.String) 
	*/
	@Override
	public JSONObject listChargeOrders(String queryJsonStr) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("接送请求充电站充电单 ： "+queryJsonStr);
		
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String stationNo = json.getString("stationNo");
		String startDate = json.getString("startDate");
		String endDate = json.getString("endDate");

		if (stationNo == null || stationNo.isEmpty() || startDate == null || startDate.isEmpty() || endDate == null
				|| endDate.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}
		
		JSONArray arrayItem = new JSONArray();
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("stationNo", stationNo);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		
		int startYm = ConverterUtil.toInt(startDate) / 100;
		int endYm = ConverterUtil.toInt(endDate) / 100;
		int monthNums = (endYm / 100 - startYm / 100) * 12
				+ (endYm % 100 - startYm % 100) + 1;

		List<Integer> monthList = new ArrayList<Integer>();
		Calendar startYM = Calendar.getInstance();
		startYM.setTime(sdf.parse(String.valueOf(startYm)));

		for (int i = 0; i < monthNums; i++) {
			monthList.add(Integer.parseInt(sdf.format(startYM.getTime())));
			startYM.add(Calendar.MONTH, 1);
		}
		
		param.put("monthList", monthList);
		

		List<Map<String, Object>> orderList = this.substparaMapper.listChargeOrders(param);
		for (Map<String,Object> orderMap : orderList) {
			JSONObject orderJson = new JSONObject();
			
			double chargedl = ConverterUtil.toDouble(orderMap.get("chargedl"));
			orderJson.put("serialNumber", ConverterUtil.toStr(orderMap.get("serialnumber")));
			orderJson.put("totalElectricity", ConverterUtil.roundBase(chargedl, 2));
			orderJson.put("pileNo", ConverterUtil.toStr(orderMap.get("pileNo")));
			orderJson.put("gunNo", ConverterUtil.toStr(orderMap.get("gunNo")));
			orderJson.put("cardNo", ConverterUtil.toStr(orderMap.get("cardno")));

			double tradeMoney = ConverterUtil.toDouble(orderMap.get("charge_money"));
			orderJson.put("chargeMoney", ConverterUtil.roundBase(tradeMoney / 100, 2));
			
			String tradeBegin = ConverterUtil.toStr(orderMap.get("trade_begin"));
			orderJson.put("startDate", DateUtil.formatTimesTampDate(DateUtil.parseTimesTampDate(tradeBegin)));
			String tradeEnd = ConverterUtil.toStr(orderMap.get("trade_end"));
			orderJson.put("endDate", DateUtil.formatTimesTampDate(DateUtil.parseTimesTampDate(tradeEnd)));
			arrayItem.add(orderJson);
		}
		
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", arrayItem);
		
		LoggerUtil.logger(LogName.INFO).info("请求获取充电站充电单,返回结果 ： " + rtnJson.toString());
		return rtnJson;
	}
}
