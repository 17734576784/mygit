/**
 * 
 */
package com.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**   
 * @ClassName:  NettyClientHandler   
 * @Description:客户端处理器 
 * @author: dbr 
 * @date:   2018年8月10日 下午5:01:20   
 *      
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到服务端消息: " + msg);
    }
}  
