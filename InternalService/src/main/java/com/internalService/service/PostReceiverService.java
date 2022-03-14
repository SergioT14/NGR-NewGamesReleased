package com.internalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import com.internalService.model.Post;

@Component
public class PostReceiverService implements RabbitListenerConfigurer{

	private static final Logger logger = LoggerFactory.getLogger(PostReceiverService.class);
	
	@RabbitListener(queues = "postQueue")
	public void recibirPost(Post post) {
		logger.info("Se ha recibido el siguiente post: " + post);
	}
	
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
		
	}
}
