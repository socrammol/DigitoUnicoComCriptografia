package br.com.banco.inter.dto;

import javax.validation.constraints.NotBlank;

public class DigitoUnicoDTO {
	@NotBlank(message = "{inteiro não pode ser nulo ou branco}")
	private String inteiro;
	int multiplicador;
	private Long idUsuario;
	private int resultado;

	public DigitoUnicoDTO() {
	}

	public DigitoUnicoDTO(String inteiro, int multiplicador, Long idUsuario, int resultado) {
		this.inteiro = inteiro.replaceAll("[^0-9]", "");
		this.multiplicador = multiplicador;
		this.idUsuario = idUsuario;
		this.resultado = resultado;
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "DigitoUnicoDTO [inteiro=" + inteiro + ", multiplicador=" + multiplicador + ", idUsuario=" + idUsuario
				+ ", resultado=" + resultado + "]";
	}

}
