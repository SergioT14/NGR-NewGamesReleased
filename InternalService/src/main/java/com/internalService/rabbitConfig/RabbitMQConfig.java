package com.internalService.rabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private String queue = "postQueue";
	private String exchange = "intercambioPost";
	private String routingKey = "post";
	private String username = "guest";
	private String password = "guest";
	private String host = "ngr-rabbitmq";
	
	@Bean
	Queue crearCola() {
		return new Queue(queue,false);
	}
	
	@Bean
	Exchange crearIntercambiador() {
		return ExchangeBuilder.directExchange(exchange).durable(false).build();
	}
	
	@Bean
	Binding unificador() {
		return BindingBuilder.bind(crearCola()).to(crearIntercambiador()).with(routingKey).noargs();
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}
	
	@Bean
	public MessageConverter jsonMesssageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMesssageConverter());
		return rabbitTemplate;
	}
	
	
}
