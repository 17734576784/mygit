/**
 * 
 */
package com.wo.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dbr
 * 
 */
public interface ChargeMapper {
	// 判断流水号是否已用
	Boolean checkWasteno(String serialNumber);

	// 判断枪类型
	Boolean checkGunType(Map<String, Object> param);
	
	/**
	 * 获取充电桩CP标志
	 * */
	Byte loadPileCPFlag(Map<String, Object> param); 

	// 判断枪类型
	Integer getGunState(Map<String, Object> param);

	// 根据桩体号查询所属终端和通道参数
	Map<String, Object> queryRtuParaByPileNo(String pileNo);

	// 获取充电过程数据
	Map<String, Object> getChargeData(String serialNumber);
	
	// 获取充电记录
	Map<String, Object> getPileChargeRcd(Map<String, Object> param);
	
	// 轮询推送充电结束记录
	List<Map<String, Object>> listPileChargeRcd(Map<String, Object> param);
	
	//通过充电桩Id获取充电桩编号
	String getPileNoById(Map<String, Object> param);
	
	//更新充电记录同步标志
	boolean updateChargeRcd(Map<String, Object> param);
	
	
	/** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
	* <p>
	* <p>NAME        : 	
	* <p>DESCRIPTION : 	充电过程实时数据
	* <p>INPUT       : 
	* <p>OUTPUT      : 
	* <p>RETURN      : Map<String,Object>
	* <p>
	*-----------------------------------------------------------*/
	Map<String, Object> getChargeRealData(String serialNumber);
	
	Integer getPartnerIdBySerialNumber(String serialNumber);	
	
	List<Map<String,Object> >getPileChargeRecord(Map<String, Object> param);
	
}
