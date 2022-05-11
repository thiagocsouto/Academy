package br.com.academy.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.repository.UsuarioRepository;
import br.com.academy.service.ServiceExc;
import br.com.academy.service.UsuarioService;
import br.com.academy.util.Util;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	private String login;

	private String senha; 
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());  
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
	
	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc{
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("login/login");
		}
	
	Usuario usuarioLogin = usuarioService.loginUsuario(usuario.getLogin(),usuario.getSenha());
	if(usuarioLogin == null){
		mv.addObject("msg", "Usuario não encontrado. Tente novamente");
	} else {
	   session.setAttribute("usuarioogado", usuarioLogin);
	   return index();
	}
	
	return mv;
}
	
	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return login();
	}
}

//caso queira colocar criptografia na senha: Util.md5(usuario.getSenha())
	

