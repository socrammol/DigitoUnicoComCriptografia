package br.com.banco.inter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.inter.dto.DigitoUnicoDTO;
import br.com.banco.inter.error.ObjectNotFoundException;
import br.com.banco.inter.model.DigitoUnico;
import br.com.banco.inter.repository.DigitoUnicoRepository;

@Service
public class DigitoUnicoService {
	@Autowired
	DigitoUnicoRepository digitoUnicoRepository;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CachedService cachedService;

	public DigitoUnicoDTO calcularDigitoUnico(DigitoUnicoDTO digito) {
		if (digito.getMultiplicador() < 1) {
			throw new ObjectNotFoundException("multiplicador invalido");
		}
		DigitoUnico digitoUnico = new DigitoUnico();
		digito.setInteiro(verificaSeTemLetra(digito.getInteiro()));
		digito = cachedService.buscaCache(digito);
		if(digito.getResultado() > 0) {
			return digito;
		}
		List<Integer> soma = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < digito.getMultiplicador(); i++) {
			builder.append(digito.getInteiro());
		}

		soma = criandoListaNumerica(builder, soma);
		builder.delete(0, builder.length());
		int resultado = soma(soma, builder);
		digito.setResultado(resultado);
		if(digito.getIdUsuario() != null) {
			digitoUnico.setUsuario(usuarioService.buscaUsuario(digito.getIdUsuario()));
			digitoUnico.setInteiro(digito.getInteiro());
			digitoUnico.setMultiplicador(digito.getMultiplicador());
			digitoUnico.setResultado(digito.getResultado());
			digitoUnicoRepository.save(digitoUnico);
		}
		cachedService.adiciona(digito);
		return digito;
	}

	private String verificaSeTemLetra(String inteiro) {
		
		return inteiro.replaceAll("[^0-9]", "");
	}

	private int soma(List<Integer> numeros, StringBuilder builder) {
		int auxSoma = 0;
		while (numeros.size() > 1) {
			for (Integer integer : numeros) {
				auxSoma += integer;
			}
			builder.append(auxSoma);
			numeros.clear();
			numeros = criandoListaNumerica(builder, numeros);
			if (numeros.size() > 1) {
				builder.delete(0, builder.length());
				auxSoma = 0;
			}
		}
		return auxSoma;
	}

	private List<Integer> criandoListaNumerica(StringBuilder builder, List<Integer> soma) {
		String[] numbers = builder.toString().split("");
		for (String number : numbers) {
			soma.add(Integer.valueOf(number));
		}
		return soma;
	}

	public void verificaCache(DigitoUnicoDTO digito) {
		// TODO Auto-generated method stub
		
	}

}
