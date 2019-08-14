package com.wo.interceptor;
/**
 * @author dbr
 *
 */

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wo.cachemanager.CacheBean;
import com.wo.cachemanager.CacheManager;
import com.wo.mapper.InterceptorMapper;
import com.wo.model.LoginUser;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.Log4jUtil;

/**
 * @author dbr
 * 
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {

	@Resource
	private InterceptorMapper interceptorMapper;
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView model) throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		String requestPath = request.getServletPath();
		String queryJsonStr = CommFunc.objToStr(request.getParameter("queryJsonStr"));
		String token = CommFunc.objToStr(request.getParameter("token"));
		
		JSONObject rtnJson = new JSONObject();
		boolean result = true;
//		System.out.println(requestPath);
		try {
			 if (!requestPath.contains("login.json")) {
				if (null == token || token.isEmpty()) {
					result = false;
					rtnJson = CommFunc.errorInfo(Constant.TIMEOUT, "token为空");
				} else {
					CacheBean cache = CacheManager.getCacheInfo(token);
		 			if (cache == null) {
						result = false;
						rtnJson = CommFunc.errorInfo(Constant.TIMEOUT, "登录超时");
					} else {
						if (queryJsonStr != null && !queryJsonStr.isEmpty()) {
							JSONObject json = JSONObject.fromObject(queryJsonStr);

							String pileNo = json.optString("pileNo");
							//判断该充电桩是否属于该合作伙伴
							if (!pileNo.isEmpty()) {
								LoginUser loginUser = cache.getLoginUser();
								int partnerId = loginUser.getPartnerId();
								Map<String,Object> param = new HashMap<String, Object>();
								param.put("pileNo", pileNo);
								param.put("partnerId", partnerId);
								boolean flag = this.interceptorMapper.isPileOfPartner(param);
								if (!flag) {
									rtnJson = CommFunc.errorInfo(Constant.Err, "充电桩["+pileNo+"]不属于该运营商");
									result = false;
								}
							}
							
							String stationNo = json.optString("stationNo");
							//判断该充电站是否属于该合作伙伴
							if (!stationNo.isEmpty()) {
								LoginUser loginUser = cache.getLoginUser();
								int partnerId = loginUser.getPartnerId();
								Map<String,Object> param = new HashMap<String, Object>();
								param.put("stationNo", stationNo);
								param.put("partnerId", partnerId);
								boolean flag = this.interceptorMapper.isStationOfPartner(param);
								if (!flag) {
									rtnJson = CommFunc.errorInfo(Constant.Err, "充电站["+stationNo+"]不属于该运营商");
									result = false;
								}
							}
							
						}
					}
				}
			} else {
				if (queryJsonStr == null || queryJsonStr.isEmpty()) {
					result = false;
					rtnJson = CommFunc.errorInfo(Constant.Err, "请求参数为空");
				}
			}
		} catch (Exception e) {
			result = false;
			rtnJson = CommFunc.errorInfo(Constant.Err, "请求参数错误");
			Log4jUtil.getError().error("拦截器异常" + queryJsonStr, e);
			e.printStackTrace();
		}
		
		if (result == false) {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(rtnJson.toString());
		}

		return result;
	}
}
