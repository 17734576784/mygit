/**   
* @Title: StartComnt.java 
* @Package com.ke.comnt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月9日 下午4:25:28 
* @version V1.0   
*/
package com.ke.comnt;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/** 
* @ClassName: StartComnt 
* @Description: 启动通信线程
* @author dbr
* @date 2019年1月9日 下午4:25:28 
*  
*/
@Component
public class StartComnt {

	@PostConstruct
	public void init(){
		ComntProc.startComntProc();
	}
}
