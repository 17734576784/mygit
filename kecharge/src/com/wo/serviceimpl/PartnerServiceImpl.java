/**
 * 
 */
package com.wo.serviceimpl;

import static com.wo.util.CommFunc.errorInfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wo.mapper.PartnerParaMapper;
import com.wo.service.IPartnerService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;

/**
 * @author dbr
 *
 */
@Service
public class PartnerServiceImpl implements IPartnerService {

	@Autowired
	private PartnerParaMapper partnerParaMapper;
	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerService#listChargeRecord()
	 */
	public JSONObject listChargeRecord() {
		JSONObject rtnJson = new JSONObject();
		JSONObject jsonItem = new JSONObject();
		JSONArray arrayItem = new JSONArray();
		List<Map<String,Object>> partnerList = partnerParaMapper.listChargeRecord();
		for (Map<String, Object> map : partnerList) {
			jsonItem.put("partnerId", CommFunc.objToInt(map.get("id")));
			jsonItem.put("partnerName", CommFunc.objToStr(map.get("describ")));
			jsonItem.put("loginPartneruserName", CommFunc.objToStr(map.get("username")));
			jsonItem.put("loginPartnerpassWord", CommFunc.objToStr(map.get("password")));
			jsonItem.put("loginKEuserName", CommFunc.objToStr(map.get("partnername")));
			jsonItem.put("loginKEpassWord", CommFunc.objToStr(map.get("passwd")));

			arrayItem.add(jsonItem);
			jsonItem.clear();
		}
		rtnJson = errorInfo(Constant.OK, "");
		rtnJson.put("rows", arrayItem);
		return rtnJson;
	}

}
