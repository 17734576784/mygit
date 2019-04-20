package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbCommand;
import com.nb.model.NbCommandKey;

/** 
* @ClassName: NbCommandMapper 
* @Description: NB命令数据库操作类
* @author dbr
* @date 2019年4月18日 下午4:34:40 
*  
*/
@Mapper
public interface NbCommandMapper {

	/** 
	* @Title: deleteNbCommand 
	* @Description: 删除Nb命令
	* @param @param key
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean deleteNbCommand(NbCommandKey key);

	/** 
	* @Title: insertNbCommand 
	* @Description: 插入Nb命令
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean insertNbCommand(NbCommand record);

	/** 
	* @Title: getNbCommand 
	* @Description: 获取NB命令
	* @param @param key
	* @param @return    设定文件 
	* @return NbCommand    返回类型 
	* @throws 
	*/
	NbCommand getNbCommand(NbCommandKey key);

	/** 
	* @Title: updateNbCommand 
	* @Description: 更新NB命令
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateNbCommand(NbCommand record);

}