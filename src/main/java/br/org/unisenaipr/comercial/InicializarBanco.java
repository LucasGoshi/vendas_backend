package br.org.unisenaipr.comercial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.org.unisenaipr.comercial.usuario.entity.Usuario;
import br.org.unisenaipr.comercial.usuario.repositorio.UsuarioRepository;

// Essa classe busca criar um usuário admin padrão para que o sistema possa ser configurado a partir de um banco de dados vazio.
// Ela implementa o CommandLineRunner, sendo executada logo após a aplicação spring inicializar.

@Component
public class InicializarBanco implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// O usuário "system" possui id=1, sempre.
		
		createUser(new Usuario(1, "system", "system@localhost.local", "system", true));
		createUser(new Usuario(2, "vendedor", "vendedor@localhost.local", "vendedor", false));
	}
	
	private void createUser(Usuario user) {
		Optional<Usuario> userExistente = usuarioRepository.findById(user.getId());
		
		// Só criamos uma conta se não existir uma atual
		if (userExistente.isEmpty()) {
			usuarioRepository.save(user);
		}
	}
}
