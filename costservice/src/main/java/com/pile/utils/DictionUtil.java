package com.pile.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DictionUtil {
	
	@Autowired
	private JedisUtils jedisUtils;
	
	public static final String PREDICTON = "Diction:";
	
	/** 
	* @Title: getItemName 
	* @Description: 获取数据字典数据项名称
	* @param @param type  ->DictionDef.USEFLAG
	* @param @param value ->1
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getItemName(String type, int value) {

		Map<Object, Object> map = jedisUtils.hmget(PREDICTON + type);

		if (map == null || map.size() == 0){
			return null;
		}

		return ConverterUtils.toStr(map.get(value));
	}
	
		
	
}
