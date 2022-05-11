package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.exception.CriptoExistException;
import br.com.academy.exception.EmailExistsException;
import br.com.academy.model.Usuario;
import br.com.academy.repository.UsuarioRepository;
import br.com.academy.util.Util;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvarUsuario(Usuario usuario) throws Exception{
		
		if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {
			throw new EmailExistsException("email já existe no cadastro: "+ usuario.getEmail());
		}
		
		usuario.setSenha(usuario.getSenha());
		
		usuarioRepository.save(usuario);  
	}
	
	
	public Usuario loginUsuario(String login, String senha) throws ServiceExc{
		
		Usuario usuarioLogin = usuarioRepository.buscarLogin(login, senha);
		return usuarioLogin;
		
	}

}

//Caso queira usar criptografia na senha:
//try {
//	
//	if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {
//		throw new EmailExistsException("email já existe no cadastro: "+ usuario.getEmail());
//	}
//	
//	usuario.setSenha(usuario.getSenha());
//	
//} catch (NoSuchAlgorithmException e) {
//	
//	throw new CriptoExistException("Erro na criptografia da senha");
//}
