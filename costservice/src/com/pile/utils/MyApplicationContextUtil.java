/**
 * 
 */
package com.pile.utils;

/**   
 * @ClassName:  MyApplicationContextUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年10月19日 下午5:13:29   
 *      
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class MyApplicationContextUtil implements ApplicationContextAware {

	
	private static ApplicationContext context;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		MyApplicationContextUtil.context = context;
	}

	public static ApplicationContext getContext(){ 
		return context;
	}
}
