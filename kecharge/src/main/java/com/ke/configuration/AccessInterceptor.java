/**   
* @Title: AccessInterceptor.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月19日 上午10:23:13 
* @version V1.0   
*/
package com.ke.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/** 
* @ClassName: AccessInterceptor 
* @Description: 自定义拦截器 
* @author dbr
* @date 2018年12月19日 上午10:23:13 
*  
*/
@Component
public class AccessInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = request.getServletPath();
//		String queryJsonStr = request.getParameter("queryJsonStr");
//		String token = request.getParameter("token");
		
		System.out.println("requestPath :" + requestPath);

		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
