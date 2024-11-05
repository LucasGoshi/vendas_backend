package br.org.unisenaipr.comercial.grupo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;

@Controller
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService grupoService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Grupo> grupos = grupoService.findAll();
		// repassar a lista de grupos para que o template possa exibi-lo
		model.addAttribute("grupos", grupos);
		
		return "grupo/list";
	}
	
	@GetMapping("/novo")
	public String formularioNovoExibir(Model model) {
		List<Grupo> grupos = grupoService.findAll();
		
		// aqui definimos o objeto que será modificado no POST subsequente do formulário do template
		model.addAttribute("grupo", new Grupo());
		model.addAttribute("grupos", grupos);
		
		return "grupo/novo";
	}
	
	@PostMapping("/novo")
	public String formularioNovoSubmit(@ModelAttribute("grupo") Grupo grupo) {
		grupoService.saveGrupo(grupo);
		
		return "redirect:/grupo/";
	}
	
	@GetMapping("/atualizar/{id}")
	public String formularioAtualizarExibir(Model model, @PathVariable("id") long id) {
		// precisamos especificar qual objeto o formulário irá atualizar
		Grupo grupo = grupoService.findId(id);
		
		model.addAttribute("grupo", grupo);
		model.addAttribute("grupos", grupoService.findAll());
		
		return "grupo/atualizar";
	}
	
	@PostMapping("/atualizar/{id}")
	public String formularioAtualizarSalvar(Model model, @PathVariable("id") long id, @ModelAttribute("grupo") Grupo grupo) {
		grupoService.saveGrupo(grupo);
		
		return "redirect:/grupo/";
	}
	
	@GetMapping("/remover/{id}")
	public String remover(Model model, @PathVariable("id") long id) {
		Grupo grupo = grupoService.findId(id);
		grupoService.deleteGrupo(grupo);
		
		return "redirect:/grupo/";
	}
}
