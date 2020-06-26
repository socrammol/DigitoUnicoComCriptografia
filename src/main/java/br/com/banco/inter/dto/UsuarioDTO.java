package br.com.banco.inter.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDTO {
	@NotBlank(message = "{nome não pode ser nulo ou branco}")
	private String nome;
	@NotBlank(message = "{email não pode ser nulo ou branco}")
	@Email(message = "{email não esta valido}")
	private String email;

	public UsuarioDTO(@NotBlank(message = "{name.not.blank}") String nome,
			@NotBlank(message = "{email.not.blank}") @Email(message = "{email.not.valid}") String email) {
		this.nome = nome;
		this.email = email;
		
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


}
