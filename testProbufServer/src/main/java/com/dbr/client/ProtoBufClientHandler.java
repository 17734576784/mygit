/**
 * 
 */
package com.dbr.client;

/**   
 * @ClassName:  ProtoBufClientHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年8月24日 下午2:53:42   
 *      
 */
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
import java.util.ArrayList;  
import java.util.List;

import com.dbr.message.Result;
import com.dbr.message.StockTickOuterClass.StockTick;
import com.dbr.message.StockTickOuterClass.StockTickOrBuilder;  
  
  
public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {  
	 @Override  
	    public void channelActive(ChannelHandlerContext ctx) {  
	        System.out.println("=======================================");  
	        StockTick.Builder builder = StockTick.newBuilder();
	        builder.setPrice(200);
	        builder.setStockId("600871");
	        ctx.writeAndFlush(builder.build());  
	    }  
	  
	  
	    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		Result.ResultMsg  result= (Result.ResultMsg)msg;
		System.out.println("收到服务端回复："+result.getId() +"  "+result.getFlag() +"  "+result.getLength());
	}


		@Override  
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
	        cause.printStackTrace();  
	        ctx.close();  
	    }  

}
