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
	
	public boolean saveNbBattery(Object obj) {
		boolean flag = false;
		try {
			NbBattery nbBattery = JsonUtil.convertJsonStringToObject(obj.toString(), NbBattery.class);

			if (null == nbBatteryMapper.getNbBattery(nbBattery)) {
				nbBatteryMapper.insertNbBattery(nbBattery);
			}
		} catch (Exception e) {
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}

		return flag;
	}

	public boolean saveDailyData(Object obj) {
		boolean flag = false;
		try {
			NbDailyData nbDailyData = JsonUtil.convertJsonStringToObject(obj.toString(), NbDailyData.class);

			if (null == nbDailyDataMapper.getNbDailyData(nbDailyData)) {
				nbDailyDataMapper.insertNbDailyData(nbDailyData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}

		return flag;
	}
	
	public boolean saveInstanceData(Object obj) {
		boolean flag = false;
		try {
			NbInstantaneous nbInstantaneous = JsonUtil.convertJsonStringToObject(obj.toString(), NbInstantaneous.class);

			if (null == nbInstantaneousMapper.getNbInstantaneous(nbInstantaneous)) {
				nbInstantaneousMapper.insertNbInstantaneous(nbInstantaneous);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.Logger(LogName.CALLBACK).info(obj.toString() + "存库失败");
		}

		return flag;
	}

}

