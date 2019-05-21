/**   
* @Title: BytesUtils.java 
* @Package com.iot.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月16日 下午4:51:23 
* @version V1.0   
*/
package com.nb.utils;

import java.nio.charset.Charset;
import org.apache.commons.lang.ArrayUtils;

/** 
* @ClassName: BytesUtils 
* @Description: 字节数组转换工具类
* @author dbr
* @date 2019年4月18日 下午4:52:01 
*  
*/
public class BytesUtils {

	public static final String GBK = "GBK";
	public static final String UTF8 = "utf-8";
	public static final char[] ASCII = "0123456789ABCDEF".toCharArray();
	private static char[] HEX_VOCABLE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 将short整型数值转换为字节数组
	 * 高位在前，低位在后
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) ((data & 0xff00) >> 8);
		bytes[1] = (byte) (data & 0xff);
		return bytes;
	}
	
	/**
	 * 将short整型数值转换为字节数组
	 * 低位在前，高位在后
	 * @param data
	 * @return
	 */
	public static byte[] getBytesReserve(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		return bytes;
	}

	/**
	 * 将字符转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(char data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data >> 8);
		bytes[1] = (byte) (data);
		return bytes;
	}

	/**
	 * 将byte转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(byte data) {
		byte[] bytes = new byte[1];
		bytes[0] = (byte) (data);
		return bytes;
	}
	
	/**
	 * 将布尔值转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(boolean data) {
		byte[] bytes = new byte[1];
		bytes[0] = (byte) (data ? 1 : 0);
		return bytes;
	}

	/**
	 * 将整型数值转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(int data) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) ((data & 0xff000000) >> 24);
		bytes[1] = (byte) ((data & 0xff0000) >> 16);
		bytes[2] = (byte) ((data & 0xff00) >> 8);
		bytes[3] = (byte) (data & 0xff);
		return bytes;
	}

	/**
	 * 将long整型数值转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(long data) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) ((data >> 56) & 0xff);
		bytes[1] = (byte) ((data >> 48) & 0xff);
		bytes[2] = (byte) ((data >> 40) & 0xff);
		bytes[3] = (byte) ((data >> 32) & 0xff);
		bytes[4] = (byte) ((data >> 24) & 0xff);
		bytes[5] = (byte) ((data >> 16) & 0xff);
		bytes[6] = (byte) ((data >> 8) & 0xff);
		bytes[7] = (byte) (data & 0xff);
		return bytes;
	}

	/**
	 * 将float型数值转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(float data) {
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	/**
	 * 将double型数值转换为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(double data) {
		long intBits = Double.doubleToLongBits(data);
		return getBytes(intBits);
	}

	/**
	 * 将字符串按照charsetName编码格式的字节数组
	 * 
	 * @param data        字符串
	 * @param charsetName 编码格式
	 * @return
	 */
	public static byte[] getBytes(String data, String charsetName) {
		Charset charset = Charset.forName(charsetName);
		return data.getBytes(charset);
	}

	/**
	 * 将字符串按照GBK编码格式的字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(String data) {
		return getBytes(data, GBK);
	}

	/**
	 * 将字节数组第0字节转换为布尔值
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean getBoolean(byte[] bytes) {
		return bytes[0] == 1;
	}

	/**
	 * 将字节数组的第index字节转换为布尔值
	 * 
	 * @param bytes
	 * @param index
	 * @return
	 */
	public static boolean getBoolean(byte[] bytes, int index) {
		return bytes[index] == 1;
	}

	/**
	 * 将字节数组前2字节转换为short整型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static short getShort(byte[] bytes) {
		return (short) ((0xff00 & (bytes[0] << 8)) | (0xff & bytes[1]));
	}
	
	/**
	 * 将字节数组前2字节转换为short整型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static short getReserveShort(byte[] bytes) {
		return (short) ((0xff00 & (bytes[1] << 8)) | (0xff & bytes[0]));
	}

	/**
	 * 将字节数组从startIndex开始的2个字节转换为short整型数值
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static short getShort(byte[] bytes, int startIndex) {
		return (short) ((0xff00 & (bytes[startIndex] << 8)) | (0xff & bytes[startIndex + 1]));
	}

	/**
	 * 将字节数组前2字节转换为字符
	 * 
	 * @param bytes
	 * @return
	 */
	public static char getChar(byte[] bytes) {
		return (char) ((0xff00 & (bytes[0] << 8)) | (0xff & bytes[1]));
	}
	
	/**
	 * 将字节数组前转换为byte
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte getByte(byte[] bytes) {
		return (byte) (0xff & bytes[0]);
	}

	/**
	 * 将字节数组从startIndex开始的2个字节转换为字符
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static char getChar(byte[] bytes, int startIndex) {
		return (char) ((0xff00 & (bytes[startIndex] << 8)) | (0xff & bytes[startIndex + 1]));
	}

	/**
	 * 将字节数组前4字节转换为整型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static int getInt(byte[] bytes) {
		return (0xff000000 & (bytes[0] << 24) | (0xff0000 & (bytes[1] << 16)) | (0xff00 & (bytes[2] << 8))
				| (0xff & bytes[3]));
	}
	
	/**
	 * 将字节数组前4字节转换为整型数值 低位在前 高位在后
	 * 
	 * @param bytes
	 * @return
	 */
	public static int getReserveInt(byte[] bytes) {
		return (0xff000000 & (bytes[3] << 24) | (0xff0000 & (bytes[2] << 16)) | (0xff00 & (bytes[1] << 8))
				| (0xff & bytes[0]));
	}
	
	/**
	 * 将字节数组从startIndex开始的4个字节转换为整型数值
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static int getInt(byte[] bytes, int startIndex) {
		return (0xff000000 & (bytes[startIndex] << 24) | (0xff0000 & (bytes[startIndex + 1] << 16))
				| (0xff00 & (bytes[startIndex + 2] << 8)) | (0xff & bytes[startIndex + 3]));
	}

	/**
	 * 将字节数组前8字节转换为long整型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static long getLong(byte[] bytes) {
		return (0xff00000000000000L & ((long) bytes[0] << 56) 
				| (0xff000000000000L & ((long) bytes[1] << 48))
				| (0xff0000000000L & ((long) bytes[2] << 40)) 
				| (0xff00000000L & ((long) bytes[3] << 32))
				| (0xff000000L & ((long) bytes[4] << 24)) 
				| (0xff0000L & ((long) bytes[5] << 16))
				| (0xff00L & ((long) bytes[6] << 8)) 
				| (0xffL & (long) bytes[7]));
	}

	/**
	 * 将字节数组从startIndex开始的8个字节转换为long整型数值
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static long getLong(byte[] bytes, int startIndex) {
		return (0xff00000000000000L & ((long) bytes[startIndex] << 56)
				| (0xff000000000000L & ((long) bytes[startIndex + 1] << 48))
				| (0xff0000000000L & ((long) bytes[startIndex + 2] << 40))
				| (0xff00000000L & ((long) bytes[startIndex + 3] << 32))
				| (0xff000000L & ((long) bytes[startIndex + 4] << 24))
				| (0xff0000L & ((long) bytes[startIndex + 5] << 16)) 
				| (0xff00L & ((long) bytes[startIndex + 6] << 8))
				| (0xffL & (long) bytes[startIndex + 7]));
	}

	/**
	 * 将字节数组前4字节转换为float型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static float getFloat(byte[] bytes) {
		return Float.intBitsToFloat(getInt(bytes));
	}

	/**
	 * 将字节数组从startIndex开始的4个字节转换为float型数值
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static float getFloat(byte[] bytes, int startIndex) {
		byte[] result = new byte[4];
		System.arraycopy(bytes, startIndex, result, 0, 4);
		return Float.intBitsToFloat(getInt(result));
	}

	/**
	 * 将字节数组前8字节转换为double型数值
	 * 
	 * @param bytes
	 * @return
	 */
	public static double getDouble(byte[] bytes) {
		long l = getLong(bytes);
		return Double.longBitsToDouble(l);
	}

	/**
	 * 将字节数组从startIndex开始的8个字节转换为double型数值
	 * 
	 * @param bytes
	 * @param startIndex
	 * @return
	 */
	public static double getDouble(byte[] bytes, int startIndex) {
		byte[] result = new byte[8];
		System.arraycopy(bytes, startIndex, result, 0, 8);
		long l = getLong(result);
		return Double.longBitsToDouble(l);
	}

	/**
	 * 将charsetName编码格式的字节数组转换为字符串
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String getString(byte[] bytes, String charsetName) {
		return new String(bytes, Charset.forName(charsetName));
	}

	/**
	 * 将GBK编码格式的字节数组转换为字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getString(byte[] bytes) {
		return getString(bytes, GBK);
	}

	/**
	 * 将16进制字符串转换为字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToBytes(String hex) {
		if (hex == null || "".equals(hex)) {
			return null;
		}
		int len = hex.length() / 2;
		byte[] result = new byte[len];
		char[] chArr = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(chArr[pos]) << 4 | toByte(chArr[pos + 1]));
		}
		return result;
	}
    
	/**
	 * 将16进制字符串转换为字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexToBytes(String hex) {
		if (hex.length() % Constant.TWO != 0){
			throw new IllegalArgumentException("input string should be any multiple of 2!");
		}
		hex.toUpperCase();

		byte[] byteBuffer = new byte[hex.length() / 2];

		byte padding = 0x00;
		boolean paddingTurning = false;
		for (int i = 0; i < hex.length(); i++) {
			if (paddingTurning) {
				char c = hex.charAt(i);
				int index = indexOf(hex, c);
				padding = (byte) ((padding << 4) | index);
				byteBuffer[i / 2] = padding;
				padding = 0x00;
				paddingTurning = false;
			} else {
				char c = hex.charAt(i);
				int index = indexOf(hex, c);
				padding = (byte) (padding | index);
				paddingTurning = true;
			}

		}
		return byteBuffer;
	}

	private static int indexOf(String input, char c) {
		int index = ArrayUtils.indexOf(HEX_VOCABLE, c);

		if (index < 0) {
			throw new IllegalArgumentException("err input:" + input);
		}
		return index;
	}

	/**
	 * 将BCD编码的字节数组转换为字符串
	 * 
	 * @param bcds
	 * @return
	 */
	public static String bcdToString(byte[] bcds) {
		if (bcds == null || bcds.length == 0) {
			return null;
		}
		byte[] temp = new byte[2 * bcds.length];
		for (int i = 0; i < bcds.length; i++) {
			temp[i * 2] = (byte) ((bcds[i] >> 4) & 0x0f);
			temp[i * 2 + 1] = (byte) (bcds[i] & 0x0f);
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			res.append(ASCII[temp[i]]);
		}
		return res.toString();
	}
	/** 
	* @Title: str2Bcd 
	* @Description: 10进制串转为BCD码 
	* @param @param asc 10进制串
	* @param @return    BCD码 
	* @return byte[]    返回类型 
	* @throws 
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

	/**
	 * 字节转整形
	 * @param value
	 * @return
	 */
	public static int bcdToInt(byte value) {
		return ((value >> 4) * 10) + (value & 0x0F);
	}

	/**
	 * 字节数组转16进制字符串
	 * @param bs
	 * @return
	 */
	public static String bytesToHex(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bs) {
			int high = (b >> 4) & 0x0f;
			int low = b & 0x0f;
			sb.append(HEX_VOCABLE[high]);
			sb.append(HEX_VOCABLE[low]);
		}
		return sb.toString();
	}
    
	/**
	 * 字节数组取前len个字节转16进制字符串
	 * @param bs
	 * @param len
	 * @return
	 */
	public static String bytesToHex(byte[] bs, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			byte b = bs[i];
			int high = (b >> 4) & 0x0f;
			int low = b & 0x0f;
			sb.append(HEX_VOCABLE[high]);
			sb.append(HEX_VOCABLE[low]);
		}
		return sb.toString();
	}
	
	/**
	 * 字节数组偏移offset长度之后的取len个字节转16进制字符串
	 * @param bs
	 * @param offset
	 * @param len
	 * @return
	 */
	public static String bytesToHex(byte[] bs, int offset, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			byte b = bs[offset + i];
			int high = (b >> 4) & 0x0f;
			int low = b & 0x0f;
			sb.append(HEX_VOCABLE[high]);
			sb.append(HEX_VOCABLE[low]);
		}
		return sb.toString();
	}
	
	/**
	 * 字节数组转16进制字符串
	 * 
	 * @param bs
	 * @return
	 */
	public static String byteToHex(byte b) {
		StringBuilder sb = new StringBuilder();
		int high = (b >> 4) & 0x0f;
		int low = b & 0x0f;
		sb.append(HEX_VOCABLE[high]);
		sb.append(HEX_VOCABLE[low]);
		return sb.toString();
	}
	
	/**
	 * 将字节数组取反
	 * @param src
	 * @return
	 */
	public static String negate(byte[] src) {
		if (src == null || src.length == 0) {
			return null;
		}
		byte[] temp = new byte[2 * src.length];
		for (int i = 0; i < src.length; i++) {
			byte tmp = (byte) (0xFF ^ src[i]);
			temp[i * 2] = (byte) ((tmp >> 4) & 0x0f);
			temp[i * 2 + 1] = (byte) (tmp & 0x0f);
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			res.append(ASCII[temp[i]]);
		}
		return res.toString();
	}

	/**
	 * 比较字节数组是否相同
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareBytes(byte[] a, byte[] b) {
		if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length) {
			return false;
		}
		if (a.length == b.length) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i]) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * 只比对指定长度byte
	 * @param a
	 * @param b
	 * @param len
	 * @return
	 */
	public static boolean compareBytes(byte[] a, byte[] b, int len) {
		if (a == null || a.length == 0 || b == null || b.length == 0 || a.length < len || b.length < len) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将字节数组转换为二进制字符串
	 * @param items
	 * @return
	 */
	public static String bytesToBinaryString(byte[] items) {
		if (items == null || items.length == 0) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		for (byte item : items) {
			buf.append(byteToBinaryString(item));
		}
		return buf.toString();
	}

	/**
	 * 将字节转换为二进制字符串
	 * @param items
	 * @return
	 */
	public static String byteToBinaryString(byte item) {
		byte a = item;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < Constant.EIGHT; i++) {
			buf.insert(0, a % 2);
			a = (byte) (a >> 1);
		}
		return buf.toString();
	}

	/**
	 * 对数组a，b进行异或运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static byte[] xor(byte[] a, byte[] b) {
		if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length) {
			return null;
		}
		byte[] result = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = (byte) (a[i] ^ b[i]);
		}
		return result;
	}

	/**
	 * 对数组a，b进行异或运算 运算长度len
	 * @param a
	 * @param b
	 * @param len
	 * @return
	 */
	public static byte[] xor(byte[] a, byte[] b, int len) {
		if (a == null || a.length == 0 || b == null || b.length == 0) {
			return null;
		}
		if (a.length < len || b.length < len) {
			return null;
		}
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = (byte) (a[i] ^ b[i]);
		}
		return result;
	}
	
	/**
	 * 将short整型数值转换为字节数组
	 * @param num
	 * @return
	 */
	public static byte[] shortToBytes(int num) {
		byte[] temp = new byte[2];
		for (int i = 0; i < Constant.TWO; i++) {
			temp[i] = (byte) ((num >>> (8 - i * 8)) & 0xFF);
		}
		return temp;
	}

	/**
	 * 将字节数组转为整型
	 * @param num
	 * @return
	 */
	public static int bytesToShort(byte[] arr) {
		int mask = 0xFF;
		int temp = 0;
		int result = 0;
		for (int i = 0; i < Constant.TWO; i++) {
			result <<= 8;
			temp = arr[i] & mask;
			result |= temp;
		}
		return result;
	}

	/**
	 * 将整型数值转换为指定长度的字节数组
	 * @param num
	 * @return
	 */
	public static byte[] intToBytes(int num) {
		byte[] temp = new byte[4];
		for (int i = 0; i < Constant.FOUR; i++) {
			temp[i] = (byte) ((num >>> (24 - i * 8)) & 0xFF);
		}
		return temp;
	}

	/**
	 * 将整型数值转换为指定长度的字节数组
	 * @param src
	 * @param len
	 * @return
	 */
	public static byte[] intToBytes(int src, int len) {
		if (len < 1 || len > Constant.FOUR) {
			return null;
		}
		byte[] temp = new byte[len];
		for (int i = 0; i < len; i++) {
			temp[len - 1 - i] = (byte) ((src >>> (8 * i)) & 0xFF);
		}
		return temp;
	}

	/**
	 * 将字节数组转换为整型数值
	 * @param arr
	 * @return
	 */
	public static int bytesToInt(byte[] arr) {
		int mask = 0xFF;
		int temp = 0;
		int result = 0;
		for (int i = 0; i < Constant.FOUR; i++) {
			result <<= 8;
			temp = arr[i] & mask;
			result |= temp;
		}
		return result;
	}

	/**
	 * 将long整型数值转换为字节数组
	 * @param num
	 * @return
	 */
	public static byte[] longToBytes(long num) {
		byte[] temp = new byte[8];
		for (int i = 0; i < Constant.EIGHT; i++) {
			temp[i] = (byte) ((num >>> (56 - i * 8)) & 0xFF);
		}
		return temp;
	}

	/**
	 * 将字节数组转换为long整型数值
	 * @param arr
	 * @return
	 */
	public static long bytesToLong(byte[] arr) {
		int mask = 0xFF;
		int temp = 0;
		long result = 0;
		int len = Math.min(8, arr.length);
		for (int i = 0; i < len; i++) {
			result <<= 8;
			temp = arr[i] & mask;
			result |= temp;
		}
		return result;
	}

	/**
	 * 将16进制字符转换为字节
	 * @param c
	 * @return
	 */
	public static byte toByte(char c) {
		String str = "0123456789ABCDEF";
		if (Character.isLowerCase(c)) {
			str ="0123456789abcdef";
		}
		
		byte b = (byte) str.indexOf(c);
		return b;
	}
    
	/**
	 * 功能描述：把两个字节的字节数组转化为整型数据，高位补零，例如：<br/>
	 * 有字节数组byte[] data = new byte[]{1,2};转换后int数据的字节分布如下：<br/>
	 * 00000000 00000000 00000001 00000010,函数返回258
	 * @param lenData 需要进行转换的字节数组
	 * @return 字节数组所表示整型值的大小
	 */
	public static int bytesToIntWhereByteLengthEquals2(byte lenData[]) {
		if (lenData.length != Constant.TWO) {
			return -1;
		}
		byte fill[] = new byte[] { 0, 0 };
		byte real[] = new byte[4];
		System.arraycopy(fill, 0, real, 0, 2);
		System.arraycopy(lenData, 0, real, 2, 2);
		int len = byteToInt(real);
		return len;

	}
    
	/**
	 * 功能描述：将byte数组转化为int类型的数据
	 * 
	 * @param byteVal 需要转化的字节数组
	 * @return 字节数组所表示的整型数据
	 */
	public static int byteToInt(byte[] byteVal) {
		int result = 0;
		for (int i = 0; i < byteVal.length; i++) {
			int tmpVal = (byteVal[i] << (8 * (3 - i)));
			switch (i) {
			case 0:
				tmpVal = tmpVal & 0xFF000000;
				break;
			case 1:
				tmpVal = tmpVal & 0x00FF0000;
				break;
			case 2:
				tmpVal = tmpVal & 0x0000FF00;
				break;
			case 3:
				tmpVal = tmpVal & 0x000000FF;
				break;
			default:
				break;
			}

			result = result | tmpVal;
		}
		return result;
	}

	public static byte checkXORSum(byte[] bData) {
		byte sum = 0x00;
		for (int i = 0; i < bData.length; i++) {
			sum ^= bData[i];
		}
		return sum;
	}
	
	/**
	 * 从offset开始 将后续长度为len的byte字节转为int
	 * @param data
	 * @param offset
	 * @param len
	 * @return
	 */
	public static int bytesToInt(byte[] data, int offset, int len) {
		int mask = 0xFF;
		int temp = 0;
		int result = 0;
		len = Math.min(len, 4);
		for (int i = 0; i < len; i++) {
			result <<= 8;
			temp = data[offset + i] & mask;
			result |= temp;
		}
		return result;
	}
    
	/**
	 * byte字节数组中的字符串的长度
	 * @param data
	 * @return
	 */
	public static int getBytesStringLen(byte[] data) {
		int count = 0;
		for (byte b : data) {
			if (b == 0x00){
				break;
			}
			count++;
		}
		return count;
	}
	
	/** 
	* @Title: byteMergerAll 
	* @Description: 合并多个byte[]
	* @param @param values
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	*/
	public static byte[] byteMergerAll(byte[]... values) {
        int lengthByte = 0;
            for (int i = 0; i < values.length; i++) {
            	lengthByte += values[i].length;
            }
            byte[] allByte = new byte[lengthByte];
            int countLength = 0;
            for (int i = 0; i < values.length; i++) {
                byte[] b = values[i];
                System.arraycopy(b, 0, allByte, countLength, b.length);
                countLength += b.length;
            }
            return allByte;
        }
	
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
	
	static byte[] crc16_tab_h = { (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40 };

	static byte[] crc16_tab_l = { (byte) 0x00, (byte) 0xC0, (byte) 0xC1, (byte) 0x01, (byte) 0xC3, (byte) 0x03,
			(byte) 0x02, (byte) 0xC2, (byte) 0xC6, (byte) 0x06, (byte) 0x07, (byte) 0xC7, (byte) 0x05, (byte) 0xC5,
			(byte) 0xC4, (byte) 0x04, (byte) 0xCC, (byte) 0x0C, (byte) 0x0D, (byte) 0xCD, (byte) 0x0F, (byte) 0xCF,
			(byte) 0xCE, (byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB, (byte) 0x0B, (byte) 0xC9, (byte) 0x09,
			(byte) 0x08, (byte) 0xC8, (byte) 0xD8, (byte) 0x18, (byte) 0x19, (byte) 0xD9, (byte) 0x1B, (byte) 0xDB,
			(byte) 0xDA, (byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF, (byte) 0x1F, (byte) 0xDD, (byte) 0x1D,
			(byte) 0x1C, (byte) 0xDC, (byte) 0x14, (byte) 0xD4, (byte) 0xD5, (byte) 0x15, (byte) 0xD7, (byte) 0x17,
			(byte) 0x16, (byte) 0xD6, (byte) 0xD2, (byte) 0x12, (byte) 0x13, (byte) 0xD3, (byte) 0x11, (byte) 0xD1,
			(byte) 0xD0, (byte) 0x10, (byte) 0xF0, (byte) 0x30, (byte) 0x31, (byte) 0xF1, (byte) 0x33, (byte) 0xF3,
			(byte) 0xF2, (byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7, (byte) 0x37, (byte) 0xF5, (byte) 0x35,
			(byte) 0x34, (byte) 0xF4, (byte) 0x3C, (byte) 0xFC, (byte) 0xFD, (byte) 0x3D, (byte) 0xFF, (byte) 0x3F,
			(byte) 0x3E, (byte) 0xFE, (byte) 0xFA, (byte) 0x3A, (byte) 0x3B, (byte) 0xFB, (byte) 0x39, (byte) 0xF9,
			(byte) 0xF8, (byte) 0x38, (byte) 0x28, (byte) 0xE8, (byte) 0xE9, (byte) 0x29, (byte) 0xEB, (byte) 0x2B,
			(byte) 0x2A, (byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F, (byte) 0xEF, (byte) 0x2D, (byte) 0xED,
			(byte) 0xEC, (byte) 0x2C, (byte) 0xE4, (byte) 0x24, (byte) 0x25, (byte) 0xE5, (byte) 0x27, (byte) 0xE7,
			(byte) 0xE6, (byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3, (byte) 0x23, (byte) 0xE1, (byte) 0x21,
			(byte) 0x20, (byte) 0xE0, (byte) 0xA0, (byte) 0x60, (byte) 0x61, (byte) 0xA1, (byte) 0x63, (byte) 0xA3,
			(byte) 0xA2, (byte) 0x62, (byte) 0x66, (byte) 0xA6, (byte) 0xA7, (byte) 0x67, (byte) 0xA5, (byte) 0x65,
			(byte) 0x64, (byte) 0xA4, (byte) 0x6C, (byte) 0xAC, (byte) 0xAD, (byte) 0x6D, (byte) 0xAF, (byte) 0x6F,
			(byte) 0x6E, (byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B, (byte) 0xAB, (byte) 0x69, (byte) 0xA9,
			(byte) 0xA8, (byte) 0x68, (byte) 0x78, (byte) 0xB8, (byte) 0xB9, (byte) 0x79, (byte) 0xBB, (byte) 0x7B,
			(byte) 0x7A, (byte) 0xBA, (byte) 0xBE, (byte) 0x7E, (byte) 0x7F, (byte) 0xBF, (byte) 0x7D, (byte) 0xBD,
			(byte) 0xBC, (byte) 0x7C, (byte) 0xB4, (byte) 0x74, (byte) 0x75, (byte) 0xB5, (byte) 0x77, (byte) 0xB7,
			(byte) 0xB6, (byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3, (byte) 0x73, (byte) 0xB1, (byte) 0x71,
			(byte) 0x70, (byte) 0xB0, (byte) 0x50, (byte) 0x90, (byte) 0x91, (byte) 0x51, (byte) 0x93, (byte) 0x53,
			(byte) 0x52, (byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57, (byte) 0x97, (byte) 0x55, (byte) 0x95,
			(byte) 0x94, (byte) 0x54, (byte) 0x9C, (byte) 0x5C, (byte) 0x5D, (byte) 0x9D, (byte) 0x5F, (byte) 0x9F,
			(byte) 0x9E, (byte) 0x5E, (byte) 0x5A, (byte) 0x9A, (byte) 0x9B, (byte) 0x5B, (byte) 0x99, (byte) 0x59,
			(byte) 0x58, (byte) 0x98, (byte) 0x88, (byte) 0x48, (byte) 0x49, (byte) 0x89, (byte) 0x4B, (byte) 0x8B,
			(byte) 0x8A, (byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F, (byte) 0x4F, (byte) 0x8D, (byte) 0x4D,
			(byte) 0x4C, (byte) 0x8C, (byte) 0x44, (byte) 0x84, (byte) 0x85, (byte) 0x45, (byte) 0x87, (byte) 0x47,
			(byte) 0x46, (byte) 0x86, (byte) 0x82, (byte) 0x42, (byte) 0x43, (byte) 0x83, (byte) 0x41, (byte) 0x81,
			(byte) 0x80, (byte) 0x40 };

	/**
	 * 计算CRC16校验 对外的接口
	 *
	 * @param data 需要计算的数组
	 * @return CRC16校验值
	 */
	public static int calcCrc16(byte[] data) {
		return calcCrc16(data, 0, data.length);
	}

	/**
	 * 计算CRC16校验
	 *
	 * @param data   需要计算的数组
	 * @param offset 起始位置
	 * @param len    长度
	 * @return CRC16校验值
	 */
	public static int calcCrc16(byte[] data, int offset, int len) {
		return calcCrc16(data, offset, len, 0xffff);
	}

	/**
	 * 计算CRC16校验
	 *
	 * @param data   需要计算的数组
	 * @param offset 起始位置
	 * @param len    长度
	 * @param preval 之前的校验值
	 * @return CRC16校验值
	 */
	public static int calcCrc16(byte[] data, int offset, int len, int preval) {
		int ucCRCHi = (preval & 0xff00) >> 8;
		int ucCRCLo = preval & 0x00ff;
		int iIndex;
		for (int i = 0; i < len; ++i) {
			iIndex = (ucCRCLo ^ data[offset + i]) & 0x00ff;
			ucCRCLo = ucCRCHi ^ crc16_tab_h[iIndex];
			ucCRCHi = crc16_tab_l[iIndex];
		}
		return ((ucCRCHi & 0x00ff) << 8) | (ucCRCLo & 0x00ff) & 0xffff;
	}

	/**
	 * 将计算的CRC值 转换为加空格的 比如 ： crc值为 A30A -> A3 0A
	 * 
	 * @param res
	 * @return
	 */
	public static String getCrc(int res) {
		String format = String.format("%04x", res);
		String substring = format.substring(0, 2);
		String substring1 = format.substring(2, 4);
		return substring.concat(" ").concat(substring1).concat(" ");
	}

	/**
	 * 将计算的CRC值 转换为加空格的 比如 ： crc值为 A30A -> A3 0A
	 * 
	 * @param res
	 * @return
	 */
	public static String getCrc(byte[] data) {
		String format = String.format("%04x", calcCrc16(data));
		String substring = format.substring(0, 2);
		String substring1 = format.substring(2, 4);
		return substring.toUpperCase().concat("").concat(substring1.toUpperCase()).concat("");
	}
	
	/**
	 * 将计算的CRC值 转换为加空格的 比如 ： crc值为 A30A ->  0A A3
	 * 低位在前 高位在后
	 * @param res
	 * @return
	 */
	public static String getReserveCrc(byte[] data) {
		String format = String.format("%04x", calcCrc16(data));
		String substring = format.substring(0, 2);
		String substring1 = format.substring(2, 4);
		return substring1.toUpperCase().concat("").concat(substring.toUpperCase()).concat("");
	}
	
	/** 
	* @Title: hexToAscii 
	* @Description: hexToAscii 
	* @param @param hexStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}
	
	/** 
	* @Title: asciiToHex 
	* @Description: asciiToHex
	* @param @param asciiStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String asciiToHex(String asciiStr) {
		char[] chars = asciiStr.toCharArray();
		StringBuilder hex = new StringBuilder();
		for (char ch : chars) {
			hex.append(Integer.toHexString((int) ch));
		}
		return hex.toString();
	}
	
    /** 
    * @Title: getByteArray 
    * @Description: 获取一个字节的bit数组 
    * @param @param value
    * @param @return    设定文件 
    * @return byte[]    返回类型 
    * @throws 
    */
    public static byte[] getByteArray(byte value) {
        byte[] byteArr = new byte[8]; //一个字节八位
        for (int i = 7; i > 0; i--) {
            byteArr[i] = (byte) (value & 1); //获取最低位
            value = (byte) (value >> 1); //每次右移一位
        }
        return byteArr;
    }
    
}
