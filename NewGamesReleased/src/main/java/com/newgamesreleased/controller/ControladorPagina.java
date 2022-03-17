package com.newgamesreleased.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newgamesreleased.model.*;
import com.newgamesreleased.repository.*;
import com.newgamesreleased.service.PostService;

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
	
	@Autowired
	private PostService postService;

	
	@PostConstruct
	public void init() {
		
		Tag e1 = new Tag("LeagueOfLegends");
		Tag e2 = new Tag("Juegos");
		tagRepository.save(e1);
		tagRepository.save(e2);
		
	}
	
	// Pagina de inicio
	@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model model, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		model.addAttribute("notauser", req.getUserPrincipal()==null);
		model.addAttribute("auser", req.getUserPrincipal()!=null);
		model.addAttribute("admin", req.isUserInRole("ADMIN"));
		
		model.addAttribute("tipo","inicio");
		model.addAttribute("contenido","los posts más recientes");
		model.addAttribute("posts", postRepository.findAll());
		
		return "index";
	}
	
	// Inicio de sesion
	@GetMapping("/login")
	public String login() { 
		return "login";
	}

	// error de inicio de sesion
	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}
	
	//Registro
	@GetMapping("/signup")
	public String registro() {
		return "registro";
	}
	
	//Registro
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	//Registro completado
	@PostMapping("/signup-ok")
	public String registrado(Model model, User usuario) {
		userRepository.save(usuario);
		return "registro_completado";
	}
	
	// Pagina de gestion de etiquetas
	@GetMapping("/etiquetas")
	public String etiquetas(Model model, HttpServletRequest req) {
		model.addAttribute("notauser", req.getUserPrincipal()==null);
		model.addAttribute("auser", req.getUserPrincipal()!=null);
		model.addAttribute("admin", req.isUserInRole("ADMIN"));
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		model.addAttribute("tipo","etiquetas");
		model.addAttribute("contenido","todas las etiquetas para buscar de manera filtrada");
		model.addAttribute("tags", tagRepository.findAll());
		
		return "etiquetas/etiquetas";
	}
	
	// Pagina de creacion de etiquetas
	@GetMapping("/crear-tag")
	public String crearTag(Model model, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		model.addAttribute("tipo","añadir etiquetas");
		
		return "etiquetas/nueva_tag";
	}
	
	// Pagina de creacion de etiquetas (2)
	@PostMapping("/tagnueva")
	public String tagCreate(Model model, Tag t) {
		tagRepository.save(t);	
		
		model.addAttribute("solicitud", "Etiqueta creada correctamente");
		return "etiquetas/solicitud_completada";
	}
	
	// Pagina de borrado de etiquetas
	@GetMapping("/borrar-tags/{id}")
	public String deleteTag(Model model, @PathVariable long id) {
		Tag etiqueta = tagRepository.getById(id);
		
		List<Post> posts = etiqueta.getPosts();
		for(int i = 0; i < posts.size(); i++) 
			posts.get(i).setEtiqueta(null);
			
		List<User> usuarios = etiqueta.getSuscritos();
		User usuario;
		for(int i = 0; i < usuarios.size(); i++) {
			usuario = usuarios.get(i);
			usuario.removeSuscripcion(etiqueta);
			userRepository.save(usuario);
		}
		
		tagRepository.deleteById(id);
		
		model.addAttribute("solicitud", "Etiqueta borrada correctamente");
		return "etiquetas/solicitud_completada";
	}
	
	// Pagina de edicion de etiquetas
	@GetMapping("/editar-tag/{id}")
	public String editarTag(Model model,@PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		Tag t = tagRepository.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("nombre",t.getNombre());
		
		return "etiquetas/editar-tag";
	}
	
	// Pagina de edicion de etiquetas (2)
	@PostMapping("editar-tag/cambiartag/{id}")
	public String modificarNombreTag(Model model, Tag tagModificada, @PathVariable long id) {
		tagModificada.setId(id);
		tagRepository.save(tagModificada);
		
		model.addAttribute("solicitud", "Etiqueta editada correctamente");
		return "etiquetas/solicitud_completada";
	}
	
	// Pagina de buscar por etiqueta
	@GetMapping("/buscar-tag/{id}")
	public String buscarTag(Model model, @PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		model.addAttribute("notauser", req.getUserPrincipal()==null);
		model.addAttribute("auser", req.getUserPrincipal()!=null);
		Tag t = tagRepository.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("tipo","búsqueda por etiquetas");
		model.addAttribute("tag",t.getNombre());
		model.addAttribute("posts",t.getPosts());
					
		return "etiquetas/buscar-tag";
	}
	
	// Pagina de buscar
	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String texto, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		model.addAttribute("notauser", req.getUserPrincipal()==null);
		model.addAttribute("auser", req.getUserPrincipal()!=null);
		
		model.addAttribute("posts",postRepository.findByTituloOrContenido(texto));
		model.addAttribute("ratings",ratingRepository.findByUsuarioOrContenido(texto));
		model.addAttribute("tags",tagRepository.findByNombre(texto));
		model.addAttribute("users",userRepository.findByUsuario(texto));
		model.addAttribute("contenido",texto);
		
		return "buscar";
	}
	
	// Pagina de creacion de post
	@GetMapping("/crear-post")
	public String crearpost(Model model, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
			
		model.addAttribute("tipo","añadir posts");
		model.addAttribute("etiquetas",tagRepository.findAll());
			
		return "post/nuevo_post";
	}
	
	// Pagina de visualizacion de post
	@GetMapping("/post/{id}")
	public String verPost(Model model, @PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		Post p = postRepository.findById(id).get();
		if(p.getEtiqueta() == null) 
			model.addAttribute("etiqueta", "None");
		else
			model.addAttribute("etiqueta", p.getEtiqueta());
		
		model.addAttribute("post",p);
		model.addAttribute("idPost",id);
		model.addAttribute("valoraciones", p.getValoraciones());
		
		return "post/ver_post";
	}
	
	// Pagina de creacion de post
	@PostMapping("/postnuevo")
	public String postCreate(Model model, @RequestParam String titulo, 
			@RequestParam String contenido , @RequestParam String etiqueta) {
		Post p = new Post(titulo, contenido);
		Tag e = tagRepository.getByNombre(etiqueta);
		e.addPost(p);
		postRepository.save(p);	
		
		postService.enviar(p);
		
		model.addAttribute("solicitud", "Post creado correctamente");
		return "post/solicitud_completada";
	}
	
	// Pagina de edicion de post
	@GetMapping("/editar-post/{id}")
	public String editarPost(Model model,@PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		Post post = postRepository.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("titulo",post.getTitulo());
		model.addAttribute("contenido", post.getContenido());
		
		return "post/editar-post";
	}
	
	// Pagina de edicion de post (2)
	@PostMapping("editar-post/cambiarpost/{id}")
	public String modificarContenidoPost(Model model, Post postModificado, @PathVariable long id) {
		Post postAntiguo = postRepository.getById(id);
		Tag etiqueta = postAntiguo.getEtiqueta();
		etiqueta.removePost(postAntiguo);
		
		etiqueta.addPost(postModificado);
		postModificado.setId(id);
		postRepository.save(postModificado);
		
		
		model.addAttribute("solicitud", "Post editado correctamente");
		return "post/solicitud_completada";
	}
	
	// Pagina de borrado de post
	@GetMapping("/borrar-post/{id}")
	public String deletePost(Model model, @PathVariable long id) {
		Post p = postRepository.getById(id);
		Tag etiqueta = p.getEtiqueta();
		etiqueta.removePost(p);
		
		postRepository.deleteById(id);
		
		model.addAttribute("solicitud", "Post borrado correctamente");
		return "post/solicitud_completada";
	}
	
	// Pagina de creacion de valoracion
	@GetMapping("/post/crear-valoracion/{id}")
	public String creaValoracion(Model model, @PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		model.addAttribute("tipo","crear una valoracion");
		model.addAttribute("id", id);
		model.addAttribute("usuarios",userRepository.findAll());
		
		return "valoraciones/nueva_valoracion";
	}
	
	// Pagina de creacion de valoracion (2)
	@PostMapping("post/crear-valoracion/valoracion-creada/{id}")
	public String ratingCreate(Model model, @PathVariable long id, @RequestParam String texto, 
			@RequestParam String puntuacion , @RequestParam String usuario) {
		
		int punt = Integer.parseInt(puntuacion);
		User u = userRepository.getByNombre(usuario);
		Rating r = new Rating(u,texto,punt);
		u.addRating(r);
		
		Post post = postRepository.getById(id);
		List<Rating> valoraciones = post.getValoraciones();
		valoraciones.add(r);
		
		ratingRepository.save(r);
		model.addAttribute("id", id);
		model.addAttribute("solicitud", "Valoracion creada correctamente");
		return "valoraciones/solicitud_completada";
	}
	
	// Pagina de borrado de valoraciones
	@GetMapping("post/borrar-valoracion/{idPost}/{idValoracion}")
	public String borrarValoracion(Model model, @PathVariable long idPost, @PathVariable long idValoracion) {
		Post p = postRepository.getById(idPost);
		p.getValoraciones().remove(ratingRepository.getById(idValoracion));
		
		ratingRepository.deleteById(idValoracion);
		model.addAttribute("id",idPost);
		
		model.addAttribute("solicitud", "Valoracion borrada correctamente");
		return "valoraciones/solicitud_completada";
	}
	
	// Pagina de gestion de usuarios
	@GetMapping("/usuarios")
	public String usuarios(Model model, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		model.addAttribute("notauser", req.getUserPrincipal()==null);
		model.addAttribute("auser", req.getUserPrincipal()!=null);
		
		model.addAttribute("tipo","usuarios");
		model.addAttribute("contenido", "todos los usuarios de la aplicacion");
		model.addAttribute("usuarios", userRepository.findAll());
		
		return "usuarios/usuarios";
	}
	
	// Pagina de borrado de usuarios
	@GetMapping("/usuarios/borrar-usuario/{id}")
	public String borrarUsuario(Model model, @PathVariable long id) {
		userRepository.deleteById(id);
		model.addAttribute("solicitud", "Usuario eliminado correctamente");
		return "usuarios/solicitud_completada";
	}
	
	// Pagina de edicion de usuario
	@GetMapping("/usuarios/editar-usuario/{id}")
	public String editarUsuario(Model model, @PathVariable long id, HttpServletRequest req) {
		String nombre = req.getUserPrincipal().getName();
		User u = userRepository.getByNombre(nombre);

		model.addAttribute("nombreuser", u.getNombre());
		
		User usuario = userRepository.getById(id);
		
		model.addAttribute("id", id);
		model.addAttribute("nombre", usuario.getNombre());
		model.addAttribute("contrasenya", usuario.getContrasenya());
		model.addAttribute("email", usuario.getEmail());
		
		return "usuarios/editar_usuario";
	}
	
	// Pagina de edicion de usuario
	@PostMapping("/usuarios/editar-usuario/usuario-editado/{id}")
	public String usuarioEditado(Model model, User usuarioModificado, @PathVariable long id) {
		usuarioModificado.setId(id);
		userRepository.save(usuarioModificado);
		
		model.addAttribute("solicitud", "Usuario modificado correctamente");
		return "usuarios/solicitud_completada";
	}
	
	// Pagina de suscripcion
	@PostMapping("/suscribirse/{id}")
	public String suscribirseAEtiqueta(Model model, @PathVariable long id) {
		Tag etiqueta = tagRepository.findById(id).get();
		User usuarioAdmin = userRepository.findById((long)3).get();
		usuarioAdmin.addSuscripcion(etiqueta);
		userRepository.save(usuarioAdmin);
		tagRepository.save(etiqueta);
		
		model.addAttribute("solicitud", "Suscripcion realizada con exito");
		
		return "etiquetas/solicitud_completada";
	}
	
	// Pagina de desuscripcion
	@PostMapping("/desuscribirse/{id}")
	public String desuscribirseDeEtiqueta(Model model, @PathVariable long id) {
		Tag etiqueta = tagRepository.findById(id).get();
		User usuarioAdmin = userRepository.findById((long)3).get();
		usuarioAdmin.removeSuscripcion(etiqueta);
		userRepository.save(usuarioAdmin);
		tagRepository.save(etiqueta);
		
		model.addAttribute("solicitud", "Desuscripcion realizada con exito");
		
		return "etiquetas/solicitud_completada";
	}
}