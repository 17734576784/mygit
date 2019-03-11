/**   
* @Title: NettyServerListener.java 
* @Package com.pile.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月16日 下午5:36:03 
* @version V1.0   
*/
package com.pile.netty.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/** 
* @ClassName: NettyServerListener 
* @Description: netty服务端监听器 
* @author dbr
* @date 2018年8月16日 下午5:36:03 
*  
*/
//@WebListener
public class NettyServerListener implements ServletContextListener {
	
	private NettyServer server;
	/** (非 Javadoc) 
	* <p>Title: contextDestroyed</p> 
	* <p>Description: </p> 
	* @param arg0 
	* @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent) 
	*/
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/** (非 Javadoc) 
	* <p>Title: contextInitialized</p> 
	* <p>Description: </p> 
	* @param arg0 
	* @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent) 
	*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		server = new NettyServer();
		Thread thread = new Thread(new NettyServerThread());
		// 启动netty服务
		thread.start();
	}

	/** 
	* @ClassName: NettyServerThread 
	* @Description: netty服务启动线程 
	* @author dbr
	* @date 2018年8月16日 下午5:39:47 
	*  
	*/
	private class NettyServerThread implements Runnable{

		/** (非 Javadoc) 
		* <p>Title: run</p> 
		* <p>Description: </p>  
		* @see java.lang.Runnable#run() 
		*/
		@Override
		public void run() {
			server.run();
		}
	}

}
