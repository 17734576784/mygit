/**   
* @Title: OperatorConfigImpl.java 
* @Package com.ke.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午2:36:03 
* @version V1.0   
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
import com.ke.mapper.MemberOrdersMapper;
import com.ke.mapper.OperatorConfigMapper;
import com.ke.model.OperatorConfig;
import com.ke.service.IOperatorConfigService;
import com.ke.utils.ConverterUtil;
import com.ke.utils.JedisUtil;

/** 
* @ClassName: OperatorConfigImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年1月10日 下午2:36:03 
*  
*/
@Service
public class OperatorConfigServiceImpl implements IOperatorConfigService {
	@Resource
	private OperatorConfigMapper operatorConfigMapper;
	@Resource
	private MemberOrdersMapper memberOrdersMapper;

	/** (非 Javadoc) 
	* <p>Title: insertOperatorConfig</p> 
	* <p>Description: </p> 
	* @param operatorConfig
	* @return
	* @throws Exception 
	* @see com.ke.service.IOperatorConfigService#insertOperatorConfig(com.ke.model.OperatorConfig) 
	*/
	@Override
	public JSONObject insertOperatorConfig(OperatorConfig operatorConfig) throws Exception {
		// TODO Auto-generated method stub
		return addOrUpdateOperatorConfig(operatorConfig);
	}

	private JSONObject addOrUpdateOperatorConfig(OperatorConfig operatorConfig) {
		Integer memberId = this.memberOrdersMapper.getMemberIdByPhone(operatorConfig.getMemberPhone());
		if (null == memberId) {
			JSONObject rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "会员手机号不存在");
			return rtnJson;
		}

		Map<String, Object> param = new HashMap<String, Object>(2);
		param.put("userName", operatorConfig.getOperatorLoginname());
		param.put("operatorId", operatorConfig.getOperatorId());

		Integer userId = this.operatorConfigMapper.checkOperatorUsername(param);
		if (null == userId) {
			JSONObject rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "该运营商下用户不存在");
			return rtnJson;
		}
		operatorConfig.setMemberId(memberId);
		if (operatorConfig.getId() == null) {
			boolean flag = operatorConfigMapper.insertOperatorConfig(operatorConfig);
			if (flag) {
				CommFunc.initOperatorConfig(operatorConfig);
				operatorConfigMapper.insertUserRole(userId);
			}
			return resultInfo("添加运营商配置", flag);
		} else {
			boolean flag = operatorConfigMapper.updateOperatorConfig(operatorConfig);
			if (flag) {
				CommFunc.initOperatorConfig(operatorConfig);
				operatorConfigMapper.insertUserRole(userId);
			}
			return resultInfo("修改运营商配置", flag);
		}

	}
	private JSONObject resultInfo(String operate, boolean flag) {
		JSONObject rtnJson = new JSONObject();
		int status = flag == true ? Constant.SUCCESS : Constant.REQUEST_BAD;
		String errorInfo = flag == true ? operate + "执行成功" : operate + "执行失败";
		rtnJson = CommFunc.errorInfo(status, errorInfo);
		return rtnJson;
	}
	
	/** (非 Javadoc) 
	* <p>Title: updateOperatorConfig</p> 
	* <p>Description: </p> 
	* @param operatorConfig
	* @return
	* @throws Exception 
	* @see com.ke.service.IOperatorConfigService#updateOperatorConfig(com.ke.model.OperatorConfig) 
	*/
	@Override
	public JSONObject updateOperatorConfig(OperatorConfig operatorConfig) throws Exception {
		// TODO Auto-generated method stub
		return addOrUpdateOperatorConfig(operatorConfig);
		
	}

	/** (非 Javadoc) 
	* <p>Title: deleteOperatorConfig</p> 
	* <p>Description: </p> 
	* @param id
	* @return
	* @throws Exception 
	* @see com.ke.service.IOperatorConfigService#deleteOperatorConfig(java.lang.Integer) 
	*/
	@Override
	public JSONObject deleteOperatorConfig(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> operatorConfig = this.operatorConfigMapper.getOperatorConfig(id);
		boolean flag = operatorConfigMapper.deleteOperatorConfig(id);
		if (flag) {
			String key = Constant.OPERATORCONFIG_PREFIX + operatorConfig.get("id");
			JedisUtil.del(key.getBytes());
			key = Constant.SERIALNUMBER_PREFIX;
			JedisUtil.hdel(key, operatorConfig.get("serialnumberPrefix").toString());
		}
		return resultInfo("删除运营商配置", flag);
	}

	/** (非 Javadoc) 
	* <p>Title: listOperatorConfig</p> 
	* <p>Description: </p> 
	* @param queryJsonStr
	* @return
	* @throws Exception 
	* @see com.ke.service.IOperatorConfigService#listOperatorConfig(java.lang.String) 
	*/
	@Override
	public JSONObject listOperatorConfig(String queryJsonStr) throws Exception {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		JSONObject json = JSONObject.parseObject(queryJsonStr);
		Map<String, Object> param = new HashMap<String, Object>();
		param.putAll(json);
		
		List<Map<String, Object>> operatorConfigList = operatorConfigMapper.listOperatorConfigArchive(param);
		if (null == operatorConfigList || operatorConfigList.isEmpty()) {
			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "无运营商配置信息");
			return rtnJson;
		}
		
		List<JSONObject> operatorConfigs = operatorConfigList.stream().filter(map -> map != null)
				.map(map -> getOperatorConfig(map)).collect(Collectors.toList());

		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", operatorConfigs);
		return rtnJson;	}
	
	/** 
	* @Title: getOperatorConfig 
	* @Description: 组建运营商配置信息 
	* @param @param map
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private JSONObject getOperatorConfig(Map<String, Object> map) {
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("id", ConverterUtil.toStr(map.get("id")));
		jsonItem.put("operatorName", ConverterUtil.toStr(map.get("operatorName")));
		jsonItem.put("operatorLoginname", ConverterUtil.toStr(map.get("operatorLoginname")));
		jsonItem.put("useFlag", ConverterUtil.toStr(map.get("useFlag")));
		jsonItem.put("userName", ConverterUtil.toStr(map.get("username")));
		jsonItem.put("serialnumberPrefix", ConverterUtil.toStr(map.get("serialnumber_prefix")));
		jsonItem.put("memberPhone", ConverterUtil.toStr(map.get("memberPhone")));

		jsonItem.put("loginUrl", ConverterUtil.toStr(map.get("login_url")));
		jsonItem.put("chargeStartUrl", ConverterUtil.toStr(map.get("charge_start_url")));
		jsonItem.put("chargeOverUrl", ConverterUtil.toStr(map.get("charge_over_url")));
		jsonItem.put("chargeDcInfoUrl", ConverterUtil.toStr(map.get("charge_dc_info_url")));
		return jsonItem;
	}

	
	/** (非 Javadoc) 
	* <p>Title: getOperatorConfig</p> 
	* <p>Description: </p> 
	* @param id
	* @return
	* @throws Exception 
	* @see com.ke.service.IOperatorConfigService#getOperatorConfig(java.lang.Integer) 
	*/
	@Override
	public JSONObject getOperatorConfig(Integer id) throws Exception {
		JSONObject rtnJson = new JSONObject();
		Map<String,Object> operatorConfig = this.operatorConfigMapper.getOperatorConfig(id);
 		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("data", operatorConfig);
		return rtnJson;
	}

	/** (非 Javadoc) 
	* <p>Title: listOperator</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.ke.service.IOperatorConfigService#listOperator() 
	*/
	@Override
	public JSONObject listOperator() {
		// TODO Auto-generated method stub
		JSONObject rtnJson = new JSONObject();
		JSONArray arrays = new JSONArray();
		List<Map<String, Object>> partnerList = operatorConfigMapper.listOperator();
		for (Map<String, Object> map : partnerList) {
			JSONObject item = new JSONObject();
			item.put("partnerId", ConverterUtil.toInt(map.get("id")));
			item.put("partnerName", ConverterUtil.toStr(map.get("name")));
			item.put("loginPartneruserName", ConverterUtil.toStr(map.get("thirdusername")));
			item.put("loginPartnerpassWord", ConverterUtil.toStr(map.get("thirdpassword")));
			item.put("loginKEuserName", ConverterUtil.toStr(map.get("keusername")));
			item.put("loginKEpassWord", ConverterUtil.toStr(map.get("kepassword")));

			arrays.add(item);
		}
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", arrays);
		return rtnJson;
	}

	/** (非 Javadoc) 
	* <p>Title: listOperatorTest</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.ke.service.IOperatorConfigService#listOperatorTest() 
	*/
	@Override
	public JSONObject listOperatorTest() {
		JSONObject rtnJson = new JSONObject();
		JSONArray arrays = new JSONArray();
		List<Map<String, Object>> partnerList = operatorConfigMapper.listOperatorTest();
		for (Map<String, Object> map : partnerList) {
			JSONObject item = new JSONObject();
			item.put("partnerId", ConverterUtil.toInt(map.get("id")));
			item.put("partnerName", ConverterUtil.toStr(map.get("name")));
			item.put("loginPartneruserName", ConverterUtil.toStr(map.get("thirdusername")));
			item.put("loginPartnerpassWord", ConverterUtil.toStr(map.get("thirdpassword")));
			item.put("loginKEuserName", ConverterUtil.toStr(map.get("keusername")));
			item.put("loginKEpassWord", ConverterUtil.toStr(map.get("kepassword")));

			arrays.add(item);
		}
		rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
		rtnJson.put("rows", arrays);
		return rtnJson;
	}
}
