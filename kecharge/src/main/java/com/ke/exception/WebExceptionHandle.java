/**   
* @Title: WebExceptionHandle.java 
* @Package com.iot.exception 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月24日 下午5:24:55 
* @version V1.0   
*/
package com.ke.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;


/** 
* @ClassName: WebExceptionHandle 
* @Description: Spring MVC 全局异常处理 
* @author dbr
* @date 2018年10月24日 下午5:24:55 
*  
*/
@RestController
@ControllerAdvice
public class WebExceptionHandle {
    /**
     * 400 - Bad Request
     */
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public JSONObject handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		LoggerUtil.logger(LogName.ERROR).error("参数解析失败", e);
		return CommFunc.errorInfo(Constant.REQUEST_BAD, "参数解析失败");
	}
    
    /**
     * 405 - Method Not Allowed
     */
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	LoggerUtil.logger(LogName.ERROR).error("不支持当前请求方法", e);
		return CommFunc.errorInfo(Constant.REQUEST_BAD, "不支持当前请求方法");
    }

    /**
     * 415 - Unsupported Media Type
     */
//	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public JSONObject handleHttpMediaTypeNotSupportedException(Exception e) {
		LoggerUtil.logger(LogName.ERROR).error("不支持当前媒体类型", e);
		return CommFunc.errorInfo(Constant.REQUEST_BAD, "不支持当前媒体类型");
	}

	/**
	 * 500 - Internal Server Error
	 */
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		LoggerUtil.logger(LogName.ERROR).error("服务运行异常", e);
		e.printStackTrace();
		String errorInfo = e.getMessage() == null ? "请求错误" : e.getMessage();
		return CommFunc.errorInfo(Constant.REQUEST_BAD, errorInfo).toJSONString();
	}
    
}