/**
 * 
 */
package com.dbr.message;

import java.util.List;

import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @ClassName: ClientProtobufDecoder
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年8月25日 上午10:11:18
 * 
 */
public class ClientProtobufDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.
	 * ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() > 4) {
			
			byte[] array;
			int offset;
			int readableLen = in.readableBytes();

			// 读取返回结果
			ByteBuf response = in.readBytes(readableLen);

			if (response.hasArray()) {
				array = response.array();
				offset = response.arrayOffset() + response.readerIndex();
			} else {
				array = new byte[readableLen];
				response.getBytes(response.readerIndex(), array, 0, readableLen);
				offset = 0;
			}

			// 反序列化
			MessageLite result = Result.ResultMsg.getDefaultInstance().getParserForType().parseFrom(array, offset,
					response.readableBytes());
			out.add(result);
//	        in.skipBytes(in.readableBytes());

			
		}
		
	}

}
