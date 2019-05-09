package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.Eve;

/** 
* @ClassName: EveMapper 
* @Description: 异常事项存储操作类 
* @author dbr
* @date 2019年4月18日 下午4:34:21 
*  
*/
@Mapper
public interface EveMapper {

    /** 
    * @Title: insertEve 
    * @Description: 数据库插入异常事项 
    * @param @param record
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean insertEve(Eve record);
    
    
	/** 
	* @Title: isExist 
	* @Description: 判断事项是否存在
	* @param @param Eve
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean isExist(Eve record);
}