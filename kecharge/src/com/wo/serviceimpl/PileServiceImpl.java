/**
 * 
 */
package com.wo.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wo.mapper.ChargeMapper;
import com.wo.mapper.PileMapper;
import com.wo.service.IPileService;
import static com.wo.util.CommFunc.*;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
@Service
public class PileServiceImpl implements IPileService{

	@Resource
	private PileMapper pileMapper;
	
	@Resource
	private ChargeMapper chargeMapper;
	
	/* (non-Javadoc)
	 * @see com.wo.service.IPileService#getPileState(java.lang.String)
	 */
	public JSONObject getPileState(String queryJsonStr) {
		
		JSONObject rtnJson = new JSONObject();
		Log4jUtil.getInfo().info("接送请求获取充电桩状态请求 ： "+queryJsonStr);

		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String pileNo = json.optString("pileNo");
			if (pileNo.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			String pileState = "", gunState = "";
			int gunNum = 0, gunId = 0, gunType = 0;
			JSONObject jsonItem = new JSONObject();
			JSONArray arrayItem = new JSONArray();
			
			List<Map<String, Object>> list = this.pileMapper.getPileState(pileNo);
			for (Map<String,Object> map : list) {
				if (map == null) {
					continue;
				}
				gunId = objToInt(map.get("gunId"));

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("pileNo", pileNo);
				param.put("gunNo", gunId);
				byte cpFlag = objToByte(this.chargeMapper.loadPileCPFlag(param));

				// 充电桩状态，取值：1=正常（0 无设置 1 初始态, 2 空闲态, 3 使用态）；2=故障（ 4  故障态）,
				pileState = objToStr(map.get("pileState")).equals("4") ? "2" : "1";
				
				gunState = objToStr(map.get("gunState"));
				gunNum = objToInt(map.get("gunNum"));
				if (gunId <= gunNum) {
					gunType = 0; // 电动汽车
				} else {
					gunType = 1; // 电动单车
				}
				
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
				} else if ((gunState.equals("0") || gunState.equals("1")  || gunState.equals("55"))
						&& (cpFlag == 1 && gunType == 0)) {
					gunState = "2";
				} else if ((gunState.equals("2") || gunState.equals("0") || gunState.equals("55") || gunState.equals("1"))) {
					if (cpFlag == 0 || (cpFlag == 1 && gunType == 1)) {
						gunState = "2";
					}
				}

				jsonItem.put("gunNo", gunId);
				jsonItem.put("gunType", gunType);
				jsonItem.put("gunStatus", gunState);
				
				arrayItem.add(jsonItem);
				jsonItem.clear();
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("pileStatus", pileState);
			rtnJson.put("rows", arrayItem);
			
			Log4jUtil.getInfo().info("请求获取充电桩状态,返回结果 ： "+rtnJson.toString());
			
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "获取指定充电桩状态异常");
			e.printStackTrace();
			Log4jUtil.getError().error("获取指定充电桩状态异常" + queryJsonStr, e);
		}
		
		return rtnJson;
	}
	
	public JSONObject getPileGps(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		Log4jUtil.getInfo().info("请求获取充电桩GPS ： "+queryJsonStr);
		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String pileNo = json.optString("pileNo");
			if (pileNo.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			Map<String, Object> pileGps = this.pileMapper.getPileGps(pileNo);
			if (pileGps == null) {
				rtnJson = errorInfo(Constant.Err, "没有查询到该充电站的位置信息");
				return rtnJson;
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("lng", objToDbl(pileGps.get("longitude")));
			rtnJson.put("lat", objToDbl(pileGps.get("latitude")));
			
		} catch (Exception e) {
			// TODO: handle exception
			rtnJson = errorInfo(Constant.Err, "获取指定充电桩Gps异常");
			e.printStackTrace();
			Log4jUtil.getError().error("获取指定充电桩Gps异常" + queryJsonStr, e);
		}
		
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPileService#getPileRate(java.lang.String)
	 */
	public JSONObject getPileRate(String queryJsonStr) {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		Log4jUtil.getInfo().info("请求获取充电桩费率 ： "+queryJsonStr);
		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			String pileNo = json.optString("pileNo");
			if (pileNo.isEmpty()) {
				rtnJson = errorInfo(Constant.Err, "请求参数错误");
				return rtnJson;
			}
			
			Map<String, Object> pileRate = this.pileMapper.getPileRate(pileNo);
			if (pileRate == null) {
				rtnJson = errorInfo(Constant.Err, "该充电桩未配置费率！");
				return rtnJson;
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			int rateType = objToInt(pileRate.get("RateType"));
			String SharpRate = "", PeakRate = "", FlatRate = "", ValleyRate = "";
			if (rateType == Constant.SINGLERATE) {
				SharpRate = roundTosString(objToDbl(pileRate.get("rateZ")), 2);
				PeakRate = roundTosString(objToDbl(pileRate.get("rateZ")), 2);
				FlatRate = roundTosString(objToDbl(pileRate.get("rateZ")), 2);
				ValleyRate = roundTosString(objToDbl(pileRate.get("rateZ")), 2);
			} else if (rateType == Constant.COMPLEXRATE) {
				SharpRate = roundTosString(objToDbl(pileRate.get("SharpRate")), 2);
				PeakRate = roundTosString(objToDbl(pileRate.get("PeakRate")), 2);
				FlatRate = roundTosString(objToDbl(pileRate.get("FlatRate")), 2);
				ValleyRate = roundTosString(objToDbl(pileRate.get("ValleyRate")), 2);
			}
			
			rtnJson.put("pileNo", pileNo);
			rtnJson.put("rateType", rateType);
			rtnJson.put("SharpRate", SharpRate);
			rtnJson.put("PeakRate", PeakRate);
			rtnJson.put("FlatRate", FlatRate);
			rtnJson.put("ValleyRate", ValleyRate);
			rtnJson.put("ServiceCharge", roundTosString(objToDbl(pileRate.get("ServiceCharge")), 2));
			
			
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "获取指定充电桩费率异常");
			e.printStackTrace();
			Log4jUtil.getError().error("获取指定充电桩费率异常" + queryJsonStr, e);
		}
		
		return rtnJson;
	}
}
