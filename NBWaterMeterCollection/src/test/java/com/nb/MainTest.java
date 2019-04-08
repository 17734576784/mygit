/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
* @version V1.0   
*/
package com.nb;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.model.NbCommand;
import com.nb.utils.JsonUtil;
import com.nb.utils.SerializeUtils;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
*  
*/
public class MainTest {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NbCommand command = new NbCommand();
		command.setRtuId(1);
		command.setCommandContent("dfdfdfsdf");
		String jsonStr = JsonUtil.jsonObj2Sting(command);
		System.out.println(jsonStr);
		
		command = JsonUtil.jsonString2SimpleObj(jsonStr, NbCommand.class);
		System.out.println(command.getRtuId());
	}

}
