/**
 * 
 */
package com.dbr.message;

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
public class ClientProtobufEncoder extends MessageToByteEncoder<MessageLite> {


	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.
	 * ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		byte[] body = msg.toByteArray();
		System.out.println("客户端编码 ");
        byte[] header = encodeHeader(msg, (short)body.length);

		out.writeBytes(header);
		out.writeBytes(body);

		return;
	}
	
	private byte[] encodeHeader(MessageLite msg, short bodyLength) {
        byte messageType = 0x0f;
        
        if (msg instanceof StockTickOuterClass.StockTick) {
            messageType = 0x00;
        } else if (msg instanceof OptionTickOuterClass.OptionTick) {
            messageType = 0x01;
        }
        
        byte[] header = new byte[4];
        header[0] = (byte) (bodyLength & 0xff);
        header[1] = (byte) ((bodyLength >> 8) & 0xff);
        header[2] = (byte)0; // 保留字段
        header[3] = messageType;

        return header;

    }
}
