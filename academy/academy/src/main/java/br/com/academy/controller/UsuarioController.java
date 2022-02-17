package br.com.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Usuario;
import br.com.academy.repository.UsuarioRepository;
import br.com.academy.service.UsuarioService;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView Cadastrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/cadastro");
		return mv;
	}
	
	@PostMapping("/salvarUsuario")
	public ModelAndView SalvarCadastro(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		usuarioService.salvarUsuario(usuario);
		mv.setViewName("redirect:/");
		return mv;
	}  
}
