package br.org.unisenaipr.comercial.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.repositorio.FabricanteRepository;
import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.repositorio.GrupoRepository;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.repositorio.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FabricanteRepository fabricanteRepository;
    private final GrupoRepository grupoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, 
                          FabricanteRepository fabricanteRepository, 
                          GrupoRepository grupoRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.grupoRepository = grupoRepository;
    }

    public Produto saveProduto(Produto produto) {
        // Validação do ID Fabricante
        if (produto.getFabricante() != null) {
            Optional<Fabricante> fabricante = fabricanteRepository.findById(produto.getFabricante().getId());
            produto.setFabricante(fabricante.orElseThrow(() -> new RuntimeException("Fabricante não encontrado")));
        }

        // Validação do ID Grupo
        if (produto.getGrupo() != null) {
            Optional<Grupo> grupo = grupoRepository.findById(produto.getGrupo().getId());
            produto.setGrupo(grupo.orElseThrow(() -> new RuntimeException("Grupo não encontrado")));
        }

        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Produto produto) {
        return saveProduto(produto); 
    }

    public void deleteProduto(Produto produto) {
        produtoRepository.delete(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findId(long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
