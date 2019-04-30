/**   
* @Title: AccessInterceptor.java 
* @Package com.ke.configuration 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月19日 上午10:23:13 
* @version V1.0   
*/
package com.ke.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.mapper.PileMapper;
import com.ke.mapper.SubstparaMapper;
import com.ke.model.LoginUser;
import com.ke.model.OperatorConfig;
import com.ke.utils.ConverterUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.SerializeUtil;

/**
 * @ClassName: AccessInterceptor
 * @Description: 自定义拦截器
 * @author dbr
 * @date 2018年12月19日 上午10:23:13
 * 
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {

	@Resource
	private PileMapper pileMapper;
	
	@Resource
	private SubstparaMapper substparaMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = request.getServletPath();
		System.out.println("requestPath :" + requestPath);

		String queryJsonStr = ConverterUtil.toStr(request.getParameter("queryJsonStr"));
		String token = ConverterUtil.toStr(request.getParameter("token"));
		System.out.println("queryJsonStr: " + queryJsonStr + "  token: " + token);
		boolean result = true;
		JSONObject rtnJson = new JSONObject();

		if (null != token && !token.isEmpty()) {
			String key = Constant.TOKEN_PREFIX + token;
			if (JedisUtil.exists(key)) {
				LoginUser loginUser = CommFunc.getLoginUserByToken(token);
				if (null != queryJsonStr && !queryJsonStr.isEmpty()) {
					JSONObject json = JSONObject.parseObject(queryJsonStr);
					String pileNo = json.getString("pileNo");
					if (null != pileNo) {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("pileNo", pileNo);
						param.put("operatorId", loginUser.getOperatorId());
						boolean flag = pileMapper.isPileOfOperator(param);
						if (!flag) {
							rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电桩[" + pileNo + "]不属于该运营商");
							result = false;
						}
					}
					
					String stationNo = json.getString("stationNo");
					if (null != stationNo && !stationNo.isEmpty()) {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("stationNo", stationNo);
						param.put("operatorId", loginUser.getOperatorId());
						boolean flag = substparaMapper.isStationOfOperator(param);
						if (!flag) {
							rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电站[" + stationNo + "]不属于该运营商");
							result = false;
						}
					}
					
					String serialNumber = json.getString("serialNumber");
					if (serialNumber != null && !serialNumber.isEmpty()) {
						int operatorId = loginUser.getOperatorId();
						key = Constant.OPERATORCONFIG_PREFIX + operatorId;
						OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
						if (null == operatorConfig) {
							rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "运营商不存在");
							result = false;
						}
						
						if(!serialNumber.substring(0, 2).equals(operatorConfig.getSerialnumberPrefix())){
							rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "流水号前缀和运营商不匹配");
							result = false;
						}
					}

				} 				
			} else {
				result = false;
				rtnJson = CommFunc.errorInfo(Constant.TIME_OUT, "登录超时或无权限访问");
			}
		}
		
//		if (queryJsonStr == null || queryJsonStr.isEmpty()) {
//			result = false;
//			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "请求参数为空");
//		}

		if (requestPath.contains("error")) {
			result = false;
			rtnJson = CommFunc.errorInfo(Constant.TIME_OUT, "登录超时或无权限访问");
		}


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
