package com.iot.test;

import static com.iot.utils.BytesUtils.getBytesReserve;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.springframework.data.redis.util.ByteUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;
import com.iot.utils.BytesUtils;
import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.ConverterUtils;
import com.iot.utils.DateUtils;
import com.iot.utils.FileUtils;
import com.iot.utils.HttpsUtils;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException { 
		
//        String dateHex = String.format("%06x",1577237);
//        System.out.println(dateHex);
		
		String a = "AQBlAAEAAAEAAAAATwIACAAAAABPAgAIAAAAAAAAAABPAgAITwIACE8CAAhPAgAITwIACE8CAAhPAgAIAAAAAAAAAAAAAAAAgQcACE8CAAhPAgAIAAAAAE8CAAhPAgAITwIACE8CAAhPAgAITwIACE8CAAgAAAAATwIACE8CAAgAAAAAAAAAAE8CAAhPAgAITwIACADwAvgA8EH4CqCQ6AAMgkSDRKrxAQfaRQHRAPA2+K/yCQ666A8AE/ABDxi/+xpD8AEDGEc0YgAAVGIAABA6JL94yHjB+thSByS/MMgwwUS/BGgMYHBHAAAAIwAkACUAJhA6KL94wfvYUgcovzDBSL8LYHBHO6I=";
		System.out.println(a.length());
		byte[] base64 = CommFunc.decode(a);
		for (byte b : base64) {
			System.out.print(BytesUtils.byteToHex(b) + " ");
		}
		System.out.println();
		
		System.out.println(CommFunc.encode(base64));

		byte[] test = new byte[] { 1, 2, 3, 4, 6 };
		System.out.println(CommFunc.encode(test));
		JSONObject jsontest = new JSONObject();
		jsontest.put("a", test);
		
		test = (byte[]) jsontest.get("a");
		for (byte b : test) {
			System.out.print(BytesUtils.byteToHex(b) + " ");
		}
		
 			
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(0X01);
		dos.writeByte(0X01);
		dos.write(getBytesReserve((short) 12));
		dos.write(getBytesReserve((short) 2));
		dos.write(getBytesReserve((short) 25));

		byte[] data = new byte[25];
		dos.write(data);
		int crc = getCRC(baos.toByteArray());
		dos.writeShort(crc);
		byte[] tmp = baos.toByteArray();

//		for (byte b : tmp) {
//			System.out.print(b + " ");
//		}
		
		
//		System.out.println(BytesUtils.byteToHex((byte)((2000)&0XFF)));
//		System.out.println(BytesUtils.byteToHex((byte) ((2000 & 0xff00) >> 8)));

//		
//		
//		DataInputStream dis=new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
//		
//		System.out.println(dis.readByte());
//		System.out.println(dis.readShort());
//		System.out.println(dis.readByte());
//		System.out.println(dis.readByte());
//		System.out.println(dis.readShort());
//		System.out.println(dis.readShort());

		
//        int length = 0;
//		byte[] rstart = new byte[1];
//		System.arraycopy(tmp, 0, rstart, 0, rstart.length);
//		length += rstart.length;
//		System.out.println(BytesUtils.bytesToHex(rstart));
//		
//		byte[] rdataLen = new byte[2];
//		System.arraycopy(tmp, length, rdataLen, 0, rdataLen.length);
//		length += rdataLen.length;
//		short dataLenr = BytesUtils.getShort(rdataLen);
//		System.out.println(dataLenr);
//		
		
		
		
		String base =  "http://222.222.60.178:18130/Enterprise_EnnGas/enngas/message/";
		String apiUrl = base + Constant.UPLOAD_ALARMCOMMAND_URL;
		Map<String, Object> paramJson =  new HashMap<>();
		paramJson.put("slope", 1);
		paramJson.put("magnetic", 1);
		paramJson.put("alarmtype", 1);
		
		paramJson.put("date", DateUtils.curDate());
		paramJson.put("time", DateUtils.curTime());
		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
		
		apiUrl = base + "nbNotifyAction!notifyAlarm.action";
		
//		paramJson.put("cycle", 0);
//		paramJson.put("devicetime", 13);
//		paramJson.put("status", 0);
//		paramJson.put("date", DateUtils.curDate());
//		paramJson.put("time", DateUtils.curTime());
//		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
//		apiUrl = base + Constant.UPLOAD_TIMECOMMAND_URL;
		
//		paramJson.put("check", 0);
//		paramJson.put("date", DateUtils.curDate());
//		paramJson.put("time", DateUtils.curTime());
//		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
//		apiUrl = base + "nbNotifyAction!checkExistOrders.action";

		Map<String, Object> urlMap = new HashMap<>();
		urlMap.put("param", paramJson.toString());
		try {
//			JSONObject httpResult = HttpsUtils.doPost(apiUrl, urlMap);
//			System.out.println(httpResult);
//			if (httpResult != null && !httpResult.isEmpty()) {
//				if (httpResult.getInteger("status") == Constant.ERROR) {
//					LoggerUtils.Logger(LogName.INFO).info("推送设置告警命令回复失败，设备id：" );
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
//		String txt="{\"status\":0,\"error\":\"\"}";
//		
//		System.out.println(JSON.parseObject(txt));
	}
	
	/**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
//    public static String getCRC(byte[] bytes) {
//        int CRC = 0x0000ffff;
//        int POLYNOMIAL = 0x0000a001;
//        int i, j;
//        for (i = 0; i < bytes.length; i++) {
//            CRC ^= ((int) bytes[i] & 0x000000ff);
//            for (j = 0; j < 8; j++) {
//                if ((CRC & 0x00000001) != 0) {
//                    CRC >>= 1;
//                    CRC ^= POLYNOMIAL;
//                } else {
//                    CRC >>= 1;
//                }
//            }
//        }
//        return Integer.toHexString(CRC);
//    }


    /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static int getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }
    
    public int CRC(byte[] bytes) {
    	
    	
    	
    	return 2;
    }
    
    /**
     * 将16进制单精度浮点型转换为10进制浮点型
     *
     * @return float
     * @since 1.0
     */
    private float parseHex2Float(String hexStr) {
        BigInteger bigInteger = new BigInteger(hexStr, 16);
        return Float.intBitsToFloat(bigInteger.intValue());
    }

    /**
     * 将十进制浮点型转换为十六进制浮点型
     *
     * @return String
     * @since 1.0
     */
    private String parseFloat2Hex(float data) {
        return Integer.toHexString(Float.floatToIntBits(data));
    }
}
