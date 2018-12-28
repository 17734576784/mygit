/**
 * 
 */
package com.netty.server;
import com.dbr.message.ServerProtobufDecoder;
import com.dbr.message.ServerProtobufEncoder;
import com.dbr.message.ClientProtobufEncoder;

import io.netty.bootstrap.ServerBootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioServerSocketChannel;  
import io.netty.handler.codec.protobuf.ProtobufDecoder;  
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;  
import io.netty.handler.logging.LogLevel;  
import io.netty.handler.logging.LoggingHandler;  
  
/**   
 * @ClassName:  ProtoBufServer   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年8月24日 下午2:49:37   
 *      
 */
public class ProtoBufServer {
	   
    public void bind(int port) throws Exception {  
        // 配置服务端的NIO线程组  
        EventLoopGroup bossGroup = new NioEventLoopGroup();  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)  
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {  
                        @Override  
                        public void initChannel(SocketChannel ch) {  
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());  
                            ch.pipeline().addLast("decoder",new ServerProtobufDecoder());
                            ch.pipeline().addLast("encoder",new ServerProtobufEncoder());
                            ch.pipeline().addLast(new ProtoBufServerHandler());  
                        }  
                    });  
  
            // 绑定端口，同步等待成功  
            ChannelFuture f = b.bind(port).sync();  
  
            System.out.println("init start");  
            // 等待服务端监听端口关闭  
            f.channel().closeFuture().sync();  
        } finally {  
            // 优雅退出，释放线程池资源  
            bossGroup.shutdownGracefully();  
            workerGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        int port = 8080;  
        if (args != null && args.length > 0) {  
            try {  
                port = Integer.valueOf(args[0]);  
            } catch (NumberFormatException e) {  
                // 采用默认值  
            }  
        }  
        new ProtoBufServer().bind(port);  
    }  
  
}
