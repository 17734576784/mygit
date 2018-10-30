/**   
* @Title: TimeService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:35:10 
* @version V1.0   
*/
package com.iot.commandstrategy;

import static com.iot.utils.ConverterUtils.toStr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.Constant;
import com.iot.utils.DateUtils;
import com.iot.utils.HttpsUtils;
import com.iot.utils.Log4jUtils;

/**
 * @ClassName: CommandTimeService
 * @Description: 设定上传周期以及时间上行解析服务
 * @author dbr
 * @date 2018年10月25日 下午2:35:10
 * 
 */
@Component
public class CommandTimeService implements ICommandService {

	@Value("${website.baseurl}")
	private String baseUrl;
	
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: parse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param deviceId
	 * @param serviceMap
	 * @see com.iot.commandstrategy.ICommandService#parse(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	public void parse(String deviceId, Map<String, String> commandMap) {
		// TODO Auto-generated method stub
		Log4jUtils.getInfo().info("设置上传周期命令回复，设备id：" + deviceId + " ,告警内容：" + commandMap.toString());

		String timetype = toStr(commandMap.get("timetype"));
		String time = toStr(commandMap.get("time"));
		String status = toStr(commandMap.get("status"));
		String apiUrl = baseUrl + Constant.UPLOAD_TIMECOMMAND_URL;

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cycle", timetype);
		paramMap.put("time", time);
		paramMap.put("status", status);
		paramMap.put("date", DateUtils.curDate());
		paramMap.put("time", DateUtils.curTime());

		JSONObject httpResult = HttpsUtils.doPost(apiUrl, paramMap);
		if (httpResult.getInteger("status") == Constant.ERROR) {
			Log4jUtils.getInfo().info("推送设置上传周期命令回复失败，设备id：" + deviceId + " ,告警内容：" + commandMap.toString());
		}
	}

}
