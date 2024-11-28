package br.org.unisenaipr.comercial.grupo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;

@RestController
@RequestMapping("/api/grupo")
@CrossOrigin(origins = "http://localhost:5173") 
public class GrupoRestController {
    @Autowired
    GrupoService grupoService;

    @GetMapping
    public List<Grupo> queryGrupos() {
        return grupoService.findAll();
    }

    @PostMapping("/novo")
    public Grupo createNew(@RequestBody Grupo grupo) {
        return grupoService.saveGrupo(grupo); 
    }

    @GetMapping("/{id}")
    public Grupo getById(@PathVariable Long id) {
        return grupoService.findId(id); 
    }

    @PutMapping("/atualizar/{id}")
    public Grupo updateGrupo(@PathVariable Long id, @RequestBody Grupo updatedGrupo) {
        updatedGrupo.setId(id);
        return grupoService.updateGrupo(updatedGrupo); 
    }

    @DeleteMapping("/remover/{id}")
    public void deleteGrupo(@PathVariable Long id) {
        Grupo grupo = grupoService.findId(id);
        if (grupo != null) {
            grupoService.deleteGrupo(grupo); 
        }
    }
}
