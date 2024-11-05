package br.org.unisenaipr.comercial.fabricante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;

@RestController
@RequestMapping("/api/fabricante")
public class FabricanteRestController {
	@Autowired
	FabricanteService fabricanteService;
	
	@GetMapping
	public List<Fabricante> queryFabricantes() {
		return fabricanteService.findAll();
	}
	
	@PostMapping("/new")
	public Fabricante createNew(@RequestBody Fabricante item) {
		Fabricante novo = fabricanteService.saveFabricante(item);
		return novo;
	}
}
