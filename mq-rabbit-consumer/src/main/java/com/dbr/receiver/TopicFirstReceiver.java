package com.dbr.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dbr.model.User;

@Component
@RabbitListener(queues = "topic.first")
public class TopicFirstReceiver {
	
	@RabbitHandler
    public void process(User user) {
		System.out.println("TopicFirstReceiver消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }
}
