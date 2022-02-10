package com.example.practica.controladores;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
		modelo.addAttribute("feed", feed());
		
		return "PlantillaInicio";
	}
	
	@GetMapping("/crear-post")
	public String crearpost(Model modelo) {
		
		modelo.addAttribute("tipo","añadir posts");
		
		return "PlantillaNewPost";
	}
	
	public List<Post> feed(){
		return repositorio.findAll();
	}
	
	@PostMapping("/postnuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public String postCreate(@RequestBody Post p) {
		repositorio.save(p);
		return "postcreado";
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
	
	@GetMapping("/post{id}")
	public ResponseEntity<Post> verPost(@PathVariable long id){
		Optional <Post> p = repositorio.findById(id);
		
		if(p.isPresent())return ResponseEntity.ok(p.get());
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
