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

import com.alibaba.fastjson.JSONObject;
import com.ke.utils.Constant;
import com.ke.utils.ConverterUtils;
import com.ke.utils.JedisUtils;
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
		String queryJsonStr = ConverterUtils.toStr(request.getParameter("queryJsonStr"));
		String token = ConverterUtils.toStr(request.getParameter("token"));
		
		boolean result = true;
		JSONObject rtnJson = new JSONObject();
		
		if (!token.isEmpty()) {
			byte[] key = (Constant.TOKEN_PREFIX + token).getBytes();
			if (JedisUtils.exists(key)) {

			} else {

			}

		}
//		else if (token.isEmpty() && queryJsonStr.isEmpty()) {
//			result = false;
//			rtnJson.put(Constant.RESULT_CODE, -2);
//			rtnJson.put(Constant.RESULT_DETAIL, "登录超时");
//		} 
		if (requestPath.contains("error")) {
			result = false;
			rtnJson.put(Constant.RESULT_CODE, -2);
			rtnJson.put(Constant.RESULT_DETAIL, "登录超时或无权限访问");
		}
		
		System.out.println("requestPath :" + requestPath);

		if (result == false) {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(rtnJson.toString());
			
		}
		return result;
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
