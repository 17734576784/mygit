/**
 * 
 */
package com.pile.netty.server;


import java.net.InetSocketAddress;
import com.pile.common.Constant;
import com.pile.utils.JedisUtils;
import com.pile.utils.Log4jUtils;
import com.pile.utils.MyApplicationContextUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**   
 * @ClassName:  NettyServerHandler   
 * @Description:服务端处理器   
 * @author: dbr 
 * @date:   2018年8月10日 下午3:56:22   
 *      
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {

 	private JedisUtils jedisUtils;
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		StringBuilder sb = null;
		String result= "";
        try {
        	jedisUtils = (JedisUtils) MyApplicationContextUtil.getContext().getBean("jedisUtils");
			// 报文解析处理
        	System.out.println("接收充电消息：" + msg);
			Log4jUtils.getDiscountinfo().info("接收充电消息：" + msg);	
			jedisUtils.lpush(Constant.COSTQUEUE,msg);
			
			sb = new StringBuilder();
			sb.append(result);
			sb.append("解析成功");
			sb.append("\n");
			ctx.writeAndFlush(sb);
        } catch (Exception e) {
			String errorCode = "-1\n";
			ctx.writeAndFlush(errorCode);
			Log4jUtils.getDiscountinfo().error("报文解析失败: " + e.getMessage());
			e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String clientIp = insocket.getAddress().getHostAddress();
		Log4jUtils.getDiscountinfo().info("收到客户端[ip:" + clientIp + "]连接");
		System.out.println("收到客户端[ip:" + clientIp + "]连接");
    }

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 当出现异常就关闭连接
//		ctx.close();
		cause.printStackTrace();
    }

    
}
