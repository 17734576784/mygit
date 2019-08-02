package com.dbr.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbr.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
@Component
public class DirectAckReceiver implements ChannelAwareMessageListener {
	@Autowired
	private ObjectMapper objectMapper;
 
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		try {
//			byte[] body = message.getBody();
// 			User user = objectMapper.readValue(body, User.class);
//			System.out.println("DirectAckReceiver消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
			channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
		} catch (Exception e) {
			channel.basicReject(deliveryTag, true);
			e.printStackTrace();
		}
	}

}
