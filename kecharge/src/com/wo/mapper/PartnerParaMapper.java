package com.wo.mapper;

import com.wo.model.PartnerPara;

public interface PartnerParaMapper {
	// 通过登录名获取合作伙伴
	PartnerPara getPartnerPara(String loginName);

}