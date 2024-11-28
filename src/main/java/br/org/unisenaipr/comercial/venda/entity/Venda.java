package br.org.unisenaipr.comercial.venda.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import br.org.unisenaipr.comercial.produto.entity.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TB_VENDA")
@SequenceGenerator(name = "SEQ_VENDA", sequenceName = "S_VENDA", allocationSize = 1)
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENDA")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Produto produto;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date dateTime;
	
	public Venda() {
		super();
	}
	
	public Venda(Long id, int quantidade, Produto produto, Date dateTime) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.produto = produto;
		this.dateTime = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDateTime() {
		return dateTime;
	}
	
	/**
	 * Ao inserir entidade, registrar campo de data de criação
	 */
    @PrePersist
    protected void prePersist() {
        if (this.dateTime == null) dateTime = new Date();
    }
}
