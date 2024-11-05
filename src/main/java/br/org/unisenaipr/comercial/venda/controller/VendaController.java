package br.org.unisenaipr.comercial.venda.controller;

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
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;
import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.service.VendaService;

@Controller
@RequestMapping("/venda")
public class VendaController {
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private ProdutoService produtoService;
	

	@Autowired
	public VendaController(VendaService vendaService, ProdutoService produtoService) {
		this.vendaService = vendaService;
		this.produtoService = produtoService;
	}
	
	
	@GetMapping("/")
	public String index(Model model) {
		// repassar a lista de vendas para que o template possa exibi-lo
		model.addAttribute("vendas", vendaService.findAll());
		
		return "venda/list";
	}
	
	@GetMapping("/novo")
	public String formularioNovoExibir(Model model) {
		model.addAttribute("produtos", produtoService.findAll());;
		
		// aqui definimos o objeto que será modificado no POST subsequente do formulário do template
		model.addAttribute("venda", new Venda());
		
		return "venda/novo";
	}
	
	@PostMapping("/novo")
	public String formularioNovoSubmit(@ModelAttribute("venda") Venda venda) {
		vendaService.saveVenda(venda);
		
		return "redirect:/venda/";
	}
	
	@GetMapping("/atualizar/{id}")
	public String formularioAtualizarExibir(Model model, @PathVariable("id") long id) {
		// popular lista de grupos no <select>
		model.addAttribute("vendas", vendaService.findAll());
		model.addAttribute("produtos", produtoService.findAll());
		
		// precisamos especificar qual objeto o formulário irá atualizar
		Venda venda = vendaService.findId(id);
		model.addAttribute("venda", venda);
		return "venda/atualizar";
	}
	
	@PostMapping("/atualizar/{id}")
	public String formularioAtualizarSalvar(Model model, @PathVariable("id") long id, @ModelAttribute("venda") Venda venda) {
		vendaService.saveVenda(venda);
		
		return "redirect:/venda/";
	}
	
	@GetMapping("/remover/{id}")
	public String remover(Model model, @PathVariable("id") long id) {
		Venda venda = vendaService.findId(id);
		vendaService.deleteVenda(venda);
		
		return "redirect:/venda/";
	}
}
