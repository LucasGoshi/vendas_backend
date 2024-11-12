package br.org.unisenaipr.comercial.venda.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.venda.entity.Venda;

@Repository("vendaRepository")
public interface VendaRepository extends JpaRepository<Venda, Long> {
	List<Venda> findAll();

    List<Venda> findAllByDateTimeBetween(
    	      Date publicationTimeStart,
    	      Date publicationTimeEnd);
}
