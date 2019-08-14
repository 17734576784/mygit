/**
 * 
 */
package com.wo.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wo.mapper.ChargeMapper;
import com.wo.mapper.ChargeRecordMapper;
import com.wo.mapper.YysCarownerOrderMapper;
import com.wo.model.ChargeRecord;
import com.wo.service.IChargeService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
public class PushTask extends QuartzJobBean {

	@Resource
	private ChargeMapper chargeMapper;

	@Autowired
	private IChargeService chargeService;

	@Resource
	private ChargeRecordMapper chargeRecordMapper;

	@Resource
	private YysCarownerOrderMapper yysCarownerOrderMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		pushMessage();
	}
	
	public void pushMessage() {
		try {
			List<ChargeRecord> chargeRecordList = chargeRecordMapper.listChargeRecord();
			for (ChargeRecord chargeRecord : chargeRecordList) {
				if (null == chargeRecord) {
					continue;
				}
				JSONObject json = new JSONObject();
				if (chargeRecord.getStartPush() == Constant.NOPUSH) {
					json.put("serialNumber", chargeRecord.getSerialnumber());
					json.put("chargeFlag", chargeRecord.getStartFlag());

					if (chargeService.SendChargStartRequest(json,Constant.RETRY)) {
						chargeRecord.setStartPush(Constant.PUSHED);
						chargeRecord.setStartPushTime(new Date());
						chargeRecordMapper.updateChargeRecord(chargeRecord);
						Thread.sleep(5000);
					}

				} else if (chargeRecord.getEndPush() == Constant.NOPUSH) {

					String tableName = "cpdata.chargercd";
					int nowYm = CommFunc.nowDateInt() / 100;
					String nowTableName = tableName + nowYm;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
					String lastTableName = tableName + sdf.format(chargeRecord.getStartDate());

					
					String chargeSerialNumber = this.yysCarownerOrderMapper.getChargeSerialNumber(chargeRecord.getSerialnumber());
			  		
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("nowTableName", nowTableName);
					param.put("lastTableName", lastTableName);
					param.put("serialNumber", chargeSerialNumber);
					
					List<Map<String, Object>> listRcd = chargeMapper.getPileChargeRecord(param);
					if (null == listRcd || listRcd.isEmpty()) {
						continue;
					}

					Map<String, Object> chargeRcd = listRcd.get(0);

					boolean checkWasteNo = CommFunc.checkWasteno(chargeRecord.getSerialnumber());
					if (!checkWasteNo) {
						continue;
					}

					double totalElectricity = 0, trade_money = 0, serviceMoney = 0;
					String endCause = "未知";

					StringBuffer startDate = new StringBuffer();
					startDate.append(CommFunc.FormatToYMD(chargeRcd.get("trade_bymd"),"day")).append(" ");
					startDate.append(CommFunc.intToTime(chargeRcd.get("trade_bhms"), 1));

					StringBuffer endDate = new StringBuffer();
					endDate.append(CommFunc.FormatToYMD(chargeRcd.get("trade_eymd"),"day")).append(" ");
					endDate.append(CommFunc.intToTime(chargeRcd.get("trade_ehms"), 1));

					totalElectricity = CommFunc.objToDbl(chargeRcd.get("trade_dl"));
					trade_money = CommFunc.objToDbl(chargeRcd.get("trade_money"));
					serviceMoney = CommFunc.objToDbl(chargeRcd.get("park_money"));

					int m_cause = CommFunc.objToInt(chargeRcd.get("end_cause"));
					endCause = chargeRecordMapper.getEndCause(m_cause);
					if (null == endCause || endCause.equals("")) {
						endCause = "未知";
					}

					JSONObject rtnJson = CommFunc.errorInfo(Constant.OK, "");

					param.clear();
					param.put("stationId", CommFunc.objToInt(chargeRcd.get("stationId")));
					param.put("pileId", CommFunc.objToStr(chargeRcd.get("pile_id")));
					String pileNo = chargeMapper.getPileNoById(param);

					rtnJson.put("serialNumber", chargeRecord.getSerialnumber());
					rtnJson.put("pileNo", pileNo);
					rtnJson.put("gunNo", CommFunc.objToStr(chargeRcd.get("mp_id")));
					rtnJson.put("startDate", startDate.toString());
					rtnJson.put("endDate", endDate.toString());

					rtnJson.put("totalElectricity", CommFunc.round(totalElectricity, 2));
					rtnJson.put("chargeMoney", CommFunc.round(trade_money, 2));
					rtnJson.put("serviceMoney", CommFunc.round(serviceMoney, 2));

					rtnJson.put("endCause", endCause);

					// 发送充电结束请求
					if (chargeService.SendChargOverRequest(rtnJson,Constant.RETRY)) {
						chargeRecord.setEndPush(Constant.PUSHED);
						chargeRecord.setEndPushTime(new Date());
						chargeRecordMapper.updateChargeRecord(chargeRecord);
						Thread.sleep(2000);
					}
				}
			}
		} catch (Exception e) {
			Log4jUtil.getError().error("推送消息任务异常！", e);
			e.printStackTrace();
		}
	}
}
