/**
 * 
 */
package com.iot.servicestrategy;

import static com.iot.utils.ConverterUtils.toInt;
import static com.iot.utils.ConverterUtils.toStr;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.FileUtils;
import com.iot.utils.JedisUtils;
import com.iot.utils.JsonUtil;

/**   
 * @ClassName:  PhotoService   
 * @Description:主动上报照片解析服务   
 * @author: dbr 
 * @date:   2018年10月22日 下午1:55:11   
 *      
 */
@Component
public class PhotoService implements IServiceStrategy {

	@Value("${website.baseurl}")
	private String baseUrl;
	
	@Value("${imageurl}")
	private String imageUrl;
	
	@Value("${photoExpireTime}")
	private int photoExpireTime;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String photo = "";
		byte[] photoByte = null;
		try {
			Object data = serviceMap.get("data");
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap = JsonUtil.jsonString2SimpleObj(data, dataMap.getClass());
			// 当前包号
			int packnum = toInt(dataMap.get("packnum"));
			// 消息总包数
			int totalpack = toInt(dataMap.get("totalpack"));
			// 照片数据
			photo = toStr(dataMap.get("rawdata"));
			photoByte = CommFunc.decode(photo);
			
			String packdate = new BigInteger(toStr(dataMap.get("packdate")), 16).toString(10);
			String packtime = new BigInteger(toStr(dataMap.get("packtime")), 16).toString(10);
			String time = packdate + packtime;

			String logInfo = " packnum : " + packnum + "  totalpack : " + totalpack + " deviceId :/" + deviceId
					+ "  time : " + time;
			LoggerUtils.Logger(LogName.CALLBACK).info(logInfo);
			System.out.println(logInfo);
			
			String devicePhotoKey = deviceId + "_" + time;
			
			if (JedisUtils.hasKey(devicePhotoKey)) {
				JSONObject photoJson = new JSONObject();
				// 获取该设备之前获取的数据
				photoJson = (JSONObject) JedisUtils.get(devicePhotoKey);
				// 获取该设备之前获取的照片数据
				LinkedHashMap<String, byte[]> photoMap = (LinkedHashMap<String, byte[]>) photoJson.get("data");
				if (totalpack == photoMap.size()) {
					photoMap.put(toStr(packnum), photoByte);
					photoJson.put("packnum", packnum);
					long expireTime = JedisUtils.getExpire(devicePhotoKey);
					JedisUtils.set(devicePhotoKey, photoJson, expireTime);
					if (generateImage(photoMap, deviceId,time)) {
						JedisUtils.del(devicePhotoKey);
					}
				} else {
					JedisUtils.del(devicePhotoKey);
					insertDevicePhoto(devicePhotoKey, totalpack, packnum, photoByte);
				}
			} else {
				/** 不存在 该包存入redis */
				insertDevicePhoto(devicePhotoKey, totalpack, packnum, photoByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean generateImage(LinkedHashMap<String, byte[]> photoMap, String deviceId, String time) {

		try {
			String photoDate =time.substring(0, 8);
			String photoTime = time.substring(8);
			String filePath = imageUrl + deviceId + "_" + LocalDateTime.now().getNano() + ".jpeg";
			byte[] tmp = new byte[0];
			Iterator<Entry<String, byte[]>> iterator = photoMap.entrySet().iterator();

			while (iterator.hasNext()) {
				Entry<String, byte[]> entry = iterator.next();
				byte[] photoByte = entry.getValue();
				if (photoByte.length != 0) {
					tmp = CommFunc.byteMerger(tmp, photoByte);
				} else {
					return false;
				}
			}
			CommFunc.byte2image(tmp, filePath);
			String url = baseUrl + Constant.UPLOAD_IMAGE_URL;
			FileUtils.upload(url, filePath, photoDate, deviceId,photoTime);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * 缓存中不存在，放入缓存
	 * 
	 * @param deviceId
	 * @param totalpack
	 * @param packnum
	 * @param photoByte
	 */
	private void insertDevicePhoto(String devicePhotoKey, int totalpack, int packnum, byte[] photoByte) {
		LinkedHashMap<String, byte[]> photoMap = new LinkedHashMap<String, byte[]>(totalpack);
		for (int i = 0; i < totalpack; i++) {
			photoMap.put(toStr((i + 1)), new byte[0]);
		}
		photoMap.put(toStr(packnum), photoByte);
		JSONObject photoJson = new JSONObject();
		photoJson.put("packnum", packnum);
		photoJson.put("data", photoMap);
		JedisUtils.set(devicePhotoKey, photoJson, photoExpireTime);
	}

}
