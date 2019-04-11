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

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        // 设置FAIL_ON_EMPTY_BEANS属性，当序列化空对象不要抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 设置FAIL_ON_UNKNOWN_PROPERTIES属性，当JSON字符串中存在Java对象没有的属性，忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Convert Object to JsonString
     * 
     * @param jsonObj
     * @return
     */
    public static String jsonObj2Sting(Object jsonObj) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(jsonObj);
        } catch (IOException e) {
            System.out.printf("pasre json Object[{}] to string failed.",jsonString);
        }

        return jsonString;
    }

    /**
     * Convert JsonString to Simple Object
     * 
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T jsonString2SimpleObj(String jsonString, Class<T> cls) {
        T jsonObj = null;

        try {
            jsonObj = objectMapper.readValue(jsonString, cls);
        } catch (IOException e) {
        	System.out.printf("pasre json Object[{}] to string failed.",jsonString);
        }

        return jsonObj;
    }
    
    /**
     * Convert JsonString to Simple Object
     * 
     * @param jsonString
     * @param cls
     * @return
     */
	public static <T> T jsonString2SimpleObj(Object object, Class<T> cls) {
		T jsonObj = null;

		try {
			String jsonString = jsonObj2Sting(object);
			jsonObj = objectMapper.readValue(jsonString, cls);
		} catch (IOException e) {
			System.out.printf("pasre json Object[{}] to string failed.", object);
		}

		return jsonObj;
	}
   
    /**
     * Method that will convert object to the ObjectNode.
     *
     * @param value
     *            the source data; if null, will return null.
     * @return the ObjectNode data after converted.
     * @throws JsonException
     */
    public static <T> ObjectNode convertObject2ObjectNode(T object)
            throws Exception {
        if (null == object) {
            return null;
        }

        ObjectNode objectNode = null;

        if (object instanceof String) {
            objectNode = convertJsonStringToObject((String) object,
                    ObjectNode.class);
        } else {
            objectNode = convertValue(object, ObjectNode.class);
        }

        return objectNode;
    }
    
    /**
     * Method that will convert the json string to destination by the type(cls).
     * 
     * @param jsonString
     *            the source json string; if null, will return null.
     * @param cls
     *            the destination data type.
     * @return
     * @throws JsonException
     */
    public static <T> T convertJsonStringToObject(String jsonString,
            Class<T> cls) throws Exception {
        if (StringUtil.strIsNullOrEmpty(jsonString)) {
            return null;
        }

        try {
            T object = objectMapper.readValue(jsonString, cls);
            return object;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /**
     * Method that will convert from given value into instance of given value
     * type.
     * 
     * @param fromValue
     * @param toValueType
     * @return
     * @throws JsonException
     */
    private static <T> T convertValue(Object fromValue, Class<T> toValueType)
            throws Exception {
        try {
            return objectMapper.convertValue(fromValue, toValueType);
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }
    
	/**
	 * Map转换成Bean，使用泛型免去了类型转换的麻烦。
	 * @param        <T>
	 * @param map
	 * @param class1
	 * @return
	 */
	public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
		T bean = null;
		try {
			bean = class1.newInstance();
			BeanUtils.populate(bean, map);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	} 
	
	/** 
	* @Title: bean2Map 
	* @Description: Bean转换成Map，使用泛型免去了类型转换的麻烦。
	* @param @param t
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	public static Map<String, String> bean2Map(Object t) {
		Map<String, String> map = null;
		try {
			try {
				map = BeanUtils.describe(t);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return map;
	} 
}
