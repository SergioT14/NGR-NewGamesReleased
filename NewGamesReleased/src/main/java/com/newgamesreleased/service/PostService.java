package com.newgamesreleased.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newgamesreleased.model.Post;

@Service
public class PostService {
	
	private String exchange = "intercambioPost";
	private String routingKey = "post";
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public PostService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void enviar(Post post) {
		rabbitTemplate.convertAndSend(exchange, routingKey, post);
	}
	
	
}
