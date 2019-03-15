/**
 * 
 */
package com.pile.netty.message;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName: CustomProtobufEncoder
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年8月24日 下午2:41:42
 * 
 */
@Sharable
public class ServerProtobufEncoder extends MessageToByteEncoder<MessageLite> {


	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.
	 * ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
		
		byte[] body = msg.toByteArray();
		out.writeBytes(body);

		return;
	}
	 
}
