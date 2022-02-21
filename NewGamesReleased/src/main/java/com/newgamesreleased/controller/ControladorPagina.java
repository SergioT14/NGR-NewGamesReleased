package com.newgamesreleased.controller;

import java.util.List;

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
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		postRepository.save(new Post("Post 1", "Hola que tal"));
		postRepository.save(new Post("Post 2", "Adios"));
		
		tagRepository.save(new Tag("League Of Legends"));
		
		userRepository.save(new User("admin","0000"));
		userRepository.save(new User("user","1234"));
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
		
		model.addAttribute("solicitud", "Etiqueta creada correctamente");
		return "etiquetas/solicitud_completada";
	}
	
	@GetMapping("/borrar-tags/{id}")
	public String deleteTag(Model model, @PathVariable long id) {
		tagRepository.deleteById(id);
		
		model.addAttribute("solicitud", "Etiqueta borrada correctamente");
		return "etiquetas/solicitud_completada";
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
		
		model.addAttribute("solicitud", "Etiqueta editada correctamente");
		return "etiquetas/solicitud_completada";
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
		model.addAttribute("posts",postRepository.findByTituloOrContenido(texto));
		model.addAttribute("ratings",ratingRepository.findByUsuarioOrContenido(texto));
		model.addAttribute("tags",tagRepository.findByNombre(texto));
		model.addAttribute("users",userRepository.findByUsuario(texto));
		model.addAttribute("contenido","los resultados de la búsqueda realizada");
		
		return "buscar";
	}
	
	@GetMapping("/post/{id}")
	public String verPost(Model model, @PathVariable long id){
		
		Post p = postRepository.findById(id).get();
		
		model.addAttribute("post",p);
		model.addAttribute("idPost",id);
		model.addAttribute("valoraciones", p.getValoraciones());
		
		return "post/ver_post";
	}
	
	@PostMapping("/postnuevo")
	public String postCreate(Model model, Post p) {
		postRepository.save(p);	

		model.addAttribute("solicitud", "Post creado correctamente");
		return "post/solicitud_completada";
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
		
		model.addAttribute("solicitud", "Post editado correctamente");
		return "post/solicitud_completada";
	}
	
	@GetMapping("/borrar-post/{id}")
	public String deletePost(Model model, @PathVariable long id) {
		postRepository.deleteById(id);
		
		model.addAttribute("solicitud", "Post borrado correctamente");
		return "post/solicitud_completada";
	}
	
	@GetMapping("/post/crear-valoracion/{id}")
	public String creaValoracion(Model model, @PathVariable long id) {
		
		model.addAttribute("tipo","crear una valoracion");
		model.addAttribute("id", id);
		model.addAttribute("usuarios",userRepository.findAll());
		
		return "valoraciones/nueva_valoracion";
	}
	
	@PostMapping("post/crear-valoracion/valoracion-creada/{id}")
	public String ratingCreate(Model model, Rating r, @PathVariable long id) {
		Post post = postRepository.getById(id);
		List<Rating> valoraciones = post.getValoraciones();
		valoraciones.add(r);
		
		ratingRepository.save(r);
		model.addAttribute("id", id);
		model.addAttribute("solicitud", "Valoracion creada correctamente");
		return "valoraciones/solicitud_completada";
	}
	
	@GetMapping("post/borrar-valoracion/{idPost}/{idValoracion}")
	public String borrarValoracion(Model model, @PathVariable long idPost, @PathVariable long idValoracion) {
		Post p = postRepository.getById(idPost);
		p.getValoraciones().remove(ratingRepository.getById(idValoracion));
		
		ratingRepository.deleteById(idValoracion);
		model.addAttribute("id",idPost);
		
		model.addAttribute("solicitud", "Valoracion borrada correctamente");
		return "valoraciones/solicitud_completada";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("tipo","usuarios");
		model.addAttribute("contenido", "todos los usuarios de la aplicacion");
		model.addAttribute("usuarios", userRepository.findAll());
		
		return "usuarios/usuarios";
	}
	
	@GetMapping("/usuarios/registrar-usuario")
	public String registrarUsuario(Model model) {
		model.addAttribute("tipo","registro de usuarios");
		
		return "usuarios/registrar_usuario";
	}
	
	@PostMapping("/usuarios/usuario-registrado")
	public String usuarioRegistrado(Model model, User usuario) {
		userRepository.save(usuario);
		model.addAttribute("solicitud", "Usuario registrado correctamente");
		return "usuarios/solicitud_completada";
	}
	
	@GetMapping("/usuarios/borrar-usuario/{id}")
	public String borrarUsuario(Model model, @PathVariable long id) {
		userRepository.deleteById(id);
		model.addAttribute("solicitud", "Usuario eliminado correctamente");
		return "usuarios/solicitud_completada";
	}
	
	@GetMapping("/usuarios/editar-usuario/{id}")
	public String editarUsuario(Model model, @PathVariable long id) {
		User usuario = userRepository.getById(id);
		
		model.addAttribute("id", id);
		model.addAttribute("nombre", usuario.getNombre());
		model.addAttribute("contrasenya", usuario.getContrasenya());
		
		return "usuarios/editar_usuario";
	}
	
	@PostMapping("/usuarios/editar-usuario/usuario-editado/{id}")
	public String usuarioEditado(Model model, User usuarioModificado, @PathVariable long id) {
		usuarioModificado.setId(id);
		userRepository.save(usuarioModificado);
		
		model.addAttribute("solicitud", "Usuario modificado correctamente");
		return "usuarios/solicitud_completada";
	}
}
