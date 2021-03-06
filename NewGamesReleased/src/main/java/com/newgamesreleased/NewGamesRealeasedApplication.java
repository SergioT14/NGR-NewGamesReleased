package com.newgamesreleased;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
public class NewGamesRealeasedApplication {
	
	private static final Log LOG = LogFactory.getLog(NewGamesRealeasedApplication.class);
	
	private final String host = "ngr-rabbitmq";
	private final String username = "guest";
	private final String password = "guest";

	public static void main(String[] args) {
		SpringApplication.run(NewGamesRealeasedApplication.class, args);
	}
	
	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}
	
	@Bean
	public MessageConverter jsonConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonConverter());
		return rabbitTemplate;
	}

	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig jConfig = config.getNetworkConfig().getJoin();
		jConfig.getMulticastConfig().setEnabled(false);
		//Lista de servidores para balancear la carga
		//ampliable si se a??aden m??s al haproxy
		List<String> instanciasWeb = new ArrayList<>();
		instanciasWeb.add("ngr-app-1");
		instanciasWeb.add("ngr-app-2");
		jConfig.getTcpIpConfig().setEnabled(true).setMembers(instanciasWeb);
		return config;
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("\tACTIVANDO LA CACH??...");
		return new ConcurrentMapCacheManager("posts");
	}
}
