/**   
* @Title: WebExceptionHandle.java 
* @Package com.iot.exception 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年10月24日 下午5:24:55 
* @version V1.0   
*/
package com.iot.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** 
* @ClassName: WebExceptionHandle 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年10月24日 下午5:24:55 
*  
*/
@Controller
@ControllerAdvice
@ResponseBody
public class WebExceptionHandle {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
	public ResultBean<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		LoggerUtils.Logger(LogName.ERROR).error("参数解析失败", e);
		ResultBean<String> resultBean = new ResultBean<String>();
		resultBean.setStatus(ErrorCodeEnum.FAILED.getStatus());
		resultBean.setError("could_not_read_json");
		return resultBean;
	}
    
    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultBean<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	LoggerUtils.Logger(LogName.ERROR).error("不支持当前请求方法", e);
        ResultBean<String> resultBean = new ResultBean<String>();
		resultBean.setStatus(ErrorCodeEnum.FAILED.getStatus());
		resultBean.setError("不支持当前请求方法");
		return resultBean;
    }

    /**
     * 415 - Unsupported Media Type
     */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResultBean<?> handleHttpMediaTypeNotSupportedException(Exception e) {
		LoggerUtils.Logger(LogName.ERROR).error("不支持当前媒体类型", e);
		ResultBean<String> resultBean = new ResultBean<String>();
		resultBean.setStatus(ErrorCodeEnum.FAILED.getStatus());
		resultBean.setError("不支持当前媒体类型");
		return resultBean;
	}

    /**
     * 500 - Internal Server Error
     */
    @SuppressWarnings("unchecked")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
	public <T> T handleException(Exception e) {
    	T result = null;
    	LoggerUtils.Logger(LogName.ERROR).error("服务运行异常", e);
        e.printStackTrace();
        
        StackTraceElement stackTraceElement= e.getStackTrace()[0];// 得到异常棧的首个元素
        String className = stackTraceElement.getClassName();
        if (className.equals("com.iot.controller.CallBackController")) {
			result = (T) new ResponseEntity<>(HttpStatus.OK);
		}else {
			ResultBean<String> resultBean = new ResultBean<String>();
	    	resultBean.setStatus(ErrorCodeEnum.FAILED.getStatus());
			resultBean.setError(e.getMessage());
			result = (T) resultBean;
		}
		return result;         
    }
    
}