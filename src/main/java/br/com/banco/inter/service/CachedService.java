package br.com.banco.inter.service;

import br.com.banco.inter.dto.DigitoUnicoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CachedService {
	// adicionando cache
	private static List<DigitoUnicoDTO> CacheDigito = new ArrayList();

	public static void adiciona(DigitoUnicoDTO digitoUnicoDTO) {
		boolean cheio = verificaSeOCacheEstaCheio();
		if (cheio) {
			// removendo o parametro mais antigo
			CacheDigito.remove(0);
		}
		// adicionando parametro
		CacheDigito.add(digitoUnicoDTO);
	}

	// verificando se o cache esta cheio
	private static boolean verificaSeOCacheEstaCheio() {
		if (CacheDigito.size() == 10) {
			return true;
		}
		return false;
	}

	public DigitoUnicoDTO buscaCache(DigitoUnicoDTO digito) {
		for (DigitoUnicoDTO digitoUnicoDTO : CacheDigito) {
			if (digito.getInteiro().equals(digitoUnicoDTO.getInteiro())
					&& digito.getMultiplicador() == digitoUnicoDTO.getMultiplicador()
					&& digito.getIdUsuario() == null || digito.getInteiro().equals(digitoUnicoDTO.getInteiro())
					&& digito.getMultiplicador() == digitoUnicoDTO.getMultiplicador()
					&& digito.getIdUsuario() != null && digito.getIdUsuario() == digitoUnicoDTO.getIdUsuario()) {
				return digitoUnicoDTO;
			}
		}
		return digito;
	}
}
