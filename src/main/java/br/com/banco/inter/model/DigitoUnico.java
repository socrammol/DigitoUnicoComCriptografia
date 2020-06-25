package br.com.banco.inter.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
@Entity
public class DigitoUnico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Schema(required = true, example = "123456")
	private String inteiro;
	@Schema(required = true, example = "3")
	private int multiplicador;
	private int resultado;
	@JsonIgnore
	@ManyToOne
	private UsuarioModel usuario;

	public DigitoUnico(Long id, String inteiro, int multiplicador, int resultado, UsuarioModel usuario) {
		this.id = id;
		this.inteiro = inteiro.replaceAll("[^0-9]", "");
		this.multiplicador = multiplicador;
		this.resultado = resultado;
		this.usuario = usuario;
	}

	public DigitoUnico() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInteiro() {
		return inteiro;
	}

	public void setInteiro(String inteiro) {
		this.inteiro = inteiro;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
