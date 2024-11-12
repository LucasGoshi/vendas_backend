package br.org.unisenaipr.comercial.usuario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "S_USUARIO", allocationSize = 1)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "usuario", nullable = false, length = 200, unique = true)
	private String usuario;
	
	@Column(name = "email", nullable = false, length = 200, unique = true)
	private String email;	

	@Column(name = "senha", nullable = false, length = 200)
	private String senha;
	
	@Column(name = "administradorCargo", nullable = false)
	private boolean administrador;
	
	public Usuario() {
		super();
	}

	public Usuario(long id, String usuario, String email, String senha, boolean administrador) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.administrador = administrador;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
