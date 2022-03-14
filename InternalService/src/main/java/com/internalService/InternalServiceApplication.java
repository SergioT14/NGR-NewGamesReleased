package com.internalService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class InternalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternalServiceApplication.class, args);
	}

}
