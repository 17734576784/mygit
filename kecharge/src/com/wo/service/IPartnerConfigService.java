/**
 * 
 */
package com.wo.service;

 import net.sf.json.JSONObject;

import com.wo.model.PartnerConfig;

/**
 * @author dbr
 *
 */
public interface IPartnerConfigService {

	JSONObject insertPartnerConfig(PartnerConfig partnerConfig);

	JSONObject updatePartnerConfig(PartnerConfig partnerConfig);

	JSONObject deletePartnerConfig(Integer id);

	JSONObject listPartnerConfig(String queryJsonStr);

	JSONObject getPartnerConfig(Integer id);

	JSONObject listPartner();
}
