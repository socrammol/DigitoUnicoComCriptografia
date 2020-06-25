package br.com.banco.inter.service;

import java.util.Base64;
import java.util.Optional;

import br.com.banco.inter.dto.CriptografiaDTO;
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
	@Autowired
	CriptografiService criptografiService;

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

	public Object criptografaDados(CriptografiaDTO chave)  {
		UsuarioModel usuario = buscaUsuario(chave.getId());
		UsuarioModel user = criptografa(chave, usuario);
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuarioRepository.save(usuario);
		return user;
	}

	private UsuarioModel criptografa(CriptografiaDTO chave, UsuarioModel usuario) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] textoCriptografado = criptografiService.criptografar(usuario.getNome(), chave.getPublicKey());
		usuario.setNome(encoder.encodeToString(textoCriptografado));
		textoCriptografado = criptografiService.criptografar(usuario.getEmail(), chave.getPublicKey());
		usuario.setEmail(encoder.encodeToString(textoCriptografado));
		return usuario;
	}


	public Object descriptografar(CriptografiaDTO crip) {
		UsuarioModel usuario = buscaUsuario(crip.getId());
		UsuarioModel user = degrip(crip, usuario);
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuarioRepository.save(usuario);
		return user;
	}

	private UsuarioModel degrip(CriptografiaDTO crip, UsuarioModel usuario)  {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] textoCriptografado = decoder.decode(usuario.getNome());
		String textoDesCriptografado = criptografiService.descriptografar(textoCriptografado, crip.getPrivateKey());
		usuario.setNome(textoDesCriptografado);
		textoCriptografado = decoder.decode(usuario.getEmail());
		textoDesCriptografado = criptografiService.descriptografar(textoCriptografado, crip.getPrivateKey());
		usuario.setEmail(textoDesCriptografado);
		return usuario;
	}
}
