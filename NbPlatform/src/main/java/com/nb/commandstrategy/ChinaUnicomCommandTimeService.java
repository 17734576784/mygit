/**   
* @Title: TimeService.java 
* @Package com.nb.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:35:10 
* @version V1.0   
*/
package com.nb.commandstrategy;

import static com.nb.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtils;
import com.nb.utils.Constant;
import com.nb.utils.DateUtils;
import com.nb.http.HttpsUtils;

/**
 * @ClassName: CommandTimeService
 * @Description: 设定上传周期以及时间上行解析服务
 * @author dbr
 * @date 2018年10月25日 下午2:35:10
 * 
 */
@Component
public class ChinaUnicomCommandTimeService implements ICommandService {

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/**
	 * (非 Javadoc)
	 * Title: parse
	 * Description:
	 * @param deviceId
	 * @param serviceMap
	 * @see com.nb.commandstrategy.ICommandService#parse(java.lang.String, java.util.Map)
	 */
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		LoggerUtils.Logger(LogName.INFO).info("设置上传周期命令回复，设备id：" + deviceId + " ,回复内容：" + commandMap.toString());
		String timetype = toStr(commandMap.get("timetype"));
		String time = toStr(commandMap.get("time"));
		String status = toStr(commandMap.get("status"));
		String apiUrl = baseUrl + Constant.UPLOAD_TIMECOMMAND_URL;

		Map<String, Object> paramJson = new HashMap<String, Object>();
		paramJson.put("cycle", timetype);
		paramJson.put("devicetime", time);
		paramJson.put("status", status);
		paramJson.put("date", DateUtils.curDate());
		paramJson.put("time", DateUtils.curTime());
		paramJson.put("deviceId", deviceId);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("param", paramJson);

		try {
			JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
			if (httpResult != null && !httpResult.isEmpty()) {
				if (httpResult.getInteger("status") == Constant.ERROR) {
					LoggerUtils.Logger(LogName.INFO).info("推送设置上传周期命令回复失败，设备id：" + deviceId + "，回复内容：" + commandMap.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.ERROR).error("上传周期命令回复执行异常", e);
		}
		
	}

}
