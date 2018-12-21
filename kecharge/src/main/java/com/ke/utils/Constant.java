/**   
* @Title: Constant.java 
* @Package com.ke.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 上午10:14:07 
* @version V1.0   
*/
package com.ke.utils;

/** 
* @ClassName: Constant 
* @Description: 常量定义类
* @author dbr
* @date 2018年12月21日 上午10:14:07 
*  
*/
public class Constant {

	/**用户权限redis前缀*/
	public static String USER_PERMS ="perms_";
	
	/**Token redis前缀*/
	public static String TOKEN ="token_"; 
	
	/** 返回结果码描述 */
	public static String RESULT_CODE = "status";

	/** 返回结果原因描述 */
	public static String RESULT_DETAIL = "error";
	/** 返回结果码 成功 */
	public static int SUCCESS = 0;
	/** 返回结果码 请求错误 */
	public static int REQUEST_BAD = -1;
	/** 返回结果码 登录超时 */
	public static int TIME_OUT = -2;
}

