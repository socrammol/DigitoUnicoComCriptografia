package br.com.banco.inter.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Object> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ErroGeral err = new ErroGeral(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
