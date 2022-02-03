package com.example.practica.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controlador {
	
	@GetMapping("/")
	public String inicio(Model modelo) {
		
		modelo.addAttribute("tipo","inicio");
		modelo.addAttribute("contenido","los posts más recientes");
		
		return "PlantillaInicio";
	}
	
	@GetMapping("/crear-post")
	public String crearpost(Model modelo) {
		
		modelo.addAttribute("tipo","añadir posts");
		
		
		return "PlantillaNewPost";
	}

	@GetMapping("/etiquetas")
	public String etiquetas(Model modelo) {
		
		modelo.addAttribute("tipo","etiquetas");
		modelo.addAttribute("contenido","todas las etiquetas para buscar de manera filtrada");
		
		return "PlantillaEtiquetas";
	}
	
	@GetMapping("/buscar")
	public String buscar(Model modelo, @RequestParam String texto) {
		
		modelo.addAttribute("tipo","búsqueda");
		modelo.addAttribute("what",texto);
		modelo.addAttribute("contenido","los resultados de la búsqueda realizada");
		
		return "PlantillaBuscar";
	}
}
