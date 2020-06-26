package br.com.banco.inter.controller;

import br.com.banco.inter.dto.Chaves;
import br.com.banco.inter.dto.CriptografiaDTO;
import br.com.banco.inter.service.CriptografiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.banco.inter.service.UsuarioService;


@RestController
@RequestMapping("/criptografia")
public class CriptografiaController {

	@Autowired
	CriptografiService criptografiServiceteste;
	@Autowired
	UsuarioService usuario;
	@Operation(summary = "Api para efetuar a criptografia e descriptografia dos usuarios")
	@GetMapping
	public Chaves criaChaves() {
		 return criptografiServiceteste.geraChave();
	}

	@PostMapping
	public Object EnCrip(@RequestBody CriptografiaDTO crip)  {
		return usuario.criptografaDados(crip);
	}
	@PostMapping(path = "/")
	public Object DesCrip(@RequestBody CriptografiaDTO crip)  {
		return usuario.descriptografar(crip);
	}


}
