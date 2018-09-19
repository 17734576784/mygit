/**
 * 
 */
package com.pile.serviceimpl;

import java.io.UnsupportedEncodingException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.springframework.stereotype.Service;

import com.pile.service.IReloadDroolsRules;
import com.pile.utils.KieUtils;

/**
 * @ClassName: ReloadDroolsRulesImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年7月18日 上午10:57:17
 * 
 */
@Service
public class ReloadDroolsRulesImpl implements IReloadDroolsRules {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pile.service.IReloadDroolsRules#reload()
	 */
	@Override
	public boolean reload() throws UnsupportedEncodingException {

		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write("src/main/resources/rules/temp.drl", loadRules());
		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Message.Level.ERROR)) {
			System.out.println(results.getMessages());
			throw new IllegalStateException("### errors ###");
 		}

		KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
		System.out.println("新规则重载成功");

		return true;
	}

	private String loadRules() {
		// 从数据库加载的规则
		return "package com.pile.address\n\n  rule \"Postcode 6 numbers\"\n\n  lock-on-active true\n  when\n  then\n        System.out.println(\"规则2中打印日志：校验通过!\");\n end";
	}

	private KieServices getKieServices() {
		return KieServices.Factory.get();
	}

}
