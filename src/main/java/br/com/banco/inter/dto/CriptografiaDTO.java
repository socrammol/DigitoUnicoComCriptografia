package br.com.banco.inter.dto;

import java.security.Key;

public class CriptografiaDTO {

	private String privateKey;
	private String publicKey;
	private Long id;

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPrivateKey() {
		return privateKey;
	}
}
