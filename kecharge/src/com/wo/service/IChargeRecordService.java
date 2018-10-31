/**
 * 
 */
package com.wo.service;
import net.sf.json.JSONObject;



/**
 * @author dbr
 *
 */
public interface IChargeRecordService {
	
	/** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
	* <p>
	* <p>NAME        : 	
	* <p>DESCRIPTION : 	获取接口充电记录列表
	* <p>INPUT       : 
	* <p>OUTPUT      : 
	* <p>RETURN      : List<ChargeRecord>
	* <p>
	*-----------------------------------------------------------*/
	JSONObject listChargeRecord(String queryJsonStr);
}
