package br.com.banco.inter.controller;

import br.com.banco.inter.dto.UsuarioDTO;
import br.com.banco.inter.model.UsuarioModel;
import br.com.banco.inter.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usarioSerive;
	//criação
	@PostMapping(path = "/criar")
	public UsuarioModel create(@Valid @RequestBody UsuarioDTO criarUsuario) {
		return usarioSerive.criarUsuario(criarUsuario);
	}
	//busca
	@GetMapping(path = "/buscar/{id}")
	public UsuarioModel buscarUsuario(@PathVariable Long id) {
		return usarioSerive.buscaUsuario(id);
	}
	//alteração
	@PutMapping(path = "/alterar/{id}")
	public UsuarioModel alterarUsuario(@PathVariable Long id,@RequestBody UsuarioDTO AlterarUsuario) {
		return usarioSerive.AlterarUsuario(id,AlterarUsuario);
	}
	//exclusao
	@DeleteMapping(path = "/delete/{id}")
	public void deletarUsuario(@PathVariable Long id) {
		usarioSerive.deletarUsuario(id);
	}

}
