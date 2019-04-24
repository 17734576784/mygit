package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbInstantaneous;
import com.nb.model.NbInstantaneousKey;

/** 
* @ClassName: NbInstantaneousMapper 
* @Description: 瞬时量数据库操作类
* @author dbr
* @date 2019年4月18日 下午4:34:51 
*  
*/
@Mapper
public interface NbInstantaneousMapper {

	/** 
	* @Title: deleteNbInstantaneous 
	* @Description: 删除NB水表瞬时量 
	* @param @param key
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean deleteNbInstantaneous(NbInstantaneousKey key);

	/** 
	* @Title: insertNbInstantaneous 
	* @Description: 插入NB水表瞬时量
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean insertNbInstantaneous(NbInstantaneous record);

	/** 
	* @Title: getNbInstantaneous 
	* @Description: 获取NB水表瞬时量 
	* @param @param key
	* @param @return    设定文件 
	* @return NbInstantaneous    返回类型 
	* @throws 
	*/
	NbInstantaneous getNbInstantaneous(NbInstantaneousKey key);
	
	/** 
	* @Title: getNbInstantaneous 
	* @Description: 获取NB水表瞬时量 
	* @param @param key
	* @param @return    设定文件 
	* @return NbInstantaneous    返回类型 
	* @throws 
	*/
	boolean isExist(NbInstantaneousKey key);

	/** 
	* @Title: updateNbInstantaneous 
	* @Description: 更新NB水表瞬时量 
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateNbInstantaneous(NbInstantaneous record);

}