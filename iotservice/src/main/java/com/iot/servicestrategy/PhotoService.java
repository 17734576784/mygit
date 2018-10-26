/**
 * 
 */
package com.iot.servicestrategy;

import static com.iot.utils.ConverterUtils.toInt;
import static com.iot.utils.ConverterUtils.toStr;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.DateUtils;
import com.iot.utils.FileUtils;
import com.iot.utils.JedisUtils;
import com.iot.utils.Log4jUtils;

/**   
 * @ClassName:  PhotoService   
 * @Description:主动上报照片解析服务   
 * @author: dbr 
 * @date:   2018年10月22日 下午1:55:11   
 *      
 */
@Component
public class PhotoService implements IServiceStrategy {

	@Autowired
	private JedisUtils jedisUtils;
	
	@Value("${website.baseurl}")
	private String baseUrl;
	
	/* (non-Javadoc)
	 * @see com.iot.strategy.IServiceStrategy#parse(java.lang.String, java.util.Map)
	 */
	@Override
	public void parse(String deviceId, Map<String, String> serviceMap) {
		String photo = "";
		byte[] photoByte = null;
		try {
			// 当前包号
			int packnum = toInt(serviceMap.get("packnum"));
			// 消息总包数
			int totalpack = toInt(serviceMap.get("totalpack"));
			// 照片数据
			photo = toStr(serviceMap.get("rawdata"));
			photoByte = CommFunc.decode(photo);
			System.out.println(" packnum : " + packnum + "  totalpack : " + totalpack + " deviceId :/" + deviceId);

			Log4jUtils.getError().info(" packnum : " + packnum + "  totalpack : " + totalpack + " deviceId :/" + deviceId);

			if (jedisUtils.hasKey(deviceId)) {
				JSONObject photoJson = new JSONObject();
				// 获取该设备之前获取的数据
				photoJson = (JSONObject) jedisUtils.get(deviceId);
				// 获取该设备之前获取的照片数据
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, byte[]> photoMap = (LinkedHashMap<String, byte[]>) photoJson.get("data");
				if (totalpack == photoMap.size()) {
					photoMap.put(toStr(packnum), photoByte);
					photoJson.put("packnum", packnum);
					long expireTime = jedisUtils.getExpire(deviceId);
					jedisUtils.set(deviceId, photoJson, expireTime);
					if (generateImage(photoMap, deviceId)) {
						jedisUtils.del(deviceId);
					}
				} else {
					jedisUtils.del(deviceId);
					insertDevicePhoto(deviceId, totalpack, packnum, photoByte);
				}
			} else {
				/** 不存在 该包存入redis */
				insertDevicePhoto(deviceId, totalpack, packnum, photoByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean generateImage(LinkedHashMap<String, byte[]> photoMap, String deviceId) {

		try {
			String filePath = "d://" + deviceId + "_" + LocalDateTime.now().getNano() + ".jpeg";
			byte[] tmp = new byte[0];
			Iterator<Entry<String, byte[]>> iterator = photoMap.entrySet().iterator();

			while (iterator.hasNext()) {
				Entry<String, byte[]> entry = iterator.next();
				byte[] photoByte = entry.getValue();
				if (photoByte.length != 0) {
					System.out.println(entry.getKey() + "    " + photoByte.length);
					tmp = CommFunc.byteMerger(tmp, photoByte);
				} else {
					return false;
				}
			}
			CommFunc.byte2image(tmp, filePath);
			String url = baseUrl + Constant.UPLOAD_IMAGE_URL;
			FileUtils.upload(url, filePath, DateUtils.curDate(), deviceId,DateUtils.curTime());
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
	private void insertDevicePhoto(String deviceId, int totalpack, int packnum, byte[] photoByte) {
		LinkedHashMap<String, byte[]> photoMap = new LinkedHashMap<String, byte[]>(totalpack);
		for (int i = 0; i < totalpack; i++) {
			photoMap.put(toStr((i + 1)), new byte[0]);
		}
		photoMap.put(toStr(packnum), photoByte);
		JSONObject photoJson = new JSONObject();
		photoJson.put("packnum", packnum);
		photoJson.put("data", photoMap);
		jedisUtils.set(deviceId, photoJson, 60 * 60 * 2);
	}

}
