/**   
* @Title: TaskServiceImpl.java 
* @Package com.ke.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月10日 下午4:43:32 
* @version V1.0   
*/
package com.ke.serviceimpl;

import static com.ke.utils.ConverterUtil.roundBase;
import static com.ke.utils.ConverterUtil.toDouble;
import static com.ke.utils.ConverterUtil.toInt;
import static com.ke.utils.ConverterUtil.toStr;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.mapper.MemberOrdersMapper;
import com.ke.mapper.PileMapper;
import com.ke.model.ChargeMonitor;
import com.ke.model.MemberOrders;
import com.ke.model.Pilepara;
import com.ke.service.IChargeService;
import com.ke.service.ITaskService;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;
import com.ke.utils.JedisUtil;

/**
 * @ClassName: TaskServiceImpl
 * @Description: 自动任务服务实现类
 * @author dbr
 * @date 2019年1月10日 下午4:43:32
 * 
 */
@Service
public class TaskServiceImpl implements ITaskService {

	@Resource
	private ChargeMonitorMapper chargeMonitorMapper;

	@Autowired
	private IChargeService chargeService;

	@Resource
	private MemberOrdersMapper memberOrdersMapper;

	@Resource
	private PileMapper pileMapper;

	@Value("${hydropwer_operator}")
	private Integer operatorId;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: pushMessageTask
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @see com.ke.service.ITaskService#pushMessageTask()
	 */
	@Override
	public void pushMessageTask() {
		try {
			List<ChargeMonitor> chargeMonitorList = chargeMonitorMapper.listChargeMonitorTask();
			for (ChargeMonitor chargeMonitor : chargeMonitorList) {
				if (null == chargeMonitor) {
					continue;
				}

				JSONObject json = new JSONObject();
				if (chargeMonitor.getStartPush() == Constant.NOPUSH) {
					json.put("serialNumber", chargeMonitor.getSerialnumber());
					json.put("chargeFlag", chargeMonitor.getStartFlag());

					if (chargeService.SendChargeStartRequest(json, Constant.RETRY)) {
						chargeMonitor.setStartPush(Constant.PUSHED);
						chargeMonitor.setStartPushTime(new Date());
						chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
						Thread.sleep(5000);
					}

				} else if (chargeMonitor.getEndPush() == Constant.NOPUSH) {

					MemberOrders order = this.memberOrdersMapper.getmemberOrders(chargeMonitor.getSerialnumber());

					if (null == order) {
						return;
					}

					boolean checkWasteNo = CommFunc.checkWasteno(order.getSerialnumber());
					if (!checkWasteNo) {
						continue;
					}

					double totalElectricity = 0, trade_money = 0, serviceMoney = 0;
					String endCause = "未知";

					totalElectricity = ConverterUtil.toDouble(order.getChargeDl());
					trade_money = ConverterUtil.toDouble(order.getTradeMoney() / 100);
					serviceMoney = ConverterUtil.toDouble(order.getServiceMoney() / 100);

					int m_cause = ConverterUtil.toInt(order.getEndCause());
					Map<String, String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
					endCause = endCauseMap.get(m_cause);
					if (null == endCause || endCause.equals("")) {
						endCause = "未知";
					}

					JSONObject rtnJson = CommFunc.errorInfo(Constant.SUCCESS, "");
					String pileNo = order.getPileCode();

					rtnJson.put("serialNumber", order.getSerialnumber());
					rtnJson.put("pileNo", pileNo);
					rtnJson.put("gunNo", order.getGunId());
					rtnJson.put("startDate", DateUtil.formatTimesTampDate(order.getChargebeginDate()));
					rtnJson.put("endDate", DateUtil.formatTimesTampDate(order.getChargeendDate()));

					rtnJson.put("totalElectricity", ConverterUtil.roundTosString(totalElectricity, 2));
					rtnJson.put("chargeMoney", ConverterUtil.roundTosString(trade_money, 2));
					rtnJson.put("serviceMoney", ConverterUtil.roundTosString(serviceMoney, 2));

					rtnJson.put("endCause", endCause);

					// 发送充电结束请求
					if (chargeService.SendChargeOverRequest(rtnJson, Constant.RETRY)) {
						chargeMonitor.setEndPush(Constant.PUSHED);
						chargeMonitor.setEndPushTime(new Date());
						chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
						Thread.sleep(2000);
					}
				}
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("推送消息任务异常！", e);
			e.printStackTrace();
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: backupChargeMonitorTask
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @see com.ke.service.ITaskService#backupChargeMonitorTask()
	 */
	@Override
	public void backupChargeMonitorTask() {
		try {
			Calendar c = Calendar.getInstance();
			int nowYear = c.get(Calendar.YEAR);
			c.add(Calendar.DAY_OF_YEAR, -10);
			int lastYear = c.get(Calendar.YEAR);

			String tableNowYear = "chargedata.charge_monitor" + nowYear;
			String tableLastYear = "chargedata.charge_monitor" + lastYear;

			Map<String, Object> param = new HashMap<String, Object>();
			if (nowYear == lastYear) {
				param.put("tableName", tableNowYear);
				param.put("symd", nowYear + "0101");
				param.put("eymd", DateUtil.curDate());
				if (chargeMonitorMapper.backupChargeMonitor(param)) {
					chargeMonitorMapper.deleteChargeMonitors(param);
				}
			} else {
				param.put("tableName", tableLastYear);
				param.put("symd", lastYear + "0101");
				param.put("eymd", lastYear + "1231");
				if (chargeMonitorMapper.backupChargeMonitor(param)) {
					chargeMonitorMapper.deleteChargeMonitors(param);
				}

				param.put("tableName", tableNowYear);
				param.put("symd", nowYear + "0101");
				param.put("eymd", DateUtil.curDate());
				if (chargeMonitorMapper.backupChargeMonitor(param)) {
					chargeMonitorMapper.deleteChargeMonitors(param);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("备份充电监控记录任务失败");
			LoggerUtil.logger(LogName.ERROR).error("备份充电记录监控任务失败", e);
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: backUpChargeOrderTask
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @see com.ke.service.ITaskService#backUpChargeOrderTask()
	 */
	@Override
	public void backUpChargeOrderTask() {
		try {
			Calendar c = Calendar.getInstance();
			int nowYear = c.get(Calendar.YEAR);
			c.add(Calendar.DAY_OF_YEAR, -30);
			int lastYear = c.get(Calendar.YEAR);

			String tableNowYear = "chargedata.orders" + nowYear;
			String tableLastYear = "chargedata.orders" + lastYear;

			Map<String, Object> param = new HashMap<String, Object>();
			if (nowYear == lastYear) {
				param.put("tableName", tableNowYear);
				param.put("symd", nowYear + "0101");
				param.put("eymd", DateUtil.curDate());
				if (chargeMonitorMapper.backupChargeOrder(param)) {
					chargeMonitorMapper.deleteChargeOrder(param);
				}
			} else {
				param.put("tableName", tableLastYear);
				param.put("symd", lastYear + "0101");
				param.put("eymd", lastYear + "1231235959");
				if (chargeMonitorMapper.backupChargeOrder(param)) {
					chargeMonitorMapper.deleteChargeOrder(param);
				}

				param.put("tableName", tableNowYear);
				param.put("symd", nowYear + "0101");
				param.put("eymd", DateUtil.curDate());
				if (chargeMonitorMapper.backupChargeOrder(param)) {
					chargeMonitorMapper.deleteChargeOrder(param);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("备份充电单任务失败");
			LoggerUtil.logger(LogName.ERROR).error("备份充电单任务失败", e);
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: pushHydropwerPileState
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @throws Exception
	 * @see com.ke.service.ITaskService#pushHydropwerPileState()
	 */
	@Override
	public void pushHydropwerPileState() throws Exception {

		List<Pilepara> pileList = this.pileMapper.listPileByOperatorId(operatorId);
		for (Pilepara pilepara : pileList) {
			JSONObject pileStateJson = new JSONObject();
			String key = Constant.PILESTATE + pilepara.getId();
			Map<String, String> pileStateMap = JedisUtil.hgetAll(key);
			pileStateJson.put("pileNo", pilepara.getSerialCode());
			pileStateJson.put("temperature", (int) toDouble(pileStateMap.get("temperature")));
			pileStateJson.put("humidity", (int) toDouble(pileStateMap.get("humidity")));
			pileStateJson.put("pileStatus", pileStateMap.get("state"));
			pileStateJson.put("operatorId", operatorId);

			key = Constant.GUNSTATE + pilepara.getId() + "_*";
			Set<String> keySet = JedisUtil.keysStr(key);
			JSONArray gunStateArray = new JSONArray();
			for (String string : keySet) {
				JSONObject gunStateJson = new JSONObject();
				Map<String, String> gunState = JedisUtil.hgetAll(string);
				gunStateJson.put("gunNo", string.split("_")[1]);
				gunStateJson.put("gunType", toInt(gunState.get("gunType")));
				gunStateJson.put("switchState", toInt(gunState.get("switchState")));
				gunStateJson.put("voltage", roundBase(toDouble(gunState.get("va")), 2));
				gunStateJson.put("current", roundBase(toDouble(gunState.get("ia")), 2));
				gunStateJson.put("power", roundBase(toDouble(gunState.get("p")), 3));

				gunStateJson.put("readings", roundBase(toDouble(gunState.get("readings")), 4));
				gunStateJson.put("time", toStr(gunState.get("dataTime")));
				gunStateArray.add(gunStateJson);
			}

			pileStateJson.put("rows", gunStateArray);
			// 发送充电结束请求
			try {
				if (chargeService.SendHydroPowerHeartBeat(pileStateJson, Constant.RETRY)) {
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
