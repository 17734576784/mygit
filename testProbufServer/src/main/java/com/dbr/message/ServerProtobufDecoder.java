/**
 * 
 */
package com.dbr.message;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import com.google.protobuf.MessageLite;

/**
 * @ClassName: CustomProtobufDecoder
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: dbr
 * @date: 2018年8月24日 下午2:47:59
 * 
 */
public class ServerProtobufDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.
	 * ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		while (in.readableBytes() > 4) { // 如果可读长度小于包头长度，退出。
			
			//我们标记一下当前的readIndex的位置
			in.markReaderIndex();  

			// 获取包头中的body长度
			byte low = in.readByte();
			byte high = in.readByte();
			short s0 = (short) (low & 0xff);
			short s1 = (short) (high & 0xff);
			s1 <<= 8;
			short length = (short) (s0 | s1);
			System.out.println("server body length "+ length);

			// 获取包头中的protobuf类型
			in.readByte();
			byte dataType = in.readByte();

			// 如果可读长度小于body长度，恢复读指针，退出。
			if (in.readableBytes() < length) {
				in.resetReaderIndex();
				return;
			}

			// 读取body
			ByteBuf bodyByteBuf = in.readBytes(length);

			byte[] array;
			int offset;

			int readableLen = bodyByteBuf.readableBytes();
			if (bodyByteBuf.hasArray()) {
				array = bodyByteBuf.array();
				offset = bodyByteBuf.arrayOffset() + bodyByteBuf.readerIndex();
			} else {
				array = new byte[readableLen];
				bodyByteBuf.getBytes(bodyByteBuf.readerIndex(), array, 0, readableLen);
				offset = 0;
			}

			// 反序列化
			MessageLite result = decodeBody(dataType, array, offset, readableLen);
			if (null == result) {
				System.out.println("反序列化为 null");
			}else {
				out.add(result);
			}
		}
	}

	public MessageLite decodeBody(byte dataType, byte[] array, int offset, int length) throws Exception {
		if (dataType == 0x00) {
			return StockTickOuterClass.StockTick.getDefaultInstance().getParserForType().parseFrom(array, offset,
					length);

		} else if (dataType == 0x01) {
			return OptionTickOuterClass.OptionTick.getDefaultInstance().getParserForType().parseFrom(array, offset,
					length);
		}

		return null; // or throw exception
	}
}
