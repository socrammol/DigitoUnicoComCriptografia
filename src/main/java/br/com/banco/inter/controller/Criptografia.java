package br.com.banco.inter.controller;

import br.com.banco.inter.dto.Chaves;
import br.com.banco.inter.dto.CriptografiaDTO;
import br.com.banco.inter.service.CriptografiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.banco.inter.service.UsuarioService;


@RestController
@RequestMapping("/criptografia")
public class Criptografia {
	@Autowired
	CriptografiService criptografiServiceteste;
	@Autowired
	UsuarioService usuario;

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
		//riptografiServiceteste.isChavesValida(crip.getPublicKey(),crip.getPrivateKey());
	}


}
