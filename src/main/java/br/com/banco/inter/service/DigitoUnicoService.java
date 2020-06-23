package br.com.banco.inter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.inter.dto.DigitoUnicoDTO;
import br.com.banco.inter.error.ObjectNotFoundException;

@Service
public class DigitoUnicoService {

	public DigitoUnicoDTO calcularDigitoUnico(DigitoUnicoDTO digito) {
		if (digito.getMultiplicador() < 1) {
			throw new ObjectNotFoundException("multiplicador invalido");
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
		return digito;
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

}
