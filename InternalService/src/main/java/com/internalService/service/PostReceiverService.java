package com.internalService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.internalService.model.*;


@Component
public class PostReceiverService implements RabbitListenerConfigurer{
	
	@Autowired
	private JavaMailSender emailSender;
	

	private static final Logger logger = LoggerFactory.getLogger(PostReceiverService.class);
	
	@RabbitListener(queues = "postQueue")
	public void recibirPost(Post post) {
		logger.info("Se ha recibido el siguiente post:\n " + post);
		logger.info("Enviando correo...");
		
		Tag etiquetaPost = post.getEtiqueta();
		
		List<User> suscritos = etiquetaPost.getSuscritos();
		
		for(User u: suscritos) {
			// Extrae la direccion de correo y envia uno predefinido
			String email = u.getEmail();
			
			SimpleMailMessage mensaje = new SimpleMailMessage();
			mensaje.setFrom("newgamesreleaseddad@gmail.com");
			mensaje.setTo(email);
			mensaje.setSubject("Nuevo post en etiqueta" + etiquetaPost);
			mensaje.setText("Se ha publicado un nuevo post en la etiqueta " + etiquetaPost + " a la que estas sucrito:\n" + post);
			emailSender.send(mensaje);
		}
		
	}
	
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
		
	}
}
