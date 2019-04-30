/**
 * 
 */
package com.ke.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.PileMapper;
import com.ke.service.IPileService;
import com.ke.utils.ConverterUtil;

import static com.ke.utils.ConverterUtil.*;

/**
 * @author dbr
 *
 */
@Service
public class PileServiceImpl implements IPileService{

	@Resource
	private PileMapper pileMapper;
	
	/* (non-Javadoc)
	 * @see com.wo.service.IPileService#getPileState(java.lang.String)
	 */
	public JSONObject getPileState(String queryJsonStr) throws Exception {

		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("接送请求获取充电桩状态请求 ： " + queryJsonStr);

		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String pileNo = json.getString("pileNo");
		if (null == pileNo || pileNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		List<Map<String, Object>> list = this.pileMapper.getPileState(pileNo);
		if (null == list || list.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "无数据");
			return rtnJson;
		}

		// 充电桩状态，取值：1=正常（0 无设置 1 初始态, 2 空闲态, 3 使用态）；2=故障（ 4 故障态）,
		String pileState = toStr(list.get(0).get("pileState")).equals("4") ? "2" : "1";

		List<JSONObject> gunStateList = list.stream().filter(map -> map != null).map(map -> formatPileState(map))
				.collect(Collectors.toList());

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("pileStatus", pileState);
		rtnJson.put("rows", gunStateList);

		LoggerUtil.logger(LogName.INFO).info("请求获取充电桩状态,返回结果 ： " + rtnJson.toString());

		return rtnJson;
	}
	
	private JSONObject formatPileState(Map<String, Object> map) {
		String gunState = "";
		int gunNum = 0, gunId = 0, gunType = 0;

		gunId = toInt(map.get("gunId"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pileNo", toStr(map.get("pileNo")));
		param.put("gunNo", gunId);
		byte cpFlag = toByte(map.get("cpFlag"));

		gunState = toStr(map.get("gunState"));
		gunNum = toInt(map.get("gunNum"));
		if (gunId <= gunNum) {
			gunType = 0; // 电动汽车
		} else {
			gunType = 1; // 电动单车
		}

		/**
		 * 充电枪状态，取值：5=异常（5 报警 51 故障 52 维修 53 离线' ）2=待机:（ 0无设置 1 空闲, 2 连接 55
		 * 准备充电,）；3=充电:（3 充电）；4=充满 :(4 充满)
		 * 
		 * 充电枪状态，取值： 5=异常（5 报警 51 故障 52 维修 53 离线'）； 1:（ 0无设置 1 空闲） 空闲 2: 连接 ； 6
		 * :待机 3=充电:（3 充电）； 4=充满 :(4 充满)
		 */
		if (gunState.equals("5") || gunState.equals("51") || gunState.equals("52") || gunState.equals("53")) {
			gunState = "1";
		} else if ((gunState.equals("0") || gunState.equals("1") || gunState.equals("55"))
				&& (cpFlag == 1 && gunType == 0)) {
			gunState = "2";
		} else if ((gunState.equals("2") || gunState.equals("0") || gunState.equals("55") || gunState.equals("1"))) {
			if (cpFlag == 0 || (cpFlag == 1 && gunType == 1)) {
				gunState = "2";
			}
		}
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("gunNo", gunId);
		jsonItem.put("gunType", gunType);
		jsonItem.put("gunStatus", gunState);

		return jsonItem;
	}
	
	
	public JSONObject getPileGps(String queryJsonStr) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("请求获取充电桩GPS ： " + queryJsonStr);
		try {
			JSONObject json = JSONObject.parseObject(queryJsonStr);
			String pileNo = json.getString("pileNo");
			if (null == pileNo || pileNo.isEmpty()) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
				return rtnJson;
			}

			Map<String, Object> pileGps = this.pileMapper.getPileGps(pileNo);
			if (pileGps == null) {
				rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "没有查询到该充电站的位置信息");
				return rtnJson;
			}

			rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
			rtnJson.put("lng", toDouble(pileGps.get("longitude")));
			rtnJson.put("lat", toDouble(pileGps.get("latitude")));
			LoggerUtil.logger(LogName.INFO).info("请求获取充电桩GPS,返回结果： " + rtnJson);

		} catch (Exception e) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "获取指定充电桩Gps异常");
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("获取指定充电桩Gps异常" + queryJsonStr, e);
		}

		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPileService#getPileRate(java.lang.String)
	 */
	public JSONObject getPileRate(String queryJsonStr) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("请求获取充电桩费率 ： " + queryJsonStr);
		
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String pileNo = json.getString("pileNo");
		if (null == pileNo || pileNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		Map<String, Object> pileRate = this.pileMapper.getPileRate(pileNo);
		if (pileRate == null) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "该充电桩未配置费率！");
			return rtnJson;
		}

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		int rateType = toInt(pileRate.get("RateType"));
		String SharpRate = "", PeakRate = "", FlatRate = "", ValleyRate = "";
		String SharpService = "", PeakService = "", FlatService = "", ValleyService = "";

		if (rateType == Constant.SINGLERATE) {
			SharpRate = roundTosString(toDouble(pileRate.get("rateZ")), 4);
			PeakRate = roundTosString(toDouble(pileRate.get("rateZ")), 4);
			FlatRate = roundTosString(toDouble(pileRate.get("rateZ")), 4);
			ValleyRate = roundTosString(toDouble(pileRate.get("rateZ")), 4);
			String ServiceCharge = roundTosString(toDouble(pileRate.get("ServiceCharge")), 4);
			SharpService = PeakService = FlatService = ValleyService = ServiceCharge;
		} else if (rateType == Constant.COMPLEXRATE) {
			SharpRate = roundTosString(toDouble(pileRate.get("SharpRate")), 4);
			PeakRate = roundTosString(toDouble(pileRate.get("PeakRate")), 4);
			FlatRate = roundTosString(toDouble(pileRate.get("FlatRate")), 4);
			ValleyRate = roundTosString(toDouble(pileRate.get("ValleyRate")), 4);
			SharpService = roundTosString(toDouble(pileRate.get("SharpService")), 4);
			PeakService = roundTosString(toDouble(pileRate.get("PeakService")), 4);
			FlatService = roundTosString(toDouble(pileRate.get("FlatService")), 4);
			ValleyService = roundTosString(toDouble(pileRate.get("ValleyService")), 4);
		}

		rtnJson.put("pileNo", pileNo);
		rtnJson.put("rateType", rateType == 0 ? 1 : 4);
		rtnJson.put("SharpRate", SharpRate);
		rtnJson.put("PeakRate", PeakRate);
		rtnJson.put("FlatRate", FlatRate);
		rtnJson.put("ValleyRate", ValleyRate);
		rtnJson.put("ServiceCharge", roundTosString(toDouble(pileRate.get("ServiceCharge")), 4));
		rtnJson.put("SharpService", SharpService);
		rtnJson.put("PeakService", PeakService);
		rtnJson.put("FlatService", FlatService);
		rtnJson.put("ValleyService", ValleyService);

		LoggerUtil.logger(LogName.INFO).info("请求获取充电桩费率 ,返回结果： " + rtnJson);


		return rtnJson;
	}

	/** (非 Javadoc) 
	* <p>Title: listPileInfo</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return
	* @throws Exception 
	* @see com.ke.service.IPileService#listPileInfo(java.lang.String) 
	*/
	@Override
	public JSONObject listPileInfo(String queryJsonStr) throws Exception {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电桩信息 ： " + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String stationNo = json.getString("stationNo");
		if (null == stationNo || stationNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		List<Map<String, Object>> pileInfoList = this.pileMapper.listPileInfo(stationNo);
		if (pileInfoList.size() == 0) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "该充电站下不存在充电桩！");
			return rtnJson;
		}

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", pileInfoList);

		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电桩信息 ,返回结果： " + rtnJson);

		return rtnJson;

	}

	/** (非 Javadoc) 
	* <p>Title: listGunInfo</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return
	* @throws Exception 
	* @see com.ke.service.IPileService#listGunInfo(java.lang.String) 
	*/
	@Override
	public JSONObject listGunInfo(String queryJsonStr) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电枪信息 ： " + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String stationNo = json.getString("stationNo");
		if (null == stationNo || stationNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		List<Map<String, Object>> gunInfoList = this.pileMapper.listGunInfo(stationNo);
		if (gunInfoList.size() == 0) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "该充电站下不存在充电枪！");
			return rtnJson;
		}
		
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", gunInfoList);

		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电枪信息 ,返回结果： " + rtnJson);

		return rtnJson;
	}

	/** (非 Javadoc) 
	* <p>Title: listGunState</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return
	* @throws Exception 
	* @see com.ke.service.IPileService#listGunState(java.lang.String) 
	*/
	@Override
	public JSONObject listGunState(String queryJsonStr) throws Exception {
		JSONObject rtnJson = new JSONObject();
		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电枪状态信息 ： " + queryJsonStr);
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		String stationNo = json.getString("stationNo");
		if (null == stationNo || stationNo.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数错误");
			return rtnJson;
		}

		List<Map<String, Object>> gunStateList = this.pileMapper.listGunState(stationNo);
		if (gunStateList.size() == 0) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "该充电站下不存在充电枪！");
			return rtnJson;
		}
		
		JSONArray gunInfoArray = new JSONArray();
		for (Map<String, Object> gunStateInfo : gunStateList) {
			JSONObject gunJson = new JSONObject();
			gunJson.put("pileNo", ConverterUtil.toStr(gunStateInfo.get("pileNo")));
			gunJson.put("gunId", ConverterUtil.toStr(gunStateInfo.get("gunId")));
			 
			String gunState = ConverterUtil.toStr(gunStateInfo.get("status"));
			/**
			 * 充电枪状态，取值：5=异常（5 报警 51 故障 52 维修 53 离线' ）2=待机:（ 0无设置 1 空闲, 2
			 * 连接 55 准备充电,）；3=充电:（3 充电）；4=充满 :(4 充满)
			 * 
			 * 充电枪状态，取值：
			 * 5=异常（5 报警 51 故障 52 维修 53 离线'）； 
			 * 1:（ 0无设置 1 空闲） 空闲
			 * 2: 连接 ；
			 * 6 :待机
			 * 3=充电:（3 充电）；
			 * 4=充满 :(4 充满)
			 * */
			if (gunState.equals("5") || gunState.equals("51")
					|| gunState.equals("52") || gunState.equals("53")) {
				gunState = "1";
			} else if ((gunState.equals("2") || gunState.equals("0")
					|| gunState.equals("55") || gunState.equals("1"))) {
				gunState = "2";
			}
			gunJson.put("gunStatus", gunState);

			gunInfoArray.add(gunJson);
		}
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", gunInfoArray);
		LoggerUtil.logger(LogName.INFO).info("请求获取特定充电站下充电枪状态信息 ,返回结果： " + rtnJson);

		return rtnJson;
	}
}

