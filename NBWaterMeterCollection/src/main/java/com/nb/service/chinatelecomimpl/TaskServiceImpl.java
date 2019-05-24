/**   
* @Title: TaskServiceImpl.java 
* @Package com.nb.service.chinatelecomimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月5日 下午4:30:23 
* @version V1.0   
*/
package com.nb.service.chinatelecomimpl;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.httputil.ChinaTelecomIotHttpsUtil;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import com.nb.mapper.TaskMapper;
import com.nb.model.StreamClosedHttpResponse;
import com.nb.service.ITaskService;
import com.nb.servicestrategy.ServiceStrategyContext;
import com.nb.utils.AuthenticationUtils;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.utils.JsonUtil;

/** 
* @ClassName: TaskServiceImpl 
* @Description: 定时任务执行类 
* @author dbr
* @date 2019年5月5日 下午4:30:23 
*  
*/
@Service
public class TaskServiceImpl implements ITaskService {


	@Resource
	private TaskMapper taskMapper;

	@Resource
	private ServiceStrategyContext chinaTelecomServiceContext;
	/** (非 Javadoc) 
	* <p>Title: callHistoryData</p> 
	* <p>Description: </p> 
	* @param className 
	* @see com.nb.service.ITaskService#callHistoryData(java.lang.String) 
	*/
	@Override
	public void callHistoryData(String className) {
		/** 根据任务名称获取任务信息 */
		Map<String, Object> taskInfo = taskMapper.getBatchCommandByClass(className);
		if (taskInfo == null) {
			return;
		}
		/** 获取补招任务执行的表名和日期列表 */
		taskInfo.put("paramList", getTaskParamList());
		
		/** 查询约定期限内存在未采集的表计 */
		List<String> deviceIdList = taskMapper.listDeviceIdByAppModel(taskInfo);
		if (deviceIdList.isEmpty()) {
			return;
		}
		LoggerUtil.logger(LogName.HISTORYDATA).info("执行{}水表补招任务,补招表计:{}", className, deviceIdList.toString());
	
		/** 遍历未采集成功表计,向电信平台获取历史数据 */
		for (String deviceId : deviceIdList) {
			ChinaTelecomIotHttpsUtil httpsUtil = new ChinaTelecomIotHttpsUtil();
			try {
				httpsUtil.initSSLConfigForTwoWay();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String appId = toStr(taskInfo.get("appId"));
			String secret = toStr(taskInfo.get("secret"));
			String accessToken = AuthenticationUtils.getChinaTelecomAccessToken(httpsUtil, appId, secret);
			Map<String, String> header = new HashMap<>();
			header.put(Constant.HEADER_APP_KEY, appId);
			header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
			
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("deviceId", deviceId);
			queryParams.put("gatewayId", deviceId);
//			queryParams.put("serviceId", toStr(taskInfo.get("serviceId")));
			queryParams.put("pageSize", toStr(Constant.PAGE_SIZE));
			queryParams.put("startTime", getTaskStartTime());

	        String urlQueryDeviceHistoryData = Constant.CHINA_TELECOM_QUERY_DEVICE_HISTORY_DATA;

			try {
				StreamClosedHttpResponse response = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceHistoryData,
						queryParams, header);
				JSONObject responseJson = JSONObject.parseObject(response.getContent());
				if (!responseJson.containsKey("deviceDataHistoryDTOs")) {
					return;
				}
				
				JSONArray dataArrays = responseJson.getJSONArray("deviceDataHistoryDTOs");
				for (int i = 0; i < dataArrays.size(); i++) {
					Map<String, String> serviceMap = new HashMap<>();
					serviceMap = JsonUtil.convertJsonStringToObject(dataArrays.getString(i), serviceMap.getClass());
					chinaTelecomServiceContext.parseService(deviceId, serviceMap);
				}
			} catch (Exception e) {
				LoggerUtil.logger(LogName.HISTORYDATA).error("执行{}水表补招任务,发生异常:{}", className, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/** 
	* @Title: getTaskParamList 
	* @Description: 获取补招任务执行的表名和日期列表
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	private List<Map<String, Object>> getTaskParamList() {
		/** 默认补招从T-1起 */
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);

		List<Map<String, Object>> paramList = new LinkedList<Map<String, Object>>();

		for (int i = 0; i < Constant.TASK_CALL_DAYS; i++) {
			String date = DateUtils.formatDateByFormat(endDate.getTime(), DateUtils.DATE_PATTERN);
			String tableName = "YDData.dbo.nb_daily_data_" + date.substring(0, 6);
			Map<String, Object> param = new HashMap<>();
			param.put("date", date);
			param.put("tableName", tableName);

			paramList.add(param);
			endDate.add(Calendar.DAY_OF_YEAR, -1);
		}
		return paramList;
	}
	
	/** 
	* @Title: getTaskStartTime 
	* @Description: 获取补招任务产生时间的开始时间
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getTaskStartTime() {
		/** 默认补招从T-1起 */
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);
		endDate.add(Calendar.DAY_OF_MONTH, -1 * (Constant.TASK_CALL_DAYS + 1));
		return DateUtils.parseDate(endDate.getTime(), DateUtils.UTC_PATTERN);
	}
}
