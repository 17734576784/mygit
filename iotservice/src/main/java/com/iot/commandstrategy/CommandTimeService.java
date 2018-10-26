/**   
* @Title: TimeService.java 
* @Package com.iot.commandstrategy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月25日 下午2:35:10 
* @version V1.0   
*/
package com.iot.commandstrategy;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @ClassName: CommandTimeService
 * @Description: 设定上传周期以及时间上行解析服务
 * @author dbr
 * @date 2018年10月25日 下午2:35:10
 * 
 */
@Component
public class CommandTimeService implements ICommandService {

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
	public void parse(String deviceId, Map<String, String> serviceMap) {
		// TODO Auto-generated method stub

	}

}
