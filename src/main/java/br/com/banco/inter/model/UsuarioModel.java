package br.com.banco.inter.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
@Entity
public class UsuarioModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome", nullable = false, length = 5000)
	private String nome;
	@Column(name = "email", nullable = false, length = 5000)
	private String email;
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<DigitoUnico> digitos = new ArrayList<>();

	public UsuarioModel() {
	}

	public UsuarioModel(Long id, String nome, String email, List<DigitoUnico> digitos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.digitos = digitos;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<DigitoUnico> getDigitos() {
		return digitos;
	}

	public void setDigitos(List<DigitoUnico> digitos) {
		this.digitos = digitos;
	}

}
