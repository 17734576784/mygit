/**
 * 
 */
package com.wo.serviceimpl;

import static com.wo.util.CommFunc.FormatToYMD;
import static com.wo.util.CommFunc.errorInfo;
import static com.wo.util.CommFunc.intToTime;
import static com.wo.util.CommFunc.round;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wo.mapper.StationMapper;
import com.wo.model.Station;
import com.wo.service.IStaionService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
@Service
public class StaionServiceImpl implements IStaionService{
	
	@Resource
	private StationMapper stationMapper;

	/* (non-Javadoc)
	 * @see com.wo.service.IStaionService#listStationGPS(java.lang.Integer)
	 */
	public JSONObject listStationGPS(Integer partnerId) {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		JSONObject jsonItem = new JSONObject();
		JSONArray arrayItem = new JSONArray();
		if (partnerId == null) {
			rtnJson = CommFunc.errorInfo(Constant.Err, "请求参数错误");
			return rtnJson;
		}
		
		try {
			List<Station> list = this.stationMapper.listStationGPS(partnerId);
			if (list.isEmpty()) {
				rtnJson = CommFunc.errorInfo(Constant.Err, "用户没有创建充电站");
				return rtnJson;
			}
			for (Station station : list) {
				if (station == null) {
					continue;
				}
				
				jsonItem.put("stationNo", CommFunc.objToStr(station.getStationNo()));
				jsonItem.put("stationName",  CommFunc.objToStr(station.getDescrib()));
				jsonItem.put("lng",  CommFunc.objToStr(station.getLongitude()));
				jsonItem.put("lat",  CommFunc.objToStr(station.getLatitude()));
				
				arrayItem.add(jsonItem);
				jsonItem.clear();
			}
			
			rtnJson = CommFunc.errorInfo(Constant.OK, "");
			rtnJson.put("rows", arrayItem);
			
		} catch (Exception e) {
			rtnJson = CommFunc.errorInfo(Constant.Err, "获取充电站GPS信息异常");
			Log4jUtil.getError().error("获取充电站GPS信息异常",e);
			e.printStackTrace();
		}
		
		return rtnJson;
	}

	public JSONObject listChargeOrders(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		Log4jUtil.getInfo().info("接送请求充电站充电单 ： "+queryJsonStr);

		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String stationNo = json.optString("stationNo");
			String startDate = json.optString("startDate");
			String endDate = json.optString("endDate");

			if (stationNo.isEmpty() || stationNo.isEmpty() || endDate.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			JSONArray arrayItem = new JSONArray();
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("stationNo", stationNo);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			
			int startYm = CommFunc.objToInt(startDate) / 100;
			int endYm = CommFunc.objToInt(endDate) / 100;
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
			

			List<Map<String, Object>> orderList = this.stationMapper.listChargeOrders(param);
			for (Map<String,Object> orderMap : orderList) {
				JSONObject orderJson = new JSONObject();
				
				orderJson.put("serialNumber", CommFunc.objToStr(orderMap.get("wasteno")));
				orderJson.put("totalElectricity", CommFunc.objToStr(orderMap.get("chargedl")));
				orderJson.put("pileNo", CommFunc.objToStr(orderMap.get("pileNo")));
				orderJson.put("gunNo", CommFunc.objToStr(orderMap.get("gunNo")));
				orderJson.put("cardNo", CommFunc.objToStr(orderMap.get("cardno")));

				double tradeMoney = CommFunc.objToDbl(orderMap.get("trade_money"));
				orderJson.put("chargeMoney", round(tradeMoney, 2));

				StringBuffer startDateBuf = new StringBuffer();
				startDateBuf.append(FormatToYMD(orderMap.get("trade_bymd"), "day")).append(" ");
				startDateBuf.append(intToTime(orderMap.get("trade_bhms"), 1));

				StringBuffer endDateBuf = new StringBuffer();
				endDateBuf.append(FormatToYMD(orderMap.get("trade_eymd"), "day")).append(" ");
				endDateBuf.append(intToTime(orderMap.get("trade_ehms"), 1));
				
				orderJson.put("startDate", startDateBuf.toString());
				orderJson.put("endDate", endDateBuf.toString());
				arrayItem.add(orderJson);
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("rows", arrayItem);
			
			Log4jUtil.getInfo().info("请求获取充电站充电单,返回结果 ： " + rtnJson.toString());
			
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "获取充电站充电单异常");
			e.printStackTrace();
			Log4jUtil.getError().error("获取充电站充电单异常" + queryJsonStr, e);
		}
		
		return rtnJson;
	}

}
