/**   
* @Title: HistoryDatabaseExecutor.java 
* @Package com.nb.customer.historydatabase 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 上午9:57:08 
* @version V1.0   
*/
package com.nb.customer.historydatabase;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.NbBatteryMapper;
import com.nb.mapper.NbDailyDataMapper;
import com.nb.mapper.NbInstantaneousMapper;
import com.nb.model.NbBattery;
import com.nb.model.NbDailyData;
import com.nb.model.NbInstantaneous;
import com.nb.utils.Constant;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;

/**
 * @ClassName: HistoryDatabaseExecutor
 * @Description: 历史库存储执行类
 * @author dbr
 * @date 2019年3月11日 上午9:57:08
 * 
 */
@Component
public class HistoryDatabaseExecutor {

	@Resource
	private NbBatteryMapper nbBatteryMapper;

	@Resource
	private NbDailyDataMapper nbDailyDataMapper;

	@Resource
	private NbInstantaneousMapper nbInstantaneousMapper;

	/** 
	* @Title: saveNbBattery 
	* @Description: 数据库持久化水表电池电压
	* @param @param obj
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean saveNbBattery(Object obj) {
		boolean flag = true;
		try {
			NbBattery nbBattery = JsonUtil.convertJsonStringToObject(obj.toString(), NbBattery.class);

			if (null == nbBatteryMapper.getNbBattery(nbBattery)) {
				flag = nbBatteryMapper.insertNbBattery(nbBattery);
			}
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_BATTERY_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			e.printStackTrace();
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}

		return flag;
	}

	/** 
	* @Title: saveDailyData 
	* @Description: 数据库持久化水表日数据 
	* @param @param obj
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean saveDailyData(Object obj) {
		boolean flag = true;
		try {
			NbDailyData nbDailyData = JsonUtil.convertJsonStringToObject(obj.toString(), NbDailyData.class);

			if (null == nbDailyDataMapper.getNbDailyData(nbDailyData)) {
				flag = nbDailyDataMapper.insertNbDailyData(nbDailyData);
			}
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_DAILY_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			e.printStackTrace();
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}
		return flag;
	}

	/** 
	* @Title: saveInstanceData 
	* @Description: 数据库持久化瞬时量 
	* @param @param obj
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean saveInstanceData(Object obj) {
		boolean flag = true;
		try {
			NbInstantaneous nbInstantaneous = JsonUtil.convertJsonStringToObject(obj.toString(), NbInstantaneous.class);

			if (null == nbInstantaneousMapper.getNbInstantaneous(nbInstantaneous)) {
				flag = nbInstantaneousMapper.insertNbInstantaneous(nbInstantaneous);
			}
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_INSTAN_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}
		return flag;
	}

}
