/**   
* @Title: ComntCfg.java 
* @Package com.keicpms.communite 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhp
* @date 2018年12月13日 下午4:58:53 
* @version V1.0   
*/
package com.ke.comnt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
* @ClassName: ComntCfg 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhp
* @date 2018年12月13日 下午4:58:53 
*  
*/
@Component
public class ComntCfg {
	public static String webservice_ip; // 提供默认初始值
	public static int webservice_port; // 提供默认初始值
	
	@Value("${webservice_ip}")
	private String host;
	
	@Value("${webservice_port}")
	private int port;
	
	@PostConstruct
	public void init(){
		ComntCfg.webservice_ip = host;
		ComntCfg.webservice_port = port;
	}
	/** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
	* <p>
	* <p>NAME        : loadComntCfg
	* <p>
	* <p>DESCRIPTION : 读取通讯配置
	* <p>
	* <p>COMPLETION
	* <p>INPUT       : 
	* <p>OUTPUT      : 
	* <p>RETURN      : String
	* <p>
	*-----------------------------------------------------------*/
//	public void loadComntCfg(){
//		this.webservice_ip = "127.0.0.1";
//		this.webservice_port = 17006; 
//	}
}
