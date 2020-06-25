package br.com.banco.inter.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErroGeral> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ErroGeral err = new ErroGeral(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}


	@ExceptionHandler(ErroValidacaoException.class)
	public ResponseEntity<ErroValidacao> erroValidacao(ErroValidacaoException e, HttpServletRequest request) {
		ErroValidacao erroValidacao = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro na validação de campos");
		erroValidacao.setErrors(e.getErros());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroValidacao);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final ErroValidacao apiError = new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro na validação de campos");
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			apiError.addError(error.getField(), error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			apiError.addError(error.getObjectName(), error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}
