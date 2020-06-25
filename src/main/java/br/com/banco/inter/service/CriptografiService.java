package br.com.banco.inter.service;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import br.com.banco.inter.error.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.banco.inter.dto.Chaves;

@Service
public class CriptografiService {
	private static final String METODO = "RSA";
	private static final Integer CHAVE = 2048;
	private static final Base64.Encoder CODIFICADOR = Base64.getEncoder();
	private static  String CHAVE_PUBLICA;
	private static  String CHAVE_PRIVADA;
	public static Chaves geraChave() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(METODO);
			keyGen.initialize(CHAVE);
			KeyPair keyPair = keyGen.generateKeyPair();
			CHAVE_PUBLICA = CODIFICADOR.encodeToString(keyPair.getPublic().getEncoded());
			CHAVE_PRIVADA = CODIFICADOR.encodeToString(keyPair.getPrivate().getEncoded());
			Chaves chave = new Chaves();
			chave.setPrivatekey(CHAVE_PRIVADA);
			chave.setPublickey(CHAVE_PUBLICA);
			return chave;
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao gerar as Chaves");
		}
	}

	public static byte[] criptografar(String texto, String chavePublica) {
		try {
			Cipher cipher = Cipher.getInstance(METODO);
			PublicKey publicKey = descodificaChavePublica(chavePublica);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(texto.getBytes());
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao Criptografar");
		}
	}

	public static String descriptografar(byte[] texto, String chavePrivada) {
		try {
			final Cipher cipher = Cipher.getInstance(METODO);
			PrivateKey privateKey = descodificaChavePrivada(chavePrivada);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return new String(cipher.doFinal(texto));
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao Descriptografar");
		}
	}


	private static PublicKey descodificaChavePublica(String chavePublicaBase64) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(chavePublicaBase64.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance(METODO);
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro Chave publica nao e valida.");
		}
	}

	private static PrivateKey descodificaChavePrivada(String chavePrivadaBase64) {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(chavePrivadaBase64.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance(METODO);
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro Chave privada nao e valida.");
		}

	}


}
