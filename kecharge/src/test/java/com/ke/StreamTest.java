package com.ke;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**   
* @Title: StreamTest.java 
* @Package  
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月16日 下午3:38:58 
* @version V1.0   
*/

/**
 * @ClassName: StreamTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月16日 下午3:38:58
 * 
 */
public class StreamTest {
	public static void main(String[] args) {
		  int num = Runtime.getRuntime().availableProcessors();
		     System.out.println(num);
		     
		 int size = 100000;
	        List<String> uuisList = new ArrayList<>(size);
	 
	        System.out.println("开始生成 = " + new Date());
	        //生成500万个uuid
	        for (int i = 0; i< size; i++){
	            uuisList.add(UUID.randomUUID().toString());
	        }
	        System.out.println("生成结束 = " + new Date());
	 
	        System.out.println("开始串行排序");
	        //long starttime = System.currentTimeMillis();//毫秒
	        long startTime = System.nanoTime();//纳秒，更为精确
	        uuisList.stream().sorted().count();//串行排序
	        long endTime = System.nanoTime();//纳秒，更为精确
	        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
	        System.out.println("结束串行排序 耗时为 " + distanceTime); 
	 
//	        System.out.println("开始并行排序");
//	        //long starttime = System.currentTimeMillis();//毫秒
//	       long startTime = System.nanoTime();//纳秒，更为精确
//	 
//	        uuisList.parallelStream().sorted().count();//并行排序
//	 
//	        long endTime = System.nanoTime();//纳秒，更为精确
//	        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
//	        System.out.println("结束并行排序 耗时为 " + distanceTime);
	}
}
