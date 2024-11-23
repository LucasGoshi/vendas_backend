package br.org.unisenaipr.comercial.infra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.unisenaipr.comercial.infra.utils.LoginFormPayload;
import br.org.unisenaipr.comercial.usuario.entity.Usuario;
import br.org.unisenaipr.comercial.usuario.service.UsuarioService;
import jakarta.servlet.http.HttpSession;


@Controller
public class IndexController {
	@Autowired
	private UsuarioService usuarioService;
	
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	model.addAttribute("userId", session.getAttribute("userId"));

        return "index"; // index.html no diretório templates
    }
    
    @GetMapping("/login")
    public String loginFormView(Model model) {
    	model.addAttribute("loginFormPayload", new LoginFormPayload("", ""));
    	model.addAttribute("erroLogin", null);
    	
    	return "login";
    }
    
    @PostMapping("/login")
    public String loginFormSubmit(Model model, @ModelAttribute("loginFormPayload") LoginFormPayload payload, HttpSession session) {
    	Optional<Usuario> usuarioLogado = usuarioService.login(payload.getUsername(), payload.getPassword());
    	
    	if (usuarioLogado.isEmpty()) {
    		// limpar campos dos formulários
    		// (aparentemente o thymeleaf faz uma persistência dos objetos contidos no Model)
    		payload.setUsername("");
    		payload.setUsername("");
    		model.addAttribute("erroLogin", "Erro de usuário ou senha");
    		return "login";
    	}
    	
    	session.setAttribute("currentUser", usuarioLogado.get());
    	
    	return "redirect:/";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();

		return "logout";
    }
}
