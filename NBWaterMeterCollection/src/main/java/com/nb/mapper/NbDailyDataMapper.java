package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbDailyData;
import com.nb.model.NbDailyDataKey;

/** 
* @ClassName: NbDailyDataMapper 
* @Description: NB日数据数据库操作类 
* @author dbr
* @date 2019年4月18日 下午4:34:46 
*  
*/
@Mapper
public interface NbDailyDataMapper {
	
    /** 
    * @Title: deleteNbDailyData 
    * @Description: 删除NB日数据 
    * @param @param key
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean deleteNbDailyData(NbDailyDataKey key);

    /** 
    * @Title: insertNbDailyData 
    * @Description: 插入Nb日数据 
    * @param @param record
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean insertNbDailyData(NbDailyData record);

    /** 
    * @Title: getNbDailyData 
    * @Description: 获取nb日数据
    * @param @param key
    * @param @return    设定文件 
    * @return NbDailyData    返回类型 
    * @throws 
    */
    NbDailyData getNbDailyData(NbDailyDataKey key);

    /** 
    * @Title: updateNbDailyData 
    * @Description: 更新Nb日数据
    * @param @param record
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    boolean updateNbDailyData(NbDailyData record);

}