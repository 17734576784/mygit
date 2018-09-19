/**
 * 
 */
package com.pile.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pile.model.PointDomain;
import com.pile.service.IReloadDroolsRules;
import com.pile.utils.KieUtils;
import com.pile.utils.Log4jUtils;

/**
 * @ClassName: TestController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年7月18日 上午11:01:29
 * 
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Resource
	private IReloadDroolsRules rules;

	@RequestMapping("/address")
	public void test() {
		Log4jUtils.getChargerecord().info("1111");
		Log4jUtils.getDiscountinfo().info("22222");
		Log4jUtils.getInfo().info("3333");
		Log4jUtils.getMinaserver().info("4444");
		Log4jUtils.getError().info("5555");
		
		KieSession kieSession = KieUtils.getKieSession();

		PointDomain pointDomain = new PointDomain();
		pointDomain.setUserName("hello kity");
		pointDomain.setBackMoney(100d);
		pointDomain.setBuyMoney(500d);
		pointDomain.setBackNums(1);
		pointDomain.setBuyNums(5);
		pointDomain.setBillThisMonth(5);
		pointDomain.setBirthDay(true);
		pointDomain.setPoint(0l);
		
		kieSession.insert(pointDomain);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		System.out.println("执行完毕规则引擎决定发送积分:"+pointDomain.getPoint());

	}

	@RequestMapping("/reload")
	public String reload() throws IOException {
		rules.reload();
		return "ok";
	}
}
