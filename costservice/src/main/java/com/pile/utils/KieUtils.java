/**
 * 
 */
package com.pile.utils;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @ClassName: KieUtils
 * @Description:Drools工具类
 * @author: dbr
 * @date: 2018年7月18日 上午10:53:59
 * 
 */
public class KieUtils {
	private static KieContainer kieContainer;

	private static KieSession kieSession;

	public static KieContainer getKieContainer() {
		return kieContainer;
	}

	public static void setKieContainer(KieContainer kieContainer) {
		KieUtils.kieContainer = kieContainer;
		kieSession = kieContainer.newKieSession();
	}

	public static KieSession getKieSession() {
		return kieSession;
	}

	public static void setKieSession(KieSession kieSession) {
		KieUtils.kieSession = kieSession;
	}
}
