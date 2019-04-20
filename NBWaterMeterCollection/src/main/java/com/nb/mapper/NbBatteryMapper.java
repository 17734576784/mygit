package com.nb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nb.model.NbBattery;
import com.nb.model.NbBatteryKey;

/** 
* @ClassName: NbBatteryMapper 
* @Description: NB水表电池电压数据库操作类 
* @author dbr
* @date 2019年4月18日 下午4:34:34 
*  
*/
@Mapper
public interface NbBatteryMapper {

	/** 
	* @Title: deleteNbBattery 
	* @Description: 删除水表电池电压 
	* @param @param key
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean deleteNbBattery(NbBatteryKey key);

	/** 
	* @Title: insertNbBattery 
	* @Description: 插入 电池电压
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean insertNbBattery(NbBattery record);

	/** 
	* @Title: getNbBattery 
	* @Description: 获取电池电压 
	* @param @param key
	* @param @return    设定文件 
	* @return NbBattery    返回类型 
	* @throws 
	*/
	NbBattery getNbBattery(NbBatteryKey key);

	/** 
	* @Title: updateNbBattery 
	* @Description: 更新电池电压 
	* @param @param record
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	boolean updateNbBattery(NbBattery record);

}