package com.wo.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wo.cachemanager.CacheBean;
import com.wo.cachemanager.CacheManager;
import com.wo.model.LoginUser;

import net.sf.json.JSONObject;

/**
 * @author dbr
 * 
 */
public class CommFunc {
	/**-------------------------数据类型间的转换方法------开始-------------------*/

	
	/**
	 * 将Object类型转换成String类型
	 * @example1 objToStr(79)->"79"   		(Object是所有数据类型的父类)
	 * @example2 objToStr(true)->"true" 
	 * @param    obj {传入Object类型}
	 * @return   返回String字符串
	 * 
	 * */
	public static String objToStr(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString().trim();
	}
	
	/**
	 * 将Object类型转换成双精度浮点 Double类型
	 * @example1 objToDbl(79)->79.0
	 * @example2 objToDbl("")->0.0
	 * @param    obj {传入Object类型}
	 * @return   返回Double类型 
	 */
	public static double objToDbl(Object obj) {
		if (obj == null)
			return 0.0;
		else if (obj.toString().trim().equals(""))
			return 0.0;
		else if (obj.toString().trim().equals("－"))
			return 0.0;
		else {
			String sdb = obj.toString();
			sdb = sdb.replaceAll("%", "").replaceAll(",", "");
			return Double.parseDouble(sdb);
		}
	}
	
	/**
	 * 将Object类型转换成Byte类型					(Byte范围-1~127)
	 * @example1 objToByte(2)->2
	 * @example2 objToByte("1")->1
	 * @param    obj {传入Object类型}
	 * @return   返回Byte类型 
	 */
	public static byte objToByte(Object obj) {
		if (obj == null)
			return 0;
		else if (obj.toString().trim().equals(""))
			return 0;
		else
			return Byte.parseByte(obj.toString());
	}
	
	/**
	 * 将Object类型转换成Short类型
	 * @example1 objToShort(2)->2
	 * @example2 objToShort("1")->1
	 * @param    obj {传入Object类型}
	 * @return   返回Short类型 
	 */
	public static short objToShort(Object obj) {
		if (obj == null)
			return 0;
		else if (obj.toString().trim().equals(""))
			return 0;
		else
			return Short.parseShort(obj.toString());
	}
	
	/**
	 * 将Object类型转换成Int类型
	 * @example1 objToInt(2)->2
	 * @example2 objToInt("1")->1
	 * @param    obj  {传入Object类型}
	 * @return   返回Int类型 
	 */
	public static int objToInt(Object obj) {
		if (obj == null)
			return 0;
		else if (obj.toString().trim().equals(""))
			return 0;
		else
			return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 将Object类型转换成Long类型
	 * @example1 objToLong(2)->2
	 * @example2 objToLong("1")->1
	 * @param    obj {传入Object类型}
	 * @return   返回Long类型 
	 */
	public static long objToLong(Object obj) {
		if (obj == null)
			return 0;
		else if (obj.toString().trim().equals(""))
			return 0;
		else
			return Long.parseLong(obj.toString());
	}
	
	/**
	 * 将Object类型转换成单精度Float类型
	 * @example1 objToFloat(2)->2.0
	 * @example2 objToFloat("1")->1.0
	 * @param    obj {传入Object类型}
	 * @return   返回Float类型 
	 */
	public static float objToFloat(Object obj) {
		if (obj == null)
			return 0;
		else if (obj.toString().trim().equals(""))
			return 0;
		else
			return Float.parseFloat(obj.toString());
	}
	
	/**
	 * 将string类型转换成Int类型
	 * @example1 strToInt("79")->79
	 * @example2 strToInt("80")->80
	 * @param    str   {传入String类型}
	 * @return   返回Int类型 
	 */     
	public static int strToInt(String str) {
		str = str.replaceAll(",", "");
		int[] offset = { 0 };
		return strToInt(str, 0, 10, offset);
	}
	
	/**
	 * 将string类型转换成Int类型
	 * @example1 strToInt("79",0,10,offset)->offset 数组中存放79
	 * @example2 strToInt("8011",1,10,offset)->offset 数组中存放011
	 * @param    str    {传入String类型}
	 * @param    begin  {传入字符串的起始位置}
	 * @param    radix  {传入转换基数}
	 * @param    offset {传入存放返回数据的int型数组}
	 * @return   返回Int类型 
	 */ 	
	public static int strToInt(String str, int begin, int radix, int[] offset) {
		if (offset != null)
			offset[0] = 0;
		if (str == null || str.length() <= 0)
			return 0;
		if (begin < 0 || begin >= str.length() || radix < 2 || radix > 16)
			return 0;

		int value = -1;
		int count = 0;
		char temp;
		boolean neg = false;

		for (; begin < str.length();) {
			if (str.charAt(begin) == ' ' || str.charAt(begin) == '\t'
					|| str.charAt(begin) == '\n') {
				begin++;
				if (offset != null)
					offset[0]++;
			} else
				break;
		}

		if (begin == str.length()) {
			if (offset != null)
				offset[0] = 0;
			return 0;
		}

		if (str.charAt(begin) == '-') {
			neg = true;
			begin++;
			offset[0]++;
		}
		if (str.charAt(begin) == '+') {
			neg = false;
			begin++;
			offset[0]++;
		}

		if (begin >= str.length()) {
			if (offset != null)
				offset[0] = 0;
			return 0;
		}

		for (int i = begin; i < str.length(); i++) {
			temp = str.charAt(i);
			if (radix <= 10) {
				if (((((int) temp) - ((int) '0'))) >= 0
						&& (((((int) temp) - ((int) '0'))) < radix))
					count++;
				else
					break;
			} else {
				if (((((int) temp) - ((int) '0'))) >= 0
						&& (((((int) temp) - ((int) '0'))) < radix)
						|| (((((int) temp) - ((int) 'A'))) >= 0 && (((((int) temp))
								- ((int) 'A') + 10) < radix))
						|| ((((((int) temp) - ((int) 'a'))) >= 0) && (((((int) temp))
								- ((int) 'a') + 10) < radix)))
					count++;
				else
					break;
			}
		}
		if (count <= 0) {
			if (offset != null)
				offset[0] = 0;
			return 0;
		}

		value = 0;
		int tempValue = 0;

		for (int i = 0; i < count; i++) {
			temp = str.charAt(begin + i);
			if (radix <= 10) {
				tempValue = ((int) temp) - ((int) '0');
				value += tempValue * Math.pow(radix, count - i - 1);
			} else {
				if (((((int) temp) - ((int) '0'))) >= 0
						&& (((((int) temp) - ((int) '0'))) < radix)) {
					tempValue = ((int) temp) - ((int) '0');
					value += tempValue * Math.pow(radix, count - i - 1);
				} else {
					if (((((int) temp) - ((int) 'A'))) >= 0
							&& (((((int) temp)) - ((int) 'A') + 10) < radix)) {
						tempValue = ((int) temp) - ((int) 'A') + 10;
						value += tempValue * Math.pow(radix, count - i - 1);
					} else {
						tempValue = ((int) temp) - ((int) 'a') + 10;
						value += tempValue * Math.pow(radix, count - i - 1);
					}
				}
			}
		}

		if (offset != null)
			offset[0] += count;
		if (neg)
			value = 0 - value;
		return value;
	}

	/**
	 * 将String类型转换成Long类型
	 * @example1 strToLong("101",offset)->101
	 * @example2 strToLong("787",offset)->787
	 * @param    str    {传入由char类型组成的数组str}
	 * @param    offset {传入存放返回值的int型数组}
	 * @return   返回Long型数值
	 */
	public static long strToLong(char[] str, int[] offset) {
		if (str == null || str.length <= 0)
			return 0;
		if (offset != null && (offset[0] < 0 || offset[0] >= str.length))
			return 0;
		int beginOF = (offset == null ? 0 : offset[0]);

		for (; beginOF < str.length; beginOF++) {
			if (str[beginOF] == ' ' || str[beginOF] == '\t'
					|| str[beginOF] == '\n')
				continue;
			else
				break;
		}

		int endOF = beginOF;

		int count = 0;
		for (; endOF < str.length; endOF++) {
			if ((str[endOF] >= '0' && str[endOF] <= '9') || (str[endOF] == '+')
					|| (str[endOF] == '-') || (str[endOF] == 'E')
					|| (str[endOF] == 'e')) {
				count++;
			} else
				break;
		}

		if (offset != null)
			offset[0] = endOF;

		if (count == 0)
			return 0;

		String strData = new String(str, beginOF, count);

		return Long.parseLong(strData);
	}
    
	/**
	 * 将String类型转换成Long类型
	 * @example1 strToLong("101",offset)->101
	 * @example2 strToLong("787",offset)->787
	 * @param    str    {传入String类型字符串str}
	 * @param    offset {传入存放返回值的int型数组}
	 * @return   返回Long型数值
	 */
	public static long strToLong(String str, int[] offset) {

		if (str == null || str.length() <= 0)
			return 0;
		if (offset != null && (offset[0] < 0 || offset[0] >= str.length()))
			return 0;
		int beginOF = (offset == null ? 0 : offset[0]);

		for (; beginOF < str.length(); beginOF++) {
			if (str.charAt(beginOF) == ' ' || str.charAt(beginOF) == '\t'
					|| str.charAt(beginOF) == '\n')
				continue;
			else
				break;
		}
		int endOF = beginOF;

		int count = 0;
		for (; endOF < str.length(); endOF++) {
			if ((str.charAt(endOF) >= '0' && str.charAt(endOF) <= '9')
					|| (str.charAt(endOF) == '+') || (str.charAt(endOF) == '-')
					|| (str.charAt(endOF) == 'E') || (str.charAt(endOF) == 'e')) {
				count++;
			} else
				break;
		}

		if (offset != null)
			offset[0] = endOF;

		if (count == 0)
			return 0;

		String strData = str.substring(beginOF, beginOF + count);
		return Long.parseLong(strData);
	}

	/**
	 * 将String类型转换成Long类型
	 * @example1 strToLong("79")->79
	 * @example2 strToLong("8011")->8011
	 * @param    str {传入char类型数组}
	 * @return   返回Long类型 
	 */ 
	public static long strToLong(char[] str) {
		int offset[] = { 0 };

		return strToLong(str, offset);
	}
 
	/**
	 * 将String类型转换成Int类型(实现函数)
	 * @example1 strToInt("79",offset)->79
	 * @example2 strToInt("8011",offset)->8011
	 * @param    str    {传入由char类型组成的数组}
	 * @param    offset {传入用于接收结果的int型数组}
	 * @return   返回Int类型 
	 */
	public static int strToInt(char[] str, int[] offset) {
		if (str == null || str.length <= 0)
			return 0;
		if (offset != null && (offset[0] < 0 || offset[0] >= str.length))
			return 0;
		int beginOF = (offset == null ? 0 : offset[0]);

		for (; beginOF < str.length; beginOF++) {
			if (str[beginOF] == ' ' || str[beginOF] == '\t'
					|| str[beginOF] == '\n')
				continue;
			else
				break;
		}

		int endOF = beginOF;

		int count = 0;
		for (; endOF < str.length; endOF++) {
			if ((str[endOF] >= '0' && str[endOF] <= '9') || (str[endOF] == '+')
					|| (str[endOF] == '-') || (str[endOF] == 'E')
					|| (str[endOF] == 'e')) {
				count++;
			} else
				break;
		}

		if (offset != null)
			offset[0] = endOF;

		if (count == 0)
			return 0;

		String strData = new String(str, beginOF, count);
		return Integer.parseInt(strData);
	}

	/**
	 * 将String类型转换为Int类型(调用函数) 
	 * @example1 strToInt("898")->898
	 * @example2 strToInt("676")->676
	 * @param    str  {传入char类型数组str}
	 * @return   返回Int类型数值
	 */
	public static int strToInt(char[] str) {
		int offset[] = { 0 };
		return strToInt(str, offset);
	}

	/**
	 * 将char类型数组值转换为Double类型(实现函数)
	 * @example1 strToDbl("123")->123.0
	 * @example2 strToDbl("456")->456.0
	 * @param    str    {传入char型数组str}
	 * @param    offset {传入用于接收返回值的int型数组offset}
	 * @return 	   返回Double型数值 
	 */
	public static double strToDbl(char[] str, int[] offset) {
		if (str == null || str.length <= 0)
			return 0;
		if (offset != null && (offset[0] < 0 || offset[0] >= str.length))
			return 0;
		int beginOF = (offset == null ? 0 : offset[0]);

		for (; beginOF < str.length; beginOF++) {
			if (str[beginOF] == ' ' || str[beginOF] == '\t'
					|| str[beginOF] == '\n')
				continue;
			else
				break;
		}

		int endOF = beginOF;

		int count = 0;
		for (; endOF < str.length; endOF++) {
			if ((str[endOF] >= '0' && str[endOF] <= '9') || (str[endOF] == '.')
					|| (str[endOF] == '+') || (str[endOF] == '-')
					|| (str[endOF] == 'E') || (str[endOF] == 'e')) {
				count++;
			} else
				break;
		}

		if (offset != null)
			offset[0] = endOF;

		if (count == 0)
			return 0;

		String strData = new String(str, beginOF, count);

		return Double.parseDouble(strData);
	}
   
	/**
	 * 将String类型转换为Double类型(实现函数)
	 * @example1 strToDbl("000")->0.0
	 * @example2 strToDbl("111")->111.0
	 * @param    str    {传入String类型的字符串str}
	 * @param    offset {传入接收返回值的int型数组}
	 * @return   返回Double型数值
	 */
	public static double strToDbl(String str, int[] offset) {
		if (str == null || str.length() <= 0)
			return 0;
		if (offset != null && (offset[0] < 0 || offset[0] >= str.length()))
			return 0;
		int beginOF = (offset == null ? 0 : offset[0]);

		for (; beginOF < str.length(); beginOF++) {
			if (str.charAt(beginOF) == ' ' || str.charAt(beginOF) == '\t'
					|| str.charAt(beginOF) == '\n')
				continue;
			else
				break;
		}

		int endOF = beginOF;

		int count = 0;
		for (; endOF < str.length(); endOF++) {
			if ((str.charAt(endOF) >= '0' && str.charAt(endOF) <= '9')
					|| (str.charAt(endOF) == '.') || (str.charAt(endOF) == '+')
					|| (str.charAt(endOF) == '-') || (str.charAt(endOF) == 'E')
					|| (str.charAt(endOF) == 'e')) {
				count++;
			} else
				break;
		}

		if (offset != null)
			offset[0] = endOF;

		if (count == 0)
			return 0;

		String strData = str.substring(beginOF, beginOF + count);
		return Double.parseDouble(strData);
	}

	/**
	 * 将String类型转换为Double类型(调用函数)
	 * @example1 strToDbl("000")->0.0
	 * @example2 strToDbl("111")->111.0
	 * @param    str  {传入char类型的数组}
	 * @return 	   返回Double型数值
	 */
	public static double strToDbl(char[] str) {
		int offset[] = { 0 };
		return strToDbl(str, offset);
	}  

	/**
	 * 将String类型转换为Double类型(调用函数)
	 * @example1 strToDbl("000")->0.0
	 * @example2 strToDbl("111")->111.0
	 * @param    str  {传入String类型的字符串}
	 * @return   返回Double型数值
	 */  

	public static double strToDbl(String str) {
		int[] offset = { 0 };
		return strToDbl(str, offset);
	}	
	/**-------------------------数据类型间的转换方法------结束-------------------*/
	
	
	
	
	
	/**-------------------------四舍五入法------开始--------------------------*/
	/**
	 * 四舍五入返回BigDecimal
	 * @example1 roundBase(34.56,1)->34.6
	 * @example2 roundBase(34.81,1)->34.8
	 * @param    d     {传入Double类型变量d}
	 * @param    scale {传入四舍五入后结果保留的精度scale}
	 * @return   返回BigDecimal对象
	 * @throws   IllegalAccessException {抛出安全权限异常}
	 */	
	public static BigDecimal roundBase(Double d, int scale)throws IllegalAccessException {
		if (d == null) {
			return null;
		}
		if (scale < 0) {
			scale = Math.abs(scale);
			// throw new
			// IllegalAccessException("scale must be a positive integer or zero!");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(d));
		BigDecimal b2 = new BigDecimal("1");
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 四舍五入返回Double
	 * @example1 roundBase(34.56,1)->34.6
	 * @example2 roundBase(34.81,1)->34.8
	 * @param    d     {传入Double类型变量d}
	 * @param    scale {传入四舍五入后结果保留的精度scale}
	 * @return   返回Double对象
	 * @throws   IllegalAccessException {抛出安全权限异常}
	 */	
	public static double round(Double d, int scale) throws IllegalAccessException {
		BigDecimal b = roundBase(d, scale);
		if (b == null)
			return 0;
		else
			return b.doubleValue();
	}

	/**
	 * 四舍五入返回String
	 * @example1 roundBase(34.56, 1)->"34.6"
	 * @example2 roundBase(34.81, 1)->"34.8"
	 * @param    d      {传入Double类型变量d}
	 * @param    scale  {传入四舍五入后结果保留的精度scale}
	 * @return   string {返回String对象}
	 * @throws   IllegalAccessException {抛出安全权限异常}
	 */	
	public static String roundTosString(Double d, int scale) throws IllegalAccessException {
		BigDecimal b = roundBase(d, scale);
		if (b == null)
			return "";
		else
			return b.toString();
	}
	/**-------------------------四舍五入法------结束---------------------------*/	
	
	
	
	/**-------------------------日期转换方法------开始--------------------------*/
	
	/**
	 * 将字符串的日期转成整数 
	 * @example1 YMDtoInt("2016年07月07日 ", 0)->20160707
	 * @example2 YMDtoInt("2016-07-07", 1)->20160707 
	 * @param    date   {传入字符串日期date}   
	 * @param    type   {传入转换类型type}   
	 * @return   返回整型的年月日   
	 * **/	
	public static String YMDtoInt(String date, int type) {
		if (type == 0) {
			return date.replace("年", "").replace("月", "").replace("日", "");
		} else {
			return date.replace("-", "").replace("-", "").replace("-", "");
		}
	}
	
	/**
	 * 将Object格式化为日期格式“YYYY年MM月DD日”
	 * @example1 FormatToYMD("20160707")->2016年07月07日
	 * @example2 FormatToYMD("201607")->2016年07月
	 * @param    obj  {传入Object类型日期} 
	 * @return   返回字符串型的年月日 
	 */
	public static String FormatToYMD(Object obj)
	{
		if(obj == null) return "";
		String strdate = obj.toString().trim();
		if( !strdate.isEmpty()) {
			if(strdate.length()== 8){
				strdate = strdate.substring(0,4) + "年" + strdate.substring(4,6) + "月" + strdate.substring(6,8) + "日";
				return strdate;
			}
			else if(strdate.length()== 6){
				strdate = strdate.substring(0,4) + "年" + strdate.substring(4,6) + "月";
				return strdate;
			}
			else if(strdate.length()== 4){
				strdate = strdate.substring(0,4) + "年";
				return strdate;
			}
			else{
				return "";
			}
		}else{
			return "";
		}		
	}
	
	/**
	 * 将Object格式化为日期格式“YYYY-MM-DD”
	 * @example1 FormatToYMD("20160707", "day")->2016-07-07
	 * @example2 FormatToYMD("201607", "month")->1970-01
	 * @param    obj  {传入Object类型日期} 
	 * @param    type {传入转换的日期格式 type="day" or "month"}
	 * @return   返回字符串型的YYYY-MM-DD 
	 */
	public static String FormatToYMD(Object obj, String type) {
		String rtn_str = null;
		if (type.equals("day")) {
			rtn_str = "1970-01-01";
		} else if (type.equals("month")) {
			rtn_str = "1970-01";
		} else {
			return "";
		}

		if (obj == null)
			return rtn_str;
		String strdate = obj.toString().trim();
		if (!strdate.isEmpty()) {
			if (strdate.length() == 8) {
				if (type.equals("day")) {
					strdate = strdate.substring(0, 4) + "-"+ strdate.substring(4, 6) + "-"+ strdate.substring(6, 8);
				} else if (type.equals("month")) {
					strdate = strdate.substring(0, 4) + "-"+ strdate.substring(4, 6);
				}
				return strdate;
			} else {
				return rtn_str;
			}
		} else {
			return rtn_str;
		}
	}
	
	/**
	 * 将Object格式化为日期格式“YYYY年MM月DD日” (8位日期为有效,否则为"")
	 * @example1 intToYMD("20160707")->2016年07月07日
	 * @example2 intToYMD("201607")->""
	 * @param    date  {传入Object类型日期} 
	 * @return   返回字符串型的年月日 
	 */
	public static String intToYMD(Object date) {
		String formatYMD = "";
		if (date == null) {
			return formatYMD;
		}
		String dateStr = date.toString();
		if (dateStr.length() == 8) {
			formatYMD = dateStr.substring(0, 4) + "年" + dateStr.substring(4, 6)
					+ "月" + dateStr.substring(6, 8) + "日";
		}

		return formatYMD;
	}

	/**
	 * 8位日期转化，6位时间转换
	 * @example1 intToTime(20160707, 1)->2016-07-07
	 * @example2 intToTime(112959, 1)->11:29:59
	 * @param    time  {传入Object类型日期或时间} 
	 * @param    type {传入转换日期或时间的格式type} 
	 * @return   返回字符串型的日期或时间 
	 */
	public static String intToTime(Object time, int type) {
		String formatTime = "";
		if (time == null) {
			return formatTime;
		}
		String timeStr = time.toString();
		if (timeStr.length() == 8) {
			switch (type) {
			case 1:
				formatTime = timeStr.substring(0, 4) + "-"+ timeStr.substring(4, 6) + "-"+ timeStr.substring(6, 8);
				break;
			case 2:
				formatTime = timeStr.substring(0, 4) + "/"+ timeStr.substring(4, 6) + "/"+ timeStr.substring(6, 8);
				break;
			default:
				formatTime = timeStr.substring(0, 4) + "年"	+ timeStr.substring(4, 6) + "月"	+ timeStr.substring(6, 8) + "日";
				break;
			}
		} else if (timeStr.length() == 6) {
			switch (type) {
			case 1:
				formatTime = timeStr.substring(0, 2) + ":"+ timeStr.substring(2, 4) + ":"+ timeStr.substring(4, 6);
				break;
			default:
				formatTime = timeStr.substring(0, 2) + "时"	+ timeStr.substring(2, 4) + "分"	+ timeStr.substring(4, 6) + "秒";
				break;
			}
		} else if (timeStr.length() < 6) {
			while (timeStr.length() < 6) {
				timeStr = "0" + timeStr;
			}
			switch (type) {
			case 1:
				formatTime = timeStr.substring(0, 2) + ":"+ timeStr.substring(2, 4) + ":"+ timeStr.substring(4, 6);
				break;
			default:
				formatTime = timeStr.substring(0, 2) + "时"	+ timeStr.substring(2, 4) + "分"	+ timeStr.substring(4, 6) + "秒";
				break;
			}
		}
		return formatTime;
	}
	
	/**
	 * 将Object格式化为时间格式"hh时mm分"或"hh:mm", 检查object不为空且长度4位以下时，格式化；否则：返回""
	 * @example1 FormatToHM(113529)->""
	 * @example2 FormatToHM(1136)->"11:36"
	 * @param    obj  {传入Object类型的时间} 
	 * @param    type {传入转换时间的格式type} 
	 * @return   返回字符串型的时间 
	 */
	public static String FormatToHM(Object obj, int type) {
		String strdate = "";
		if (obj != null) {
			strdate = obj.toString().trim();
			if (strdate.length() > 4)
				return "";
			while (strdate.length() < 4) {
				strdate = "0" + strdate;      /////////////////????????
			}
			if (type == 1) {
				strdate = strdate.substring(0, 2) + ":"
						+ strdate.substring(2, 4);
			} else {
				strdate = strdate.substring(0, 2) + "时"
						+ strdate.substring(2, 4) + "分";
			}
		}
		
		return strdate;
	}
		
	/**
	 * 将Object格式化为时间格式"hh时mm分ss秒"或"hh:mm:ss",检查object不为空且长度6位时，格式化；否则：返回""
	 * @example1 FormatToHMS(113529, 1)->"11:35:29"
	 * @example2 FormatToHMS(1136)->""
	 * @param    obj  {传入Object类型的时间} 
	 * @param    type {传入转换时间的格式,int类型} 
	 * @return   返回字符串型的时间 
	 */
	public static String FormatToHMS(Object obj, int type) {
		if (obj == null)
			return "";
		String strdate = obj.toString().trim();

		if (strdate.length() < 6) {
			strdate = "000000" + strdate;
		}
		if (strdate.length() > 6) {
			strdate = strdate.substring(strdate.length() - 6, strdate.length());
		}
		if (!strdate.isEmpty()) {
			if (strdate.length() == 6) {
				if (type == 1) {
					strdate = strdate.substring(0, 2) + ":"
							+ strdate.substring(2, 4) + ":"
							+ strdate.substring(4, 6);
				} else {
					strdate = strdate.substring(0, 2) + "时"
							+ strdate.substring(2, 4) + "分"
							+ strdate.substring(4, 6) + "秒";
				}
				return strdate;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 将Object格式化为日期格式"DD日hh时mm分",检查object不为空且长度6位时，格式化；否则：返回""
	 * @example1 FormatToDHM(111204)->"11日12时04分"
	 * @example2 FormatToDHM(91204)->"09日12时04分"
	 * @param    obj  {传入Object类型的时间} 
	 * @return   返回字符串型的时间 
	 */
	public static String FormatToDHM(Object obj) {
		String strdate = "";
		if (obj != null) {
			strdate = obj.toString().trim();
			if (!strdate.isEmpty()) {
				if (strdate.length() == 6) {
					strdate = strdate.substring(0, 2) + "日"	+ strdate.substring(2, 4) + "时"	+ strdate.substring(4, 6) + "分";
				} else if (strdate.length() == 5) {
					strdate = "0" + strdate.substring(0, 1) + "日"+ strdate.substring(1, 3) + "时"+ strdate.substring(3, 5) + "分";
				}
			}
		}
		return strdate;
	}
	
	/**
	 * 将Object格式化为日期格式"YYYY年MM月",检查object不为空且长度6位以上时（6位YYYYMM或8位YYYYMMDD），格式化;否则：返回""
	 * @example1 FormatToYM(20160711)->"2016年07月"
	 * @example2 FormatToYM(160711)->""
	 * @param    obj {传入Object类型的时间} 
	 * @return   返回字符串型的时间 
	 */
	public static String FormatToYM(Object obj) {
		String strdate = obj.toString().trim();
		if (!strdate.isEmpty()) {
			if (strdate.length() >= 6) {
				strdate = strdate.substring(0, 4) + "年"
						+ strdate.substring(4, 6) + "月";
				return strdate;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 计算距当前日期 X天 的日期 (调用函数)
	 * @example1 computeByDay("20160711",10)->20160721
	 * @example2 computeByDay("20160711",-10)->20160701
	 * @param    nowdate  {传入当前日期}
	 * @param    day_diff {传入距离当前的天数}
	 * @return   返回距离当前X天的int型日期
	 */
	public static int computeByDay(String nowdate, int day_diff) {
		if (nowdate.length() == 6) {
			nowdate = nowdate + "28";
		}
		String year = nowdate.substring(0, 4);
		String month = nowdate.substring(4, 6);
		String day = nowdate.substring(6, 8);

		return computeByDay(year, month, day, day_diff);
	}
	
	
	/**
	 * 计算距当前日期 X天 的日期 (调用函数)
	 * @example1 computeByDay("2016","07","11",10)->20160721
	 * @example2 computeByDay("2016","07","11",-10)->20160701
	 * @param    year     {传入当前年year}
	 * @param    month    {传入当前月month}
	 * @param    day      {传入当前日day}
	 * @param    day_diff {传入距离当前的天数day_diff}
	 * @return 	   返回距离当前X天的int型日期
	 */
	public static int computeByDay(String year, String month, String day,int day_diff) {
		return computeByDay(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day), day_diff);
	}
	
	/**
	 * 计算距当前日期 X天 的日期 (实现函数)
	 * @example1 computeByDay(2016,07,11,10)->20160721
	 * @example2 computeByDay(2016,07,11,-10)->20160701
	 * @param    year     {传入当前年year}
	 * @param    month    {传入当前月month}
	 * @param    day      {传入当前日day}
	 * @param    day_diff {传入距离当前的天数day_diff}
	 * @return   返回距离当前X天的int型日期
	 */
	public static int computeByDay(int year, int month, int day, int day_diff) {
		if (month < 1 || month > 12 || day < 1 || day > 31) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		int end_date = day + day_diff;
		c1.set(year, month - 1, end_date);

		SimpleDateFormat fd1 = new SimpleDateFormat("yyyyMMdd");
		Date date = c1.getTime();
		String back_date = fd1.format(date);
		return Integer.parseInt(back_date);
	}
	
	/**
	 * 计算距当前日期 X月的日期。如果结果月份天数少于当前月，则取结果月最后一天(调用函数)
	 * @example1 computeByMonth(20160725,2)->20160825
	 * @example2 computeByMonth(201607,-2)->20160501
	 * @param    nowdate     {传入String类型的当前日期或当前月nowdate}
	 * @param    month_diff  {传入int类型的相差月份，正数向后，负数向前}
	 * @return   返回距离当前X月的int型日期
	 */
	public static int computeByMonth(String nowdate, int month_diff) {
		int str_l = nowdate.length();
		if (str_l < 6 || str_l > 8) {
			return 0;
		} else if (str_l == 6) {
			nowdate += "01"; // 变为8位,默认为该月第一天
		}

		String year = nowdate.substring(0, 4);
		String month = nowdate.substring(4, 6);
		String day = nowdate.substring(6, 8);

		return computeByMonth(year, month, day, month_diff);
	}
	
	/**
	 * 计算距当前日期 X月的日期。如果结果月份天数少于当前月，则取结果月最后一天(调用函数)
	 * @example1 computeByMonth("2016","07","25",2)->20160825
	 * @example2 computeByMonth("2016","07","01",-2)->20160501
	 * @param    year    	{传入String类型的当前年}
	 * @param    month   	{传入String类型的当前月}
	 * @param    day    	{传入String类型的当前日}
	 * @param    month_diff {传入int类型的相差月份，正数向后，负数向前}
	 * @return   返回距离当前X月的int型日期
	 */
	public static int computeByMonth(String year, String month, String day, int month_diff){
		return computeByMonth(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), month_diff);
		
	}
	
	/**
	 * 计算距当前日期 X月的日期。如果结果月份天数少于当前月，则取结果月最后一天(调用函数)
	 * @example1 computeByMonth("2016","07","25",2)->20160825
	 * @example2 computeByMonth("2016","07","01",-2)->20160501
	 * @param    year    		{传入int类型的当前年}
	 * @param    month   		{传入int类型的当前月}
	 * @param    day    		{传入int类型的当前日}
	 * @param    month_diff     {传入int类型的相差月份，正数向后，负数向前}
	 * @return   返回距离当前X月的int型日期
	 */
	public static int computeByMonth(int year, int month, int day,
			int month_diff) {
		if (month > 12 || month < 1 || day < 1 || day > 31) {
			return 0;
		}

		Calendar c1 = Calendar.getInstance();
		c1.set(year, month + month_diff - 1, 1);

		int end_date = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (day > end_date) {
			c1.set(Calendar.DATE, end_date);
		} else {
			c1.set(Calendar.DATE, day);
		}

		SimpleDateFormat fd1 = new SimpleDateFormat("yyyyMMdd");
		String back_date = "";

		Date date = c1.getTime();
		back_date = fd1.format(date);
		return Integer.parseInt(back_date);
	}
		
	/**
	 * 返回某年某月最后一天
	 * @example1 lastDateOfMonth("201607")->20160731
	 * @example2 lastDateOfMonth("20160712")->20160731
	 * @param    yearmonth {传入String类型的某年月}
	 * @return	  返回某年月最后一天的int型日期
	 */
	public static int lastDateOfMonth(String yearmonth) {
		String year = yearmonth.substring(0, 4);
		String month = yearmonth.substring(4, 6);
		return lastDateOfMonth(year, month);
	}
	
	public static int lastDateOfMonth(String year, String month) {
		return lastDateOfMonth(Integer.parseInt(year), Integer.parseInt(month));
	}
	
	public static int lastDateOfMonth(int year, int month) {
		if (month > 12 || month < 1) {
			return 0;
		}

		Calendar c1 = Calendar.getInstance();
		c1.set(year, month - 1, 1);

		SimpleDateFormat fd1 = new SimpleDateFormat("yyyyMMdd");
		String back_date = "";

		int end_date = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		c1.set(Calendar.DATE, end_date);

		Date date = c1.getTime();
		back_date = fd1.format(date);
		return Integer.parseInt(back_date);
	}
	
	/**
	 * 返回某年某月的第一天
	 * @example1 firstDateOfMonth("20160712")->20160701
	 * @example2 firstDateOfMonth("201607")->20160701
	 * @param    yearmonth {传入String类型的某年月 }
	 * @return   返回某年月第一天的int型日期
	 */
	public static int firstDateOfMonth(String yearmonth) {
		String year = yearmonth.substring(0, 4);
		String month = yearmonth.substring(4, 6);
		return firstDateOfMonth(year, month);
	}

	public static int firstDateOfMonth(String year, String month) {
		return Integer.parseInt(year + month + "01");
	}
	
	/**
	 * 返回某年某月的天数
	 * @example1 lastDayOfDate("201607")->31
	 * @example2 lastDayOfDate("20160201")->29
	 * @param    yearmonth {传入String类型的某年月yearmonth}
	 * @return   以int类型返回某年月的天数 
	 */
	public static int lastDayOfDate(String yearmonth) {
		if (yearmonth == null || yearmonth.isEmpty() || yearmonth.length() < 6) {
			return 0;
		}
		String year = yearmonth.substring(0, 4);
		String month = yearmonth.substring(4, 6);
		return lastDayOfDate(year, month);
	}
	
	/**
	 * 返回某年某月的天数
	 * @example1 lastDayOfDate("2016", "07")->31
	 * @example2 lastDayOfDate("2016","02")->29
	 * @param  year  {传入String类型的某年year}
	 * @param  month {传入String类型的某月month}
	 * @return 以int类型返回某年月的天数 
	 */
	public static int lastDayOfDate(String year, String month) {
		Calendar MonthEnd = Calendar.getInstance();
		MonthEnd.clear();
		MonthEnd.set(Calendar.YEAR, Integer.parseInt(year));
		MonthEnd.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		int end = MonthEnd.getActualMaximum(Calendar.DAY_OF_MONTH);
		return end;
	}
	
	/**
	 * 返回某年某月的天数
	 * @example1 lastDayOfDate(2016, 07)->31
	 * @example2 lastDayOfDate(2016, 02)->29
	 * @param  year  {传入int类型的某年}
	 * @param  month {传入int类型的某月}
	 * @return 以int类型返回某年月的天数 
	 */
	public static int lastDayOfDate(int year, int month) {
		Calendar MonthEnd = Calendar.getInstance();
		MonthEnd.clear();
		MonthEnd.set(Calendar.YEAR, year);
		MonthEnd.set(Calendar.MONTH, month - 1);
		int end = MonthEnd.getActualMaximum(Calendar.DAY_OF_MONTH);
		return end;
	}
	
	/**
	 * 将Object类型的时间格式化HH时MM分SS秒
	 * @example1 intToSFM(193356)->19时33分56秒
	 * @example2 intToSFM(3356)->0时33分56秒
	 * @param    date {传入Object类型的时间}
	 * @return   以String类型返回HH时MM分SS秒
	 */
	public static  String intToSFM(Object date){
		String formatSFM = "";
		if(date == null){
			return formatSFM;
		}
		String dateStr = date.toString();
		if(dateStr.length() == 6){
			formatSFM = dateStr.substring(0, 2) + "时" + dateStr.substring(2, 4) + "分" + dateStr.substring(4, 6) + "秒";
		}else if(dateStr.length() == 5){
			formatSFM = dateStr.substring(0, 1) + "时" + dateStr.substring(1, 3) + "分" + dateStr.substring(3, 5) + "秒";
		}else if(dateStr.length() == 4){
			formatSFM = "0时" + dateStr.substring(0, 2) + "分" + dateStr.substring(2, 4) + "秒";
		}else if(dateStr.length() == 3){
			formatSFM = "0时" + dateStr.substring(0, 1) + "分" + dateStr.substring(1, 3) + "秒";
		}else if(dateStr.length() == 2){
			formatSFM = "0时0分" + dateStr.substring(0, 2) + "秒";
		}else{
			formatSFM = "0时0分" + dateStr.substring(0, 1) + "秒";
		}
		
		return formatSFM;
	}
	
	/**
	 * 获取当前年份
	 * @example nowYear()->2016
	 * @return  以int类型返回当前年份
	 */
	public static int nowYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获取当前日期，格式化为"20160712"
	 * @example nowDate()->"20160712"
	 * @return  以String类型返回当前日期
	 */
	public static String nowDate(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "" + (month < 10 ? "0" + month : month )+ "" + (date < 10 ? "0" + date : date);
	}
	
	/**
	 * 获取当前日期，格式化为20160712
	 * @example nowDateInt()->20160712
	 * @return  以int类型返回当前日期
	 */
	public static int nowDateInt(){
		return Integer.parseInt(nowDate());
	}
	
	/**
	 * 获取当前日期，格式化为"20160713"
	 * @example nowDateStr()->"20160713"
	 * @return  以String类型返回当前日期
	 */
	public static String nowDateStr() {
		return String.valueOf(nowDateInt());
	}
	

	/**
	 * 获取当前日期的下个月15日的日期，格式为"20160715"
	 * @example nextMiddleDate()->"20160815"
	 * @return  以String类型返回
	 */
	public static String nextMiddleDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 2;
		if (month > 12) {
			year = year + 1;
			month = 1;
		}
		int date = 15;
		return year + "" + (month < 10 ? "0" + month : month) + "" + date;
	}
	
	/**
	 * 获取当前日期的下个月15日的日期，格式为20160715
	 * @example nextMiddleDate()->20160815
	 * @return  以int类型返回
	 */
	public static int nextMiddleDateInt() {
		return Integer.parseInt(nextMiddleDate());
	}
	
	/**
	 * 获取当前时分秒，格式化为"195154"
	 * @example nowTime()->"195154"
	 * @return  以String类型返回
	 */
	public static String nowTime() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		return (hour < 10 ? "0" + hour : hour) + ""
				+ (minute < 10 ? "0" + minute : minute) + ""
				+ (sec < 10 ? "0" + sec : sec);
	}
	
	/**
	 * 获取当前时分秒，格式化为195154
	 * @example nowTime()->195154
	 * @return  以int类型返回
	 */
	public static int nowTimeInt(){
		return Integer.parseInt(nowTime());
	}
	
	/**
	 * 获取当前时间，格式为HHmmss
	 * @example nowTimeStr()->"092734"
	 * @return  以String类型返回
	 */
	public static String nowTimeStr() {
		return nowTimeInt() + "";
	}
	
	/**
	 * 获取当前年月日,时分秒,并分别存放在StringBuffer ymd,hms变量中
	 * @example nowTime("20160712","204455")-> StringBuffer ymd ="20160712" ,StringBuffer hms="204455"
	 * @param   ymd {传入StringBuffer类型的年月日}
	 * @param   hms {传入StringBuffer类型的时分秒}
	 * @return  返回void
	 */
	public static void GetYmdHms(StringBuffer ymd, StringBuffer hms) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		String tmp = "";
		tmp = String.format("%04d%02d%02d", year, month, date);
		ymd.append(tmp);

		tmp = String.format("%02d%02d%02d", hour, minute, sec);
		hms.append(tmp);

		return;
	}
	
	/**
	 * 计算日期差
	 * @example1 dateSubtract(20160713,20160701)->12
	 * @example2 dateSubtract(20160713,20160630)->13
	 * @param    sdate {传入int类型的被减数}
	 * @param    mdate {传入int类型的减数}
	 * @return   以int类型值返回两日期差
	 */
	public static int dateSubtract(int sdate, int mdate) {
		int dateBetween = 0;
		if (sdate == 0 || mdate == 0)
			return 10000;

		int year = 0, month = 0, day = 0;
		year = sdate / 10000;
		month = sdate % 10000 / 100;
		day = sdate % 100;

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		long stime = cal.getTimeInMillis();

		year = mdate / 10000;
		month = mdate % 10000 / 100;
		day = mdate % 100;

		cal.set(year, month - 1, day);
		long maxtime = cal.getTimeInMillis();

		dateBetween = strToInt(objToStr((maxtime - stime)/ (1000 * 3600 * 24)));

		return dateBetween;
	}
	
	/**
	 * 获取当前年月的下一个年月,addflag = 1 下个月， 0 上个月
	 * @example1 GetLastMonth(201607,1)->201608
	 * @example2 GetLastMonth(201601,0)->201512
	 * @param    ym	     {传入int类型的被减数}
	 * @param    addflag {传入int类型的减数}
	 * @return 	   以int类型值返回当前年月的下一个年月
	 */
	public static int GetLastMonth(int ym, int addflag) {
		int year = ym / 100;
		int mon = ym % 100;

		if (addflag == 1) {
			if (mon >= 12) {
				mon = 1;
				year++;
			} else
				mon++;

		} else {
			if (mon <= 1) {
				mon = 1;
				year--;
			} else
				mon--;
		}
		return year * 100 + mon;
	}
	

	/**
	 * 获取当前月份--int
	 * @example nowMonthInt()->07
	 * @return 以int类型返回当前月份
	 * */
	public static int nowMonthInt() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取当前月份--String
	 * @example nowMonthInt()->"07"
	 * @return  以String类型返回当前月份
	 * */
	public static String nowMonth() {
		int month = nowMonthInt();
		String month_str = month < 10 ? "0" + month : month + "";
		return month_str;
	}
	
	/**
	 * 获取当前日期为周几   1=周日，2=周一...7=周六
	 * @example nowWeekDay()->4 (周三)
	 * @return  以int类型返回当前日期为周几
	 * */
	public static int nowWeekDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}
		
	/**
	 * 将数据库存储的日期时间转换为Calendar
	 * @example ymdhmsToCalendar(20160713,093923)->Calendar对象
	 * @param   ymd {传入int型的年月日}
	 * @param   hms {传入int型的时分秒}
	 * @return  以Calendar对象返回日期
	 */
	public static Calendar ymdhmsToCalendar(int ymd, int hms) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, ymd / 10000);
		c.set(Calendar.MONTH, ymd % 10000 / 100 - 1);
		c.set(Calendar.DATE, ymd % 10000 % 100);

		if (hms > 0) {
			c.set(Calendar.HOUR_OF_DAY, hms / 10000);
			c.set(Calendar.MINUTE, hms % 10000 / 100);
			c.set(Calendar.SECOND, hms % 10000 % 100);
		}

		return c;
	}
	
	/**
	 * 计算两个日期之间的差值--精确到毫秒
	 * */
	public static long diffDate(Calendar c1, Calendar c2) {
		return Math.abs(c2.getTimeInMillis() - c1.getTimeInMillis());
	}
	
	/**
	 * yyyyMMdd(20160118)根据byte值数据转换为'yyyy年MM月dd日','yyyy-MM-dd'等形式
	 * @example1 intToFormatDate(20160713,1)->2016年07月13日
	 * @example2 intToFormatDate(20160713,2)->2016-07-13
	 * @param    ymd  {传入待转换的int型年月日}
	 * @param    type {传入byte型的数据转换类型}
	 * @return   以String型返回'yyyy年MM月dd日','yyyy-MM-dd'等形式
	 */
	public static String intToFormatDate(int ymd, byte type) {
		if (ymd == 0 || ymd < 20080808)
			return "";
		int year = ymd / 10000;
		int month = ymd % 10000 / 100;
		int day = ymd % 10000 % 100;

		String month_str = month < 10 ? "0" + month : String.valueOf(month);
		String day_str = day < 10 ? "0" + day : String.valueOf(day);

		String formatType = "";

		switch (type) {
		case 1:
			formatType = year + "年" + month_str + "月" + day_str + "日";
			break;
		case 2:
			formatType = year + "-" + month_str + "-" + day_str;
			break;
		default:
			formatType = year + "/" + month_str + "/" + day_str;
			break;
		}
		return formatType;
	}
	
	/**
	 * HHmmss(194350)根据byte值转换为'hh时mm分ss秒','hh:mm:ss'时间格式
	 * @example1 intToFormatTime(100202,1)->10时02分02秒
	 * @example2 intToFormatTime(100354,2)->10:03:54
	 * @param    hms  {传入待转换的int型时分秒}
	 * @param    type {传入byte型的数据转换类型}
	 * @return   以String型返回'hh时mm分ss秒','hh:mm:ss'时间格式
	 */
	public static String intToFormatTime(int hms, byte type){
		if (hms == 0 || hms < 10000)
			return "";
		int hh = hms / 10000;
		int mm = hms % 10000 / 100;
		int ss = hms % 10000 % 100;

		String hh_str = hh < 10 ? "0" + hh : hh + "";
		String mm_str = mm < 10 ? "0" + mm : mm + "";
		String ss_str = ss < 10 ? "0" + ss : ss + "";

		String formatType = "";

		switch (type) {
		case 1:
			formatType = hh_str + "时" + mm_str + "分" + ss_str + "秒";
			break;
		case 2:
			formatType = hh_str + ":" + mm_str + ":" + ss_str;
			break;
		default:
			formatType = hh_str + "时" + mm_str + "分" + ss_str + "秒";
			break;
		}
		return formatType;
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss转换为Date
	 * @example1 timeStampToDate(2016-07-13 10:22:55)->Date对象
	 * @example2 timeStampToDate(2016/07/13 10:27:33)->Date对象
	 * @param    timeStr {传入String类型的时间}
	 * @return   以Date型返回时间
	 */
	public static Date timeStampToDate(String timeStr) {
		if (timeStr == null || timeStr.isEmpty()) {
			return null;
		}

		byte type = 0;
		if (timeStr.contains("-")) {
			type = 1;
		} else if (timeStr.contains("/")) {
			type = 2;
		}

		Date date = new Date();
		DateFormat fdate = null;

		switch (type) {
		case 1:
			fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 2:
			fdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			break;
		default:
			fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		}

		try {
			date = fdate.parse(timeStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss转换为Calendar
	 * @example1 timeStampToCalendar(2016-07-13 10:22:55)->Calendar对象
	 * @example2 timeStampToCalendar(2016/07/13 10:27:33)->Calendar对象
	 * @param    timeStr {传入String类型的时间}
	 * @return   以Calendar型返回时间
	 */
	public static Calendar timeStampToCalendar(String timeStr) {
		Date date = timeStampToDate(timeStr);
		if (date == null) {
			return null;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c;
		}
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss转换为yyyyMMddHHmmss
	 * @example1 timeStampToLong(2016-07-13 10:22:55)->20160713102255
	 * @example2 timeStampToLong(2016/07/13 10:27:33)->20160713102733
	 * @param    timeStr {传入String类型的时间}
	 * @return   以Calendar型返回yyyyMMddHHmmss
	 */
	public static long timeStampToLong(String timeStr) {
		Calendar c = timeStampToCalendar(timeStr);
		if (c == null) {
			return 0;
		} else {
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int min = c.get(Calendar.MINUTE);
			int sec = c.get(Calendar.SECOND);

			return year * 10000000000l + month * 100000000 + day * 1000000
					+ hour * 10000 + min * 100 + sec;
		}
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss转换为yyyyMMdd
	 * @example1 timeStampToYMD(2016-07-13 10:22:55)->20160713102255
	 * @example2 timeStampToYMD(2016/07/13 10:27:33)->20160713102733
	 * @param    timeStr {传入String类型的时间}
	 * @return   以int型返回yyyyMMdd
	 */
	public static int timeStampToYMD(String timeStr) {
		Calendar c = timeStampToCalendar(timeStr);
		if (c == null) {
			return 0;
		} else {
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);

			return year * 10000 + month * 100 + day;
		}
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss转换为HHmmss  
	 * @example1 timeStampToYMD(2016-07-13 10:22:55)->102255
	 * @example2 timeStampToYMD(2016/07/13 10:27:33)->102733
	 * @param    timeStr {传入String类型的时间}
	 * @return   以int型返回HHmmss
	 */
	public static int timeStampToHMS(String timeStr){
		Calendar c = timeStampToCalendar(timeStr);
		if(c == null){
			return 0;
		}
		else{
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int min = c.get(Calendar.MINUTE);
			int sec = c.get(Calendar.SECOND);
			
			return hour*10000 + min*100 + sec; 
		} 
	}
	
	/**
	 * 将yyyyMMddHHmmss(String类型)转换为yyyyMMdd(String类型)
	 * @example1 stringTimeToYMD(20160713102255)->"20160713"
	 * @example2 stringTimeToYMD(2016071310:27:33)->"0"
	 * @param    timeStr {传入String类型的时间}
	 * @return   以String类型返回yyyyMMdd
	 * */
	public static String stringTimeToYMD(String timeStr) {
		if (timeStr == null || timeStr.isEmpty() || timeStr.length() != 14) {
			return "0";
		} else {
			return timeStr.substring(0, 8);
		}
	}
	
	/**
	 * yyyyMMddHHmmss(String类型)--->HHmmss(String)
	 * @example1 stringTimeToHMS(20160713102255)->"102255"
	 * @example2 stringTimeToHMS(2016071310:27:33)->"0"
	 * @param    timeStr {传入String类型的时间}
	 * @return   以String型返回HHmmss
	 * */
	public static String stringTimeToHMS(String timeStr) {
		if (timeStr == null || timeStr.isEmpty() || timeStr.length() != 14) {
			return "0";
		} else {
			return timeStr.substring(8, timeStr.length());
		}
	}
	
	
	/**
	 *获取该年月份--前n/后n几个月月份，多用于月表范围的查询定位(包含本月)
	 *@example1 getYMArray(201607,-5)->201602
	 *@example2 getYMArray(201607,5)->201612
	 *@param    ym 		   {传入int型的某年月           201604(年月)}
	 *@param    difference {传入int型的月份范围      -5(前5个月)/5(后5个月)}
	 *@return 	返回int[]:按月份由小到大顺序排列
	 * */
	public static int[] getYMArray(int ym, int difference){
		int num = Math.abs(difference);
		int[] data = new int[num];
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, ym/100);
		c.set(Calendar.MONTH, ym%100-1);
		
		//返回值顺序都是按照小到大顺序排列
		for(int i=0; i<num; i++){
			Calendar temp = Calendar.getInstance();
			temp.set(Calendar.YEAR, c.get(Calendar.YEAR));
			
			if(difference > 0){
				temp.set(Calendar.MONTH, c.get(Calendar.MONTH) + i);
			}
			else{
				temp.set(Calendar.MONTH, c.get(Calendar.MONTH) + i + (difference + 1));
			}
			
			data[i] = temp.get(Calendar.YEAR) * 100 + temp.get(Calendar.MONTH) + 1;
		}
		
		return data;
	}	
	/**-------------------------日期转换方法------结束---------------------------*/
	
	
	
	
	
	/**-------------------------获取系统路径------开始---------------------------*/
	
	/**
	 * 获取工程根路径
	 */
	public static String loadRealPath(HttpServletRequest request){
		String realpath = request.getContextPath();
		return realpath;
	}
	  
	/**
	 * 获取服务路径
	 */
	public static String loadServerPath(HttpServletRequest request){
		StringBuffer serverPath = new StringBuffer();
		serverPath.append(request.getScheme()).append("://")
		  		  .append(request.getServerName()).append(":").append(request.getServerPort());
		return serverPath.toString();
	}
	 
	/**
	 * 获取工程根目录路径url
	 */
	public static String loadFullPath(HttpServletRequest request){
		return  loadServerPath(request) + loadRealPath(request) + "/";
	}
	  
	/**
	 * 加载文件完整路径url
	 */
	public static String setFullUrl(HttpServletRequest request, String url){
		if(url == null || url.isEmpty()) return Constant.EMPTY;
		StringBuffer rootUrl = new StringBuffer(loadFullPath(request));
		String fullUrl = rootUrl.append(url).toString();
		return fullUrl;
	}
	  
	/**
	 * 获取工程在服务器中的路径
	 */
	public static String loadLocalRoot(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**-------------------------获取系统路径------结束----------------------------*/
	
	
	/**
	 * 将数字封装成大写汉字
	 * @example1 Object2Capital(99.09)-> 玖拾玖元零玖分
	 * @example2 Object2Capital(1000) -> 壹仟元
	 * @param    ob 待转换的数值                          Object类型
	 * @return   返回数字转换成汉字的字符串值 
	 * **/
	public static String Object2Capital (Object ob){
		  String s=new DecimalFormat("#.00").format(ob);
		  s=s.replaceAll("\\.", "");//将字符串中的"."去掉  拾佰仟万亿
		  char d[]={ '零', '壹', '贰', '叁', '肆', 
		    '伍', '陆', '柒', '捌', '玖'}; 
		  String unit="仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
		  int c=unit.length();
		  StringBuffer sb=new StringBuffer(unit);
		  for(int i=s.length()-1;i>=0;i--){
		   sb.insert(c-s.length()+i,d[s.charAt(i)-0x30]);
		  }
		  s=sb.substring(c-s.length(),c+s.length());
		  s=s.replaceAll("零[仟佰拾]", "零").
		  replaceAll("零{2,}", "零"). 
		  replaceAll("零([兆万元])", "$1"). 
		  replaceAll("零[角分]", ""); 
		  return s; 
		 }
	
	/**
	 * 将Double类型值保留N位小数
	 * @example1 FormatDouble(23,2) -> 23.00
	 * @example2 FormatDouble(301,3) -> 301.000
	 * @param    obj 待转换的数值          Object类型
	 * @param    scale 保留精度的位数  int类型
	 * @return   保留N位小数的字符串值 
	 * 
	 * **/
	public static String FormatDouble(Object obj, int scale) {
		double bddl = objToDbl(obj);
		String format = scale == 0 ? "##0" : "##0.";
		for (int i = 0; i < scale; i++) {
			format += "0";
		}

		DecimalFormat myformat = new DecimalFormat(format);
		return myformat.format(bddl);
	}	
	
	/**
	 * 根据错误码将错误信息以json格式返回
	 * @param errorCode
	 * @return {@link JSONObject}
	 * */
	public static JSONObject errorInfo(String errorCode,String errorInfo) {
		JSONObject json = new JSONObject();
		json.put("status", errorCode);
		json.put("error", errorInfo);
//		json.put("error", WebConfig.errorMap.get(errorCode));
		
		return json;
	}
	
	public static String splitToken(String token){
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < token.length();i=i+4){
			sb.append(token.substring(i, i+4));
			if (i < token.length()-4) sb.append(" ");			
		}			
		 
		return sb.toString();		
	}
	
	 /**
     * 	byte[]转成二进制--再转成十进制数  
     */
	public static String conver2HexStr(byte b){
		return Long.toString(b & 0xff, 2);
	}
	
	//补成8位
	public static String formatBinaryStrTo8(String binaryStr){
		int size = binaryStr.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0, num=8-size; i<num; i++){
			sb.append("0");
		}
		
		return sb.toString() + binaryStr;
	}
	
	public static List<Integer> parseByteArray(byte[] array) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0, nums = array.length; i < nums; i++) {
			sb.append(formatBinaryStrTo8(conver2HexStr(array[i])));
		}

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0, nums = sb.length(); i < nums; i++) {
			if (1 == Integer.parseInt(String.valueOf(sb.charAt(i)))) {
				list.add(nums - i);
			}
		}

		return list;
	}
	
	/** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
	* <p>
	* <p>NAME        : FormatAdd0
	* <p>
	* <p>DESCRIPTION : 格式化数值to字符,前面补零
	* <p>			        
	* <p>COMPLETION
	* <p>INPUT       : object obj 对象  int len 最后长度
	* <p>RETURN      : Double
	* <p>
	*-----------------------------------------------------------*/        
	public static String FormatAdd0(Object obj, int len) {
		if (obj == null)
			return "";

		String ret = obj.toString();
		while (ret.length() < len) {
			ret = "0" + ret;
		}

		return ret;
	}
	
	public static LoginUser getLoginUserByToken(String token){
		LoginUser loginuser = null;
		try {
			CacheBean cache = CacheManager.getCacheInfo(token);
			if (cache != null) {
				loginuser = cache.getLoginUser();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return loginuser;
	}
	
	public static String Josn2Str(JSONObject json){
		ObjectMapper objMapper = new ObjectMapper();
		String rtnStr ="";
		try {
			rtnStr = objMapper.writeValueAsString(json);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rtnStr;
	}
	/**
	 * 判断终端是否在线 在线返回true 不在线返回false
	 * */
	public static boolean rtuOnlineFlag(int rtuId) {
		boolean rtnFlag = false;
		OnlineRtu.RTUSTATE_ITEM[] rtustate_items = OnlineRtu.getRtuState();
		try {
			for (int j = 0; j < rtustate_items.length; j++) {

				if (rtuId == rtustate_items[j].rtu_id) { // /找到

					if (rtustate_items[j].comm_state == 1) {
						rtnFlag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rtnFlag;
	}
    
	/**
	 * 验证流水号
	 * */
	public static boolean checkWasteno(String wasteno) {
		if (null == wasteno || wasteno.length() > Constant.PAY_SERIALNUMBER_LENGTH) {
			return false;
		}
 		
		wasteno = CommFunc.objToStr(CommFunc.objToLong(wasteno));
		if (wasteno.length() != Constant.CHARGE_SERIALNUMBER_LENGTH) {
			return false;
		}
		
		String prefix = wasteno.substring(Constant.ZERO,Constant.CHARGE_SERIALNUMBER_PREFIX);
		if (!WebConfig.prefixList.contains(prefix)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", objToLong((code)));
        return result;
    }
}
