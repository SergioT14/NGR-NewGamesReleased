package com.example.practica.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

	@GetMapping("/mustache")
	public String saludoMustache() {
		
		return "miplantilla";
	}
}
