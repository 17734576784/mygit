/**
 * 
 */
package com.wo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wo.cachemanager.CacheBean;
import com.wo.cachemanager.CacheManager;
import com.wo.model.LoginUser;
import com.wo.service.IStaionService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 *
 */
@RestController
public class StationController {
	
	@Autowired
	private IStaionService staionService;
	
	@RequestMapping("/listStationGPS")
	public String listStationGPS(String token) {
		JSONObject rtnJson = new JSONObject();
		try {
			CacheBean cache = CacheManager.getCacheInfo(token);
			LoginUser loginUser = cache.getLoginUser();
			int partnerId = loginUser.getPartnerId();
			rtnJson = this.staionService.listStationGPS(partnerId);

		} catch (Exception e) {
			rtnJson = CommFunc.errorInfo(Constant.Err, "请求参数错误");
			Log4jUtil.getError().error("请求参数异常", e);
			e.printStackTrace();
		}
		
		return CommFunc.Josn2Str(rtnJson);
	}
}
