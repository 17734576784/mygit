/**   
* @Title: ChinaMobileSuntrontServiceImpl.java 
* @Package com.nb.service.chinamobileimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月17日 下午2:07:45 
* @version V1.0   
*/
package com.nb.service.chinamobileimpl;

import static com.nb.utils.ConverterUtils.toInt;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.mapper.NbCommandMapper;
import com.nb.model.Eve;
import com.nb.model.NbBattery;
import com.nb.model.NbCommand;
import com.nb.model.NbDailyData;
import com.nb.service.IChinaMobileSuntrontService;
import com.nb.utils.CommandEnum;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.SuntrontProtocolUtil;

/**
 * @ClassName: ChinaMobileSuntrontServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年5月17日 下午2:07:45
 * 
 */
@Service
public class ChinaMobileSuntrontServiceImpl implements IChinaMobileSuntrontService {

	@Resource
	private CommonMapper commonMapper;
	
	@Resource
	private NbCommandMapper nbCommandMapper;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: parseMsg
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param msgJson
	 * @see com.nb.service.IChinaMobileSuntrontService#parseMsg(com.alibaba.fastjson.JSONObject)
	 */
	@Override
	public void parseMsg(JSONObject msgJson) {
		// TODO Auto-generated method stub
		// 标识消息类型
		int msgType = msgJson.getIntValue("type");
		String deviceId = msgJson.getString("dev_id");
		String dsId = msgJson.getString("ds_id");
		long at = msgJson.getLongValue("at");
		String reportDate = DateUtils.formatNoCharDate(new Date(at));
		try {
			// 数据点消息
			if (msgType == Constant.CHINA_MOBILE_DATA_MSG) {
				if (dsId.equals(Constant.SUNTRONTDSID)) {
					JSONObject dataJson = SuntrontProtocolUtil.parseDataMsg(msgJson);
					saveReportData(deviceId, dataJson, reportDate);
				}
			}
			// 下行命令的应答（仅限NB设备）
			else if (msgType == Constant.CHINA_MOBILE_COMMAND_MSG) {
				if (dsId.equals(Constant.SUNTRONTDSID)) {
					parseCommandMsg(msgJson);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("解析新天科技移动平台异常，接收数据{}", msgJson);
		}
		
	}
	
	/** 
	* @Title: parseCommandMsg 
	* @Description: 解析缓存命令下发后结果上报
	* @param @param msgJson    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void parseCommandMsg(JSONObject msgJson) {
		JSONObject confirmBody = msgJson.getJSONObject("confirm_body");
		JSONArray objInst = confirmBody.getJSONArray("obj_inst");
		for (int i = 0; i < objInst.size(); i++) {
			JSONObject res = objInst.getJSONObject(i);
			JSONObject dataJson = SuntrontProtocolUtil.parseCommandMsg(res);
			if (dataJson == null) {
				continue;
			}
			saveCommandData(dataJson, msgJson);

		}
	}
	
	/** 
	* @Title: saveCommandData 
	* @Description: 更新命令执行结果，更新水表阀门状态
	* @param @param dataJson
	* @param @param msgJson    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCommandData(JSONObject dataJson, JSONObject msgJson) {

		JSONObject comm = dataJson.getJSONObject("comm");

		String commandId = msgJson.getString("cmd_id");
		String deviceId = msgJson.getString("dev_id");
		int confirmStatus = msgJson.getIntValue("confirm_status");
		if (confirmStatus == Constant.ZERO) {
			confirmStatus = CommandEnum.SUCCESSFUL.getResultValue();
		} else {
			confirmStatus = CommandEnum.FAILED.getResultValue();
		}

		String tableNameDate = JedisUtils.get(Constant.COMMAND + commandId);
		if (tableNameDate != null) {
			NbCommand nbCommand = new NbCommand();
			nbCommand.setTableName(tableNameDate);
			nbCommand.setCommandId(commandId);
			nbCommand.setExecuteResult((byte) confirmStatus);
			nbCommandMapper.updateNbCommand(nbCommand);
		}

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		/** 更新表计阀门状态 */
		meterInfo.put("valveState", dataJson.getInteger("vavleState"));
		meterInfo.put("version", comm.getString("version"));
		commonMapper.updateWaterMeterValve(meterInfo);
	}
		
	/** 
	* @Title: saveReportData 
	* @Description: 保存上报数据 
	* @param @param deviceId
	* @param @param dataJson
	* @param @param reportDate
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void saveReportData(String deviceId, JSONObject dataJson, String reportDate) throws Exception{
		if (dataJson == null || dataJson.isEmpty()) {
			return;
		}
		String logInfo = "移动平台上报新天科技,内容：" + dataJson.toJSONString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);

		Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
		if (meterInfo == null) {
			return;
		}

		saveData(dataJson, meterInfo, reportDate);
		saveAlarm(dataJson, meterInfo, reportDate);
	}

	/** 
	* @Title: saveAlarm 
	* @Description: 保存异常事项 
	* @param @param dataJson
	* @param @param meterInfo
	* @param @param reportDate
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void saveAlarm(JSONObject dataJson, Map<String, Object> meterInfo, String reportDate) throws Exception {
		JSONObject comm = dataJson.getJSONObject("comm");
		JSONObject st0 = comm.getJSONObject("st0");
		JSONObject st2 = comm.getJSONObject("st2");
		String tableNameDate = reportDate.substring(0, 6);
		int ymd = toInt(reportDate.substring(0, 8));
		int hms = toInt(reportDate.substring(9, 15));
		int rtuId = toInt(meterInfo.get("rtuId"));
		int mpId = toInt(meterInfo.get("mpId"));
		
		String eveInfo = "";
		Short typeNo = 0;
		if (st2.getBooleanValue("reverseFlow")) {
			eveInfo = "反流告警";
			typeNo = Constant.ALARM_2003;
			insertEve(rtuId, mpId, tableNameDate, ymd, hms, eveInfo, typeNo);
		}

		if (st0.getBooleanValue("meterBatteryVoltage")) {
			eveInfo = "电池低电压告警";
			typeNo = Constant.ALARM_2005;
			insertEve(rtuId, mpId, tableNameDate, ymd, hms, eveInfo, typeNo);
		}

		if (st0.getBooleanValue("magneticInterference")) {
			eveInfo = "磁干扰告警";
			typeNo = Constant.ALARM_2004;
			insertEve(rtuId, mpId, tableNameDate, ymd, hms, eveInfo, typeNo);
		}
		
		if (st0.getInteger("valveStatus") >= Constant.THREE) {
			eveInfo = "阀门异常";
			typeNo = Constant.ALARM_2010;
			insertEve(rtuId, mpId, tableNameDate, ymd, hms, eveInfo, typeNo);
		}
	}
	
	
	/** 
	* @Title: insertEve 
	* @Description: 插入事项
	* @param @param rtuId
	* @param @param mpId
	* @param @param tableNameDate
	* @param @param ymd
	* @param @param hms
	* @param @param eveInfo
	* @param @param typeNo
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(int rtuId, int mpId, String tableNameDate, int ymd, int hms, String eveInfo, short typeNo) {

		Eve eve = new Eve();
		eve.setTableName(tableNameDate);
		eve.setYmd(ymd);
		eve.setHmsms(hms * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno(typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}

	/** 
	* @Title: saveData 
	* @Description: 保存日数据、电池数据、更新版本信息和阀门状态 
	* @param @param dataJson
	* @param @param meterInfo
	* @param @param reportDate
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void saveData(JSONObject dataJson, Map<String, Object> meterInfo, String reportDate) throws Exception {
		
		JSONObject comm = dataJson.getJSONObject("comm");
		JSONObject st0 = comm.getJSONObject("st0");
		String reportTime = dataJson.getString("reportTime");
		String tableNameDate = reportTime.substring(0, 6);
		int ymd = toInt(reportTime.substring(0, 8));
		int hms = toInt(reportTime.substring(9, 15));
		int rtuId = toInt(meterInfo.get("rtuId"));
		short mpId = ConverterUtils.toShort(meterInfo.get("mpId"));

		/** 插入日数据 data0 */
		NbDailyData nbDailyData = new NbDailyData();
		nbDailyData.setTableName(tableNameDate);
		nbDailyData.setReportType((byte) Constant.ZERO);
		nbDailyData.setRtuId(rtuId);
		nbDailyData.setMpId(mpId);
		nbDailyData.setYmd(ymd);
		nbDailyData.setHms(hms);

		nbDailyData.setTotalFlow(dataJson.getDouble("totalFlow"));
		nbDailyData.setBatteryVoltage(comm.getDouble("batteryVoltage"));

		byte valveStatus = st0.getByte("valveStatus");
		nbDailyData.setValveStatus(getValveStatus(valveStatus));
		JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
		
		/** 插入data11 */
		if (dataJson.getInteger("historyDate") != -1) {
			Calendar c = Calendar.getInstance();
			c.setTime(DateUtils.parseDate(reportTime.substring(0, 8), DateUtils.DATE_PATTERN));
			c.add(Calendar.DAY_OF_YEAR, Constant.ONE);
			String ymdStr = DateUtils.parseDate(c.getTime(), DateUtils.DATE_PATTERN);
			nbDailyData.setTableName(ymdStr.substring(0, 6));
			nbDailyData.setYmd(toInt(ymdStr));
			nbDailyData.setTotalFlow(dataJson.getDouble("historyTotalFlow"));
			JedisUtils.lpush(Constant.HISTORY_DAILY_QUEUE, JsonUtil.jsonObj2Sting(nbDailyData));
		}
		
		/** 更新表计阀门状态 */
		meterInfo.put("valveState", getValveStatus(valveStatus));
		meterInfo.put("version", comm.getString("version"));
		commonMapper.updateWaterMeterValve(meterInfo);

		/** 保存电池电压信息 */
		NbBattery nbBattery = new NbBattery();
		nbBattery.setTableName(tableNameDate);
		nbBattery.setYmd(toInt(reportDate.substring(0, 8)));
		nbBattery.setHms(toInt(reportDate.substring(9, 15)));
		nbBattery.setRtuId(rtuId);
		nbBattery.setMpId((short) mpId);
		nbBattery.setBatteryVoltage(comm.getDouble("batteryVoltage"));

		JedisUtils.lpush(Constant.HISTORY_BATTERY_QUEUE, JsonUtil.jsonObj2Sting(nbBattery));
	}

	/** 
	* @Title: getValveStatus 
	* @Description: 转换阀门状态
	* @param @param valveStatus
	* @param @return    设定文件 
	* @return byte    返回类型 
	* @throws 
	*/
	private byte getValveStatus(byte valveStatus) {
		if (valveStatus == Constant.ZERO) {
			valveStatus = Constant.TWO;
		} else if (valveStatus == Constant.ONE) {
			valveStatus = Constant.FOUR;
		} else if (valveStatus == Constant.THREE) {
			valveStatus = Constant.SIX;
		}
		return valveStatus;
	}
}
