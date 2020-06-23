package br.com.banco.inter.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DigitoUnico implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String inteiro;
	private int multiplicador;
	private int resultado;

	public DigitoUnico(Long id, String inteiro, int multiplicador, int resultado) {
		this.id = id;
		this.inteiro = inteiro;
		this.multiplicador = multiplicador;
		this.resultado = resultado;
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

	@Override
	public String toString() {
		return "digitoUnico [id=" + id + ", inteiro=" + inteiro + ", multiplicador=" + multiplicador + ", resultado="
				+ resultado + "]";
	}

}
