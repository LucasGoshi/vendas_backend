package br.org.unisenaipr.comercial.fabricante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;

@RestController
@RequestMapping("/api/fabricante")
@CrossOrigin(origins = "http://localhost:5173") 
public class FabricanteRestController {
    @Autowired
    FabricanteService fabricanteService;
    
    @GetMapping
    public List<Fabricante> queryFabricantes() {
        return fabricanteService.findAll();
    }
    
    @PostMapping("/novo")
    public Fabricante createNew(@RequestBody Fabricante item) {
        return fabricanteService.saveFabricante(item);
    }

    @GetMapping("/{id}")
    public Fabricante getById(@PathVariable Long id) {
        return fabricanteService.findId(id); 
    }
    
    @PutMapping("/atualizar/{id}")
    public Fabricante updateFabricante(@PathVariable Long id, @RequestBody Fabricante updatedItem) {
        updatedItem.setId(id);
        return fabricanteService.updateFabricante(updatedItem);
    }
    
    @DeleteMapping("/remover/{id}")
    public void deleteFabricante(@PathVariable Long id) {
        Fabricante fabricante = fabricanteService.findId(id);
        if (fabricante != null) {
            fabricanteService.deleteFabricante(fabricante); 
        }
    }
}
