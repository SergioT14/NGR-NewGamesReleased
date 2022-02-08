package com.example.practica.controladores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.practica.bd.entities.*;
import com.example.practica.bd.interfaces.*;

@Controller
public class ControladorPagina {
	
	@Autowired
	private PostRepository repositorio;
	
	@PostConstruct
	public void init() {
		repositorio.save(new Post("Post 1", "Hola que tal"));
		repositorio.save(new Post("Post 2", "Adios"));
	}
	
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
