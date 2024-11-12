package br.org.unisenaipr.comercial.produto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;
import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private FabricanteService fabricanteService;
	
	@GetMapping("/")
	public String index(Model model) {
		// repassar a lista de grupos para que o template possa exibi-lo
		model.addAttribute("produtos", produtoService.findAll());
		
		return "produto/list";
	}
	
	@GetMapping("/novo")
	public String formularioNovoExibir(Model model) {
		// popular lista de grupos e subgrupos no <select>
		model.addAttribute("grupos", grupoService.findAll());
		model.addAttribute("fabricantes", fabricanteService.findAll());
		
		// aqui definimos o objeto que será modificado no POST subsequente do formulário do template
		model.addAttribute("produto", new Produto());
		
		return "produto/novo";
	}
	
	@PostMapping("/novo")
	public String formularioNovoSubmit(@ModelAttribute("produto") Produto produto) {
		produtoService.saveProduto(produto);
		
		return "redirect:/produto/";
	}
	
	@GetMapping("/atualizar/{id}")
	public String formularioAtualizarExibir(Model model, @PathVariable("id") long id) {
		// popular lista de grupos no <select>
		model.addAttribute("grupos", grupoService.findAll());
		model.addAttribute("fabricantes", fabricanteService.findAll());
		
		// precisamos especificar qual objeto o formulário irá atualizar
		Produto produto = produtoService.findId(id);
		model.addAttribute("produto", produto);
		return "produto/atualizar";
	}
	
	@PostMapping("/atualizar/{id}")
	public String formularioAtualizarSalvar(Model model, @PathVariable("id") long id, @ModelAttribute("produto") Produto produto) {
		produtoService.saveProduto(produto);
		
		return "redirect:/produto/";
	}
	
	@GetMapping("/remover/{id}")
	public String remover(Model model, @PathVariable("id") long id) {
		Produto produto = produtoService.findId(id);
		produtoService.deleteProduto(produto);
		
		return "redirect:/produto/";
	}
}
