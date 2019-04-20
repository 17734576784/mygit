package com.nb.model;

import java.util.Map;

/** 
* @ClassName: RequestInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:52:40 
*  
*/
public interface RequestInfo {
	/** 
	* @ClassName: Method 
	* @Description: Http restful请求枚举类 
	* @author dbr
	* @date 2019年4月19日 上午9:12:07 
	*  
	*/
	public enum Method {
		/** 
		* Http restful请求
		*/ 
		POST, GET, DELETE, PUT
	}

	/** 
	* @Title: setHeader 
	* @Description: 设置Http头信息
	* @param @param params    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void setHeader(Map<String, Object> params);

	/** 
	* @Title: setEntity 
	* @Description: 设置HTTP请求参数 
	* @param @param json    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void setEntity(Object json);

	/** 
	* @Title: setEntity 
	* @Description: 设置HTTP请求参数 
	* @param @param stringMap
	* @param @param fileMap    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void setEntity(Map<String, String> stringMap, Map<String, String> fileMap);

	/** 
	* @Title: setcompleteUrl 
	* @Description: 组建Http参数
	* @param @param completeUrl
	* @param @param params    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void setcompleteUrl(String completeUrl, Map<String, Object> params);

	/** 
	* @Title: sethttpMethod 
	* @Description: 设置http请求方法 
	* @param @param method    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void sethttpMethod(Method method);

	public void setType(boolean upload);

}
