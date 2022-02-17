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
	
	@Autowired
	private TagRepository tagRepository;
	
	@PostConstruct
	public void init() {
		postRepository.save(new Post("Post 1", "Hola que tal"));
		postRepository.save(new Post("Post 2", "Adios"));
		tagRepository.save(new Tag("League Of Legends"));
	}
	
	@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model model) {
		
		model.addAttribute("tipo","inicio");
		model.addAttribute("contenido","los posts más recientes");
		model.addAttribute("posts", postRepository.findAll());
		
		return "index";
	}
	
	@GetMapping("/crear-post")
	public String crearpost(Model model) {
		
		model.addAttribute("tipo","añadir posts");
		
		return "post/nuevo_post";
	}
	

	@GetMapping("/etiquetas")
	public String etiquetas(Model model) {
		
		model.addAttribute("tipo","etiquetas");
		model.addAttribute("contenido","todas las etiquetas para buscar de manera filtrada");
		model.addAttribute("tags", tagRepository.findAll());
		
		return "etiquetas/etiquetas";
	}
	
	@GetMapping("/crear-tag")
	public String crearTag(Model model) {
		
		model.addAttribute("tipo","añadir etiquetas");
		
		return "etiquetas/nueva_tag";
	}
	
	@PostMapping("/tagnueva")
	public String tagCreate(Model model, Tag t) {
		tagRepository.save(t);	
		return "etiquetas/tag_creada";
	}
	
	@GetMapping("/borrar-tags/{id}")
	public String deleteTag(Model model, @PathVariable long id) {
		tagRepository.deleteById(id);
		return "etiquetas/tag_borrada";
	}
	
	@GetMapping("/editar-tag/{id}")
	public String editarTag(Model model,@PathVariable long id) {
		Tag t = tagRepository.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("nombre",t.getNombre());
		
		return "etiquetas/editar-tag";
	}
	
	@PostMapping("editar-tag/cambiartag/{id}")
	public String modificarNombreTag(Model model, Tag tagModificada, @PathVariable long id) {
		tagModificada.setId(id);
		tagRepository.save(tagModificada);
		
		return "etiquetas/tag_editada";
	}
	
	/*@GetMapping("/buscar_por_tag/{id}")
	public String buscarPorTag(Model model, @PathVariable long id){
		
		Tag t = tagRepository.findById(id).get();
		
		model.addAttribute("tag",t);
		model.addAttribute("id",id);
		
		return "etiquetas/buscar_por_tag";
	}*/
	
	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String texto) {
		
		model.addAttribute("tipo","búsqueda");
		model.addAttribute("what",texto);
		model.addAttribute("contenido","los resultados de la búsqueda realizada");
		
		return "buscar";
	}
	
	@GetMapping("/post/{id}")
	public String verPost(Model model, @PathVariable long id){
		
		Post p = postRepository.findById(id).get();
		
		model.addAttribute("post",p);
		model.addAttribute("id",id);
		
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
	
	@GetMapping("/post/crear-valoracion/{id}")
	public String creaValoracion(Model model, @PathVariable long id) {
		
		model.addAttribute("tipo","crear una valoracion");
		model.addAttribute("id", id);
		
		return "nueva_valoracion";
	}
	
	@PostMapping("post/crear-valoracion/valoracion-creada/{id}")
	public String ratingCreate(Model model, Rating r, @PathVariable long id) {
		ratingRepository.save(r);
		model.addAttribute("id", id);
		return "valoracion_creada";
	}
}
