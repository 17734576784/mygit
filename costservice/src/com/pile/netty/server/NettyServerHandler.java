/**
 * 
 */
package com.pile.netty.server;


import java.net.InetSocketAddress;
import com.pile.common.Constant;
import com.pile.netty.message.ChargeInfoBufOuterClass.ChargeInfoBuf;
import com.pile.netty.message.ResultOuterClass;
import com.pile.utils.JedisUtils;
import com.pile.utils.Log4jUtils;

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

	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		
		ResultOuterClass.Result.Builder result = ResultOuterClass.Result.newBuilder();
		ChargeInfoBuf chargeInfo = (ChargeInfoBuf) msg;
		result.setOrderSerialNumber(chargeInfo.getOrderSerialNumber());
		try {
			// 报文解析处理
			Log4jUtils.getDiscountinfo().info("接收充电消息：" + chargeInfo);
			JedisUtils.lpush(Constant.COST_QUEUE, chargeInfo);
			result.setFlag(Constant.SUCCESS);
		} catch (Exception e) {
			result.setFlag(Constant.FAILED);
			Log4jUtils.getDiscountinfo().error("报文解析失败: " + e.getMessage());
			e.printStackTrace();

		} finally {
			ctx.writeAndFlush(result.build());
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
