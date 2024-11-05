package br.org.unisenaipr.comercial.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.usuario.entity.Usuario;
import br.org.unisenaipr.comercial.usuario.repositorio.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> login(String username, String password) {
		List<Usuario> users = usuarioRepository.findAll();
		
		for (Usuario user : users) {
			if ((user.getUsuario().equals(username) || user.getEmail().equals(username))
					&& user.getSenha().equals(password)) {
				return Optional.of(user);
			}
		}
		
		return Optional.empty();
	}
}
