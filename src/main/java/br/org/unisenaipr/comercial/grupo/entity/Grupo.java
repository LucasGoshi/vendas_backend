package br.org.unisenaipr.comercial.grupo.entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_GRUPO")
@SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "S_GRUPO", allocationSize = 1)
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 200)
	private String nome;
	
	@Column(name = "descricao", nullable = false, length = 200)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "grupo_parente_id", referencedColumnName = "id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	private Grupo grupoParente;
    
	public Grupo() {
		super();
	}
	
	public Grupo(Long id, String nome, String descricao, Grupo grupoParente) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.grupoParente = grupoParente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Grupo getGrupoParente() {
		return grupoParente;
	}
	
	public void setGrupoParente(Grupo grupoParente) {
		this.grupoParente = grupoParente;
	}
	
	/**
	 * Usado pelo Jackson ao serializar para JSON, para que o campo "grupoParenteId" exista.
	 */
	public Long getGrupoParenteId() {
		return grupoParente == null ? null : grupoParente.id;
	}
}
