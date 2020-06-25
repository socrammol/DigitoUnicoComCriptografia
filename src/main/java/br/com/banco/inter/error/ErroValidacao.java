package br.com.banco.inter.error;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroGeral {
	public ErroValidacao(Integer status, String msg) {
		super(status, msg);
	}

	private List<FieldMessage> erros = new ArrayList<>();


	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String nomeCampo, String mensagem) {
		erros.add(new FieldMessage(nomeCampo, mensagem));
	}

	public void setErrors(List<FieldMessage> erros) {
		this.erros = erros;
		
	}
}
