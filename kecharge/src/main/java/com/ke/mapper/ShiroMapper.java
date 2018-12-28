/**   
* @Title: ShiroMapper.java 
* @Package com.ke.mapper 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月20日 上午11:22:30 
* @version V1.0   
*/
package com.ke.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ShiroMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年12月20日 上午11:22:30
 * 
 */
@Mapper
public interface ShiroMapper {
	List<String> getPermissionByUserName(String username);

	String getPasswordByUserName(String username);

	/** 
	* @Title: listUrlPermission 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return com.ke.configuration.List<Map<String,String>>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> listUrlPermission();
}
