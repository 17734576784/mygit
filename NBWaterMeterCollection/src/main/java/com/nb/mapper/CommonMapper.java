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

/** 
* @ClassName: CommonMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月10日 下午4:10:12 
*  
*/
@Mapper
public interface CommonMapper {
	
	Map<String, Object> getNbInfoByDeviceId(String deviceId);
}
