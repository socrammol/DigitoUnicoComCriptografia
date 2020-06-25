package br.com.banco.inter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDTO {
	@NotBlank(message = "{name.not.blank}")
	private String nome;
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	private String email;
	private String privateKey;

	public UsuarioDTO(@NotBlank(message = "{name.not.blank}") String nome,
			@NotBlank(message = "{email.not.blank}") @Email(message = "{email.not.valid}") String email) {
		this.nome = nome;
		this.email = email;
		
	}
	public UsuarioDTO(@NotBlank(message = "{name.not.blank}") String nome,
			@NotBlank(message = "{email.not.blank}") @Email(message = "{email.not.valid}") String email,
			String privateKey) {
		this.nome = nome;
		this.email = email;
		this.privateKey = privateKey;
	}

	public UsuarioDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
