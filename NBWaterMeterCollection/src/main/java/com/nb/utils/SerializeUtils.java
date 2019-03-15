/**   
* @Title: ObjectTranscoder.java 
* @Package com.icpms.redis 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年1月24日 下午1:32:44 
* @version V1.0   
*/
package com.nb.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;

/**
 * @ClassName: ObjectTranscoder
 * @Description: 序列号工具类
 * @author dbr
 * @date 2018年1月24日 下午1:32:44
 */
public class SerializeUtils {

	/**
	 * 通过私有构造器强化不可实例化的能力
	 */
	private SerializeUtils() {
		throw new AssertionError();
	}
	
	public static byte[] serialize(Object value) {
		if (value == null) {
			LoggerUtil.Logger(LogName.ERROR).error("不能序列化NULL");
			throw new NullPointerException("不能序列化NULL");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			LoggerUtil.Logger(LogName.ERROR).error("不能序列化对象", e);
			throw new IllegalArgumentException("不能序列化对象", e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return rv;
	}

	public static Object deserialize(byte[] in) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
				is.close();
				bis.close();
			}
		} catch (Exception e) {
			LoggerUtil.Logger(LogName.ERROR).error("反序列化异常", e);
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return rv;
	}
}
