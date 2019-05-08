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
import com.nb.mapper.JFDayFlowMapper;
import com.nb.mapper.NbBatteryMapper;
import com.nb.mapper.NbDailyDataMapper;
import com.nb.mapper.NbInstantaneousMapper;
import com.nb.model.JFDayFlow;
import com.nb.model.NbBattery;
import com.nb.model.NbDailyData;
import com.nb.model.NbInstantaneous;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
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
	private JFDayFlowMapper jfDayFlowMapper;

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
				flag &= updateDailyBaterry(nbBattery);
			}
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_BATTERY_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			e.printStackTrace();
			LoggerUtil.logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}

		return flag;
	}

	/** 
	* @Title: updateDailyBaterry 
	* @Description: 更新NB水表日数据结构表中的电池电压字段 
	* @param @param nbBattery
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	private boolean updateDailyBaterry(NbBattery nbBattery) throws Exception {

		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setRtuId(nbBattery.getRtuId());
		nbDailyData.setMpId(nbBattery.getMpId());
		nbDailyData.setYmd(nbBattery.getYmd());
		nbDailyData.setHms(nbBattery.getHms());
		nbDailyData.setTableName(ConverterUtils.toStr(nbBattery.getYmd() / 100));
		nbDailyData.setBatteryVoltage(nbBattery.getBatteryVoltage());
		return nbDailyDataMapper.updateNbDailyData(nbDailyData);
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

			if (!nbDailyDataMapper.isExist(nbDailyData)) {
				flag = nbDailyDataMapper.insertNbDailyData(nbDailyData);
			} else {
				flag = nbDailyDataMapper.updateNbDailyData(nbDailyData);
			}
			if (nbDailyData.getTotalFlow() != null) {
				flag &= insertJFDayFlow(nbDailyData);
			}

		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_DAILY_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			e.printStackTrace();
			LoggerUtil.logger(LogName.HISTORYDATA).error(obj.toString() + "存库失败," + e.getMessage());
		}
		return flag;
	}
	
	/** 
	* @Title: insertJFDayFlow 
	* @Description: 插入流量计日流量数据表
	* @param @param nbDailyData
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean insertJFDayFlow(NbDailyData nbDailyData) throws Exception {
		JFDayFlow jfDayFlow = new JFDayFlow();
		jfDayFlow.setTableName(ConverterUtils.toStr(nbDailyData.getYmd() / 100));
		jfDayFlow.setRtuId(nbDailyData.getRtuId());
		jfDayFlow.setMpId(nbDailyData.getMpId());
		jfDayFlow.setDate(nbDailyData.getYmd());

		jfDayFlow.setTime(nbDailyData.getHms());
		jfDayFlow.setLjllZx(nbDailyData.getTotalFlow());
		jfDayFlow.setLlLjllZx(nbDailyData.getDailyPositiveFlow());
		jfDayFlow.setLlLjllFx(nbDailyData.getDailyNegativeFlow());
		if (jfDayFlowMapper.isExist(jfDayFlow)) {
			return true;
		}
		return jfDayFlowMapper.insertJFDayFlow(jfDayFlow);
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
			if (nbInstantaneousMapper.isExist(nbInstantaneous)) {
				flag = nbInstantaneousMapper.updateNbInstantaneous(nbInstantaneous);
			} else {
				flag = nbInstantaneousMapper.insertNbInstantaneous(nbInstantaneous);
			}
		} catch (Exception e) {
			flag = false;
			JedisUtils.lpush(Constant.HISTORY_INSTAN_ERROR_QUEUE, JsonUtil.jsonObj2Sting(obj));
			LoggerUtil.logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}
		return flag;
	}

}
