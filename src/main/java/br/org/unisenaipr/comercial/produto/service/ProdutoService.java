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
        // Verifica e associa o objeto Fabricante
        if (produto.getFabricante() != null) {
            Optional<Fabricante> fabricante = fabricanteRepository.findById(produto.getFabricante().getId());
            if (fabricante.isPresent()) {
                produto.setFabricante(fabricante.get());
            } else {
                throw new RuntimeException("Fabricante não encontrado com o ID: " + produto.getFabricante().getId());
            }
        } else {
            throw new RuntimeException("O objeto Fabricante é obrigatório.");
        }

        // Verifica e associa o objeto Grupo
        if (produto.getGrupo() != null) {
            Optional<Grupo> grupo = grupoRepository.findById(produto.getGrupo().getId());
            if (grupo.isPresent()) {
                produto.setGrupo(grupo.get());
            } else {
                throw new RuntimeException("Grupo não encontrado com o ID: " + produto.getGrupo().getId());
            }
        } else {
            throw new RuntimeException("O objeto Grupo é obrigatório.");
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
        return obj.orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }
}
