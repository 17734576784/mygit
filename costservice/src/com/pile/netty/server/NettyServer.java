/**   
* @Title: NettyServer.java 
* @Package com.pile.netty 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年8月16日 下午5:13:15 
* @version V1.0   
*/
package com.pile.netty.server;

import com.pile.netty.message.ServerProtobufDecoder;
import com.pile.netty.message.ServerProtobufEncoder;
import com.pile.utils.Log4jUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/** 
* @ClassName: NettyServer 
* @Description: netty服务端
* @author dbr
* @date 2018年8月16日 下午5:13:15 
*  
*/
public class NettyServer {
	
	/** 
	* @Fields port : 服务端端口号 
	*/ 
//	@Value("${netty.port}")
	private int port = 19999;
	
	/** 
	* @Title: run 
	* @Description: 启动服务器方法 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
 		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
//			serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
//			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {   
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  
                    // 注册handler    
                	ch.pipeline().addLast("decoder",new ServerProtobufDecoder());
                    ch.pipeline().addLast("encoder",new ServerProtobufEncoder());
                    ch.pipeline().addLast(new NettyServerHandler());                      
                }  
            }) ; 
			//绑定端口
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			System.out.println("netty服务启动:[port:" + port + "]");
			Log4jUtils.getNettyserver().info("netty服务启动:[port:" + port + "]");
			
			//等待服务器socket关闭
			channelFuture.channel().closeFuture().sync();			
		} catch (Exception e) {
			System.out.println("netty服务启动失败:[port:" + port + "]");
			Log4jUtils.getNettyserver().info("netty服务启动失败:[port:" + port + "]"+e.getMessage());
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
