/**   
* @Title: ReportExceptionService.java 
* @Package com.nb.servicestrategy.chinamobile.fx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月24日 下午3:27:01 
* @version V1.0   
*/
package com.nb.servicestrategy.chinamobile.fx;

import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.CommonMapper;
import com.nb.model.Eve;
import com.nb.servicestrategy.IServiceStrategy;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JedisUtils;
import com.nb.utils.JsonUtil;
import com.thrid.party.codec.ef.ReceiveCodeEF;
import com.thrid.party.codec.ef.ReturnObject;

/** 
* @ClassName: FxMoileReportExceptionService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年5月24日 下午3:27:01 
*  
*/
@Service
public class FxMoileReportExceptionService implements IServiceStrategy {
	
	@Resource
	private CommonMapper commonMapper;
	
	/** (非 Javadoc) 
	* <p>Title: parse</p> 
	* <p>Description: </p> 
	* @param deviceId
	* @param serviceMap 
	* @see com.nb.servicestrategy.IServiceStrategy#parse(java.lang.String, java.util.Map) 
	*/
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String logInfo = "府星移动 定时主动上报,设备 ：" + deviceId + ",数据：" + serviceMap.toString();
		LoggerUtil.logger(LogName.CALLBACK).info(logInfo);
		if (serviceMap == null || serviceMap.isEmpty()) {
			return;
		}
		
		try {
			ReturnObject obj = JsonUtil.GsonToBean(serviceMap.get("msg"), ReturnObject.class);
			ReceiveCodeEF receiveCode = obj.getContentObj();
			/** 根据设备Id获取表计的终端和测定id */
			Map<String, Object> meterInfo = this.commonMapper.getRtuMpIdByDeviceId(deviceId);
			if (meterInfo == null) {
				return;
			}

			int rtuId = toInt(meterInfo.get("rtuId"));
			int mpId = toInt(meterInfo.get("mpId"));

			Date dateTime = DateUtils.parseTimesTampDate(receiveCode.getCurrentDateTime());
			int date = toInt(DateUtils.formatDateByFormat(dateTime, "yyyyMMdd"));
			int time = toInt(DateUtils.formatDateByFormat(dateTime, "HHmmss"));

			int typeNo = toInt(receiveCode.getErrorNo());
			String eveInfo = "";
			
			/** BIT0 阀门异常 */
			if ((typeNo & 0x01) == 0x01) {
				eveInfo = "阀门异常";
				typeNo = Constant.ALARM_2010;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT3：强磁异常 */
			if ((typeNo & 0x08) == 0x08) {
				eveInfo = "强磁异常（双干簧管异常），连续10秒2个干簧管都吸合，关阀门并自动上报，有5次按键打开阀门的机会，第5次按键打卡阀门后，如果还是出现强磁只有服务器下发“开阀命令”才能打开阀门。";
				typeNo = Constant.ALARM_2004;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT4：一级低压 BIT5：二级低压 */
			if ((typeNo & 0x10) == 0x10 || (typeNo & 0x20) == 0x20) {
				eveInfo = "电池电压低压告警，电压值:" + receiveCode.getBatteryVoltage();
				typeNo = Constant.ALARM_2008;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT6：存储器异常 */
			if ((typeNo & 0x40) == 0x40) {
				eveInfo = "存储器异常";
				typeNo = Constant.ALARM_2011;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT8：大流量告警 */
			if ((typeNo & 0x100) == 0x100) {
				eveInfo = "大流量告警";
				typeNo = Constant.ALARM_2001;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT9：小流量告警 */
			if ((typeNo & 0x200) == 0x200) {
				eveInfo = "小流量告警";
				typeNo = Constant.ALARM_2002;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT11: 高压力告警 */
			if ((typeNo & 0x800) == 0x800) {
				eveInfo = "高压力告警";
				typeNo = Constant.ALARM_2009;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}

			/** BIT12: 低压力告警 */
			if ((typeNo & 0x1000) == 0x1000) {
				eveInfo = "低压力告警";
				typeNo = Constant.ALARM_2008;
				insertEve(receiveCode, rtuId, mpId, eveInfo, typeNo, date, time);
			}
		 
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error(logInfo + ",异常:" + e.getMessage());
		}
	}
	

	/** 
	* @Title: insertEve 
	* @Description: 插入报警事项
	* @param @param receiveCode
	* @param @param rtuId
	* @param @param mpId
	* @param @param eveInfo
	* @param @param typeNo
	* @param @param date
	* @param @param time
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void insertEve(ReceiveCodeEF receiveCode, int rtuId, int mpId, String eveInfo, int typeNo, int date,
			int time) throws Exception {

		Eve eve = new Eve();
		eve.setTableName(toStr(date / 100));
		eve.setYmd(date);
		eve.setHmsms(time * 1000);
		eve.setMemberId0(rtuId);
		eve.setMemberId1(mpId);
		eve.setMemberId2(-1);
		eve.setClassno(Constant.NB_ALARM);
		eve.setTypeno((short) typeNo);
		eve.setCharInfo(eveInfo);

		JedisUtils.lpush(Constant.ALARM_EVENT_QUEUE, JsonUtil.jsonObj2Sting(eve));
	}
}
