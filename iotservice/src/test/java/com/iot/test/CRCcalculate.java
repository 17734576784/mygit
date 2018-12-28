/**   
* @Title: CRCcalculate.java 
* @Package com.iot.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月22日 上午11:20:16 
* @version V1.0   
*/
package com.iot.test;

/** 
* @ClassName: CRCcalculate 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月22日 上午11:20:16 
*  
*/
import java.util.Arrays;

import com.iot.utils.BytesUtils;

public class CRCcalculate {
	private static int crcData[] = new int[] {};

	public static int cCRCalculate(int[] converarr) {
		short crcorig = 0;
		// 转换为无符号整数位
		int cRCdata = getUnsignedByte(crcorig);
		for (int i = 0; i < 11; i++) {
			int a = crcData[(cRCdata & 0xFF) ^ converarr[i]];
			cRCdata = ((cRCdata >> 8) ^ a);
		}
		return cRCdata;
	}

	public static void cRCFill(int[] buffer) {// 填充CRC码
//      crcint= cCRCalculate(buffer);
//      //取低八位
//      buffer[13]=(crcint&0xff);
//      //取高八位
//      buffer[11]=(crcint>>8);
	}

	public static void main(String[] args) {
		String sendString = "02 00 6A FF FF 00 9C 67 3D FF 00 45 96 82";
		String newSendstr = sendString.trim().replace(" ", "");
		byte[] hexbytes = BytesUtils.hexStringToBytes(newSendstr);
		int[] converarr = getUnsignedByte(hexbytes);
		System.out.println(Arrays.toString(hexbytes) + "hexbytes");
		System.out.println(Arrays.toString(converarr) + "unsigned");
		int testi = cCRCalculate(converarr);
		System.out.println(testi + "converarrint");
		// 低八位校验和
		int crcr = testi & 0xff;
		// 高八位校验和
		int crcl = testi >> 8;
	}

//相当于C++ 中的BYTE
	public static int[] getUnsignedByte(byte[] data) {
		// 将data字节型数据转换为0~255 (0xFF 即BYTE)。
		int[] arr = new int[14];
		for (int i = 0; i < data.length; i++) {
			arr[i] = data[i] & 0x0FF;
		}
		return arr;

	}

//相当于C++ 中的WORD
	public static int getUnsignedByte(short data) {
//将data字节型数据转换为0~65535 (0xFFFF 即 WORD)。
		return data & 0x0FFFF;
	}
}
