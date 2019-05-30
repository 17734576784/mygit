/**   
* @Title: FxProtocolUtil.java 
* @Package com.nb.protocolutil 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月30日 上午9:26:17 
* @version V1.0   
*/
package com.nb.protocolutil;

import com.alibaba.fastjson.JSONObject;
import com.nb.model.DeviceInfo;
import com.nb.utils.Constant;
import com.nb.utils.JsonUtil;
import com.nb.utils.StringUtil;
import com.thrid.party.codec.ef.ReturnObject;
import com.thrid.party.codec.ef.SIM_ModelEF;
import com.thrid.party.codec.ef.SendReceiveHelper;
import com.thrid.party.codec.ef.SendReceiveHelperEF;

/** 
* @ClassName: FxProtocolUtil 
* @Description: 府星移动规约解析工具
* @author dbr
* @date 2019年5月30日 上午9:26:17 
*  
*/
public class FxProtocolUtil {

	/** 
	* @Title: setReportPeriod 
	* @Description: 设置表端上报时间周期
	* @param @param deviceInfo
	* @param @param param
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String setReportPeriod(DeviceInfo deviceInfo, JSONObject param) throws Exception {

		SendReceiveHelper helper = new SendReceiveHelperEF();// 实例化操作类
		SIM_ModelEF sm = new SIM_ModelEF();// 定义数据模型
		String imei = StringUtil.flushLeft("0", Constant.NUM_20, deviceInfo.getImei());
		sm.setIMSI(imei);// 设置IMSI
		sm.setReportBaseTime(param.getString("ReportBaseTime"));// 上报起始基准时间
		sm.setReportIntervalHours(param.getBigDecimal("ReportIntervalHours"));// 上报间隔时间 单位：小时
		String json = JsonUtil.GsonString(sm);// 模型转JSON
		String ret = helper.ServerSendData(json, 0, 1, "SettingReportPeriod");// 执行编码操作
		ReturnObject obj = JsonUtil.GsonToBean(ret, ReturnObject.class);// json反徐丽华
		return obj.getCommand();
	}
	
	
	/** 
	* @Title: setDateTime 
	* @Description: 设置表端日期时间
	* @param @param deviceInfo
	* @param @param param
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String setDateTime(DeviceInfo deviceInfo, JSONObject param) throws Exception {

		SendReceiveHelper helper = new SendReceiveHelperEF();// 实例化操作类
		SIM_ModelEF sm = new SIM_ModelEF();// 定义数据模型
		String imei = StringUtil.flushLeft("0", Constant.NUM_20, deviceInfo.getImei());
		sm.setIMSI(imei);// 设置IMSI
		sm.setCurrentDateTime(param.getString("CurrentDateTime"));
		String json = JsonUtil.GsonString(sm);// 模型转JSON
		String ret = helper.ServerSendData(json, 0, 1, "SettingDateTime");// 执行编码操作
		ReturnObject obj = JsonUtil.GsonToBean(ret, ReturnObject.class);// json反徐丽华
		return obj.getCommand();
	}

	/** 
	* @Title: setValveState 
	* @Description: 阀门控制命令 
	* @param @param deviceInfo
	* @param @param param
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String setValveState(DeviceInfo deviceInfo, JSONObject param) throws Exception {

		SendReceiveHelper helper = new SendReceiveHelperEF();// 实例化操作类
		SIM_ModelEF sm = new SIM_ModelEF();// 定义数据模型
		String imei = StringUtil.flushLeft("0", Constant.NUM_20, deviceInfo.getImei());
		sm.setIMSI(imei);// 设置IMSI
		sm.setValveOperate(param.getBigDecimal("ValveOperate"));
		String json = JsonUtil.GsonString(sm);// 模型转JSON
		String ret = helper.ServerSendData(json, 0, 1, "SettingValveState");// 执行编码操作
		ReturnObject obj = JsonUtil.GsonToBean(ret, ReturnObject.class);// json反徐丽华
		return obj.getCommand();
	}
	
	/** 
	* @Title: setFlowAlarmThreshold 
	* @Description: 设置表端流量告警参数
	* @param @param deviceInfo
	* @param @param param
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String setFlowAlarmThreshold(DeviceInfo deviceInfo, JSONObject param) throws Exception {

		SendReceiveHelper helper = new SendReceiveHelperEF();// 实例化操作类
		SIM_ModelEF sm = new SIM_ModelEF();// 定义数据模型
		String imei = StringUtil.flushLeft("0", Constant.NUM_20, deviceInfo.getImei());
		sm.setIMSI(imei);// 设置IMSI
		sm.setLargeFlowAlarmThreshold(param.getBigDecimal("LargeFlowAlarmThreshold"));
		sm.setLargeFlowContinuousMonitorTime(param.getBigDecimal("LargeFlowContinuousMonitorTime"));
		sm.setContinuousFlowContinuousMonitorTime(param.getBigDecimal("ContinuousFlowContinuousMonitorTime"));
		sm.setLowFlowAlarmThreshold(param.getBigDecimal("LowFlowAlarmThreshold"));
		sm.setLowFlowContinuousMonitorTime(param.getBigDecimal("LowFlowContinuousMonitorTime"));

 		String json = JsonUtil.GsonString(sm);// 模型转JSON
		String ret = helper.ServerSendData(json, 0, 1, "SettingFlowAlarmThreshold");// 执行编码操作
		ReturnObject obj = JsonUtil.GsonToBean(ret, ReturnObject.class);// json反徐丽华
		return obj.getCommand();
	}
	
	/** 
	* @Title: setPressureAlarmThreshold 
	* @Description: 设置表端压力告警参数
	* @param @param deviceInfo
	* @param @param param
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String setPressureAlarmThreshold(DeviceInfo deviceInfo, JSONObject param) throws Exception {

		SendReceiveHelper helper = new SendReceiveHelperEF();// 实例化操作类
		SIM_ModelEF sm = new SIM_ModelEF();// 定义数据模型
		String imei = StringUtil.flushLeft("0", Constant.NUM_20, deviceInfo.getImei());
		sm.setIMSI(imei);// 设置IMSI
		sm.setHighPressureAlarmThreshold(param.getBigDecimal("HighPressureAlarmThreshold"));
		sm.setLowPressureAlarmThreshold(param.getBigDecimal("LowPressureAlarmThreshold"));
		String json = JsonUtil.GsonString(sm);// 模型转JSON
		String ret = helper.ServerSendData(json, 0, 1, "SettingPressureAlarmThreshold");// 执行编码操作
		ReturnObject obj = JsonUtil.GsonToBean(ret, ReturnObject.class);// json反徐丽华
		return obj.getCommand();
	}
}
