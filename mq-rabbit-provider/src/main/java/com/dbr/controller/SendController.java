package com.dbr.controller;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbr.model.User;

@RestController
public class SendController {
	@Autowired
	private RabbitTemplate template;

	@GetMapping("/sendDirect")
	private String sendDirect(String message) throws Exception {
		for (int i = 0; i < 1000; i++) {
			User user = new User(UUID.randomUUID().toString(), message + "__" + i, "123456", "sendDirect");
			template.convertAndSend("CalonDirectExchange", "CalonDirectRouting", user);
		}
		return "OK,sendDirect:" + message;
	}

	@GetMapping("/sendTopicFirst")
	private String sendTopicFirst(String message) {
		User user = new User(UUID.randomUUID().toString(), message, "123456", "sendTopicFirst");
		template.convertAndSend("topicExchange", "topic.first", user);
		return "OK,sendTopicFirst:" + message;
	}

	@GetMapping("/sendTopicSecond")
	private String sendTopicSecond(String message) {
		User user = new User(UUID.randomUUID().toString(), message, "123456", "sendTopicSecond");
		template.convertAndSend("topicExchange", "topic.second", user);
		return "OK,sendTopicSecond:" + message;
	}

	@GetMapping("/sendFanout")
	private String sendFanout(String message) {
		User user = new User(UUID.randomUUID().toString(), message, "123456", "sendFanout");
		template.convertAndSend("fanoutExchange", null, user);
		return "OK,sendFanout:" + message;
	}
	
}
