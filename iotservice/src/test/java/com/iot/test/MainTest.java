package com.iot.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;

import com.iot.utils.CommFunc;
import com.iot.utils.Constant;
import com.iot.utils.FileUtils;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
//		String str = "ASNFZ4mrze8BI0VniavN7wEjRWeJq83vASNFZ4mrze8BI0VniavN7wEjRWeJq83vASNFZ4mrze8BI0VniavN7wEjRWeJq83vASNFZ4mrze8BI0VniavN7wEjRWeJq83vAAAAAA==";
//		byte[] result = decode(str);
//		for (byte b : result) {
//			System.out.print(b + " ");
//		}
		
//		System.out.println(LocalDateTime.now().getNano());
//		String fileName = "d://c446472e-207d-4c53-a676-4fd3873b6406.jpeg";
//		byte[] a = new byte[10];
//		for (int i = 0; i < a.length; i++) {
//			a[i] = (byte)i;
//		}
//		
//		CommFunc.byte2image(a, fileName);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(new Date()));
		
		FileUtils.upload(Constant.UPLOADIMAGEURL, "d:/cee8649d-c079-4424-aa76-a05a085881d6_40000000.jpeg", "20180912", "cee8649d-c079-4424-aa76-a05a085881d6");

	}
	
	/**  
	    * 编码  
	    * @param bstr  
	    * @return String  
	    */    
	   public static String encode(byte[] bstr){    
	   return new sun.misc.BASE64Encoder().encode(bstr);    
	   }    
	   
	   /**  
	    * 解码  
	    * @param str  
	    * @return string  
	    */    
	   public static byte[] decode(String str){    
	   byte[] bt = null;    
	   try {    
	       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
	       bt = decoder.decodeBuffer( str );    
	   } catch (IOException e) {    
	       e.printStackTrace();    
	   }    
	   
	       return bt;    
	   }

}
