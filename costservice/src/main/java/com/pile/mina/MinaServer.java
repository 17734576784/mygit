/**   
* @Title: MinaServer.java 
* @Package com.pile.mina 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月19日 上午11:12:41 
* @version V1.0   
*/
package com.pile.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/** 
* @ClassName: MinaServer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年4月19日 上午11:12:41 
*  
*/
public class MinaServer {

	private static final int PORT = 8888;
	
	public static void main(String[] args) throws IOException {
		// 监听传入连接的对象
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 记录所有的信息 比如创建session 接收消息 发生消息 关闭会员等
		LoggingFilter loggingFilter = new LoggingFilter();
		loggingFilter.setSessionClosedLogLevel(LogLevel.NONE);
		loggingFilter.setSessionCreatedLogLevel(LogLevel.DEBUG);
		loggingFilter.setSessionOpenedLogLevel(LogLevel.INFO);
		acceptor.getFilterChain().addLast("logging", loggingFilter);
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));

		acceptor.setHandler(new MinaServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		acceptor.bind(new InetSocketAddress(PORT));
		System.out.println("Listening on port  " + PORT);
	}
	
	public static void startServer() throws IOException{
		// 监听传入连接的对象
			IoAcceptor acceptor = new NioSocketAcceptor();
			// 记录所有的信息 比如创建session 接收消息 发生消息 关闭会员等
			LoggingFilter loggingFilter = new LoggingFilter();
			loggingFilter.setSessionClosedLogLevel(LogLevel.NONE);
			loggingFilter.setSessionCreatedLogLevel(LogLevel.DEBUG);
			loggingFilter.setSessionOpenedLogLevel(LogLevel.INFO);
			acceptor.getFilterChain().addLast("logging", loggingFilter);
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));

			acceptor.setHandler(new MinaServerHandler());
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("Listening on port  " + PORT);
	}
	
	
}
