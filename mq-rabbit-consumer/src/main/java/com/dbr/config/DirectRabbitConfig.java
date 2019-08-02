package com.dbr.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dbr.receiver.DirectAckReceiver;

@Configuration
public class DirectRabbitConfig {

	// 队列
	@Bean
	public Queue CalonDirectQueue() {
		return new Queue("CalonDirectQueue", true);
	}

	// Direct交换机
	@Bean
	DirectExchange CalonDirectExchange() {
		return new DirectExchange("CalonDirectExchange");
	}

	// 绑定
	@Bean
	Binding bindingDirect() {
		return BindingBuilder.bind(CalonDirectQueue()).to(CalonDirectExchange()).with("CalonDirectRouting");
	}
	
	@Autowired
	private CachingConnectionFactory connectionFactory;
	@Autowired
	private DirectAckReceiver directAckReceiver;// 消息接收处理类

//	@Bean
//	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setConcurrentConsumers(1);
//		container.setMaxConcurrentConsumers(1);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
//
//		container.setQueues(CalonDirectQueue());
//		container.setMessageListener(directAckReceiver);
//		return container;
//	}
}
