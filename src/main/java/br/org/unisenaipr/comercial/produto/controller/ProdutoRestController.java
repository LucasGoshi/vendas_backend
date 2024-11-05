package br.org.unisenaipr.comercial.produto.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;
import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;
import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.service.VendaService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> queryProdutos() {
		return produtoService.findAll();
	}
}