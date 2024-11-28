package br.org.unisenaipr.comercial.produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "http://localhost:5173") 
public class ProdutoRestController {
    @Autowired
    ProdutoService produtoService;
    
    @GetMapping
    public List<Produto> queryProdutos() {
        return produtoService.findAll();
    }
    
    @PostMapping("/novo")
    public Produto createNew(@RequestBody Produto produto) {
        return produtoService.saveProduto(produto); 
    }

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Long id) {
        return produtoService.findId(id); 
    }

    @PutMapping("/atualizar/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto updatedProduto) {
        updatedProduto.setId(id);
        return produtoService.updateProduto(updatedProduto);
    }

    @DeleteMapping("/remover/{id}")
    public void deleteProduto(@PathVariable Long id) {
        Produto produto = produtoService.findId(id);
        if (produto != null) {
            produtoService.deleteProduto(produto); 
        }
    }
}
