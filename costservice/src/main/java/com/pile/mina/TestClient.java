/**   
* @Title: TestClient.java 
* @Package com.pile.mina 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年5月31日 下午3:24:11 
* @version V1.0   
*/
package com.pile.mina;

 import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pile.model.ChargeInfo;
import com.pile.utils.JsonUtils;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

/** 
* @ClassName: TestClient 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年5月31日 下午3:24:11 
*  
*/
public class TestClient {
	
	private static final String ip = "127.0.0.1";
	private static final int port = 9123;
	
	public static void main(String[] args) {
		// 创建客户端连接器. 
//        NioSocketConnector connector = new NioSocketConnector(); 
//        connector.getFilterChain().addLast( "logger", new LoggingFilter() ); 
//        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "utf-8" )))); //设置编码过滤器 
//        connector.setHandler(new MinaClientHandler());//设置事件处理器 
//        
//        
//        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 8888));//建立连接 
//        cf.awaitUninterruptibly();//等待连接创建完成 
//        
        ChargeInfo chargeInfo = new ChargeInfo();
//		chargeInfo.setSerialNumber("10000001");
		chargeInfo.setMemberId(1);
		chargeInfo.setPileCode("KE000001");
		chargeInfo.setPrepaidMoney(10000);
		chargeInfo.setRechargeMoney(0);
		chargeInfo.setRemainingMoney(500);
		chargeInfo.setPayableMoney(10000-500);
//		
//		
//        cf.getSession().write(JSON.toJSONString(chargeInfo));//发送消息 
//        for (int i = 0; i < 10; i++) {
//        	 cf.getSession().write(i);//发送消息 
//		}
//        cf.getSession().close(true);
//        cf.getSession().getCloseFuture().awaitUninterruptibly();//等待连接断开 
//        connector.dispose(); 
		System.out.println(JSON.toJSONString(chargeInfo));
		for(int i =0; i < 10;i++){
			chargeInfo.setSerialNumber(""+i);
			sendMsg(JSON.toJSONString(chargeInfo));
		}
	}
	
	public static void sendMsg(String msg){
		// 创建客户端连接器. 
        NioSocketConnector connector = new NioSocketConnector(); 
        connector.getFilterChain().addLast( "logger", new LoggingFilter() ); 
        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "utf-8" )))); //设置编码过滤器 
        connector.setHandler(new MinaClientHandler());//设置事件处理器 
        ConnectFuture cf = connector.connect(new InetSocketAddress(ip,port ));//建立连接 
        cf.awaitUninterruptibly();//等待连接创建完成 
        
		
//        for (int i = 0; i < 10; i++) {
        	 cf.getSession().write(msg);//发送消息 
//		}
//        cf.getSession().close(true);
//        cf.getSession().getCloseFuture().awaitUninterruptibly();//等待连接断开 
//        connector.dispose(); 
		
	}
	
	
	
	
	
	
	
	
	
}
