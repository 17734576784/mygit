/*
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.nb.utils;

/** 
* @ClassName: StringUtil 
* @Description: 字符串工具类
* @author dbr
* @date 2019年4月18日 下午4:50:30 
*  
*/
public class StringUtil {

    /** 
    * @Title: strIsNullOrEmpty 
    * @Description: 判断字符串是否为null或者空 
    * @param @param s
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws 
    */
    public static boolean strIsNullOrEmpty(String s) {
        return (null == s || s.trim().length() < 1);
    }
    
    /** 
	* @Title: toLowerCaseFirstOne 
	* @Description: 首字母转小写
	* @param @param s
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))){
			return s;
		}
		else{
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/** 
	* @Title: toUpperCaseFirstOne 
	* @Description: 首字母转大写
	* @param @param s
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}
}
