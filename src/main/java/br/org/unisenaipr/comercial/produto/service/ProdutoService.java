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
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deleteProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findId(long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.get();
	}
}
