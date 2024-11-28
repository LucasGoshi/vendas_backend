package br.org.unisenaipr.comercial.venda.service;

import java.util.Calendar;
import java.util.Date;
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
import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.repositorio.VendaRepository;

@Service
public class VendaService {
	private VendaRepository vendaRepository;
	
	@Autowired
	public VendaService(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	public Venda saveVenda(Venda venda) {
		return vendaRepository.save(venda);
	}

	public Venda updateVenda(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public void deleteVenda(Venda venda) {
		vendaRepository.delete(venda);
	}
	
	public List<Venda> findAll() {
		return vendaRepository.findAll();
	}
	
	public List<Venda> findAllInDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date minimo = calendar.getTime();
        Date maximo = calendar.getTime();
		
		return vendaRepository.findAllByDateTimeBetween(minimo, maximo);
	}
	
	public Venda findId(long id) {
		Optional<Venda> obj = vendaRepository.findById(id);
		return obj.get();
	}
}
