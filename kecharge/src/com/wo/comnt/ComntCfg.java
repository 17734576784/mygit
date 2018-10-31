package com.wo.comnt;

import com.wo.util.WebConfig;

public class ComntCfg {
	public String webservice_ip   = "127.0.0.1";	//提供默认初始值
	public int	  webservice_port = 17206;			//提供默认初始值
	
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
	public void loadComntCfg(){
		this.webservice_ip   = WebConfig.webservice_ip;
		this.webservice_port = WebConfig.webservice_port; 
	}
	
	
}
