package com.ke.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ke.model.MemberOrders;

@Mapper
public interface MemberOrdersMapper {
	
    boolean deletememberOrders(Integer id);

    boolean insertmemberOrders(MemberOrders record);

    MemberOrders getmemberOrders(String payserialNumber);

    boolean updatememberOrders(MemberOrders record);

    Integer getMemberIdByPhone(String phone);

	/** 
	* @Title: getPaySerialNumber 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param chargeSerialNumber
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getPaySerialNumber(String chargeSerialNumber);
}