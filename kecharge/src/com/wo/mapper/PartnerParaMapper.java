package com.wo.mapper;

import java.util.List;
import java.util.Map;

import com.wo.model.PartnerPara;

public interface PartnerParaMapper {
	// 通过登录名获取合作伙伴
	PartnerPara getPartnerPara(String loginName);

	List<Map<String,Object>> listChargeRecord();
}