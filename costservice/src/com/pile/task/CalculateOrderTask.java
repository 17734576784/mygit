/**   
* @Title: CalculateOrderTask.java 
* @Package com.pile.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月22日 下午2:06:53 
* @version V1.0   
*/
package com.pile.task;

import java.util.Map;
import java.util.List;
import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.pile.common.Constant;
import com.pile.customer.CalculateFeeExecutor;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.netty.message.ChargeInfoBufOuterClass.ChargeInfoBuf;
import com.pile.utils.JedisUtils;
import com.pile.utils.JsonUtils;
import com.pile.utils.Log4jUtils;
import static com.pile.utils.ConverterUtils.*;
/** 
* @ClassName: CalculateOrderTask 
* @Description: 充电单结费任务 
* @author dbr
* @date 2018年8月22日 下午2:06:53 
*  
*/
@Component
public class CalculateOrderTask extends QuartzJobBean{

	@Autowired
	private CalculateFeeExecutor calculateorder;

	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	/** (非 Javadoc) 
	* <p>Title: executeInternal</p> 
	* <p>Description: </p> 
	* @param arg0
	* @throws JobExecutionException 
	* @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext) 
	*/
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

		// 先对redis中异常的工单进行算费
		// processErrorQueue();
		// 查询数据库中 遗漏的充电单进行算费
		// processDBOrder();
	}

	/** 
	* @Title: processErrorQueue 
	* @Description: 先对redis中异常的工单进行算费 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void processErrorQueue() {
		byte[] value = null;
		try {
			value = JedisUtils.rpop(Constant.ERROR_COST_QUEUE);
			if (null == value) {
				return;
			}
			// 执行成功与否，不再处理
			calculateorder.calculateOrder(value);
		} catch (Exception e) {
			String errorInfo = "redis异常工单处理任务异常：" + JsonUtils.toJSONString(value) + ", 异常信息：" + e.getMessage();
			Log4jUtils.getDiscountinfo().error(errorInfo);
			e.printStackTrace();
		}
	}
	
	/** 
	* @Title: processDBOrder 
	* @Description: 查询数据库中 遗漏的充电单进行算费 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void processDBOrder() {
		String errorInfo = "";
		try {
			/** 结束充电半小时没算费的查库进行算费 */
			List<Map<String, String>> orderList = this.calculateFeeMapper.listMemberOrder();
			for (Map<String, String> order : orderList) {
				if (null == order || order.isEmpty()) {
					continue;
				}
				
				ChargeInfoBuf.Builder chargeBuf = ChargeInfoBuf.newBuilder();
				chargeBuf.setMemberId(toInt(order.get("memberId")));
				chargeBuf.setPileCode(toStr(order.get("pileCode")));
				chargeBuf.setPileId(toInt(order.get("pileId")));
				chargeBuf.setGunId(toInt(order.get("gunId")));
				chargeBuf.setOrderSerialNumber(toStr(order.get("orderSerialNumber")));
				
				chargeBuf.setServiceMoney(toInt(order.get("serviceMoney")));
				chargeBuf.setChargeMoney(toInt(order.get("chargeMoney")));
				chargeBuf.setChargeAmount(toDouble(order.get("chargeAmount")));
				chargeBuf.setEndCause(toInt(order.get("endCause")));
				
				JedisUtils.lpush(Constant.COST_QUEUE, chargeBuf);
			}
		} catch (Exception e) {
			errorInfo = "数据库异常工单处理任务异常：" + errorInfo + ", 异常信息：" + e.getMessage();
			Log4jUtils.getDiscountinfo().error(errorInfo);
			e.printStackTrace();
		}
	}
	
	
}
