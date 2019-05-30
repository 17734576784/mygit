/**   
* @Title: CommonMapper.java 
* @Package com.nb.mapper 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月10日 下午4:10:12 
* @version V1.0   
*/
package com.nb.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nb.model.DeviceInfo;
import com.nb.model.NbWaterMeter;

/** 
* @ClassName: CommonMapper 
* @Description: 公共数据库操作类
* @author dbr
* @date 2019年4月10日 下午4:10:12 
*  
*/
@Mapper
public interface CommonMapper {
	
	/** 
	* @Title: getNbInfoByDeviceId 
	* @Description: 根据设备编号获取水表rtuId mpId
	* @param @param deviceId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getRtuMpIdByDeviceId(String deviceId);
	
	/** 
	* @Title: updateWaterMeterValve 
	* @Description: 更新水表阀门状态和版本信息 
	* @param @param param
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateWaterMeterValve(Map<String, Object> param);
	
	/** 
	* @Title: updateWaterMeter 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param nbWaterMeter
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateWaterMeter(NbWaterMeter nbWaterMeter);
	
	/** 
	* @Title: updateDeviceIdByImei 
	* @Description: 根据imei更新水表的deviceId 
	* @param @param param
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateDeviceIdByImei(@Param("deviceId") String deviceId, @Param("imei") String imei);
	
	/** 
	* @Title: getCommand 
	* @Description: 根据rtuId mpId commandId获取对应的命令参数
	* @param @param param
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	Map<String, String> getCommand(Map<String, String> param);
	
	/** 
	* @Title: getDeviceInfo 
	* @Description: 根据rtuId mpId 获取水表设备的基本信息（app信息 设备类型 imei等） 
	* @param @param param
	* @param @return    设定文件 
	* @return DeviceInfo    返回类型 
	* @throws 
	*/
	DeviceInfo getDeviceInfo(Map<String, String> param);
	
}
