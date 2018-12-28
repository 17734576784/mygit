/**   
* @Title: MainTest.java 
* @Package com.pile.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年3月26日 下午5:05:02 
* @version V1.0   
*/
package com.pile.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pile.common.Constant;
import com.pile.model.ChargeInfo;
import com.pile.utils.DateUtils;
import com.pile.utils.JedisUtils;
import com.pile.utils.JsonUtils;
import com.pile.utils.SerializeUtils;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年3月26日 下午5:05:02 
*  
*/
public class MainTest {
	public static void main(String[] args) {
		
		Map<String,Object> value = new HashMap<String,Object>();
		value.put("memberId", 1);
		value.put("pileCode", "KE00000001");
		value.put("pileId", 10001);
		
		
		String text = JsonUtils.toJSONString(value);
		ChargeInfo chargeInfo = JsonUtils.toBean(text, ChargeInfo.class);
		System.out.println(chargeInfo.toString());
		
//		Map<Integer, String> endCause = new HashMap<>();
//		endCause.put(0, "正常充满");
//		endCause.put(9, "人为结束");
//		endCause.put(12, "计费控制单元控制停止充电");
//		endCause.put(18, "充满或插头断开");
//		
//		System.out.println(endCause.containsKey(0));
		
//		System.out.println(DateUtils.nowYM());
		
		
//		String tableName = "";
//		Calendar c = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		for (int i = 0; i < 3; i++) {
//			c.add(Calendar.MONTH, i * -1);
//			System.out.println(sdf.format(c.getTime()));
//			tableName = "ChargeData.charge"+ sdf.format(c.getTime());
// 			System.out.println(tableName);
//		 
 	}
}
