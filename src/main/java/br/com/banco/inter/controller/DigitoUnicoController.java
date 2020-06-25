package br.com.banco.inter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.inter.dto.DigitoUnicoDTO;
import br.com.banco.inter.service.DigitoUnicoService;
import io.swagger.v3.oas.annotations.*;

@RestController
@RequestMapping("/digitounico")
public class DigitoUnicoController {
	@Autowired
	DigitoUnicoService digitoUnicoService;
	@Operation(summary = "Api para efetuar o calculo do digito unico e salvar os dados do digito no banco")   
	@PostMapping(path = "/calcular")
	public DigitoUnicoDTO calcularDigitoUnico(@RequestBody DigitoUnicoDTO digito) {
		return digitoUnicoService.calcularDigitoUnico(digito);
	}

}
