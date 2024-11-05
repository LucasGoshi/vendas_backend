package br.org.unisenaipr.comercial.venda.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;
import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.service.VendaService;


@RestController
@RequestMapping("/api/venda")
public class VendaRestController {
	@Autowired
	VendaService vendaService;

	@GetMapping
	public List<Venda> queryVendas(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date data) {
		if (data == null) {
			return vendaService.findAll();
		} else {
			return vendaService.findAllInDate(data);
		}
	}
}
