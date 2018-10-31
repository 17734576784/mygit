/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.service.IPileService;
import com.wo.util.CommFunc;

/**
 * @author dbr
 *
 */
@RestController
public class PileController {
	
	@Autowired
	private IPileService pileService;
	
	@RequestMapping("/getPileState")
	public String getPileState(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.pileService.getPileState(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/getPileGps")
	public String getPileGps(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.pileService.getPileGps(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
	
	@RequestMapping("/getPileRate")
	public String getPileRate(String queryJsonStr) {
		JSONObject rtnJson = new JSONObject();
		rtnJson = this.pileService.getPileRate(queryJsonStr);
		return CommFunc.Josn2Str(rtnJson);
	}
}