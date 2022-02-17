package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

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
		
//		try {
//			
//			if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {
//				throw new EmailExistsException("email já existe no cadastro: "+ usuario.getEmail());
//			}
//			
//			usuario.setSenha(Util.md5(usuario.getSenha()));
//			
//		} catch (NoSuchAlgorithmException e) {
//			
//			throw new CriptoExistException("Erro na criptografia da senha");
//		}
		
		usuarioRepository.save(usuario);  
	}

}
