/**   
* @Title: TaskMapper.java 
* @Package com.nb.mapper 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年4月25日 上午11:51:35 
* @version V1.0   
*/
package com.nb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/** 
* @ClassName: TaskMapper 
* @Description: 任务公共数据库操作类 
* @author dbr
* @date 2019年4月25日 上午11:51:35 
*  
*/
@Mapper
public interface TaskMapper {

	/** 
	* @Title: getBatchCommandByClass 
	* @Description: 根据任命明确获取对应的批量命令信息 
	* @param @param className
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getBatchCommandByClass(String className);
	
	/** 
	* @Title: listDeviceIdByAppModel 
	* @Description: 根据应用设备类型获取为采集成功的deviceId
	* @param @param param
	* @param @return    设定文件 
	* @return List<String>    返回类型 
	* @throws 
	*/
	List<String> listDeviceIdByAppModel(Map<String, Object> param);
}
