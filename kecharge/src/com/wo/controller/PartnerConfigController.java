/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.model.PartnerConfig;
import com.wo.service.IPartnerConfigService;
import com.wo.util.CommFunc;

/**
 * @author dbr
 *
 */
@RestController
@RequestMapping("/partnerConfig")
public class PartnerConfigController {

	@Autowired
	private IPartnerConfigService partnerConfigService;
	
	@RequestMapping("/insertPartnerConfig")
	public String insertPartnerConfig(PartnerConfig partnerConfig) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.insertPartnerConfig(partnerConfig);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/updatePartnerConfig")
	public String updatePartnerConfig(PartnerConfig partnerConfig) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.updatePartnerConfig(partnerConfig);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/deletePartnerConfig")
	public String deletePartnerConfig(Integer id) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.deletePartnerConfig(id);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	
	@RequestMapping("/listPartnerConfig")
	public String listPartnerConfig(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.listPartnerConfig(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/getPartnerConfig")
	public String getPartnerConfig(Integer id) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.getPartnerConfig(id);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/listPartner")
	public String listPartner() {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.partnerConfigService.listPartner();
		return CommFunc.Josn2Str(rtnJson);
	}
}
