/**   
* @Title: CalculateFeeService.java 
* @Package com.pile.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年2月26日 下午1:58:49 
* @version V1.0   
*/
package com.pile.service;

import com.pile.model.ChargeInfo;

/**
 * @ClassName: CalculateFeeService
 * @Description: 算费接口
 * @author dbr
 * @date 2018年2月26日 下午1:58:49
 * 
 */
public interface ICalculateFeeService {

	/**
	 * 充电费用结费方法
	 * */
	boolean calculateChargeFee(ChargeInfo chargeInfo);

}
