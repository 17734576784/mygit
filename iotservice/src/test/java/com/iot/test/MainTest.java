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
import com.iot.utils.FileUtils;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String url ="http://222.222.60.178:18130/Enterprise_EnnGas/enngas/message/nbNotifyAction!notifyToWeb.action";
//		url = "http://127.0.0.1:8080/file";
		String filePath ="D:/20180831114948.png";
		
		FileUtils.upload(url, filePath, "20180901", "232323232");
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
