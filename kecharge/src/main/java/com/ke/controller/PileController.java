/**
 * 
 */
package com.ke.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ke.service.IPileService;

/**
 * @author dbr
 *
 */
@RestController
public class PileController {
	
	@Autowired
	private IPileService pileService;

	@RequestMapping("/getPileState.json")
	public String getPileState(String queryJsonStr) throws Exception {
		return this.pileService.getPileState(queryJsonStr).toJSONString();
	}

	@RequestMapping("/getPileGps.json")
	public String getPileGps(String queryJsonStr) throws Exception {
		return this.pileService.getPileGps(queryJsonStr).toJSONString();
	}

	@RequestMapping("/getPileRate.json")
	public String getPileRate(String queryJsonStr) throws Exception {
		return this.pileService.getPileRate(queryJsonStr).toJSONString();
	}

	@RequestMapping("/listPileInfo.json")
	public String listPileState(String queryJsonStr) throws Exception {
		return this.pileService.listPileInfo(queryJsonStr).toJSONString();
	}

	@RequestMapping("/listGunInfo.json")
	public String listGunInfo(String queryJsonStr) throws Exception {
		return this.pileService.listGunInfo(queryJsonStr).toJSONString();
	}

	@RequestMapping("/listGunState.json")
	public String listGunState(String queryJsonStr) throws Exception {
		return this.pileService.listGunState(queryJsonStr).toJSONString();
	}
}