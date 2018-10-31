/**
 * 
 */
package com.wo.serviceimpl;
import static com.wo.util.CommFunc.errorInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wo.mapper.ChargeRecordMapper;
import com.wo.model.ChargeRecord;
import com.wo.service.IChargeRecordService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
@Service
public class ChargeRecordServiceImpl implements IChargeRecordService {

	@Autowired
	private ChargeRecordMapper chargeRecordMapper;
	/* (non-Javadoc)
	 * @see com.wo.service.IChargeRecordService#listChargeRecord()
	 */
	@SuppressWarnings("unchecked")
	public JSONObject listChargeRecord(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		JSONObject jsonItem = new JSONObject();
		JSONArray arrayItem = new JSONArray();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			int beginYear = json.getInt("beginDate") / 10000;
			int endYear = json.getInt("endDate") / 10000;
			
			String tableName = "cpdata.charge_record" + beginYear;
			String lastTableName = "";
			if (beginYear != endYear) {
				lastTableName = "cpdata.charge_record" + endYear;
			}
			
			param.put("tableName", tableName);
			param.put("lastTableName", lastTableName);
			param.putAll(json);
			
			List<ChargeRecord> chargeRecordList = chargeRecordMapper.listChargeRecordMonitor(param);
			for (ChargeRecord chargeRecord : chargeRecordList) {
				
				jsonItem.put("serialnumber", chargeRecord.getSerialnumber());
				jsonItem.put("charge_money", chargeRecord.getChargeMoney());
				jsonItem.put("pile_code", chargeRecord.getPileCode());
				jsonItem.put("gun_id", chargeRecord.getGunId());
				
				jsonItem.put("start_date", formatDate(chargeRecord.getStartDate()));
				jsonItem.put("start_receive_time",  formatDate(chargeRecord.getStartReceiveTime()));
				jsonItem.put("start_flag",  chargeRecord.getStartFlag());
				jsonItem.put("start_push",  chargeRecord.getStartPush());
				
				jsonItem.put("start_push_time",  formatDate(chargeRecord.getStartPushTime()));
				jsonItem.put("end_date",  formatDate(chargeRecord.getEndDate()));
				jsonItem.put("end_receive_time",  formatDate(chargeRecord.getEndReceiveTime()));
				jsonItem.put("end_push",  chargeRecord.getEndPush());
				jsonItem.put("end_push_time",formatDate(chargeRecord.getEndPushTime()));
				
				arrayItem.add(jsonItem);
				jsonItem.clear();
			}

			rtnJson = CommFunc.errorInfo(Constant.OK, "");
			rtnJson.put("rows", arrayItem);
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "获取充电记录监控列表异常");
			Log4jUtil.getError().error("获取充电记录监控列表异常" + queryJsonStr, e);
			e.printStackTrace();
		}
		
		return rtnJson;
	}
	
	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return date == null ? "" : sdf.format(date);
	}

}
