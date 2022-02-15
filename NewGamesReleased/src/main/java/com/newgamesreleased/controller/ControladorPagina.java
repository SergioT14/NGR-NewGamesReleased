package com.newgamesreleased.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newgamesreleased.model.*;
import com.newgamesreleased.repository.*;

@Controller
public class ControladorPagina {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	
	@PostConstruct
	public void init() {
		postRepository.save(new Post("Post 1", "Hola que tal"));
		postRepository.save(new Post("Post 2", "Adios"));
	}
	
	@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model modelo) {
		
		modelo.addAttribute("tipo","inicio");
		modelo.addAttribute("contenido","los posts más recientes");
		modelo.addAttribute("posts", postRepository.findAll());
		
		return "index";
	}
	
	@GetMapping("/crear-post")
	public String crearpost(Model modelo) {
		
		modelo.addAttribute("tipo","añadir posts");
		
		return "post/nuevo_post";
	}
	

	@GetMapping("/etiquetas")
	public String etiquetas(Model modelo) {
		
		modelo.addAttribute("tipo","etiquetas");
		modelo.addAttribute("contenido","todas las etiquetas para buscar de manera filtrada");
		
		return "etiquetas/etiquetas";
	}
	
	@GetMapping("/buscar")
	public String buscar(Model modelo, @RequestParam String texto) {
		
		modelo.addAttribute("tipo","búsqueda");
		modelo.addAttribute("what",texto);
		modelo.addAttribute("contenido","los resultados de la búsqueda realizada");
		
		return "buscar";
	}
	
	@GetMapping("/post/{id}")
	public String verPost(Model model, @PathVariable long id){
		
		Post p = postRepository.findById(id).get();
		
		model.addAttribute("post",p);
		
		return "post/ver_post";
	}
	
	@PostMapping("/postnuevo")
	public String postCreate(Model model, Post p) {
		postRepository.save(p);	
		return "post/post_creado";
	}
	
	@GetMapping("/editar-post/{id}")
	public String editarPost(Model model,@PathVariable long id) {
		Post post = postRepository.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("titulo",post.getTitulo());
		model.addAttribute("contenido", post.getContenido());
		
		return "post/editar-post";
	}
	
	@PostMapping("editar-post/cambiarpost/{id}")
	public String modificarContenidoPost(Model model, Post postModificado, @PathVariable long id) {
		postModificado.setId(id);
		postRepository.save(postModificado);
		
		return "post/post_editado";
	}
	
	@GetMapping("/borrar-post/{id}")
	public String deletePost(Model model, @PathVariable long id) {
		postRepository.deleteById(id);
		return "post/post_borrado";
	}
	
	@GetMapping("/crear-valoracion")
	public String creavaloracion(Model modelo) {
		
		modelo.addAttribute("tipo","crear una valoracion");
		
		return "nueva_valoracion";
	}
	
	@PostMapping("/crear-valoracion/{id}")
	public String ratingCreate(Model model, Rating r, @PathVariable long id) {
		ratingRepository.save(r);
		model.addAttribute("id", id);
		return "valoracion_creada";
	}
}
