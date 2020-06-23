package br.com.banco.inter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuarioModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
//	@ManyToOne
//	private List<DigitoUnico> digitos = new ArrayList<>();

	public UsuarioModel() {
	}

	public UsuarioModel(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		//this.digitos = digitos;
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
//
//	public List<DigitoUnico> getDigitos() {
//		return digitos;
//	}
//
//	public void setDigitos(List<DigitoUnico> digitos) {
//		this.digitos = digitos;
//	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email +  "]";
	}
	

}
