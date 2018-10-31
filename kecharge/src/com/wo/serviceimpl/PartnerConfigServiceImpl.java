/**
 * 
 */
package com.wo.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

 import com.wo.mapper.PartnerConfigMapper;
import com.wo.model.PartnerConfig;
import com.wo.service.IPartnerConfigService;
import com.wo.util.Constant;
import com.wo.util.WebConfig;

import static com.wo.util.CommFunc.*;

/**
 * @author dbr
 *
 */
@Service
public class PartnerConfigServiceImpl implements IPartnerConfigService {

	@Resource
	private PartnerConfigMapper partnerConfigMapper;
	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#deletePartnerConfig(java.lang.Integer)
	 */
	public JSONObject deletePartnerConfig(Integer id) {
		JSONObject rtnJson = new JSONObject();
		try {
			PartnerConfig partnerConfig = partnerConfigMapper.getPartnerConfig(id);
			if (null == partnerConfig) {
				rtnJson = errorInfo(Constant.Err, "没有匹配的合作伙伴配置记录！");
				return rtnJson;
			}
			
			boolean flag = partnerConfigMapper.deletePartnerConfig(id);
			if (flag) {
				rtnJson = errorInfo(Constant.OK, "");
				WebConfig.initPartnerConfig(partnerConfig.getPartnerId());
			} else {
				rtnJson = errorInfo(Constant.Err, "删除失败！");
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#insertPartnerConfig(com.wo.model.PartnerConfig)
	 */
	public JSONObject insertPartnerConfig(PartnerConfig partnerConfig) {
		JSONObject rtnJson = new JSONObject();
		try {
			
			if (!isExistCarowner(partnerConfig.getCarownerId())) {
				rtnJson = errorInfo(Constant.Err, "绑定车主不存在！");
				return rtnJson;
			}
			
			boolean flag = partnerConfigMapper.insertPartnerConfig(partnerConfig);
			if (flag) {
				rtnJson = errorInfo(Constant.OK, "");
				WebConfig.initPartnerConfig(partnerConfig.getPartnerId());
			} else {
				rtnJson = errorInfo(Constant.Err, "插入失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rtnJson = errorInfo(Constant.Err, "插入异常");
		}
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#listPartnerConfig(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public JSONObject listPartnerConfig(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(queryJsonStr);
			Map<String, Object> param = new HashMap<String, Object>();
			param.putAll(json);
			
			JSONArray arrays = new JSONArray();
			int index = 1;
			List<Map<String, Object>> partnerConfigList = partnerConfigMapper.listPartnerConfigArchive(param);
			for (Map<String, Object> map : partnerConfigList) {
				json.put("id", objToStr(map.get("id")));
				json.put("no", index++);

				json.put("partner_name", objToStr(map.get("describ")));
				json.put("use_flag", objToStr(map.get("use_flag")));
				json.put("username", objToStr(map.get("username")));
				
				json.put("carowner_id", objToStr(map.get("carowner_id")));
				json.put("login_url", objToStr(map.get("login_url")));
				json.put("charge_start_url", objToStr(map.get("charge_start_url")));
				json.put("charge_over_url", objToStr(map.get("charge_over_url")));
				json.put("charge_dc_info_url", objToStr(map.get("charge_dc_info_url")));

				arrays.add(json);
				json.clear();
			}
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("rows", arrays);
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "加载配置合作伙伴列表异常！");		
			e.printStackTrace();
		}
		
 		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#updatePartnerConfig(com.wo.model.PartnerConfig)
	 */
	public JSONObject updatePartnerConfig(PartnerConfig partnerConfig) {
		JSONObject rtnJson = new JSONObject();
		try {
			if (!isExistCarowner(partnerConfig.getCarownerId())) {
				rtnJson = errorInfo(Constant.Err, "绑定车主不存在！");
				return rtnJson;
			}
			boolean flag = partnerConfigMapper.updatePartnerConfig(partnerConfig);
			if (flag) {
				rtnJson = errorInfo(Constant.OK, "");
				WebConfig.initPartnerConfig(partnerConfig.getPartnerId());
			}else {
				rtnJson = errorInfo(Constant.Err, "更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rtnJson = errorInfo(Constant.Err, "更新异常");
		}
		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#getPartnerConfig(java.lang.String)
	 */
	public JSONObject getPartnerConfig(Integer id) {
		JSONObject rtnJson = new JSONObject();
		try {
			PartnerConfig partnerConfig = partnerConfigMapper.getPartnerConfig(id);
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("data", partnerConfig);
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "加载配置合作伙伴异常！");		
			e.printStackTrace();
		}
		
 		return rtnJson;
	}

	/* (non-Javadoc)
	 * @see com.wo.service.IPartnerConfigService#listPartner()
	 */
	public JSONObject listPartner() {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		try {
			JSONArray arrays = new JSONArray();
			List<Map<String, Object>> partnerList = partnerConfigMapper.listPartner();
			for (Map<String, Object> map : partnerList) {
				rtnJson.put("value", objToStr(map.get("id")));
				rtnJson.put("text", objToStr(map.get("describ")));
				arrays.add(rtnJson);
				rtnJson.clear();
			}
			
			rtnJson = errorInfo(Constant.OK, "");
			rtnJson.put("rows", arrays);
		} catch (Exception e) {
			rtnJson = errorInfo(Constant.Err, "加载配置合作伙伴列表异常！");		
			e.printStackTrace();
		}
		
 		return rtnJson;
	}
	
	private boolean isExistCarowner(int carownerId){
		boolean flag = false;
		try {
			flag = partnerConfigMapper.isExistCarowner(carownerId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

}
