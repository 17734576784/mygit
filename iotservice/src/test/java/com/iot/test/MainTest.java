package com.iot.test;

import static com.iot.utils.BytesUtils.getBytesReserve;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;import javax.print.attribute.standard.Chromaticity;

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
import com.iot.utils.NumberUtils;

public class MainTest {

	/**
     * 10进制转bcd
     * @param str 10进制数字 String.valueOf(int number);将10进制数字转成字符串传入此参数
     * @return bcd码
     */
     public static String DecimaltoBcd(String str){     
            String b_num="";
            for (int i = 0; i < str.length(); i++) {

                String b = Integer.toBinaryString(Integer.parseInt(str.valueOf(str.charAt(i))));

                int b_len=4-b.length();

                for(int j=0;j<b_len;j++){
                    b="0"+b;                
                }
                b_num+=b;
            }

            return b_num;
        }

     /**
 	 * @功能: BCD码转为10进制串(阿拉伯数据)
 	 * @参数: BCD码
 	 * @结果: 10进制串
 	 */
 	public static String bcd2Str(byte[] bytes) {
 		StringBuffer temp = new StringBuffer(bytes.length * 2);
 		for (int i = 0; i < bytes.length; i++) {
 			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
 			temp.append((byte) (bytes[i] & 0x0f));
 		}
 		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
 				.toString().substring(1) : temp.toString();
 	}
  
 	/**
 	 * @功能: 10进制串转为BCD码
 	 * @参数: 10进制串
 	 * @结果: BCD码
 	 */
 	public static byte[] str2Bcd(String asc) {
 		int len = asc.length();
 		int mod = len % 2;
 		if (mod != 0) {
 			asc = "0" + asc;
 			len = asc.length();
 		}
 		byte abt[] = new byte[len];
 		if (len >= 2) {
 			len = len / 2;
 		}
 		byte bbt[] = new byte[len];
 		abt = asc.getBytes();
 		int j, k;
 		for (int p = 0; p < asc.length() / 2; p++) {
 			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
 				j = abt[2 * p] - '0';
 			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
 				j = abt[2 * p] - 'a' + 0x0a;
 			} else {
 				j = abt[2 * p] - 'A' + 0x0a;
 			}
 			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
 				k = abt[2 * p + 1] - '0';
 			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
 				k = abt[2 * p + 1] - 'a' + 0x0a;
 			} else {
 				k = abt[2 * p + 1] - 'A' + 0x0a;
 			}
 			int a = (j << 4) + k;
 			byte b = (byte) a;
 			bbt[p] = b;
 		}
 		return bbt;
 	}

 	public static void dateformate(String time) {
 		//此方法是将2017-11-18T07:12:06.615Z格式的时间转化为秒为单位的Long类型。
// 		time = "2017-11-30T10:41:44.651Z";
 		time = time.replace("Z", " UTC");//UTC是本地时间
 		SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd'T'HHmmss Z");
 		Date d = null;
 		try {
 		d = format.parse(time);
 		} catch (ParseException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 		}
 		//此处是将date类型装换为字符串类型，比如：Sat Nov 18 15:12:06 CST 2017转换为2017-11-18 15:12:06
 		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HHmmss");
 		String date = sf.format(d);
 		System.out.println(d);//这里输出的是date类型的时间
 		System.out.println(d.getTime()/1000);//这里输出的是以秒为单位的long类型的时间。如果需要一毫秒为单位，可以不用除1000.
 		        System.out.println(sf.format(d));//这里输出的是字符串类型的时间
 	}

	public static void main(String[] args) throws ClientProtocolException, IOException { 
		String apiUrl = "http://127.0.0.1:11028/Enterprise_EnnGas/enngas/message/" + Constant.UPLOAD_UPGRADERESULT_URL;
		try {
			Map<String, Object> paramJson = new HashMap<>();
			paramJson.put("status", Constant.UPGRADE_FAILED);
			paramJson.put("version", "1");
			paramJson.put("deviceId", "434ff6cb-89bc-498b-a7c3-9f21f55afc96");

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("param", paramJson);
			JSONObject response = HttpsUtils.doPost(apiUrl, paramMap);
			if (response != null && !response.isEmpty()) {
				if (response.getInteger("status") == Constant.SUCCESS) {
					LoggerUtils.Logger(LogName.CALLBACK).info("434ff6cb-89bc-498b-a7c3-9f21f55afc96" + "升级失败发送成功");
				} else {
					LoggerUtils.Logger(LogName.CALLBACK).info("434ff6cb-89bc-498b-a7c3-9f21f55afc96" + "升级失败发送失败：");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.Logger(LogName.CALLBACK).error("下发升级版本回复，设备id：" + "434ff6cb-89bc-498b-a7c3-9f21f55afc96");
		}
		
//		String eventTime = "20181129T124539Z";
//		String date11 = eventTime.substring(0, 8); 
//		String time11 = eventTime.substring(9, 15);
//		System.out.println(date11 + "  " + time11);
////		dateformate(eventTime);
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		
//		JSONObject s = new JSONObject();
//		String dd =ConverterUtils.toStr(LocalDateTime.now());
//		s.put("a", ConverterUtils.toStr(LocalDateTime.now()));
//		
//		System.out.println(s.get("a"));
		
//		String date  = "20181124";
//		int dateInt = BytesUtils.getInt(str2Bcd(date));
//		System.out.println(dateInt);
//		System.out.println(bcd2Str(str2Bcd(date)));

//        int dateTen = ConverterUtils.toInt("538448166");
//        String dateHex = String.format("%08x",dateTen);
//        
//        System.out.println(dateHex);

//        String str = new BigInteger("133F086", 16).toString(10);

//        System.out.println(str);
		
//		String a = "AQBqAAYAAAEBQACRAL8AvwC/HUgAaED0gHAbSQhgAL8B8NT7YLsAvxZIkDAAaED0gDAUScH4kAAAvwC/CEbQ+JAAIPSAMMH4kAAAvwC/CEbQ+JAAQPABAMH4kAAAvwC/AfC0+wEo+9EAAgZJkDEJaCH0QHEBQwNKwviQEAC/AL8IvQAAABACQABwAEBwtQRGACYAJbT1gD8H00/2/3C0+/DxAPsRRgYlAeAmRgQlFEgB8AT8AL8SSIBoIPSAYBBJiGAAvwC/AL8NSMBowPOAAAAo+NAAvwpIQGlv8w8AMEMHSUhhAL8AvwhGgGgg8AcAKEOIYAC/CEYB8OX7cL0AAAAoAEAt6fBBBrE=";
//		String bb = "AQBqAAYAAAEBQACRAL8AvwC/HUgAaED0gHAbSQhgAL8B8NT7YLsAvxZIkDAAaED0gDAUScH4kAAAvwC/CEbQ+JAAIPSAMMH4kAAAvwC/CEbQ+JAAQPABAMH4kAAAvwC/AfC0+wEo+9EAAgZJkDEJaCH0QHEBQwNKwviQEAC/AL8IvQAAABACQABwAEBwtQRGACYAJbT1gD8H00/2/3C0+/DxAPsRRgYlAeAmRgQlFEgB8AT8AL8SSIBoIPSAYBBJiGAAvwC/AL8NSMBowPOAAAAo+NAAvwpIQGlv8w8AMEMHSUhhAL8AvwhGgGgg8AcAKEOIYAC/CEYB8OX7cL0AAAAoAEAt6fBBBrE=";
//		System.out.println(a.equals(bb));
//		byte[] base64 = CommFunc.decode(a);
//		for (byte b : base64) {
//			System.out.print(BytesUtils.byteToHex(b) + " ");
//		}
//		System.out.println();
//		
//		LocalDate date = LocalDate.of(2016, 11, 29);
		
//		System.out.println(date.getYear());
//		System.out.println(date.getMonth());
//		System.out.println(date.getDayOfMonth());
//		System.out.println(date.getDayOfWeek());
//		System.out.println(date.lengthOfMonth());
//		System.out.println(date.isLeapYear());
		
		LocalDate today = LocalDate.now();
//		System.out.println(today);
		
//		System.out.println(date.get(ChronoField.YEAR));
//		System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
//		System.out.println(date.get(ChronoField.DAY_OF_MONTH));
		
		LocalTime time = LocalTime.of(18, 55, 45);
//		System.out.println(time.getHour());
//		System.out.println(time.getMinute());
//		System.out.println(time.getSecond());
		
//		date = LocalDate.parse("2015-10-25");
//		time = LocalTime.parse("08:57:03");
//		
//		LocalDateTime dt1 = LocalDateTime.of(2018, Month.NOVEMBER, 30, 8, 28,45);
//		LocalDateTime dt2 = LocalDateTime.of(date, time);
//		LocalDateTime dt3 = date.atTime(13, 45);
//		LocalDateTime dt4 = date.atTime(time);
//		LocalDateTime dt5 = time.atDate(date);
		
//		System.out.println(dt1);
//		System.out.println(dt2);
//		System.out.println(dt3);
//		System.out.println(dt4);
//		System.out.println(dt5);

		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy年MM月dd日 ");
		LocalDate date1 = LocalDate.now();
//		System.out.println(date1.format(df));

//		Duration duration = Duration.between(dt1, LocalDateTime.now());
//		long timeDiff = duration.getSeconds();
//		System.out.println("timeDiff :" + timeDiff);
//
//		for (byte b : base64) {
//			System.out.print(b + " ");
//		}
		
		
		System.out.println();

//		long crc1 = getCRC(base64);
//		System.out.println(crc1);
		
		System.out.println();
		
//		System.out.println(CommFunc.encode(base64));
//
//		byte[] test = new byte[] { 1, 2, 3, 4, 6 };
//		System.out.println(CommFunc.encode(test));
//		JSONObject jsontest = new JSONObject();
//		jsontest.put("a", test);
//		
//		test = (byte[]) jsontest.get("a");
//		for (byte b : test) {
//			System.out.print(BytesUtils.byteToHex(b) + " ");
//		}
		
 			
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
		
//		
//		
//		String base =  "http://222.222.60.178:18130/Enterprise_EnnGas/enngas/message/";
//		String apiUrl = base + Constant.UPLOAD_ALARMCOMMAND_URL;
//		Map<String, Object> paramJson =  new HashMap<>();
//		paramJson.put("slope", 1);
//		paramJson.put("magnetic", 1);
//		paramJson.put("alarmtype", 1);
//		
//		paramJson.put("date", DateUtils.curDate());
//		paramJson.put("time", DateUtils.curTime());
//		paramJson.put("deviceId", "6e3dfa04-a0ef-4a8a-9475-f6b65605467e");
//		
//		apiUrl = base + "nbNotifyAction!notifyAlarm.action";
		
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

//		Map<String, Object> urlMap = new HashMap<>();
//		urlMap.put("param", paramJson.toString());
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
