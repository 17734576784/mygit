/**
 * 
 */
package com.netty.server;
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
import java.util.List;

import com.dbr.message.OptionTickOuterClass;
import com.dbr.message.OptionTickOuterClass.OptionTick;
import com.dbr.message.Result;
import com.dbr.message.StockTickOuterClass;
import com.dbr.message.StockTickOuterClass.StockTick;  
/**   
 * @ClassName:  ProtoBufServerHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年8月24日 下午2:51:31   
 *      
 */
public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter{
	 @Override  
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
		 
		   if (msg instanceof StockTickOuterClass.StockTick) {
			 	StockTick req = (StockTick)msg;
			 	System.out.println(req.getStockId() +" 现价  ： "+req.getPrice());

	        } else if (msg instanceof OptionTickOuterClass.OptionTick) {
	        	OptionTick req = (OptionTick)msg;
			 	System.out.println(req.getOptionId() +" 现价  ： "+req.getPrice() +"  securityId_ "+req.getSecurityId());
	        }
		   
		   Result.ResultMsg.Builder builder = Result.ResultMsg.newBuilder();
		   builder.setId("Ke0001");
		   builder.setFlag(1);
//		   builder.setLength(10);
		   
		   ctx.writeAndFlush(builder.build());
	    }  
	  
	  
	    @Override  
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
	        cause.printStackTrace();  
	        ctx.close();   
	    }  
}
