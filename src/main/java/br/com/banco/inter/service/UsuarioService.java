package br.com.banco.inter.service;

import java.util.Optional;

import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.inter.dto.UsuarioDTO;
import br.com.banco.inter.error.ObjectNotFoundException;
import br.com.banco.inter.model.UsuarioModel;
import br.com.banco.inter.repository.UsuarioRepository;



@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public UsuarioModel criarUsuario(UsuarioDTO criarUsuario) {
		UsuarioModel user = setaDadosUsuario(criarUsuario);
		return save(user);
		
		
	}

	private UsuarioModel save(UsuarioModel user) {
		return usuarioRepository.save(user);
	}

	private UsuarioModel setaDadosUsuario(UsuarioDTO criarUsuario) {
		UsuarioModel user = new UsuarioModel();
		// pode - se fazer alguma validaçao posterior
		user.setEmail(criarUsuario.getEmail());
		user.setNome(criarUsuario.getNome());
		return user;
	}

	public UsuarioModel buscaUsuario(Long id) {
		Optional<UsuarioModel> user = usuarioRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("O usuario nao foi encontrado"));
	}

	public UsuarioModel AlterarUsuario(Long id, UsuarioDTO alterarUsuario) {
		UsuarioModel user = buscaUsuario(id);
		if(alterarUsuario.getEmail() != null && !alterarUsuario.getEmail().isEmpty()) {
			user.setEmail(alterarUsuario.getEmail());
		}
		if(alterarUsuario.getNome() != null && !alterarUsuario.getNome().isEmpty()) {
			user.setNome(alterarUsuario.getNome());
		}
		
		return save(user);
	}

	public void deletarUsuario(Long id) {
		UsuarioModel user = buscaUsuario(id);
		usuarioRepository.delete(user);
	}



}
