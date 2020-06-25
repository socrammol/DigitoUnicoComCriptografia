package br.com.banco.inter.error;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<FieldMessage> erros = new ArrayList<>();

	public ErroValidacaoException(String msg, List<FieldMessage> erros) {
		super(msg);
		this.erros = erros;
	}
	
	public ErroValidacaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void setErros(List<FieldMessage> erros) {
		this.erros = erros;
	}
	
	
}
