package br.org.unisenaipr.comercial.grupo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;
import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;

@RestController
@RequestMapping("/api/grupo")
public class GrupoRestController {
	@Autowired
	GrupoService grupoService;
	
	@GetMapping
	public List<Grupo> queryGrupos() {
		return grupoService.findAll();
	}
}

