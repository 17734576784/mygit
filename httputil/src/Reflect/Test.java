/**   
* @Title: Test.java 
* @Package Reflect 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月16日 下午4:32:49 
* @version V1.0   
*/
package Reflect;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/** 
* @ClassName: Test 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月16日 下午4:32:49 
*  
*/
public class Test {

	/**
	 * @throws IOException  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) throws IOException {
//		String str = "罗长";
//		byte[] sb = str.getBytes();
//		for (byte b : sb) {
//			System.out.print(b + " ");
//		}
//		
//		System.out.println();
//		byte[] str1 = new byte[] {-62, -34, -77, -92 };
//		System.out.println(new String (str1));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(0X01);
		dos.writeByte(0X01);
		dos.write(((short) 12));
		dos.write(((short) 2));
		dos.write(((short) 25));
		byte[] tmp = baos.toByteArray();
		System.out.println("tmp : " + bytesToHexString(tmp));
		for (byte b : tmp) {
			System.out.print(b + " ");
		}
		
		
		ByteBuffer bf = ByteBuffer.allocate(100);
		bf.asIntBuffer().put(0,3);
		
//		String fileName = "E:\\BaiduYunDownload\\dubbo\\Dubboyhzn_jb51.rar";
//		String[] tmp = fileName.split("\\\\");
//		System.out.println(tmp[tmp.length-1]);
				
		
 	}
	public static String bytesToHexString(byte[] src){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length <= 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < src.length; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString();   
	}   
}
