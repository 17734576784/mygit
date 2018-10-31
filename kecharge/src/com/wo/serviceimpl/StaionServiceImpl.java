/**
 * 
 */
package com.wo.serviceimpl;

import java.util.List;
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
				
				jsonItem.put("stationNo", CommFunc.objToStr(station.getId()));
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

}
